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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mplus.core.entity.base.BaseEntity;
import com.mplus.enums.PriviType;
import com.mplus.enums.PriviTypeConverter;

@Entity
@Table(name = "MP_PRIVILEGE")
public class Privilege extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -7748357850803156457L;

	@Id
    @Column(length=64)
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String priviId;
	
	@Column(length=20, nullable = false, unique = true)
	private String priviCode;
	
	@Column(length=100, nullable = false)
	private String priviName;
	
	@Column(length = 2, nullable = false)
	@Convert(converter = PriviTypeConverter.class)
	private PriviType priviType;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
	private Set<Role> roles = new HashSet<Role>();
	
	public Privilege() {}

	public String getPriviId() {
		return priviId;
	}

	public void setPriviId(String priviId) {
		this.priviId = priviId;
	}

	public String getPriviCode() {
		return priviCode;
	}

	public void setPriviCode(String priviCode) {
		this.priviCode = priviCode;
	}

	public String getPriviName() {
		return priviName;
	}

	public void setPriviName(String priviName) {
		this.priviName = priviName;
	}

	public PriviType getPriviType() {
		return priviType;
	}

	public void setPriviType(PriviType priviType) {
		this.priviType = priviType;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
