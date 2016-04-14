/**
 * RefundInfo.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.screens.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderManager;

/**
 * @CreateDate:  2013-4-22 下午3:22:24 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class RefundInfo  extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		
		long orderId = data.getParameters().getLong("orderId");
		IOrderManager orderManager = (IOrderManager)this.getBean("orderManager");
		OrderBean ob = orderManager.getOrder(orderId);
		context.put("order", ob);
		
	}

}
