package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 实体：岗位
 * 
 * @author tyg
 * 
 */
public class Role implements Serializable {
	private Long id;
	private String name;
	private String description;
	private Set<User> users = new HashSet<User>(0);

	private Set<Privilege> privileges = new HashSet<>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
}
