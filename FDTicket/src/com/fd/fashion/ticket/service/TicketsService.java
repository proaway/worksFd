package com.fd.fashion.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fd.dao.IBaseDao;
import com.fd.fashion.ticket.bean.TicketsAttachment;
import com.fd.fashion.ticket.bean.TicketsBean;
import com.fd.fashion.ticket.bean.TicketsInfoBean;
import com.fd.fashion.ticket.vo.SearchTicketVo;
import com.fd.util.PageInfo;

/** Tickets */
@Component
@SuppressWarnings("unchecked")
public class TicketsService implements ITicketsService {
	@Autowired
	@Qualifier("dao")
	private IBaseDao dao;

	/**
	 * 增加Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public TicketsBean insertTicketsBean(TicketsBean tickets) throws Exception {
		dao.insertObj("insertTicketsBean", tickets);
		return tickets;
	}

	/**
	 * 修改Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public int updateTicketsBean(TicketsBean tickets) throws Exception {
		return dao.updateObj("updateTicketsBean", tickets);
	}

	/**
	 * 获取Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public TicketsBean getTicketsBean(TicketsBean tickets) throws Exception {
		return (TicketsBean) dao.getAsObject("getTicketsBean", tickets);
	}

	/**
	 * 获取Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public List<TicketsBean> getTicketsBeans(TicketsBean tickets)
			throws Exception {
		return dao.getAsList("getTicketsBean", tickets);
	}

	/**
	 * 获取Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public List<TicketsBean> getTicketsBeans(TicketsBean tickets,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getTicketsBeanCount",
				tickets);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getTicketsBean", tickets, pageInfo);
		}
		return null;
	}

	/**
	 * 删除Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public int deleteTicketsBean(TicketsBean tickets) throws Exception {
		return dao.deleteObj("deleteTicketsBean", tickets);
	}

	/**
	 * 增加消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public TicketsInfoBean insertTicketsInfoBean(TicketsInfoBean ticketsInfo)
			throws Exception {
		dao.insertObj("insertTicketsInfoBean", ticketsInfo);
		return ticketsInfo;
	}

	/**
	 * 修改消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public int updateTicketsInfoBean(TicketsInfoBean ticketsInfo)
			throws Exception {
		return dao.updateObj("updateTicketsInfoBean", ticketsInfo);
	}

	/**
	 * 获取消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public TicketsInfoBean getTicketsInfoBean(TicketsInfoBean ticketsInfo)
			throws Exception {
		return (TicketsInfoBean) dao.getAsObject("getTicketsInfoBean",
				ticketsInfo);
	}

	/**
	 * 获取消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public List<TicketsInfoBean> getTicketsInfoBeans(TicketsInfoBean ticketsInfo)
			throws Exception {
		return dao.getAsList("getTicketsInfoBean", ticketsInfo);
	}

	/**
	 * 获取消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public List<TicketsInfoBean> getTicketsInfoBeans(
			TicketsInfoBean ticketsInfo, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getTicketsInfoBeanCount",
				ticketsInfo);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getTicketsInfoBean", ticketsInfo, pageInfo);
		}
		return null;
	}

	/**
	 * 删除消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public int deleteTicketsInfoBean(TicketsInfoBean ticketsInfo)
			throws Exception {
		return dao.deleteObj("deleteTicketsInfoBean", ticketsInfo);
	}

	/**
	 * 增加消息附件
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public TicketsAttachment insertTicketsAttachment(
			TicketsAttachment ticketsAttachment) throws Exception {
		dao.insertObj("insertTicketsAttachment", ticketsAttachment);
		return null;
	}

	/**
	 * 修改消息附件
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public int updateTicketsAttachment(TicketsAttachment ticketsAttachment)
			throws Exception {
		return dao.updateObj("updateTicketsAttachment", ticketsAttachment);
	}

	/**
	 * 获取消息附件
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public List<TicketsAttachment> getTicketsAttachment(
			TicketsAttachment ticketsAttachment) throws Exception {
		return dao.getAsList("getTicketsAttachment", ticketsAttachment);
	}
	
	/**
	 * 获取Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public List<TicketsBean> getTicketsBeans(SearchTicketVo searchTicket,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getTicketsListCount",
				searchTicket);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			pageInfo.setRecordCount(count);
			searchTicket.setPageInfo(pageInfo);
			return dao.getAsList("getTicketsList", searchTicket);
		}
		return null;
	}
}