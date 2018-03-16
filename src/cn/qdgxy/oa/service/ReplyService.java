package cn.qdgxy.oa.service;

import cn.qdgxy.oa.base.DaoSupport;
import cn.qdgxy.oa.domain.Reply;

public interface ReplyService extends DaoSupport<Reply> {

	void delete(Reply reply);

}
