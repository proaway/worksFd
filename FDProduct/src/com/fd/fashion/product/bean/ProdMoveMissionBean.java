package com.fd.fashion.product.bean;

import java.util.Date;

/** 产品搬家任务申请 */
public class ProdMoveMissionBean {
	/** 任务Id */
	private Long missionId;
	/** 网站 */
	private String website;
	/** 店铺地址 */
	private String storeUrl;
	/** 店铺唯一编码 */
	private String storeCode;
	/** 校验码 */
	private String verifyCode;
	/** 校验产品地址 */
	private String verifyUrl;
	/** 校验状态：0-未校验，1-已校验 */
	private Integer verifyStatus;
	/** 校验时间 */
	private Date verifyTime;
	/** 产品数量 */
	private Integer productCount;
	/** 同步数量 */
	private Integer syncCount;
	/** 同步产品状态：0-下架，1-上架，2-草稿 */
	private Integer productStatus;
	/** 同步频次，单位：天 */
	private Integer syncFreq;
	/** 同步状态：0-停止，1-开启 */
	private Integer syncStatus;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 任务状态 */
	private Integer status;

	/** 任务Id */
	public void setMissionId(Long missionId) {
		this.missionId = missionId;
	}

	/** 任务Id */
	public Long getMissionId() {
		return this.missionId;
	}

	/** 网站 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/** 网站 */
	public String getWebsite() {
		return this.website;
	}

	/** 店铺地址 */
	public void setStoreUrl(String storeUrl) {
		this.storeUrl = storeUrl;
	}

	/** 店铺地址 */
	public String getStoreUrl() {
		return this.storeUrl;
	}

	/** 校验码 */
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	/** 校验码 */
	public String getVerifyCode() {
		return this.verifyCode;
	}

	/** 校验产品地址 */
	public void setVerifyUrl(String verifyUrl) {
		this.verifyUrl = verifyUrl;
	}

	/** 校验产品地址 */
	public String getVerifyUrl() {
		return this.verifyUrl;
	}

	/** 校验状态：0-未校验，1-已校验 */
	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	/** 校验状态：0-未校验，1-已校验 */
	public Integer getVerifyStatus() {
		return this.verifyStatus;
	}

	/** 校验时间 */
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	/** 校验时间 */
	public Date getVerifyTime() {
		return this.verifyTime;
	}

	/** 产品数量 */
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	/** 产品数量 */
	public Integer getProductCount() {
		return this.productCount;
	}

	/** 同步数量 */
	public void setSyncCount(Integer syncCount) {
		this.syncCount = syncCount;
	}

	/** 同步数量 */
	public Integer getSyncCount() {
		return this.syncCount;
	}

	/** 同步产品状态：0-下架，1-上架，2-草稿 */
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	/** 同步产品状态：0-下架，1-上架，2-草稿 */
	public Integer getProductStatus() {
		return this.productStatus;
	}

	/** 同步频次，单位：天 */
	public void setSyncFreq(Integer syncFreq) {
		this.syncFreq = syncFreq;
	}

	/** 同步频次，单位：天 */
	public Integer getSyncFreq() {
		return this.syncFreq;
	}

	/** 同步状态：0-停止，1-开启 */
	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}

	/** 同步状态：0-停止，1-开启 */
	public Integer getSyncStatus() {
		return this.syncStatus;
	}

	/** 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 创建时间 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/** 更新时间 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/** 更新时间 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/** 任务状态 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 任务状态 */
	public Integer getStatus() {
		return this.status;
	}

	/** 店铺唯一编码 */
	public String getStoreCode() {
		return storeCode;
	}

	/** 店铺唯一编码 */
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
}