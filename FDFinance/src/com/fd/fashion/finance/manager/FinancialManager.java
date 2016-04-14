/**
 * IFinanicalManager.java
 * @author:  Zhou Rongyu
 */
package com.fd.fashion.finance.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fd.fashion.finance.bean.FinancialBean;
import com.fd.fashion.finance.service.IFinancialService;
import com.fd.fashion.finance.vo.FinancialVo;
import com.fd.util.PageInfo;

/**
 * @CreateDate:  2013-5-13 下午5:38:11 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
@Transactional(rollbackFor=Exception.class)
@Component
public class FinancialManager implements IFinancialManager{
	
	@Autowired
	@Qualifier("financialService")
	IFinancialService financialService;
	/**
	 * 财务明细列表
	 * @param FinanicalBean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public FinancialVo getFinancialList(FinancialVo financialVo, PageInfo pageInfo)
			throws Exception {
		FinancialVo fv = new FinancialVo();
		fv = financialService.getFinancialSum();
		List<FinancialBean> financialList =  financialService.getFinancialBeans(financialVo, pageInfo);
		fv.setFinancialList(financialList);
		return fv;
	}
	
	/**
	 * 财务图表数据
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public List<List<Object>> getFinancialMapDate(String date) throws Exception{
		HashMap<String,Double> todayMap = financialService.getFinancialMapDate(date);
		List<List<Object>> dataLine = new ArrayList<List<Object>>();
		List<Object> tmp = new ArrayList<Object>();
		tmp.add(0);
		tmp.add(0);
		dataLine.add(tmp);
		for(int i=1;i<13;i++){
			String key = "T"+i;
			Double total = todayMap.get(key);
			List<Object> line = new ArrayList<Object>();
			line.add(i*2);
			line.add(total);
			dataLine.add(line);
		}
		
		return dataLine;
	}

}
