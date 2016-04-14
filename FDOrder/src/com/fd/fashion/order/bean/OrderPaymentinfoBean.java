package com.fd.fashion.order.bean;

import java.util.Date;

/** 订单支付情况信息 */
public class OrderPaymentinfoBean {
	/** 订单支付情况ID，主键 */
	private Long paymentInfoId;
	/** 订单ID，主键 */
	private Long orderId;
	/** 订单付款方式 */
	private Integer paymentType;
	/** 支付时间 */
	private Date payTime;
	/** 付款交易号 */
	private String txnId;
	/** 支付IP */
	private String paymentIp;
	/** 支付方式主键ID */
	private Long payMethodId;

	/** 订单支付情况ID，主键 */
	public void setPaymentInfoId(Long paymentInfoId) {
		this.paymentInfoId = paymentInfoId;
	}

	/** 订单支付情况ID，主键 */
	public Long getPaymentInfoId() {
		return this.paymentInfoId;
	}

	/** 订单ID，主键 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/** 订单ID，主键 */
	public Long getOrderId() {
		return this.orderId;
	}

	/** 订单付款方式 */
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	/** 订单付款方式 */
	public Integer getPaymentType() {
		return this.paymentType;
	}

	/** 支付时间 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/** 支付时间 */
	public Date getPayTime() {
		return this.payTime;
	}

	/** 付款交易号 */
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	/** 付款交易号 */
	public String getTxnId() {
		return this.txnId;
	}

	/** 支付IP */
	public void setPaymentIp(String paymentIp) {
		this.paymentIp = paymentIp;
	}

	/** 支付IP */
	public String getPaymentIp() {
		return this.paymentIp;
	}

	/** 支付方式主键ID */
	public void setPayMethodId(Long payMethodId) {
		this.payMethodId = payMethodId;
	}

	/** 支付方式主键ID */
	public Long getPayMethodId() {
		return this.payMethodId;
	}
}