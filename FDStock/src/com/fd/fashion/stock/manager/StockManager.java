package com.fd.fashion.stock.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.fd.fashion.dict.service.IDictService;
import com.fd.fashion.seller.service.ISellerService;
import com.fd.fashion.stock.service.IStockService;

/**
 * @CreateDate: 2013-3-13 下午10:00:26
 * @author Longli
 * @Description: 产品Manager实现类
 * 
 */
@Transactional(rollbackFor = Exception.class)
@Component
public class StockManager implements IStockManager {
	@Autowired
	@Qualifier("productService")
	IStockService productService;

	@Autowired
	@Qualifier("sellerService")
	ISellerService sellerService;

	@Autowired
	@Qualifier("dictService")
	IDictService dictService;

	/**
	 * 通过产品ID和规格ID查询库存量总 量:一种规格以上，不含一种
	 * 
	 * @param productId
	 * @param productConfigId
	 * @param productConfigId2
	 * @return
	 * @throws Exception
	 */
	public Integer getStockNum(Long productId, Long productConfigId,
			Long productConfigId2) throws Exception {
		if (productId == null || productConfigId == null
				|| productConfigId2 == null) {
			return 0;
		}
		return null;
	}

	/**
	 * 通过产品ID和规格ID查询库存量:一种规格
	 * 
	 * @param productId
	 * @param productConfigId
	 * @return
	 * @throws Exception
	 */
	public Integer getStockNum(Long productId, Long productConfigId)
			throws Exception {
		if (productId == null || productConfigId == null) {
			return 0;
		}
		return null;
	}
}
