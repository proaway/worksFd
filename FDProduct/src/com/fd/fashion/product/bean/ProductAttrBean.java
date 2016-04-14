package com.fd.fashion.product.bean;

import java.io.Serializable;


/** 产品属性 */
public class ProductAttrBean implements Serializable {
	private static final long serialVersionUID = 7476535752203329064L;
	/** 产品属性ID */
	private Long productAttrId;
	/** 产品Id */
	private Long productId;
	/** 属性名ID */
	private Long titleAttrId;
	/** 属性值ID */
	private Long valueAttrId;
	/** 属性值 */
	private String value;

	/** 产品属性ID */
	public void setProductAttrId(Long productAttrId) {
		this.productAttrId = productAttrId;
	}

	/** 产品属性ID */
	public Long getProductAttrId() {
		return this.productAttrId;
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

	/** 属性值 */
	public void setValue(String value) {
		this.value = value;
	}

	/** 属性值 */
	public String getValue() {
		return this.value;
	}
}