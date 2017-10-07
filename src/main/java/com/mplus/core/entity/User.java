package com.mplus.core.entity;

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

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;
import com.mplus.core.entity.base.BaseEntity;
import com.mplus.enums.UserState;
import com.mplus.enums.UserStateConverter;
import com.mplus.utils.MD5Util;

@Entity
@Table(name = "MP_USER")
public class User extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -9071755205002858798L;

	@Id
	@Column(length = 64)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String userId;

	@Column(length = 20, nullable = false, unique = true)
	private String userCode;

	@Column(length = 100, nullable = false)
	private String userName;

	@Column(length = 48)
	private String password;

	@Column(length = 100)
	private String zhName;

	@Column(length = 50)
	private String email;

	@Column(length = 2, nullable = false)
	@Convert(converter = UserStateConverter.class)
	private UserState userState;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_ID")
	private Org org;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "MP_USER_ROLE_REL", joinColumns = {
			@JoinColumn(name = "USER_ID")
	}, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID")
	})
	private Set<Role> roles = new HashSet<Role>();

	public User() {
		this.userState = UserState.ENABLE;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = MD5Util.MD5Salt(password);
	}

	public String getZhName() {
		return zhName;
	}

	public void setZhName(String zhName) {
		this.zhName = zhName;
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

}
