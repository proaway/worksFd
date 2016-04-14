/**
 * Financial_overview.java
 * @author:  Zhou Rongyu
 */
package com.fd.b2c.manager.modules.screens.financial;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.finance.manager.IFinancialManager;
import com.fd.fashion.finance.vo.FinancialVo;
import com.fd.util.CullNumUtil;
import com.fd.util.PageInfo;
import com.fd.util.ParametersUtil;
import com.fd.util.RateUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate:  2013-5-11 下午6:10:26 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class FinancialDetails  extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		IFinancialManager financialManager = (IFinancialManager) getBean("financialManager");
		
		//**********设置PageInfo分页信息
		int pageSize = 4;
		int pageIndex = data.getParameters().getInt("pageIndex", 1);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setPageSize(pageSize);
		
		FinancialVo financialVo = new FinancialVo();
		ParametersUtil.initParameters(data, financialVo);
		
		context.put("startAmount", financialVo.getStartAmount());
		context.put("endAmount", financialVo.getEndAmount());
		context.put("type", financialVo.getType());
		context.put("startDate", StringUtil.getDateString(financialVo.getStartDate()));
		context.put("endDate", StringUtil.getDateString(financialVo.getEndDate()));
		
		String searchType = data.getParameters().getString("searchType","0");
		context.put("searchType", searchType);

		financialVo = financialManager.getFinancialList(financialVo, pageInfo);
		//获取汇率计算价格
		String rate = RateUtil.getRate();
		rate  = CullNumUtil.cullNumTwo(Double.valueOf(rate)/100);
		context.put("rate", rate);
		Double incomeTotalRMB = CullNumUtil.cullNum(financialVo.getIncomeTotal()*Double.valueOf(rate));
		financialVo.setIncomeTotalRMB(incomeTotalRMB);
		
		//不显示负号
		if(financialVo!=null && financialVo.getExpendTotal()!=null){
			if(financialVo.getExpendTotal()<0){
				Double total = financialVo.getExpendTotal()*(-1);
				financialVo.setExpendTotal(total);
			}
		}
		
		context.put("financialVo", financialVo);
		context.put("pageInfo", pageInfo);
		context.put("CullNumUtil", new CullNumUtil());
	}

}
