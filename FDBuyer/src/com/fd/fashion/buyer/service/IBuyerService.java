package com.fd.fashion.buyer.service;
import java.util.List;

import com.fd.fashion.buyer.bean.BuyerAddressBookBean;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.bean.BuyerMemoBean;
import com.fd.fashion.buyer.bean.EmailInfoBean;
import com.fd.fashion.buyer.vo.BuyerVo;
import com.fd.util.PageInfo;
/** 买家接口 */
public interface IBuyerService {
	/**
	 * 增加买家
	 * 
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	public BuyerBean insertBuyerBean(BuyerBean buyer) throws Exception;
	
	/**
	 * 修改买家
	 * 
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	public int updateBuyerBean(BuyerBean buyer) throws Exception;
	
	/**
	 * 获取买家
	 * 
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	public List<BuyerBean> getBuyerBeans(BuyerBean buyer) throws Exception;
	
	/**
	 * 返回获取到的第一个买家
	 * 
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	public BuyerBean getBuyerBean(BuyerBean buyer) throws Exception;
	
	/**
	 * 获取买家
	 * 
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	public List<BuyerBean> getBuyerBeans(BuyerBean buyer, PageInfo pageInfo) throws Exception;
	
	/**获取买家列表
	 * @param buyer
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<BuyerBean> getBuyerList(BuyerBean buyer,PageInfo pageInfo) throws Exception;
	
	/**
	 * 删除买家
	 * 
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	public int deleteBuyerBean(BuyerBean buyer) throws Exception;
	
	/**
	 * 激活买家邮箱
	 * 
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public int activeBuyerEmail(String activeCode) throws Exception;
	
	/**
	 * 获取买家地址本
	 * 
	 * @param addressBook
	 * @return
	 * @throws Exception
	 */
	public BuyerAddressBookBean getBuyerAddressBook(BuyerAddressBookBean addressBook) throws Exception;
	
	/**
	 * 获取买家地址本
	 * 
	 * @param addressBook
	 * @return
	 * @throws Exception
	 */
	public List<BuyerAddressBookBean> getBuyerAddressBooks(BuyerAddressBookBean addressBook) throws Exception;
	
	/**
	 * 新增地址本
	 * 
	 * @param addressBook
	 * @return
	 * @throws Exception
	 */
	public BuyerAddressBookBean addBuyerAddressBook(BuyerAddressBookBean addressBook) throws Exception;
	
	/**
	 * 更新地址本
	 * 
	 * @param addressBook
	 * @return
	 * @throws Exception
	 */
	public int updateBuyerAddressBook(BuyerAddressBookBean addressBook) throws Exception;
	
	/**
	 * 清除缺省地址本
	 * 
	 * @param buyerId
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public int clearDefaultAddressBook(long buyerId) throws Exception;
	
	/**
	 * 设置缺省地址本
	 * 
	 * @param buyerId
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public int setDefaultAddressBook(long addressId) throws Exception;
	
	/**删除单个地址
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public Integer deleteAddressBook(long addressId)throws Exception;
	
	/**获取买家备注列表
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<BuyerMemoBean> getBuyerMemoList(long buyerId) throws Exception;
	
	/**添加买家备注信息
	 * @param buyerMemo
	 * @return
	 * @throws Exception
	 */
	public BuyerMemoBean insertBuyerMemo(BuyerMemoBean buyerMemo) throws Exception;
	
	/**获取买家邮件列表
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<EmailInfoBean> buyerEmailList(EmailInfoBean eamilBean,PageInfo pageInfo) throws Exception;
	
	/**获取某个用户的各种订单统计信息
	 * @param buyerId,flag(flag表示查询哪个类型统计数据1表示支付订单，2表示未支付订单，3表示退款订单)
	 * @return
	 * @throws Exception
	 */
	public BuyerVo getBuyerCountInfo(long buyerId,String flag) throws Exception;
	
	/**查询买家下单天数
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public Integer getBuyerCreateOrderDayCount(long buyerId) throws Exception;

}