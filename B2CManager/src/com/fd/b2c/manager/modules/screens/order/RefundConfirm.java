/**
 * RefundConfirm.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.screens.order;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.bean.OrderStatusBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.bean.OrderstatusInfoBean;
import com.fd.fashion.order.manager.IOrderStatusManager;

/**
 * @CreateDate:  2013-4-22 下午9:25:33 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class RefundConfirm  extends SecureScreen{
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		long orderId = data.getParameters().getLong("orderId");
		IOrderStatusManager orderStatusManager = (IOrderStatusManager)this.getBean("orderStatusManager");
		OrderBean order = orderStatusManager.getOrder(orderId);
		context.put("order", order);
		OrderstatusInfoBean oo = new OrderstatusInfoBean();
		List<OrderstatusInfoBean> ois = orderStatusManager.getOrderStatusInfo(orderId,"1");
		for(int i = 0;i<ois.size();i++){
			if(order.getRefundStatus().equals(ois.get(i).getOrderStatus())){
				oo = ois.get(i+1);
				if(oo.getOrderStatus().indexOf("OR") >= 0 
						&& !oo.getOrderStatus().equals("OR213") 
						&& !oo.getOrderStatus().equals("OR214")){
					break;
				}

			}
		}
		IDictManager dict = (IDictManager)this.getBean("dictManager");
		OrderStatusBean osb =  dict.getOrderStatus(oo.getOrderStatus());
		context.put("osb", osb);
	}

}
