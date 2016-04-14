package com.fd.fashion.product.vo;

import java.io.Serializable;

import com.fd.fashion.product.bean.ProductBean;

/**
 * MIC所有平台封装commission属性的VO
 * @since: Jan 02, 2009
 * @author: Alan
 * @company: shopmadeinchina.com
 */
public class CommissionVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4003369124626148878L;
	private ProductBean product;
	
	
	public CommissionVO(ProductBean product) {
		this.product = product;
	}
	
	/**
	 * @return the product
	 */
	public ProductBean getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductBean product) {
		this.product = product;
	}
}
