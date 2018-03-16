package cn.qdgxy.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.qdgxy.oa.base.DaoSupportImpl;
import cn.qdgxy.oa.domain.Privilege;
import cn.qdgxy.oa.service.PrivilegeService;

@Service
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService {

	public List<Privilege> findTopList() {
		return getSession().createQuery(//
				"FROM Privilege p WHERE p.parent IS NULL")//
				.list();
	}

	public List<String> getAllPrivilegeUrls() {
		return getSession()
				.createQuery(//
						"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")//
				.list();
	}

}
