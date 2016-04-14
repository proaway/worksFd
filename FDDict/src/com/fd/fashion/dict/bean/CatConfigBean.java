package com.fd.fashion.dict.bean;

import java.io.Serializable;

import com.google.code.ssm.api.CacheKeyMethod;


/** 分类配置 */
public class CatConfigBean implements Serializable {
	private static final long serialVersionUID = 5049678173687205173L;
	/** 配置ID */
	private Long configId;
	/** 分类ID */
	private String catId;
	/** 属性ID */
	private Long attrId;
	/** 是否允许自定义title和图片，0-不允许，1-允许 */
	private Boolean allowCustom;
	/** 显示样式 */
	private String showStyle;
	/** 表单类型 */
	private String showType;

	/**
	 * @return the showStyle
	 */
	public String getShowStyle() {
		return showStyle;
	}

	/**
	 * @param showStyle the showStyle to set
	 */
	public void setShowStyle(String showStyle) {
		this.showStyle = showStyle;
	}

	/**
	 * @return the showType
	 */
	public String getShowType() {
		return showType;
	}

	/**
	 * @param showType the showType to set
	 */
	public void setShowType(String showType) {
		this.showType = showType;
	}

	/** 配置ID */
	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	/** 配置ID */
	public Long getConfigId() {
		return this.configId;
	}

	/** 分类ID */
	public void setCatId(String catId) {
		this.catId = catId;
	}

	/** 分类ID */
	public String getCatId() {
		return this.catId;
	}

	/** 属性ID */
	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	/** 属性ID */
	public Long getAttrId() {
		return this.attrId;
	}

	/** 是否允许自定义title和图片，0-不允许，1-允许 */
	public void setAllowCustom(Boolean allowCustom) {
		this.allowCustom = allowCustom;
	}

	/** 是否允许自定义title和图片，0-不允许，1-允许 */
	public Boolean getAllowCustom() {
		return this.allowCustom;
	}
	
	@CacheKeyMethod
	public String cacheKey() {
		return this.getClass().getName() + "." + configId + "." + catId+ "." + attrId;
	}
}