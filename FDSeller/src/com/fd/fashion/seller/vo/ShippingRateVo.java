package com.fd.fashion.seller.vo;

import java.io.Serializable;

public class ShippingRateVo implements Serializable {
	/**
	 * 运费的计算的vo
	 */
	private static final long serialVersionUID = 1032902824081597678L;
	// 长
	private double length;
	// 宽
	private double width;
	// 高
	private double height;
	// 重
	private double heavy;

	// 数量
	private int amount;

	// 卖家运费信息模板ID，主键
	private Long shippingTemplateId;
	
	// 地区ID
	private String country;
	
	// 产品ID
	private Long productId;

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the length
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * @return the heavy
	 */
	public double getHeavy() {
		return heavy;
	}

	/**
	 * @param heavy
	 *            the heavy to set
	 */
	public void setHeavy(double heavy) {
		this.heavy = heavy;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}



	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @param shippingTemplateId the shippingTemplateId to set
	 */
	public void setShippingTemplateId(Long shippingTemplateId) {
		this.shippingTemplateId = shippingTemplateId;
	}

	/**
	 * @return the shippingTemplateId
	 */
	public Long getShippingTemplateId() {
		return shippingTemplateId;
	}

	/**
	 * @param regionId the regionId to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the regionId
	 */
	public String getCountry() {
		return country;
	}
}
