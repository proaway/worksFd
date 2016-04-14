package com.fd.b2c.manager.modules.actions.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.util.JSONUtil;

public class AjaxRejectPaymentAction  extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long orderId = data.getParameters().getLongObject("orderId");
		OrderBean orderBean = new OrderBean();
		orderBean.setOrderId(orderId);
		IOrderStatusManager orderStatusManager = (IOrderStatusManager)getBean("orderStatusManager");
		boolean flag = orderStatusManager.oa002ToOa003(orderBean, "");
		if(flag==true){
			context.put("contentdata", JSONUtil.obj2JSON(orderBean));
		}
		
	}

}
