package cn.qdgxy.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.Tag;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.service.TagService;

@Service
@SuppressWarnings("unchecked")
public class TagServiceImpl extends DaoSupportImpl<Tag> implements TagService {

	public List<Tag> getTags(User teacher) {
		
		return getSession().createQuery(//
				"FROM Tag WHERE teacher = ?")//
				.setParameter(0, teacher)//
				.list();
	}

	public boolean findTag(String tag, User teacher) {
		List<Tag> list = getSession().createQuery(//
				"FROM Tag t WHERE t.tag = ? AND t.teacher = ?")//
				.setParameter(0, tag)//
				.setParameter(1, teacher)//
				.list();
		if (list.size() == 0) {
			return false;
		}
		return true;
	}

}
