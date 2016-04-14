/**
 * CouponManager.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.screens.marketing;

import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.order.bean.CouponBean;
import com.fd.fashion.order.manager.ICouponManager;
import com.fd.util.PageInfo;
import com.fd.util.ParametersUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate:  2013-4-23 下午5:19:00 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class CouponList  extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
		CouponBean coupon = new CouponBean();
		ParametersUtil.initParameters(data, coupon);
		
		ICouponManager couponManager = (ICouponManager)this.getBean("couponManager");
		/**分页信息*/

		//**********设置PageInfo分页信息
		int pageSize = 10;
		int pageIndex = data.getParameters().getInt("pageIndex", 1);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setPageSize(pageSize);
		
		List<CouponBean> couponList = couponManager.getCouponList(coupon, pageInfo);
		if(couponList != null && couponList.size() > 0){
			for(int i=0;i<couponList.size();i++){
				CouponBean cp =  couponList.get(i);
				Date d = new Date();
				if(null != cp.getEndDate()){
					if(cp.getEndDate().getTime() < d.getTime()){
						cp.setNormalState(1);
					}
				}

			}
			context.put("couponList", couponList);
		}
		
		context.put("couponCode", coupon.getCouponCode());
		context.put("userId",coupon.getUserId());
		context.put("createDateStart", StringUtil.getDateString(coupon.getCreateDateStart()));
		context.put("createDateEnd", StringUtil.getDateString(coupon.getCreateDateEnd()));
		context.put("counponType", coupon.getCouponType());
		context.put("miniAmount", coupon.getMinaMount());
		context.put("startAmount", coupon.getStartAmount());
		context.put("endAmount", coupon.getEndAmount());
		context.put("startMiniAmount", coupon.getStartMiniAmount());
		context.put("endMiniAmount", coupon.getEndMiniAmount());
		context.put("normalState", coupon.getNormalState());
		context.put("pageInfo", pageInfo);
		String searchType = data.getParameters().getString("searchType","0");
		context.put("searchType", searchType);
		
	}
}
