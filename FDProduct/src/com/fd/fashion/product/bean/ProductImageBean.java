package com.fd.fashion.product.bean;

import java.io.Serializable;


/** 产品图片 */
public class ProductImageBean implements Serializable {
	private static final long serialVersionUID = -1388306465327200012L;
	/** 产品ID */
	private Long productId;
	/** 图片ID */
	private Long imageId;
	/** 图片index */
	private Integer indexId;

	/** 产品ID */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/** 产品ID */
	public Long getProductId() {
		return this.productId;
	}

	/** 图片ID */
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	/** 图片ID */
	public Long getImageId() {
		return this.imageId;
	}

	/**
	 * @return the indexId
	 */
	public Integer getIndexId() {
		return indexId;
	}

	/**
	 * @param indexId the indexId to set
	 */
	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

}