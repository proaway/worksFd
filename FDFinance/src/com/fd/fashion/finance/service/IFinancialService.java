package com.fd.fashion.finance.service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fd.fashion.finance.bean.FinancialBean;
import com.fd.fashion.finance.vo.FinancialVo;
import com.fd.util.PageInfo;
/** 财务收支接口 */
public interface IFinancialService {
	/**
	 * 增加财务收支
	 * 
	 * @param financial
	 * @return
	 * @throws Exception
	 */
	public FinancialBean insertFinancialBean(FinancialBean financial) throws Exception;
	
	/**
	 * 修改财务收支
	 * 
	 * @param financial
	 * @return
	 * @throws Exception
	 */
	public int updateFinancialBean(FinancialBean financial) throws Exception;
	
	
	/**
	 * 获取财务收支
	 * 
	 * @param financial
	 * @return
	 * @throws Exception
	 */
	public FinancialBean getFinancialBean(FinancialBean financial) throws Exception;
	/**
	 * 获取财务收支
	 * 
	 * @param financial
	 * @return
	 * @throws Exception
	 */
	public List<FinancialBean> getFinancialBeans(FinancialBean financial) throws Exception;
	
	/**
	 * 获取财务收支
	 * 
	 * @param financial
	 * @return
	 * @throws Exception
	 */
	public List<FinancialBean> getFinancialBeans(FinancialVo financialVo, PageInfo pageInfo) throws Exception;
	
	/**
	 * 删除财务收支
	 * 
	 * @param financial
	 * @return
	 * @throws Exception
	 */
	public int deleteFinancialBean(FinancialBean financial) throws Exception;
	
	
	/**
	 * 获取当前盈余总值
	 * 
	 * @param financial
	 * @return
	 * @throws Exception
	 */
	public FinancialVo getFinancialSum() throws Exception;
	
	/**
	 * 财务图表数据
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Double> getFinancialMapDate(String date) throws Exception;
	
	
}