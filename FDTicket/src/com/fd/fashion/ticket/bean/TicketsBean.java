package com.fd.fashion.ticket.bean;

import java.util.Date;

/** Tickets */
public class TicketsBean {
	/** 消息ID */
	private Long ticketsId;
	/** 发送时间 */
	private Date sendTime;
	/** 题目 */
	private String title;
	/** 后台身份 */
	private String ticketsSystem;
	/** 问题分类 */
	private Integer questionType;
	/** 最后回复时间 */
	private Date lastReplayTime;
	/** 最后回复人 */
	private String lastRelayer;
	/** 买家阅读时间 */
	private Date buyerReadTime;
	/** 系统后台阅读时间 */
	private Date systemReadTime;
	/**消臭状态  1:未回复,2:已回复,3:关闭*/
	private Integer status;
	/**IDENTITY身份buyer*/
	private Long buyerId;
	/**买家名称*/
	private String buyerName;
	/**身份标识 IDENTITY*/
	private Integer identity;

	/** 消息ID */
	public void setTicketsId(Long ticketsId) {
		this.ticketsId = ticketsId;
	}

	/** 消息ID */
	public Long getTicketsId() {
		return this.ticketsId;
	}

	/** 发送时间 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/** 发送时间 */
	public Date getSendTime() {
		return this.sendTime;
	}

	/** 题目 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** 题目 */
	public String getTitle() {
		return this.title;
	}

	/** 问题分类 */
	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	/** 问题分类 */
	public Integer getQuestionType() {
		return this.questionType;
	}

	/** 最后回复时间 */
	public void setLastReplayTime(Date lastReplayTime) {
		this.lastReplayTime = lastReplayTime;
	}

	/** 最后回复时间 */
	public Date getLastReplayTime() {
		return this.lastReplayTime;
	}

	/** 最后回复人 */
	public void setLastRelayer(String lastRelayer) {
		this.lastRelayer = lastRelayer;
	}

	/** 最后回复人 */
	public String getLastRelayer() {
		return this.lastRelayer;
	}

	/** 买家阅读时间 */
	public void setBuyerReadTime(Date buyerReadTime) {
		this.buyerReadTime = buyerReadTime;
	}

	/** 买家阅读时间 */
	public Date getBuyerReadTime() {
		return this.buyerReadTime;
	}

	/** 系统后台阅读时间 */
	public void setSystemReadTime(Date systemReadTime) {
		this.systemReadTime = systemReadTime;
	}

	/** 系统后台阅读时间 */
	public Date getSystemReadTime() {
		return this.systemReadTime;
	}

	/**消臭状态 1:未回复,2:已回复,3:关闭*/
	public Integer getStatus() {
		return status;
	}

	/**消臭状态 1:未回复,2:已回复,3:关闭*/
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**IDENTITY*/
	public Long getBuyerId() {
		return buyerId;
	}

	/**IDENTITY身份*/
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/** 发送人 */
	public String getTicketsSystem() {
		return ticketsSystem;
	}

	/** 发送人 */
	public void setTicketsSystem(String ticketsSystem) {
		this.ticketsSystem = ticketsSystem;
	}

	/**买家名称*/
	public String getBuyerName() {
		return buyerName;
	}

	/**买家名称*/
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	/**身份标识*/
	public Integer getIdentity() {
		return identity;
	}

	/**身份标识*/
	public void setIdentity(Integer identity) {
		this.identity = identity;
	}
	
	
	
}