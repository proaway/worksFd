package com.fd.fashion.seller.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.google.code.ssm.api.CacheKeyMethod;

/** 自定义分类 */
public class CustomCategoryBean implements Serializable {
	private static final long serialVersionUID = -2847448250853820123L;
	/** 分类ID */
	private String catId;
	/** 父分类ID */
	private String parentId;
	/** 分类编码 */
	private String catCode;
	/** 分类名称 */
	private String catName;
	/** 分类中文名称 */
	private String catNameCn;
	/** 分类级别 */
	private Integer catLevel;
	/** 是否叶节点 */
	private Integer isleaf;
	/** 创建人 */
	private String creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人 */
	private String operator;
	/** 创建时间 */
	private Date createTime;
	/** 数量聚合*/
	private Integer productCount;
	
	@CacheKeyMethod
	public String cacheKey() {
		String key = this.getClass().getName() + "." + parentId + "." + catId;
		try {
			if (StringUtils.isNotEmpty(catName)) {
				key = key + "." + URLEncoder.encode(catName, "utf-8");
			}
			if (StringUtils.isNotEmpty(catNameCn)) {
				key = key + "." + URLEncoder.encode(catNameCn, "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
		}
		key += "." + catLevel;
		return key;
	}

	/** 分类ID */
	public void setCatId(String catId) {
		this.catId = catId;
	}

	/** 分类ID */
	public String getCatId() {
		return this.catId;
	}

	/** 父分类ID */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/** 父分类ID */
	public String getParentId() {
		return this.parentId;
	}

	/** 分类编码 */
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	/** 分类编码 */
	public String getCatCode() {
		return this.catCode;
	}

	/** 分类名称 */
	public void setCatName(String catName) {
		this.catName = catName;
	}

	/** 分类名称 */
	public String getCatName() {
		return this.catName;
	}

	/** 分类中文名称 */
	public void setCatNameCn(String catNameCn) {
		this.catNameCn = catNameCn;
	}

	/** 分类中文名称 */
	public String getCatNameCn() {
		return this.catNameCn;
	}

	/** 分类级别 */
	public void setCatLevel(Integer catLevel) {
		this.catLevel = catLevel;
	}

	/** 分类级别 */
	public Integer getCatLevel() {
		return this.catLevel;
	}

	/** 是否叶节点 */
	public void setIsleaf(Integer isleaf) {
		this.isleaf = isleaf;
	}

	/** 是否叶节点 */
	public Integer getIsleaf() {
		return this.isleaf;
	}

	/** 创建人 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/** 创建人 */
	public String getCreator() {
		return this.creator;
	}

	/** 更新时间 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/** 更新时间 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/** 更新人 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/** 更新人 */
	public String getOperator() {
		return this.operator;
	}

	/** 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 创建时间 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * @return the productCount
	 */
	public Integer getProductCount() {
		return productCount;
	}

	/**
	 * @param productCount the productCount to set
	 */
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
}