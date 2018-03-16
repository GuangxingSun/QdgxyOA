package cn.qdgxy.oa.view.action;

import java.util.HashSet;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.qdgxy.oa.base.BaseAction;
import cn.qdgxy.oa.domain.Clazz;
import cn.qdgxy.oa.domain.Course;
import cn.qdgxy.oa.domain.Department;
import cn.qdgxy.oa.domain.Role;
import cn.qdgxy.oa.domain.User;
import cn.qdgxy.oa.util.DepartmentUtils;
import cn.qdgxy.oa.util.QueryHelper;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class UserAction extends BaseAction<User> {

	private Long departmentId;
	private Long[] roleIds;
	private Long[] clazzIds;
	private Long[] courseIds;
	private String oldPassword;

	/** 列表 */
	public String list() throws Exception {
		// 分页数据
		new QueryHelper(User.class, "u").preparePageBean(userService, pageNum,
				pageSize);
		return "list";
	}

	/** 按条件筛选，列出用户 */
	public String filtrate() throws Exception {
		return "filtrate";
	}

	/** 按照学号进行查询学生 */
	@SuppressWarnings("null")
	public String filtrate_loginName() throws Exception {
		User user = userService.getByLoginName(model.getLoginName());
		//如果没有该用户
		if (user==null) {
			ServletActionContext 
					.getResponse()
					.getWriter()
					.print(//
					"<script language=javascript>alert('对不起！没有查询到您查询的用户！！');history.go(-1);</script>");
		 
			System.out.println("*-------------*");
			return null;
		}else{
		Role role = new Role();
		if(user.getIsTorS()==1){
			role.setName("学生");
		}else{
			role.setName("老师");
		}  
		//ActionContext.getContext().getValueStack().push(role);
		ActionContext.getContext().put("role", role);
		ActionContext.getContext().getValueStack().push(user); 
		
		System.out.println("姓名："+user.getName());
		System.out.println("role====:"+role.getName());
		return "list_one";
		}
	}

	/** 删除 */
	public String delete() throws Exception {
		userService.delete(userService.getById(model.getId()));
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备数据：departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(
				topList, null);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据：roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		// 准备数据： course
		List<Course> courseList = courseService.findAll();
		ActionContext.getContext().put("courseList", courseList);

		// 准备数据： clazz
		List<Clazz> clazzList = clazzService.findAll();
		ActionContext.getContext().put("clazzList", clazzList);

		List<Integer> gradeList = clazzService.findGrade();
		ActionContext.getContext().put("gradeList", gradeList);
		List<String> majorList = clazzService.findMajor();
		ActionContext.getContext().put("majorList", majorList);

		return "saveUI";

	}

	/** 添加 */
	public String add() throws Exception {
		// 判断用户名是否存在
		boolean loginName = userService.judge(model.getLoginName());
		if (!loginName) {
			ServletActionContext
					.getResponse()
					.getWriter()
					.print(//
					"<script language=javascript>alert('此登入名已被注册！');history.go(-1);</script>");
			return null;
		}
		// 封装对象
		// >> 处理关联的一个部门
		model.setDepartment(departmentService.getById(departmentId));
		// >> 处理关联的多个岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		// >> 处理关联的班级
		List<Clazz> clazzList = clazzService.getByIds(clazzIds);
		model.setClazzs(new HashSet<Clazz>(clazzList));
		//>>处理关联的课程（指的是老师教授的课程）
		
		
		// >> 处理关联的课程 把数据存到TeacherCourse表里面，加上学期
		// List<Course> courseList = courseService.getByIds(courseIds);
		userService.save(model, courseIds); // 已变成持久化对象

		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		// >> 处理部门
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		// >> 处理岗位
		roleIds = new Long[user.getRoles().size()];
		int index = 0;
		for (Role role : user.getRoles()) {
			roleIds[index++] = role.getId();
		}
		// >> 处理班级
		clazzIds = new Long[user.getClazzs().size()];
		index = 0; 
		for (Clazz clazz : user.getClazzs()) {
			clazzIds[index++] = clazz.getId();
		}
		// >> 处理课程
		List<Course> thisTermCourseList = teacherCourseService.getCourses(user,
				thisTerm());
		courseIds = new Long[thisTermCourseList.size()];
		index = 0;
		for (Course course : thisTermCourseList) {
			courseIds[index++] = course.getId();
		}

		// 准备数据：departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(
				topList, null);
		ActionContext.getContext().put("departmentList", departmentList);

		
		// 准备数据：roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		
		// 准备数据： clazz
		List<Clazz> clazzList = clazzService.findAll();
		ActionContext.getContext().put("clazzList", clazzList);

		
		// 准备数据： course
		List<Course> courseList = courseService.findAll();
		ActionContext.getContext().put("courseList", courseList);

		List<Integer> gradeList = clazzService.findGrade();
		ActionContext.getContext().put("gradeList", gradeList);
		List<String> majorList = clazzService.findMajor();
		ActionContext.getContext().put("majorList", majorList);

		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());
		User oldUser = new User();
		oldUser.setRoles(user.getRoles());
		oldUser.setClazzs(user.getClazzs());
		// 2，设置要修改的属性
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		user.setName(model.getName());
		user.setGender(model.getGender());
		// >> 处理关联的一个部门
		user.setDepartment(departmentService.getById(departmentId));
		// >> 处理关联的多个岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		// >> 处理关联的班级
		List<Clazz> clazzList = clazzService.getByIds(clazzIds);
		user.setClazzs(new HashSet<Clazz>(clazzList));
		// >> 处理关联的课程
		// List<Course> courseList = courseService.getByIds(courseIds);
		// user.setCourses(new HashSet<Course>(courseList));

		// 3，更新到数据库
		userService.update(user, oldUser, courseIds);

		return "toList";
	}

	/** 初始化密码为1234 */
	public String initPassword() throws Exception {
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());

		// 2，设置要修改的属性
		String md5 = DigestUtils.md5Hex("1234"); // 密码要使用MD5摘要
		user.setPassword(md5);

		// 3，更新到数据库
		userService.update(user);
		return "toList";
	}

	public String toEditPassword() throws Exception {
		return "editPassword";
	}

	public String editPassword() throws Exception {
		User user = userService.getById(model.getId());
		if (user.getPassword().equals(DigestUtils.md5Hex(oldPassword.trim()))) {
			user.setPassword(DigestUtils.md5Hex(model.getPassword()));
			userService.update(user);
			ServletActionContext
					.getResponse()
					.getWriter()
					.print(//
					"<script language=javascript>alert('密码修改成功！');history.go(-1);</script>");
		} else {
			ServletActionContext
					.getResponse()
					.getWriter()
					.print(//
					"<script language=javascript>alert('原始密码错误！');history.go(-1);</script>");
		}
		return null;
	}

	public String info() throws Exception {
		// 准备回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		// >> 处理部门
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		Department department = departmentService.getById(departmentId);
		ActionContext.getContext().put("department", department);

		// >> 处理岗位
		roleIds = new Long[user.getRoles().size()];
		int index = 0;
		for (Role role : user.getRoles()) {
			roleIds[index++] = role.getId();
		}
		List<Role> roleList = roleService.getByIds(roleIds);
		if (roleList.isEmpty()) {
			ActionContext.getContext().put("roleName", "无");
		} else {
			ActionContext.getContext().put("roleList", roleList);
		}

		// >> 处理班级
		clazzIds = new Long[user.getClazzs().size()];
		index = 0;
		for (Clazz clazz : user.getClazzs()) {
			clazzIds[index++] = clazz.getId();
		}
		List<Clazz> clazzList = clazzService.getByIds(clazzIds);
		if (clazzList.isEmpty()) {
			ActionContext.getContext().put("clazzName", "无");
		} else {
			ActionContext.getContext().put("clazzList", clazzList);
		}
		// >> 处理课程
		List<Course> thisTermCourseList = teacherCourseService.getCourses(user,
				thisTerm());
		courseIds = new Long[thisTermCourseList.size()];
		index = 0;
		for (Course course : thisTermCourseList) {
			courseIds[index++] = course.getId();
		}
		if (thisTermCourseList.isEmpty()) {
			ActionContext.getContext().put("courseName", "无");
		} else {
			ActionContext.getContext().put("courseList", thisTermCourseList);
		}

		return "info";
	}

	public String infoEdit() throws Exception {
		User user = userService.getById(model.getId());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		userService.update(user);
		ServletActionContext
				.getResponse()
				.getWriter()
				.print(//
				"<script language=javascript>alert('个人信息修改成功！');history.go(-1);</script>");
		return null;
	}

	public String intoInfoUI() throws Exception {
		if (!model.getId().equals(getCurrentUser().getId())
				|| getCurrentUser().isAdmin()) {
			return "noPrivilegeUI";
		}
		User user = getCurrentUser();
		if (isStudent()) { // 如果是学生
			// >> 处理班级
			clazzIds = new Long[user.getClazzs().size()];
			int index = 0;
			for (Clazz clazz : user.getClazzs()) {
				clazzIds[index++] = clazz.getId();
			}
			List<Clazz> clazzList = clazzService.findAll();
			ActionContext.getContext().put("clazzList", clazzList);
			List<Integer> gradeList = clazzService.findGrade();
			ActionContext.getContext().put("gradeList", gradeList);
			List<String> majorList = clazzService.findMajor();
			ActionContext.getContext().put("majorList", majorList);
		}
		if (isTeacher()) { // 如果是老师
			// >> 处理课程
			List<Course> thisTermCourseList = teacherCourseService.getCourses(
					user, thisTerm());
			courseIds = new Long[thisTermCourseList.size()];
			int index = 0;
			for (Course course : thisTermCourseList) {
				courseIds[index++] = course.getId();
			}
			List<Course> courseList = courseService.findAll();
			ActionContext.getContext().put("courseList", courseList);
		}
		return "intoInfoUI";
	}

	public String intoInfo() throws Exception {
		User user = getCurrentUser();
		User oldUser = new User();
		oldUser.setRoles(user.getRoles());
		oldUser.setClazzs(user.getClazzs());
		// >> 处理关联的班级
		List<Clazz> clazzList = clazzService.getByIds(clazzIds);
		user.setClazzs(new HashSet<Clazz>(clazzList));
		// >>处理关联的课程
		userService.update(user, oldUser, courseIds);
		ServletActionContext
				.getResponse()
				.getWriter()
				.print(//
				"<script language=javascript>alert('数据录入成功！');history.go(-2);</script>");
		return null;
	}

	// ---

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Long[] getClazzIds() {
		return clazzIds;
	}

	public void setClazzIds(Long[] clazzIds) {
		this.clazzIds = clazzIds;
	}

	public Long[] getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(Long[] courseIds) {
		this.courseIds = courseIds;
	}

}
