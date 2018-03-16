package cn.qdgxy.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Term implements java.io.Serializable {

	private Long id;
	private String schoolYear; // 学年
	private String term; // 学期
	private Date time; // 开学时间
	
	
	private Set<Homework> homeworks = new HashSet<Homework>();
	private Set<Result> results = new HashSet<Result>();
	private Set<Course> courses = new HashSet<Course>();
	private Set<OnlineTestCase> onlineTestCases = new HashSet<OnlineTestCase>();
	/** 1表示当前学期 */
	private int thisTerm;
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getThisTerm() {
		return thisTerm;
	}
	public void setThisTerm(int thisTerm) {
		this.thisTerm = thisTerm;
	}
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	public Set<Homework> getHomeworks() {
		return homeworks;
	}
	public void setHomeworks(Set<Homework> homeworks) {
		this.homeworks = homeworks;
	}
	public Set<Result> getResults() {
		return results;
	}
	public void setResults(Set<Result> results) {
		this.results = results;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public Set<OnlineTestCase> getOnlineTestCases() {
		return onlineTestCases;
	}
	public void setOnlineTestCases(Set<OnlineTestCase> onlineTestCases) {
		this.onlineTestCases = onlineTestCases;
	}
	
	
	
}
