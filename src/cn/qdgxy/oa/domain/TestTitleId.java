package cn.qdgxy.oa.domain;

/**
 * 考生抽题使用的临时表
 * @author Guangxing
 *
 */
public class TestTitleId {

	private Long id;
	private User student;
	private Course course;
	
	private String testTitleIds;

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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getTestTitleIds() {
		return testTitleIds;
	}

	public void setTestTitleIds(String testTitleIds) {
		this.testTitleIds = testTitleIds;
	}

	
}
