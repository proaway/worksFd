/**
 * AjaxRefund.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderStatusManager;

/**
 * @CreateDate:  2013-4-22 下午4:01:52 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class AjaxRefund extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long orderId = data.getParameters().getLong("orderId");
		Double refundNum = data.getParameters().getDouble("refundNum");
		String refundType = data.getParameters().getString("refundType");
		String remark = data.getParameters().getString("remark");
		
		//System.out.println(orderId +ajustNum);
		IOrderStatusManager orderStatusManager = (IOrderStatusManager)this.getBean("orderStatusManager");
		OrderBean order = orderStatusManager.getOrder(orderId);
		if(refundNum != null){
			if(refundNum > order.getTotal() && refundNum > 0){
				context.put("contentdata", "0");
			}else{
				orderStatusManager.or201ToOr2XX(order, refundType, refundNum,"SYSTEM",remark);
				context.put("contentdata", "1");
			}
		}else{
			context.put("contentdata", "2");
		}


	}

}
