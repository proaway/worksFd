package com.fd.b2c.manager.modules.screens.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.order.bean.PickingInfoBean;
import com.fd.fashion.order.manager.IOrderManager;

/**
 * @author zhangling
 * 
 * 缺货登记
 *
 */
public class NoticeStockOut extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		long orderId = data.getParameters().getLongObject("orderId");
		String sku  = data.getParameters().getString("sku");
		PickingInfoBean pick = new PickingInfoBean();
		pick.setOrderId(orderId);
		pick.setSku(sku);
		IOrderManager orderManager = (IOrderManager)getBean("orderManager");
		pick = orderManager.getPickingInfoBean(pick);
		if(pick!=null){
			context.put("pick", pick);
		}
	}

}
