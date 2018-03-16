package cn.qdgxy.oa.service;

import java.util.List;

import cn.qdgxy.oa.base.DaoSupport;
import cn.qdgxy.oa.domain.Department;

public interface DepartmentService extends DaoSupport<Department> {

	/**
	 * 查询所有顶级部门的列表
	 * 
	 * @return
	 */
	List<Department> findTopList();

	/**
	 * 查询所有子部门的列表
	 * 
	 * @param parentId
	 * @return
	 */
	List<Department> findChildren(Long parentId);

}
