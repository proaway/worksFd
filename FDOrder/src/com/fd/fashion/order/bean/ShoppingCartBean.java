package com.fd.fashion.order.bean;

import java.util.Date;

import com.fd.util.PageInfo;

/** 购物车 */
public class ShoppingCartBean {
	/** 购物车ID */
	private Long cartId;
	/** 买家ID */
	private Long buyerId;
	/** 产品ID */
	private Long productId;
	/** 主属性Id */
	private Long mainConfigId;
	/** 从属性ID */
	private Long subConfigId;
	/** sku */
	private String sku;
	/** 数量 */
	private Integer quantity;
	/** 产品名称 */
	private String productName;
	/** 产品规格ID */
	private Long standardId;
	/** 重量 */
	private Double weight;
	/** 长 */
	private Integer length;
	/** 宽 */
	private Integer width;
	/** 高 */
	private Integer height;
	/** 添加时间 */
	private Date createDate;
	/** 图片路径 */
	private String imageUrl;
	/** 价格 */
	private Double price;
	/** 选定主属性title */
	private String mainTitle;
	/** 选定主属性值 */
	private String subTitle;
	/** 选定从属性title */
	private String mainId;
	/** 选定从属性值 */
	private String subId;
	/** 备货期 */
	private Integer maxStockDays;
	
	private PageInfo pageInfo;
	
	/**产品分类*/
	private String catName;

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	/** 购物车ID */
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	/** 购物车ID */
	public Long getCartId() {
		return this.cartId;
	}

	/** 买家ID */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/** 买家ID */
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

	/** 主属性Id */
	public void setMainConfigId(Long mainConfigId) {
		this.mainConfigId = mainConfigId;
	}

	/** 主属性Id */
	public Long getMainConfigId() {
		return this.mainConfigId;
	}

	/** 从属性ID */
	public void setSubConfigId(Long subConfigId) {
		this.subConfigId = subConfigId;
	}

	/** 从属性ID */
	public Long getSubConfigId() {
		return this.subConfigId;
	}

	/** sku */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/** sku */
	public String getSku() {
		return this.sku;
	}

	/** 数量 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/** 数量 */
	public Integer getQuantity() {
		return this.quantity;
	}

	/** 产品名称 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/** 产品名称 */
	public String getProductName() {
		return this.productName;
	}

	/** 产品规格ID */
	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	/** 产品规格ID */
	public Long getStandardId() {
		return this.standardId;
	}

	/** 重量 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/** 重量 */
	public Double getWeight() {
		return this.weight;
	}

	/** 长 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/** 长 */
	public Integer getLength() {
		return this.length;
	}

	/** 宽 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/** 宽 */
	public Integer getWidth() {
		return this.width;
	}

	/** 高 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/** 高 */
	public Integer getHeight() {
		return this.height;
	}

	/** 添加时间 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/** 添加时间 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/** 图片路径 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/** 图片路径 */
	public String getImageUrl() {
		return this.imageUrl;
	}

	/** 价格 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/** 价格 */
	public Double getPrice() {
		return this.price;
	}

	/** 选定主属性title */
	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	/** 选定主属性title */
	public String getMainTitle() {
		return this.mainTitle;
	}

	/** 选定主属性值 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/** 选定主属性值 */
	public String getSubTitle() {
		return this.subTitle;
	}

	/** 选定从属性title */
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	/** 选定从属性title */
	public String getMainId() {
		return this.mainId;
	}

	/** 选定从属性值 */
	public void setSubId(String subId) {
		this.subId = subId;
	}

	/** 选定从属性值 */
	public String getSubId() {
		return this.subId;
	}

	/** 备货期 */
	public void setMaxStockDays(Integer maxStockDays) {
		this.maxStockDays = maxStockDays;
	}

	/** 备货期 */
	public Integer getMaxStockDays() {
		return this.maxStockDays;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
}