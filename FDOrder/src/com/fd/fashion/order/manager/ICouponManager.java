/**
 * ICouponManager.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.order.manager;

import java.util.List;

import com.fd.fashion.order.bean.CouponBean;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.util.PageInfo;

/**
 * @CreateDate:  2013-4-8 下午12:23:19 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public interface ICouponManager {
	/**
	 * 使用couponCode获取Coupon
	 * 
	 * @param couponCode
	 * @return
	 * @throws Exception
	 */
	public CouponBean getCoupon(String couponCode) throws Exception ;
	

	/**领用coupon时更新coupon的信息
	 * @param couponActionConditionVo
	 * @return
	 * @throws Exception
	 */
	public Integer updateCouponInfo(CouponBean coupon) throws Exception ;
	
	/**
	 * 优惠券列表
	 * @param couponConditionVo
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<CouponBean> getCouponList(CouponBean coupon,PageInfo pageInfo) throws Exception ;
	
	/**
	 * 增加Coupon
	 * 
	 * @param coupon
	 * @return
	 * @throws Exception
	 */
	public CouponBean addCoupon(CouponBean coupon) throws Exception ;
	
	/**
	 * 使用优惠券的Order列表
	 * @param couponConditionVo
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<OrderBean> getCouponOrder(OrderBean ob) throws Exception ;
	
}
