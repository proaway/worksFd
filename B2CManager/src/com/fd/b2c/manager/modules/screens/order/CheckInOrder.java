package com.fd.b2c.manager.modules.screens.order;

import java.util.List;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.seller.bean.ShippingInfoBean;
import com.fd.fashion.seller.manager.IShippingManager;
import com.fd.payment.bean.PaymentTypeBean;
import com.fd.util.AppContextUtil;
import com.fd.util.PageInfo;

/**
 * @author zhangling
 * 
 * 扫描订单
 *
 */
public class CheckInOrder extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/OrderSearchLayout.html");
		
		IShippingManager shippingManager = (IShippingManager) getBean("shippingManager");
		List<ShippingInfoBean> shippingInfos = shippingManager.getShippingInfoBeans();
		if (shippingInfos != null && shippingInfos.size()>0) {
			ShippingInfoBean shippingInfo = shippingManager.getShippingInfo(shippingInfos.get(0).getShippingInfoId());
			context.put("shippingInfo", shippingInfo);
		}
	}
}
