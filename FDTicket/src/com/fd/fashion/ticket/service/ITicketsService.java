package com.fd.fashion.ticket.service;
import java.util.List;

import com.fd.fashion.ticket.bean.TicketsAttachment;
import com.fd.fashion.ticket.bean.TicketsBean;
import com.fd.fashion.ticket.bean.TicketsInfoBean;
import com.fd.fashion.ticket.vo.SearchTicketVo;
import com.fd.util.PageInfo;
/** Tickets接口 */
public interface ITicketsService {
	/**
	 * 增加Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public TicketsBean insertTicketsBean(TicketsBean tickets) throws Exception;
	
	/**
	 * 修改Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public int updateTicketsBean(TicketsBean tickets) throws Exception;
	
	
	/**
	 * 获取Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public TicketsBean getTicketsBean(TicketsBean tickets) throws Exception;
	/**
	 * 获取Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public List<TicketsBean> getTicketsBeans(TicketsBean tickets) throws Exception;
	
	/**
	 * 获取Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public List<TicketsBean> getTicketsBeans(TicketsBean tickets, PageInfo pageInfo) throws Exception;
	
	/**
	 * 删除Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public int deleteTicketsBean(TicketsBean tickets) throws Exception;
	
	/**
	 * 增加消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public TicketsInfoBean insertTicketsInfoBean(TicketsInfoBean ticketsInfo) throws Exception;
	
	/**
	 * 修改消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public int updateTicketsInfoBean(TicketsInfoBean ticketsInfo) throws Exception;
	
	
	/**
	 * 获取消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public TicketsInfoBean getTicketsInfoBean(TicketsInfoBean ticketsInfo) throws Exception;
	/**
	 * 获取消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public List<TicketsInfoBean> getTicketsInfoBeans(TicketsInfoBean ticketsInfo) throws Exception;
	
	/**
	 * 获取消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public List<TicketsInfoBean> getTicketsInfoBeans(TicketsInfoBean ticketsInfo, PageInfo pageInfo) throws Exception;
	
	/**
	 * 删除消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public int deleteTicketsInfoBean(TicketsInfoBean ticketsInfo) throws Exception;
	
	/**
	 * 增加消息附件
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public TicketsAttachment insertTicketsAttachment(TicketsAttachment ticketsAttachment) throws Exception;
	
	/**
	 * 修改消息附件
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public int updateTicketsAttachment(TicketsAttachment ticketsAttachment) throws Exception;
	
	/**
	 * 获取消息附件
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public List<TicketsAttachment> getTicketsAttachment(TicketsAttachment ticketsAttachment) throws Exception;
	
	/**
	 * 获取Tickets
	 * 
	 * @param searchTicket
	 * @return
	 * @throws Exception
	 */
	public List<TicketsBean> getTicketsBeans(SearchTicketVo searchTicket,PageInfo pageInfo) throws Exception;
}