/**
 * AjaxSendMailToBuyer.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.util.AppContextUtil;
import com.fd.util.MailSendServer;

/**
 * @CreateDate:  2013-4-18 下午8:30:12 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class AjaxSendMailToBuyer  extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long buyerId = data.getParameters().getLongObject("buyerId");
		long orderId = data.getParameters().getLongObject("orderId");
		String content = data.getParameters().getString("content");
		IBuyerManager buyerManager = (IBuyerManager)this.getBean("buyerManager");
		BuyerBean buyer = buyerManager.getBuyerByBuyerId(buyerId);
		String email = buyer.getEmail();
		String[] emails = new String[1];
		emails[0] = email;
		MailSendServer server = (MailSendServer) AppContextUtil.getBean("serviceMailSender");
		String subject = "关于您的订单"+orderId+"回复";
		server.sendEmail(emails, subject, content, null, null);
		context.put("contentdata", "1");
	}

}
