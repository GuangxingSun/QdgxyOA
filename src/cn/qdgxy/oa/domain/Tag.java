package cn.qdgxy.oa.domain;

/**
 * 教师评分的标记
 * @author HK
 *
 */
public class Tag {

	private Long id;
	private String tag; // 标记
	private String description; // 说明
	private Integer settle; // 处理
	private User teacher;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSettle() {
		return settle;
	}

	public void setSettle(Integer settle) {
		this.settle = settle;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

}
