package com.fd.fashion.seller.bean;

import java.io.Serializable;
import java.util.Date;

/** 系统用户 */
public class UsersBean implements Serializable {
	private static final long serialVersionUID = -7576934302610739858L;
	/** 用户ID */
	private Long userId;
	/** 角色ID */
	private Long roleId;
	/** 登录名 */
	private String loginName;
	/** 名称 */
	private String userName;
	/** 密码 */
	private String psw;
	/** 邮箱 */
	private String email;
	/** 电话 */
	private String phone;
	/** 部门ID */
	private Long departmentId;
	/** 负责行业 */
	private String mainIndustry;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creator;
	/** 修改时间 */
	private Date updateTime;
	/** 修改人 */
	private Long operator;
	/** 有效性，0-无效，1-有效 */
	private Integer vaild;
	/** 权限级别，0-私有权限，1-所有权限 */
	private Integer priority;

	/** 用户ID */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/** 用户ID */
	public Long getUserId() {
		return this.userId;
	}

	/** 角色ID */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/** 角色ID */
	public Long getRoleId() {
		return this.roleId;
	}

	/** 登录名 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/** 登录名 */
	public String getLoginName() {
		return this.loginName;
	}

	/** 名称 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** 名称 */
	public String getUserName() {
		return this.userName;
	}

	/** 密码 */
	public void setPsw(String psw) {
		this.psw = psw;
	}

	/** 密码 */
	public String getPsw() {
		return this.psw;
	}

	/** 邮箱 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** 邮箱 */
	public String getEmail() {
		return this.email;
	}

	/** 电话 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/** 电话 */
	public String getPhone() {
		return this.phone;
	}

	/** 部门ID */
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	/** 部门ID */
	public Long getDepartmentId() {
		return this.departmentId;
	}

	/** 负责行业 */
	public void setMainIndustry(String mainIndustry) {
		this.mainIndustry = mainIndustry;
	}

	/** 负责行业 */
	public String getMainIndustry() {
		return this.mainIndustry;
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

	/** 修改时间 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/** 修改时间 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/** 修改人 */
	public void setOperator(Long operator) {
		this.operator = operator;
	}

	/** 修改人 */
	public Long getOperator() {
		return this.operator;
	}

	/** 有效性，0-无效，1-有效 */
	public void setVaild(Integer vaild) {
		this.vaild = vaild;
	}

	/** 有效性，0-无效，1-有效 */
	public Integer getVaild() {
		return this.vaild;
	}

	/** 权限级别，0-私有权限，1-所有权限 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/** 权限级别，0-私有权限，1-所有权限 */
	public Integer getPriority() {
		return this.priority;
	}
}