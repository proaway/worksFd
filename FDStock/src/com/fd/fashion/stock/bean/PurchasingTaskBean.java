package com.fd.fashion.stock.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @createTime 2013-6-19 下午3:25:00
 * @author ErWei
 * @description 采购任务
 * 
 */
public class PurchasingTaskBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1008309690506406156L;
	// 任务ID
	private Long taskId;
	// 产品SKU
	private String sku;
	// 产品名称
	private String productName;
	// 采购数量
	private int purchasingCount;
	// 推荐采购渠道
	private String recommendBuyType;
	// 简单描述
	private String description;
	// 发布时间
	private Date publishTime;
	// 发布人
	private String publishUser;
	// 状态（1：未采购，2：已认领，3：等待入库，4：成功，5：部分成功，断货，6：未成功断货 ，7：取消采购）
	private int status;
	// 备注
	private String memo;
	// 领取人
	private String receiver;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPurchasingCount() {
		return purchasingCount;
	}

	public void setPurchasingCount(int purchasingCount) {
		this.purchasingCount = purchasingCount;
	}

	public String getRecommendBuyType() {
		return recommendBuyType;
	}

	public void setRecommendBuyType(String recommendBuyType) {
		this.recommendBuyType = recommendBuyType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getPublishUser() {
		return publishUser;
	}

	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

}