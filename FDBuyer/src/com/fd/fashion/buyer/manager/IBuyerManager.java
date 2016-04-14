package com.fd.fashion.buyer.manager;

import java.util.List;

import com.fd.fashion.buyer.bean.BuyerAddressBookBean;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.bean.BuyerMemoBean;
import com.fd.fashion.buyer.bean.EmailInfoBean;
import com.fd.fashion.buyer.vo.BuyerVo;
import com.fd.util.PageInfo;

/**
 * @CreateDate: 2013-3-14 下午01:25:07
 * @author Longli
 * @Description: 买家操作接口
 * 
 */
public interface IBuyerManager {
	/**
	 * 买家登录
	 * 
	 * @param buyer 
	 * @return 成功返回1，失败返回0
	 * @throws Exception
	 */
	public int register(BuyerBean buyer) throws Exception;
	
	/**
	 * 用email获取买家信息，一般用于判断email重复
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public BuyerBean getBuyerUseEmail(String email) throws Exception;
	
	/**
	 * 用userId获取买家信息，一般用于判断userId重复
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public BuyerBean getBuyerUseUserId(String userId) throws Exception;
	
	/**通过buyerid查询buyer信息
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public BuyerBean getBuyerByBuyerId(Long buyerId) throws Exception;
	
	/**
	 * 用户登录，成功返回buyer对象， 失败返回空
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public BuyerBean login(String email, String password) throws Exception;
	
	/**
	 * 买家激活邮箱
	 * 
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public int activeBuyerEmail(String activeCode) throws Exception;
	
	/**
	 * 获取买家全部地址本
	 * 
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<BuyerAddressBookBean> getBuyerAddressBooks(long buyerId) throws Exception;
	
	/**
	 * 获取买家单个地址
	 * 
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public BuyerAddressBookBean getBuyerAddressBook(long addressId) throws Exception;
	
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
	 * 设置缺省地址本
	 * 
	 * @param buyerId
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public int setDefaultAddressBook(long buyerId, long addressId) throws Exception;
	
	/**删除单个地址
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public Integer deleteAddressBook(long addressId)throws Exception;
	
	/**
	 * 注册买家同时生成地址本
	 * 
	 * @param buyer
	 * @param address
	 * @return
	 * @throws Exception
	 */
	public BuyerBean registeBuyer(BuyerBean buyer, BuyerAddressBookBean address) throws Exception;
	
	/**更新buyer信息
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	public Integer updateBuyer(BuyerBean buyer)throws Exception;
	
	/**获取买家列表
	 * @return
	 * @throws Exception
	 */
	public List<BuyerBean> getBuyerList(BuyerBean buyerBean,PageInfo pageInfo) throws Exception;
	
	/**批量修改买家信息(批量激活与冻结)
	 * @return
	 * @throws Exception
	 */
	public Integer updateBuyerBatch(List<BuyerBean> buyers) throws Exception;
	
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
	public List<EmailInfoBean> buyerEmailList(EmailInfoBean emailBean,PageInfo pageInfo) throws Exception;
	
	/**获取某个用户的各种订单统计信息
	 * @param  buyerId,flag(flag表示查询哪个类型统计数据1表示支付订单，2表示未支付订单，3表示退款订单)
	 * @return
	 * @throws Exception
	 */
	public BuyerVo getBuyerCountInfo(long buyerId) throws Exception;
	
	/**查询买家下单天数
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public Integer getBuyerCreateOrderDayCount(long buyerId) throws Exception;
}
