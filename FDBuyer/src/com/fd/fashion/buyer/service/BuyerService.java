package com.fd.fashion.buyer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fd.dao.IBaseDao;
import com.fd.fashion.buyer.bean.BuyerAddressBookBean;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.bean.BuyerMemoBean;
import com.fd.fashion.buyer.bean.EmailInfoBean;
import com.fd.fashion.buyer.vo.BuyerVo;
import com.fd.util.PageInfo;

/** 买家 */
@Component
@SuppressWarnings("unchecked")
public class BuyerService implements IBuyerService {
	@Autowired
	@Qualifier("dao")
	private IBaseDao dao;

	/**
	 * 增加买家
	 * 
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	public BuyerBean insertBuyerBean(BuyerBean buyer) throws Exception {
		dao.insertObj("insertBuyerBean", buyer);
		return buyer;
	}

	/**
	 * 修改买家
	 * 
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	public int updateBuyerBean(BuyerBean buyer) throws Exception {
		return dao.updateObj("updateBuyerBean", buyer);
	}

	/**
	 * 获取买家
	 * 
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	public List<BuyerBean> getBuyerBeans(BuyerBean buyer) throws Exception {
		return dao.getAsList("getBuyerBean", buyer);
	}
	
	/**
	 * 返回获取到的第一个买家
	 * 
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	public BuyerBean getBuyerBean(BuyerBean buyer) throws Exception {
		List<BuyerBean> buyers = getBuyerBeans(buyer);
		if (buyers != null && buyers.size()>0) {
			return buyers.get(0);
		}
		return null;
	}

	/**
	 * 获取买家
	 * 
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	public List<BuyerBean> getBuyerBeans(BuyerBean buyer, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getBuyerBeanCount", buyer);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getBuyerBean", buyer, pageInfo);
		}
		return null;
	}
	
	/**获取买家列表
	 * @param buyer
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<BuyerBean> getBuyerList(BuyerBean buyer,PageInfo pageInfo) throws Exception{
		
		Integer count = (Integer) dao.getAsObject("getBuyerBeanCount", buyer);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			buyer.setPageInfo(pageInfo);
			return dao.getAsList("getBuyerListCase", buyer);
		}
		return null;
		
	}
	

	/**
	 * 删除买家
	 * 
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	public int deleteBuyerBean(BuyerBean buyer) throws Exception {
		return dao.deleteObj("deleteBuyerBean", buyer);
	}
	
	/**
	 * 激活买家邮箱
	 * 
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public int activeBuyerEmail(String activeCode) throws Exception {
		return dao.updateObj("activeBuyerEmail", activeCode);
	}
	
	/**
	 * 获取买家地址本
	 * 
	 * @param id buyerId 或 addressId
	 * @return
	 * @throws Exception
	 */
	public BuyerAddressBookBean getBuyerAddressBook(BuyerAddressBookBean addressBook) throws Exception {
		return (BuyerAddressBookBean)dao.getAsObject("getBuyerAddressBook", addressBook);
	}
	
	/**
	 * 获取买家地址本
	 * 
	 * @param id buyerId 或 addressId
	 * @return
	 * @throws Exception
	 */
	public List<BuyerAddressBookBean> getBuyerAddressBooks(BuyerAddressBookBean addressBook) throws Exception {
		return dao.getAsList("getBuyerAddressBook", addressBook);
	}
	
	/**
	 * 新增地址本
	 * 
	 * @param addressBook
	 * @return
	 * @throws Exception
	 */
	public BuyerAddressBookBean addBuyerAddressBook(BuyerAddressBookBean addressBook) throws Exception {
		dao.insertObj("insertBuyerAddressBook", addressBook);
		return addressBook;
	}
	
	/**
	 * 更新地址本
	 * 
	 * @param addressBook
	 * @return
	 * @throws Exception
	 */
	public int updateBuyerAddressBook(BuyerAddressBookBean addressBook) throws Exception {
		return dao.updateObj("updateBuyerAddressBook", addressBook);
	}
	
	/**
	 * 清除缺省地址本
	 * 
	 * @param buyerId
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public int clearDefaultAddressBook(long buyerId) throws Exception {
		return dao.updateObj("clearDefaultAddressBook", buyerId);
	}
	
	/**
	 * 设置缺省地址本
	 * 
	 * @param buyerId
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public int setDefaultAddressBook(long addressId) throws Exception {
		return dao.updateObj("setDefaultAddressBook", addressId);
	}
	
	/**删除单个地址
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public Integer deleteAddressBook(long addressId)throws Exception{
		return dao.deleteObj("deleteAddressBook",addressId );
	}
	
	/**获取买家备注列表
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<BuyerMemoBean> getBuyerMemoList(long buyerId) throws Exception{
		return dao.getAsList("getBuyerMemoList", buyerId);
	}
	
	/**添加买家备注信息
	 * @param buyerMemo
	 * @return
	 * @throws Exception
	 */
	public BuyerMemoBean insertBuyerMemo(BuyerMemoBean buyerMemo) throws Exception{
		 dao.insertObj("insertBuyerMemo", buyerMemo);
		 return buyerMemo;
	}
	
	/**获取买家邮件列表
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<EmailInfoBean> buyerEmailList(EmailInfoBean emailBean,PageInfo pageInfo) throws Exception{
		Integer count = (Integer)dao.getAsObject("buyerEmailByTypeAndBuyerIdCount",emailBean);
		if(count>0){
			pageInfo.setRecordCount(count);
			emailBean.setPageInfo(pageInfo);
			return dao.getAsList("buyerEmailListByTypeAndBuyerId", emailBean);
		}
		return null;
	}
	
	/**获取某个用户的各种订单统计信息
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public BuyerVo getBuyerCountInfo(long buyerId,String flag) throws Exception{
		if(flag==null || "".equals(flag)){
			return null;
		}
		if(flag.equals("1")){
			return (BuyerVo)dao.getAsObject("getBuyerOrderCount", buyerId);
		}else if(flag.equals("2")){
			return (BuyerVo)dao.getAsObject("getBuyerWaitOrderCount", buyerId);
		}else {
			return (BuyerVo)dao.getAsObject("getBuyerRefundOrderCount", buyerId);
		}
		
	}
	
	/**查询买家下单天数
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public Integer getBuyerCreateOrderDayCount(long buyerId) throws Exception{
		return (Integer)dao.getAsObject("getBuyerCreateOrderDayCount", buyerId);
	}
}