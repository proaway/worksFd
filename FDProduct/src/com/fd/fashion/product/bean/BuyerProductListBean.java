package com.fd.fashion.product.bean;

import java.util.Date;

import com.fd.util.PageInfo;

public class BuyerProductListBean {

	/**  买家收藏产品ID，主键 */
	private long listId;
	
	/** 买家ID，外键  */
	private long buyerId;
	
	/** 产品ID  */
	private long productId;
	
	/** 创建时间  */
	private Date createTime;
	
	/** 收藏删除时间  */
	private Date delDate;
	
	/** 状态：0：正常；1：删除；  */
	private int state;
	
	private PageInfo pageInfo;
	
	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public long getListId() {
		return listId;
	}

	public void setListId(long listId) {
		this.listId = listId;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDelDate() {
		return delDate;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
}
