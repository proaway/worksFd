package com.fd.fashion.product.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import com.fd.util.PageInfo;
import com.google.code.ssm.api.CacheKeyMethod;

/** 图片 */
public class ImageBean implements Serializable {
	private static final long serialVersionUID = -7282290341916266356L;
	/** 图片ID */
	private Long imageId;
	/** 分类ID */
	private String catId;
	/** 图片URL */
	private String imageUrl;
	/** 供应商ID */
	private Long sellerId;
	/** 标签 */
	//private String tag;
	/** 图片名称 */
	private String imageName;
	/** 创建时间 */
	private Date createTime;
	/** 上传人 */
	private String operator;
	/** 图片大小 */
	private Long imageSize;
	/** 是否已使用，0-未使用，1-已使用 */
	private Integer isused;
	/** 排序规则：1-文件名称  2-文件大小 3-上传时间 **/
	private String orderByStatus;
	/** 分页 **/
	private PageInfo pageInfo;
	/**查询参数 **/
	private HashMap<String,String> params;
	
	@CacheKeyMethod
	public String cachedKey() {
		return this.getClass().getName() + "." + imageId;
	}

	/** 图片ID */
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	/** 图片ID */
	public Long getImageId() {
		return this.imageId;
	}

	/** 分类ID */
	public void setCatId(String catId) {
		this.catId = catId;
	}

	/** 分类ID */
	public String getCatId() {
		return this.catId;
	}

	/** 图片URL */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/** 图片URL */
	public String getImageUrl() {
		return this.imageUrl;
	}

	/** 供应商ID */
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	/** 供应商ID */
	public Long getSellerId() {
		return this.sellerId;
	}
/*
	*//** 标签 *//*
	public void setTag(String tag) {
		this.tag = tag;
	}

	*//** 标签 *//*
	public String getTag() {
		return this.tag;
	}*/

	/** 图片名称 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/** 图片名称 */
	public String getImageName() {
		return this.imageName;
	}

	/** 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 创建时间 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/** 上传人 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/** 上传人 */
	public String getOperator() {
		return this.operator;
	}

	/** 图片大小 */
	public void setImageSize(Long imageSize) {
		this.imageSize = imageSize;
	}

	/** 图片大小 */
	public Long getImageSize() {
		return this.imageSize;
	}

	/** 是否已使用，0-未使用，1-已使用 */
	public void setIsused(Integer isused) {
		this.isused = isused;
	}

	/** 是否已使用，0-未使用，1-已使用 */
	public Integer getIsused() {
		return this.isused;
	}

	/**
	 * @return the orderByStatus
	 */
	public String getOrderByStatus() {
		return orderByStatus;
	}

	/**
	 * @param orderByStatus the orderByStatus to set
	 */
	public void setOrderByStatus(String orderByStatus) {
		this.orderByStatus = orderByStatus;
	}

	/**
	 * @return the pageInfo
	 */
	public PageInfo getPageInfo() {
		return pageInfo;
	}

	/**
	 * @param pageInfo the pageInfo to set
	 */
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	/**
	 * @return the params
	 */
	public HashMap<String,String> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(HashMap<String,String> params) {
		this.params = params;
	}
}