/**
 * CartProductVo.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.product.vo;

import java.io.Serializable;

import com.fd.util.CullNumUtil;

/**
 * @CreateDate:  2013-4-12 下午3:17:20 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class CartProductVo implements Serializable {
	private static final long serialVersionUID = -8250941133468295869L;
	/** 产品ID */
	private long productId;
	/** 产品主配置ID */
	private Long mainConfigId;
	/** 产品从配置ID2 */
	private Long subConfigId;
	/** 规格ID */
	private Long standardId;
	/** 规格SKU */
	private String sku;
	/** 数量 */
	private Integer quantity;
	/** 产品名称 */
	private String productName;
	/** 图片url */
	private String imageUrl;
	/** 产品零售价 */
	private Double price;
	/** 重量 kg*/
	private double weight;
	/** 长 cm*/
	private int length;
	/** 高 cm*/
	private int height;
	/** 宽 cm*/
	private int width;
	/** 主属性名称 */
	private String mainTitle;
	/** 从属性名称 */
	private String subTitle;
	/** 主属性 */
	private String mainId;
	/** 从属性 */
	private String subId;
	/** 备货天数*/
	private Integer maxStockDays;
	/** 购物车产品是否有改动 */
	private boolean changed;
	/** 是否下架 */
	private boolean offSale;
	/** 购物车产品分类 */
	private String catId;
	/** 购物车coupon产品标记 */
	private int isCouponProduct;
	
	/**
	 * @return the productId
	 */
	public long getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}
	/**
	 * @return the mainConfigId
	 */
	public Long getMainConfigId() {
		return mainConfigId;
	}
	/**
	 * @param mainConfigId the mainConfigId to set
	 */
	public void setMainConfigId(Long mainConfigId) {
		this.mainConfigId = mainConfigId;
	}
	/**
	 * @return the subConfigId
	 */
	public Long getSubConfigId() {
		return subConfigId;
	}
	/**
	 * @param subConfigId the subConfigId to set
	 */
	public void setSubConfigId(Long subConfigId) {
		this.subConfigId = subConfigId;
	}
	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}
	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the mainTitle
	 */
	public String getMainTitle() {
		return mainTitle;
	}
	/**
	 * @param mainTitle the mainTitle to set
	 */
	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}
	/**
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}
	/**
	 * @param subTitle the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	/**
	 * @return the mainId
	 */
	public String getMainId() {
		return mainId;
	}
	/**
	 * @param mainId the mainId to set
	 */
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	/**
	 * @return the subId
	 */
	public String getSubId() {
		return subId;
	}
	/**
	 * @param subId the subId to set
	 */
	public void setSubId(String subId) {
		this.subId = subId;
	}
	/**
	 * @return the sumPrice
	 */
	public double getSumPrice() {
		return CullNumUtil.cullNum(this.price * this.quantity);
	}
	/**
	 * @return the maxStockDays
	 */
	public Integer getMaxStockDays() {
		return maxStockDays;
	}
	/**
	 * @param maxStockDays the maxStockDays to set
	 */
	public void setMaxStockDays(Integer maxStockDays) {
		this.maxStockDays = maxStockDays;
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
	 * @return the changed
	 */
	public boolean isChanged() {
		return changed;
	}
	/**
	 * @param changed the changed to set
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	/**
	 * @return the offSale
	 */
	public boolean isOffSale() {
		return offSale;
	}
	/**
	 * @param offSale the offSale to set
	 */
	public void setOffSale(boolean offSale) {
		this.offSale = offSale;
	}
	/**
	 * @return the catId
	 */
	public String getCatId() {
		return catId;
	}
	/**
	 * @param catId the catId to set
	 */
	public void setCatId(String catId) {
		this.catId = catId;
	}
	/**
	 * @return the isCouponProduct
	 */
	public int getIsCouponProduct() {
		return isCouponProduct;
	}
	/**
	 * @param isCouponProduct the isCouponProduct to set
	 */
	public void setIsCouponProduct(int isCouponProduct) {
		this.isCouponProduct = isCouponProduct;
	}
	
}
