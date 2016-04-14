package com.fd.statistics.bean;

/** 搜索结果点击统计 */
public class SearchClickBean {
	/** 搜索结果ID（外键） */
	private Long resultId;
	/** 产品ID */
	private Long productId;
	/** 产品位置 */
	private int position;

	/** 搜索结果ID（外键） */
	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

	/** 搜索结果ID（外键） */
	public Long getResultId() {
		return this.resultId;
	}

	/** 产品位置 */
	public void setPosition(int position) {
		this.position = position;
	}

	/** 产品位置 */
	public int getPosition() {
		return this.position;
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
}