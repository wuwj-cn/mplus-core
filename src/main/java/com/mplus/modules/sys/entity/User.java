package com.mplus.modules.sys.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mplus.core.entity.base.BaseEntity;
import com.mplus.enums.UserState;
import com.mplus.enums.UserStateConverter;

@Entity
@Table(name = "MP_SYS_USER")
public class User extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -9071755205002858798L;

	@Id
	@Column(length = 64)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String userId;

	@Column(length = 20, nullable = false, unique = true)
	private String userCode;

	@Column(length = 100, nullable = false, unique = true)
	private String username;

	@Column(length = 48)
	private String password;

	@Column(length = 100)
	private String name;

	@Column(length = 50)
	private String email;

	@Column(length = 2, nullable = false)
	@Convert(converter = UserStateConverter.class)
	private UserState userState;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_ID")
	private Org org;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "MP_USER_ROLE_REL", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles = new HashSet<Role>();
	
	@Transient
	private Boolean rememberMe;

	public User() {
		this.userState = UserState.ENABLE;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@JSONField(serialize = false)
	public String getPassword() {
		return password;
	}

	@JSONField(serialize = false)
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JSONField(serialize = false)
	public UserState getUserState() {
		return userState;
	}

	@JSONField(serialize = false)
	public void setUserState(UserState userState) {
		this.userState = userState;
	}

	@JSONField(name = "uState")
	public String getUState() {
		return userState.getCode();
	}

	@JSONField(name = "uState")
	public void setUState(String code) {
		this.userState = UserState.fromString(code);
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	@JSONField(serialize = false)
	public String getCredentialsSalt() {
		return this.password.substring(0, 16);
	}

}
