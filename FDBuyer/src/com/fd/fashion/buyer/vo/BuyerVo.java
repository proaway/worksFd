package com.fd.fashion.buyer.vo;

import java.util.Date;

import com.fd.fashion.buyer.bean.BuyerBean;

public class BuyerVo {

	/**买家基本信息*/
	private BuyerBean buyer;
	/**支付订单数*/
	private Integer paymentOrderCount;
	/**订单总金额*/
	private Double paymentOrderTotal;
	/**平均金额*/
	private Double paymentOrderAverage;
	/**退款订单*/
	private Integer refundOrderCount;
	/**退款金额*/
	private Double refundOrderTotal;
	/**未付款订单*/
	private Integer waitForPayOrderCount;
	/**未付款金额*/
	private Double waitForPayOrderTotal;
	/**初次下单时间*/
	private Date firstCreateOrderTime;
	/**最近下单时间*/
	private Date lastCreateOrderTime;
	/**初次下单IP*/
	private String fistCreateOrderIp;
	/**最近下单IP*/
	private String lastCreateOrderIp;
	public BuyerBean getBuyer() {
		return buyer;
	}
	public void setBuyer(BuyerBean buyer) {
		this.buyer = buyer;
	}
	public Integer getPaymentOrderCount() {
		return paymentOrderCount;
	}
	public void setPaymentOrderCount(Integer paymentOrderCount) {
		this.paymentOrderCount = paymentOrderCount;
	}
	public Double getPaymentOrderTotal() {
		return paymentOrderTotal;
	}
	public void setPaymentOrderTotal(Double paymentOrderTotal) {
		this.paymentOrderTotal = paymentOrderTotal;
	}
	public Double getPaymentOrderAverage() {
		return paymentOrderAverage;
	}
	public void setPaymentOrderAverage(Double paymentOrderAverage) {
		this.paymentOrderAverage = paymentOrderAverage;
	}
	public Integer getRefundOrderCount() {
		return refundOrderCount;
	}
	public void setRefundOrderCount(Integer refundOrderCount) {
		this.refundOrderCount = refundOrderCount;
	}
	public Double getRefundOrderTotal() {
		return refundOrderTotal;
	}
	public void setRefundOrderTotal(Double refundOrderTotal) {
		this.refundOrderTotal = refundOrderTotal;
	}
	public Integer getWaitForPayOrderCount() {
		return waitForPayOrderCount;
	}
	public void setWaitForPayOrderCount(Integer waitForPayOrderCount) {
		this.waitForPayOrderCount = waitForPayOrderCount;
	}
	public Double getWaitForPayOrderTotal() {
		return waitForPayOrderTotal;
	}
	public void setWaitForPayOrderTotal(Double waitForPayOrderTotal) {
		this.waitForPayOrderTotal = waitForPayOrderTotal;
	}
	public Date getFirstCreateOrderTime() {
		return firstCreateOrderTime;
	}
	public void setFirstCreateOrderTime(Date firstCreateOrderTime) {
		this.firstCreateOrderTime = firstCreateOrderTime;
	}
	public Date getLastCreateOrderTime() {
		return lastCreateOrderTime;
	}
	public void setLastCreateOrderTime(Date lastCreateOrderTime) {
		this.lastCreateOrderTime = lastCreateOrderTime;
	}
	public String getFistCreateOrderIp() {
		return fistCreateOrderIp;
	}
	public void setFistCreateOrderIp(String fistCreateOrderIp) {
		this.fistCreateOrderIp = fistCreateOrderIp;
	}
	public String getLastCreateOrderIp() {
		return lastCreateOrderIp;
	}
	public void setLastCreateOrderIp(String lastCreateOrderIp) {
		this.lastCreateOrderIp = lastCreateOrderIp;
	}

	
}
