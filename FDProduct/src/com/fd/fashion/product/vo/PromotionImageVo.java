package com.fd.fashion.product.vo;

/**某产品所带的赠品
 * @author Administrator
 *
 */
public class PromotionImageVo {
	
	/**
	 * 赠品ID
	 */
	private long productId;
	
	/** 赠品名称 */
	private String productName;
	
	/**
	 * 赠品路径
	 */
	private String imageUrl;

	/**获取赠品ID
	 * @return
	 */
	public long getProductId() {
		return productId;
	}

	/**设置赠品iD
	 * @param productId
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}

	/**获取赠品路径
	 * @return
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**设置赠品路径
	 * @param imageUrl
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

}
