package com.fd.fashion.seller.bean;

import java.util.Date;

/** 尺码模版 */
public class SizeTemplateBean {
	/** 模版ID */
	private Long templateId;
	/** 模版分类ID */
	private Long sizecatId;
	/** 模版名称 */
	private String templateName;
	/** 图片ID */
	private Long imageId;
	/** 最后编辑时间 */
	private Date modifyTime;
	/** 编辑人 */
	private String operator;

	/** 模版ID */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	/** 模版ID */
	public Long getTemplateId() {
		return this.templateId;
	}

	/** 模版分类ID */
	public void setSizecatId(Long sizecatId) {
		this.sizecatId = sizecatId;
	}

	/** 模版分类ID */
	public Long getSizecatId() {
		return this.sizecatId;
	}

	/** 模版名称 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/** 模版名称 */
	public String getTemplateName() {
		return this.templateName;
	}

	/** 图片ID */
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	/** 图片ID */
	public Long getImageId() {
		return this.imageId;
	}

	/** 最后编辑时间 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/** 最后编辑时间 */
	public Date getModifyTime() {
		return this.modifyTime;
	}

	/** 编辑人 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/** 编辑人 */
	public String getOperator() {
		return this.operator;
	}
}