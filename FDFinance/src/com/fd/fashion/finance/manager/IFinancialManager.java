/**
 * IFinanicalManager.java
 * @author:  Zhou Rongyu
 */
package com.fd.fashion.finance.manager;

import java.util.Date;
import java.util.List;

import com.fd.fashion.finance.vo.FinancialVo;
import com.fd.util.PageInfo;

/**
 * @CreateDate:  2013-5-13 下午5:38:11 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public interface IFinancialManager {

	/**
	 * 财务明细列表
	 * @param FinanicalBean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public FinancialVo getFinancialList(FinancialVo financialVo,PageInfo pageInfo) throws Exception ;
	

	/**
	 * 财务图表数据
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public List<List<Object>> getFinancialMapDate(String date) throws Exception ;
}
