package com.fd.payment.bean;


/** 西联付款设置 */
public class WesternSettingBean extends AbstractPaymentSetting {
	/** ID主键 */
	private Integer id;
	/** 名 */
	private String firstName;
	/** 姓 */
	private String lastName;
	/** 国家 */
	private String country;
	/** 城市 */
	private String city;
	/** 地址 */
	private String address;
	/** 电话 */
	private String tel;

	/** ID主键 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** ID主键 */
	public Integer getId() {
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

	/** 国家 */
	public void setCountry(String country) {
		this.country = country;
	}

	/** 国家 */
	public String getCountry() {
		return this.country;
	}

	/** 城市 */
	public void setCity(String city) {
		this.city = city;
	}

	/** 城市 */
	public String getCity() {
		return this.city;
	}

	/** 地址 */
	public void setAddress(String address) {
		this.address = address;
	}

	/** 地址 */
	public String getAddress() {
		return this.address;
	}

	/** 电话 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/** 电话 */
	public String getTel() {
		return this.tel;
	}
}