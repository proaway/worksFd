package com.fd.fashion.product.vo;

/**
 * @CreateDate: 2013-4-28 下午03:52:07
 * @author Longli
 * @Description: 封装产品不同状态的产品数量
 * 
 */
public class ProductCountVo {
	/* 产品状态 */
	private int productStatus;
	/** 产品数量 */
	private int productCount;
	/**
	 * @return the productStatus
	 */
	public int getProductStatus() {
		return productStatus;
	}
	/**
	 * @param productStatus the productStatus to set
	 */
	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}
	/**
	 * @return the productCount
	 */
	public int getProductCount() {
		return productCount;
	}
	/**
	 * @param productCount the productCount to set
	 */
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
}
