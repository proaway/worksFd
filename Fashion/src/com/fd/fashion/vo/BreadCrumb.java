package com.fd.fashion.vo;

/**
 * @CreateDate: 2013-4-24 下午03:20:40
 * @author Longli
 * @Description: 封装导航面包屑VO
 * 
 */
public class BreadCrumb {
	/** url */
	private String url;
	/** 名称 */
	private String name;

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
