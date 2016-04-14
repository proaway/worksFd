package com.fd.b2c.manager.modules.screens.order;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.order.bean.OrderstatusInfoBean;
import com.fd.fashion.order.manager.IOrderManager;

public class OrderMemo extends SecureScreen {
	
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		long orderId = data.getParameters().getLongObject("orderId");
		String statusCode = data.getParameters().getString("statusCode","");
		context.put("orderId", orderId);
		context.put("statusCode", statusCode);
		IOrderManager orderManager = (IOrderManager)getBean("orderManager");
		List<OrderstatusInfoBean> listOrderStatus = orderManager.getOrderStatusInfo(orderId,"1");
		context.put("orderStatuList", listOrderStatus);
	}

}
