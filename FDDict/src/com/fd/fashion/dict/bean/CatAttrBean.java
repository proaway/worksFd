package com.fd.fashion.dict.bean;

import java.io.Serializable;

import com.google.code.ssm.api.CacheKeyMethod;


/** 分类属性 */
public class CatAttrBean implements Serializable {
	private static final long serialVersionUID = -6654718798994503205L;
	/** 分类ID */
	private String catId;
	/** 属性ID */
	private Long attrId;
	/** 显示样式 */
	private String showStyle;
	/** 表单类型 */
	private String showType;
	
	@CacheKeyMethod
	public String cacheKey() {
		return this.getClass().getName() + "." + catId + "." + attrId;
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

	/** 显示样式 */
	public void setShowStyle(String showStyle) {
		this.showStyle = showStyle;
	}

	/** 显示样式 */
	public String getShowStyle() {
		return this.showStyle;
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
}