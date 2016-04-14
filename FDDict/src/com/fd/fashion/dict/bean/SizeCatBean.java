package com.fd.fashion.dict.bean;


/** 尺码分类 */
public class SizeCatBean {
	/** 分类Id */
	private Long sizecatId;
	/** 名称 */
	private String name;
	/** 中文名称 */
	private String nameCn;

	/** 分类Id */
	public void setSizecatId(Long sizecatId) {
		this.sizecatId = sizecatId;
	}

	/** 分类Id */
	public Long getSizecatId() {
		return this.sizecatId;
	}

	/** 名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 名称 */
	public String getName() {
		return this.name;
	}

	/** 中文名称 */
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	/** 中文名称 */
	public String getNameCn() {
		return this.nameCn;
	}
}