package cn.qdgxy.oa.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import cn.qdgxy.oa.domain.Homework;
import cn.qdgxy.oa.domain.Clazz;
import cn.qdgxy.oa.domain.SubmitWork;

import com.opensymphony.xwork2.ActionContext;
/**
 * 用户
 * 
 * @author Guangxing
 * 
 */
@SuppressWarnings({ "unchecked", "serial" })
public class User implements java.io.Serializable {
	private Long id;
	private Department department;
	private Set<Role> roles = new HashSet<Role>();
	/** 1表示学生，2表示老师 */
	private int isTorS;
	
	private Set<Clazz> clazzs = new HashSet<Clazz>(); // 上课的班级
	private Set<Homework> homeworks = new HashSet<Homework>(); // 老师布置作业
	private Set<SubmitWork> submitWorks = new HashSet<SubmitWork>(); // 那个学生提交的作业
	private Set<TeacherCourse> teacherCourses = new HashSet<TeacherCourse>();
	//学生 作业
	private Set<Result> results = new HashSet<Result>();//学生的成绩
	
	private Set<TestTitleId> testTitleIds = new HashSet<TestTitleId>();

	private String loginName; // 登录名
	private String password; // 密码
	private String name; // 真实姓名
	private String gender; // 性别
	private String phoneNumber; // 电话号码
	private String email; // 电子邮件
	private String description; // 说明
	private Set<Tag> tags = new HashSet<Tag>();
	

	/**
	 * 判断当前用户是否有指定名称的权限
	 * 
	 * @param privName
	 *            权限的名称
	 */
	public boolean hasPrivilegeByName(String privName) {
		// 如果是超级管理员，就有所有的权限
		if (isAdmin()) {
			return true;
		}

		// 如果是普通用户，就需要判断权限了
		for (Role role : roles) {
			for (Privilege p : role.getPrivileges()) {
				if (p.getName().equals(privName)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断当前用户是否有指定URL的权限
	 * 
	 * @param privUrl
	 *            权限的URL
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String privUrl) {
		// 如果是超级管理员，就有所有的权限
		if (isAdmin()) {
			return true;
		}

		// a, 去掉后面的参数字符串（如果有）
		int pos = privUrl.indexOf("?");
		if (pos > -1) {
			privUrl = privUrl.substring(0, pos);
		}
		// b, 去掉后面的UI后缀（如果有）
		if (privUrl.endsWith("UI")) {
			privUrl = privUrl.substring(0, privUrl.length() - 2);
		}

		// 如果是普通用户，就需要判断权限了
		// a, 如果这个URL是不需要控制的功能（登录后就能直接使用的），这是应直接返回true
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext
				.getContext().getApplication().get("allPrivilegeUrls");
		if (!allPrivilegeUrls.contains(privUrl)) {
			return true;
		}
		// b, 如果这个URL是需要控制的功能（登录后还得有对应的权限才能使用的），这是应判断权限
		else {
			for (Role role : roles) {
				for (Privilege p : role.getPrivileges()) {
					if (privUrl.equals(p.getUrl())) {
						return true;
					}
				}
			}
			return false;
		}
	}

	/**
	 * 判断当前用户是否是超级管理员
	 */
	public boolean isAdmin() {
		return "admin".equals(loginName);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Clazz> getClazzs() {
		return clazzs;
	}

	public void setClazzs(Set<Clazz> clazzs) {
		this.clazzs = clazzs;
	}

	public Set<Homework> getHomeworks() {
		return homeworks;
	}

	public void setHomeworks(Set<Homework> homeworks) {
		this.homeworks = homeworks;
	}

	public Set<SubmitWork> getSubmitWorks() {
		return submitWorks;
	}

	public void setSubmitWorks(Set<SubmitWork> submitWorks) {
		this.submitWorks = submitWorks;
	}

	public int getIsTorS() {
		return isTorS;
	}

	public void setIsTorS(int isTorS) {
		this.isTorS = isTorS;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<TeacherCourse> getTeacherCourses() {
		return teacherCourses;
	}

	public void setTeacherCourses(Set<TeacherCourse> teacherCourses) {
		this.teacherCourses = teacherCourses;
	}

	public Set<Result> getResults() {
		return results;
	}

	public void setResults(Set<Result> results) {
		this.results = results;
	}

	public Set<TestTitleId> getTestTitleIds() {
		return testTitleIds;
	}

	public void setTestTitleIds(Set<TestTitleId> testTitleIds) {
		this.testTitleIds = testTitleIds;
	}

	

}
