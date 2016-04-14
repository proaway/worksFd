package com.fd.fashion.dict.bean;

import java.io.Serializable;

import com.google.code.ssm.api.CacheKeyMethod;


/** 包装单位 */
public class PackageUnitBean implements Serializable {
	private static final long serialVersionUID = -2602790290663443591L;
	/** 单位ID */
	private Long unitId;
	/** 名称 */
	private String name;
	/** 中文名称 */
	private String nameCn;

	/** 单位ID */
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	/** 单位ID */
	public Long getUnitId() {
		return this.unitId;
	}

	/** 名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 名称 */
	public String getName() {
		return this.name;
	}
	
	@CacheKeyMethod
	public String cacheKey() {
		return this.getClass().getName() + "." + String.valueOf(unitId);
	}

	/**
	 * @return the nameCn
	 */
	public String getNameCn() {
		return nameCn;
	}

	/**
	 * @param nameCn the nameCn to set
	 */
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
}