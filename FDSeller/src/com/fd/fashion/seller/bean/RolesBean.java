package com.fd.fashion.seller.bean;

import java.util.Date;

/** 角色 */
public class RolesBean {
	/** 角色ID */
	private Long roleId;
	/** 角色名称 */
	private String roleName;
	/** 角色描述 */
	private String roleDesc;
	/** 0:删除，1：有效，2：禁用 */
	private Integer status;
	/** 创建者 */
	private Long createBy;
	/** 创建时间 */
	private Date createTime;
	/** 最后修改者 */
	private Long lastUpdateBy;
	/** 最后修改时间 */
	private Date lastUpdateTime;
	
	private Long[] modulesIds;

	/** 角色ID */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/** 角色ID */
	public Long getRoleId() {
		return this.roleId;
	}

	/** 角色名称 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/** 角色名称 */
	public String getRoleName() {
		return this.roleName;
	}

	/** 角色描述 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	/** 角色描述 */
	public String getRoleDesc() {
		return this.roleDesc;
	}

	/** 0:删除，1：有效，2：禁用 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 0:删除，1：有效，2：禁用 */
	public Integer getStatus() {
		return this.status;
	}

	/** 创建者 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	/** 创建者 */
	public Long getCreateBy() {
		return this.createBy;
	}

	/** 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 创建时间 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/** 最后修改者 */
	public void setLastUpdateBy(Long lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	/** 最后修改者 */
	public Long getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	/** 最后修改时间 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/** 最后修改时间 */
	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	/**
	 * @return the modulesIds
	 */
	public Long[] getModulesIds() {
		return modulesIds;
	}

	/**
	 * @param modulesIds the modulesIds to set
	 */
	public void setModulesIds(Long[] modulesIds) {
		this.modulesIds = modulesIds;
	}
}