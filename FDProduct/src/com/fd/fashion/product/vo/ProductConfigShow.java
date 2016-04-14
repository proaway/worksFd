package com.fd.fashion.product.vo;

import java.util.List;

import com.fd.fashion.dict.bean.AttributeBean;
import com.fd.fashion.product.bean.ProductConfigBean;

/**
 * @CreateDate: 2013-3-14 上午09:53:00
 * @author Longli
 * @Description: 包装的产品属性显示类
 * 
 */
public class ProductConfigShow {
	/** 标题属性 */
	private AttributeBean titleAttr;
	
	/** 产品属性明细 */
	private List<ProductConfigVo> configs;
	
	/** 显示类型 */
	private String showType;

	/**
	 * @return the titleAttr
	 */
	public AttributeBean getTitleAttr() {
		return titleAttr;
	}

	/**
	 * @param titleAttr the titleAttr to set
	 */
	public void setTitleAttr(AttributeBean titleAttr) {
		this.titleAttr = titleAttr;
	}

	/**
	 * @return the configs
	 */
	public List<ProductConfigVo> getConfigs() {
		return configs;
	}

	/**
	 * @param configs the configs to set
	 */
	public void setConfigs(List<ProductConfigVo> configs) {
		this.configs = configs;
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
