package com.fd.fashion.dict.bean;


/** 尺码列属性（列头） */
public class SizeColattrBean {
	/** 列ID */
	private Long colId;
	/** 尺码分类ID */
	private Long sizecatId;
	/** 属性ID */
	private Long attrId;
	/** 排序序号 */
	private Integer indexId;

	/** 列ID */
	public void setColId(Long colId) {
		this.colId = colId;
	}

	/** 列ID */
	public Long getColId() {
		return this.colId;
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

	/** 排序序号 */
	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	/** 排序序号 */
	public Integer getIndexId() {
		return this.indexId;
	}
}