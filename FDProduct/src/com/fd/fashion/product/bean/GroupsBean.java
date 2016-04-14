package com.fd.fashion.product.bean;

/** 组合销售 */
public class GroupsBean {
	/** 活动ID */
	private String activityId;
	/** 活动产品1 */
	private Long groupProductId1;
	/** 活动产品2 */
	private Long groupProductId2;
	/** 活动产品3 */
	private Long groupProductId3;
	/** 活动产品4 */
	private Long groupProductId4;
	/** 购买一个组合销售产品折扣 */
	private Double groupDiscount1;
	/** 购买两个组合销售产品折扣 */
	private Double groupDiscount2;
	/** 购买三个组合销售产品折扣 */
	private Double groupDiscount3;
	/** 购买四个组合销售产品折扣 */
	private Double groupDiscount4;

	/** 活动ID */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/** 活动ID */
	public String getActivityId() {
		return this.activityId;
	}

	/** 活动产品1 */
	public void setGroupProductId1(Long groupProductId1) {
		this.groupProductId1 = groupProductId1;
	}

	/** 活动产品1 */
	public Long getGroupProductId1() {
		return this.groupProductId1;
	}

	/** 活动产品2 */
	public void setGroupProductId2(Long groupProductId2) {
		this.groupProductId2 = groupProductId2;
	}

	/** 活动产品2 */
	public Long getGroupProductId2() {
		return this.groupProductId2;
	}

	/** 活动产品3 */
	public void setGroupProductId3(Long groupProductId3) {
		this.groupProductId3 = groupProductId3;
	}

	/** 活动产品3 */
	public Long getGroupProductId3() {
		return this.groupProductId3;
	}

	/** 活动产品4 */
	public void setGroupProductId4(Long groupProductId4) {
		this.groupProductId4 = groupProductId4;
	}

	/** 活动产品4 */
	public Long getGroupProductId4() {
		return this.groupProductId4;
	}

	/** 购买一个组合销售产品折扣 */
	public void setGroupDiscount1(Double groupDiscount1) {
		this.groupDiscount1 = groupDiscount1;
	}

	/** 购买一个组合销售产品折扣 */
	public Double getGroupDiscount1() {
		return this.groupDiscount1;
	}

	/** 购买两个组合销售产品折扣 */
	public void setGroupDiscount2(Double groupDiscount2) {
		this.groupDiscount2 = groupDiscount2;
	}

	/** 购买两个组合销售产品折扣 */
	public Double getGroupDiscount2() {
		return this.groupDiscount2;
	}

	/** 购买三个组合销售产品折扣 */
	public void setGroupDiscount3(Double groupDiscount3) {
		this.groupDiscount3 = groupDiscount3;
	}

	/** 购买三个组合销售产品折扣 */
	public Double getGroupDiscount3() {
		return this.groupDiscount3;
	}

	/** 购买四个组合销售产品折扣 */
	public void setGroupDiscount4(Double groupDiscount4) {
		this.groupDiscount4 = groupDiscount4;
	}

	/** 购买四个组合销售产品折扣 */
	public Double getGroupDiscount4() {
		return this.groupDiscount4;
	}
}