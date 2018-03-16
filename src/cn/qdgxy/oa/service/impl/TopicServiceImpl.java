package cn.qdgxy.oa.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.Forum;
import cn.qdgxy.oa.domain.Topic;
import cn.qdgxy.oa.service.TopicService;

@Service
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements TopicService {

	public void save(Topic topic) {
		// 1，设置属性并保存
		topic.setType(Topic.TYPE_NORMAL); // 普通帖
		topic.setReplyCount(0);
		topic.setLastReply(null);
		topic.setPostTime(new Date()); // 当前时间
		topic.setLastUpdateTime(topic.getPostTime()); // 默认为主题的发表时间
		getSession().save(topic); // 保存

		// 2，更新相关的信息
		Forum forum = topic.getForum();

		forum.setTopicCount(forum.getTopicCount() + 1); // 主题数量
		forum.setArticleCount(forum.getArticleCount() + 1); // 文章数量（主题+回复）
		forum.setLastTopic(topic); // 最后发表的主题
		getSession().update(forum);
	}

	/** 删除一个主题 */
	public void delete(Topic topic) {
		Forum forum = topic.getForum();
		// >>处理最后发表的主题
		if (forum.getLastTopic() == topic) {
			Topic lastTopic = (Topic) getSession()//
					.createQuery(//
							"FROM Topic WHERE postTime = ( SELECT MAX(postTime) FROM Topic WHERE forum = ? AND id != ?)")//
					.setParameter(0, forum)//
					.setParameter(1, topic.getId())//
					.uniqueResult();
			forum.setLastTopic(lastTopic);
		}
		forum.setTopicCount(forum.getTopicCount() - 1);
		forum.setArticleCount(forum.getArticleCount() - topic.getReplyCount() - 1);//
		getSession().update(forum); // 把板块关联最后发表的主题更新后才能删除本主题
		getSession().delete(topic);
	}

	/** 移动主题到另一个板块 */
	public void move(Topic topic, Forum oldForum) {
		Forum newForum = topic.getForum();
		// >>处理最后发表的主题
		if (oldForum.getLastTopic() == topic) {
			Topic lastTopic = (Topic) getSession()//
					.createQuery(//
							"FROM Topic WHERE postTime = ( SELECT MAX(postTime) FROM Topic WHERE forum = ? )")//
					.setParameter(0, oldForum)//
					.uniqueResult();
			oldForum.setLastTopic(lastTopic);
		}
		if (newForum.getLastTopic() != null) {
			if (topic.getPostTime().after(newForum.getLastTopic().getPostTime())) {
				newForum.setLastTopic(topic);
			}
		} else {
			newForum.setLastTopic(topic);
		}
		// >>处理板块
		oldForum.setTopicCount(oldForum.getTopicCount() - 1);
		oldForum.setArticleCount(oldForum.getArticleCount() - topic.getReplyCount() - 1);
		// ---------------------
		newForum.setTopicCount(newForum.getTopicCount() + 1);
		newForum.setArticleCount(newForum.getArticleCount() + topic.getReplyCount() + 1);
		// ----------------------
		getSession().update(oldForum);
		getSession().update(newForum);
	}

}
