/**
 * CouponVo.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.order.vo;

import java.util.Date;
import java.util.List;

import com.fd.fashion.order.bean.CouponBean;
import com.fd.util.PageInfo;

/**
 * @CreateDate:  2013-4-23 下午4:07:01 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class CouponVo {
	private List<CouponBean> couponList;
	private String couponCode;
	private Date startDate;
	private Date endDate;
	private Double price;
	private String couponType;
	private PageInfo pageInfo;
	
}
