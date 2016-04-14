/**
 * SearchOrderVo.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.order.vo;

import java.util.Date;
import java.util.List;

import com.fd.util.PageInfo;

/**
 * @CreateDate:  2013-4-12 下午6:40:54 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class SearchOrderVo {
	/** 订单Id*/
	private Long orderId;
	/** 订单信息*/
	private List<OrderVo> orders;
	/** 订单总数*/
	private Integer orderCount;
	/**待付款数量*/
	private Integer waitPaymentcCount;
	/**待发货数量*/
	private Integer waitDeliveryCount;
	/**纠纷数量*/
	private Integer issueCount;
	/**剩余备货时间小于12小时数量*/
	private Integer isStockCount;
	/**备货天数*/
	private Integer maxStockDays;
	/** 订单总金额*/
	private Double orderPriceTotal;
	/** 买家名称 (相当于buyerName)*/
	private String userId;
	/** 订单国家*/
	private String shippingCountry;
	/** 订单状态*/
	private String orderStatus;
	/** 订单状态*/
	private List<String> statusList;
	/** 货运方式*/
	private String shippingType;
	/** 支付类型*/
	private String paymentType;
	/** 订单FD纠纷状态 */
	private String fdDisputeStatus;
	/** 订单支付网关纠纷状态 */
	private List<String> disputeStatus;
	/** 订单冻结状态 */
	private String freeze;
	/** 订单风控状态 */
	private String riskStatus;
	/** 订单退款状态 */
	private List<String> refundStatus;

	/** 订单生成时间*/
	private Date startCreateDate;
	private Date endCreateDate;
	
	/** 订单付款时间*/
	private Date startPaymentDate;
	private Date endPaymentDate;
	
	/** 订单发货时间*/
	private Date startShippingDate;
	private Date endShippingDate;
	/** 分页*/
	private PageInfo pageInfo;
	
	
	public String getFdDisputeStatus() {
		return fdDisputeStatus;
	}
	public void setFdDisputeStatus(String fdDisputeStatus) {
		this.fdDisputeStatus = fdDisputeStatus;
	}

	public List<String> getDisputeStatus() {
		return disputeStatus;
	}
	public void setDisputeStatus(List<String> disputeStatus) {
		this.disputeStatus = disputeStatus;
	}
	public List<String> getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(List<String> refundStatus) {
		this.refundStatus = refundStatus;
	}
	public String getFreeze() {
		return freeze;
	}
	public void setFreeze(String freeze) {
		this.freeze = freeze;
	}
	public String getRiskStatus() {
		return riskStatus;
	}
	public void setRiskStatus(String riskStatus) {
		this.riskStatus = riskStatus;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public List<OrderVo> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderVo> orders) {
		this.orders = orders;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	public Integer getWaitPaymentcCount() {
		return waitPaymentcCount;
	}
	public void setWaitPaymentcCount(Integer waitPaymentcCount) {
		this.waitPaymentcCount = waitPaymentcCount;
	}
	public Integer getWaitDeliveryCount() {
		return waitDeliveryCount;
	}
	public void setWaitDeliveryCount(Integer waitDeliveryCount) {
		this.waitDeliveryCount = waitDeliveryCount;
	}
	public Integer getIssueCount() {
		return issueCount;
	}
	public void setIssueCount(Integer issueCount) {
		this.issueCount = issueCount;
	}
	public Integer getMaxStockDays() {
		return maxStockDays;
	}
	public void setMaxStockDays(Integer maxStockDays) {
		this.maxStockDays = maxStockDays;
	}
	public Double getOrderPriceTotal() {
		return orderPriceTotal;
	}
	public void setOrderPriceTotal(Double orderPriceTotal) {
		this.orderPriceTotal = orderPriceTotal;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShippingCountry() {
		return shippingCountry;
	}
	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getShippingType() {
		return shippingType;
	}
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public Date getStartCreateDate() {
		return startCreateDate;
	}
	public void setStartCreateDate(Date startCreateDate) {
		this.startCreateDate = startCreateDate;
	}
	public Date getEndCreateDate() {
		return endCreateDate;
	}
	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
	public Date getStartPaymentDate() {
		return startPaymentDate;
	}
	public void setStartPaymentDate(Date startPaymentDate) {
		this.startPaymentDate = startPaymentDate;
	}
	public Date getEndPaymentDate() {
		return endPaymentDate;
	}
	public void setEndPaymentDate(Date endPaymentDate) {
		this.endPaymentDate = endPaymentDate;
	}
	public Date getStartShippingDate() {
		return startShippingDate;
	}
	public void setStartShippingDate(Date startShippingDate) {
		this.startShippingDate = startShippingDate;
	}
	public Date getEndShippingDate() {
		return endShippingDate;
	}
	public void setEndShippingDate(Date endShippingDate) {
		this.endShippingDate = endShippingDate;
	}
	/**
	 * @return the pageInfo
	 */
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	/**
	 * @param pageInfo the pageInfo to set
	 */
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	/**
	 * @return the isStockCount
	 */
	public Integer getIsStockCount() {
		return isStockCount;
	}
	/**
	 * @param isStockCount the isStockCount to set
	 */
	public void setIsStockCount(Integer isStockCount) {
		this.isStockCount = isStockCount;
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
	/**
	 * @return the statusList
	 */
	public List<String> getStatusList() {
		return statusList;
	}
	/**
	 * @param statusList the statusList to set
	 */
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}
	




}
