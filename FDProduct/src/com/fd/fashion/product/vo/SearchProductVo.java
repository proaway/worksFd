/**
 * SearchProductVo.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.product.vo;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.util.PageInfo;
import com.google.code.ssm.api.CacheKeyMethod;

/**
 * @CreateDate:  2013-4-9 下午1:58:12 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class SearchProductVo implements Serializable {
	private static final long serialVersionUID = -166976812129690119L;
	//搜索产品列表
	private List<ProductVo> productList;
	//搜索产品分类列表
	private List<CustomCategoryBean> categoryList;
	//搜索关键字
	private String key;
	//普通属性列表
	private List<Long> attrList;
	//配置属性列表
	private List<Long> configsList;
	//搜索分类ID
	private String catId;
	//分页
	private PageInfo pageInfo;
	//排序规则: 0:价格排序  1:评分
	private String orderType;
	//价格起始值
	private Double priceStart;
	//价格结束值
	private Double priceEnd;
	/** 产品状态 */
	private Integer productStatus;
	/** 产品状上传时间开始*/
	private Date createDateStart;
	/** 产品状上传时间结束 */
	private Date createDateEnd;
	// 佣金比例
	private double commission = 1.05;
	/** 多个状态查询条件 */
	private List<Integer> productStatuses;
	
	@CacheKeyMethod
	public String cachedKey() {
		StringBuffer sb = new StringBuffer(this.getClass().getName());
		try {
			sb.append(".").append(URLEncoder.encode(key,"utf-8"));
		} catch (UnsupportedEncodingException e) {
		}
		sb.append(".").append(catId);
		sb.append(".").append(priceStart);
		sb.append(".").append(priceEnd);
		sb.append(".").append(orderType);
		if (attrList != null && attrList.size()>0) {
			for (Long id : attrList) {
				sb.append(".att").append(id);
			}
		}
		if (configsList != null && configsList.size()>0) {
			for (Long id : configsList) {
				sb.append(".conf").append(id);
			}
		}
		return sb.toString();
	}
	
	/**增加状态查询条件*/
	public void addProductStatus(int productStatus) {
		if (productStatuses == null) {
			productStatuses = new ArrayList<Integer>();
		}
		productStatuses.add(productStatus);
	}
	
	public Double getPriceStart() {
		return priceStart;
	}
	public void setPriceStart(Double priceStart) {
		this.priceStart = priceStart;
	}
	public Double getPriceEnd() {
		return priceEnd;
	}
	public void setPriceEnd(Double priceEnd) {
		this.priceEnd = priceEnd;
	}
	/**
	 * @return the categoryList
	 */
	public List<CustomCategoryBean> getCategoryList() {
		return categoryList;
	}
	/**
	 * @param categoryList the categoryList to set
	 */
	public void setCategoryList(List<CustomCategoryBean> categoryList) {
		this.categoryList = categoryList;
	}
	/**
	 * @return the productList
	 */
	public List<ProductVo> getProductList() {
		return productList;
	}
	/**
	 * @param productList the productList to set
	 */
	public void setProductList(List<ProductVo> productList) {
		this.productList = productList;
	}
	/**
	 * @return the attrList
	 */
	public List<Long> getAttrList() {
		return attrList;
	}
	/**
	 * @param attrList the attrList to set
	 */
	public void setAttrList(List<Long> attrList) {
		this.attrList = attrList;
	}
	/**
	 * @return the configsList
	 */
	public List<Long> getConfigsList() {
		return configsList;
	}
	/**
	 * @param configsList the configsList to set
	 */
	public void setConfigsList(List<Long> configsList) {
		this.configsList = configsList;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the catId
	 */
	public String getCatId() {
		return catId;
	}
	/**
	 * @param catId the catId to set
	 */
	public void setCatId(String catId) {
		this.catId = catId;
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
	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * @return the commission
	 */
	public double getCommission() {
		return commission;
	}

	/**
	 * @param commission the commission to set
	 */
	public void setCommission(double commission) {
		this.commission = commission;
	}

	/**
	 * @return the productStatus
	 */
	public Integer getProductStatus() {
		return productStatus;
	}

	/**
	 * @param productStatus the productStatus to set
	 */
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	/**
	 * @return the createDateStart
	 */
	public Date getCreateDateStart() {
		return createDateStart;
	}

	/**
	 * @param createDateStart the createDateStart to set
	 */
	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	/**
	 * @return the createDateEnd
	 */
	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	/**
	 * @param createDateEnd the createDateEnd to set
	 */
	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	/**
	 * @return the productStatuses
	 */
	public List<Integer> getProductStatuses() {
		return productStatuses;
	}

	/**
	 * @param productStatuses the productStatuses to set
	 */
	public void setProductStatuses(List<Integer> productStatuses) {
		this.productStatuses = productStatuses;
	}
}
