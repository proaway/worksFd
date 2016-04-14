package com.fd.fashion.product.bean;

import java.util.Date;

/** 缺货登记 */
public class StockoutBean {
	/** 缺货ID */
	private Long stockoutId;
	/** 产品ID */
	private Long productId;
	/** 产品SKU */
	private String sku;
	/** 拣货人 */
	private String stockoutUser;
	/** 缺货数量 */
	private Integer stockoutCount;
	/** 缺货日期 */
	private Date stockoutDate;
	/** 数据有效性（1：有效，2：无效） */
	private Integer vald;
	/** 买家ID */
	private Long buyerId;

	/** 缺货ID */
	public void setStockoutId(Long stockoutId) {
		this.stockoutId = stockoutId;
	}

	/** 缺货ID */
	public Long getStockoutId() {
		return this.stockoutId;
	}

	/** 产品ID */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/** 产品ID */
	public Long getProductId() {
		return this.productId;
	}

	/** 产品SKU */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/** 产品SKU */
	public String getSku() {
		return this.sku;
	}

	/** 拣货人 */
	public void setStockoutUser(String stockoutUser) {
		this.stockoutUser = stockoutUser;
	}

	/** 拣货人 */
	public String getStockoutUser() {
		return this.stockoutUser;
	}

	/** 缺货数量 */
	public void setStockoutCount(Integer stockoutCount) {
		this.stockoutCount = stockoutCount;
	}

	/** 缺货数量 */
	public Integer getStockoutCount() {
		return this.stockoutCount;
	}

	/** 缺货日期 */
	public void setStockoutDate(Date stockoutDate) {
		this.stockoutDate = stockoutDate;
	}

	/** 缺货日期 */
	public Date getStockoutDate() {
		return this.stockoutDate;
	}

	/** 数据有效性（1：有效，2：无效） */
	public void setVald(Integer vald) {
		this.vald = vald;
	}

	/** 数据有效性（1：有效，2：无效） */
	public Integer getVald() {
		return this.vald;
	}

	/** 买家ID */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/** 买家ID */
	public Long getBuyerId() {
		return this.buyerId;
	}
}