package cn.qdgxy.oa.service;

import java.util.List;

import cn.qdgxy.oa.base.DaoSupport;
import cn.qdgxy.oa.domain.Tag;
import cn.qdgxy.oa.domain.User;

public interface TagService extends DaoSupport<Tag> {

	List<Tag> getTags(User teacher);

	boolean findTag(String tag, User teacher);

}
