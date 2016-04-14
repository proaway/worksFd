/**
 * OrderVo.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.order.vo;

import java.util.Date;

import com.fd.fashion.buyer.bean.BuyerAddressBookBean;
import com.fd.fashion.order.bean.OrderAddressBean;

/**
 * @CreateDate:  2013-4-12 下午6:07:00 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class OrderVo {
	/** 订单Id*/
	private Long orderId;
	/** 买家名称buyerName*/
	private String userId;
	/**买家ID*/
	private String buyerId;
	/** 订单国家*/
	private String shippingCountry;
	/** 备货时间*/
	private String stockDate;
	/** 订单生成时间*/
	private Date createDate;
	/** 订单付款时间*/
	private Date paymentDate;
	/** 订单付款方式*/
	private String paymentType;
	/** 订单金额*/
	private Double total;
	/** 发货日期*/
	private Date shippingDate;
	/** 货运方式*/
	private String shippingType;
	/** 订单状态*/
	private String orderStatus;
	/**订单状态中文*/
	private String orderStatusCN;
	/** 订单FD纠纷状态 */
	private String fdDisputeStatus;
	/** 订单支付网关纠纷状态 */
	private String disputeStatus;
	/** 订单冻结状态 */
	private String freeze;
	/**坏单*/
	private Integer badOrder;
	
	/** 订单风控状态 */
	private String riskStatus;
	/** 订单退款状态 */
	private String refundStatus;
	/** 订单最长备货期*/
	private Integer maxStockDays;
	/** 订单送货地址 */
	private String shippingAddress;
	/** 订单调整金额 */
	private Double adjustAmount;
	/** 订单调整金额 */
	private Double couponAmount;
	/**支付ID*/
	private Long paymentInfoId;
	/** 买家配送地址ID */
	private Long orderAddressId;
	/** 地址 */
	private OrderAddressBean addressBean;
	/**
	 * @return
	 */
	public String getOrderStatusCN() {
		return orderStatusCN;
	}
	/**
	 * @param orderStatusCode
	 */
	public void setOrderStatusCN(String orderStatusCN) {
		this.orderStatusCN = orderStatusCN;
	}
	/**
	 * @return the buyerId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param buyerId the buyerId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	/**
	 * @return the stockDate
	 */
	public String getStockDate() {
		return stockDate;
	}
	/**
	 * @param stockDate the stockDate to set
	 */
	public void setStockDate(String stockDate) {
		this.stockDate = stockDate;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}
	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}
	/**
	 * @return the shippingDate
	 */
	public Date getShippingDate() {
		return shippingDate;
	}
	/**
	 * @param shippingDate the shippingDate to set
	 */
	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
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
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return the fdDisputeStatus
	 */
	public String getFdDisputeStatus() {
		return fdDisputeStatus;
	}
	/**
	 * @param fdDisputeStatus the fdDisputeStatus to set
	 */
	public void setFdDisputeStatus(String fdDisputeStatus) {
		this.fdDisputeStatus = fdDisputeStatus;
	}
	/**
	 * @return the disputeStatus
	 */
	public String getDisputeStatus() {
		return disputeStatus;
	}
	/**
	 * @param disputeStatus the disputeStatus to set
	 */
	public void setDisputeStatus(String disputeStatus) {
		this.disputeStatus = disputeStatus;
	}
	/**
	 * @return the freeze
	 */
	public String getFreeze() {
		return freeze;
	}
	/**
	 * @param freeze the freeze to set
	 */
	public void setFreeze(String freeze) {
		this.freeze = freeze;
	}
	
	/**
	 * @return
	 */
	public Integer getBadOrder() {
		return badOrder;
	}
	
	/**
	 * @param badOrder
	 */
	public void setBadOrder(Integer badOrder) {
		this.badOrder = badOrder;
	}
	
	/**
	 * @return the riskStatus
	 */
	public String getRiskStatus() {
		return riskStatus;
	}
	/**
	 * @param riskStatus the riskStatus to set
	 */
	public void setRiskStatus(String riskStatus) {
		this.riskStatus = riskStatus;
	}
	/**
	 * @return the refundStatus
	 */
	public String getRefundStatus() {
		return refundStatus;
	}
	/**
	 * @param refundStatus the refundStatus to set
	 */
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
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
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	/**
	 * @return the shippingAddress
	 */
	public String getShippingAddress() {
		return shippingAddress;
	}
	/**
	 * @param shippingAddress the shippingAddress to set
	 */
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	/**
	 * @return the adjustAmount
	 */
	public Double getAdjustAmount() {
		return adjustAmount;
	}
	/**
	 * @param adjustAmount the adjustAmount to set
	 */
	public void setAdjustAmount(Double adjustAmount) {
		this.adjustAmount = adjustAmount;
	}
	/**
	 * @return the couponAmount
	 */
	public Double getCouponAmount() {
		return couponAmount;
	}
	/**
	 * @param couponAmount the couponAmount to set
	 */
	public void setCouponAmount(Double couponAmount) {
		this.couponAmount = couponAmount;
	}
	public Long getPaymentInfoId() {
		return paymentInfoId;
	}
	public void setPaymentInfoId(Long paymentInfoId) {
		this.paymentInfoId = paymentInfoId;
	}
	/**
	 * @return the orderAddressId
	 */
	public Long getOrderAddressId() {
		return orderAddressId;
	}
	/**
	 * @param orderAddressId the orderAddressId to set
	 */
	public void setOrderAddressId(Long orderAddressId) {
		this.orderAddressId = orderAddressId;
	}
	/**
	 * @return the addressBook
	 */
	public OrderAddressBean getAddressBean() {
		return addressBean;
	}
	/**
	 * @param addressBook the addressBook to set
	 */
	public void setAddressBean(OrderAddressBean addressBean) {
		this.addressBean = addressBean;
	}
	
	
	
}
