package cn.qdgxy.oa.domain;

import java.util.Date;

/**
 * 提交作业
 * 
 * @author Guangxing
 * 
 */
@SuppressWarnings("serial")
public class SubmitWork implements java.io.Serializable {

	private Long id;
	private User student; // 提交作业的学生
	private Homework homework; // 提交的那个作业
	private String fileName; // 文件名
	private Date time; // 时间
	/** 未检查：0, 不合格:1, 良好:2, 中等:3, 优秀:4 */
	private int score;
	private String result;//作业成绩

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

	public Homework getHomework() {
		return homework;
	}

	public void setHomework(Homework homework) {
		this.homework = homework;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	

}
