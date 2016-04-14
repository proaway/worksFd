package com.fd.fashion.finance.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fd.dao.IBaseDao;
import com.fd.fashion.finance.bean.FinancialBean;
import com.fd.fashion.finance.vo.FinancialVo;
import com.fd.util.PageInfo;

/** 财务收支 */
@Component
@SuppressWarnings("unchecked")
public class FinancialService implements IFinancialService {
	@Autowired
	@Qualifier("dao")
	private IBaseDao dao;

	/**
	 * 增加财务收支
	 * 
	 * @param financial
	 * @return
	 * @throws Exception
	 */
	public FinancialBean insertFinancialBean(FinancialBean financial)
			throws Exception {
		dao.insertObj("insertFinancialBean", financial);
		return financial;
	}

	/**
	 * 修改财务收支
	 * 
	 * @param financial
	 * @return
	 * @throws Exception
	 */
	public int updateFinancialBean(FinancialBean financial) throws Exception {
		return dao.updateObj("updateFinancialBean", financial);
	}

	/**
	 * 获取财务收支
	 * 
	 * @param financial
	 * @return
	 * @throws Exception
	 */
	public FinancialBean getFinancialBean(FinancialBean financial)
			throws Exception {
		return (FinancialBean) dao.getAsObject("getFinancialBean", financial);
	}

	/**
	 * 获取财务收支
	 * 
	 * @param financial
	 * @return
	 * @throws Exception
	 */
	public List<FinancialBean> getFinancialBeans(FinancialBean financial)
			throws Exception {
		return dao.getAsList("getFinancialBean", financial);
	}

	/**
	 * 获取财务收支
	 * 
	 * @param financial
	 * @return
	 * @throws Exception
	 */
	public List<FinancialBean> getFinancialBeans(FinancialVo financialVo,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getFinancialBeanCount",
				financialVo);
		if(pageInfo != null){
			pageInfo.setRecordCount(count == null ? 0 : count);
		}
		if (count != null && count > 0) {
			if(pageInfo != null){
				return dao.getAsList("getFinancialBean", financialVo, pageInfo);
			}else{
				return dao.getAsList("getFinancialBean", financialVo);
			}

		}
		return null;
	}
	
	

	/**
	 * 删除财务收支
	 * 
	 * @param financial
	 * @return
	 * @throws Exception
	 */
	public int deleteFinancialBean(FinancialBean financial) throws Exception {
		return dao.deleteObj("deleteFinancialBean", financial);
	}
	
	/**
	 * 获取当前盈余总值
	 * 
	 * @param financial
	 * @return
	 * @throws Exception
	 */
	public FinancialVo getFinancialSum() throws Exception{
		return (FinancialVo)dao.getAsObject("getFinancialSum");
	}
	
	
	/**
	 * 财务图表数据
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,Double> getFinancialMapDate(String date) throws Exception{
		return (HashMap<String,Double>)dao.getAsObject("FinancialMapDate",date);
	}
}