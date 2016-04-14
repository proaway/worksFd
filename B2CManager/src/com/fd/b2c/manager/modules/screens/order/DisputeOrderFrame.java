package com.fd.b2c.manager.modules.screens.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;

public class DisputeOrderFrame  extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		long orderId = data.getParameters().getLongObject("orderId");
	}

}
