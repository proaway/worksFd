package com.fd.fashion.product.vo;

import java.util.List;

/**
 * @CreateDate: 2013-4-25 下午01:19:37
 * @author Longli
 * @Description: 搜索属性聚合
 * 
 */
public class SearchAttrVo {
	/** 标题Id  */
	private Long titleId;
	
	/** 属性ID */
	private Long attrId;
	
	/** 属性值 */
	private String attrValue;
	
	/** 产品数量 */
	private Integer prodCount;
	
	/** 下级属性 */
	private List<SearchAttrVo> nodes;
	
	private boolean checked;

	/**
	 * @return the titleId
	 */
	public Long getTitleId() {
		return titleId;
	}

	/**
	 * @param titleId the titleId to set
	 */
	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}

	/**
	 * @return the attrId
	 */
	public Long getAttrId() {
		return attrId;
	}

	/**
	 * @param attrId the attrId to set
	 */
	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	/**
	 * @return the attrValue
	 */
	public String getAttrValue() {
		return attrValue;
	}

	/**
	 * @param attrValue the attrValue to set
	 */
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	/**
	 * @return the prodCount
	 */
	public Integer getProdCount() {
		return prodCount;
	}

	/**
	 * @param prodCount the prodCount to set
	 */
	public void setProdCount(Integer prodCount) {
		this.prodCount = prodCount;
	}

	/**
	 * @return the nodes
	 */
	public List<SearchAttrVo> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(List<SearchAttrVo> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
