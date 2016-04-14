package com.fd.fashion.seller.bean;


/** 卖家运费信息模板细节自定义 */
public class ShippingOptionBean {
	/** 卖家运费信息模板细节自定义ID,主键 */
	private Long optionId;
	/** 卖家运费信息模板细节ID,外键 */
	private Long detailId;
	/** 首重价格 */
	private Double weightPrice;
	/** 续重价格 */
	private Double reWeightPrice;
	/** 物流天数 */
	private String transportDays;
	/** 该方案所支持的国家（多个国家以逗号分隔） */
	private String shippingCountry;
	/** 具体运费类型（折扣：off、免运费：freeshipping、自定义运费：custom） */
	private String shippingType;
	/** 折扣率 */
	private Integer discountRate;

	/** 卖家运费信息模板细节自定义ID,主键 */
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	/** 卖家运费信息模板细节自定义ID,主键 */
	public Long getOptionId() {
		return this.optionId;
	}

	/** 卖家运费信息模板细节ID,外键 */
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	/** 卖家运费信息模板细节ID,外键 */
	public Long getDetailId() {
		return this.detailId;
	}

	/** 首重价格 */
	public void setWeightPrice(Double weightPrice) {
		this.weightPrice = weightPrice;
	}

	/** 首重价格 */
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

	/** 物流天数 */
	public void setTransportDays(String shippingcost) {
		this.transportDays = shippingcost;
	}

	/** 物流天数 */
	public String getTransportDays() {
		return this.transportDays;
	}

	/** 该方案所支持的国家（多个国家以逗号分隔） */
	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	/** 该方案所支持的国家（多个国家以逗号分隔） */
	public String getShippingCountry() {
		return this.shippingCountry;
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
}