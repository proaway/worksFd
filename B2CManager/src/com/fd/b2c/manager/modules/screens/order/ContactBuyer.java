package com.fd.b2c.manager.modules.screens.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.buyer.manager.IBuyerManager;

public class ContactBuyer extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		long buyerId = data.getParameters().getLongObject("buyerId");
		long orderId = data.getParameters().getLongObject("orderId");
		context.put("orderId",orderId);
		context.put("buyerId", buyerId);
		IBuyerManager buyerManager = (IBuyerManager)this.getBean("buyerManager");
		String userId = buyerManager.getBuyerByBuyerId(buyerId).getUserId();
		context.put("userId", userId);
	}
}
