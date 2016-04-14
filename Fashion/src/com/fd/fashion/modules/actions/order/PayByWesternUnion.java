package com.fd.fashion.modules.actions.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.actions.SecureAction;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.payment.bean.WesternUnionBean;
import com.fd.util.ParametersUtil;

public class PayByWesternUnion extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		long orderId = data.getParameters().getLong("orderId");
		IOrderManager orderManager = (IOrderManager) getBean("orderManager");
		OrderBean order = orderManager.getOrder(orderId);

		order.setPaymentType(2);
		
		WesternUnionBean westernUnion = new WesternUnionBean();
		ParametersUtil.initParameters(data, westernUnion);
		
		IOrderStatusManager orderStatusManager = (IOrderStatusManager) getBean("orderStatusManager");
		if (orderStatusManager.oa001ToOa002(order, westernUnion, "")) {
			// 跳转到支付成功页
			setTemplate(data, "order,PaymentSuccessful.html");
		}
		// 跳转到失败页
	}
}
