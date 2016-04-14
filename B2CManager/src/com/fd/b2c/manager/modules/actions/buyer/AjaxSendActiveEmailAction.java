package com.fd.b2c.manager.modules.actions.buyer;

import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.util.Context2Map;
import com.fd.util.MailSendServer;
import com.fd.util.WebPropUtil;

/**
 * @author zhangling
 * 发送验证邮箱
 *
 */
public class AjaxSendActiveEmailAction extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long buyerId = data.getParameters().getLongObject("buyerId");
		BuyerBean buyer = new BuyerBean();
		IBuyerManager buyerManager = (IBuyerManager)getBean("buyerManager");
		buyer = buyerManager.getBuyerByBuyerId(buyerId);
		try {
			String activeCode = buyer.getActiveCode();
			int status = buyerManager.activeBuyerEmail(activeCode);
			if (status >= 1) {
				context.put("contentdata", 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
