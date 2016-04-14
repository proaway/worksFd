/**
 * CreateCoupon.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.marketing;

import java.util.Date;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.order.bean.CouponBean;
import com.fd.fashion.order.manager.ICouponManager;
import com.fd.fashion.order.manager.IOrderManager;

/**
 * @CreateDate:  2013-4-24 下午4:23:54 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class CreateCoupon extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		int cCouponType = data.getParameters().getInt("cCouponType");
		Double cCouponAmount = data.getParameters().getDoubleObject("cCouponAmount");
		Date cStartDate = data.getParameters().getDate("cStartDate");
		Date cEndDate = data.getParameters().getDate("cEndDate");
		String numType = data.getParameters().getString("numType");
		Double cMinaMount = data.getParameters().getDoubleObject("cMinaMount");
		String[] customCatIds = data.getParameters().getStrings("customCatId");
		String[] userIds = data.getParameters().getStrings("userIds");
		String[] skus = data.getParameters().getStrings("skus");
		
		String customCatId = null;
		if(customCatIds != null && customCatIds.length > 0){
			if(customCatIds.length > 1){
				customCatId = new String();
				for(int j=0;j<customCatIds.length;j++){
					customCatId += customCatIds[j];
					if(j != customCatIds.length -1){
						customCatId += ",";
					}
				}
			}else if(customCatIds.length == 1){
				customCatId = new String();
				customCatId = customCatIds[0];
			}
		}
		
		String sku = null;
		if(skus != null && skus.length > 0){
			if(skus.length > 1){
				sku = new String();
				for(int m=0;m<skus.length;m++){
					sku += skus[m];
					if(m != skus.length -1){
						sku += ",";
					}
				}
			}else if(skus.length == 1){
				sku = new String();
				sku = skus[0];
			}
		}

		ICouponManager couponManager = (ICouponManager)this.getBean("couponManager");
		if(userIds != null && userIds.length > 0){
			for(int i=0;i<userIds.length;i++){
				CouponBean cb = new CouponBean();
				if(cCouponType == 0){
					cb.setCouponAmount(cCouponAmount);
				}else{
					cb.setCouponDiscount(cCouponAmount);
				}
				cb.setStartDate(cStartDate);
				cb.setEndDate(cEndDate);
				cb.setCouponType(cCouponType);
				cb.setNumberType(numType);
				cb.setLimitCategory(customCatId);
				cb.setMinaMount(cMinaMount);
				cb.setLimitProduct(sku);
				cb.setUserId(userIds[i]);
				cb.setNormalState(0);
				cb.setCreateDate(new Date());
				couponManager.addCoupon(cb);
			}
		}else{
			CouponBean cb = new CouponBean();
			if(cCouponType == 0){
				cb.setCouponAmount(cCouponAmount);
			}else{
				cb.setCouponDiscount(cCouponAmount);
			}
			cb.setStartDate(cStartDate);
			cb.setEndDate(cEndDate);
			cb.setCouponType(cCouponType);
			cb.setNumberType(numType);
			cb.setMinaMount(cMinaMount);
			cb.setNormalState(0);
			cb.setCreateDate(new Date());
			couponManager.addCoupon(cb);
		}
		
		context.put("contentdata", 1);
	}

}
