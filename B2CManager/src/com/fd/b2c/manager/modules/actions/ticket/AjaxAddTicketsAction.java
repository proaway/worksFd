package com.fd.b2c.manager.modules.actions.ticket;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.ticket.bean.TicketsBean;
import com.fd.fashion.ticket.bean.TicketsInfoBean;
import com.fd.fashion.ticket.manager.ITicketsManager;
import com.fd.fashion.ticket.vo.TicketInfoVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.JSONUtil;

/**
 * @author zhangling
 * 新增tickets
 *
 */
public class AjaxAddTicketsAction extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		//后台操作人员信息
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean)session.getAttribute("KEY_LOGIN_USER");
		//ticket信息
		String title = data.getParameters().getString("titleContent");
		String questionType = data.getParameters().getString("questionType","");
		String buyerName = data.getParameters().getString("buyerName");
		Long buyerId = data.getParameters().getLongObject("buyerId");
		String messContent = data.getParameters().getString("messContent");
		String sendEmail = data.getParameters().getString("sendEmail");
		//由系统运营人员发给buyerName的ticket
		//ticket
		Calendar c = Calendar.getInstance();
		TicketsBean ticket = new TicketsBean();
		ticket.setBuyerId(buyerId);
		ticket.setBuyerName(buyerName);
		ticket.setIdentity(1);
		ticket.setQuestionType(questionType.equals("")?null:Integer.parseInt(questionType));
		ticket.setStatus(2);
		ticket.setTicketsSystem(user.getLoginName());	
		ticket.setLastRelayer(user.getLoginName());
		ticket.setLastReplayTime(c.getTime());
		ticket.setTitle(title);
		ticket.setSendTime(c.getTime());
		//ticketinfo
		TicketsInfoBean ticketsInfo = new TicketsInfoBean();
		ticketsInfo.setEmail(Integer.parseInt(sendEmail));
		ticketsInfo.setIdentity(1);
		ticketsInfo.setMessage(messContent);
		ticketsInfo.setSender(user.getLoginName());
		ticketsInfo.setSendTime(c.getTime());
		
		TicketInfoVo tiv = new TicketInfoVo();
		tiv.setTicketInfo(ticketsInfo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		ITicketsManager  ticketsManager = (ITicketsManager)getBean("ticketsManager");
		ticket = ticketsManager.insertTicketsBean(ticket,tiv);
		
		if(ticket!=null && ticket.getTicketsId()!=null){
			map.put("status", 1);
			map.put("ticket", ticket);
		}else{
			map.put("status", 2);
		}
		
		if(sendEmail.equals("1")){
			//发送邮件
		}
		
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
