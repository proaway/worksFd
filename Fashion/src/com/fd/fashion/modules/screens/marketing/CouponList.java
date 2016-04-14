/**
 * CouponList.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.modules.screens.marketing;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.modules.screens.SecureScreen;
import com.fd.fashion.order.bean.CouponBean;
import com.fd.fashion.order.manager.ICouponManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.PageInfo;
import com.fd.util.ParametersUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate: 2013-4-24 下午8:53:47
 * @author: ZhouRongyu
 * @Description: TODO
 * 
 * @version: V1.0
 */
public class CouponList extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/BuyerAccountLayout.html");
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		
		CouponBean coupon = new CouponBean();
		ParametersUtil.initParameters(data, coupon);
		coupon.setUserId(buyer.getUserId());
		ICouponManager couponManager = (ICouponManager)this.getBean("couponManager");
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(2);
		context.put("pageInfo", pageInfo);
		
		List<CouponBean> couponList = couponManager.getCouponList(coupon, pageInfo);
		if(couponList != null && couponList.size() > 0){
			context.put("couponList", couponList);
		}
		context.put("couponCode", coupon.getCouponCode());
		context.put("endDate", StringUtil.getDateString(coupon.getEndDate()));
		context.put("normalState", coupon.getNormalState());
	}
}
