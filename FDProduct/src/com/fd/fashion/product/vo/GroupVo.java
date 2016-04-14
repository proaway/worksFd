package com.fd.fashion.product.vo;

import java.io.Serializable;
import java.util.List;

import com.fd.fashion.product.bean.GroupsBean;
import com.fd.fashion.product.bean.ProductBean;

/**
 * @CreateDate: 2013-3-20 下午01:51:34
 * @author Longli
 * @Description: 产品组合销售的表示层VO
 * 
 */
public class GroupVo implements Serializable {
	private static final long serialVersionUID = 329428545297479916L;
	/** 分组对象 */
	private GroupsBean group;
	/** 分组主产品对象 */
	private ProductBean product;
	/** 分组组合产品对象列表 */
	private List<ProductVo> groupProducts;
	/**
	 * @return the group
	 */
	public GroupsBean getGroup() {
		return group;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(GroupsBean group) {
		this.group = group;
	}
	/**
	 * @return the product
	 */
	public ProductBean getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductBean product) {
		this.product = product;
	}
	/**
	 * @return the groupProducts
	 */
	public List<ProductVo> getGroupProducts() {
		return groupProducts;
	}
	/**
	 * @param groupProducts the groupProducts to set
	 */
	public void setGroupProducts(List<ProductVo> groupProducts) {
		this.groupProducts = groupProducts;
	}
}
