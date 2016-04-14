package com.fd.fashion.ticket.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fd.fashion.ticket.bean.TicketsBean;
import com.fd.util.PageInfo;

/**
 * @author zhangling
 * 
 * 消息查询条件类
 *
 */
public class SearchTicketVo implements Serializable {

	private static final long serialVersionUID = 8200701996014316270L;
	
	/**查询条件*/
	
	//时间分类(发送时间，最后回复时间)
	private String timeType;
	
	//发送开始时间
	private Date sendTimeStart;
	
	//发送结束时间
	private Date sendTimeEnd;
	
	//回复开始时间
	private Date replyTimeStart;
	
	//回复结束时间
	private Date replyTimeEnd;
	
	//发送人类型
	private String sendType;
	//发送人
	private String sender;
	
	//问题分类
	private Integer questionType;
	
	//消息状态
	private String messType;
	
	//主题关键词
	private String keyword;
	
	private String buyerName;
	
	//分页信息
	private PageInfo pageInfo;
	
	/**返回显示内容*/
	
	//基本信息
	private TicketsBean ticket;
	//对话列表
	private List<TicketInfoVo> ticketInfoList;

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public Date getSendTimeStart() {
		return sendTimeStart;
	}

	public void setSendTimeStart(Date sendTimeStart) {
		this.sendTimeStart = sendTimeStart;
	}

	public Date getSendTimeEnd() {
		return sendTimeEnd;
	}

	public void setSendTimeEnd(Date sendTimeEnd) {
		this.sendTimeEnd = sendTimeEnd;
	}

	public Date getReplyTimeStart() {
		return replyTimeStart;
	}

	public void setReplyTimeStart(Date replyTimeStart) {
		this.replyTimeStart = replyTimeStart;
	}

	public Date getReplyTimeEnd() {
		return replyTimeEnd;
	}

	public void setReplyTimeEnd(Date replyTimeEnd) {
		this.replyTimeEnd = replyTimeEnd;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public String getMessType() {
		return messType;
	}

	public void setMessType(String messType) {
		this.messType = messType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public TicketsBean getTicket() {
		return ticket;
	}

	public void setTicket(TicketsBean ticket) {
		this.ticket = ticket;
	}

	public List<TicketInfoVo> getTicketInfoList() {
		return ticketInfoList;
	}

	public void setTicketInfoList(List<TicketInfoVo> ticketInfoList) {
		this.ticketInfoList = ticketInfoList;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	
}
