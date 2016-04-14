package com.fd.fashion.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.fd.dao.IBaseDao;
import com.fd.fashion.stock.bean.PurchasingTaskAttBean;
import com.fd.fashion.stock.bean.PurchasingTaskBean;
import com.fd.fashion.stock.bean.PurchasingTaskInfoBean;
import com.fd.fashion.stock.bean.StockBean;
import com.fd.util.PageInfo;

/**
 * @creatTime 2013-6-19 下午5:05:39
 * @author CuiErWei
 * @description 仓库操作service
 * 
 */
@Component
@SuppressWarnings("unchecked")
public class StockService implements IStockService {
	@Autowired
	@Qualifier("dao")
	private IBaseDao dao;

	/**
	 * 增加库存
	 * 
	 * @param stockBean
	 * @return
	 * @throws Exception
	 */
	public StockBean insertStockBean(StockBean stockBean) throws Exception {
		Long id = stockBean.getStockId();
		if (id != null && id <= 0) {
			stockBean.setStockId(null);
		}
		dao.insertObj("insertStockBean", stockBean);
		if (id != null && id > 0) {
			stockBean.setStockId(id);
		}
		return stockBean;
	}

	/**
	 * 修改库存
	 * 
	 * @param stockBean
	 * @return
	 * @throws Exception
	 */
	public int updateStockBean(StockBean stockBean) throws Exception {
		return dao.updateObj("updateStockBean", stockBean);
	}

	/**
	 * 获取库存
	 * 
	 * @param stockBean
	 * @return
	 * @throws Exception
	 */
	public List<StockBean> getStockBeans(StockBean stockBean) throws Exception {
		return dao.getAsList("getStockBean", stockBean);
	}

	/**
	 * 获取库存
	 * 
	 * @param stockBean
	 * @return
	 * @throws Exception
	 */
	public List<StockBean> getStockBeans(StockBean stockBean, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getStockBeanCount",
				stockBean);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getStockBean", stockBean, pageInfo);
		}
		return null;
	}

	/**
	 * 删除库存
	 * 
	 * @param stockBean
	 * @return
	 * @throws Exception
	 */
	public int deleteStockBean(StockBean stockBean) throws Exception {
		return dao.deleteObj("deleteStockBean", stockBean);
	}

	// 采购任务////////////////////////////////////////////////////////////////////////////////
	/**
	 * 增加采购任务
	 * 
	 * @param purchasingTaskBean
	 * @return
	 * @throws Exception
	 */
	public PurchasingTaskBean insertPurchasingTaskBean(
			PurchasingTaskBean purchasingTaskBean) throws Exception {
		dao.insertObj("insertPurchasingTaskBean", purchasingTaskBean);
		
		return purchasingTaskBean;
	}

	/**
	 * 修改采购任务
	 * 
	 * @param purchasingTaskBean
	 * @return
	 * @throws Exception
	 */
	public int updatePurchasingTaskBean(PurchasingTaskBean purchasingTaskBean)
			throws Exception {
		return dao.updateObj("updatePurchasingTaskBean", purchasingTaskBean);
	}

	/**
	 * 获取采购任务
	 * 
	 * @param purchasingTaskBean
	 * @return
	 * @throws Exception
	 */
	public List<PurchasingTaskBean> getPurchasingTaskBeans(
			PurchasingTaskBean purchasingTaskBean) throws Exception {
		return dao.getAsList("getPurchasingTaskBeans", purchasingTaskBean);
	}

	/**
	 * 获取采购任务
	 * 
	 * @param purchasingTaskBean
	 * @return
	 * @throws Exception
	 */
	public List<PurchasingTaskBean> getPurchasingTaskBeans(
			PurchasingTaskBean purchasingTaskBean, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getPurchasingTaskBeanCount",
				purchasingTaskBean);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getPurchasingTaskBeans", purchasingTaskBean,
					pageInfo);
		}
		return null;
	}

	/**
	 * 删除采购任务
	 * 
	 * @param purchasingTaskBean
	 * @return
	 * @throws Exception
	 */
	public int deletePurchasingTaskBean(PurchasingTaskBean purchasingTaskBean)
			throws Exception {
		return dao.deleteObj("deletePurchasingTaskBean", purchasingTaskBean);
	}

	// 采购任务附件
	/**
	 * 增加采购任务附件
	 * 
	 * @param purchasingTaskAttBean
	 * @return
	 * @throws Exception
	 */
	public PurchasingTaskAttBean insertPurchasingTaskAttBean(
			PurchasingTaskAttBean purchasingTaskAttBean) throws Exception {
		dao.insertObj("insertPurchasingTaskAttBean", purchasingTaskAttBean);
		return purchasingTaskAttBean;
	}

	/**
	 * 修改采购任务附件
	 * 
	 * @param purchasingTaskAttBean
	 * @return
	 * @throws Exception
	 */
	public int updatePurchasingTaskAttBean(
			PurchasingTaskAttBean purchasingTaskAttBean) throws Exception {
		return dao.updateObj("updatePurchasingTaskAttBean",
				purchasingTaskAttBean);
	}

	/**
	 * 获取采购任务附件
	 * 
	 * @param purchasingTaskAttBean
	 * @return
	 * @throws Exception
	 */
	public List<PurchasingTaskAttBean> getPurchasingTaskAttBeans(
			PurchasingTaskAttBean purchasingTaskAttBean) throws Exception {
		return dao
				.getAsList("getPurchasingTaskAttBeans", purchasingTaskAttBean);
	}

	/**
	 * 获取采购任务附件
	 * 
	 * @param purchasingTaskAttBean
	 * @return
	 * @throws Exception
	 */
	public List<PurchasingTaskAttBean> getPurchasingTaskAttBeans(
			PurchasingTaskAttBean purchasingTaskAttBean, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject(
				"getPurchasingTaskAttBeanCount", purchasingTaskAttBean);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getPurchasingTaskAttBeans",
					purchasingTaskAttBean, pageInfo);
		}
		return null;
	}

	/**
	 * 删除采购任务附件
	 * 
	 * @param purchasingTaskAttBean
	 * @return
	 * @throws Exception
	 */
	public int deletePurchasingTaskAttBean(
			PurchasingTaskAttBean purchasingTaskAttBean) throws Exception {
		return dao.deleteObj("deletePurchasingTaskAttBean",
				purchasingTaskAttBean);
	}

	// 采购任务信息

	/**
	 * 增加采购任务附件
	 * 
	 * @param purchasingTaskInfoBean
	 * @return
	 * @throws Exception
	 */
	public PurchasingTaskInfoBean insertPurchasingTaskInfoBean(
			PurchasingTaskInfoBean purchasingTaskInfoBean) throws Exception {
		dao.insertObj("insertPurchasingTaskInfoBean", purchasingTaskInfoBean);
		return purchasingTaskInfoBean;
	}

	/**
	 * 修改采购任务附件
	 * 
	 * @param purchasingTaskInfoBean
	 * @return
	 * @throws Exception
	 */
	public int updatePurchasingTaskInfoBean(
			PurchasingTaskInfoBean purchasingTaskInfoBean) throws Exception {
		return dao.updateObj("updatePurchasingTaskInfoBean",
				purchasingTaskInfoBean);
	}

	/**
	 * 获取采购任务附件
	 * 
	 * @param purchasingTaskInfoBean
	 * @return
	 * @throws Exception
	 */
	public List<PurchasingTaskInfoBean> getPurchasingTaskInfoBeans(
			PurchasingTaskInfoBean purchasingTaskInfoBean) throws Exception {
		return dao.getAsList("getPurchasingTaskInfoBeans",
				purchasingTaskInfoBean);
	}

	/**
	 * 获取采购任务附件
	 * 
	 * @param purchasingTaskInfoBean
	 * @return
	 * @throws Exception
	 */
	public List<PurchasingTaskInfoBean> getPurchasingTaskInfoBeans(
			PurchasingTaskInfoBean purchasingTaskInfoBean, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject(
				"getPurchasingTaskInfoBeanCount", purchasingTaskInfoBean);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getPurchasingTaskInfoBeans",
					purchasingTaskInfoBean, pageInfo);
		}
		return null;
	}

	/**
	 * 删除采购任务附件
	 * 
	 * @param purchasingTaskInfoBean
	 * @return
	 * @throws Exception
	 */
	public int deletePurchasingTaskInfoBean(
			PurchasingTaskInfoBean purchasingTaskInfoBean) throws Exception {
		return dao.deleteObj("deletePurchasingTaskInfoBean",
				purchasingTaskInfoBean);
	}
}