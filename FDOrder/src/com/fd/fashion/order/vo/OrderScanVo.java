package com.fd.fashion.order.vo;

import java.io.Serializable;
import java.util.Date;

import com.fd.util.PageInfo;

/**
 * @author zhangling
 * 
 * 扫单录入
 *
 */
public class OrderScanVo implements Serializable{
	
	/**
	 * 扫单录入所需显示的属性
	 */
	private static final long serialVersionUID = 1L;
	
	/**订单号*/
	private Long orderNo;
	/**物流号*/
	private String trackNo;
	/**订单状态CODE*/
	private String orderStatus;
	/**订单状态NAME*/
	private String orderStatusName;
	/**订单金额*/
	private Double totalCost;
	/**订单产品个数*/
	private Integer totalCount;
	/**订单总重量*/
	private Double totalWeight;
	/**买家ID*/
	private Long buyerId;
	/**买家NAME*/
	private String buyerName;
	/**货运地址*/
	private String shippingAddress;
	/**货运方式*/
	private String shippingType;
	/**货运时间*/
	private String shippingDate;
	/**支付时间*/
	private Date paymentTime;
	/**支付方式*/
	private Integer paymentType;
	private String paymentTypeName;
	
	/**货运国家*/
	private String shippingCountry;
	private String countryName;
	/**支付开始时间*/
	private Date paymentTimeStart;
	
	/**支付结束时间*/
	private Date paymentTimeEnd;
	
	private String pickUser;
	
	private String pickDate;
	
	private PageInfo pageInfo;
	
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public String getTrackNo() {
		return trackNo;
	}
	public void setTrackNo(String trackNo) {
		this.trackNo = trackNo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderStatusName() {
		return orderStatusName;
	}
	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Double getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}
	public Long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getShippingType() {
		return shippingType;
	}
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}
	public String getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}
	public Date getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	public Integer getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
	public String getShippingCountry() {
		return shippingCountry;
	}
	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}
	public Date getPaymentTimeStart() {
		return paymentTimeStart;
	}
	public void setPaymentTimeStart(Date paymentTimeStart) {
		this.paymentTimeStart = paymentTimeStart;
	}
	public Date getPaymentTimeEnd() {
		return paymentTimeEnd;
	}
	public void setPaymentTimeEnd(Date paymentTimeEnd) {
		this.paymentTimeEnd = paymentTimeEnd;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public String getPaymentTypeName() {
		return paymentTypeName;
	}
	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getPickUser() {
		return pickUser;
	}
	public void setPickUser(String pickUser) {
		this.pickUser = pickUser;
	}
	public String getPickDate() {
		return pickDate;
	}
	public void setPickDate(String pickDate) {
		this.pickDate = pickDate;
	}
	
	
}
