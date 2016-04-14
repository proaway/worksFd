package com.fd.fashion.seller.vo;

import java.io.Serializable;

public class LogisticsRelationVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3146423351369217534L;

	// ID主键
	private Integer relateionId;

	// 地区id
	private Long regionId;

	// 物流公司
	private String shippingCompany;

	// 地区名
	private String regionName;

	// 起重价格
	private Float weightPrice;

	// 续重价格
	private Float reWeightPrice;

	// 运输天数
	private String transportDays;

	/**
	 * ID主键
	 * 
	 * @return the relateionId
	 */
	public Integer getRelateionId() {
		return relateionId;
	}

	/**
	 * ID主键
	 * 
	 * @param relateionId
	 *            the relateionId to set
	 */
	public void setRelateionId(Integer relateionId) {
		this.relateionId = relateionId;
	}



	

	/**
	 * 地区名
	 * 
	 * @return the regionName
	 */
	public String getRegionName() {
		return regionName;
	}

	/**
	 * 地区名
	 * 
	 * @param regionName
	 *            the regionName to set
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	/**
	 * 起重价格
	 * 
	 * @return the weightPrice
	 */
	public Float getWeightPrice() {
		return weightPrice;
	}

	/**
	 * 起重价格
	 * 
	 * @param weightPrice
	 *            the weightPrice to set
	 */
	public void setWeightPrice(Float weightPrice) {
		this.weightPrice = weightPrice;
	}

	/**
	 * 续重价格
	 * 
	 * @return the reWeightPrice
	 */
	public Float getReWeightPrice() {
		return reWeightPrice;
	}

	/**
	 * 续重价格
	 * 
	 * @param reWeightPrice
	 *            the reWeightPrice to set
	 */
	public void setReWeightPrice(Float reWeightPrice) {
		this.reWeightPrice = reWeightPrice;
	}

	/**
	 * 运输天数
	 * 
	 * @return the transportDays
	 */
	public String getTransportDays() {
		return transportDays;
	}

	/**
	 * 运输天数
	 * 
	 * @param transportDays
	 *            the transportDays to set
	 */
	public void setTransportDays(String transportDays) {
		this.transportDays = transportDays;
	}

	/**
	 * @param regionId the regionId to set
	 */
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	/**
	 * @return the regionId
	 */
	public Long getRegionId() {
		return regionId;
	}

	/**
	 * @param shippingCompany the shippingCompany to set
	 */
	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}

	/**
	 * @return the shippingCompany
	 */
	public String getShippingCompany() {
		return shippingCompany;
	}
}
