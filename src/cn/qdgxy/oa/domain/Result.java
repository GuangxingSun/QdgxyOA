package cn.qdgxy.oa.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 学生测试成绩
 * 
 * @author Guangxing
 *
 */
public class Result implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date doTest;	//做题时间
	private Date downTest;	//做完时间
	private int count;		//第几次测试
	private int testResult;	//本次测验成绩
	
	//做了还是没有做    
	//0 表示未做      1表示已经做了
	private int yesOrNo;	
	//是否已经抽到题
	//0 表示未抽      1表示已经抽了
	private int isOrNot;
	
	private User student;	//测评的学生
	private Course course;	//属于哪个课程
	private Term term;		//成绩属于哪个学期
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getStudent() {
		return student;
	}
	public void setStudent(User student) {
		this.student = student;
	}
	public Date getDoTest() {
		return doTest;
	}
	public void setDoTest(Date doTest) {
		this.doTest = doTest;
	}
	public Date getDownTest() {
		return downTest;
	}
	public void setDownTest(Date downTest) {
		this.downTest = downTest;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTestResult() {
		return testResult;
	}
	public void setTestResult(int testResult) {
		this.testResult = testResult;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public int getYesOrNo() {
		return yesOrNo;
	}
	public void setYesOrNo(int yesOrNo) {
		this.yesOrNo = yesOrNo;
	}
	public int getIsOrNot() {
		return isOrNot;
	}
	public void setIsOrNot(int isOrNot) {
		this.isOrNot = isOrNot;
	}
	
	
}
