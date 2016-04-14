/**
 * FinancialVo.java
 * @author:  Zhou Rongyu
 */
package com.fd.fashion.finance.vo;

import java.util.Date;
import java.util.List;

import com.fd.fashion.finance.bean.FinancialBean;

/**
 * @CreateDate:  2013-5-14 下午5:23:24 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class FinancialVo {
	private List<FinancialBean> financialList;
	
	/** 账户收入总和 */
	private Double incomeTotal;
	/** 账户收入RMB*/
	private Double incomeTotalRMB;
	
	/** 账户支出总和 */
	private Double expendTotal;
	
	/** 搜索起止日期 */
	private Date startDate;	
	/** 搜索结束日期 */
	private Date endDate;
	/** 起始金额*/
	private Double startAmount;
	/** 结束金额 */
	private Double endAmount;
	/** 资金流向*/
	private String type;
	
	
	
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the startAmount
	 */
	public Double getStartAmount() {
		return startAmount;
	}
	/**
	 * @param startAmount the startAmount to set
	 */
	public void setStartAmount(Double startAmount) {
		this.startAmount = startAmount;
	}
	/**
	 * @return the endAmount
	 */
	public Double getEndAmount() {
		return endAmount;
	}
	/**
	 * @param endAmount the endAmount to set
	 */
	public void setEndAmount(Double endAmount) {
		this.endAmount = endAmount;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the financialList
	 */
	public List<FinancialBean> getFinancialList() {
		return financialList;
	}
	/**
	 * @param financialList the financialList to set
	 */
	public void setFinancialList(List<FinancialBean> financialList) {
		this.financialList = financialList;
	}
	/**
	 * @return the incomeTotal
	 */
	public Double getIncomeTotal() {
		return incomeTotal;
	}
	/**
	 * @param incomeTotal the incomeTotal to set
	 */
	public void setIncomeTotal(Double incomeTotal) {
		this.incomeTotal = incomeTotal;
	}
	/**
	 * @return the expendTotal
	 */
	public Double getExpendTotal() {
		return expendTotal;
	}
	/**
	 * @param expendTotal the expendTotal to set
	 */
	public void setExpendTotal(Double expendTotal) {
		this.expendTotal = expendTotal;
	}
	/**
	 * @return the incomeTotalRMB
	 */
	public Double getIncomeTotalRMB() {
		return incomeTotalRMB;
	}
	/**
	 * @param incomeTotalRMB the incomeTotalRMB to set
	 */
	public void setIncomeTotalRMB(Double incomeTotalRMB) {
		this.incomeTotalRMB = incomeTotalRMB;
	}
	
}
