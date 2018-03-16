package cn.qdgxy.oa.service;

import cn.qdgxy.oa.base.DaoSupport;
import cn.qdgxy.oa.domain.Forum;
import cn.qdgxy.oa.domain.Topic;

public interface TopicService extends DaoSupport<Topic> {

	void move(Topic topic, Forum oldForum);

	void delete(Topic topic);

}
