package com.fd.fashion.order.bean;

/** 订单地址 */
public class OrderAddressBean {
	/** 订单地址ID主键 */
	private Long orderAddressId;
	/** 买家内部ID */
	private Long buyerId;
	/** 买家名 */
	private String firstName;
	/** 买家姓 */
	private String lastName;
	/** 地址第一行 */
	private String addressline1;
	/** 地址第二行 */
	private String addressline2;
	/** 城市 */
	private String city;
	/** 国家 */
	private String country;
	/** 省，区 */
	private String province;
	/** 邮编 */
	private String postalCode;
	/** 电话 */
	private String phone;

	/** 订单地址ID主键 */
	public void setOrderAddressId(Long orderAddressId) {
		this.orderAddressId = orderAddressId;
	}

	/** 订单地址ID主键 */
	public Long getOrderAddressId() {
		return this.orderAddressId;
	}

	/** 买家内部ID */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/** 买家内部ID */
	public Long getBuyerId() {
		return this.buyerId;
	}

	/** 买家名 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** 买家名 */
	public String getFirstName() {
		return this.firstName;
	}

	/** 买家姓 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** 买家姓 */
	public String getLastName() {
		return this.lastName;
	}

	/** 地址第一行 */
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	/** 地址第一行 */
	public String getAddressline1() {
		return this.addressline1;
	}

	/** 地址第二行 */
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	/** 地址第二行 */
	public String getAddressline2() {
		return this.addressline2;
	}

	/** 城市 */
	public void setCity(String city) {
		this.city = city;
	}

	/** 城市 */
	public String getCity() {
		return this.city;
	}

	/** 国家 */
	public void setCountry(String country) {
		this.country = country;
	}

	/** 国家 */
	public String getCountry() {
		return this.country;
	}

	/** 省，区 */
	public void setProvince(String province) {
		this.province = province;
	}

	/** 省，区 */
	public String getProvince() {
		return this.province;
	}

	/** 邮编 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/** 邮编 */
	public String getPostalCode() {
		return this.postalCode;
	}

	/** 电话 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/** 电话 */
	public String getPhone() {
		return this.phone;
	}
}