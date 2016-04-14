package com.fd.fashion.product.vo;

public class ProductSelectVo {
	/** 产品ID */
	private Long productId;
	/** 分类ID */
	private String catId;
	/** sku号，唯一编号 */
	private String sku;
	/** 产品名称 */
	private String productName;
	/** 图片url */
	private String imageUrl;
	/** 产品链接 */
	private String productUrl;
	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}
	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * @return the productUrl
	 */
	public String getProductUrl() {
		return productUrl;
	}
	/**
	 * @param productUrl the productUrl to set
	 */
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	/**
	 * @return the catId
	 */
	public String getCatId() {
		return catId;
	}
	/**
	 * @param catId the catId to set
	 */
	public void setCatId(String catId) {
		this.catId = catId;
	}

}
