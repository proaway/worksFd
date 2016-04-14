package com.fd.fashion.seller.bean;

import java.util.List;


/** 卖家运费信息模板细节 */
public class ShippingDetailBean {
	/** 卖家运费信息模板细节ID,主键 */
	private Long detailId;
	/** 卖家运费信息模板ID，主键 */
	private Long shippingInfoId;
	/** 物流方式（EMS、UPS、DHL、TNT、FEDEX） */
	private String shippingCompany;
	/** 具体运费类型（折扣：off、免运费：freeshipping、自定义运费：custom） */
	private String shippingType;
	/** 折扣率 */
	private Integer discountRate;
	/** 物流天数 */
	private String transportDays;
	/** 自定义明细 */
	private List<ShippingOptionBean> options;

	/** 卖家运费信息模板细节ID,主键 */
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	/** 卖家运费信息模板细节ID,主键 */
	public Long getDetailId() {
		return this.detailId;
	}

	/** 卖家运费信息模板ID，主键 */
	public void setShippingInfoId(Long shippingInfoId) {
		this.shippingInfoId = shippingInfoId;
	}

	/** 卖家运费信息模板ID，主键 */
	public Long getShippingInfoId() {
		return this.shippingInfoId;
	}

	/** 物流方式（EMS、UPS、DHL、TNT、FEDEX） */
	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}

	/** 物流方式（EMS、UPS、DHL、TNT、FEDEX） */
	public String getShippingCompany() {
		return this.shippingCompany;
	}

	/** 具体运费类型（折扣：off、免运费：freeshipping、自定义运费：custom） */
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	/** 具体运费类型（折扣：off、免运费：freeshipping、自定义运费：custom） */
	public String getShippingType() {
		return this.shippingType;
	}

	/** 折扣率 */
	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}

	/** 折扣率 */
	public Integer getDiscountRate() {
		return this.discountRate;
	}

	/**
	 * @return the options
	 */
	public List<ShippingOptionBean> getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(List<ShippingOptionBean> options) {
		this.options = options;
	}

	/**
	 * @return the transportDays
	 */
	public String getTransportDays() {
		return transportDays;
	}

	/**
	 * @param transportDays the transportDays to set
	 */
	public void setTransportDays(String transportDays) {
		this.transportDays = transportDays;
	}
}