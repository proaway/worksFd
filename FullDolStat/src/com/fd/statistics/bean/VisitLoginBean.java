package com.fd.statistics.bean;
import java.util.Date;
/** 访问登录 */public class VisitLoginBean {
/** 访问ID*/
private Long visitId;
/** 登录买家名*/
private String buyer;
/** 登录时间*/
private Date loginTime;
/** 访问ID*/
public void setVisitId(Long visitId) {
this.visitId = visitId;}
/** 访问ID*/
public Long getVisitId() {return this.visitId;}
/** 登录买家名*/
public void setBuyer(String buyer) {
this.buyer = buyer;}
/** 登录买家名*/
public String getBuyer() {return this.buyer;}
/** 登录时间*/
public void setLoginTime(Date loginTime) {
this.loginTime = loginTime;}
/** 登录时间*/
public Date getLoginTime() {return this.loginTime;}}