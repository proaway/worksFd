/**
 * AjaxAjustAmount.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.order.manager.IOrderStatusManager;

/**
 * @CreateDate:  2013-4-18 下午4:43:24 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class AjaxAjustAmount  extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long orderId = data.getParameters().getLong("orderId");
		double ajustNum = data.getParameters().getDouble("ajustNum");
		IOrderStatusManager orderStatusManager = (IOrderStatusManager)this.getBean("orderStatusManager");
		int flag = orderStatusManager.adjustOrderAmount(orderId, ajustNum, "");
		if(flag == 1){
			context.put("contentdata", "1");
		}else{
			context.put("contentdata", "0");
		}
		
	}

}
