package com.fd.fashion.product.manager;

import com.fd.fashion.product.bean.PriceBean;
import com.fd.fashion.product.bean.ProductAttrBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.bean.ProductConfImgBean;
import com.fd.fashion.product.bean.ProductConfigBean;
import com.fd.fashion.product.bean.ProductImageBean;
import com.fd.fashion.product.bean.ProductStandardBean;
import com.fd.fashion.product.vo.ProductUploadVo;


/**
 * @CreateDate: 2013-3-26 下午08:57:44
 * @author ZhouRongyu
 * @Description: Product上传流程
 * 
 */
public interface IProductUploadManager {
	/**
	 * 上传产品提交方法入口
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public int addProductInfo(ProductUploadVo productUpload) throws Exception;
	
	/**
	 * 上传产品图片信息
	 * 
	 * @param pib
	 * @return
	 * @throws Exception
	 */
	public void addProductImages(ProductImageBean pib) throws Exception;
	
	/**
	 * 添加产品Attr属性
	 * 
	 * @param pab
	 * @return
	 * @throws Exception
	 */
	public void addProductAttr(ProductAttrBean pab) throws Exception;
	
	/**
	 * 添加产品Config属性
	 * 
	 * @param pcb
	 * @return
	 * @throws Exception
	 */
	public void addProductConfig(ProductConfigBean pcb) throws Exception;
	
	/**
	 * 添加产品Standard属性
	 * 
	 * @param psb
	 * @return
	 * @throws Exception
	 */
	public void addProductStandard(ProductStandardBean psb) throws Exception;
	
	/**
	 * 添加产品批发折扣价格
	 * 
	 * @param priceBean
	 * @return
	 * @throws Exception
	 */
	public void addProductPrice(PriceBean price) throws Exception;
	
	/**
	 * 添加产品ConfImg
	 * 
	 * @param priceBean
	 * @return
	 * @throws Exception
	 */
	public void addProductConfImg(ProductConfImgBean productConfImg) throws Exception;
	
	/**
	 * 更新上传产品
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public int updateProductInfo(ProductUploadVo productUpload, ProductBean product) throws Exception;
	
	/**
	 * 删除原始产品信息
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public int deleteProductInfo(Long productId) throws Exception;
}
