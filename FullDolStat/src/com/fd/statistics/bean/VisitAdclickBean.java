package com.fd.statistics.bean;
import java.util.Date;
/** 广告点击统计 */public class VisitAdclickBean {
/** ID主键*/
private Long clickId;
/** 访问ID*/
private Long visitId;
/** 频道ID*/
private String channelId;
/** 位置*/
private Integer position;
/** 点击时间*/
private Date clickTime;
/** 产品所在url地址*/
private String url;
/** ID主键*/
public void setClickId(Long clickId) {
this.clickId = clickId;}
/** ID主键*/
public Long getClickId() {return this.clickId;}
/** 访问ID*/
public void setVisitId(Long visitId) {
this.visitId = visitId;}
/** 访问ID*/
public Long getVisitId() {return this.visitId;}
/** 频道ID*/
public void setChannelId(String channelId) {
this.channelId = channelId;}
/** 频道ID*/
public String getChannelId() {return this.channelId;}
/** 位置*/
public void setPosition(Integer position) {
this.position = position;}
/** 位置*/
public Integer getPosition() {return this.position;}
/** 点击时间*/
public void setClickTime(Date clickTime) {
this.clickTime = clickTime;}
/** 点击时间*/
public Date getClickTime() {return this.clickTime;}
/** 产品所在url地址*/
public void setUrl(String url) {
this.url = url;}
/** 产品所在url地址*/
public String getUrl() {return this.url;}}