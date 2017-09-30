/**
 * 
 */
package com.mplus.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mplus.core.entity.base.BaseEntity;

/**
 * @author wuwj
 *
 */
@Entity
@Table(name = "MP_ORG")
public class Org extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -5221776501169665796L;
	
	@Id
    @Column(length=64)
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String orgId;
	
	@Column(length=20, nullable = false, unique = true)
	private String orgCode;
	
	@Column(length=100, nullable = false)
	private String orgName;
	
	@Column(length=64)
	private String parentOrgId;
	
	@Column(length=255)
	private String remark;
	
	public Org(){}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
