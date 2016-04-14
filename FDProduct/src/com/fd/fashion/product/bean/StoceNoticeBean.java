package com.fd.fashion.product.bean;


/** 缺货登记 */
public class StoceNoticeBean {
	/** ID主键 */
	private Long stockNoticeId;
	/** 买家Id */
	private Long buyerId;
	/** 产品ID */
	private Long productId;
	/** 产品规格ID */
	private Long standardId;

	/** ID主键 */
	public void setStockNoticeId(Long stockNoticeId) {
		this.stockNoticeId = stockNoticeId;
	}

	/** ID主键 */
	public Long getStockNoticeId() {
		return this.stockNoticeId;
	}

	/** 买家Id */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/** 买家Id */
	public Long getBuyerId() {
		return this.buyerId;
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
}