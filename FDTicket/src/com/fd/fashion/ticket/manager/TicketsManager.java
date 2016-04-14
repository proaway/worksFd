package com.fd.fashion.ticket.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fd.fashion.ticket.bean.TicketsAttachment;
import com.fd.fashion.ticket.bean.TicketsBean;
import com.fd.fashion.ticket.bean.TicketsInfoBean;
import com.fd.fashion.ticket.service.ITicketsService;
import com.fd.fashion.ticket.vo.AttachmentVo;
import com.fd.fashion.ticket.vo.SearchTicketVo;
import com.fd.fashion.ticket.vo.TicketInfoVo;
import com.fd.util.PageInfo;
import com.fd.util.RewriteUtil;

/**
 * @CreateDate: 2013-3-26 下午02:16:05
 * @author Longli
 * @Description: TODO
 * 
 */
@Transactional(rollbackFor=Exception.class)
@Component
public class TicketsManager implements ITicketsManager {
	@Autowired
	@Qualifier("ticketsService")
	ITicketsService ticketsService;

	/**
	 * 增加Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public TicketsBean insertTicketsBean(TicketsBean tickets,TicketInfoVo ticketInfo) throws Exception{
		 tickets = ticketsService.insertTicketsBean(tickets);
		 if(tickets!=null){
			 TicketsInfoBean ticket = ticketInfo.getTicketInfo();
			 ticket.setTicketsId(tickets.getTicketsId());
			 ticketInfo.setTicketInfo(ticket);
			 ticketInfo = insertTicketsInfoBean(ticketInfo);
		 }
		return tickets;
	}
	
	/**
	 * 修改Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public int updateTicketsBean(TicketsBean tickets) throws Exception{
		return ticketsService.updateTicketsBean(tickets);
	}
	
	
	/**
	 * 获取Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public TicketsBean getTicketsBean(TicketsBean tickets) throws Exception{
		return ticketsService.getTicketsBean(tickets);
	}
	
	/**
	 * 获取Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public List<TicketsBean> getTicketsBeans(TicketsBean tickets, PageInfo pageInfo) throws Exception{
		return ticketsService.getTicketsBeans(tickets, pageInfo);
	}
	
	/**
	 * 增加消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public TicketsInfoBean insertTicketsInfoBean(TicketsInfoBean ticketsInfo) throws Exception{
		ticketsInfo = ticketsService.insertTicketsInfoBean(ticketsInfo);
		return ticketsInfo;
	}
	
	/**
	 * 增加消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public TicketInfoVo insertTicketsInfoBean(TicketInfoVo ticketInfo) throws Exception{
		if(ticketInfo!=null){
			TicketsInfoBean tif = ticketInfo.getTicketInfo();
			tif = insertTicketsInfoBean(tif);
			if(tif!=null){
				ticketInfo.setTicketInfo(tif);
				//更新主对话信息
				TicketsBean ticket = new TicketsBean();
				ticket.setLastRelayer(tif.getSender());
				ticket.setLastReplayTime(tif.getSendTime());
				if(tif.getIdentity()==1){
					ticket.setStatus(2);
				}else{
					ticket.setStatus(1);
				}
				ticket.setTicketsId(tif.getTicketsId());
				ticketsService.updateTicketsBean(ticket);
				
				//插入附件
				if(ticketInfo.getAttachList()!=null&&ticketInfo.getAttachList().size()>0){
					List<AttachmentVo> list = new ArrayList<AttachmentVo>();
					for(AttachmentVo av :ticketInfo.getAttachList()){
						TicketsAttachment  tic = av.getAttachment();
						tic.setTicketsInfoId(tif.getTicketsInfoId());
						tic = ticketsService.insertTicketsAttachment(tic);
						if(tic!=null && tic.getAttachmentId()!=null){
							av.setAttachment(tic);
							list.add(av);
						}
					}
					ticketInfo.setAttachList(list);
				}
			}
		}
		return ticketInfo;
	}
	
	/**
	 * 修改消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public int updateTicketsInfoBean(TicketsInfoBean ticketsInfo) throws Exception{
		return ticketsService.updateTicketsInfoBean(ticketsInfo);
	}
	
	
	/**
	 * 获取消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public TicketsInfoBean getTicketsInfoBean(TicketsInfoBean ticketsInfo) throws Exception{
		return ticketsService.getTicketsInfoBean(ticketsInfo);
	}
	
	/**
	 * 获取消息信息
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public List<TicketsInfoBean> getTicketsInfoBeans(TicketsInfoBean ticketsInfo, PageInfo pageInfo) throws Exception{
		if(pageInfo==null){
			return ticketsService.getTicketsInfoBeans(ticketsInfo);
		}
		return ticketsService.getTicketsInfoBeans(ticketsInfo, pageInfo);
	}
	
	/**
	 * 增加消息附件
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public TicketsAttachment insertTicketsAttachment(TicketsAttachment ticketsAttachment) throws Exception{
		return ticketsService.insertTicketsAttachment(ticketsAttachment);
	}
	
	/**
	 * 修改消息附件
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public int updateTicketsAttachment(TicketsAttachment ticketsAttachment) throws Exception{
		return ticketsService.updateTicketsAttachment(ticketsAttachment);
	}
	
	/**
	 * 获取消息附件
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	private List<TicketsAttachment> getTicketsAttachment(TicketsAttachment ticketsAttachment) throws Exception{
		return ticketsService.getTicketsAttachment(ticketsAttachment);
	}
	
	/**
	 * 获取Tickets
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public List<TicketsBean> getTicketsList(SearchTicketVo searchTicket,
			PageInfo pageInfo) throws Exception{
		return ticketsService.getTicketsBeans(searchTicket, pageInfo);
	}
	
	/**
	 * 需要获取对话详细信息来判断是否有新回复
	 * @param searchTicket
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<SearchTicketVo> getTicketInfosList(SearchTicketVo searchTicket,
			PageInfo pageInfo) throws Exception{
		List<TicketsBean> list = getTicketsList(searchTicket,pageInfo);
		if(list!=null && list.size()>0){
			List<SearchTicketVo>  searchList = new ArrayList<SearchTicketVo>();
			for(TicketsBean tb :list){
				SearchTicketVo st = new SearchTicketVo();
				//st = getTicketVo(tb.getTicketsId(),null);
				st.setTicket(tb);
				List<TicketInfoVo> infos = getTicketInfoList(tb.getTicketsId(), null);
				if(infos!=null && infos.size()>0){
					st.setTicketInfoList(infos);
				}
				searchList.add(st);
			}
			return searchList;
		}
		return null;
	}
	
	/**
	 * 获取某个ticket的对话列表
	 * 
	 */
	private List<TicketInfoVo> getTicketInfoList(long ticketId,PageInfo pageInfo) throws Exception{
		TicketsInfoBean t = new TicketsInfoBean();
		t.setTicketsId(ticketId);
		List<TicketsInfoBean> list = getTicketsInfoBeans(t, pageInfo);
		if(list!=null && list.size()>0){
			List<TicketInfoVo> infos = new ArrayList<TicketInfoVo>();
			for(TicketsInfoBean info :list){
				TicketInfoVo tinfo = new TicketInfoVo();
				tinfo.setTicketInfo(info);
				TicketsAttachment ticketsAttachment = new TicketsAttachment();
				ticketsAttachment.setTicketsInfoId(info.getTicketsInfoId());
				List<AttachmentVo> listAttach = getAttachmentList(ticketsAttachment);
				if(listAttach!=null && listAttach.size()>0){
					tinfo.setAttachList(listAttach);
				}
				infos.add(tinfo);
			}
			return infos;
		}
		return null;
	}
	
	
	/**
	 * 获取Ticket
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public SearchTicketVo getTicketVo(long ticketId,PageInfo pageInfo) throws Exception{
		if(ticketId<0){
			return null;
		}
		SearchTicketVo searchTicketVo = new SearchTicketVo();
		TicketsBean ticketBean = new TicketsBean();
		ticketBean.setTicketsId(ticketId);
		ticketBean = getTicketsBean(ticketBean);
		searchTicketVo.setTicket(ticketBean);
		List<TicketInfoVo> list = getTicketInfoList(ticketId,pageInfo);
		searchTicketVo.setTicketInfoList(list);
		return searchTicketVo;
	}
	
	
	/**
	 * 获取Ticket
	 * 
	 * @param tickets
	 * @return
	 * @throws Exception
	 */
	public SearchTicketVo getTicketVo(long ticketId) throws Exception{
		if(ticketId<0){
			return null;
		}
		SearchTicketVo searchTicketVo = new SearchTicketVo();
		TicketsBean ticketBean = new TicketsBean();
		ticketBean.setTicketsId(ticketId);
		ticketBean = getTicketsBean(ticketBean);
		searchTicketVo.setTicket(ticketBean);
		List<TicketInfoVo> list = getTicketInfoList(ticketId,null);
		searchTicketVo.setTicketInfoList(list);
		return searchTicketVo;
	}
	
	/**
	 * 获取消息附件
	 * 
	 * @param ticketsInfo
	 * @return
	 * @throws Exception
	 */
	public  List<AttachmentVo> getAttachmentList(TicketsAttachment ticketsAttachment) throws Exception{
		if(ticketsAttachment==null){
			return null;
		}
		List<TicketsAttachment> attachs =getTicketsAttachment(ticketsAttachment);
		if(attachs!=null && attachs.size()>0){
			List<AttachmentVo> listAttach = new ArrayList<AttachmentVo>();
			for(TicketsAttachment ta :attachs){
				AttachmentVo av = new AttachmentVo();
				av.setAttachment(ta);
				String str = RewriteUtil.getImageUrl(ta.getAttachUrl());
				av.setAttachPath(str);
				int i = ta.getAttachUrl().lastIndexOf("/");
				str = ta.getAttachUrl().substring(i+1);
				av.setAttachName(str);
				i = ta.getAttachUrl().lastIndexOf(".");
				str = ta.getAttachUrl().substring(i+1);
				av.setAttachExt(str);
				listAttach.add(av);
			}
			return listAttach;
		}
		return null;
	}
}
