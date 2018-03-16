package cn.qdgxy.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.Department;
import cn.qdgxy.oa.service.DepartmentService;

@Service
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements DepartmentService {

	public List<Department> findChildren(Long parentId) {
		return getSession().createQuery(//
				"FROM Department d WHERE d.parent.id=?")//
				.setParameter(0, parentId)//
				.list();
	}

	public List<Department> findTopList() {
		return getSession().createQuery(//
				"FROM Department d WHERE d.parent IS NULL")//
				.list();
	}

}
