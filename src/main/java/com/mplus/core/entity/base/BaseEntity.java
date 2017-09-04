package com.mplus.core.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;

import com.mplus.utils.DataState;

@MappedSuperclass
public abstract class BaseEntity {

	@Column(length=32)
	private String insertBy; // 插入人

	private Date insertAt; // 插入时间

	@Column(length=32)
	private String updateBy; // 修改人

	private Date updateAt; // 修改时间

	private DataState state; // 数据状态

	public BaseEntity() {
		this.insertAt = this.updateAt = new Date();
		this.state = DataState.ENABLE;
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

	public DataState getState() {
		return state;
	}

	public void setState(DataState state) {
		this.state = state;
	}
}
