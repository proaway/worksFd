package com.fd.fashion.seller.vo;

import java.io.Serializable;
import java.util.List;

public class ShippingTemplateDetailVo implements Serializable {
	/**
	 * 卖家运费信息模板细节表
	 */
	private static final long serialVersionUID = 5949630328317795663L;


	// 卖家运费信息模板细节ID,主键
	private Long detailId;

	// 卖家运费信息模板ID
	private Long shippingInfoId;

	// 物流方式（EMS、UPS、DHL、TNT、FEDEX、HK post）
	private String shippingCompany;

	// 具体运费类型（off、freeshipping、custom）
	private String shippingType;

	// 折扣率
	private Integer discountRate;
	
	// 是否免运费
	private boolean isFreeShipping;
	
	// 免运费国家
	private String freeShippingCountries;
	
	// 自定义明细
	private List<ShippingTemplateOptionVo> options;
	
	/**
	 *  产品详细页所用的运费显示
	 */
	private String shippingTypeShow;
	
	/**
	 * @return the isFreeShipping
	 */
	public boolean isFreeShipping() {
		return isFreeShipping;
	}

	/**
	 * @param isFreeShipping the isFreeShipping to set
	 */
	public void setFreeShipping(boolean isFreeShipping) {
		this.isFreeShipping = isFreeShipping;
	}

	/**
	 * @return the freeShippingCountries
	 */
	public String getFreeShippingCountries() {
		return freeShippingCountries;
	}

	/**
	 * @param freeShippingCountries the freeShippingCountries to set
	 */
	public void setFreeShippingCountries(String freeShippingCountries) {
		this.freeShippingCountries = freeShippingCountries;
	}

	public String getShippingTypeShow() {
		return shippingTypeShow;
	}

	public void setShippingTypeShow(String shippingTypeShow) {
		this.shippingTypeShow = shippingTypeShow;
	}




	/**
	 * 物流方式（EMS、UPS、DHL、TNT、FEDEX）
	 * 
	 * @return the shippingCompany
	 */
	public String getShippingCompany() {
		return shippingCompany;
	}

	/**
	 * 物流方式（EMS、UPS、DHL、TNT、FEDEX）
	 * 
	 * @param shippingCompany
	 *            the shippingCompany to set
	 */
	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}

	/**
	 * 具体运费类型（off、免运费、自定义运费）
	 * 
	 * @return the shippingType
	 */
	public String getShippingType() {
		return shippingType;
	}

	/**
	 * 具体运费类型（off、免运费、自定义运费）
	 * 
	 * @param shippingType
	 *            the shippingType to set
	 */
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	/**
	 * @param edetailId the edetailId to set
	 */
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	/**
	 * @return the edetailId
	 */
	public Long getDetailId() {
		return detailId;
	}

	/**
	 * @param shippingInfoId the shippingInfoId to set
	 */
	public void setShippingInfoId(Long shippingInfoId) {
		this.shippingInfoId = shippingInfoId;
	}

	/**
	 * @return the shippingInfoId
	 */
	public Long getShippingInfoId() {
		return shippingInfoId;
	}

	/**
	 * @param discountRate the discountRate to set
	 */
	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}

	/**
	 * @return the discountRate
	 */
	public Integer getDiscountRate() {
		return discountRate;
	}

	/**
	 * @return the options
	 */
	public List<ShippingTemplateOptionVo> getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(List<ShippingTemplateOptionVo> options) {
		this.options = options;
	}

	
	
}
