package com.fd.fashion.order.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 订单 */
public class OrderBean {
	/** 订单ID，主键 */
	private Long orderId;
	/** 买家ID，外键 */
	private Long buyerId;
	/** 买家昵称（冗余字段，普通索引） */
	private String userId;
	/** 订单总金额 */
	private Double total;
	/** 订单创建时间 */
	private Date createDate;
	/** 订单付款方式 */
	private Integer paymentType;
	/** 买家配送地址ID */
	private Long orderAddressId;
	/** 订单信息IP */
	private String ipAddress;
	/** 订单FD纠纷状态 */
	private String fdDisputeStatus;
	/** 订单支付网关纠纷状态 */
	private String disputeStatus;
	/** 订单冻结状态 */
	private String freeze;
	/** 订单风控状态 */
	private String riskStatus;
	/** 订单主状态 */
	private String orderStatus;
	/** 订单退款状态 */
	private String refundStatus;
	/** 物流方式 */
	private String shippingType;
	/** 支付ID */
	private String paymentInfoId;
	/** 优惠卷 */
	private String couponCode;
	/** 优惠卷金额 */
	private Double couponAmount;
	/** 订单调整金额 */
	private Double adjustAmount;
	/** 订单交易号 */
	private String txnId;
	/** 退款金额 */
	private Double refundAmount;
	/** 运费总价*/
	private Double shippingTotal;
	/** 产品总价*/
	private Double itemTotal;
	/** 订单最长备货期*/
	private Integer maxStockDays;
	/** 订单运送国家*/
	private String shippingCountry;
	/**问题订单*/
	private Integer badOrder;
	
	/** 更新订单状态时，指定只更新包含在oldOrderStatus中的订单，防止订单状态更新错误 */
	private List<String> oldOrderStatus;
	
	/** 更新订单状态时，指定只更新包含在oldOrderStatus中的订单，防止订单状态更新错误 */
	public void addOldOrderStatus(String status) {
		if (oldOrderStatus == null) {
			oldOrderStatus = new ArrayList<String>();
		}
		oldOrderStatus.add(status);
	}
	
	/** 更新订单状态时，指定只更新包含在oldOrderStatus中的订单，防止订单状态更新错误 */
	public List<String> getOldOrderStatus() {
		return this.oldOrderStatus;
	}

	/** 订单ID，主键 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/** 订单ID，主键 */
	public Long getOrderId() {
		return this.orderId;
	}

	/** 买家ID，外键 */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/** 买家ID，外键 */
	public Long getBuyerId() {
		return this.buyerId;
	}

	/** 买家昵称（冗余字段，普通索引） */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/** 买家昵称（冗余字段，普通索引） */
	public String getUserId() {
		return this.userId;
	}

	/** 订单总金额 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/** 订单总金额 */
	public Double getTotal() {
		return this.total;
	}

	/** 订单创建时间 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/** 订单创建时间 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/** 订单付款方式 */
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	/** 订单付款方式 */
	public Integer getPaymentType() {
		return this.paymentType;
	}

	/** 买家配送地址ID */
	public void setOrderAddressId(Long orderAddressId) {
		this.orderAddressId = orderAddressId;
	}

	/** 买家配送地址ID */
	public Long getOrderAddressId() {
		return this.orderAddressId;
	}

	/** 订单信息IP */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/** 订单信息IP */
	public String getIpAddress() {
		return this.ipAddress;
	}

	/** 订单FD纠纷状态 */
	public void setFdDisputeStatus(String fdDisputeStatus) {
		this.fdDisputeStatus = fdDisputeStatus;
	}

	/** 订单FD纠纷状态 */
	public String getFdDisputeStatus() {
		return this.fdDisputeStatus;
	}

	/** 订单支付网关纠纷状态 */
	public void setDisputeStatus(String disputeStatus) {
		this.disputeStatus = disputeStatus;
	}

	/** 订单支付网关纠纷状态 */
	public String getDisputeStatus() {
		return this.disputeStatus;
	}

	/** 订单冻结状态 */
	public void setFreeze(String freeze) {
		this.freeze = freeze;
	}

	/** 订单冻结状态 */
	public String getFreeze() {
		return this.freeze;
	}

	/** 订单风控状态 */
	public void setRiskStatus(String riskStatus) {
		this.riskStatus = riskStatus;
	}

	/** 订单风控状态 */
	public String getRiskStatus() {
		return this.riskStatus;
	}

	/** 订单主状态 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/** 订单主状态 */
	public String getOrderStatus() {
		return this.orderStatus;
	}

	/** 订单退款状态 */
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	/** 订单退款状态 */
	public String getRefundStatus() {
		return this.refundStatus;
	}

	/** 物流方式 */
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	/** 物流方式 */
	public String getShippingType() {
		return this.shippingType;
	}

	/** 支付ID */
	public void setPaymentInfoId(String paymentInfoId) {
		this.paymentInfoId = paymentInfoId;
	}

	/** 支付ID */
	public String getPaymentInfoId() {
		return this.paymentInfoId;
	}

	/** 优惠卷 */
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	/** 优惠卷 */
	public String getCouponCode() {
		return this.couponCode;
	}

	/** 优惠卷金额 */
	public void setCouponAmount(Double couponAmount) {
		this.couponAmount = couponAmount;
	}

	/** 优惠卷金额 */
	public Double getCouponAmount() {
		return this.couponAmount;
	}

	/** 订单调整金额 */
	public void setAdjustAmount(Double adjustAmount) {
		this.adjustAmount = adjustAmount;
	}

	/** 订单调整金额 */
	public Double getAdjustAmount() {
		return this.adjustAmount;
	}

	/** 订单交易号 */
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	/** 订单交易号 */
	public String getTxnId() {
		return this.txnId;
	}

	/** 退款金额 */
	public Double getRefundAmount() {
		return refundAmount;
	}

	/** 退款金额 */
	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}

	/**
	 * @return the shippingTotal
	 */
	public Double getShippingTotal() {
		return shippingTotal;
	}

	/**
	 * @param shippingTotal the shippingTotal to set
	 */
	public void setShippingTotal(Double shippingTotal) {
		this.shippingTotal = shippingTotal;
	}

	/**
	 * @return the itemTotal
	 */
	public Double getItemTotal() {
		return itemTotal;
	}

	/**
	 * @param itemTotal the itemTotal to set
	 */
	public void setItemTotal(Double itemTotal) {
		this.itemTotal = itemTotal;
	}

	/**
	 * @return the maxStockDays
	 */
	public Integer getMaxStockDays() {
		return maxStockDays;
	}

	/**
	 * @param maxStockDays the maxStockDays to set
	 */
	public void setMaxStockDays(Integer maxStockDays) {
		this.maxStockDays = maxStockDays;
	}

	/**
	 * @return the shippingCountry
	 */
	public String getShippingCountry() {
		return shippingCountry;
	}

	/**
	 * @param shippingCountry the shippingCountry to set
	 */
	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public Integer getBadOrder() {
		return badOrder;
	}

	public void setBadOrder(Integer badOrder) {
		this.badOrder = badOrder;
	}
	
	
}