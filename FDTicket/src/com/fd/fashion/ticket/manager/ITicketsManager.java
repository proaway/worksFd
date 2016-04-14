package com.fd.fashion.ticket.manager;

import java.util.List;
import java.util.Map;

import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.bean.DepartmentBean;
import com.fd.fashion.seller.bean.ModulesBean;
import com.fd.fashion.seller.bean.RolePrivsBean;
import com.fd.fashion.seller.bean.RolesBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.vo.UserSearchVo;
import com.fd.fashion.ticket.bean.TicketsAttachment;
import com.fd.fashion.ticket.bean.TicketsBean;
import com.fd.fashion.ticket.bean.TicketsInfoBean;
import com.fd.fashion.ticket.vo.AttachmentVo;
import com.fd.fashion.ticket.vo.SearchTicketVo;
import com.fd.fashion.ticket.vo.TicketInfoVo;
import com.fd.util.PageInfo;

public interface ITicketsManager {
	
	/**
	 * 增加Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public TicketsBean insertTicketsBean(TicketsBean tickets,TicketInfoVo ticketInfo) throws Exception;
	
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
	public List<TicketsBean> getTicketsBeans(TicketsBean tickets, PageInfo pageInfo) throws Exception;
	
	/**
	 * 增加消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public TicketsInfoBean insertTicketsInfoBean(TicketsInfoBean ticketsInfo) throws Exception;
	
	/**
	 * 增加消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public TicketInfoVo insertTicketsInfoBean(TicketInfoVo ticketInfo) throws Exception;
	
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
	public List<TicketsInfoBean> getTicketsInfoBeans(TicketsInfoBean ticketsInfo, PageInfo pageInfo) throws Exception;
	
	/**
	 * 需要获取对话详细信息来判断是否有新回复
	 * @param searchTicket
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<SearchTicketVo> getTicketInfosList(SearchTicketVo searchTicket,
			PageInfo pageInfo) throws Exception;
	
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
	public  List<AttachmentVo> getAttachmentList(TicketsAttachment ticketsAttachment) throws Exception;
	
	/**
	 * 获取Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public List<TicketsBean> getTicketsList(SearchTicketVo searchTicket,
			PageInfo pageInfo) throws Exception;
	
	/**
	 * 获取Ticket
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public SearchTicketVo getTicketVo(long ticketId,PageInfo pageInfo) throws Exception;
	
	/**
	 * 获取Ticket
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public SearchTicketVo getTicketVo(long ticketId) throws Exception;
	
}
