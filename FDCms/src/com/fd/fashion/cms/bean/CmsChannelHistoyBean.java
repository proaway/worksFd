package com.fd.fashion.cms.bean;

import java.util.Date;

/** 频道历史 */
public class CmsChannelHistoyBean {
	/** 频道历史ID ，主键 */
	private Long channelhistoryId;
	/** 产品SKU */
	private String sku;
	/** 产品ID */
	private Long productId;
	/** 频道ID */
	private String channelId;
	/** 栏目ID */
	private Long blockId;
	/** 页面位置 */
	private Integer channelLocation;
	/** 发布时间 */
	private Date pubDate;
	/** 结束时间 */
	private Date endDate;

	/** 频道历史ID ，主键 */
	public void setChannelhistoryId(Long channelhistoryId) {
		this.channelhistoryId = channelhistoryId;
	}

	/** 频道历史ID ，主键 */
	public Long getChannelhistoryId() {
		return this.channelhistoryId;
	}

	/** 产品SKU */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/** 产品SKU */
	public String getSku() {
		return this.sku;
	}

	/** 频道ID */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/** 频道ID */
	public String getChannelId() {
		return this.channelId;
	}

	/** 栏目ID */
	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	/** 栏目ID */
	public Long getBlockId() {
		return this.blockId;
	}

	/** 页面位置 */
	public void setChannelLocation(Integer channelLocation) {
		this.channelLocation = channelLocation;
	}

	/** 页面位置 */
	public Integer getChannelLocation() {
		return this.channelLocation;
	}

	/** 发布时间 */
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	/** 发布时间 */
	public Date getPubDate() {
		return this.pubDate;
	}

	/** 结束时间 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/** 结束时间 */
	public Date getEndDate() {
		return this.endDate;
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
}