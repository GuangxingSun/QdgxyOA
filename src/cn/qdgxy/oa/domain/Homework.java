package cn.qdgxy.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 作业
 * 
 * @author Guangxing
 * 
 */
@SuppressWarnings("serial")
public class Homework implements java.io.Serializable {

	private Long id;
	private String name; // 作业名
	private Date time; // 布置时间
	private Date stime; // 提交的时间
	private int week; // 周次
	private String fileName; // 文件名
	private int submitCount;  // 学生的数量
	private int hasSubmit;  // 已交作业的数量
	
	/** true：公开，false：不公开 */
	private boolean sc;  // 成绩是否公开
	
	/** 是否收取过本作业，0：没有；1：有 */
	private int i;
	
	/** 是否已经交齐，0：没有；1：有 */
	private int j;

	private User teacher; // 老师
	private Course course; // 课
	private Set<Clazz> clazzs = new HashSet<Clazz>(); // 布置作业的对象
	private Set<SubmitWork> submitWorks = new HashSet<SubmitWork>(); // 学生提交的那个作业
	private Term thisTerm; // 作业所在的学期
	
	/** 瞬时状态 作用于学生 */
	private boolean sub;  // 是否提交过
	private int score;  // 获得的评分
	private Long submitWorkId;  // 提交的作用的id
	private Integer day;  // 距今的天数

	public boolean getSc() {
		return sc;
	}

	public void setSc(boolean sc) {
		this.sc = sc;
	}

	public int getHasSubmit() {
		return hasSubmit;
	}

	public void setHasSubmit(int hasSubmit) {
		this.hasSubmit = hasSubmit;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Long getSubmitWorkId() {
		return submitWorkId;
	}

	public void setSubmitWorkId(Long submitWorkId) {
		this.submitWorkId = submitWorkId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isSub() {
		return sub;
	}

	public void setSub(boolean sub) {
		this.sub = sub;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getSubmitCount() {
		return submitCount;
	}

	public void setSubmitCount(int submitCount) {
		this.submitCount = submitCount;
	}

	public Set<SubmitWork> getSubmitWorks() {
		return submitWorks;
	}

	public void setSubmitWorks(Set<SubmitWork> submitWorks) {
		this.submitWorks = submitWorks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public Set<Clazz> getClazzs() {
		return clazzs;
	}

	public void setClazzs(Set<Clazz> clazzs) {
		this.clazzs = clazzs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public Term getThisTerm() {
		return thisTerm;
	}

	public void setThisTerm(Term thisTerm) {
		this.thisTerm = thisTerm;
	}

}
