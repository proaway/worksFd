package com.fd.payment.bean;

/** 西联付款 */
public class WesternUnionBean {
	/** ID主键 */
	private Long id;
	/** 名 */
	private String firstName;
	/** 姓 */
	private String lastName;
	/** 国家编码 */
	private String country;
	/** 电话号码 */
	private String phoneNo;
	/** MTCN码 */
	private String mtcnCode;

	/** ID主键 */
	public void setId(Long id) {
		this.id = id;
	}

	/** ID主键 */
	public Long getId() {
		return this.id;
	}

	/** 名 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** 名 */
	public String getFirstName() {
		return this.firstName;
	}

	/** 姓 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** 姓 */
	public String getLastName() {
		return this.lastName;
	}

	/** 国家编码 */
	public void setCountry(String country) {
		this.country = country;
	}

	/** 国家编码 */
	public String getCountry() {
		return this.country;
	}

	/** 电话号码 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/** 电话号码 */
	public String getPhoneNo() {
		return this.phoneNo;
	}

	/** MTCN码 */
	public void setMtcnCode(String mtcnCode) {
		this.mtcnCode = mtcnCode;
	}

	/** MTCN码 */
	public String getMtcnCode() {
		return this.mtcnCode;
	}
}