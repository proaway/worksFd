package com.fd.statistics.bean;

import java.util.Date;

/** google 统计代码链接访问统计 */
public class VisitAdwordBean {
	/** 点击编号 */
	private Long clickId;
	/** 访问编号 */
	private Long visitId;
	/** 广告系列来源 */
	private String utmSource;
	/** 广告系列媒介 */
	private String utmMedium;
	/** 广告系列字词 */
	private String utmTerm;
	/** 广告系列内容 */
	private String utmContent;
	/** 广告系列名称 */
	private String utmCampaign;
	/** 着陆页 */
	private String landingPage;
	/** 来源页 */
	private String referer;
	/** 来源关键词 */
	private String srckey;
	/** 点击时间 */
	private Date clickTime;

	/** 点击编号 */
	public void setClickId(Long clickId) {
		this.clickId = clickId;
	}

	/** 点击编号 */
	public Long getClickId() {
		return this.clickId;
	}

	/** 访问编号 */
	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}

	/** 访问编号 */
	public Long getVisitId() {
		return this.visitId;
	}

	/** 广告系列来源 */
	public void setUtmSource(String utmSource) {
		this.utmSource = utmSource;
	}

	/** 广告系列来源 */
	public String getUtmSource() {
		return this.utmSource;
	}

	/** 广告系列媒介 */
	public void setUtmMedium(String utmMedium) {
		this.utmMedium = utmMedium;
	}

	/** 广告系列媒介 */
	public String getUtmMedium() {
		return this.utmMedium;
	}

	/** 广告系列字词 */
	public void setUtmTerm(String utmTerm) {
		this.utmTerm = utmTerm;
	}

	/** 广告系列字词 */
	public String getUtmTerm() {
		return this.utmTerm;
	}

	/** 广告系列内容 */
	public void setUtmContent(String utmContent) {
		this.utmContent = utmContent;
	}

	/** 广告系列内容 */
	public String getUtmContent() {
		return this.utmContent;
	}

	/** 广告系列名称 */
	public void setUtmCampaign(String utmCampaign) {
		this.utmCampaign = utmCampaign;
	}

	/** 广告系列名称 */
	public String getUtmCampaign() {
		return this.utmCampaign;
	}

	/** 着陆页 */
	public void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}

	/** 着陆页 */
	public String getLandingPage() {
		return this.landingPage;
	}

	/** 来源页 */
	public void setReferer(String referer) {
		this.referer = referer;
	}

	/** 来源页 */
	public String getReferer() {
		return this.referer;
	}

	/** 来源关键词 */
	public void setSrckey(String srckey) {
		this.srckey = srckey;
	}

	/** 来源关键词 */
	public String getSrckey() {
		return this.srckey;
	}

	/** 点击时间 */
	public void setClickTime(Date clickTime) {
		this.clickTime = clickTime;
	}

	/** 点击时间 */
	public Date getClickTime() {
		return this.clickTime;
	}
}