package cn.qdgxy.oa.service.impl;

import java.util.Date;
import org.springframework.stereotype.Service;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.Forum;
import cn.qdgxy.oa.domain.Reply;
import cn.qdgxy.oa.domain.Topic;
import cn.qdgxy.oa.service.ReplyService;

@Service
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements ReplyService {

	public void save(Reply reply) {
		// 1，设置属性并保存
		reply.setPostTime(new Date()); // 当前时间
		getSession().save(reply);

		// 2，更新相关的信息
		Topic topic = reply.getTopic();
		Forum forum = topic.getForum();

		forum.setArticleCount(forum.getArticleCount() + 1); // 版块的文章数量（主题+回复）
		topic.setReplyCount(topic.getReplyCount() + 1); // 主题的回复数量
		topic.setLastUpdateTime(reply.getPostTime()); // 主题的最后更新时间（主题发表的时间或最后回复的时间）
		topic.setLastReply(reply); // 主题的最后发表的回复

		getSession().update(topic);
		getSession().update(forum);
	}

	public void delete(Reply reply) {
		// >>设置主题
		Topic topic = reply.getTopic();
		if( topic.getLastReply() == reply ) {
			Reply lastReply = (Reply) getSession()//
					.createQuery(//
							"FROM Reply WHERE postTime = ( SELECT MAX(postTime) FROM Reply WHERE topic = ? AND id != ? )")//
							.setParameter(0, topic)//
							.setParameter(1, reply.getId())//
							.uniqueResult();
			topic.setLastReply(lastReply);
		}
		topic.setReplyCount(topic.getReplyCount() - 1);
		// >>设置板块
		Forum forum = topic.getForum();
		forum.setArticleCount(forum.getArticleCount() - 1);
		// >>更新到数据库
		getSession().update(topic);
		getSession().update(forum);
		getSession().delete(reply); // 删除回复
	}

}
