package com.fd.fashion.modules.actions.tickets;

import java.util.HashMap;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.actions.SecureAction;
import com.fd.fashion.ticket.bean.TicketsBean;
import com.fd.fashion.ticket.manager.ITicketsManager;

/**
 * @author zhangling
 * 
 * 关闭tickets
 *
 */
public class AjaxCloseTicketAction extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		Long ticketsId = data.getParameters().getLongObject("ticketsId");
		TicketsBean ticket = new TicketsBean();
		ticket.setTicketsId(ticketsId);
		ticket.setStatus(3); //状态3表示关闭
		ITicketsManager ticketsManager = (ITicketsManager)getBean("ticketsManager");
		HashMap<String, Object> map = new HashMap<String, Object>();
		int count = ticketsManager.updateTicketsBean(ticket);
		if(count>0){
			context.put("contentdata", 1);
		}else{
			context.put("contentdata", 0);
		}
		
	}

}
