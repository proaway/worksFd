package com.fd.fashion.stock.manager;

/**
 * @CreateDate: 2013-3-13 下午09:57:44
 * @author Longli
 * @Description: ProductManager接口类
 * 
 */
public interface IStockManager {
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
			Long productConfigId2) throws Exception;

	/**
	 * 通过产品ID和规格ID查询库存量:一种规格
	 * 
	 * @param productId
	 * @param productConfigId
	 * @return
	 * @throws Exception
	 */
	public Integer getStockNum(Long productId, Long productConfigId)
			throws Exception;

}
