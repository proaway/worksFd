package com.fd.fashion.ticket.bean;

public class TicketsAttachment {

	/**附件ID ATTACHMENT_ID*/
	private Long attachmentId;
	
	/**消息记录ID TICKETS_INFO_ID*/
	private Long ticketsInfoId;
	
	/**URL 附件URL*/
	private String attachUrl;

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Long getTicketsInfoId() {
		return ticketsInfoId;
	}

	public void setTicketsInfoId(Long ticketsInfoId) {
		this.ticketsInfoId = ticketsInfoId;
	}

	public String getAttachUrl() {
		return attachUrl;
	}

	public void setAttachUrl(String attachUrl) {
		this.attachUrl = attachUrl;
	}
	
}
