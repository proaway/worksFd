package com.fd.util;

import java.io.Serializable;

import com.google.code.ssm.api.CacheKeyMethod;

/**
 * 分页Bean,提供页面显示范围的扩展属性
 * 
 * @author kongqz
 * @date 2008-07-11
 */
public class PageInfo implements Serializable {
	private static final long serialVersionUID = -6258624531978352590L;

	protected static int DEF_PAGE_VIEW_SIZE = 10;
	
	@CacheKeyMethod
	public String cachedKey() {
		return this.getClass().getName() + "." + pageIndex + "." + pageSize;
	}

	// 分页默认的构造函数
	public PageInfo() {
		// 设定默认索引页面以及默认的每页大小
		this.pageIndex = 1;
		this.pageSize = DEF_PAGE_VIEW_SIZE;
	}

	/**
	 * 分页构造函数
	 */
	public PageInfo(int pageIndex, int pageSize) {
		this.pageIndex = (pageIndex <= 0) ? 1 : pageIndex;
		this.pageSize = (pageSize <= 0) ? DEF_PAGE_VIEW_SIZE : pageSize;
	}

	// 不进行分页，查询所有的时候，设定PageInfo=PageInfo.PAGE_ALL
	public static final PageInfo PAGE_ALL = null;

	// 每页显示的记录数目
	protected int pageSize;

	// 页数
	protected int pageCount;

	// 记录数
	protected int recordCount;

	// 当前页号
	protected int pageIndex;

	// 扩展属性，设定当前显示的翻页列表中的最小编号
	protected int rangeMin;

	// 扩展属性，设定当前显示的翻页列表中的最大编号
	protected int rangeMax;

	// 首页
	protected int startNo;
	// 尾页
	protected int endNo;
	// 下一页
	protected int nextPageNo;
	// 上一夜
	protected int prePageNo;
	
	public int getRecordStartNo() {
		int recordStartNo = (pageIndex-1) * pageSize;
		return recordStartNo;
	}

	public int getRangeMax() {
		return rangeMax;
	}

	public void setRangeMax(int rangeMax) {
		this.rangeMax = rangeMax;
	}

	public int getRangeMin() {
		return rangeMin;
	}

	public void setRangeMin(int rangeMin) {
		this.rangeMin = rangeMin;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {

		this.pageCount = pageCount;
	}

	public int getRecordCount() {
		return recordCount;
	}
	
	

	// 设定记录条数的时候要做相关页数，以及当前页的设定
	public void setRecordCount(int recordCount) {
		if (recordCount == 0) {
			// 没有记录
			this.recordCount = 0;
			this.pageCount = 1;
			this.pageIndex = 1;
			this.nextPageNo = 1;
			this.prePageNo = 1;
			this.rangeMin = 1;
			this.rangeMax = 1;
			this.startNo = 1;
			this.endNo = 1;
			return;
		}
		// 设定记录总述
		this.recordCount = recordCount;
		// 计算页数
		this.pageCount = this.recordCount / pageSize
				+ (this.recordCount % pageSize == 0 ? 0 : 1);
		if (this.pageIndex > this.pageCount) {
			pageIndex = pageCount;
		}
		if (this.pageIndex < 1) {
			this.pageIndex = 1;
		}
		// 开始设定扩展属性,设定相关显示的范围
		if (this.getPageIndex() <= 10 && this.getPageCount() <= 10) {
			this.rangeMin = 1;
			this.rangeMax = this.getPageCount();
			if (this.rangeMax == 0) {
				this.rangeMax = 1;
			}
		} else if (this.getPageIndex() <= 10 && this.getPageCount() >= 10) {
			this.rangeMin = 1;
			this.rangeMax = 10;
		} else if (this.getPageIndex() > (this.getPageCount() - 10)) {
			this.rangeMin = this.getPageCount() - 9;
			this.rangeMax = this.getPageCount();
		} else {
			this.rangeMin = this.getPageIndex() - 4;
			this.rangeMax = this.getPageIndex() + 4;
		}

		// 开始设置前后页、首尾页面
		this.startNo = 1;
		this.endNo = this.getPageCount();
		this.nextPageNo = Math.min(getPageIndex() + 1, this.getPageCount());
		this.prePageNo = Math.max(getPageIndex() - 1, 1);
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getEndNo() {
		return endNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public int getPrePageNo() {
		return prePageNo;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public void setPrePageNo(int prePageNo) {
		this.prePageNo = prePageNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	
	public int getBeginRowNum() {
		return (pageIndex-1) * pageSize;
	}
	
	public int getEndRowNum() {
		return pageIndex * pageSize;
	}
}