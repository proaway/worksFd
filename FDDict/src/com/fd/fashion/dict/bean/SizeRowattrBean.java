package com.fd.fashion.dict.bean;


/** 尺码行属性（行头） */
public class SizeRowattrBean {
	/** 行ID */
	private Long rowId;
	/** 尺码分类ID */
	private Long sizecatId;
	/** 属性ID */
	private Long attrId;
	/** 显示排序序号 */
	private Integer indexId;
	/** 所属列ID */
	private Long colId;

	/** 行ID */
	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	/** 行ID */
	public Long getRowId() {
		return this.rowId;
	}

	/** 尺码分类ID */
	public void setSizecatId(Long sizecatId) {
		this.sizecatId = sizecatId;
	}

	/** 尺码分类ID */
	public Long getSizecatId() {
		return this.sizecatId;
	}

	/** 属性ID */
	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	/** 属性ID */
	public Long getAttrId() {
		return this.attrId;
	}

	/** 显示排序序号 */
	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	/** 显示排序序号 */
	public Integer getIndexId() {
		return this.indexId;
	}

	/** 所属列ID */
	public void setColId(Long colId) {
		this.colId = colId;
	}

	/** 所属列ID */
	public Long getColId() {
		return this.colId;
	}
}