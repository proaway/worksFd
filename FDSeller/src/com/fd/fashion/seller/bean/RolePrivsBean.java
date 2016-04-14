package com.fd.fashion.seller.bean;


/** 角色权限 */
public class RolePrivsBean {
	/** 角色ID */
	private Long roleId;
	/** 菜单ID */
	private Long modulesId;

	/** 角色ID */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/** 角色ID */
	public Long getRoleId() {
		return this.roleId;
	}

	/** 菜单ID */
	public void setModulesId(Long modulesId) {
		this.modulesId = modulesId;
	}

	/** 菜单ID */
	public Long getModulesId() {
		return this.modulesId;
	}
}