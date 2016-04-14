/**
 * BuyerAddressBookBean.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.buyer.bean;

/**
 * @CreateDate:  2013-4-2 下午5:09:05 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class BuyerAddressBookBean {
		// 地址本ID
		private Long addressId;

		// 买家Id
		private Long buyerId;

		// First Name
		private String firstName;

		// Last Name
		private String lastName;

		// 地址第一行
		private String addressLine1;

		// 地址第二行
		private String addressLine2;

		// 城市
		private String city;

		// 国家ID
		private String country;

		// 省、区
		private String province;

		// 邮编
		private String postalCode;

		// 电话
		private String phone;
		
		// 是否缺省地址：0-否，1-是
		private Integer isDefault;



		/**
		 * @return the firstName
		 */
		public String getFirstName() {
			return firstName;
		}

		/**
		 * @param firstName the firstName to set
		 */
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		/**
		 * @return the lastname
		 */
		public String getLastName() {
			return lastName;
		}

		/**
		 * @param lastname the lastname to set
		 */
		public void setLastName(String lastname) {
			this.lastName = lastname;
		}

		/**
		 * @return the addressLine1
		 */
		public String getAddressLine1() {
			return addressLine1;
		}

		/**
		 * @param addressLine1 the addressLine1 to set
		 */
		public void setAddressLine1(String addressLine1) {
			this.addressLine1 = addressLine1;
		}

		/**
		 * @return the addressLine2
		 */
		public String getAddressLine2() {
			return addressLine2;
		}

		/**
		 * @param addressLine2 the addressLine2 to set
		 */
		public void setAddressLine2(String addressLine2) {
			this.addressLine2 = addressLine2;
		}

		/**
		 * @return the city
		 */
		public String getCity() {
			return city;
		}

		/**
		 * @param city the city to set
		 */
		public void setCity(String city) {
			this.city = city;
		}

		/**
		 * @return the country
		 */
		public String getCountry() {
			return country;
		}

		/**
		 * @param country the country to set
		 */
		public void setCountry(String country) {
			this.country = country;
		}

		/**
		 * @return the province
		 */
		public String getProvince() {
			return province;
		}

		/**
		 * @param province the province to set
		 */
		public void setProvince(String province) {
			this.province = province;
		}

		/**
		 * @return the postalCode
		 */
		public String getPostalCode() {
			return postalCode;
		}

		/**
		 * @param postalCode the postalCode to set
		 */
		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		/**
		 * @return the phone
		 */
		public String getPhone() {
			return phone;
		}

		/**
		 * @param phone the phone to set
		 */
		public void setPhone(String phone) {
			this.phone = phone;
		}

		/**
		 * @return the isDefault
		 */
		public Integer getIsDefault() {
			return isDefault;
		}

		/**
		 * @param isDefault the isDefault to set
		 */
		public void setIsDefault(Integer isDefault) {
			this.isDefault = isDefault;
		}

		/**
		 * @return the addressId
		 */
		public Long getAddressId() {
			return addressId;
		}

		/**
		 * @param addressId the addressId to set
		 */
		public void setAddressId(Long addressId) {
			this.addressId = addressId;
		}

		/**
		 * @return the buyerId
		 */
		public Long getBuyerId() {
			return buyerId;
		}

		/**
		 * @param buyerId the buyerId to set
		 */
		public void setBuyerId(Long buyerId) {
			this.buyerId = buyerId;
		}
}
