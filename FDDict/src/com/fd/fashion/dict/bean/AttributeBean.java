package com.fd.fashion.dict.bean;

import java.io.Serializable;
import java.util.List;

import com.google.code.ssm.api.CacheKeyMethod;


/** 属性 */
public class AttributeBean implements Serializable {
	private static final long serialVersionUID = 7873417143289103094L;
	/** 属性ID */
	private Long attrId;
	/** 父属性ID */
	private Long parentId;
	/** 属性名 */
	private String value;
	/** 属性名中文 */
	private String valueCn;
	/** 属性类型，name 或 value */
	private String noteType;
	/** 下级节点属性 */
	private List<AttributeBean> nodes;

	/** 属性ID */
	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	/** 属性ID */
	public Long getAttrId() {
		return this.attrId;
	}

	/** 父属性ID */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/** 父属性ID */
	public Long getParentId() {
		return this.parentId;
	}

	/** 属性名 */
	public void setValue(String value) {
		this.value = value;
	}

	/** 属性名 */
	public String getValue() {
		return this.value;
	}

	/** 属性名中文 */
	public void setValueCn(String valueCn) {
		this.valueCn = valueCn;
	}

	/** 属性名中文 */
	public String getValueCn() {
		return this.valueCn;
	}

	/** 属性类型，name 或 value */
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

	/** 属性类型，name 或 value */
	public String getNoteType() {
		return this.noteType;
	}

	/**
	 * 下级节点属性
	 * @return the nodes
	 */
	public List<AttributeBean> getNodes() {
		return nodes;
	}

	/**
	 * 下级节点属性
	 * @param nodes the nodes to set
	 */
	public void setNodes(List<AttributeBean> nodes) {
		this.nodes = nodes;
	}
	
	/**
	 * 缓存键值
	 * 
	 * @return
	 */
	@CacheKeyMethod
	public String cacheKey() {
		return this.getClass().getName() + "." + String.valueOf(attrId);
	}
}