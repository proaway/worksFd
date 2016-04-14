package com.fd.fashion.order.bean;

import java.util.Date;

/** 订单货运 */
public class OrderShippingBean {
	/** 订单运输信息ID，主键 */
	private Long orderShippingId;
	/** 订单ID，主键 */
	private Long orderId;
	/** 物流跟踪号 */
	private String trackingNo;
	/** 发货方式 */
	private String shippingType;
	/** 填写发货记录时间 */
	private Date shippingDate;
	/** 备注信息 */
	private String remark;
	/** 货运单文件 */
	private String freightBill;

	/** 订单运输信息ID，主键 */
	public void setOrderShippingId(Long orderShippingId) {
		this.orderShippingId = orderShippingId;
	}

	/** 订单运输信息ID，主键 */
	public Long getOrderShippingId() {
		return this.orderShippingId;
	}

	/** 订单ID，主键 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/** 订单ID，主键 */
	public Long getOrderId() {
		return this.orderId;
	}

	/** 物流跟踪号 */
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	/** 物流跟踪号 */
	public String getTrackingNo() {
		return this.trackingNo;
	}

	/** 填写发货记录时间 */
	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	/** 填写发货记录时间 */
	public Date getShippingDate() {
		return this.shippingDate;
	}

	/** 备注信息 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/** 备注信息 */
	public String getRemark() {
		return this.remark;
	}

	/** 货运单文件 */
	public void setFreightBill(String freightBill) {
		this.freightBill = freightBill;
	}

	/** 货运单文件 */
	public String getFreightBill() {
		return this.freightBill;
	}

	/**
	 * @return the shippingType
	 */
	public String getShippingType() {
		return shippingType;
	}

	/**
	 * @param shippingType the shippingType to set
	 */
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}
}