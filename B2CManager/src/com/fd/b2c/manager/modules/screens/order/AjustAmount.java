package com.fd.b2c.manager.modules.screens.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;

/**
 * @CreateDate: 2013-4-15 下午03:30:42
 * @author Longli
 * @Description: 修改订单金额弹出层
 * 
 */
public class AjustAmount extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		long orderId = data.getParameters().getLong("orderId");
		String orderTotal = data.getParameters().getString("orderTotal");
		String orderStatus = data.getParameters().getString("orderStatus");
		context.put("orderId", orderId);
		context.put("orderTotal", orderTotal);
		context.put("orderStatus", orderStatus);
		
	}
}
