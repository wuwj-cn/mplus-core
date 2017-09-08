package com.mplus.core.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;

import com.mplus.utils.DataState;
import com.mplus.utils.DataStateConverter;

@MappedSuperclass
public abstract class BaseEntity {

	@Column(length=64)
	private String insertBy; // 插入人

	private Date insertAt; // 插入时间

	@Column(length=64)
	private String updateBy; // 修改人

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

	public DataState getDataState() {
		return dataState;
	}

	public void setDataState(DataState dataState) {
		this.dataState = dataState;
	}

}
