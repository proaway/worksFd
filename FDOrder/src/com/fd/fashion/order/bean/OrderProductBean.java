package com.fd.fashion.order.bean;

import java.util.HashMap;

import com.fd.fashion.dict.bean.AttributeBean;


/** 订单产品 */
public class OrderProductBean {
	/** 订单产品ID，主键 */
	private Long orderProductId;
	/** 产品ID */
	private Long productId;
	/** 订单ID，主键 */
	private Long orderId;
	/** 产品编号（冗余） */
	private String sku;
	/** 购买数量 */
	private Integer quantity;
	/** 产品成本总价格 */
	private Double itemCost;
	/** 产品价格 */
	private Double itemTotal;
	/** 生成订单是的产品单价 */
	private Double itemPrice;
	/** 生成订单时产品使用的commission */
	private Double commission;
	/** 产品折扣率，为0-100的整数 */
	private Double discount;
	/** 产品规格ID */
	private Long standardId;
	/** 产品主属性名称 */
	private HashMap<String,AttributeBean> config;
	/**产品名称*/
	private String productName;
	/**产品图片路径*/
	private String imageUrl; 
	/**产品封装单位*/
	private String packing;
	/**订单产品coupon优惠金额*/
	private Double couponAmount;
	/**是否缺货*/
	private Integer stockOut;
	/** 产品快照 */
	private String snapshot;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImageUrl() { 
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/** 订单产品ID，主键 */
	public void setOrderProductId(Long orderProductId) {
		this.orderProductId = orderProductId;
	}

	/** 订单产品ID，主键 */
	public Long getOrderProductId() {
		return this.orderProductId;
	}

	/** 产品ID */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/** 产品ID */
	public Long getProductId() {
		return this.productId;
	}

	/** 订单ID，主键 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/** 订单ID，主键 */
	public Long getOrderId() {
		return this.orderId;
	}

	/** 产品编号（冗余） */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/** 产品编号（冗余） */
	public String getSku() {
		return this.sku;
	}

	/** 购买数量 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/** 购买数量 */
	public Integer getQuantity() {
		return this.quantity;
	}

	/** 产品成本价格 */
	public void setItemCost(Double itemCost) {
		this.itemCost = itemCost;
	}

	/** 产品成本价格 */
	public Double getItemCost() {
		return this.itemCost;
	}

	/** 产品价格 */
	public void setItemTotal(Double itemTotal) {
		this.itemTotal = itemTotal;
	}

	/** 产品价格 */
	public Double getItemTotal() {
		return this.itemTotal;
	}

	/** 生成订单是的产品单价 */
	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	/** 生成订单是的产品单价 */
	public Double getItemPrice() {
		return this.itemPrice;
	}

	/** 生成订单时产品使用的commission */
	public void setCommission(Double commission) {
		this.commission = commission;
	}

	/** 生成订单时产品使用的commission */
	public Double getCommission() {
		return this.commission;
	}

	/** 产品折扣率，为0-100的整数 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/** 产品折扣率，为0-100的整数 */
	public Double getDiscount() {
		return this.discount;
	}

	/**
	 * @return the standardId
	 */
	public Long getStandardId() {
		return standardId;
	}

	/**
	 * @param standardId the standardId to set
	 */
	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	/**
	 * @return the config
	 */
	public HashMap<String,AttributeBean> getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(HashMap<String,AttributeBean> config) {
		this.config = config;
	}

	/**产品封装单位
	 * @return
	 */
	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	/**
	 * @return the couponAmount
	 */
	public Double getCouponAmount() {
		return couponAmount;
	}

	/**
	 * @param couponAmount the couponAmount to set
	 */
	public void setCouponAmount(Double couponAmount) {
		this.couponAmount = couponAmount;
	}

	/**是否缺货*/
	public Integer getStockOut() {
		return stockOut;
	}

	/**是否缺货*/
	public void setStockOut(Integer stockOut) {
		this.stockOut = stockOut;
	}

	/** 产品快照 */
	public String getSnapshot() {
		return snapshot;
	}

	/** 产品快照 */
	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}
	
}