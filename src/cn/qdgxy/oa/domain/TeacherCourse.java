package cn.qdgxy.oa.domain;

/**
 * 加入了学期，老师每个学期的课程不一样
 * @author guangxing
 *
 */
@SuppressWarnings("serial")
public class TeacherCourse implements java.io.Serializable {
	
	private Long id;
	private User teacher;
	private Long courseId;
	private Long termId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Long getTermId() {
		return termId;
	}
	public void setTermId(Long termId) {
		this.termId = termId;
	}
	public User getTeacher() {
		return teacher;
	}
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
	

}
