package com.fd.statistics.bean;

import java.util.Date;

/** 搜索统计 */
public class VisitSearchBean {
	/** ID主键 */
	private Long resultId;
	/** 访问ID */
	private Long visitId;
	/** 关键词（索引） */
	private String keyword;
	/** 分类 */
	private String category;
	/** 页大小 */
	private Integer pageSize;
	/** 页码 */
	private Integer pageIndex;
	/** 结果数 */
	private Integer resultCount;
	/** 搜索时间（索引） */
	private Date searchTime;
	/** 只搜批发 */
	private Integer onlyWholesale;
	/** 只显示折扣 */
	private Integer onlyDiscount;
	/** 查询参数 */
	private String querystr;

	/** ID主键 */
	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

	/** ID主键 */
	public Long getResultId() {
		return this.resultId;
	}

	/** 访问ID */
	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}

	/** 访问ID */
	public Long getVisitId() {
		return this.visitId;
	}

	/** 关键词（索引） */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/** 关键词（索引） */
	public String getKeyword() {
		return this.keyword;
	}

	/** 分类 */
	public void setCategory(String category) {
		this.category = category;
	}

	/** 分类 */
	public String getCategory() {
		return this.category;
	}

	/** 页大小 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/** 页大小 */
	public Integer getPageSize() {
		return this.pageSize;
	}

	/** 页码 */
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	/** 页码 */
	public Integer getPageIndex() {
		return this.pageIndex;
	}

	/** 结果数 */
	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}

	/** 结果数 */
	public Integer getResultCount() {
		return this.resultCount;
	}

	/** 搜索时间（索引） */
	public void setSearchTime(Date searchTime) {
		this.searchTime = searchTime;
	}

	/** 搜索时间（索引） */
	public Date getSearchTime() {
		return this.searchTime;
	}

	/** 只搜批发 */
	public void setOnlyWholesale(Integer onlyWholesale) {
		this.onlyWholesale = onlyWholesale;
	}

	/** 只搜批发 */
	public Integer getOnlyWholesale() {
		return this.onlyWholesale;
	}

	/** 只显示折扣 */
	public void setOnlyDiscount(Integer onlyDiscount) {
		this.onlyDiscount = onlyDiscount;
	}

	/** 只显示折扣 */
	public Integer getOnlyDiscount() {
		return this.onlyDiscount;
	}

	/** 查询参数 */
	public void setQuerystr(String querystr) {
		this.querystr = querystr;
	}

	/** 查询参数 */
	public String getQuerystr() {
		return this.querystr;
	}
}