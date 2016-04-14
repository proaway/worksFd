package com.fd.b2c.manager.modules.screens.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.bean.OrderPaymentinfoBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.util.OrderUtil;

public class ConfirmPayment  extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		long orderId = data.getParameters().getLongObject("orderId");
		String statusCode = data.getParameters().getString("statusCode");
		
		IOrderManager orderManager = (IOrderManager)getBean("orderManager");
		//订单信息
		OrderBean orderInfo = orderManager.getOrder(orderId);
		orderInfo.setOrderStatus(statusCode);
		double total = OrderUtil.calOrderTotal(orderInfo);
		orderInfo.setTotal(total);
		context.put("orderInfo", orderInfo);
		
		//支付信息
		OrderPaymentinfoBean orderPaymentInfo = orderManager.getOrderPaymentinfo(orderId);
		context.put("orderPaymentInfo", orderPaymentInfo);
		
	}

}
