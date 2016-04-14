package com.fd.fashion.product.bean;

import java.io.Serializable;


/** 产品规格 */
public class ProductStandardBean implements Serializable {
	private static final long serialVersionUID = -1420778929412686050L;
	/** 规格ID */
	private Long standardId;
	/** 产品ID */
	private Long productId;
	/** 产品主配置ID */
	private Long mainConfigId;
	/** 产品从配置ID2 */
	private Long subConfigId;
	/** 库存 */
	private Integer stock;
	/** 规格SKU */
	private String sku;
	/** 产品零售价 */
	private Double price;
	/** 是否支持批发 */
	private Boolean wholesale;
	/** 是否支持折扣 */
	private Boolean discount;

	/** 规格ID */
	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	/** 规格ID */
	public Long getStandardId() {
		return this.standardId;
	}

	/** 产品ID */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/** 产品ID */
	public Long getProductId() {
		return this.productId;
	}

	/** 产品配置ID1 */
	public void setMainConfigId(Long mainConfigId) {
		this.mainConfigId = mainConfigId;
	}

	/** 产品配置ID1 */
	public Long getMainConfigId() {
		return this.mainConfigId;
	}

	/** 产品配置ID2 */
	public void setSubConfigId(Long subConfigId) {
		this.subConfigId = subConfigId;
	}

	/** 产品配置ID2 */
	public Long getSubConfigId() {
		return this.subConfigId;
	}

	/** 库存 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/** 库存 */
	public Integer getStock() {
		return this.stock;
	}

	/** 规格SKU */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/** 规格SKU */
	public String getSku() {
		return this.sku;
	}

	/** 产品零售价 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/** 产品零售价 */
	public Double getPrice() {
		return this.price;
	}

	/** 是否支持批发 */
	public Boolean getWholesale() {
		return wholesale;
	}

	/** 是否支持批发 */
	public void setWholesale(Boolean wholesale) {
		this.wholesale = wholesale;
	}
	
	/** 是否支持折扣 */
	public Boolean getDiscount() {
		return discount;
	}

	/** 是否支持折扣 */
	public void setDiscount(Boolean discount) {
		this.discount = discount;
	}
}