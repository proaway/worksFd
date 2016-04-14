package com.fd.b2c.manager.modules.screens.order;

import java.util.Collections;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.order.bean.OrderShippingBean;
import com.fd.fashion.order.manager.IOrderManager;

public class ShippingInfo extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		long orderId = data.getParameters().getLong("orderId");
		IOrderManager orderManager = (IOrderManager)this.getBean("orderManager");
		List<OrderShippingBean> orderShippings = orderManager.getOrderShipping(orderId);
		Collections.reverse(orderShippings);
		context.put("orderId", orderId);
		context.put("orderShippings", orderShippings);
	}

}
