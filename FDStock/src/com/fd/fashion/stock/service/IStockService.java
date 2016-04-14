package com.fd.fashion.stock.service;

import java.util.List;

import com.fd.fashion.stock.bean.PurchasingTaskAttBean;
import com.fd.fashion.stock.bean.PurchasingTaskBean;
import com.fd.fashion.stock.bean.PurchasingTaskInfoBean;
import com.fd.fashion.stock.bean.StockBean;
import com.fd.util.PageInfo;

/**
 * @creatTime 2013-6-19 下午5:08:37
 * @author CuiErWei
 * @description 仓库操作service
 * 
 */
public interface IStockService {

	/**
	 * 增加库存
	 * 
	 * @param stockBean
	 * @return
	 * @throws Exception
	 */
	public StockBean insertStockBean(StockBean stockBean) throws Exception;

	/**
	 * 修改库存
	 * 
	 * @param stockBean
	 * @return
	 * @throws Exception
	 */
	public int updateStockBean(StockBean stockBean) throws Exception;

	/**
	 * 获取库存
	 * 
	 * @param stockBean
	 * @return
	 * @throws Exception
	 */
	public List<StockBean> getStockBeans(StockBean stockBean) throws Exception;

	/**
	 * 获取库存
	 * 
	 * @param stockBean
	 * @return
	 * @throws Exception
	 */
	public List<StockBean> getStockBeans(StockBean stockBean, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除库存
	 * 
	 * @param stockBean
	 * @return
	 * @throws Exception
	 */
	public int deleteStockBean(StockBean stockBean) throws Exception;

	// 采购任务 //////////////////////////////////

	/**
	 * 增加采购任务
	 * 
	 * @param purchasingTaskBean
	 * @return
	 * @throws Exception
	 */
	public PurchasingTaskBean insertPurchasingTaskBean(
			PurchasingTaskBean purchasingTaskBean) throws Exception;

	/**
	 * 修改采购任务
	 * 
	 * @param purchasingTaskBean
	 * @return
	 * @throws Exception
	 */
	public int updatePurchasingTaskBean(PurchasingTaskBean purchasingTaskBean)
			throws Exception;

	/**
	 * 获取采购任务
	 * 
	 * @param purchasingTaskBean
	 * @return
	 * @throws Exception
	 */
	public List<PurchasingTaskBean> getPurchasingTaskBeans(
			PurchasingTaskBean purchasingTaskBean) throws Exception;

	/**
	 * 获取采购任务
	 * 
	 * @param purchasingTaskBean
	 * @return
	 * @throws Exception
	 */
	public List<PurchasingTaskBean> getPurchasingTaskBeans(
			PurchasingTaskBean purchasingTaskBean, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除采购任务
	 * 
	 * @param purchasingTaskBean
	 * @return
	 * @throws Exception
	 */
	public int deletePurchasingTaskBean(PurchasingTaskBean purchasingTaskBean)
			throws Exception;

	// 采购任务附件
	/**
	 * 增加采购任务附件
	 * 
	 * @param purchasingTaskAttBean
	 * @return
	 * @throws Exception
	 */
	public PurchasingTaskAttBean insertPurchasingTaskAttBean(
			PurchasingTaskAttBean purchasingTaskAttBean) throws Exception;

	/**
	 * 修改采购任务附件
	 * 
	 * @param purchasingTaskAttBean
	 * @return
	 * @throws Exception
	 */
	public int updatePurchasingTaskAttBean(
			PurchasingTaskAttBean purchasingTaskAttBean) throws Exception;

	/**
	 * 获取采购任务附件
	 * 
	 * @param purchasingTaskAttBean
	 * @return
	 * @throws Exception
	 */
	public List<PurchasingTaskAttBean> getPurchasingTaskAttBeans(
			PurchasingTaskAttBean purchasingTaskAttBean) throws Exception;

	/**
	 * 获取采购任务附件
	 * 
	 * @param purchasingTaskAttBean
	 * @return
	 * @throws Exception
	 */
	public List<PurchasingTaskAttBean> getPurchasingTaskAttBeans(
			PurchasingTaskAttBean purchasingTaskAttBean, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除采购任务附件
	 * 
	 * @param purchasingTaskAttBean
	 * @return
	 * @throws Exception
	 */
	public int deletePurchasingTaskAttBean(
			PurchasingTaskAttBean purchasingTaskAttBean) throws Exception;

	// 采购任务信息

	/**
	 * 增加采购任务信息
	 * 
	 * @param purchasingTaskInfoBean
	 * @return
	 * @throws Exception
	 */
	public PurchasingTaskInfoBean insertPurchasingTaskInfoBean(
			PurchasingTaskInfoBean purchasingTaskInfoBean) throws Exception;

	/**
	 * 修改采购任务信息
	 * 
	 * @param purchasingTaskInfoBean
	 * @return
	 * @throws Exception
	 */
	public int updatePurchasingTaskInfoBean(
			PurchasingTaskInfoBean purchasingTaskInfoBean) throws Exception;

	/**
	 * 获取采购任务信息
	 * 
	 * @param purchasingTaskInfoBean
	 * @return
	 * @throws Exception
	 */
	public List<PurchasingTaskInfoBean> getPurchasingTaskInfoBeans(
			PurchasingTaskInfoBean purchasingTaskInfoBean) throws Exception;

	/**
	 * 获取采购任务信息
	 * 
	 * @param purchasingTaskInfoBean
	 * @return
	 * @throws Exception
	 */
	public List<PurchasingTaskInfoBean> getPurchasingTaskInfoBeans(
			PurchasingTaskInfoBean purchasingTaskInfoBean, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除采购任务信息
	 * 
	 * @param purchasingTaskInfoBean
	 * @return
	 * @throws Exception
	 */
	public int deletePurchasingTaskInfoBean(
			PurchasingTaskInfoBean purchasingTaskInfoBean) throws Exception;
}