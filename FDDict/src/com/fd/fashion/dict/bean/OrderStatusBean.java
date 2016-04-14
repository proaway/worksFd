package com.fd.fashion.dict.bean;

import com.google.code.ssm.api.CacheKeyMethod;


/** 订单状态 */
public class OrderStatusBean {
	/** 状态代码 */
	private String statusCode;
	/** 订单状态说明描述英文 */
	private String statusEn;
	/** 订单状态说明描述中文 */
	private String statusCn;
	
	@CacheKeyMethod
	public String cachedKey() {
		return this.getClass().getName() + "." + statusCode;
	}

	/** 状态代码 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/** 状态代码 */
	public String getStatusCode() {
		return this.statusCode;
	}

	/** 订单状态说明描述英文 */
	public void setStatusEn(String statusEn) {
		this.statusEn = statusEn;
	}

	/** 订单状态说明描述英文 */
	public String getStatusEn() {
		return this.statusEn;
	}

	/** 订单状态说明描述中文 */
	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
	}

	/** 订单状态说明描述中文 */
	public String getStatusCn() {
		return this.statusCn;
	}
}