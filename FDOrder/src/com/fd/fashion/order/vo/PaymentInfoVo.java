package com.fd.fashion.order.vo;

import com.fd.fashion.order.bean.OrderPaymentinfoBean;

public class PaymentInfoVo {
	private OrderPaymentinfoBean paymentInfoBean;
	/** 付款方式 */
	private String paymentType;
	/** 付款时间 */
	private String payTime;
	/** 信用卡号 */
	private String cardNum;
	/** 账单地址 */
	private String billAddress;
	/** 账单邮箱 */
	private String billEmail;
	/** Paypal帐号状态 */
	private String paypalVerified;
	/**
	 * @return the paymentInfoBean
	 */
	public OrderPaymentinfoBean getPaymentInfoBean() {
		return paymentInfoBean;
	}
	/**
	 * @param paymentInfoBean the paymentInfoBean to set
	 */
	public void setPaymentInfoBean(OrderPaymentinfoBean paymentInfoBean) {
		this.paymentInfoBean = paymentInfoBean;
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
	 * @return the payTime
	 */
	public String getPayTime() {
		return payTime;
	}
	/**
	 * @param payTime the payTime to set
	 */
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	/**
	 * @return the cardNum
	 */
	public String getCardNum() {
		return cardNum;
	}
	/**
	 * @param cardNum the cardNum to set
	 */
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	/**
	 * @return the billAddress
	 */
	public String getBillAddress() {
		return billAddress;
	}
	/**
	 * @param billAddress the billAddress to set
	 */
	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}
	/**
	 * @return the billEmail
	 */
	public String getBillEmail() {
		return billEmail;
	}
	/**
	 * @param billEmail the billEmail to set
	 */
	public void setBillEmail(String billEmail) {
		this.billEmail = billEmail;
	}
	/**
	 * @return the paypalVerified
	 */
	public String getPaypalVerified() {
		return paypalVerified;
	}
	/**
	 * @param paypalVerified the paypalVerified to set
	 */
	public void setPaypalVerified(String paypalVerified) {
		this.paypalVerified = paypalVerified;
	}
}
