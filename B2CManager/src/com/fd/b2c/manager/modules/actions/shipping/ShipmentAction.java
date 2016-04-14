/**
 * ShipmentAction.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.shipping;

import java.util.Date;
import java.util.HashMap;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.order.bean.OrderShippingBean;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.util.JSONUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate:  2013-4-19 下午3:43:17 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ShipmentAction extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long orderId = data.getParameters().getLong("orderId");
		String shippingType = data.getParameters().getString("shippingType");
		String trackingNo = data.getParameters().getString("trackingNo");
		String remark = data.getParameters().getString("remark");
		
		OrderShippingBean osb = new OrderShippingBean();
		osb.setOrderId(orderId);
		osb.setShippingType(shippingType);
		osb.setTrackingNo(trackingNo);
		osb.setRemark(remark);
		osb.setShippingDate(new Date());

		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			IOrderStatusManager orderStatusManager = (IOrderStatusManager)this.getBean("orderStatusManager");
			orderStatusManager.shipment(osb);
			map.put("status", 1);
			map.put("shippingType", shippingType);
			map.put("trackingNo", trackingNo);
			map.put("shippingDate", StringUtil.getDateTimeString(osb.getShippingDate()));
		} catch (RuntimeException e) {
			e.printStackTrace();
			map.put("status", 0);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}

}
