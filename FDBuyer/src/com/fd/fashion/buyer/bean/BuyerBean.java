package com.fd.fashion.buyer.bean;

import java.io.Serializable;
import java.util.Date;

import com.fd.util.PageInfo;

/** 买家 */
public class BuyerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 买家内部ID */
	private Long buyerId;
	/** 买家登录邮箱 */
	private String email;
	/** 买家ID */
	private String userId;
	/** 买家名 */
	private String firstName;
	/** 买家姓 */
	private String lastName;
	/** 密码 */
	private String password;
	/** 性别，0-女，1-男 */
	private Integer sex;
	/** 邮箱激活，0-未激活，1-已激活 */
	private Integer emailActive;
	/** 邮箱激活码，注册后自动发送到注册邮箱 */
	private String activeCode;
	/** 买家有效状态，0-无效，1-有效 */
	private Integer vaild;
	/** IP地址 */
	private String ipAddress;
	/** 买家国家 **/
	private String country;
	/**买家身份标识*/
	private Integer buyerType;
	/**公司名*/
	private String company;
	/**用户头像*/
	private String imageUrl;
	/**是否冻结FREEZE*/
	private Integer freeze;
	/**买家注册时间*/
	private Date regDate;
	/**买家级别*/
	private Integer userLevel;
	
	/**注册开始时间*/
	private Date regStartDate;
	/**注册结束时间*/
	private Date regEndDate;
	/**最后购买时间*/
	private Date lastBuyDate;
	
	private PageInfo pageInfo;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public Date getRegStartDate() {
		return regStartDate;
	}

	public void setRegStartDate(Date regStartDate) {
		this.regStartDate = regStartDate;
	}

	public Date getRegEndDate() {
		return regEndDate;
	}

	public void setRegEndDate(Date regEndDate) {
		this.regEndDate = regEndDate;
	}

	public Date getLastBuyDate() {
		return lastBuyDate;
	}

	public void setLastBuyDate(Date lastBuyDate) {
		this.lastBuyDate = lastBuyDate;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	/**是否被冻结
	 * @return
	 */
	public Integer getFreeze() {
		return freeze;
	}

	public void setFreeze(Integer freeze) {
		this.freeze = freeze;
	}

	/**用户头像
	 * @return
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**用户公司
	 * @return
	 */
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	/** 买家内部ID */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/** 买家内部ID */
	public Long getBuyerId() {
		return this.buyerId;
	}

	/** 买家登录邮箱 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** 买家登录邮箱 */
	public String getEmail() {
		return this.email;
	}

	/** 买家ID */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/** 买家ID */
	public String getUserId() {
		return this.userId;
	}

	/** 买家名 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** 买家名 */
	public String getFirstName() {
		return this.firstName;
	}

	/** 买家姓 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** 买家姓 */
	public String getLastName() {
		return this.lastName;
	}

	/** 密码 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 密码 */
	public String getPassword() {
		return this.password;
	}

	/** 性别，0-女，1-男 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/** 性别，0-女，1-男 */
	public Integer getSex() {
		return this.sex;
	}

	/** 邮箱激活，0-未激活，1-已激活 */
	public void setEmailActive(Integer emailActive) {
		this.emailActive = emailActive;
	}

	/** 邮箱激活，0-未激活，1-已激活 */
	public Integer getEmailActive() {
		return this.emailActive;
	}

	/** 邮箱激活码，注册后自动发送到注册邮箱 */
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	/** 邮箱激活码，注册后自动发送到注册邮箱 */
	public String getActiveCode() {
		return this.activeCode;
	}

	/** 买家有效状态，0-无效，1-有效 */
	public void setVaild(Integer vaild) {
		this.vaild = vaild;
	}

	/** 买家有效状态，0-无效，1-有效 */
	public Integer getVaild() {
		return this.vaild;
	}

	/** IP地址 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/** IP地址 */
	public String getIpAddress() {
		return this.ipAddress;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**买家身份标识
	 * @return
	 */
	public Integer getBuyerType() {
		return buyerType;
	}

	public void setBuyerType(Integer buyerType) {
		this.buyerType = buyerType;
	}
	
	
}