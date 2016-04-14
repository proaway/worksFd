package com.fd.statistics.bean;
import java.util.Date;
/** 每日访问统计 */public class VisitDailystatBean {
/** 统计日期*/
private Date statDate;
/** 产品编号*/
private Long productId;
/** 产品订单数*/
private Integer orderCount;
/** 产品支付订单数*/
private Integer payorderCount;
/** 产品销售数*/
private Integer saleCount;
/** 产品访问总数*/
private Integer clickCount;
/** 统计日期*/
public void setStatDate(Date statDate) {
this.statDate = statDate;}
/** 统计日期*/
public Date getStatDate() {return this.statDate;}
/** 产品编号*/
public void setProductId(Long productId) {
this.productId = productId;}
/** 产品编号*/
public Long getProductId() {return this.productId;}
/** 产品订单数*/
public void setOrderCount(Integer orderCount) {
this.orderCount = orderCount;}
/** 产品订单数*/
public Integer getOrderCount() {return this.orderCount;}
/** 产品支付订单数*/
public void setPayorderCount(Integer payorderCount) {
this.payorderCount = payorderCount;}
/** 产品支付订单数*/
public Integer getPayorderCount() {return this.payorderCount;}
/** 产品销售数*/
public void setSaleCount(Integer saleCount) {
this.saleCount = saleCount;}
/** 产品销售数*/
public Integer getSaleCount() {return this.saleCount;}
/** 产品访问总数*/
public void setClickCount(Integer clickCount) {
this.clickCount = clickCount;}
/** 产品访问总数*/
public Integer getClickCount() {return this.clickCount;}}