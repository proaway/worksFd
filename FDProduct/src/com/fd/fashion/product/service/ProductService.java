package com.fd.fashion.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fd.dao.IBaseDao;
import com.fd.fashion.product.bean.ActivityBean;
import com.fd.fashion.product.bean.BuyerProductListBean;
import com.fd.fashion.product.bean.GiftsBean;
import com.fd.fashion.product.bean.GroupsBean;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.bean.ImageCatBean;
import com.fd.fashion.product.bean.PriceBean;
import com.fd.fashion.product.bean.PriceHistoryBean;
import com.fd.fashion.product.bean.ProdMoveMissionBean;
import com.fd.fashion.product.bean.ProductAttrBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.bean.ProductConfImgBean;
import com.fd.fashion.product.bean.ProductConfigBean;
import com.fd.fashion.product.bean.ProductImageBean;
import com.fd.fashion.product.bean.ProductStandardBean;
import com.fd.fashion.product.bean.StoceNoticeBean;
import com.fd.fashion.product.bean.StockoutBean;
import com.fd.fashion.product.vo.ProductCountVo;
import com.fd.fashion.product.vo.SearchAttrVo;
import com.fd.fashion.product.vo.SearchProductVo;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.util.PageInfo;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;

/**
 * 产品Service接口实现类
 * 
 * @author Longli
 * 
 */
@Component
@SuppressWarnings("unchecked")
public class ProductService implements IProductService {
	@Autowired
	@Qualifier("dao")
	private IBaseDao dao;

	/**
	 * 增加图片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public ImageBean insertImageBean(ImageBean image) throws Exception {
		dao.insertObj("insertImageBean", image);
		return image;
	}

	/**
	 * 修改图片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public int updateImageBean(ImageBean image) throws Exception {
		return dao.updateObj("updateImageBean", image);
	}

	/**
	 * 获取图片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getImageBean", expiration = 3600)
	public ImageBean getImageBean(@ParameterValueKeyProvider
	ImageBean image) throws Exception {
		// return dao.getAsList("getImageBean", image);
		return (ImageBean) dao.getAsObject("getImageBean", image);
	}

	/**
	 * 获取图片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public List<ImageBean> getImageBeans(ImageBean image, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getImageBeanCount", image);
		if (pageInfo != null) {
			pageInfo.setRecordCount(count == null ? 0 : count);
		}
		if (count != null && count > 0) {
			if (pageInfo != null) {
				return dao.getAsList("getImageBean", image, pageInfo);
			} else {
				return dao.getAsList("getImageBean", image);
			}
		}
		return null;
	}

	/**
	 * 过滤参数获取图片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public List<ImageBean> getImageBeansByStatus(ImageBean image,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getImageBeanCount", image);
		pageInfo.setRecordCount(count == null ? 0 : count);
		image.setPageInfo(pageInfo);
		if (count != null && count > 0) {
			List<ImageBean> list = dao.getAsList("getImageBeanByStatus", image);
			return list;
		}
		return null;
	}

	/**
	 * 删除图片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public int deleteImageBean(ImageBean image) throws Exception {
		return dao.deleteObj("deleteImageBean", image);
	}

	/**
	 * 增加价格历史
	 * 
	 * @param priceHistory
	 * @return
	 * @throws Exception
	 */
	public PriceHistoryBean insertPriceHistoryBean(PriceHistoryBean priceHistory)
			throws Exception {
		dao.insertObj("insertPriceHistoryBean", priceHistory);
		return priceHistory;
	}

	/**
	 * 修改价格历史
	 * 
	 * @param priceHistory
	 * @return
	 * @throws Exception
	 */
	public int updatePriceHistoryBean(PriceHistoryBean priceHistory)
			throws Exception {
		return dao.updateObj("updatePriceHistoryBean", priceHistory);
	}

	/**
	 * 获取价格历史
	 * 
	 * @param priceHistory
	 * @return
	 * @throws Exception
	 */
	public List<PriceHistoryBean> getPriceHistoryBeans(
			PriceHistoryBean priceHistory) throws Exception {
		return dao.getAsList("getPriceHistoryBean", priceHistory);
	}

	/**
	 * 获取价格历史
	 * 
	 * @param priceHistory
	 * @return
	 * @throws Exception
	 */
	public List<PriceHistoryBean> getPriceHistoryBeans(
			PriceHistoryBean priceHistory, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getPriceHistoryBeanCount",
				priceHistory);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getPriceHistoryBean", priceHistory, pageInfo);
		}
		return null;
	}

	/**
	 * 删除价格历史
	 * 
	 * @param priceHistory
	 * @return
	 * @throws Exception
	 */
	public int deletePriceHistoryBean(PriceHistoryBean priceHistory)
			throws Exception {
		return dao.deleteObj("deletePriceHistoryBean", priceHistory);
	}

	/**
	 * 增加产品价格
	 * 
	 * @param price
	 * @return
	 * @throws Exception
	 */
	public PriceBean insertPriceBean(PriceBean price) throws Exception {
		dao.insertObj("insertPriceBean", price);
		return price;
	}

	/**
	 * 修改产品价格
	 * 
	 * @param price
	 * @return
	 * @throws Exception
	 */
	public int updatePriceBean(PriceBean price) throws Exception {
		return dao.updateObj("updatePriceBean", price);
	}

	/**
	 * 获取产品价格
	 * 
	 * @param price
	 * @return
	 * @throws Exception
	 */
	public PriceBean getPriceBean(PriceBean price) throws Exception {
		return (PriceBean) dao.getAsObject("getPriceBean", price);
	}

	/**
	 * 获取产品价格
	 * 
	 * @param price
	 * @return
	 * @throws Exception
	 */
	public List<PriceBean> getPriceBeans(PriceBean price, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getPriceBeanCount", price);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getPriceBean", price, pageInfo);
		}
		return null;
	}

	/**
	 * 删除产品价格
	 * 
	 * @param price
	 * @return
	 * @throws Exception
	 */
	public int deletePriceBean(PriceBean price) throws Exception {
		return dao.deleteObj("deletePriceBean", price);
	}

	/**
	 * 增加产品属性
	 * 
	 * @param producattr
	 * @return
	 * @throws Exception
	 */
	public ProductAttrBean insertProductAttrBean(ProductAttrBean producattr)
			throws Exception {
		dao.insertObj("insertProductAttrBean", producattr);
		return producattr;
	}

	/**
	 * 修改产品属性
	 * 
	 * @param producattr
	 * @return
	 * @throws Exception
	 */
	public int updateProductAttrBean(ProductAttrBean producattr)
			throws Exception {
		return dao.updateObj("updateProductAttrBean", producattr);
	}

	/**
	 * 获取产品属性
	 * 
	 * @param producattr
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrBean> getProductAttrBeans(ProductAttrBean producattr)
			throws Exception {
		return dao.getAsList("getProductAttrBean", producattr);
	}

	/**
	 * 获取产品属性
	 * 
	 * @param producattr
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrBean> getProductAttrBeans(
			ProductAttrBean producattr, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getProductAttrBeanCount",
				producattr);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getProductAttrBean", producattr, pageInfo);
		}
		return null;
	}

	/**
	 * 删除产品属性
	 * 
	 * @param producattr
	 * @return
	 * @throws Exception
	 */
	public int deleteProductAttrBean(ProductAttrBean producattr)
			throws Exception {
		return dao.deleteObj("deleteProductAttrBean", producattr);
	}

	/**
	 * 增加产品配置
	 * 
	 * @param producconfig
	 * @return
	 * @throws Exception
	 */
	public ProductConfigBean insertProductConfigBean(
			ProductConfigBean producconfig) throws Exception {
		dao.insertObj("insertProductConfigBean", producconfig);
		return producconfig;
	}

	/**
	 * 修改产品配置
	 * 
	 * @param producconfig
	 * @return
	 * @throws Exception
	 */
	public int updateProductConfigBean(ProductConfigBean producconfig)
			throws Exception {
		return dao.updateObj("updateProductConfigBean", producconfig);
	}

	/**
	 * 获取产品配置
	 * 
	 * @param producconfig
	 * @return
	 * @throws Exception
	 */
	public ProductConfigBean getProductConfigBean(ProductConfigBean producconfig)
			throws Exception {
		return (ProductConfigBean) dao.getAsObject("getProductConfigBean",
				producconfig);
	}

	/**
	 * 获取产品配置
	 * 
	 * @param producconfig
	 * @return
	 * @throws Exception
	 */
	public List<ProductConfigBean> getProductConfigBeans(
			ProductConfigBean producconfig) throws Exception {
		return dao.getAsList("getProductConfigBean", producconfig);
	}

	/**
	 * 获取产品配置
	 * 
	 * @param producconfig
	 * @return
	 * @throws Exception
	 */
	public List<ProductConfigBean> getProductConfigBeans(
			ProductConfigBean producconfig, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getProductConfigBeanCount",
				producconfig);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao
					.getAsList("getProductConfigBean", producconfig, pageInfo);
		}
		return null;
	}

	/**
	 * 删除产品配置
	 * 
	 * @param producconfig
	 * @return
	 * @throws Exception
	 */
	public int deleteProductConfigBean(ProductConfigBean producconfig)
			throws Exception {
		return dao.deleteObj("deleteProductConfigBean", producconfig);
	}

	/**
	 * 增加产品配置自定义图片
	 * 
	 * @param producconfImg
	 * @return
	 * @throws Exception
	 */
	public ProductConfImgBean insertProductConfImgBean(
			ProductConfImgBean producconfImg) throws Exception {
		dao.insertObj("insertProductConfImgBean", producconfImg);
		return producconfImg;
	}

	/**
	 * 修改产品配置自定义图片
	 * 
	 * @param producconfImg
	 * @return
	 * @throws Exception
	 */
	public int updateProductConfImgBean(ProductConfImgBean producconfImg)
			throws Exception {
		return dao.updateObj("updateProductConfImgBean", producconfImg);
	}

	/**
	 * 获取产品配置自定义图片
	 * 
	 * @param producconfImg
	 * @return
	 * @throws Exception
	 */
	public ProductConfImgBean getProductConfImgBean(
			ProductConfImgBean producconfImg) throws Exception {
		return (ProductConfImgBean) dao.getAsObject("getProductConfImgBean",
				producconfImg);
	}

	/**
	 * 获取产品配置自定义图片
	 * 
	 * @param producconfImg
	 * @return
	 * @throws Exception
	 */
	public List<ProductConfImgBean> getProductConfImgBeans(
			ProductConfImgBean producconfImg) throws Exception {
		return dao.getAsList("getProductConfImgBean", producconfImg);
	}

	/**
	 * 获取产品配置自定义图片
	 * 
	 * @param producconfImg
	 * @return
	 * @throws Exception
	 */
	public List<ProductConfImgBean> getProductConfImgBeans(
			ProductConfImgBean producconfImg, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getProductConfImgBeanCount",
				producconfImg);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getProductConfImgBean", producconfImg,
					pageInfo);
		}
		return null;
	}

	/**
	 * 删除产品配置自定义图片
	 * 
	 * @param producconfImg
	 * @return
	 * @throws Exception
	 */
	public int deleteProductConfImgBean(ProductConfImgBean producconfImg)
			throws Exception {
		return dao.deleteObj("deleteProductConfImgBean", producconfImg);
	}

	/**
	 * 增加产品图片
	 * 
	 * @param producimage
	 * @return
	 * @throws Exception
	 */
	public ProductImageBean insertProductImageBean(ProductImageBean producimage)
			throws Exception {
		dao.insertObj("insertProductImageBean", producimage);
		return producimage;
	}

	/**
	 * 修改产品图片
	 * 
	 * @param producimage
	 * @return
	 * @throws Exception
	 */
	public int updateProductImageBean(ProductImageBean producimage)
			throws Exception {
		return dao.updateObj("updateProductImageBean", producimage);
	}

	/**
	 * 获取产品图片
	 * 
	 * @param producimage
	 * @return
	 * @throws Exception
	 */
	public List<ProductImageBean> getProductImageBeans(
			ProductImageBean producimage) throws Exception {
		return dao.getAsList("getProductImageBean", producimage);
	}

	/**
	 * 获取产品图片
	 * 
	 * @param producimage
	 * @return
	 * @throws Exception
	 */
	public List<ProductImageBean> getProductImageBeans(
			ProductImageBean producimage, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getProductImageBeanCount",
				producimage);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getProductImageBean", producimage, pageInfo);
		}
		return null;
	}

	/**
	 * 删除产品图片
	 * 
	 * @param producimage
	 * @return
	 * @throws Exception
	 */
	public int deleteProductImageBean(ProductImageBean producimage)
			throws Exception {
		return dao.deleteObj("deleteProductImageBean", producimage);
	}

	/**
	 * 增加产品
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public ProductBean insertProductBean(ProductBean product) throws Exception {
		Long productId = product.getProductId();
		if (productId != null && productId <= 0) {
			product.setProductId(null);
		}
		dao.insertObj("insertProductBean", product);
		if (productId != null && productId > 0) {
			product.setProductId(productId);
		}
		return product;
	}

	/**
	 * 修改产品
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public int updateProductBean(ProductBean product) throws Exception {
		return dao.updateObj("updateProductBean", product);
	}

	/**
	 * 获取产品
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> getProductBeans(ProductBean product)
			throws Exception {
		return dao.getAsList("getProductBean", product);
	}

	/**
	 * 获取产品
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> getProductBeans(ProductBean product,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getProductBeanCount",
				product);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getProductBean", product, pageInfo);
		}
		return null;
	}

	/**
	 * 删除产品
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public int deleteProductBean(ProductBean product) throws Exception {
		return dao.deleteObj("deleteProductBean", product);
	}

	/**
	 * 增加产品规格
	 * 
	 * @param producstandard
	 * @return
	 * @throws Exception
	 */
	public ProductStandardBean insertProductStandardBean(
			ProductStandardBean producstandard) throws Exception {
		dao.insertObj("insertProductStandardBean", producstandard);
		return producstandard;
	}

	/**
	 * 修改产品规格
	 * 
	 * @param producstandard
	 * @return
	 * @throws Exception
	 */
	public int updateProductStandardBean(ProductStandardBean producstandard)
			throws Exception {
		return dao.updateObj("updateProductStandardBean", producstandard);
	}

	/**
	 * 获取产品规格
	 * 
	 * @param producstandard
	 * @return
	 * @throws Exception
	 */
	public ProductStandardBean getProductStandardBean(
			ProductStandardBean producstandard) throws Exception {
		return (ProductStandardBean) dao.getAsObject("getProductStandardBean",
				producstandard);
	}

	/**
	 * 获取产品规格
	 * 
	 * @param producstandard
	 * @return
	 * @throws Exception
	 */
	public List<ProductStandardBean> getProductStandardBeans(
			ProductStandardBean producstandard) throws Exception {
		return dao.getAsList("getProductStandardBean", producstandard);
	}

	/**
	 * 获取产品规格
	 * 
	 * @param producstandard
	 * @return
	 * @throws Exception
	 */
	public List<ProductStandardBean> getProductStandardBeans(
			ProductStandardBean producstandard, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject(
				"getProductStandardBeanCount", producstandard);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getProductStandardBean", producstandard,
					pageInfo);
		}
		return null;
	}

	/**
	 * 删除产品规格
	 * 
	 * @param producstandard
	 * @return
	 * @throws Exception
	 */
	public int deleteProductStandardBean(ProductStandardBean producstandard)
			throws Exception {
		return dao.deleteObj("deleteProductStandardBean", producstandard);
	}

	/**
	 * 增加缺货登记
	 * 
	 * @param stoceNotice
	 * @return
	 * @throws Exception
	 */
	public StoceNoticeBean insertStoceNoticeBean(StoceNoticeBean stoceNotice)
			throws Exception {
		dao.insertObj("insertStoceNoticeBean", stoceNotice);
		return stoceNotice;
	}

	/**
	 * 修改缺货登记
	 * 
	 * @param stoceNotice
	 * @return
	 * @throws Exception
	 */
	public int updateStoceNoticeBean(StoceNoticeBean stoceNotice)
			throws Exception {
		return dao.updateObj("updateStoceNoticeBean", stoceNotice);
	}

	/**
	 * 获取缺货登记
	 * 
	 * @param stoceNotice
	 * @return
	 * @throws Exception
	 */
	public StoceNoticeBean getStoceNoticeBean(StoceNoticeBean stoceNotice)
			throws Exception {
		return (StoceNoticeBean) dao.getAsObject("getStoceNoticeBean",
				stoceNotice);
	}

	/**
	 * 获取缺货登记
	 * 
	 * @param stoceNotice
	 * @return
	 * @throws Exception
	 */
	public List<StoceNoticeBean> getStoceNoticeBeans(
			StoceNoticeBean stoceNotice, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getStoceNoticeBeanCount",
				stoceNotice);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getStoceNoticeBean", stoceNotice, pageInfo);
		}
		return null;
	}

	/**
	 * 删除缺货登记
	 * 
	 * @param stoceNotice
	 * @return
	 * @throws Exception
	 */
	public int deleteStoceNoticeBean(StoceNoticeBean stoceNotice)
			throws Exception {
		return dao.deleteObj("deleteStoceNoticeBean", stoceNotice);
	}

	/**
	 * 获取某个产品的详细信息
	 */
	public ProductBean getProductBean(ProductBean product) throws Exception {
		return (ProductBean) dao.getAsObject("getProductBean", product);
	}

	/**
	 * 通过规格条件查询库存数量
	 * 
	 * @param standardBean
	 * @return
	 * @throws Exception
	 */
	public Integer getStockNum(ProductStandardBean standardBean)
			throws Exception {
		return (Integer) dao.getAsObject("getStockCount", standardBean);
	}

	/**
	 * 增加自定义图片分类
	 * 
	 * @param tImageCat
	 * @return
	 * @throws Exception
	 */
	public ImageCatBean insertImageCatBean(ImageCatBean tImageCat)
			throws Exception {
		dao.insertObj("insertImageCatBean", tImageCat);
		return tImageCat;
	}

	/**
	 * 修改自定义图片分类
	 * 
	 * @param tImageCat
	 * @return
	 * @throws Exception
	 */
	public int updateImageCatBean(ImageCatBean tImageCat) throws Exception {
		return dao.updateObj("updateImageCatBean", tImageCat);
	}

	/**
	 * 获取自定义图片分类
	 * 
	 * @param tImageCat
	 * @return
	 * @throws Exception
	 */
	public List<ImageCatBean> getImageCatBeans(ImageCatBean tImageCat)
			throws Exception {
		return dao.getAsList("getImageCatBean", tImageCat);
	}

	/**
	 * 获取自定义图片分类
	 * 
	 * @param tImageCat
	 * @return
	 * @throws Exception
	 */
	public List<ImageCatBean> getImageCatBeans(ImageCatBean tImageCat,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getImageCatBeanCount",
				tImageCat);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getImageCatBean", tImageCat, pageInfo);
		}
		return null;
	}

	/**
	 * 删除自定义图片分类
	 * 
	 * @param tImageCat
	 * @return
	 * @throws Exception
	 */
	public int deleteImageCatBean(ImageCatBean tImageCat) throws Exception {
		return dao.deleteObj("deleteImageCatBean", tImageCat);
	}

	/**
	 * 更改图片CatId为未分组
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public int updateImageCatInfo(ImageCatBean icb) throws Exception {
		return dao.updateObj("updateImageCatId", icb);
	}

	/**
	 * 获取某个用户所用空间大小
	 * 
	 * @param sellerId
	 * @return
	 * @throws Exception
	 */
	public long getImageSize(long sellerId) throws Exception {
		Long size = (Long) dao.getAsObject("selectImageSize", sellerId);
		return size == null ? 0 : size / 1024 / 1024;
	}

	/**
	 * 添加买家用户喜欢的产品
	 * 
	 * @param buyerProductList
	 * @return
	 * @throws Exception
	 */
	public BuyerProductListBean insertBuyerProductListBean(
			BuyerProductListBean buyerProductList) throws Exception {
		dao.insertObj("insertBuyerProductListBean", buyerProductList);
		return buyerProductList;
	}

	/**
	 * 通过BUYERID,PRODUCTID查询买家用户喜欢的产品
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public BuyerProductListBean getBuyerProductListBean(
			BuyerProductListBean buyerProductList) throws Exception {
		return (BuyerProductListBean) dao.getAsObject(
				"getBuyerProductListBean", buyerProductList);
	}

	/**
	 * 通过BUYERID,PRODUCTID删除买家用户喜欢的产品
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public Integer deleteBuyerProductListBean(
			BuyerProductListBean buyerProductList) throws Exception {
		return dao.deleteObj("deleteBuyerProductListBean", buyerProductList);
	}

	/**
	 * 根据关键词搜索产品
	 * 
	 * @param key,pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> getSearchProductBeans(
			SearchProductVo searchProductVo, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getSearchProductCount",
				searchProductVo);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			searchProductVo.setPageInfo(pageInfo);
			return dao.getAsList("getProductBeanByKey", searchProductVo);
		}
		return null;
	}

	/**
	 * 根据关键词搜索产品分类聚合
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<CustomCategoryBean> getSearchProductCat(
			SearchProductVo searchProductVo) throws Exception {
		return dao.getAsList("getSearchProductCatByKey", searchProductVo);
	}

	/**
	 * 返回产品搜索配置属性聚合
	 * 
	 * @param searchProductVo
	 * @return
	 * @throws Exception
	 */
	public List<SearchAttrVo> getSearchConfigs(SearchProductVo searchProductVo)
			throws Exception {
		return dao.getAsList("getSearchProductConfCount", searchProductVo);
	}

	/**
	 * 返回产品搜索普通属性聚合
	 * 
	 * @param searchProductVo
	 * @return
	 * @throws Exception
	 */
	public List<SearchAttrVo> getSearchAttributes(
			SearchProductVo searchProductVo) throws Exception {
		return dao.getAsList("getSearchProductAttrCount", searchProductVo);
	}

	/**
	 * 获取某个买家的favorite产品
	 * 
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> getBuyerFavorite(
			BuyerProductListBean buyerProductListBean) throws Exception {
		return dao.getAsList("selectFavoriteProduct", buyerProductListBean);
	}

	/**
	 * 获取某个买家的favorite产品数量
	 * 
	 * @param buyerProductListBean
	 * @return
	 * @throws Exception
	 */
	public Integer getBuyerFavoriteCount(
			BuyerProductListBean buyerProductListBean) throws Exception {
		return (Integer) dao.getAsObject("selectFavoriteProductCount",
				buyerProductListBean);
	}

	/**
	 * 获取买家购买过产品
	 * 
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public Integer getBuyerBuyProductCount(long buyerId) throws Exception {
		return (Integer) dao.getAsObject("getViewerProductsCount", buyerId);
	}

	/**
	 * 获取买家购买过产品
	 * 
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> getBuyerBuyProducts(long buyerId, PageInfo pageInfo)
			throws Exception {
		BuyerProductListBean p = new BuyerProductListBean();
		p.setBuyerId(buyerId);
		p.setPageInfo(pageInfo);
		return dao.getAsList("getViewerProducts", p);
	}

	/**
	 * 获取自定义分类产品总数
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public int getCustomCatProductCount(String catId) throws Exception {
		return (Integer) dao.getAsObject("getCustomCatProductCount", catId);
	}

	/**
	 * 获取产品状态数量统计
	 * 
	 * @return
	 */
	public List<ProductCountVo> getProductStatusCount() throws Exception {
		return dao.getAsList("getProductStatusCount");
	}

	/**
	 * 获取分类下热销产品
	 * 
	 * @return
	 */
	public List<ProductBean> getHotProducts(ProductBean product)
			throws Exception {
		return dao.getAsList("getHotSaleProducts", product);
	}

	/**
	 * 增加活动
	 * 
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public ActivityBean insertActivityBean(ActivityBean activity)
			throws Exception {
		dao.insertObj("insertActivityBean", activity);
		return activity;
	}

	/**
	 * 修改活动
	 * 
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public int updateActivityBean(ActivityBean activity) throws Exception {
		return dao.updateObj("updateActivityBean", activity);
	}

	/**
	 * 获取活动
	 * 
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public ActivityBean getActivityBean(ActivityBean activity) throws Exception {
		return (ActivityBean) dao.getAsObject("getActivityBean", activity);
	}

	/**
	 * 获取活动
	 * 
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public List<ActivityBean> getActivityBeans(ActivityBean activity)
			throws Exception {
		return dao.getAsList("getActivityBean", activity);
	}

	/**
	 * 获取活动
	 * 
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public List<ActivityBean> getActivityBeans(ActivityBean activity,
			PageInfo pageInfo) throws Exception {
		if (pageInfo != null) {
			Integer count = (Integer) dao.getAsObject("getActivityBeanCount",
					activity);
			pageInfo.setRecordCount(count == null ? 0 : count);
			if (count != null && count > 0) {
				return dao.getAsList("getActivityBean", activity, pageInfo);
			}
		} else {
			return dao.getAsList("getActivityBean", activity);
		}
		return null;
	}

	/**
	 * 删除活动
	 * 
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public int deleteActivityBean(ActivityBean activity) throws Exception {
		return dao.deleteObj("deleteActivityBean", activity);
	}

	/**
	 * 增加赠品
	 * 
	 * @param gifts
	 * @return
	 * @throws Exception
	 */
	public GiftsBean insertGiftsBean(GiftsBean gifts) throws Exception {
		dao.insertObj("insertGiftsBean", gifts);
		return gifts;
	}

	/**
	 * 修改赠品
	 * 
	 * @param gifts
	 * @return
	 * @throws Exception
	 */
	public int updateGiftsBean(GiftsBean gifts) throws Exception {
		return dao.updateObj("updateGiftsBean", gifts);
	}

	/**
	 * 获取赠品
	 * 
	 * @param gifts
	 * @return
	 * @throws Exception
	 */
	public GiftsBean getGiftsBean(GiftsBean gifts) throws Exception {
		return (GiftsBean) dao.getAsObject("getGiftsBean", gifts);
	}

	/**
	 * 获取赠品
	 * 
	 * @param gifts
	 * @return
	 * @throws Exception
	 */
	public List<GiftsBean> getGiftsBeans(GiftsBean gifts) throws Exception {
		return dao.getAsList("getGiftsBean", gifts);
	}

	/**
	 * 获取赠品
	 * 
	 * @param gifts
	 * @return
	 * @throws Exception
	 */
	public List<GiftsBean> getGiftsBeans(GiftsBean gifts, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getGiftsBeanCount", gifts);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getGiftsBean", gifts, pageInfo);
		}
		return null;
	}

	/**
	 * 删除赠品
	 * 
	 * @param gifts
	 * @return
	 * @throws Exception
	 */
	public int deleteGiftsBean(GiftsBean gifts) throws Exception {
		return dao.deleteObj("deleteGiftsBean", gifts);
	}

	/**
	 * 增加组合销售
	 * 
	 * @param groups
	 * @return
	 * @throws Exception
	 */
	public GroupsBean insertGroupsBean(GroupsBean groups) throws Exception {
		dao.insertObj("insertGroupsBean", groups);
		return groups;
	}

	/**
	 * 修改组合销售
	 * 
	 * @param groups
	 * @return
	 * @throws Exception
	 */
	public int updateGroupsBean(GroupsBean groups) throws Exception {
		return dao.updateObj("updateGroupsBean", groups);
	}

	/**
	 * 获取组合销售
	 * 
	 * @param groups
	 * @return
	 * @throws Exception
	 */
	public GroupsBean getGroupsBean(GroupsBean groups) throws Exception {
		return (GroupsBean) dao.getAsObject("getGroupsBean", groups);
	}

	/**
	 * 获取组合销售
	 * 
	 * @param groups
	 * @return
	 * @throws Exception
	 */
	public List<GroupsBean> getGroupsBeans(GroupsBean groups) throws Exception {
		return dao.getAsList("getGroupsBean", groups);
	}

	/**
	 * 获取组合销售
	 * 
	 * @param groups
	 * @return
	 * @throws Exception
	 */
	public List<GroupsBean> getGroupsBeans(GroupsBean groups, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getGroupsBeanCount", groups);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getGroupsBean", groups, pageInfo);
		}
		return null;
	}

	/**
	 * 删除组合销售
	 * 
	 * @param groups
	 * @return
	 * @throws Exception
	 */
	public int deleteGroupsBean(GroupsBean groups) throws Exception {
		return dao.deleteObj("deleteGroupsBean", groups);
	}

	/**
	 * 查询最近15天的热销产品
	 * 
	 * @param searchProductVo
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> getHotProductsByTime(
			SearchProductVo searchProductVo) throws Exception {
		return dao.getAsList("getHotProductByRecent", searchProductVo);
	}

	/**
	 * 增加产品搬家任务申请
	 * 
	 * @param prodMoveMission
	 * @return
	 * @throws Exception
	 */
	public ProdMoveMissionBean insertProdMoveMissionBean(
			ProdMoveMissionBean prodMoveMission) throws Exception {
		dao.insertObj("insertProdMoveMissionBean", prodMoveMission);
		return prodMoveMission;
	}

	/**
	 * 修改产品搬家任务申请
	 * 
	 * @param prodMoveMission
	 * @return
	 * @throws Exception
	 */
	public int updateProdMoveMissionBean(ProdMoveMissionBean prodMoveMission)
			throws Exception {
		return dao.updateObj("updateProdMoveMissionBean", prodMoveMission);
	}

	/**
	 * 获取产品搬家任务申请
	 * 
	 * @param prodMoveMission
	 * @return
	 * @throws Exception
	 */
	public ProdMoveMissionBean getProdMoveMissionBean(
			ProdMoveMissionBean prodMoveMission) throws Exception {
		return (ProdMoveMissionBean) dao.getAsObject("getProdMoveMissionBean",
				prodMoveMission);
	}

	/**
	 * 获取产品搬家任务申请
	 * 
	 * @param prodMoveMission
	 * @return
	 * @throws Exception
	 */
	public List<ProdMoveMissionBean> getProdMoveMissionBeans(
			ProdMoveMissionBean prodMoveMission) throws Exception {
		return dao.getAsList("getProdMoveMissionBean", prodMoveMission);
	}

	/**
	 * 获取产品搬家任务申请
	 * 
	 * @param prodMoveMission
	 * @return
	 * @throws Exception
	 */
	public List<ProdMoveMissionBean> getProdMoveMissionBeans(
			ProdMoveMissionBean prodMoveMission, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject(
				"getProdMoveMissionBeanCount", prodMoveMission);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getProdMoveMissionBean", prodMoveMission,
					pageInfo);
		}
		return null;
	}

	/**
	 * 删除产品搬家任务申请
	 * 
	 * @param prodMoveMission
	 * @return
	 * @throws Exception
	 */
	public int deleteProdMoveMissionBean(ProdMoveMissionBean prodMoveMission)
			throws Exception {
		return dao.deleteObj("deleteProdMoveMissionBean", prodMoveMission);
	}

	/**
	 * 增加缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public StockoutBean insertStockoutBean(StockoutBean stockout)
			throws Exception {
		dao.insertObj("insertStockoutBean", stockout);
		return stockout;
	}

	/**
	 * 修改缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public int updateStockoutBean(StockoutBean stockout) throws Exception {
		return dao.updateObj("updateStockoutBean", stockout);
	}

	/**
	 * 获取缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public StockoutBean getStockoutBean(StockoutBean stockout) throws Exception {
		return (StockoutBean) dao.getAsObject("getStockoutBean", stockout);
	}

	/**
	 * 获取缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public List<StockoutBean> getStockoutBeans(StockoutBean stockout)
			throws Exception {
		return dao.getAsList("getStockoutBean", stockout);
	}

	/**
	 * 获取缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public List<StockoutBean> getStockoutBeans(StockoutBean stockout,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getStockoutBeanCount",
				stockout);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getStockoutBean", stockout, pageInfo);
		}
		return null;
	}

	/**
	 * 删除缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public int deleteStockoutBean(StockoutBean stockout) throws Exception {
		return dao.deleteObj("deleteStockoutBean", stockout);
	}
}