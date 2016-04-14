package com.fd.fashion.dict.vo;


public class CatConfigVo extends CatAttrVo {
	/** 是否允许自定义title和图片，0-不允许，1-允许 */
	private Boolean allowCustom;
	
	/**
	 * @return the allowCustomTitle
	 */
	public Boolean getAllowCustom() {
		return allowCustom;
	}
	/**
	 * @param allowCustomTitle the allowCustomTitle to set
	 */
	public void setAllowCustom(Boolean allowCustom) {
		this.allowCustom = allowCustom;
	}
}
