package cn.qdgxy.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 岗位  固定为老师和学生和管理员
 * 
 * @author tyg
 * 
 */
@SuppressWarnings("serial")
public class Role implements java.io.Serializable {
	private Long id;
	private String name;
	private String description;
	
	private Set<User> users = new HashSet<User>();
	private Set<Privilege> privileges = new HashSet<Privilege>();
	
	public Role() {
		
	}
	
	public Role(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

}
