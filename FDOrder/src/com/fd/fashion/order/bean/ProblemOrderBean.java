package com.fd.fashion.order.bean;

import java.util.Date;

/** 问题订单池 */
public class ProblemOrderBean {
	/** 订单号 */
	private Long orderId;
	/** 问题订单创建时间 */
	private Date createTime;
	/** 订单状态 */
	private Integer status;

	/** 订单号 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/** 订单号 */
	public Long getOrderId() {
		return this.orderId;
	}

	/** 问题订单创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 问题订单创建时间 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/** 订单状态 */
	public Integer getStatus() {
		return status;
	}

	/** 订单状态 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}