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

	public String getInsertBy() {
		return insertBy;
	}

	public void setInsertBy(String insertBy) {
		this.insertBy = insertBy;
	}

	public Date getInsertAt() {
		return insertAt;
	}

	public void setInsertAt(Date insertAt) {
		this.insertAt = insertAt;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

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
	
	@JSONField(name = "state")
	public String getState() {
		return dataState.getCode();
	}
	
	@JSONField(name = "state")
	public void setState(String code) {
		this.dataState = DataState.fromString(code);
	}

}
