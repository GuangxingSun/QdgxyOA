package cn.qdgxy.oa.domain;

import java.util.List;

/**
 * 用来封装学生成绩相关信息
 * 
 * @author Guangxing
 *
 */

public class StudentResultCase {
	
	private User student;
	private List<SubmitWork> submitWorklist;
	private List<Result> resultlist;
	private double avg;
	public User getStudent() {
		return student;
	}
	public void setStudent(User student) {
		this.student = student;
	}
	public List<SubmitWork> getSubmitWorklist() {
		return submitWorklist;
	}
	public void setSubmitWorklist(List<SubmitWork> submitWorklist) {
		this.submitWorklist = submitWorklist;
	}
	public List<Result> getResultlist() {
		return resultlist;
	}
	public void setResultlist(List<Result> resultlist) {
		this.resultlist = resultlist;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}

	
	
}
