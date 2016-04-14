package com.fd.fashion.seller.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/** 菜单 */
public class ModulesBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2766258963404269319L;
	/** 菜单ID */
	private Long modulesId;
	/** 菜单名称 */
	private String modulesName;
	/** 上级菜单ID */
	private Long parentId;
	/** 链接地址 */
	private String url;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creator;
	/** 排序值 */
	private Integer sort;
	/** 子菜单*/
	private List<ModulesBean> subModules;

	/** 菜单ID */
	public void setModulesId(Long modulesId) {
		this.modulesId = modulesId;
	}

	/** 菜单ID */
	public Long getModulesId() {
		return this.modulesId;
	}

	/** 菜单名称 */
	public void setModulesName(String modulesName) {
		this.modulesName = modulesName;
	}

	/** 菜单名称 */
	public String getModulesName() {
		return this.modulesName;
	}

	/** 上级菜单ID */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/** 上级菜单ID */
	public Long getParentId() {
		return this.parentId;
	}

	/** 链接地址 */
	public void setUrl(String url) {
		this.url = url;
	}

	/** 链接地址 */
	public String getUrl() {
		return this.url;
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
	public void setCreator(Long creator) {
		this.creator = creator;
	}

	/** 创建人 */
	public Long getCreator() {
		return this.creator;
	}

	/** 排序值 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/** 排序值 */
	public Integer getSort() {
		return this.sort;
	}

	/**
	 * @return the subModules
	 */
	public List<ModulesBean> getSubModules() {
		return subModules;
	}

	/**
	 * @param subModules the subModules to set
	 */
	public void setSubModules(List<ModulesBean> subModules) {
		this.subModules = subModules;
	}
}