package com.fd.fashion.cms.bean;

import java.util.Date;

import com.fd.util.PageInfo;

/** CMD模板 */
public class CmsTemplateBean {
	/** 模板id，主键 */
	private String templateId;
	/** 模板名 */
	private String templateName;
	/** 模板类型（1：首页，2：频道，3：活动，4：文章，5：邮件）） */
	private Integer templateType;
	/** 文件URL */
	private String fileUrl;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private String createUser;
	/** 修改人 */
	private String operateUser;
	/** 修改时间 */
	private Date operateDate;
	/** 语言ID */
	private String languageId;
	/** 模板状态：0删除，1启用，2禁用 */
	private Integer status;
	/** 备注 */
	private String memo;
	/** 栏目数 */
	private Integer blockNum;
	/** 模版创建起始时间 */
	private Date createTimeStart;
	/** 模版创建结束 时间 */
	private Date createTimeEnd;
	/** 分页 **/
	private PageInfo pageInfo;

	/**
	 * @return the createTimeStart
	 */
	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	/**
	 * @param createTimeStart the createTimeStart to set
	 */
	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	/**
	 * @return the createTimeEnd
	 */
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	/**
	 * @param createTimeEnd the createTimeEnd to set
	 */
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	/** 模板id，主键 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	/** 模板id，主键 */
	public String getTemplateId() {
		return this.templateId;
	}

	/** 模板名 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/** 模板名 */
	public String getTemplateName() {
		return this.templateName;
	}

	/** 模板类型（1：首页，2：频道，3：活动，4：文章，5：邮件）） */
	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	/** 模板类型（1：首页，2：频道，3：活动，4：文章，5：邮件）） */
	public Integer getTemplateType() {
		return this.templateType;
	}

	/** 文件URL */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	/** 文件URL */
	public String getFileUrl() {
		return this.fileUrl;
	}

	/** 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 创建时间 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/** 创建人 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/** 创建人 */
	public String getCreateUser() {
		return this.createUser;
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
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	/** 修改时间 */
	public Date getOperateDate() {
		return this.operateDate;
	}

	/** 语言ID */
	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	/** 语言ID */
	public String getLanguageId() {
		return this.languageId;
	}

	/** 模板状态：1启用，2禁用 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 模板状态：1启用，2禁用 */
	public Integer getStatus() {
		return this.status;
	}

	/** 备注 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/** 备注 */
	public String getMemo() {
		return this.memo;
	}

	/** 栏目数 */
	public void setBlockNum(Integer blockNum) {
		this.blockNum = blockNum;
	}

	/** 栏目数 */
	public Integer getBlockNum() {
		return this.blockNum;
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