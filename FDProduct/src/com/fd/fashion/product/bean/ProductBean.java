package com.fd.fashion.product.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 产品 */
public class ProductBean implements Serializable {
	private static final long serialVersionUID = 611433802139666248L;
	/** 产品ID */
	private Long productId;
	/** 供应商ID */
	private Long sellerId;
	/** 分类ID */
	private String catId;
	/** 自定义分类ID */
	private String customCatId;
	/** 产品名称 */
	private String productName;
	/** 图片url */
	private String imageUrl;
	/** 产品编号，不同属性产品编号相同，sku不同 */
	private String productCode;
	/** sku号，唯一编号 */
	private String sku;
	/** 数量 */
	private Integer quantity;
	/** 单位 */
	private String packing;
	/** 重 */
	private Double weight;
	/** 宽 */
	private Integer width;
	/** 长 */
	private Integer length;
	/** 高 */
	private Integer height;
	/** 材料 */
	private String material;
	/** 源产地 */
	private String origin;
	/** 简短描述 */
	private String description;
	/** 详细描述(文件路径) */
	private String specifications;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creator;
	/** 更新时间 */
	private Date updateTime;
	/** 操作人 */
	private Long operator;
	/** 产品状态 */
	private Integer productStatus;
	/** 产品有效性：1-有效，0-无效 */
	private Integer vaild;
	/** 条形码 */
	private String barcode;
	/** 备货时间 */
	private Integer stockDays;
	/** 关键词1 */
	private String keyword;
/*	*//** 关键词2 *//*
	private String keyword2;
	*//** 关键词3 *//*
	private String keyword3;
	*//** 关键词4 *//*
	private String keyword4;*/
	/** 品牌  */
	private String brand;
	/** 包裹信息 */
	private String packageInformation;
	/** 物流信息ID */
	private Long shippingTemplateId;
	/** 尺码表模版ID */
	private Long sizeTemplateId;
	/** 有效期天数，0-无有效期 */
	private Integer expiredDay;
	/** 库存 */
	private Integer stock;
	/** 是否打包 */
	private Integer ispackage;
	/** 产品编辑时间 */
	private Date editTime;
	/** 忽略库存销售 */
	private Boolean saleIgnoreStock;

	/** 产品ID */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/** 产品ID */
	public Long getProductId() {
		return this.productId;
	}

	/** 供应商ID */
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	/** 供应商ID */
	public Long getSellerId() {
		return this.sellerId;
	}

	/** 分类ID */
	public void setCatId(String catId) {
		this.catId = catId;
	}

	/** 分类ID */
	public String getCatId() {
		return this.catId;
	}

	/** 自定义分类ID */
	public void setCustomCatId(String customCatId) {
		this.customCatId = customCatId;
	}

	/** 自定义分类ID */
	public String getCustomCatId() {
		return this.customCatId;
	}

	/** 产品名称 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/** 产品名称 */
	public String getProductName() {
		return this.productName;
	}

	/** 图片url */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/** 图片url */
	public String getImageUrl() {
		return this.imageUrl;
	}

	/** 产品编号，不同属性产品编号相同，sku不同 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/** 产品编号，不同属性产品编号相同，sku不同 */
	public String getProductCode() {
		return this.productCode;
	}

	/** sku号，唯一编号 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/** sku号，唯一编号 */
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

	/** 单位 */
	public void setPacking(String packing) {
		this.packing = packing;
	}

	/** 单位 */
	public String getPacking() {
		return this.packing;
	}

	/** 重 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/** 重 */
	public Double getWeight() {
		return this.weight;
	}

	/** 宽 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/** 宽 */
	public Integer getWidth() {
		return this.width;
	}

	/** 长 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/** 长 */
	public Integer getLength() {
		return this.length;
	}

	/** 高 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/** 高 */
	public Integer getHeight() {
		return this.height;
	}

	/** 材料 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/** 材料 */
	public String getMaterial() {
		return this.material;
	}

	/** 源产地 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/** 源产地 */
	public String getOrigin() {
		return this.origin;
	}

	/** 简短描述 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** 简短描述 */
	public String getDescription() {
		return this.description;
	}

	/** 详细描述(文件路径) */
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	/** 详细描述(文件路径) */
	public String getSpecifications() {
		return this.specifications;
	}

	/** 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 创建时间 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/** 创建人 */
	public void setCreator(Long creator) {
		this.creator = creator;
	}

	/** 创建人 */
	public Long getCreator() {
		return this.creator;
	}

	/** 更新时间 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/** 更新时间 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/** 操作人 */
	public void setOperator(Long operator) {
		this.operator = operator;
	}

	/** 操作人 */
	public Long getOperator() {
		return this.operator;
	}

	/** 产品状态 */
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	/** 产品状态 */
	public Integer getProductStatus() {
		return this.productStatus;
	}

	/** 产品有效性：1-有效，0-无效 */
	public void setVaild(Integer vaild) {
		this.vaild = vaild;
	}

	/** 产品有效性：1-有效，0-无效 */
	public Integer getVaild() {
		return this.vaild;
	}

	/** 条形码 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/** 条形码 */
	public String getBarcode() {
		return this.barcode;
	}




	/** 包裹信息 */
	public void setPackageInformation(String packageInformation) {
		this.packageInformation = packageInformation;
	}

	/** 包裹信息 */
	public String getPackageInformation() {
		return this.packageInformation;
	}



	/** 尺码表模版ID */
	public void setSizeTemplateId(Long sizeTemplateId) {
		this.sizeTemplateId = sizeTemplateId;
	}

	/** 尺码表模版ID */
	public Long getSizeTemplateId() {
		return this.sizeTemplateId;
	}
	
	public String dateToString(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * @param shippingTemplateId the shippingTemplateId to set
	 */
	public void setShippingTemplateId(Long shippingTemplateId) {
		this.shippingTemplateId = shippingTemplateId;
	}

	/**
	 * @return the shippingTemplateId
	 */
	public Long getShippingTemplateId() {
		return shippingTemplateId;
	}


	/**
	 * @return the expiredDay
	 */
	public Integer getExpiredDay() {
		return expiredDay;
	}

	/**
	 * @param expiredDay the expiredDay to set
	 */
	public void setExpiredDay(Integer expiredDay) {
		this.expiredDay = expiredDay;
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the stockDate
	 */
	public Integer getStockDays() {
		return stockDays;
	}

	/**
	 * @param stockDate the stockDate to set
	 */
	public void setStockDays(Integer stockDays) {
		this.stockDays = stockDays;
	}

	/**
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * @return the ispackage
	 */
	public Integer getIspackage() {
		return ispackage;
	}

	/**
	 * @param ispackage the ispackage to set
	 */
	public void setIspackage(Integer ispackage) {
		this.ispackage = ispackage;
	}

	/**
	 * @return the editTime
	 */
	public Date getEditTime() {
		return editTime;
	}

	/**
	 * @param editTime the editTime to set
	 */
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	/**
	 * @return the saleIgnoreStock
	 */
	public Boolean getSaleIgnoreStock() {
		return saleIgnoreStock;
	}

	/**
	 * @param saleIgnoreStock the saleIgnoreStock to set
	 */
	public void setSaleIgnoreStock(Boolean saleIgnoreStock) {
		this.saleIgnoreStock = saleIgnoreStock;
	}
}