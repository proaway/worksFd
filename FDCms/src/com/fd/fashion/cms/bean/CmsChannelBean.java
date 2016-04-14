package com.fd.fashion.cms.bean;

import java.util.Date;
import java.util.List;

/** 频道管理 */
public class CmsChannelBean {
	/** 频道编号,按需求规则生成编号 */
	private String channelId;
	/** 频道名字 */
	private String channelName;
	/** 模板ID号 */
	private String templateId;
	/** 模板类型（1：首页，2：频道，3：活动，4：文章，5：邮件）） */
	private Integer templateType;
	/** 模板名字 */
	private String templateName;
	/** 频道URL */
	private String channelUrl;
	/** 栏目数 */
	private Integer blockNum;
	/** 创建时间 */
	private Date createTime;
	/** 发布时间 */
	private Date pubDate;
	/** 创建人 */
	private String createUser;
	/** 更新类型1：手动，2：自动 */
	private Integer updataType;
	/** 状态：1：启用，2：禁用 */
	private Integer status;
	/** 修改人 */
	private String operateUser;
	/** 修改时间 */
	private Date operateTime;
	/** 描述 */
	private String description;
	/** 关键词 */
	private String keywords;
	/** 网页TITLE */
	private String titleInfo;
	/** 频道栏目 */
	private List<CmsBlockBean> blocks;
	
	private Date createDateStart;
	private Date createDateEnd;
	private Date pubDateStart;
	private Date pubDateEnd;
	

	/** 频道编号,按需求规则生成编号 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/** 频道编号,按需求规则生成编号 */
	public String getChannelId() {
		return this.channelId;
	}

	/** 频道名字 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/** 频道名字 */
	public String getChannelName() {
		return this.channelName;
	}

	/** 模板ID号 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	/** 模板ID号 */
	public String getTemplateId() {
		return this.templateId;
	}

	/** 模板类型（1：首页，2：频道，3：活动，4：文章，5：邮件）） */
	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	/** 模板类型（1：首页，2：频道，3：活动，4：文章，5：邮件）） */
	public Integer getTemplateType() {
		return this.templateType;
	}

	/** 模板名字 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/** 模板名字 */
	public String getTemplateName() {
		return this.templateName;
	}

	/** 频道URL */
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	/** 频道URL */
	public String getChannelUrl() {
		return this.channelUrl;
	}

	/** 栏目数 */
	public void setBlockNum(Integer blockNum) {
		this.blockNum = blockNum;
	}

	/** 栏目数 */
	public Integer getBlockNum() {
		return this.blockNum;
	}

	/** 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 创建时间 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/** 发布时间 */
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	/** 发布时间 */
	public Date getPubDate() {
		return this.pubDate;
	}

	/** 创建人 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/** 创建人 */
	public String getCreateUser() {
		return this.createUser;
	}

	/** 更新类型1：手动，2：自动 */
	public void setUpdataType(Integer updataType) {
		this.updataType = updataType;
	}

	/** 更新类型1：手动，2：自动 */
	public Integer getUpdataType() {
		return this.updataType;
	}

	/** 状态：1：启用，2：禁用 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 状态：1：启用，2：禁用 */
	public Integer getStatus() {
		return this.status;
	}

	/** 修改人 */
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	/** 修改人 */
	public String getOperateUser() {
		return this.operateUser;
	}

	/** 修改时间 */
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	/** 修改时间 */
	public Date getOperateTime() {
		return this.operateTime;
	}

	/** 描述 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** 描述 */
	public String getDescription() {
		return this.description;
	}

	/** 关键词 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/** 关键词 */
	public String getKeywords() {
		return this.keywords;
	}

	/** 网页TITLE */
	public void setTitleInfo(String titleInfo) {
		this.titleInfo = titleInfo;
	}

	/** 网页TITLE */
	public String getTitleInfo() {
		return this.titleInfo;
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
	 * @return the pubDateStart
	 */
	public Date getPubDateStart() {
		return pubDateStart;
	}

	/**
	 * @param pubDateStart the pubDateStart to set
	 */
	public void setPubDateStart(Date pubDateStart) {
		this.pubDateStart = pubDateStart;
	}

	/**
	 * @return the pubDateEnd
	 */
	public Date getPubDateEnd() {
		return pubDateEnd;
	}

	/**
	 * @param pubDateEnd the pubDateEnd to set
	 */
	public void setPubDateEnd(Date pubDateEnd) {
		this.pubDateEnd = pubDateEnd;
	}

	/**
	 * @return the blocks
	 */
	public List<CmsBlockBean> getBlocks() {
		return blocks;
	}

	/**
	 * @param blocks the blocks to set
	 */
	public void setBlocks(List<CmsBlockBean> blocks) {
		this.blocks = blocks;
	}
}