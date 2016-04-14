package com.fd.fashion.ticket.vo;

import java.io.Serializable;
import java.util.List;

import com.fd.fashion.ticket.bean.TicketsAttachment;
import com.fd.fashion.ticket.bean.TicketsInfoBean;
import com.fd.util.PageInfo;

/**
 * @author zhangling
 *
 */
public class TicketInfoVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//基本信息
	private TicketsInfoBean ticketInfo;
	
	//附件列表
	private List<AttachmentVo> attachList;
	
	
	public TicketsInfoBean getTicketInfo() {
		return ticketInfo;
	}

	public void setTicketInfo(TicketsInfoBean ticketInfo) {
		this.ticketInfo = ticketInfo;
	}

	public List<AttachmentVo> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<AttachmentVo> attachList) {
		this.attachList = attachList;
	}
	
}
