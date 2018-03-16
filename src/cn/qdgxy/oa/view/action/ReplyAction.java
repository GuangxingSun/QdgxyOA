package cn.qdgxy.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.qdgxy.oa.base.BaseAction;
import cn.qdgxy.oa.domain.Reply;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ReplyAction extends BaseAction<Reply> {

	private Long topicId;
	private int currentPage;

	/** 发表回复 */
	public String add() throws Exception {
		// 封装对象
		Reply reply = new Reply();
		// a, 表单中的参数
		reply.setContent(model.getContent());
		reply.setTopic(topicService.getById(topicId));
		// b, 在显示层才能获得的数据
		reply.setAuthor(getCurrentUser()); // 当前登录的用户
		reply.setIpAddr(getRequestIP()); // 客户端的IP地址

		// 调用业务方法
		replyService.save(reply);

		return "toTopicShow"; // 转到当前这个新回复所属的主题显示页面
	}

	/** 编辑回帖的页面 */
	public String editUI() throws Exception {
		// 准备数据
		Reply reply = replyService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(reply);
		ActionContext.getContext().getSession().put("currentPage", currentPage); //把回帖所在的页数提交到session保存
		return "edit";
	}
	
	/** 修改回帖 */
	public String edit() throws Exception {
		Reply reply = replyService.getById(model.getId());
		reply.setContent(model.getContent());
		replyService.update(reply);
		setTopicId(reply.getTopic().getId());
		return "toTopicShow"; // 转到当前这个新回复所属的主题显示页面
	}
	
	/** 删除回帖 */
	public String delete() throws Exception {
		Reply reply = replyService.getById(model.getId());
		replyService.delete(reply);
		setTopicId(reply.getTopic().getId());
		ActionContext.getContext().getSession().put("currentPage", currentPage);
		return "toTopicShow";
	}

	// ---

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
