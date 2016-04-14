package com.fd.fashion.order.bean;

public class ShippingCompanyBean {

	//货运方式lId
	private Long shippingDetailId;
	//货运方式名称
	private String shippingCompany;

	public Long getShippingDetailId() {
		return shippingDetailId;
	}

	public void setShippingDetailId(Long shippingDetailId) {
		this.shippingDetailId = shippingDetailId;
	}

	public String getShippingCompany() {
		return shippingCompany;
	}

	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}
	
	
}
