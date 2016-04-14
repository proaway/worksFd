package com.fd.b2c.manager.modules.screens.ticket;

import java.util.Date;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.ticket.bean.TicketsInfoBean;
import com.fd.fashion.ticket.manager.ITicketsManager;
import com.fd.fashion.ticket.manager.TicketsManager;
import com.fd.fashion.ticket.vo.SearchTicketVo;
import com.fd.fashion.ticket.vo.TicketInfoVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;

/**
 * @author zhangling
 * 
 * 回复页
 *
 */
public class tickets_backstage_reply extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		
		//后台操作人员信息
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean)session.getAttribute("KEY_LOGIN_USER");
		
		Long ticketId = data.getParameters().getLongObject("ticketsId");
		context.put("ticketsId", ticketId);
		
		String flag = data.getParameters().getString("flag");
		if(flag==null || flag.equals("")){
			context.put("flag", 1);
		}
		
		ITicketsManager  ticketsManager = (ITicketsManager)getBean("ticketsManager");
		
		String actionFlag = data.getParameters().getString("actionFlag");
		if(!(actionFlag== null || actionFlag.equals(""))){
			String messContent = data.getParameters().getString("messContent");
			String isEmail = data.getParameters().getString("isEmail");
			TicketsInfoBean ticket = new TicketsInfoBean();
			ticket.setIdentity(1);
			ticket.setMessage(messContent);
			ticket.setSender(user.getLoginName());
			ticket.setSendTime(new Date());
			ticket.setTicketsId(ticketId);
			ticket.setEmail(Integer.parseInt(isEmail));
			if(isEmail.equals("1")){
				//发送邮件
				
			}
			TicketInfoVo ticketInfoVo = new TicketInfoVo();
			ticketInfoVo.setTicketInfo(ticket);
			ticketInfoVo = ticketsManager.insertTicketsInfoBean(ticketInfoVo);
			if(ticketInfoVo!=null&&ticketInfoVo.getTicketInfo()!=null){
				context.put("addTicket", ticketInfoVo.getTicketInfo());
			}
		}
		
		
		SearchTicketVo searchTicket = new SearchTicketVo();
		searchTicket = ticketsManager.getTicketVo(ticketId);
		context.put("ticketVo", searchTicket);
		
		if(searchTicket!=null && searchTicket.getTicketInfoList()!=null&& searchTicket.getTicketInfoList().size()>0){
			context.put("ticketInfoVo", searchTicket.getTicketInfoList().get(0));
		}
	}

}
