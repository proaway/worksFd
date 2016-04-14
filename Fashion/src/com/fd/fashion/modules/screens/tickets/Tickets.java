package com.fd.fashion.modules.screens.tickets;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.modules.screens.SecureScreen;
import com.fd.fashion.ticket.bean.TicketsBean;
import com.fd.fashion.ticket.manager.ITicketsManager;
import com.fd.fashion.ticket.vo.SearchTicketVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.PageInfo;

public class Tickets extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/BuyerAccountLayout.html");
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		
		String timeType = data.getParameters().getString("timeTye");
		String createOrderStart =data.getParameters().getString("timeStart");
		String createOrderEnd = data.getParameters().getString("timeEnd");
		String sendType = data.getParameters().getString("sendIt","4");
		String questionType = data.getParameters().getString("questionType"); 
		String messType = data.getParameters().getString("messType");
		String subjectContent = data.getParameters().getString("subjectContent");
		
		if(subjectContent==null || subjectContent.equals("")){
			subjectContent = null;
		}else{
			subjectContent = URLDecoder.decode(subjectContent, "utf-8");
		}
				
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(10);
		context.put("pageInfo", pageInfo);
		
		SearchTicketVo searchTicket = new SearchTicketVo();
		searchTicket.setTimeType(timeType);
		searchTicket.setKeyword(subjectContent);
		searchTicket.setBuyerName(buyer.getUserId());
		searchTicket.setSendType(sendType);
		searchTicket.setSender(null);
		
		Date createStart =null;
		Date createEnd = null;
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		if(createOrderStart==null|| createOrderStart.equals("") ){
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -7);
			createStart = format.parse(format.format(c.getTime()));
		}else{
			createStart = format.parse(format.format(format.parse(createOrderStart)));
		}
		if(createOrderEnd==null || createOrderEnd.equals("") ){
			createEnd = new Date();
			format.parse(format.format(createEnd.getTime()));
		}else{
			createEnd = format.parse(format.format(format.parse(createOrderEnd)));
			 
		}
		
		if(timeType==null || timeType.equals("")){
		}else{
			if(timeType.equals("1")){
				searchTicket.setSendTimeStart(createStart);
				searchTicket.setSendTimeEnd(createEnd);
			}else{
				searchTicket.setReplyTimeStart(createStart);
				searchTicket.setReplyTimeEnd(createEnd);
			}
		}
		
		if(messType==null || messType.equals("")){
			searchTicket.setMessType(null);
		}else{
			searchTicket.setMessType(messType);
		}
		
		if(questionType==null||questionType.equals("")){
			searchTicket.setQuestionType(null);
		}else{
			searchTicket.setQuestionType(Integer.parseInt(questionType));
		}
		
		ITicketsManager  ticketsManager = (ITicketsManager)getBean("ticketsManager");
		List<TicketsBean> searchTickets = ticketsManager.getTicketsList(searchTicket, pageInfo);
		if(searchTickets!=null && searchTickets.size()>0){
			context.put("ticketList", searchTickets);
		}
		
		context.put("searchVo", searchTicket);
	}
}
