/**
 * CouponManager.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.screens.marketing;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.ICouponManager;

/**
 * @CreateDate:  2013-4-23 下午5:19:00 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class AjaxGetCouponOrder  extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		String couponCode = data.getParameters().getString("couponCode");
		
		ICouponManager couponManager = (ICouponManager)this.getBean("couponManager");

		OrderBean ob = new OrderBean();
		ob.setCouponCode(couponCode);
		List<OrderBean> orderList = couponManager.getCouponOrder(ob);
		context.put("orderList", orderList);
		
	}
}
