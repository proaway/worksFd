package com.fd.fashion.dict.bean;


/** 尺码属性（头） */
public class SizeAttrBean {
	/** 属性ID */
	private Long attrId;
	/** 属性名 */
	private String name;
	/** 属性中文名 */
	private String nameCn;

	/** 属性ID */
	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	/** 属性ID */
	public Long getAttrId() {
		return this.attrId;
	}

	/** 属性名 */
	public void setName(String name) {
		this.name = name;
	}

	/** 属性名 */
	public String getName() {
		return this.name;
	}

	/** 属性中文名 */
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	/** 属性中文名 */
	public String getNameCn() {
		return this.nameCn;
	}
}