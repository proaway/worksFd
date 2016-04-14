package com.fd.fashion.order.vo;

import java.util.List;

import com.fd.fashion.order.bean.PickingBean;
import com.fd.fashion.order.bean.PickingInfoBean;

/**
 * @author zhangling
 * 
 * 拣货详情
 *
 */
public class PickingVo {
	/**订单待拣信息*/
	private PickingBean pick;
	/**某订单待拣产品列表*/
	private List<PickingInfoBean> pickInfos;
	/**订单当前状态*/
	private String orderStatus;
	/**订单当前状态*/
	private String orderStatusCn;
	/**产品种类数*/
	private Integer calCounts;
	/**产品总个数*/
	private Integer totalCount;

	public Integer getCalCounts() {
		return calCounts;
	}

	public void setCalCounts(Integer calCounts) {
		this.calCounts = calCounts;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public PickingBean getPick() {
		return pick;
	}

	public void setPick(PickingBean pick) {
		this.pick = pick;
	}

	public List<PickingInfoBean> getPickInfos() {
		return pickInfos;
	}

	public void setPickInfos(List<PickingInfoBean> pickInfos) {
		this.pickInfos = pickInfos;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatusCn() {
		return orderStatusCn;
	}

	public void setOrderStatusCn(String orderStatusCn) {
		this.orderStatusCn = orderStatusCn;
	}

}
