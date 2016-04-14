package com.fd.fashion.cms.bean;

/** 栏目管理 */
public class CmsBlockBean {
	/** 栏目id，主键 */
	private Long blockId;
	/** 频道id， 外键 */
	private String channelId;
	/** 栏目名字 */
	private String blockName;
	/** 栏目位置 */
	private Integer blockLocation;
	/** 栏目类型：0，产品；1，广告；2，文章； */
	private Integer blockType;
	/** 根据栏目类型添加的信息ID，当栏目类型为0时，此为产品表ID;为1时，为固定广告表ID；为2时，为文章表的ID; */
	private Long infoId;
	/** 外链地址 */
	private String linkUrl;
	/** 图片URL */
	private String imgUrl;
	/** 产品标题 */
	private String title;
	/** 产品摘要 */
	private String summary;
	/** 图片ALT */
	private String imgAlt;
	/** 图片规格 */
	private String imgSpec;
	/** 产品SKU */
	private String sku;
	/** title保留长度 */
	private Integer titleLength;
	/** summary保留长度 */
	private Integer summaryLength;

	/** 栏目id，主键 */
	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	/** 栏目id，主键 */
	public Long getBlockId() {
		return this.blockId;
	}

	/** 频道id， 外键 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/** 频道id， 外键 */
	public String getChannelId() {
		return this.channelId;
	}

	/** 栏目名字 */
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	/** 栏目名字 */
	public String getBlockName() {
		return this.blockName;
	}

	/** 栏目位置 */
	public void setBlockLocation(Integer blockLocation) {
		this.blockLocation = blockLocation;
	}

	/** 栏目位置 */
	public Integer getBlockLocation() {
		return this.blockLocation;
	}

	/** 栏目类型：0，产品；1，广告；2，文章； */
	public void setBlockType(Integer blockType) {
		this.blockType = blockType;
	}

	/** 栏目类型：0，产品；1，广告；2，文章； */
	public Integer getBlockType() {
		return this.blockType;
	}

	/** 根据栏目类型添加的信息ID，当栏目类型为0时，此为产品表ID;为1时，为固定广告表ID；为2时，为文章表的ID; */
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	/** 根据栏目类型添加的信息ID，当栏目类型为0时，此为产品表ID;为1时，为固定广告表ID；为2时，为文章表的ID; */
	public Long getInfoId() {
		return this.infoId;
	}

	/** 外链地址 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	/** 外链地址 */
	public String getLinkUrl() {
		return this.linkUrl;
	}

	/** 图片URL */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/** 图片URL */
	public String getImgUrl() {
		return this.imgUrl;
	}

	/** 产品标题 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** 产品标题 */
	public String getTitle() {
		return this.title;
	}

	/** 产品摘要 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/** 产品摘要 */
	public String getSummary() {
		return this.summary;
	}

	/** 图片ALT */
	public void setImgAlt(String imgAlt) {
		this.imgAlt = imgAlt;
	}

	/** 图片ALT */
	public String getImgAlt() {
		return this.imgAlt;
	}

	/** 图片规格 */
	public void setImgSpec(String imgSpec) {
		this.imgSpec = imgSpec;
	}

	/** 图片规格 */
	public String getImgSpec() {
		return this.imgSpec;
	}

	/** 产品SKU */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/** 产品SKU */
	public String getSku() {
		return this.sku;
	}

	/**
	 * @return the titleLength
	 */
	public Integer getTitleLength() {
		return titleLength;
	}

	/**
	 * @param titleLength the titleLength to set
	 */
	public void setTitleLength(Integer titleLength) {
		this.titleLength = titleLength;
	}

	/**
	 * @return the summaryLength
	 */
	public Integer getSummaryLength() {
		return summaryLength;
	}

	/**
	 * @param summaryLength the summaryLength to set
	 */
	public void setSummaryLength(Integer summaryLength) {
		this.summaryLength = summaryLength;
	}
}