package cn.qdgxy.oa.domain;

import java.util.Date;

/**
 * 测试情况
 * 
 * @author Guangxing
 * 
 */
@SuppressWarnings("serial")
public class OnlineTestCase implements java.io.Serializable {
	private Long id;
	
	private Date startTime;		//发布测试时间
	private Date endTime;		//结束测试时间
	private int number;		//第几次测试
	private int shouldSubmit;//本次测试应交多少
	private int actualSubmit;//实际交了多少
 	
	private Course course;		//关联对应的课程
	private Term thisTerm; // 作业所在的学期
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Term getThisTerm() {
		return thisTerm;
	}
	public void setThisTerm(Term thisTerm) {
		this.thisTerm = thisTerm;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getShouldSubmit() {
		return shouldSubmit;
	}
	public void setShouldSubmit(int shouldSubmit) {
		this.shouldSubmit = shouldSubmit;
	}
	public int getActualSubmit() {
		return actualSubmit;
	}
	public void setActualSubmit(int actualSubmit) {
		this.actualSubmit = actualSubmit;
	}
	
	
	
}
