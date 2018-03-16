package cn.qdgxy.oa.view.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.qdgxy.oa.base.BaseAction;
import cn.qdgxy.oa.domain.Department;
import cn.qdgxy.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class DepartmentAction extends BaseAction<Department> {

	private Long parentId;

	/**
	 * 列表： 列表页面只显示一层的（同级的）部门数据，默认显示最顶级的部门列表
	 */
	public String list() throws Exception {
		List<Department> departmentList = null;
		if (parentId == null) {
			// 默认显示顶级部门列表
			departmentList = departmentService.findTopList();
		} else {
			// 显示指定部门的子部门列表
			departmentList = departmentService.findChildren(parentId);
			if (departmentList.size() == 0) {
				ServletActionContext.getResponse().getWriter().print(//
						"<script language=javascript>history.go(-1);</script>");
				return null;
			}
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	public String addUI() throws Exception {

		// 准备数据（树状结构）
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(
				topList, null);
		ActionContext.getContext().put("departmentList", departmentList);

		return "saveUI";
	}

	public String add() throws Exception {

		// 处理上级部门
		Department parent = departmentService.getById(parentId);
		model.setParent(parent);

		// 保存到数据库
		departmentService.save(model);

		return "toList";
	}

	public String editUI() throws Exception {
		// 准备回显的数据
		Department department = departmentService.getById(model.getId()); // 当前要修改的部门
		ActionContext.getContext().getValueStack().push(department);
		if (department.getParent() != null) {
			this.parentId = department.getParent().getId();
		}

		// 准备数据（树状结构）
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(
				topList, department);
		ActionContext.getContext().put("departmentList", departmentList);

		return "saveUI";
	}

	public String edit() throws Exception {
		// 1，从数据库中取出要修改的原始数据
		Department department = departmentService.getById(model.getId());

		// 2，设置要修改的属性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		// >> 处理上级部门
		Department parent = departmentService.getById(parentId);
		department.setParent(parent);

		// 3，更新到数据库
		departmentService.update(department);

		return "toList";
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
