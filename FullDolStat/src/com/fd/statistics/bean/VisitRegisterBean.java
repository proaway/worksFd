package com.fd.statistics.bean;
import java.util.Date;
/** 访问注册 */public class VisitRegisterBean {
/** 访问ID*/
private Long visitId;
/** 买家注册名*/
private String buyer;
/** 注册时间*/
private Date registerTime;
/** 访问ID*/
public void setVisitId(Long visitId) {
this.visitId = visitId;}
/** 访问ID*/
public Long getVisitId() {return this.visitId;}
/** 买家注册名*/
public void setBuyer(String buyer) {
this.buyer = buyer;}
/** 买家注册名*/
public String getBuyer() {return this.buyer;}
/** 注册时间*/
public void setRegisterTime(Date registerTime) {
this.registerTime = registerTime;}
/** 注册时间*/
public Date getRegisterTime() {return this.registerTime;}}