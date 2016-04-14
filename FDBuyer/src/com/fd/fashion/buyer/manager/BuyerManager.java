package com.fd.fashion.buyer.manager;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fd.fashion.buyer.bean.BuyerAddressBookBean;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.bean.BuyerMemoBean;
import com.fd.fashion.buyer.bean.EmailInfoBean;
import com.fd.fashion.buyer.service.IBuyerService;
import com.fd.fashion.buyer.vo.BuyerVo;
import com.fd.fashion.dict.service.IDictService;
import com.fd.util.DESadeUtil;
import com.fd.util.MD5Util;
import com.fd.util.PageInfo;
import com.fd.util.RewriteUtil;
import com.fd.util.StringUtil;
import com.fd.util.WebPropUtil;

/**
 * @CreateDate: 2013-3-14 下午01:25:38
 * @author Longli
 * @Description: 买家操作接口实现类
 * 
 */
@Transactional(rollbackFor=Exception.class)
@Component
public class BuyerManager implements IBuyerManager {
	@Autowired
	@Qualifier("buyerService")
	IBuyerService buyerService;

	@Autowired
	@Qualifier("dictService")
	IDictService dictService;
	
	/**
	 * 买家注册
	 * 
	 * @param buyer 
	 * @return 成功返回1，失败返回0
	 * @throws Exception
	 */
	public int register(BuyerBean buyer) throws Exception {
		if (StringUtils.isNotEmpty(buyer.getPassword())) {
			buyer.setPassword(DESadeUtil.encryptMode(buyer.getPassword()));
		}
		String code = MD5Util.calcMD5(Math.random()*10 + StringUtil.getDateString(new Date(),"yyyyMMddHHmmssSSS" + Math.random()*10));
		buyer.setActiveCode(code);
		buyerService.insertBuyerBean(buyer);
		if (buyer.getBuyerId() > 0) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * 用email获取买家信息，一般用于判断email重复
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public BuyerBean getBuyerUseEmail(String email) throws Exception {
		if (StringUtils.isNotEmpty(email)) {
			BuyerBean buyer = new BuyerBean();
			buyer.setEmail(email);
			return buyerService.getBuyerBean(buyer);
		}
		return null;
	}
	
	/**
	 * 用userId获取买家信息，一般用于判断userId重复
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public BuyerBean getBuyerUseUserId(String userId) throws Exception {
		if (StringUtils.isNotEmpty(userId)) {
			BuyerBean buyer = new BuyerBean();
			buyer.setUserId(userId);
			return buyerService.getBuyerBean(buyer);
		}
		return null;
	}
	
	/**通过buyerid查询buyer信息
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public BuyerBean getBuyerByBuyerId(Long buyerId) throws Exception{
		if (buyerId!=null && buyerId>0 ) {
			BuyerBean buyer = new BuyerBean();
			buyer.setBuyerId(buyerId);
			buyer = buyerService.getBuyerBean(buyer);
			if (buyer != null) {
				if (StringUtils.isEmpty(buyer.getImageUrl())) {
					buyer.setImageUrl(new WebPropUtil().getProperty("buyer.default.imageurl"));
				} else {
					buyer.setImageUrl(RewriteUtil.getImageUrl(buyer.getImageUrl(),"m"));
				}
			}
			return buyer;
		}
		return null;
	}
	
	/**
	 * 用户登录，成功返回buyer对象， 失败返回空
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public BuyerBean login(String email, String password) throws Exception {
		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
			return null;
		}
		BuyerBean buyer = new BuyerBean();
		buyer.setEmail(email);
		buyer.setPassword(DESadeUtil.encryptMode(password));
		return buyerService.getBuyerBean(buyer);
	}
	
	/**
	 * 买家激活邮箱
	 * 
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public int activeBuyerEmail(String activeCode) throws Exception {
		if (StringUtils.isNotEmpty(activeCode)) {
			BuyerBean buyer = new BuyerBean();
			buyer.setActiveCode(activeCode);
			buyer = buyerService.getBuyerBean(buyer);
			if (buyer == null) {
				return 0;
			}
			buyer.setEmailActive(1);
			
			return buyerService.updateBuyerBean(buyer);
		}
		return 0;
	}
	
	/**
	 * 获取买家全部地址本
	 * 
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<BuyerAddressBookBean> getBuyerAddressBooks(long buyerId) throws Exception {
		BuyerAddressBookBean addressBook = new BuyerAddressBookBean();
		addressBook.setBuyerId(buyerId);
		return buyerService.getBuyerAddressBooks(addressBook);
	}
	
	/**
	 * 获取买家单个地址
	 * 
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public BuyerAddressBookBean getBuyerAddressBook(long addressId) throws Exception {
		BuyerAddressBookBean addressBook = new BuyerAddressBookBean();
		addressBook.setAddressId(addressId);
		addressBook = buyerService.getBuyerAddressBook(addressBook);
		return addressBook;
	}
	
	/**
	 * 新增地址本
	 * 
	 * @param addressBook
	 * @return
	 * @throws Exception
	 */
	public BuyerAddressBookBean addBuyerAddressBook(BuyerAddressBookBean addressBook) throws Exception {
		return buyerService.addBuyerAddressBook(addressBook);
	}
	
	/**
	 * 更新地址本
	 * 
	 * @param addressBook
	 * @return
	 * @throws Exception
	 */
	public int updateBuyerAddressBook(BuyerAddressBookBean addressBook) throws Exception {
		return buyerService.updateBuyerAddressBook(addressBook);
	}
	
	/**
	 * 设置缺省地址本
	 * 
	 * @param buyerId
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public int setDefaultAddressBook(long buyerId, long addressId) throws Exception {
		buyerService.clearDefaultAddressBook(buyerId);
		return buyerService.setDefaultAddressBook(addressId);
	}
	
	/**删除单个地址
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public Integer deleteAddressBook(long addressId)throws Exception{
		return buyerService.deleteAddressBook(addressId);
	}
	
	/**
	 * 注册买家同时生成地址本
	 * 
	 * @param buyer
	 * @param address
	 * @return
	 * @throws Exception
	 */
	public BuyerBean registeBuyer(BuyerBean buyer, BuyerAddressBookBean address) throws Exception {
		if (StringUtils.isNotEmpty(buyer.getPassword())) {
			buyer.setPassword(DESadeUtil.encryptMode(buyer.getPassword()));
		}
		String code = StringUtil.getRandomStr();
		buyer.setActiveCode(code);
		buyerService.insertBuyerBean(buyer);
		address.setBuyerId(buyer.getBuyerId());
		buyerService.addBuyerAddressBook(address);
		return buyer;
	}
	
	/**更新buyer信息
	 * @param buyer
	 * @return
	 * @throws Exception
	 */
	public Integer updateBuyer(BuyerBean buyer)throws Exception{
		return buyerService.updateBuyerBean(buyer);
	}
	
	/**批量修改买家信息(批量激活与冻结)
	 * @return
	 * @throws Exception
	 */
	public Integer updateBuyerBatch(List<BuyerBean> buyers) throws Exception{
		int count = 0;
		if(buyers!=null && buyers.size()>0){
			for(BuyerBean buyer :buyers){
				Integer index = buyerService.updateBuyerBean(buyer);
				if(index>0){
					count = count+index.intValue();
				}
			}
		}
		return count;
	}


	/**获取买家列表
	 * @param buyerBean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<BuyerBean> getBuyerList(BuyerBean buyerBean,PageInfo pageInfo) throws Exception{
		return buyerService.getBuyerList(buyerBean, pageInfo);
	}
	
	/**获取买家备注列表
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<BuyerMemoBean> getBuyerMemoList(long buyerId) throws Exception{
		return buyerService.getBuyerMemoList(buyerId);
	}
	
	/**添加买家备注信息
	 * @param buyerMemo
	 * @return
	 * @throws Exception
	 */
	public BuyerMemoBean insertBuyerMemo(BuyerMemoBean buyerMemo) throws Exception{
		return buyerService.insertBuyerMemo(buyerMemo);
	}
	
	/**获取买家邮件列表
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<EmailInfoBean> buyerEmailList(EmailInfoBean emailBean,PageInfo pageInfo) throws Exception{
		return buyerService.buyerEmailList(emailBean, pageInfo);
	}
	
	/**获取某个用户的各种订单统计信息
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public BuyerVo getBuyerCountInfo(long buyerId) throws Exception{
		BuyerVo buyerVo = new BuyerVo();
		BuyerVo bv = new BuyerVo();
		bv = buyerService.getBuyerCountInfo(buyerId, String.valueOf(1));
		if(bv!=null){
			buyerVo.setPaymentOrderAverage(bv.getPaymentOrderAverage()!=null && bv.getPaymentOrderAverage()>0?bv.getPaymentOrderAverage():0);
			buyerVo.setPaymentOrderCount(bv.getPaymentOrderCount()!=null && bv.getPaymentOrderCount()>0?bv.getPaymentOrderCount():0);
			buyerVo.setPaymentOrderTotal(bv.getPaymentOrderTotal()!=null && bv.getPaymentOrderTotal()>0?bv.getPaymentOrderTotal():0);
		}
		bv = new BuyerVo();
		bv =  buyerService.getBuyerCountInfo(buyerId,String.valueOf(2));
		if(bv!=null){
			buyerVo.setWaitForPayOrderCount(bv.getWaitForPayOrderCount()!=null && bv.getWaitForPayOrderCount()>0?bv.getWaitForPayOrderCount():0);
			buyerVo.setWaitForPayOrderTotal(bv.getWaitForPayOrderTotal()!=null &&bv.getWaitForPayOrderTotal()>0?bv.getWaitForPayOrderTotal():0);
		}
		bv = new BuyerVo();
		bv = buyerService.getBuyerCountInfo(buyerId, String.valueOf(3));
		if(bv!=null){
			buyerVo.setRefundOrderCount(bv.getRefundOrderCount()!=null &&bv.getRefundOrderCount()>0?bv.getRefundOrderCount():0);
			buyerVo.setRefundOrderTotal(bv.getRefundOrderTotal()!=null &&bv.getRefundOrderTotal()>0?bv.getRefundOrderTotal():0);
		}
		if(buyerVo!=null){
			return buyerVo;
		}
		return null;
	}
	
	/**查询买家下单天数
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public Integer getBuyerCreateOrderDayCount(long buyerId) throws Exception{
		return buyerService.getBuyerCreateOrderDayCount(buyerId);
	}
}
