/**
 * FinancialExceportExcelAction.java
 * @author:  Zhou Rongyu
 */
package com.fd.b2c.manager.modules.actions.financial;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.finance.bean.FinancialBean;
import com.fd.fashion.finance.manager.IFinancialManager;
import com.fd.fashion.finance.vo.FinancialVo;
import com.fd.util.ParametersUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate:  2013-5-16 上午10:22:43 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class FinancialExceportExcelAction extends SecureAction {

	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");

		FinancialVo financialVo = new FinancialVo();
		ParametersUtil.initParameters(data, financialVo);
		
		IFinancialManager financialManager = (IFinancialManager) getBean("financialManager");
		financialVo = financialManager.getFinancialList(financialVo, null);
		for(int i=0;i<financialVo.getFinancialList().size();i++){
			FinancialBean financial = financialVo.getFinancialList().get(i);
			String type = financial.getAmountType();
			if("1".equals(type)){
				financial.setAmountType("收入");
			}else if("2".equals(type)){
				financial.setAmountType("退款");
			}else if("3".equals(type)){
				financial.setAmountType("采购");
			}else if("4".equals(type)){
				financial.setAmountType("运费");
			}else if("5".equals(type)){
				financial.setAmountType("佣金");
			}else if("6".equals(type)){
				financial.setAmountType("活动");
			}
			
		}
		
		
		// 获取模板文件
			HttpServletResponse response = data.getResponse();
			HttpServletRequest request = data.getRequest();
			ServletOutputStream out = response.getOutputStream();

			/*IOrderManager orderManager = (IOrderManager) getBean("orderManager");
			SearchOrderVo searchOrder = orderManager.getSearchOrderList(orderSearch);*/
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("financialList", financialVo.getFinancialList());
			
			params.put("StringUtil", new StringUtil());

			// 提供下载
			response.reset();
			response.setContentType("application/x-download;charset=utf-8;");
			response.setHeader("Content-Disposition",
					"attachment;filename=report.xls");
			String path = request.getSession().getServletContext().getRealPath("/");
			String fileUrl = path + "templates" + File.separator
					+ "reportFinancial.xls";
			InputStream in = new FileInputStream(fileUrl);

			ExcelUtils.export(in, params, out);
			out.flush();
			out.close();
	}

}
