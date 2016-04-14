package com.fd.fashion.order.bean;
import java.util.Date;
/** 拣货 */public class PickingBean {
/** 订单号*/
private Long orderId;
/** 拣货时间(打印订单时间)*/
private Date pickingDate;
/** 拣货状态 1：未开始拣货，2完成拣货，3未完成*/
private Integer status;
/** 拣货人*/
private String pickingUser;
/** 订单号*/
public void setOrderId(Long orderId) {
this.orderId = orderId;}
/** 订单号*/
public Long getOrderId() {return this.orderId;}
/** 拣货时间(打印订单时间)*/
public void setPickingDate(Date pickingDate) {
this.pickingDate = pickingDate;}
/** 拣货时间(打印订单时间)*/
public Date getPickingDate() {return this.pickingDate;}
/** 拣货状态 1：未开始拣货，2完成拣货，3未完成*/
public void setStatus(Integer status) {
this.status = status;}
/** 拣货状态 1：未开始拣货，2完成拣货，3未完成*/
public Integer getStatus() {return this.status;}
/** 拣货人*/
public void setPickingUser(String pickingUser) {
this.pickingUser = pickingUser;}
/** 拣货人*/
public String getPickingUser() {return this.pickingUser;}}