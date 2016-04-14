/**
 * CreateCoupon.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.marketing;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.order.bean.CouponBean;
import com.fd.fashion.order.manager.ICouponManager;

/**
 * @CreateDate:  2013-4-24 下午4:23:54 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class DisableCoupon extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);

		String couponCodes = data.getParameters().getString("couponCode");
		int status = data.getParameters().getInt("status");
		ICouponManager couponManager = (ICouponManager)this.getBean("couponManager");
		
		String[] couponCode = couponCodes.split(",");
		for(int i=0;i<couponCode.length;i++){
			CouponBean coupon = new CouponBean();
			coupon.setCouponCode(couponCode[i]);
			coupon.setStatus(status);
			couponManager.updateCouponInfo(coupon);
		}
		context.put("contentdata", 1);
	}

}
