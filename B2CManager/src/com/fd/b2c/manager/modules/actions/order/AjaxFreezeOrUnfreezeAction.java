package com.fd.b2c.manager.modules.actions.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.util.JSONUtil;

public class AjaxFreezeOrUnfreezeAction extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long orderId = data.getParameters().getLongObject("orderId");
		String flag = data.getParameters().getString("flag");
		OrderBean order = new OrderBean();
		order.setOrderId(orderId);
		String actionFlag = data.getParameters().getString("actionFlag");
		context.put("actionFlag", actionFlag);
		IOrderStatusManager orderStatusManager = (IOrderStatusManager)getBean("orderStatusManager");
		order = orderStatusManager.getOrder(orderId);
		if(flag.equals("1")){
			//执行冻结操作
			boolean updateFlag = orderStatusManager.freezeOrder(order, "", "订单已冻结");
			if(updateFlag){
				order.setFreeze("OF001");
				context.put("ord", order);
				setTemplate(data, "order,FreezeRes.html");
			}
		}else{
			//执行解冻操作
			boolean updateFlag = orderStatusManager.cancelFreeze(order, "", "订单已解冻");
			if(updateFlag){
				order.setFreeze("");
				context.put("ord", order);
				setTemplate(data, "order,FreezeRes.html");
			}
		}
		
	}
}
