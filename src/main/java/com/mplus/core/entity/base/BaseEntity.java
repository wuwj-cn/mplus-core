package com.mplus.core.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;

import com.alibaba.fastjson.annotation.JSONField;
import com.mplus.enums.DataState;
import com.mplus.enums.DataStateConverter;

@MappedSuperclass
public abstract class BaseEntity {

	@Column(length=64)
	private String insertBy; // 插入人

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date insertAt; // 插入时间

	@Column(length=64)
	private String updateBy; // 修改人

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateAt; // 修改时间

	@Column(length=2, nullable = false)
	@Convert(converter = DataStateConverter.class)
	private DataState dataState; // 数据状态

	public BaseEntity() {
		this.insertAt = this.updateAt = new Date();
		this.dataState = DataState.ENABLE;
	}

	@JSONField(serialize = false)
	public String getInsertBy() {
		return insertBy;
	}

	@JSONField(serialize = false)
	public void setInsertBy(String insertBy) {
		this.insertBy = insertBy;
	}

	@JSONField(serialize = false)
	public Date getInsertAt() {
		return insertAt;
	}

	@JSONField(serialize = false)
	public void setInsertAt(Date insertAt) {
		this.insertAt = insertAt;
	}

	@JSONField(serialize = false)
	public String getUpdateBy() {
		return updateBy;
	}

	@JSONField(serialize = false)
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@JSONField(serialize = false)
	public Date getUpdateAt() {
		return updateAt;
	}

	@JSONField(serialize = false)
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	@JSONField(serialize = false)
	public DataState getDataState() {
		return dataState;
	}

	@JSONField(serialize = false)
	public void setDataState(DataState dataState) {
		this.dataState = dataState;
	}
	
	@JSONField(name = "dState")
	public String getDState() {
		return dataState.getCode();
	}
	
	@JSONField(name = "dState")
	public void setDState(String code) {
		this.dataState = DataState.fromString(code);
	}

}
