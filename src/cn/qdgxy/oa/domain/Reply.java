package cn.qdgxy.oa.domain;

/**
 * 实体：回帖
 * 
 * @author tyg
 * 
 */
public class Reply extends Article {
	private Topic topic; // 所属的主题

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
