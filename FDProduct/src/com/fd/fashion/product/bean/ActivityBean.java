package com.fd.fashion.product.bean;

import java.util.Date;

/** 活动 */
public class ActivityBean {
	/** 活动ID */
	private String activityId;
	/** 活动类型0:组合销售  1:赠品 */
	private Integer type;
	/** 活动名称 */
	private String activityName;
	/** 创建日期 */
	private Date createTime;
	/** 最后修改时间 */
	private Date lastEditTime;
	/** 最低消费金额 */
	private Double minAmount;
	/** 国家 */
	private String country;
	/** 相关产品ID */
	private Long productId;
	/** 活动开始时间 */
	private Date startTime;
	/** 活动 结束时间 */
	private Date endTime;
	/** 产品SKU */
	private String sku;
	/** 状态0：未开始，1：开始，2：结束 */
	private Integer status;
	/** 修改人 */
	private String operator;
	
	/** */
	private String searchDateType;
	private Date searchStartDate;
	private Date searchEndDate;

	/** 活动ID */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/** 活动ID */
	public String getActivityId() {
		return this.activityId;
	}

	/** 活动类型 */
	public void setType(Integer type) {
		this.type = type;
	}

	/** 活动类型 */
	public Integer getType() {
		return this.type;
	}

	/** 活动名称 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/** 活动名称 */
	public String getActivityName() {
		return this.activityName;
	}

	/** 创建日期 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 创建日期 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/** 最后修改时间 */
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	/** 最后修改时间 */
	public Date getLastEditTime() {
		return this.lastEditTime;
	}

	/** 最低消费金额 */
	public void setMinAmount(Double minAmount) {
		this.minAmount = minAmount;
	}

	/** 最低消费金额 */
	public Double getMinAmount() {
		return this.minAmount;
	}

	/** 国家 */
	public void setCountry(String country) {
		this.country = country;
	}

	/** 国家 */
	public String getCountry() {
		return this.country;
	}

	/** 相关产品ID */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/** 相关产品ID */
	public Long getProductId() {
		return this.productId;
	}

	/** 活动开始时间 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/** 活动开始时间 */
	public Date getStartTime() {
		return this.startTime;
	}

	/** 活动 结束时间 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/** 活动 结束时间 */
	public Date getEndTime() {
		return this.endTime;
	}

	/** 产品SKU */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/** 产品SKU */
	public String getSku() {
		return this.sku;
	}

	/** 状态0：未开始，1：开始，2：结束 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 状态0：未开始，1：开始，2：结束 */
	public Integer getStatus() {
		return this.status;
	}

	/** 修改人 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/** 修改人 */
	public String getOperator() {
		return this.operator;
	}

	/**
	 * @return the searchDateType
	 */
	public String getSearchDateType() {
		return searchDateType;
	}

	/**
	 * @param searchDateType the searchDateType to set
	 */
	public void setSearchDateType(String searchDateType) {
		this.searchDateType = searchDateType;
	}

	/**
	 * @return the searchStartDate
	 */
	public Date getSearchStartDate() {
		return searchStartDate;
	}

	/**
	 * @param searchStartDate the searchStartDate to set
	 */
	public void setSearchStartDate(Date searchStartDate) {
		this.searchStartDate = searchStartDate;
	}

	/**
	 * @return the searchEndDate
	 */
	public Date getSearchEndDate() {
		return searchEndDate;
	}

	/**
	 * @param searchEndDate the searchEndDate to set
	 */
	public void setSearchEndDate(Date searchEndDate) {
		this.searchEndDate = searchEndDate;
	}
}