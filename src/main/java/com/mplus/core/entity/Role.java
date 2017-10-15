package com.mplus.core.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mplus.core.entity.base.BaseEntity;

@Entity
@Table(name = "MP_ROLE")
public class Role extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 6520776568058298929L;

	@Id
    @Column(length=64)
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String roleId;
	
	@Column(length=20, nullable = false, unique = true)
	private String roleCode;
	
	@Column(length=100, nullable = false)
	private String roleName;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "MP_USER_ROLE_REL", joinColumns = {
			@JoinColumn(name = "ROLE_ID")
	}, inverseJoinColumns = {
			@JoinColumn(name = "USER_ID")
	})
	private Set<User> users = new HashSet<User>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "MP_ROLE_PRIVI_REL", joinColumns = {
			@JoinColumn(name = "ROLE_ID")
	}, inverseJoinColumns = {
			@JoinColumn(name = "PRIVI_ID")
	})
	private Set<Permission> privileges = new HashSet<Permission>();
	
	public Role() {
	}
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Permission> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Permission> privileges) {
		this.privileges = privileges;
	}
}
