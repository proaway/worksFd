package com.fd.fashion.product.bean;

import java.util.Date;

/** 自定义图片分类 */
public class ImageCatBean {
	/** 产品自定分类ID */
	private Long catId;
	/** 产品自定分类名字 */
	private String catName;
	/** 创建时间 */
	private Date createTime;

	/** 产品自定分类ID */
	public void setCatId(Long catId) {
		this.catId = catId;
	}

	/** 产品自定分类ID */
	public Long getCatId() {
		return this.catId;
	}

	/** 产品自定分类名字 */
	public void setCatName(String catName) {
		this.catName = catName;
	}

	/** 产品自定分类名字 */
	public String getCatName() {
		return this.catName;
	}

	/** 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 创建时间 */
	public Date getCreateTime() {
		return this.createTime;
	}
}