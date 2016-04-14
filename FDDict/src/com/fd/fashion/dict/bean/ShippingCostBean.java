package com.fd.fashion.dict.bean;


/** 物流费率 */
public class ShippingCostBean {
	/** 主键 */
	private Long shippingCostId;
	/** 地区id，取值于TC_REGIONS(外键) */
	private String country;
	/** 物流公司 */
	private String shippingCompany;
	/** 起重价格 */
	private Double weightPrice;
	/** 续重价格 */
	private Double reWeightPrice;
	/** 预计到达时间*/
	private String transportDays;
	/** 物流国家区域 */
	private Integer area;
	
	/** 主键 */
	public void setShippingCostId(Long shippingCostId) {
		this.shippingCostId = shippingCostId;
	}

	/** 主键 */
	public Long getShippingCostId() {
		return this.shippingCostId;
	}

	/** 地区id，取值于TC_REGIONS(外键) */
	public void setCountry(String regionId) {
		this.country = regionId;
	}

	/** 地区id，取值于TC_REGIONS(外键) */
	public String getCountry() {
		return this.country;
	}

	/** 物流公司 */
	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}

	/** 物流公司 */
	public String getShippingCompany() {
		return this.shippingCompany;
	}

	/** 起重价格 */
	public void setWeightPrice(Double weightPrice) {
		this.weightPrice = weightPrice;
	}

	/** 起重价格 */
	public Double getWeightPrice() {
		return this.weightPrice;
	}

	/** 续重价格 */
	public void setReWeightPrice(Double reWeightPrice) {
		this.reWeightPrice = reWeightPrice;
	}

	/** 续重价格 */
	public Double getReWeightPrice() {
		return this.reWeightPrice;
	}

	/**
	 * @param transportDays the transportDays to set
	 */
	public void setTransportDays(String transportDays) {
		this.transportDays = transportDays;
	}

	/**
	 * @return the transportDays
	 */
	public String getTransportDays() {
		return transportDays;
	}

	/**
	 * @return the area
	 */
	public Integer getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(Integer area) {
		this.area = area;
	}
}