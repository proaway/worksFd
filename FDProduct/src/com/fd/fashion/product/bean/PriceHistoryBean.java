package com.fd.fashion.product.bean;

import java.util.Date;

/** 价格历史 */
public class PriceHistoryBean {
	/** 价格历史ID */
	private Long historyId;
	/** 产品ID */
	private Long productId;
	/** 产品规格ID */
	private Long standardId;
	/** 价格 */
	private Float price;
	/** 批发起始数量 */
	private Integer wholesaleCount;
	/** 批发折扣 */
	private Float wholesaleDiscount;
	/** 折扣起始日期 */
	private Date discountStartDate;
	/** 折扣截止日期 */
	private Date discountEndDate;
	/** 折扣值 */
	private Float discount;
	/** 修改时间 */
	private Date modifyTime;

	/** 价格历史ID */
	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	/** 价格历史ID */
	public Long getHistoryId() {
		return this.historyId;
	}

	/** 产品ID */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/** 产品ID */
	public Long getProductId() {
		return this.productId;
	}

	/** 产品规格ID */
	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	/** 产品规格ID */
	public Long getStandardId() {
		return this.standardId;
	}

	/** 价格 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/** 价格 */
	public Float getPrice() {
		return this.price;
	}

	/** 批发起始数量 */
	public void setWholesaleCount(Integer wholesaleCount) {
		this.wholesaleCount = wholesaleCount;
	}

	/** 批发起始数量 */
	public Integer getWholesaleCount() {
		return this.wholesaleCount;
	}

	/** 批发折扣 */
	public void setWholesaleDiscount(Float wholesaleDiscount) {
		this.wholesaleDiscount = wholesaleDiscount;
	}

	/** 批发折扣 */
	public Float getWholesaleDiscount() {
		return this.wholesaleDiscount;
	}

	/** 折扣起始日期 */
	public void setDiscountStartDate(Date discountStartDate) {
		this.discountStartDate = discountStartDate;
	}

	/** 折扣起始日期 */
	public Date getDiscountStartDate() {
		return this.discountStartDate;
	}

	/** 折扣截止日期 */
	public void setDiscountEndDate(Date discountEndDate) {
		this.discountEndDate = discountEndDate;
	}

	/** 折扣截止日期 */
	public Date getDiscountEndDate() {
		return this.discountEndDate;
	}

	/** 折扣值 */
	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	/** 折扣值 */
	public Float getDiscount() {
		return this.discount;
	}

	/** 修改时间 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/** 修改时间 */
	public Date getModifyTime() {
		return this.modifyTime;
	}
}