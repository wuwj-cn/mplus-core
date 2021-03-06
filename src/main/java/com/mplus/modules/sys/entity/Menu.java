package com.mplus.modules.sys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mplus.core.base.entity.BaseEntity;

@Entity
@Table(name = "MP_SYS_MENU")
public class Menu extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 5001754947956101113L;

	@Column(length = 20, nullable = false, unique = true)
	private String menuCode;

	@Column(length = 100, nullable = false, unique = true)
	private String menuName;
	
	@Column(length = 100, nullable = false, unique = true)
	private String url;
	
	@Column(length = 20)
	private String icon;
	
	@Column(length=64)
	private String parentId;

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
