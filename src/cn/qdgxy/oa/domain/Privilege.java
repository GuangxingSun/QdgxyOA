package cn.qdgxy.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 实体：权限
 * 
 * @author tyg
 * 
 */
@SuppressWarnings("serial")
public class Privilege implements java.io.Serializable {
	private Long id;
	private String url;
	private String name;
	private String ice;
	private Set<Role> roles = new HashSet<Role>();

	private Privilege parent;
	private Set<Privilege> children = new HashSet<Privilege>();

	public Privilege() {
	}

	public Privilege(String name, String url, Privilege parent, String ice) {
		this.name = name;
		this.url = url;
		this.parent = parent;
		this.ice = ice;
	}

	public String getIce() {
		return ice;
	}

	public void setIce(String ice) {
		this.ice = ice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Privilege getParent() {
		return parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public Set<Privilege> getChildren() {
		return children;
	}

	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}

}
