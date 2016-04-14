package com.fd.fashion.product.bean;

import java.io.Serializable;
import java.util.Date;

/** 产品价格 */
public class PriceBean implements Serializable {
	private static final long serialVersionUID = 4651325126898177774L;
	/** 产品ID */
	private Long productId;
	/** 价格 */
	private Double price;
	/** 批发起始数量 */
	private Integer wholesaleCount;
	/** 批发折扣 */
	private Double wholesaleDiscount;
	/** 折扣起始日期 */
	private Date discountStartDate;
	/** 折扣截止日期 */
	private Date discountEndDate;
	/** 折扣值 */
	private Double discount;

	/** 产品ID */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/** 产品ID */
	public Long getProductId() {
		return this.productId;
	}

	/** 价格 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/** 价格 */
	public Double getPrice() {
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
	public void setWholesaleDiscount(Double wholesaleDiscount) {
		this.wholesaleDiscount = wholesaleDiscount;
	}

	/** 批发折扣 */
	public Double getWholesaleDiscount() {
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
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/** 折扣值 */
	public Double getDiscount() {
		return this.discount;
	}
}