package com.fd.statistics.bean;
import java.util.Date;
/** 外链访问 */public class VisitOutsideBean {
/** ID主键*/
private Long openId;
/** 访问ID*/
private Long visitId;
/** 访问时间*/
private Date openTime;
/** 访问来源*/
private String openFrom;
/** 访问页面*/
private String openUrl;
/** 访问来源页面*/
private String referer;
/** 来源域名*/
private String domain;
/** ID主键*/
public void setOpenId(Long openId) {
this.openId = openId;}
/** ID主键*/
public Long getOpenId() {return this.openId;}
/** 访问ID*/
public void setVisitId(Long visitId) {
this.visitId = visitId;}
/** 访问ID*/
public Long getVisitId() {return this.visitId;}
/** 访问时间*/
public void setOpenTime(Date openTime) {
this.openTime = openTime;}
/** 访问时间*/
public Date getOpenTime() {return this.openTime;}
/** 访问来源*/
public void setOpenFrom(String openFrom) {
this.openFrom = openFrom;}
/** 访问来源*/
public String getOpenFrom() {return this.openFrom;}
/** 访问页面*/
public void setOpenUrl(String openUrl) {
this.openUrl = openUrl;}
/** 访问页面*/
public String getOpenUrl() {return this.openUrl;}
/** 访问来源页面*/
public void setReferer(String referer) {
this.referer = referer;}
/** 访问来源页面*/
public String getReferer() {return this.referer;}
/** 来源域名*/
public void setDomain(String domain) {
this.domain = domain;}
/** 来源域名*/
public String getDomain() {return this.domain;}}