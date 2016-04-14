package com.fd.fashion.seller.bean;


/** 尺码模版明细 */
public class SizeTemplateDetailBean {
	/** 明细ID */
	private Long detailId;
	/** 模版ID */
	private Long templateId;
	/** 列Id */
	private Long colId;
	/** 行ID */
	private Long rowId;
	/** 值 */
	private String attrValue;

	/** 明细ID */
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	/** 明细ID */
	public Long getDetailId() {
		return this.detailId;
	}

	/** 模版ID */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	/** 模版ID */
	public Long getTemplateId() {
		return this.templateId;
	}

	/** 列Id */
	public void setColId(Long colId) {
		this.colId = colId;
	}

	/** 列Id */
	public Long getColId() {
		return this.colId;
	}

	/** 行ID */
	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	/** 行ID */
	public Long getRowId() {
		return this.rowId;
	}

	/** 值 */
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	/** 值 */
	public String getAttrValue() {
		return this.attrValue;
	}
}