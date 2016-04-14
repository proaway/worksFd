package com.fd.fashion.dict.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.google.code.ssm.api.CacheKeyMethod;

/** 分类 */
public class CategoryBean implements Serializable {
	private static final long serialVersionUID = -5350111339945549149L;
	/** 分类ID */
	private String catId;
	/** 父分类ID */
	private String parentId;

	/** 分类名称 */
	private String catName;
	/** 分类中文名称 */
	private String catNameCn;
	/** 分类级别 */
	private Integer catLevel;
	/** 是否有子节点 */
	private Integer isleaf;
	/** SEO词汇 */
	private String seoKeywords;
	/** 页面TITLE值 */
	private String title;
	/** 页面DESCRIPTION值 */
	private String descripTion;
	/** 页面KEYWORDS值 */
	private String keywords;
	/** 页面H1标题 */
	private String h1Title;
	/** 页面H1标题内容 */
	private String h1Content;
	/** 创建时间 */
	private Long creator;
	/** 创建人 */
	private Date updateTime;
	/** 更新时间 */
	private Long operator;
	/** 更新人 */
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

	/** 是否有子节点 */
	public void setIsleaf(Integer isleaf) {
		this.isleaf = isleaf;
	}

	/** 是否有子节点 */
	public Integer getIsleaf() {
		return this.isleaf;
	}

	/** SEO词汇 */
	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	/** SEO词汇 */
	public String getSeoKeywords() {
		return this.seoKeywords;
	}

	/** 页面TITLE值 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** 页面TITLE值 */
	public String getTitle() {
		return this.title;
	}

	/** 页面DESCRIPTION值 */
	public void setDescripTion(String descripTion) {
		this.descripTion = descripTion;
	}

	/** 页面DESCRIPTION值 */
	public String getDescripTion() {
		return this.descripTion;
	}

	/** 页面KEYWORDS值 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/** 页面KEYWORDS值 */
	public String getKeywords() {
		return this.keywords;
	}

	/** 页面H1标题 */
	public void setH1Title(String h1Title) {
		this.h1Title = h1Title;
	}

	/** 页面H1标题 */
	public String getH1Title() {
		return this.h1Title;
	}

	/** 页面H1标题内容 */
	public void setH1Content(String h1Content) {
		this.h1Content = h1Content;
	}

	/** 页面H1标题内容 */
	public String getH1Content() {
		return this.h1Content;
	}

	/** 创建时间 */
	public void setCreator(Long creator) {
		this.creator = creator;
	}

	/** 创建时间 */
	public Long getCreator() {
		return this.creator;
	}

	/** 创建人 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/** 创建人 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/** 更新时间 */
	public void setOperator(Long operator) {
		this.operator = operator;
	}

	/** 更新时间 */
	public Long getOperator() {
		return this.operator;
	}

	/** 更新人 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 更新人 */
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