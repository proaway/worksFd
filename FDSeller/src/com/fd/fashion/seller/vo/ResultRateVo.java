package com.fd.fashion.seller.vo;

import java.io.Serializable;

public class ResultRateVo implements Serializable {
	/**
	 * 运费的计算后的vo
	 */
	private static final long serialVersionUID = 1032902824081597678L;
	
	
	//卖家运费信息模板细节ID
	private Long shippingCompanyId;

	// 物流方式（EMS、UPS、DHL、TNT、FEDEX）
	private String shippingCompany;
	// 运费
	private String freight;
	// 天数
	private String numberDays;
	
	// 卖家运费成本
	private Double shippingCost;
	
	// 买家运费（卖家运费成本×commission）
	private Double shippingTotal;
	
	//运费方式图标
	private String shippingCompanyImage;
	
	private String isTracking;
	
	private String jsonBackHtml;
	
	
	private boolean selected = false;

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the shippingCost
	 */
	public Double getShippingCost() {
		return shippingCost;
	}

	/**
	 * @param shippingCost the shippingCost to set
	 */
	public void setShippingCost(Double shippingCost) {
		this.shippingCost = shippingCost;
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
	 * @return the shippingCompany
	 */
	public String getShippingCompany() {
		return shippingCompany;
	}

	/**
	 * @param shippingCompany
	 *            the shippingCompany to set
	 */
	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}

	/**
	 * @return the freight
	 */
	public String getFreight() {
		return freight;
	}

	/**
	 * @param freight
	 *            the freight to set
	 */
	public void setFreight(String freight) {
		this.freight = freight;
	}

	/**
	 * @return the numberDays
	 */
	public String getNumberDays() {
		return numberDays;
	}

	/**
	 * @param numberDays
	 *            the numberDays to set
	 */
	public void setNumberDays(String numberDays) {
		this.numberDays = numberDays;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getShippingCompanyImage() {
		return shippingCompanyImage;
	}

	public void setShippingCompanyImage(String shippingCompanyImage) {
		this.shippingCompanyImage = shippingCompanyImage;
	}

	public String getIsTracking() {
		return isTracking;
	}

	public void setIsTracking(String isTracking) {
		this.isTracking = isTracking;
	}

	public String getJsonBackHtml() {
		return jsonBackHtml;
	}

	public void setJsonBackHtml(String jsonBackHtml) {
		this.jsonBackHtml = jsonBackHtml;
	}

	/**
	 * @param shippingCompanyId the shippingCompanyId to set
	 */
	public void setShippingCompanyId(Long shippingCompanyId) {
		this.shippingCompanyId = shippingCompanyId;
	}

	/**
	 * @return the shippingCompanyId
	 */
	public Long getShippingCompanyId() {
		return shippingCompanyId;
	}



}
