/**
 * IShippingManager.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.seller.manager;

import java.util.List;

import com.fd.fashion.seller.bean.ShippingInfoBean;
import com.fd.fashion.seller.vo.ResultRateVo;
import com.fd.fashion.seller.vo.ShippingDetailVo;
import com.fd.fashion.seller.vo.ShippingRateVo;
import com.fd.fashion.seller.vo.ShippingTemplateVo;

/**
 * @CreateDate:  2013-3-15 下午05:30:02 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public interface IShippingManager {
	
	/**
	 * 得到运费计算结果
	 * 
	 * @param shippingRateVo
	 *            将运算条件封装为一个Vo作为传入参数
	 * @return
	 * @throws Exception
	 */
	public List<ResultRateVo> getShippingCalculateResult(ShippingRateVo shippingRateVo) throws Exception;

	/**
	 * 获取所有的运费模版
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ShippingInfoBean> getShippingInfoBeans() throws Exception;
	
	/**
	 * 获得所有物流方式
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ShippingTemplateVo> getShippingInfos() throws Exception;
	
	/**
	 * 增加物流方式
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public ShippingInfoBean addShippingInfo(ShippingInfoBean shippingInfo) throws Exception;
	
	/**
	 * 获取单个物流模版
	 * 
	 * @param shippingInfoId
	 * @return
	 * @throws Exception
	 */
	public ShippingInfoBean getShippingInfo(long shippingInfoId) throws Exception;
	
	/**
	 * 删除物流模版
	 * 
	 * @param shippingInfoIds
	 * @return
	 * @throws Exception
	 */
	public int deleteShippingInfos(long[] shippingInfoIds) throws Exception;
	
	/**
	 * 更新物流方式
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public ShippingInfoBean updateShippingInfo(ShippingInfoBean shippingInfo) throws Exception;
	
	/**
	 * 获取物流明细
	 * 
	 * @param weight 重量，单位kg
	 * @param length 长，单位cm
	 * @param width 宽，单位cm
	 * @param height 高，单位cm
	 * @return
	 * @throws Exception
	 */
	public List<ShippingDetailVo> getShippingDetails(double weight, int length, int width, int height, String country) throws Exception;
}
