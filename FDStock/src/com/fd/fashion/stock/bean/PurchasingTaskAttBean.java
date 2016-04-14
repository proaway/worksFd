package com.fd.fashion.stock.bean;

import java.io.Serializable;

/**
 * @createTime 2013-6-19 下午4:32:14
 * @author ErWei
 * @description 采购任务附件
 */
public class PurchasingTaskAttBean implements Serializable {
	private static final long serialVersionUID = -3946006752378026378L;
	// 采购任务ID
	private Long taskId;
	// 附件URL
	private String url;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}