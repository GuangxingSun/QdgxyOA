package cn.qdgxy.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 年级、专业、班级
 * 
 * @author HK
 * 
 */
@SuppressWarnings("serial")
public class Clazz implements java.io.Serializable {

	private Long id;
	private Integer grade; // 年级
	private String major; // 专业
	private String clazz; // 班级
	private int studentCount; // 学生人数

	private Set<User> students = new HashSet<User>();
	private Set<Homework> homeworks = new HashSet<Homework>(); // 学生的作业
	
	private Set<Course> courses = new HashSet<Course>();
	
	public int getStudentCount() {
		return studentCount;
	}
	
	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public Set<User> getStudents() {
		return students;
	}

	public void setStudents(Set<User> students) {
		this.students = students;
	}

	public Set<Homework> getHomeworks() {
		return homeworks;
	}

	public void setHomeworks(Set<Homework> homeworks) {
		this.homeworks = homeworks;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}
