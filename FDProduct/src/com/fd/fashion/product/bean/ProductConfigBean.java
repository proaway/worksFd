package com.fd.fashion.product.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fd.fashion.dict.bean.AttributeBean;

/** 产品配置 */
public class ProductConfigBean implements Serializable {
	private static final long serialVersionUID = 2126682039890004155L;
	/** 产品配置ID */
	private Long productConfigId;
	/** 产品Id */
	private Long productId;
	/** 属性名ID */
	private Long titleAttrId;
	/** 属性值ID */
	private Long valueAttrId;
	/** 属性自定义名称 */
	private String attrName;
	/** 属性图片 */
	private List<ProductConfImgBean> images;

	/** 产品配置ID */
	public void setProductConfigId(Long productConfigId) {
		this.productConfigId = productConfigId;
	}

	/** 产品配置ID */
	public Long getProductConfigId() {
		return this.productConfigId;
	}

	/** 产品Id */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/** 产品Id */
	public Long getProductId() {
		return this.productId;
	}

	/** 属性名ID */
	public void setTitleAttrId(Long titleAttrId) {
		this.titleAttrId = titleAttrId;
	}

	/** 属性名ID */
	public Long getTitleAttrId() {
		return this.titleAttrId;
	}

	/** 属性值ID */
	public void setValueAttrId(Long valueAttrId) {
		this.valueAttrId = valueAttrId;
	}

	/** 属性值ID */
	public Long getValueAttrId() {
		return this.valueAttrId;
	}

	/** 产品名称 */
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	/** 产品名称 */
	public String getAttrName() {
		return this.attrName;
	}

	/**
	 * @return the images
	 */
	public List<ProductConfImgBean> getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(List<ProductConfImgBean> images) {
		this.images = images;
	}
}