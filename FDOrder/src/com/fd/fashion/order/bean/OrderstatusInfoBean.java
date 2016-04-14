package com.fd.fashion.order.bean;

import java.util.Date;

/** 订单状态信息 */
public class OrderstatusInfoBean {
	/** 主键 */
	private Long orderInfoId;
	/** 订单ID */
	private Long orderId;
	/** 订单状态 */
	private String orderStatus;
	/** 订单状态中文 */
	private String orderStatusCn;
	/** 操作人 */
	private String operator;
	/** 备注信息 */
	private String memo;
	/** 创建记录时间 */
	private Date createTime;
	/** 买家可见标识，0-不可见，1-可见 */
	private Integer status;

	/** 主键 */
	public void setOrderInfoId(Long orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	/** 主键 */
	public Long getOrderInfoId() {
		return this.orderInfoId;
	}

	/** 订单ID */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/** 订单ID */
	public Long getOrderId() {
		return this.orderId;
	}

	/** 订单状态 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/** 订单状态 */
	public String getOrderStatus() {
		return this.orderStatus;
	}

	/** 操作人 */
	public void setOperator(String opertor) {
		this.operator = opertor;
	}

	/** 操作人 */
	public String getOperator() {
		return this.operator;
	}

	/** 备注信息 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/** 备注信息 */
	public String getMemo() {
		return this.memo;
	}

	/** 创建记录时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 创建记录时间 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/** 买家可见标识，0-不可见，1-可见 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 买家可见标识，0-不可见，1-可见 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * @return the orderStatusCn
	 */
	public String getOrderStatusCn() {
		return orderStatusCn;
	}

	/**
	 * @param orderStatusCn the orderStatusCn to set
	 */
	public void setOrderStatusCn(String orderStatusCn) {
		this.orderStatusCn = orderStatusCn;
	}
}