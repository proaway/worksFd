package com.fd.statistics.bean;
import java.util.Date;
/** 访问统计 */public class VisitOpenBean {
/** ID主键*/
private Long openId;
/** 访问ID*/
private Long visitId;
/** 访问时间*/
private Date openTime;
/** 访问来源*/
private String openFrom;
/** 访问页面url*/
private String openUrl;
/** 查询参数*/
private String queryStr;
/** 访问来源页面*/
private String referer;
/** 访问页面url 的md5值，索引*/
private String urlMd5;
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
/** 访问页面url*/
public void setOpenUrl(String openUrl) {
this.openUrl = openUrl;}
/** 访问页面url*/
public String getOpenUrl() {return this.openUrl;}
/** 查询参数*/
public void setQueryStr(String queryStr) {
this.queryStr = queryStr;}
/** 查询参数*/
public String getQueryStr() {return this.queryStr;}
/** 访问来源页面*/
public void setReferer(String referer) {
this.referer = referer;}
/** 访问来源页面*/
public String getReferer() {return this.referer;}
/** 访问页面url 的md5值，索引*/
public void setUrlMd5(String urlMd5) {
this.urlMd5 = urlMd5;}
/** 访问页面url 的md5值，索引*/
public String getUrlMd5() {return this.urlMd5;}}