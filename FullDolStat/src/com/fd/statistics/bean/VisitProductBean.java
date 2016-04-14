package com.fd.statistics.bean;
import java.util.Date;
/** 产品访问统计 */public class VisitProductBean {
/** 访问ID*/
private Long visitId;
/** 产品编号*/
private Long productId;
/** 频道ID*/
private String channelId;
/** 位置*/
private Integer position;
/** 点击时间*/
private Date clickTime;
/** 购物车情况，0-未加入，1-加入，2-确认订单，3--生成订单*/
private Integer cart;
/** 订单号*/
private Long orderNo;
/** 产品所在url地址*/
private String url;
/** 访问ID*/
public void setVisitId(Long visitId) {
this.visitId = visitId;}
/** 访问ID*/
public Long getVisitId() {return this.visitId;}
/** 产品编号*/
public void setProductId(Long productId) {
this.productId = productId;}
/** 产品编号*/
public Long getProductId() {return this.productId;}
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
/** 购物车情况，0-未加入，1-加入，2-确认订单，3--生成订单*/
public void setCart(Integer cart) {
this.cart = cart;}
/** 购物车情况，0-未加入，1-加入，2-确认订单，3--生成订单*/
public Integer getCart() {return this.cart;}
/** 订单号*/
public void setOrderNo(Long orderNo) {
this.orderNo = orderNo;}
/** 订单号*/
public Long getOrderNo() {return this.orderNo;}
/** 产品所在url地址*/
public void setUrl(String url) {
this.url = url;}
/** 产品所在url地址*/
public String getUrl() {return this.url;}}