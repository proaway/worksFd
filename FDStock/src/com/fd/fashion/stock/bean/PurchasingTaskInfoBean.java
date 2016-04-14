package com.fd.fashion.stock.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @createTime 2013-6-19 下午3:41:35
 * @author ErWei
 * @description 采购任务信息类
 * 
 */

public class PurchasingTaskInfoBean implements Serializable {
	private static final long serialVersionUID = -1343183747858925854L;
	// 采购信息ID
	private Long purchTaskInfoId;
	// 任务ID
	private Long taskId;
	// 凭证号
	private String proffNo;
	// 采购单价
	private Double buyPrice;
	// 采购数量
	private int buyCount;
	// 运费
	private Double shippingPrice;
	// 物流方式
	private String shippingType;
	// 运单号
	private String shippingNo;
	// 采购日期
	private Date buyTime;
	// 产品名称
	private String productName;
	// 产品SKU
	private String sku;

	public Long getPurchTaskInfoId() {
		return purchTaskInfoId;
	}

	public void setPurchTaskInfoId(Long purchTaskInfoId) {
		this.purchTaskInfoId = purchTaskInfoId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getProffNo() {
		return proffNo;
	}

	public void setProffNo(String proffNo) {
		this.proffNo = proffNo;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public Double getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(Double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getShippingNo() {
		return shippingNo;
	}

	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}