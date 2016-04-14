package com.fd.fashion.seller.vo;

import java.io.Serializable;

public class ShippingTemplateOptionVo implements Serializable {
	/**
	 * 卖家运费信息模板细节自定义表
	 */
	private static final long serialVersionUID = -2488644624605220072L;

	// 卖家运费信息模板细节自定义ID,主键
	private String shippingTemplateOptionId;
	
	// 卖家运费信息模板细节ID,外键
	private Long detailId;
	
	// 起始采购量
	private Integer beginCount;
	
	// 截至采购量
	private Integer endCount;
	
	// 运费成本
	private Float shippingcost;
	
	// 单位增加产品数量
	private Integer addCount;
	
	// 单位增加运费成本
	private Float addShippingcost;
	
	// 该方案所支持的国家（多个国家以逗号分隔）
	private String shippingCountry;
	
	// 该方案所支持的国家名称（多个国家以逗号分隔）
	private String shippingCountryNames;
	
	/** 具体运费类型（折扣：off、免运费：freeshipping、自定义运费：custom） */
	private String shippingType;
	/** 折扣率 */
	private Integer discountRate;
	
	//是否运输到世界各地
	private String toWorld;
	
	//内部的是否免运费
	private String innerNoFreight;
	
	//外部的是否免运费
	private String outerNoFreight;

	public String getInnerNoFreight() {
		return innerNoFreight;
	}

	public void setInnerNoFreight(String innerNoFreight) {
		this.innerNoFreight = innerNoFreight;
	}

	public String getOuterNoFreight() {
		return outerNoFreight;
	}

	public void setOuterNoFreight(String outerNoFreight) {
		this.outerNoFreight = outerNoFreight;
	}

	public String getToWorld() {
		return toWorld;
	}

	public void setToWorld(String toWorld) {
		this.toWorld = toWorld;
	}

	/**
	 * @return the shippingTemplateOptionId
	 */
	public String getShippingTemplateOptionId() {
		return shippingTemplateOptionId;
	}

	/**
	 * @param shippingTemplateOptionId the shippingTemplateOptionId to set
	 */
	public void setShippingTemplateOptionId(String shippingTemplateOptionId) {
		this.shippingTemplateOptionId = shippingTemplateOptionId;
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

	public String getShippingCountryNames() {
		return shippingCountryNames;
	}

	public void setShippingCountryNames(String shippingCountryNames) {
		this.shippingCountryNames = shippingCountryNames;
	}

	/**
	 * @param shippingtYpe the shippingtYpe to set
	 */
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	/**
	 * @return the shippingtYpe
	 */
	public String getShippingType() {
		return shippingType;
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
	 * @param beginCount the beginCount to set
	 */
	public void setBeginCount(Integer beginCount) {
		this.beginCount = beginCount;
	}

	/**
	 * @return the beginCount
	 */
	public Integer getBeginCount() {
		return beginCount;
	}

	/**
	 * @param endCount the endCount to set
	 */
	public void setEndCount(Integer endCount) {
		this.endCount = endCount;
	}

	/**
	 * @return the endCount
	 */
	public Integer getEndCount() {
		return endCount;
	}

	/**
	 * @param shippingcost the shippingcost to set
	 */
	public void setShippingcost(Float shippingcost) {
		this.shippingcost = shippingcost;
	}

	/**
	 * @return the shippingcost
	 */
	public Float getShippingcost() {
		return shippingcost;
	}

	/**
	 * @param addCount the addCount to set
	 */
	public void setAddCount(Integer addCount) {
		this.addCount = addCount;
	}

	/**
	 * @return the addCount
	 */
	public Integer getAddCount() {
		return addCount;
	}

	/**
	 * @param addShippingcost the addShippingcost to set
	 */
	public void setAddShippingcost(Float addShippingcost) {
		this.addShippingcost = addShippingcost;
	}

	/**
	 * @return the addShippingcost
	 */
	public Float getAddShippingcost() {
		return addShippingcost;
	}
}
