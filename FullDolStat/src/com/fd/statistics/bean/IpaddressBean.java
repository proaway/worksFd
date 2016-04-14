package com.fd.statistics.bean;

import java.util.Date;

/** IP地址库 */
public class IpaddressBean {
	/** ID主键 */
	private Long id;
	/** 起始IP地址 */
	private Long ipstart;
	/** 结束IP地址 */
	private Long ipend;
	/** 国家 */
	private String country;
	/** 是否爬虫：0-不是，1-是 */
	private Integer isspider;
	public static String spiderFlags[] = new String[] {
		"Baiduspider",
		"Googlebot",
		"Yahoo",
		"bingbot",
		"Sogou web spider",
		"Sosospider",
		"DotBot",
		"discobot",
		"Yandex",
		"msnbot",
		"Speedy",
		"spider"
};


	/** ID主键 */
	public void setId(Long id) {
		this.id = id;
	}

	/** ID主键 */
	public Long getId() {
		return this.id;
	}

	/** 起始IP地址 */
	public void setIpstart(Long ipstart) {
		this.ipstart = ipstart;
	}

	/** 起始IP地址 */
	public Long getIpstart() {
		return this.ipstart;
	}

	/** 结束IP地址 */
	public void setIpend(Long ipend) {
		this.ipend = ipend;
	}

	/** 结束IP地址 */
	public Long getIpend() {
		return this.ipend;
	}

	/** 国家 */
	public void setCountry(String country) {
		this.country = country;
	}

	/** 国家 */
	public String getCountry() {
		return this.country;
	}

	/** 是否爬虫：0-不是，1-是 */
	public void setIsspider(Integer isspider) {
		this.isspider = isspider;
	}

	/** 是否爬虫：0-不是，1-是 */
	public Integer getIsspider() {
		return this.isspider;
	}
}