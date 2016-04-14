/**
 * CouponManager.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.screens.marketing;

import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.order.bean.CouponBean;
import com.fd.fashion.order.manager.ICouponManager;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.manager.ISellerManager;

/**
 * @CreateDate:  2013-4-23 下午5:19:00 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class AjaxGetCouponLimit  extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		String couponCode = data.getParameters().getString("couponCode");
		CouponBean coupon = new CouponBean();
		
		ICouponManager couponManager = (ICouponManager)this.getBean("couponManager");
		ISellerManager sellerManager = (ISellerManager)this.getBean("sellerManager");
		
/*		*//**分页信息*//*
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(2);
		context.put("pageInfo", pageInfo);*/
		
		//**********设置PageInfo分页信息
		coupon = couponManager.getCoupon(couponCode);
		List<List<CustomCategoryBean>> list = new ArrayList<List<CustomCategoryBean>>();
		if(coupon.getLimitCategory() != null){
			String[] limitCategory = coupon.getLimitCategory().split(",");
			for(int j=0;j<limitCategory.length;j++){
				String catId = limitCategory[j];
				List<CustomCategoryBean> cats = sellerManager.getParentsCats(catId);
				list.add(cats);
			}
		}
		if(list.size() > 0){
			context.put("catList", list);
		}
		if( coupon.getLimitProduct() != null){
			context.put("limitProduct", coupon.getLimitProduct());
		}
		if(coupon.getUserId() != null){
			context.put("userIds", coupon.getUserId());
		}


		
	}
}
