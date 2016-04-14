package com.fd.fashion.seller.vo;

import java.io.Serializable;
import java.util.Date;

import com.fd.util.PageInfo;

/**
 * @author zhangling
 * 用户查询条件
 *
 */
public class UserSearchVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 用户ID */
	private Long userId;
	/** 角色ID */
	private Long roleId;
	/** 登录名 */
	private String loginName;
	/** 名称 */
	private String userName;
	/** 邮箱 */
	private String email;
	/** 电话 */
	private String phone;
	/** 部门ID */
	private Long departmentId;
	/** 负责行业 */
	private String mainIndustry;
	/** 创建时间 */
	private Date createTimeStart;
	/** 创建时间 */
	private Date createTimeEnd;
	/** 有效性，0-无效，1-有效 */
	private Integer vaild;
	/** 权限级别，0-私有权限，1-所有权限 */
	private Integer priority;
	/**排序字段0.创建时间，1.用户名，2.姓名，3.EMAIL，4.电话，5.部门，6.角色，7.状态*/
	private String orderBy;
	/**升序降序*/
	private String adsc;
	/**分页*/
	private PageInfo pageInfo;
	/**用户查询的哪个名称*/
	private String userNameFlag;
	
	public String getUserNameFlag() {
		return userNameFlag;
	}
	public void setUserNameFlag(String userNameFlag) {
		this.userNameFlag = userNameFlag;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getMainIndustry() {
		return mainIndustry;
	}
	public void setMainIndustry(String mainIndustry) {
		this.mainIndustry = mainIndustry;
	}
	public Date getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public Integer getVaild() {
		return vaild;
	}
	public void setVaild(Integer vaild) {
		this.vaild = vaild;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getAdsc() {
		return adsc;
	}
	public void setAdsc(String adsc) {
		this.adsc = adsc;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	

}
