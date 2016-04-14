package com.fd.fashion.seller.bean;

import java.util.Date;

/** 部门 */
public class DepartmentBean {
	/** 部门ID */
	private Long departmentId;
	/** 部门名称 */
	private String departmentName;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creator;
	/** 备注 */
	private String memo;

	/** 部门ID */
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	/** 部门ID */
	public Long getDepartmentId() {
		return this.departmentId;
	}

	/** 部门名称 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/** 部门名称 */
	public String getDepartmentName() {
		return this.departmentName;
	}

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

	/** 备注 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/** 备注*/
	public String getMemo() {
		return this.memo;
	}
}