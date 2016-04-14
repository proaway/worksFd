/**
 * CouponManager.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.order.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fd.fashion.order.bean.CouponBean;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.service.IOrderService;
import com.fd.fashion.order.util.MD5To16Str;
import com.fd.fashion.order.vo.CouponVo;
import com.fd.util.PageInfo;
import com.fd.util.StringUtil;

/**
 * @CreateDate:  2013-4-8 下午12:23:51 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
@Transactional(rollbackFor = Exception.class)
@Component
public class CouponManager implements ICouponManager {
	@Autowired
	@Qualifier("orderService")
	IOrderService orderService;
	
	/**
	 * 使用couponCode获取Coupon
	 * 
	 * @param couponCode
	 * @return
	 * @throws Exception
	 */
	public CouponBean getCoupon(String couponCode) throws Exception {
		if (StringUtil.isEmpty(couponCode)) {
			return null;
		}
		CouponBean cb = new CouponBean();
		cb.setCouponCode(couponCode);
		
		List<CouponBean> list = orderService.getCouponBeans(cb);
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	

	/**领用coupon时更新coupon的信息
	 * @param couponActionConditionVo
	 * @return
	 * @throws Exception
	 */
	public Integer updateCouponInfo(CouponBean coupon) throws Exception {
		return orderService.updateCouponBean(coupon);
	}
	
	/**
	 * 增加Coupon
	 * 
	 * @param coupon
	 * @return
	 * @throws Exception
	 */
	public CouponBean addCoupon(CouponBean coupon) throws Exception {
		String code = MD5To16Str.getStrByMd5(StringUtil.getDateString(new Date(),"yyyyMMddHHmmssSSS") + MD5To16Str.flushLeft(3,String.format("%010d", 1))+ (int)(Math.random()*10));
		coupon.setCouponCode(code);
		return orderService.insertCouponBean(coupon);
	}
	
	/**
	 * 优惠券列表
	 * @param couponConditionVo
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<CouponBean> getCouponList(CouponBean coupon,PageInfo pageInfo) throws Exception {
		return orderService.getCouponBeans(coupon, pageInfo);
	}
	
	/**
	 * 使用优惠券的Order列表
	 * @param couponConditionVo
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<OrderBean> getCouponOrder(OrderBean ob) throws Exception {
		return orderService.getOrderBeans(ob);
	}
	
	
}
