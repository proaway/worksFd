package com.fd.fashion.product.service;

import java.util.List;

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

/**
 * 产品Service接口
 * 
 * @author Longli
 * 
 */
public interface IProductService {
	/**
	 * 增加图片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public ImageBean insertImageBean(ImageBean image) throws Exception;

	/**
	 * 修改图片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public int updateImageBean(ImageBean image) throws Exception;

	/**
	 * 获取图片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public ImageBean getImageBean(ImageBean image) throws Exception;

	/**
	 * 获取图片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public List<ImageBean> getImageBeans(ImageBean image, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除图片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public int deleteImageBean(ImageBean image) throws Exception;

	/**
	 * 增加价格历史
	 * 
	 * @param priceHistory
	 * @return
	 * @throws Exception
	 */
	public PriceHistoryBean insertPriceHistoryBean(PriceHistoryBean priceHistory)
			throws Exception;

	/**
	 * 修改价格历史
	 * 
	 * @param priceHistory
	 * @return
	 * @throws Exception
	 */
	public int updatePriceHistoryBean(PriceHistoryBean priceHistory)
			throws Exception;

	/**
	 * 获取价格历史
	 * 
	 * @param priceHistory
	 * @return
	 * @throws Exception
	 */
	public List<PriceHistoryBean> getPriceHistoryBeans(
			PriceHistoryBean priceHistory) throws Exception;

	/**
	 * 获取价格历史
	 * 
	 * @param priceHistory
	 * @return
	 * @throws Exception
	 */
	public List<PriceHistoryBean> getPriceHistoryBeans(
			PriceHistoryBean priceHistory, PageInfo pageInfo) throws Exception;

	/**
	 * 删除价格历史
	 * 
	 * @param priceHistory
	 * @return
	 * @throws Exception
	 */
	public int deletePriceHistoryBean(PriceHistoryBean priceHistory)
			throws Exception;

	/**
	 * 增加产品价格
	 * 
	 * @param price
	 * @return
	 * @throws Exception
	 */
	public PriceBean insertPriceBean(PriceBean price) throws Exception;

	/**
	 * 修改产品价格
	 * 
	 * @param price
	 * @return
	 * @throws Exception
	 */
	public int updatePriceBean(PriceBean price) throws Exception;

	/**
	 * 获取产品价格
	 * 
	 * @param price
	 * @return
	 * @throws Exception
	 */
	public PriceBean getPriceBean(PriceBean price) throws Exception;

	/**
	 * 获取产品价格
	 * 
	 * @param price
	 * @return
	 * @throws Exception
	 */
	public List<PriceBean> getPriceBeans(PriceBean price, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除产品价格
	 * 
	 * @param price
	 * @return
	 * @throws Exception
	 */
	public int deletePriceBean(PriceBean price) throws Exception;

	/**
	 * 增加产品属性
	 * 
	 * @param producattr
	 * @return
	 * @throws Exception
	 */
	public ProductAttrBean insertProductAttrBean(ProductAttrBean producattr)
			throws Exception;

	/**
	 * 修改产品属性
	 * 
	 * @param producattr
	 * @return
	 * @throws Exception
	 */
	public int updateProductAttrBean(ProductAttrBean producattr)
			throws Exception;

	/**
	 * 获取产品属性
	 * 
	 * @param producattr
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrBean> getProductAttrBeans(ProductAttrBean producattr)
			throws Exception;

	/**
	 * 获取产品属性
	 * 
	 * @param producattr
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrBean> getProductAttrBeans(
			ProductAttrBean producattr, PageInfo pageInfo) throws Exception;

	/**
	 * 删除产品属性
	 * 
	 * @param producattr
	 * @return
	 * @throws Exception
	 */
	public int deleteProductAttrBean(ProductAttrBean producattr)
			throws Exception;

	/**
	 * 增加产品配置
	 * 
	 * @param producconfig
	 * @return
	 * @throws Exception
	 */
	public ProductConfigBean insertProductConfigBean(
			ProductConfigBean producconfig) throws Exception;

	/**
	 * 修改产品配置
	 * 
	 * @param producconfig
	 * @return
	 * @throws Exception
	 */
	public int updateProductConfigBean(ProductConfigBean producconfig)
			throws Exception;

	/**
	 * 获取产品配置
	 * 
	 * @param producconfig
	 * @return
	 * @throws Exception
	 */
	public ProductConfigBean getProductConfigBean(ProductConfigBean producconfig)
			throws Exception;

	/**
	 * 获取产品配置
	 * 
	 * @param producconfig
	 * @return
	 * @throws Exception
	 */
	public List<ProductConfigBean> getProductConfigBeans(
			ProductConfigBean producconfig) throws Exception;

	/**
	 * 获取产品配置
	 * 
	 * @param producconfig
	 * @return
	 * @throws Exception
	 */
	public List<ProductConfigBean> getProductConfigBeans(
			ProductConfigBean producconfig, PageInfo pageInfo) throws Exception;

	/**
	 * 删除产品配置
	 * 
	 * @param producconfig
	 * @return
	 * @throws Exception
	 */
	public int deleteProductConfigBean(ProductConfigBean producconfig)
			throws Exception;

	/**
	 * 增加产品配置自定义图片
	 * 
	 * @param producconfImg
	 * @return
	 * @throws Exception
	 */
	public ProductConfImgBean insertProductConfImgBean(
			ProductConfImgBean producconfImg) throws Exception;

	/**
	 * 修改产品配置自定义图片
	 * 
	 * @param producconfImg
	 * @return
	 * @throws Exception
	 */
	public int updateProductConfImgBean(ProductConfImgBean producconfImg)
			throws Exception;

	/**
	 * 获取产品配置自定义图片
	 * 
	 * @param producconfImg
	 * @return
	 * @throws Exception
	 */
	public ProductConfImgBean getProductConfImgBean(
			ProductConfImgBean producconfImg) throws Exception;

	/**
	 * 获取产品配置自定义图片
	 * 
	 * @param producconfImg
	 * @return
	 * @throws Exception
	 */
	public List<ProductConfImgBean> getProductConfImgBeans(
			ProductConfImgBean producconfImg) throws Exception;

	/**
	 * 获取产品配置自定义图片
	 * 
	 * @param producconfImg
	 * @return
	 * @throws Exception
	 */
	public List<ProductConfImgBean> getProductConfImgBeans(
			ProductConfImgBean producconfImg, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除产品配置自定义图片
	 * 
	 * @param producconfImg
	 * @return
	 * @throws Exception
	 */
	public int deleteProductConfImgBean(ProductConfImgBean producconfImg)
			throws Exception;

	/**
	 * 增加产品图片
	 * 
	 * @param producimage
	 * @return
	 * @throws Exception
	 */
	public ProductImageBean insertProductImageBean(ProductImageBean producimage)
			throws Exception;

	/**
	 * 修改产品图片
	 * 
	 * @param producimage
	 * @return
	 * @throws Exception
	 */
	public int updateProductImageBean(ProductImageBean producimage)
			throws Exception;

	/**
	 * 获取产品图片
	 * 
	 * @param producimage
	 * @return
	 * @throws Exception
	 */
	public List<ProductImageBean> getProductImageBeans(
			ProductImageBean producimage) throws Exception;

	/**
	 * 获取产品图片
	 * 
	 * @param producimage
	 * @return
	 * @throws Exception
	 */
	public List<ProductImageBean> getProductImageBeans(
			ProductImageBean producimage, PageInfo pageInfo) throws Exception;

	/**
	 * 删除产品图片
	 * 
	 * @param producimage
	 * @return
	 * @throws Exception
	 */
	public int deleteProductImageBean(ProductImageBean producimage)
			throws Exception;

	/**
	 * 增加产品
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public ProductBean insertProductBean(ProductBean product) throws Exception;

	/**
	 * 修改产品
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public int updateProductBean(ProductBean product) throws Exception;

	/**
	 * 获取产品
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> getProductBeans(ProductBean product)
			throws Exception;

	/**
	 * 获取产品
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> getProductBeans(ProductBean product,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除产品
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public int deleteProductBean(ProductBean product) throws Exception;

	/**
	 * 增加产品规格
	 * 
	 * @param producstandard
	 * @return
	 * @throws Exception
	 */
	public ProductStandardBean insertProductStandardBean(
			ProductStandardBean producstandard) throws Exception;

	/**
	 * 修改产品规格
	 * 
	 * @param producstandard
	 * @return
	 * @throws Exception
	 */
	public int updateProductStandardBean(ProductStandardBean producstandard)
			throws Exception;

	/**
	 * 获取产品规格
	 * 
	 * @param producstandard
	 * @return
	 * @throws Exception
	 */
	public ProductStandardBean getProductStandardBean(
			ProductStandardBean producstandard) throws Exception;

	/**
	 * 获取产品规格
	 * 
	 * @param producstandard
	 * @return
	 * @throws Exception
	 */
	public List<ProductStandardBean> getProductStandardBeans(
			ProductStandardBean producstandard) throws Exception;

	/**
	 * 获取产品规格
	 * 
	 * @param producstandard
	 * @return
	 * @throws Exception
	 */
	public List<ProductStandardBean> getProductStandardBeans(
			ProductStandardBean producstandard, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除产品规格
	 * 
	 * @param producstandard
	 * @return
	 * @throws Exception
	 */
	public int deleteProductStandardBean(ProductStandardBean producstandard)
			throws Exception;

	/**
	 * 增加缺货登记
	 * 
	 * @param stoceNotice
	 * @return
	 * @throws Exception
	 */
	public StoceNoticeBean insertStoceNoticeBean(StoceNoticeBean stoceNotice)
			throws Exception;

	/**
	 * 修改缺货登记
	 * 
	 * @param stoceNotice
	 * @return
	 * @throws Exception
	 */
	public int updateStoceNoticeBean(StoceNoticeBean stoceNotice)
			throws Exception;

	/**
	 * 获取缺货登记
	 * 
	 * @param stoceNotice
	 * @return
	 * @throws Exception
	 */
	public StoceNoticeBean getStoceNoticeBean(StoceNoticeBean stoceNotice)
			throws Exception;

	/**
	 * 获取缺货登记
	 * 
	 * @param stoceNotice
	 * @return
	 * @throws Exception
	 */
	public List<StoceNoticeBean> getStoceNoticeBeans(
			StoceNoticeBean stoceNotice, PageInfo pageInfo) throws Exception;

	/**
	 * 删除缺货登记
	 * 
	 * @param stoceNotice
	 * @return
	 * @throws Exception
	 */
	public int deleteStoceNoticeBean(StoceNoticeBean stoceNotice)
			throws Exception;

	/**
	 * 获取某个产品的详细信息
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public ProductBean getProductBean(ProductBean product) throws Exception;

	/**
	 * 通过规格条件查询库存数量
	 * 
	 * @param standardBean
	 * @return
	 * @throws Exception
	 */
	public Integer getStockNum(ProductStandardBean standardBean)
			throws Exception;

	/**
	 * 增加自定义图片分类
	 * 
	 * @param tImageCat
	 * @return
	 * @throws Exception
	 */
	public ImageCatBean insertImageCatBean(ImageCatBean tImageCat)
			throws Exception;

	/**
	 * 修改自定义图片分类
	 * 
	 * @param tImageCat
	 * @return
	 * @throws Exception
	 */
	public int updateImageCatBean(ImageCatBean tImageCat) throws Exception;

	/**
	 * 获取自定义图片分类
	 * 
	 * @param tImageCat
	 * @return
	 * @throws Exception
	 */
	public List<ImageCatBean> getImageCatBeans(ImageCatBean tImageCat)
			throws Exception;

	/**
	 * 获取自定义图片分类
	 * 
	 * @param tImageCat
	 * @return
	 * @throws Exception
	 */
	public List<ImageCatBean> getImageCatBeans(ImageCatBean tImageCat,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除自定义图片分类
	 * 
	 * @param tImageCat
	 * @return
	 * @throws Exception
	 */
	public int deleteImageCatBean(ImageCatBean tImageCat) throws Exception;

	/**
	 * 获取图片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public List<ImageBean> getImageBeansByStatus(ImageBean image,
			PageInfo pageInfo) throws Exception;

	/**
	 * 更改图片CatId为未分组
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public int updateImageCatInfo(ImageCatBean icb) throws Exception;

	/**
	 * 获取某个用户所用空间大小
	 * 
	 * @param sellerId
	 * @return
	 * @throws Exception
	 */
	public long getImageSize(long sellerId) throws Exception;

	/**
	 * 添加买家用户喜欢的产品
	 * 
	 * @param buyerProductList
	 * @return
	 * @throws Exception
	 */
	public BuyerProductListBean insertBuyerProductListBean(
			BuyerProductListBean buyerProductList) throws Exception;

	/**
	 * 通过BUYERID,PRODUCTID查询买家用户喜欢的产品
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public BuyerProductListBean getBuyerProductListBean(
			BuyerProductListBean buyerProductList) throws Exception;

	/**
	 * 通过BUYERID,PRODUCTID删除买家用户喜欢的产品
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public Integer deleteBuyerProductListBean(
			BuyerProductListBean buyerProductList) throws Exception;

	/**
	 * 根据关键词搜索产品
	 * 
	 * @param SearchProductVo,pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> getSearchProductBeans(
			SearchProductVo searchProductVo, PageInfo pageInfo)
			throws Exception;

	/**
	 * 根据关键词搜索产品分类聚合
	 * 
	 * @param searchProductVo
	 * @return
	 * @throws Exception
	 */
	public List<CustomCategoryBean> getSearchProductCat(
			SearchProductVo searchProductVo) throws Exception;

	/**
	 * 返回产品搜索配置属性聚合
	 * 
	 * @param searchProductVo
	 * @return
	 * @throws Exception
	 */
	public List<SearchAttrVo> getSearchConfigs(SearchProductVo searchProductVo)
			throws Exception;

	/**
	 * 返回产品搜索普通属性聚合
	 * 
	 * @param searchProductVo
	 * @return
	 * @throws Exception
	 */
	public List<SearchAttrVo> getSearchAttributes(
			SearchProductVo searchProductVo) throws Exception;

	/**
	 * 获取某个买家的favorite产品
	 * 
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> getBuyerFavorite(
			BuyerProductListBean buyerProductListBean) throws Exception;

	/**
	 * 获取某个买家的favorite产品数量
	 * 
	 * @param buyerProductListBean
	 * @return
	 * @throws Exception
	 */
	public Integer getBuyerFavoriteCount(
			BuyerProductListBean buyerProductListBean) throws Exception;

	/**
	 * 获取买家购买过产品
	 * 
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public Integer getBuyerBuyProductCount(long buyerId) throws Exception;

	/**
	 * 获取买家购买过产品
	 * 
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> getBuyerBuyProducts(long buyerId, PageInfo pageInfo)
			throws Exception;

	/**
	 * 获取自定义分类产品总数
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public int getCustomCatProductCount(String catId) throws Exception;

	/**
	 * 获取产品状态数量统计
	 * 
	 * @return
	 */
	public List<ProductCountVo> getProductStatusCount() throws Exception;

	/**
	 * 获取分类下热销产品
	 * 
	 * @return
	 */
	public List<ProductBean> getHotProducts(ProductBean product)
			throws Exception;

	/**
	 * 增加活动
	 * 
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public ActivityBean insertActivityBean(ActivityBean activity)
			throws Exception;

	/**
	 * 修改活动
	 * 
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public int updateActivityBean(ActivityBean activity) throws Exception;

	/**
	 * 获取活动
	 * 
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public ActivityBean getActivityBean(ActivityBean activity) throws Exception;

	/**
	 * 获取活动
	 * 
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public List<ActivityBean> getActivityBeans(ActivityBean activity)
			throws Exception;

	/**
	 * 获取活动
	 * 
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public List<ActivityBean> getActivityBeans(ActivityBean activity,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除活动
	 * 
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public int deleteActivityBean(ActivityBean activity) throws Exception;

	/**
	 * 增加赠品
	 * 
	 * @param gifts
	 * @return
	 * @throws Exception
	 */
	public GiftsBean insertGiftsBean(GiftsBean gifts) throws Exception;

	/**
	 * 修改赠品
	 * 
	 * @param gifts
	 * @return
	 * @throws Exception
	 */
	public int updateGiftsBean(GiftsBean gifts) throws Exception;

	/**
	 * 获取赠品
	 * 
	 * @param gifts
	 * @return
	 * @throws Exception
	 */
	public GiftsBean getGiftsBean(GiftsBean gifts) throws Exception;

	/**
	 * 获取赠品
	 * 
	 * @param gifts
	 * @return
	 * @throws Exception
	 */
	public List<GiftsBean> getGiftsBeans(GiftsBean gifts) throws Exception;

	/**
	 * 获取赠品
	 * 
	 * @param gifts
	 * @return
	 * @throws Exception
	 */
	public List<GiftsBean> getGiftsBeans(GiftsBean gifts, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除赠品
	 * 
	 * @param gifts
	 * @return
	 * @throws Exception
	 */
	public int deleteGiftsBean(GiftsBean gifts) throws Exception;

	/**
	 * 增加组合销售
	 * 
	 * @param groups
	 * @return
	 * @throws Exception
	 */
	public GroupsBean insertGroupsBean(GroupsBean groups) throws Exception;

	/**
	 * 修改组合销售
	 * 
	 * @param groups
	 * @return
	 * @throws Exception
	 */
	public int updateGroupsBean(GroupsBean groups) throws Exception;

	/**
	 * 获取组合销售
	 * 
	 * @param groups
	 * @return
	 * @throws Exception
	 */
	public GroupsBean getGroupsBean(GroupsBean groups) throws Exception;

	/**
	 * 获取组合销售
	 * 
	 * @param groups
	 * @return
	 * @throws Exception
	 */
	public List<GroupsBean> getGroupsBeans(GroupsBean groups) throws Exception;

	/**
	 * 获取组合销售
	 * 
	 * @param groups
	 * @return
	 * @throws Exception
	 */
	public List<GroupsBean> getGroupsBeans(GroupsBean groups, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除组合销售
	 * 
	 * @param groups
	 * @return
	 * @throws Exception
	 */
	public int deleteGroupsBean(GroupsBean groups) throws Exception;

	/**
	 * 查询最近15天的热销产品
	 * 
	 * @param searchProductVo
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> getHotProductsByTime(
			SearchProductVo searchProductVo) throws Exception;

	/**
	 * 增加产品搬家任务申请
	 * 
	 * @param prodMoveMission
	 * @return
	 * @throws Exception
	 */
	public ProdMoveMissionBean insertProdMoveMissionBean(
			ProdMoveMissionBean prodMoveMission) throws Exception;

	/**
	 * 修改产品搬家任务申请
	 * 
	 * @param prodMoveMission
	 * @return
	 * @throws Exception
	 */
	public int updateProdMoveMissionBean(ProdMoveMissionBean prodMoveMission)
			throws Exception;

	/**
	 * 获取产品搬家任务申请
	 * 
	 * @param prodMoveMission
	 * @return
	 * @throws Exception
	 */
	public ProdMoveMissionBean getProdMoveMissionBean(
			ProdMoveMissionBean prodMoveMission) throws Exception;

	/**
	 * 获取产品搬家任务申请
	 * 
	 * @param prodMoveMission
	 * @return
	 * @throws Exception
	 */
	public List<ProdMoveMissionBean> getProdMoveMissionBeans(
			ProdMoveMissionBean prodMoveMission) throws Exception;

	/**
	 * 获取产品搬家任务申请
	 * 
	 * @param prodMoveMission
	 * @return
	 * @throws Exception
	 */
	public List<ProdMoveMissionBean> getProdMoveMissionBeans(
			ProdMoveMissionBean prodMoveMission, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除产品搬家任务申请
	 * 
	 * @param prodMoveMission
	 * @return
	 * @throws Exception
	 */
	public int deleteProdMoveMissionBean(ProdMoveMissionBean prodMoveMission)
			throws Exception;

	/**
	 * 增加缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public StockoutBean insertStockoutBean(StockoutBean stockout)
			throws Exception;

	/**
	 * 修改缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public int updateStockoutBean(StockoutBean stockout) throws Exception;

	/**
	 * 获取缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public StockoutBean getStockoutBean(StockoutBean stockout) throws Exception;

	/**
	 * 获取缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public List<StockoutBean> getStockoutBeans(StockoutBean stockout)
			throws Exception;

	/**
	 * 获取缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public List<StockoutBean> getStockoutBeans(StockoutBean stockout,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public int deleteStockoutBean(StockoutBean stockout) throws Exception;
}