package com.fd.fashion.modules.actions.buyer;

import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.modules.actions.SecureAction;
import com.fd.util.Context2Map;
import com.fd.util.MailSendServer;
import com.fd.util.WebPropUtil;

public class AjaxSendActiveEmailAction extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long buyerId = data.getParameters().getLongObject("buyerId");
		BuyerBean buyer = new BuyerBean();
		IBuyerManager buyerManager = (IBuyerManager)getBean("buyerManager");
		buyer = buyerManager.getBuyerByBuyerId(buyerId);
		sendRegisterEmail(context, buyer);
		context.put("contentdata", 1);
	}
	
	/**
	 * @param context
	 * @param buyer
	 * @throws Exception
	 */
	private void sendRegisterEmail(Context context, BuyerBean buyer)
			throws Exception {
		WebPropUtil propUtil = new WebPropUtil();
		context.put("buyer", buyer);
		context.put("propUtil", propUtil);
		String sendTo[] = new String[]{buyer.getEmail()};
		Map<String, Object> params = Context2Map.context2Map(context);
		String title = "Congratulations on resent active Email at " + propUtil.getProperty("website.webname");
		String templatePath = propUtil.getProperty("email.template.root") + "/" + propUtil.getProperty("email.buyer.register");
		MailSendServer mailSendServer = (MailSendServer) getBean("serviceMailSender");
		mailSendServer.sendEmail(sendTo, title, templatePath, params);
	}

}
