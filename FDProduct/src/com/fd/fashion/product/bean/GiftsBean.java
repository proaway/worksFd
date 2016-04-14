package com.fd.fashion.product.bean;

import com.fd.fashion.product.vo.ProductVo;

/** 赠品 */
public class GiftsBean {
	/** */
	private Long giftsId;
	/** 活动ID */
	private String activityId;
	/** 赠品ID */
	private Long productId;
	/** 赠品数量类型（0, 库存数量，1 自定义） */
	private Integer numType;
	/** 赠品数量 */
	private Integer num;
	/** 已赠数量 */
	private Integer giftsCount;
	
	private ProductVo productVo;
	

	/** 活动ID */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/** 活动ID */
	public String getActivityId() {
		return this.activityId;
	}

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
	 * @return the numType
	 */
	public Integer getNumType() {
		return numType;
	}

	/**
	 * @param numType the numType to set
	 */
	public void setNumType(Integer numType) {
		this.numType = numType;
	}

	/**
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * @return the giftsId
	 */
	public Long getGiftsId() {
		return giftsId;
	}

	/**
	 * @param giftsId the giftsId to set
	 */
	public void setGiftsId(Long giftsId) {
		this.giftsId = giftsId;
	}

	/**
	 * @return the productVo
	 */
	public ProductVo getProductVo() {
		return productVo;
	}

	/**
	 * @param productVo the productVo to set
	 */
	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}

	/**
	 * @return the giftsCount
	 */
	public Integer getGiftsCount() {
		return giftsCount;
	}

	/**
	 * @param giftsCount the giftsCount to set
	 */
	public void setGiftsCount(Integer giftsCount) {
		this.giftsCount = giftsCount;
	}


}