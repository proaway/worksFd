package com.fd.fashion.dict.vo;


public class CatAttrVo {
	/** 属性ID */
	private Long id;
	/** 属性名 */
	private String value;
	/** 属性名中文 */
	private String valueCn;
	/** 属性类型，name 或 value */
	private String noteType;
	/** 显示样式 */
	private String showStyle;
	/** 表单类型 */
	private String showType;
	
	/**
	 * @return the attrId
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param attrId the attrId to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the valueCn
	 */
	public String getValueCn() {
		return valueCn;
	}
	/**
	 * @param valueCn the valueCn to set
	 */
	public void setValueCn(String valueCn) {
		this.valueCn = valueCn;
	}
	/**
	 * @return the noteType
	 */
	public String getNoteType() {
		return noteType;
	}
	/**
	 * @param noteType the noteType to set
	 */
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
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
}
