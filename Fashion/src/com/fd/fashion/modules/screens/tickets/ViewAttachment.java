package com.fd.fashion.modules.screens.tickets;

import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.screens.SecureScreen;
import com.fd.fashion.ticket.bean.TicketsAttachment;
import com.fd.fashion.ticket.manager.ITicketsManager;
import com.fd.fashion.ticket.vo.AttachmentVo;

/**
 * @author zhangling
 * 
 * 查看附件
 *
 */
public class ViewAttachment  extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		Long ticketsInfoId = data.getParameters().getLongObject("ticketsInfoId");
		context.put("ticketsInfoId", ticketsInfoId);
		TicketsAttachment ticketAtta = new TicketsAttachment();
		ticketAtta.setTicketsInfoId(ticketsInfoId);
		ITicketsManager  ticketsManager = (ITicketsManager)getBean("ticketsManager");
		
		List<AttachmentVo> ticketAttachs = new ArrayList<AttachmentVo>();
		ticketAttachs = ticketsManager.getAttachmentList(ticketAtta);
		
		if(ticketAttachs!=null && ticketAttachs.size()>0){
			context.put("ticketsAttachments", ticketAttachs);
		}
	}
}
