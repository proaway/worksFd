package com.fd.fashion.ticket.vo;

import com.fd.fashion.ticket.bean.TicketsAttachment;

public class AttachmentVo {
	
	//附件信息
	private TicketsAttachment attachment;
	//附件类型
	private String attachExt;
	//附件名称
	private String attachName;
	//附件原路径
	private String attachPath;
	
	public TicketsAttachment getAttachment() {
		return attachment;
	}
	public void setAttachment(TicketsAttachment attachment) {
		this.attachment = attachment;
	}
	public String getAttachExt() {
		return attachExt;
	}
	public void setAttachExt(String attachExt) {
		this.attachExt = attachExt;
	}
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	
}
