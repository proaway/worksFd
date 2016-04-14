package com.fd.fashion.ticket.bean;

import java.util.Date;

import com.fd.util.PageInfo;

/** 消息信息 */
public class TicketsInfoBean {
	/** 消息信息ID */
	private Long ticketsInfoId;
	/** 消息ID */
	private Long ticketsId;
	/** 发消息人 */
	private String sender;
	/** 内容 */
	private String message;

	/** 是否发email 0:未发送，1:发送 */
	private Integer email;
	/** 发消息时间 */
	private Date sendTime;
	
	/**身份1:system,2:buyer*/
	private Integer identity;
	
	private PageInfo pageInfo;

	/** 消息信息ID */
	public void setTicketsInfoId(Long ticketsInfoId) {
		this.ticketsInfoId = ticketsInfoId;
	}

	/** 消息信息ID */
	public Long getTicketsInfoId() {
		return this.ticketsInfoId;
	}

	/** 消息ID */
	public void setTicketsId(Long ticketsId) {
		this.ticketsId = ticketsId;
	}

	/** 消息ID */
	public Long getTicketsId() {
		return this.ticketsId;
	}

	/** 发消息人 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/** 发消息人 */
	public String getSender() {
		return this.sender;
	}

	/** 内容 */
	public void setMessage(String message) {
		this.message = message;
	}

	/** 内容 */
	public String getMessage() {
		return this.message;
	}

	/** 是否发email 0:未发送，1:发送 */
	public void setEmail(Integer email) {
		this.email = email;
	}

	/** 是否发email 0:未发送，1:发送 */
	public Integer getEmail() {
		return this.email;
	}

	/** 发消息时间 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/** 发消息时间 */
	public Date getSendTime() {
		return this.sendTime;
	}

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}