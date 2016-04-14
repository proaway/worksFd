/**
 * Financial_overview.java
 * @author:  Zhou Rongyu
 */
package com.fd.b2c.manager.modules.screens.financial;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.finance.bean.FinancialBean;
import com.fd.fashion.finance.manager.IFinancialManager;
import com.fd.fashion.finance.vo.FinancialVo;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.util.CullNumUtil;
import com.fd.util.PageInfo;
import com.fd.util.RateUtil;

/**
 * @CreateDate:  2013-5-11 下午6:10:26 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class FinancialOverview  extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/FinancialLayout.html");
		IFinancialManager financialManager = (IFinancialManager) getBean("financialManager");
		
		//**********设置PageInfo分页信息
		int pageSize = 10;
		int pageIndex = data.getParameters().getInt("pageIndex", 1);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setPageSize(pageSize);
		FinancialVo financialVo = new FinancialVo();
		financialVo = financialManager.getFinancialList(financialVo, pageInfo);
		
		//获取当日汇率
		String rate = RateUtil.getRate();
		rate  = CullNumUtil.cullNumTwo(Double.valueOf(rate)/100);
		context.put("rate", rate);
		Double incomeTotalRMB = CullNumUtil.cullNum(financialVo.getIncomeTotal()*Double.valueOf(rate));
		financialVo.setIncomeTotalRMB(incomeTotalRMB);
		
		context.put("financialVo", financialVo);
		
		//获取图表数据
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int size = hour/2;
		
		cal.add(Calendar.DATE, -1);
		List<List<Object>> dataLineToday = financialManager.getFinancialMapDate(sdf.format(date));
		List<List<Object>> dataLineYesday = financialManager.getFinancialMapDate(sdf.format(cal.getTime()));
		
		dataLineToday = dataLineToday.subList(0, size+1);
		System.out.println(dataLineToday.toString());
		
		context.put("dataToday", dataLineToday.toString());
		context.put("dataYesterday", dataLineYesday.toString());
		Double d1 = (Double)dataLineToday.get(dataLineToday.size()-1).get(1);
		Double d2 = (Double)dataLineYesday.get(dataLineYesday.size()-1).get(1);
		
		//对纵坐标处理，去整
		Double maxTotal = 0.00;
		if(d1>d2){
			maxTotal = d1 * 1.1;
		}else{
			maxTotal = d2 * 1.1;
		}
		int tmp = Integer.valueOf(maxTotal.toString().substring(0, 1))+1;
		int s = String.valueOf((maxTotal.intValue()*100)).length() -3;
		for(int i=0;i<s;i++){
			tmp *= 10;
		}
		String max = String.valueOf(tmp);
		context.put("maxTotal", max);
	}

}
