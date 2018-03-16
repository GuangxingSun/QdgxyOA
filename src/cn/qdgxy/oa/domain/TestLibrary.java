package cn.qdgxy.oa.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 题库信息
 * 
 * @author Guangxing
 *
 */

@SuppressWarnings("serial")
public class TestLibrary implements Serializable{
	private Long id;
	private Date putTime;//更新题目时间
	private String subjectTitle;//题目
	private String optionA;//选项
	private String optionB;//选项
	private String optionC;
	private String optionD;
	private String answer;//正确选项
	private String parse;//解析
	
	private Course course;//属于哪个课程

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPutTime() {
		return putTime;
	}

	public void setPutTime(Date putTime) {
		this.putTime = putTime;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getParse() {
		return parse;
	}

	public void setParse(String parse) {
		this.parse = parse;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
