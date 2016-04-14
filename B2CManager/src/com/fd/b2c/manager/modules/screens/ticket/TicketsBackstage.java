package com.fd.b2c.manager.modules.screens.ticket;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.ticket.bean.TicketsBean;
import com.fd.fashion.ticket.manager.ITicketsManager;
import com.fd.fashion.ticket.vo.SearchTicketVo;
import com.fd.util.PageInfo;

/**
 * @author zhangling
 *
 */
public class TicketsBackstage extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
		String timeType = data.getParameters().getString("timeTye");
		Date timeStart = data.getParameters().getDate("timeStart");
		Date timeEnd = data.getParameters().getDate("timeEnd");
		String sendType = data.getParameters().getString("sendType");
		String sender = data.getParameters().getString("sender");
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
		
		if(timeType==null || timeType.equals("")){
		}else{
			if(timeType.equals("1")){
				searchTicket.setSendTimeStart(timeStart);
				searchTicket.setSendTimeEnd(timeEnd);
			}else{
				searchTicket.setReplyTimeStart(timeStart);
				searchTicket.setReplyTimeEnd(timeEnd);
			}
		}
		
		if(messType==null || messType.equals("")){
			searchTicket.setMessType(null);
		}else{
			searchTicket.setMessType(messType);
		}
		
		if(sender==null || sender.equals("")){
			searchTicket.setSender(null);
			searchTicket.setSendType(null);
		}else{
			searchTicket.setSender(sender);
			searchTicket.setSendType(sendType);
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
		/*List<SearchTicketVo> searchTickets = ticketsManager.getTicketInfosList(searchTicket, pageInfo);
		if(searchTickets!=null && searchTickets.size()>0){
			context.put("ticketList", searchTickets);
		}*/
		
		context.put("searchVo", searchTicket);
	}

}
