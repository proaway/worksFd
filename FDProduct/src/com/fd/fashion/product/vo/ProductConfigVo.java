package com.fd.fashion.product.vo;

import java.util.List;

import com.fd.fashion.dict.bean.AttributeBean;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.bean.ProductConfigBean;

/**
 * @CreateDate: 2013-3-16 下午04:40:25
 * @author Longli
 * @Description: 封装产品配置属性显示层数据，包括commission、价格、属性对象、下级属性对象等
 * 
 */
public class ProductConfigVo {
	/** 配置属性bean */
	private ProductConfigBean productConfigBean;
	/** 配置属性内容对象 */
	private AttributeBean attribute;
	/** 对应二级配置属性 */
	private List<ProductConfigVo> subConfigs;
	/**规格图片(首图) zl*/
	private String imageUrl;
	/** 规格图片 */
	private List<ImageBean> images;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the productConfigBean
	 */
	public ProductConfigBean getProductConfigBean() {
		return productConfigBean;
	}

	/**
	 * @param productConfigBean the productConfigBean to set
	 */
	public void setProductConfigBean(ProductConfigBean productConfigBean) {
		this.productConfigBean = productConfigBean;
	}

	/** 配置属性内容对象 */
	public AttributeBean getAttribute() {
		return attribute;
	}

	/** 配置属性内容对象 */
	public void setAttribute(AttributeBean attribute) {
		this.attribute = attribute;
	}

	/**
	 * @return the productConfigs
	 */
	public List<ProductConfigVo> getSubConfigs() {
		return subConfigs;
	}

	/**
	 * @param productConfigs the productConfigs to set
	 */
	public void setSubConfigs(List<ProductConfigVo> subConfigs) {
		this.subConfigs = subConfigs;
	}

	/**
	 * @return the images
	 */
	public List<ImageBean> getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(List<ImageBean> images) {
		this.images = images;
	}
}
