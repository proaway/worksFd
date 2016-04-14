package com.fd.fashion.dict.bean;

import java.io.Serializable;

import com.google.code.ssm.api.CacheKeyMethod;


/** 地区 */
public class RegionBean implements Serializable {
	private static final long serialVersionUID = 4111445065599201986L;
	/** 地区ID */
	private String regionId;
	/** 上级地区 */
	private Long parentId;
	/** 地区级别：1国家，2省 3市 */
	private Integer regionLevel;
	/** 地区名 */
	private String regionName;
	/** 地区中文名 */
	private String regionNameCn;
	/** 缩写 */
	private String shortName;
	/** 大洲 1 非洲，2亚洲 3大洋洲 4 欧洲 5北美洲 6南美洲 */
	private Integer continent;
	/** 电话号码 */
	private String telCountryCode;
	/** 国家区域 1-9 */
	private Integer area;
	/**时区代码*/
	private Float timeZone;
	
	public Float getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Float timeZone) {
		this.timeZone = timeZone;
	}

	@CacheKeyMethod
	public String cachedKey() {
		return this.getClass().getName() + "." + regionId;
	}

	/** 地区ID */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	/** 地区ID */
	public String getRegionId() {
		return this.regionId;
	}

	/** 上级地区 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/** 上级地区 */
	public Long getParentId() {
		return this.parentId;
	}

	/** 地区级别：1国家，2省 3市 */
	public void setRegionLevel(Integer regionLevel) {
		this.regionLevel = regionLevel;
	}

	/** 地区级别：1国家，2省 3市 */
	public Integer getRegionLevel() {
		return this.regionLevel;
	}

	/** 地区名 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	/** 地区名 */
	public String getRegionName() {
		return this.regionName;
	}

	/** 地区中文名 */
	public void setRegionNameCn(String regionNameCn) {
		this.regionNameCn = regionNameCn;
	}

	/** 地区中文名 */
	public String getRegionNameCn() {
		return this.regionNameCn;
	}

	/** 缩写 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/** 缩写 */
	public String getShortName() {
		return this.shortName;
	}

	/** 大洲 1 非洲，2亚洲 3大洋洲 4 欧洲 5北美洲 6南美洲 */
	public void setContinent(Integer continent) {
		this.continent = continent;
	}

	/** 大洲 1 非洲，2亚洲 3大洋洲 4 欧洲 5北美洲 6南美洲 */
	public Integer getContinent() {
		return this.continent;
	}

	/** 电话号码 */
	public void setTelCountryCode(String telCountryCode) {
		this.telCountryCode = telCountryCode;
	}

	/** 电话号码 */
	public String getTelCountryCode() {
		return this.telCountryCode;
	}

	/** 国家区域 1-9 */
	public void setArea(Integer area) {
		this.area = area;
	}

	/** 国家区域 1-9 */
	public Integer getArea() {
		return this.area;
	}
}