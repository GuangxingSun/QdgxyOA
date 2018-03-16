package cn.qdgxy.oa.view.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.qdgxy.oa.base.BaseAction;
import cn.qdgxy.oa.domain.Forum;
import cn.qdgxy.oa.domain.Reply;
import cn.qdgxy.oa.domain.Topic;
import cn.qdgxy.oa.util.QueryHelper;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class TopicAction extends BaseAction<Topic> {

	private Long forumId;
	private Long oldForumId;
	private String choose;

	/** 显示单个主题 */
	public String show() throws Exception {
		// 修改或删除回帖时，设置当前回帖的页数
		Integer currentPage = (Integer) ActionContext.getContext().getSession().get("currentPage");
		if (currentPage != null) {
			setPageNum(currentPage);
			ActionContext.getContext().getSession().remove("currentPage");
		}
		// 准备数据: topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);

		// 准备分页的数据 （最终版）
		new QueryHelper(Reply.class, "r")//
				.addWhereCondition("r.topic=?", topic)//
				.addOrderByProperty("r.postTime", true)//
				.preparePageBean(replyService, pageNum, pageSize);

		return "show";
	}

	/** 发新帖页面 */
	public String addUI() throws Exception {
		// 准备数据
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "saveUI";
	}

	/** 发新帖 */
	public String add() throws Exception {
		// 封装对象
		Topic topic = new Topic();
		// >> a, 表单中的参数
		topic.setTitle(model.getTitle());
		topic.setContent(model.getContent());
		topic.setForum(forumService.getById(forumId));
		// >> b, 在显示层才能获得的数据
		topic.setAuthor(getCurrentUser()); // 当前登录的用户
		topic.setIpAddr(getRequestIP()); // 客户端的IP地址

		// 调用业务方法
		topicService.save(topic);

		ActionContext.getContext().put("topicId", topic.getId());
		return "toShow"; // 转到当前这个新主题的显示页面
	}

	public String moveUI() throws Exception {
		// 准备数据: topic
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		forumId = topic.getForum().getId();
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "moveUI";
	}

	/** 移动主题到另一个板块 */
	public String move() throws Exception {
		Topic topic = topicService.getById(model.getId());
		if (oldForumId != forumId) {
			topic.setForum(forumService.getById(forumId));
			topicService.update(topic);
			Forum oldForum = forumService.getById(oldForumId);
			topicService.move(topic, oldForum);
		}
		ActionContext.getContext().put("topicId", topic.getId());
		return "toShow";
	}

	public String editUI() throws Exception {
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(topic);
		return "saveUI";
	}

	public String edit() throws Exception {
		Topic topic = topicService.getById(model.getId());
		if ("1".equals(choose)) { // choose为1时说明是修改主题，不为时是修改主题类型
			topic.setTitle(model.getTitle());
			topic.setContent(model.getContent());
			topic.setLastUpdateTime(new Date()); // 设置最后修改的时间
		} else {
			topic.setType(model.getType());
		}

		topicService.update(topic);
		ActionContext.getContext().put("topicId", topic.getId());
		return "toShow";
	}

	public String delete() throws Exception {
		Topic topic = topicService.getById(model.getId());
		topicService.delete(topic);
		ActionContext.getContext().put("forumId", topic.getForum().getId());
		return "toTopic";
	}

	// ---

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public Long getOldForumId() {
		return oldForumId;
	}

	public void setOldForumId(Long oldForumId) {
		this.oldForumId = oldForumId;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

}
