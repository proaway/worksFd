package com.fd.fashion.seller.bean;

import java.util.Date;
import java.util.List;

/** 卖家运费信息模板（主） */
public class ShippingInfoBean {
	/** 卖家运费信息模板ID，主键 */
	private Long shippingInfoId;
	/** 卖家ID */
	private Long sellerId;
	/** 模板名称 */
	private String name;
	/** 模板创建时间 */
	private Date createDate;
	/** 最后修改时间 */
	private Date modifyTime;
	/** 明细 */
	private List<ShippingDetailBean> details;

	/** 卖家运费信息模板ID，主键 */
	public void setShippingInfoId(Long shippingInfoId) {
		this.shippingInfoId = shippingInfoId;
	}

	/** 卖家运费信息模板ID，主键 */
	public Long getShippingInfoId() {
		return this.shippingInfoId;
	}

	/** 卖家ID */
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	/** 卖家ID */
	public Long getSellerId() {
		return this.sellerId;
	}

	/** 模板名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 模板名称 */
	public String getName() {
		return this.name;
	}

	/** 模板创建时间 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/** 模板创建时间 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/** 最后修改时间 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/** 最后修改时间 */
	public Date getModifyTime() {
		return this.modifyTime;
	}

	/**
	 * @return the details
	 */
	public List<ShippingDetailBean> getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(List<ShippingDetailBean> details) {
		this.details = details;
	}
}