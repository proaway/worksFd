package com.fd.b2c.manager.modules.actions.ticket;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.ticket.manager.ITicketsManager;
import com.fd.fashion.ticket.vo.SearchTicketVo;
import com.fd.util.PageInfo;

public class AjaxGetTicketInfomation extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		Long ticketsId = data.getParameters().getLongObject("ticketsId");
		ITicketsManager  ticketsManager = (ITicketsManager)getBean("ticketsManager");
		
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(10);
		context.put("pageInfo", pageInfo);
		SearchTicketVo searchTicketVo = new SearchTicketVo();
		searchTicketVo = ticketsManager.getTicketVo(ticketsId,pageInfo);
		if(searchTicketVo!=null && searchTicketVo.getTicketInfoList().size()>0){
			context.put("ticketInfos",searchTicketVo.getTicketInfoList());
			setTemplate(data, "ticket,TicketInfomation.html");
		}
	}

}
