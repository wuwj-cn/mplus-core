package com.mplus.core.entity.base;

import java.util.Date;

public abstract class BaseEntity {
	//插入人
	protected Long insertBy;
	//插入时间
	protected Date insertAt;
	//修改人
	protected Long updateBy;
	//修改时间
	protected Date updateAt;
	protected BaseEntity(){
		this.insertAt = this.updateAt = new Date();
	}
	public Long getInsertBy() {
		return insertBy;
	}
	public void setInsertBy(Long insertBy) {
		this.insertBy = insertBy;
	}
	public Date getInsertAt() {
		return insertAt;
	}
	public void setInsertAt(Date insertAt) {
		this.insertAt = insertAt;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
}
