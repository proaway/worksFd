package com.fd.bean;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7667523056960521889L;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人 */
	private Long operator;

	/** 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 创建时间 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/** 创建人 */
	public void setCreator(Long creator) {
		this.creator = creator;
	}

	/** 创建人 */
	public Long getCreator() {
		return this.creator;
	}

	/** 更新时间 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/** 更新时间 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/** 更新人 */
	public void setOperator(Long operator) {
		this.operator = operator;
	}

	/** 更新人 */
	public Long getOperator() {
		return this.operator;
	}
}
