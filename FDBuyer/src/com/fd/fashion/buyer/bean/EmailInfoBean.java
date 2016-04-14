package com.fd.fashion.buyer.bean;

import java.io.Serializable;
import java.util.Date;

import com.fd.util.PageInfo;

/**
 * @author zhangling
 *
 */
public class EmailInfoBean implements Serializable{

	/**
	 * 买家邮件列表
	 */
	private static final long serialVersionUID = 1L;
	/**ID*/
	private long id;
	/**邮件模板号EMAIL_CODE*/
	private String emailCode;
	/**发送时间SENT_TIME*/
	private Date sendTime;
	/**打开时间OPEN_TIME*/
	private Date openTime;
	/**邮件主题EMAIL_TITLE*/
	private String emailTitle;
	/**邮件类型(1表示系统邮件)EMAIL_TYPE*/
	private String emailType;
	/**BUYER_ID*/
	private long buyerId;
	/**发件人邮箱地址SENDER_EMAIL*/
	private String senderEmail;
	/**收件人邮箱地址RECIPIENT_EMAIL*/
	private String recipientEmail;
	
	private PageInfo pageInfo;
	
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmailCode() {
		return emailCode;
	}
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public String getEmailTitle() {
		return emailTitle;
	}
	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	public long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getRecipientEmail() {
		return recipientEmail;
	}
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}
	
}
