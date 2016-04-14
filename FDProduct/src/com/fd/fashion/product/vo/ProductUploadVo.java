/**
 * ProductUploadVo.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.product.vo;

import java.util.List;

import com.fd.fashion.product.bean.GiftsBean;
import com.fd.fashion.product.bean.PriceBean;
import com.fd.fashion.product.bean.ProductAttrBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.bean.ProductConfImgBean;
import com.fd.fashion.product.bean.ProductConfigBean;
import com.fd.fashion.product.bean.ProductImageBean;
import com.fd.fashion.product.bean.ProductStandardBean;

/**
 * @CreateDate: 2013-3-26 下午8:40:16
 * @author: ZhouRongyu
 * @Description: TODO
 * 
 * @version: V1.0
 */
public class ProductUploadVo {
	// 产品基本信息
	private ProductBean productBean;
	// 产品属性
	private List<ProductAttrBean> attrs;
	// 产品规格
	private List<ProductStandardBean> standards;
	// 产品规格主属性
	private List<ProductConfigBean> mainConfigs;
	// 产品规格属性
	private List<ProductConfigBean> subConfigs;
	// 产品图片
	private List<ProductImageBean> images;
	// 产品价格
	private PriceBean price;

	/**
	 * @return the productBean
	 */
	public ProductBean getProductBean() {
		return productBean;
	}

	/**
	 * @param productBean
	 *            the productBean to set
	 */
	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}

	/**
	 * @return the productAttr
	 */
	public List<ProductAttrBean> getAttrs() {
		return attrs;
	}

	/**
	 * @param productAttr
	 *            the productAttr to set
	 */
	public void setAttrs(List<ProductAttrBean> productAttrs) {
		this.attrs = productAttrs;
	}

	/**
	 * @return the productStandard
	 */
	public List<ProductStandardBean> getStandards() {
		return standards;
	}

	/**
	 * @param productStandard
	 *            the productStandard to set
	 */
	public void setStandards(List<ProductStandardBean> productStandards) {
		this.standards = productStandards;
	}

	/**
	 * @return the productImage
	 */
	public List<ProductImageBean> getImages() {
		return images;
	}

	/**
	 * @param productImage
	 *            the productImage to set
	 */
	public void setImages(List<ProductImageBean> productImage) {
		this.images = productImage;
	}

	/**
	 * @return the price
	 */
	public PriceBean getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(PriceBean price) {
		this.price = price;
	}

	/**
	 * @return the mainConfigs
	 */
	public List<ProductConfigBean> getMainConfigs() {
		return mainConfigs;
	}

	/**
	 * @param mainConfigs the mainConfigs to set
	 */
	public void setMainConfigs(List<ProductConfigBean> mainConfigs) {
		this.mainConfigs = mainConfigs;
	}

	/**
	 * @return the subConfigs
	 */
	public List<ProductConfigBean> getSubConfigs() {
		return subConfigs;
	}

	/**
	 * @param subConfigs the subConfigs to set
	 */
	public void setSubConfigs(List<ProductConfigBean> subConfigs) {
		this.subConfigs = subConfigs;
	}
}
