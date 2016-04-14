/**
 * ImageVo.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.product.vo;

import com.fd.fashion.product.bean.ImageBean;
import com.fd.util.PageInfo;

/**
 * @CreateDate:  2013-3-25 下午9:21:18 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ImageVo {
	private ImageBean imageBean;
	private String orderByStatus;
	private PageInfo pageInfo;
	/**
	 * @return the orderByStatus
	 */
	public String getOrderByStatus() {
		return orderByStatus;
	}
	/**
	 * @param orderByStatus the orderByStatus to set
	 */
	public void setOrderByStatus(String orderByStatus) {
		this.orderByStatus = orderByStatus;
	}
	/**
	 * @return the imageBean
	 */
	public ImageBean getImageBean() {
		return imageBean;
	}
	/**
	 * @param imageBean the imageBean to set
	 */
	public void setImageBean(ImageBean imageBean) {
		this.imageBean = imageBean;
	}
	/**
	 * @return the pageInfo
	 */
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	/**
	 * @param pageInfo the pageInfo to set
	 */
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
}
