package com.fd.statistics.bean;

import java.io.Serializable;
import java.util.Date;

/** 访问统计 - 会话 */
public class VisitSessionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1095974888588533773L;
	/** id主键 */
	private Long visitId;
	/** 访问session */
	private String sessionId;
	/** 访问时间 */
	private Date visitTime;
	/** 访问IP(十进制) */
	private Long ipAddress;
	/** 语言 */
	private String language;
	/** 访问者编码 */
	private String visitorCode;
	/** 访问着陆页 */
	private String landingPage;
	/** IP地址来源国际 */
	private String country;
	/** 访问IP(字符串) */
	private String ip;
	/** 操作系统 */
	private String system;
	/** 用户AGENT 信息 */
	private String userAgent;
	/** 浏览器 */
	private String browser;
	/** 访问用户 */
	private String buyer;

	/** id主键 */
	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}

	/** id主键 */
	public Long getVisitId() {
		return this.visitId;
	}

	/** 访问session */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/** 访问session */
	public String getSessionId() {
		return this.sessionId;
	}

	/** 访问时间 */
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	/** 访问时间 */
	public Date getVisitTime() {
		return this.visitTime;
	}

	/** 访问IP(十进制) */
	public void setIpAddress(Long ipAddress) {
		this.ipAddress = ipAddress;
	}

	/** 访问IP(十进制) */
	public Long getIpAddress() {
		return this.ipAddress;
	}

	/** 语言 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/** 语言 */
	public String getLanguage() {
		return this.language;
	}

	/** 访问者编码 */
	public void setVisitorCode(String visitorCode) {
		this.visitorCode = visitorCode;
	}

	/** 访问者编码 */
	public String getVisitorCode() {
		return this.visitorCode;
	}

	/** 访问着陆页 */
	public void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}

	/** 访问着陆页 */
	public String getLandingPage() {
		return this.landingPage;
	}

	/** IP地址来源国际 */
	public void setCountry(String country) {
		this.country = country;
	}

	/** IP地址来源国际 */
	public String getCountry() {
		return this.country;
	}

	/** 访问IP(字符串) */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/** 访问IP(字符串) */
	public String getIp() {
		return this.ip;
	}

	/** 操作系统 */
	public void setSystem(String system) {
		this.system = system;
	}

	/** 操作系统 */
	public String getSystem() {
		return this.system;
	}

	/** 用户AGENT 信息 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/** 用户AGENT 信息 */
	public String getUserAgent() {
		return this.userAgent;
	}

	/** 浏览器 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	/** 浏览器 */
	public String getBrowser() {
		return this.browser;
	}

	/**
	 * @return the buyerId
	 */
	public String getBuyer() {
		return buyer;
	}

	/**
	 * @param buyerId
	 *            the buyerId to set
	 */
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
}