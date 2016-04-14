package com.fd.fashion.order.vo;

import com.fd.fashion.order.bean.OrderProductBean;

public class OrderProductVo {
	
	/** 订单产品 */
	private OrderProductBean orderProduct;
	/** 是否使用Coupon  */
	private boolean useCoupon = false;
	/** 状态中文 */
	private String statusCn;

	public OrderProductBean getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(OrderProductBean orderProduct) {
		this.orderProduct = orderProduct;
	}

	public String getStatusCn() {
		return statusCn;
	}

	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
	}

	/**
	 * @return the useCoupon
	 */
	public boolean isUseCoupon() {
		return useCoupon;
	}

	/**
	 * @param useCoupon the useCoupon to set
	 */
	public void setUseCoupon(boolean useCoupon) {
		this.useCoupon = useCoupon;
	}
	
	

}
