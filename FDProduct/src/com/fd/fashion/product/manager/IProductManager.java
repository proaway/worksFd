package com.fd.fashion.product.manager;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.fd.fashion.dict.bean.AttributeBean;
import com.fd.fashion.dict.bean.SizeAttrBean;
import com.fd.fashion.dict.bean.SizeCatBean;
import com.fd.fashion.dict.bean.SizeColattrBean;
import com.fd.fashion.dict.bean.SizeRowattrBean;
import com.fd.fashion.product.bean.ActivityBean;
import com.fd.fashion.product.bean.BuyerProductListBean;
import com.fd.fashion.product.bean.GiftsBean;
import com.fd.fashion.product.bean.GroupsBean;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.bean.ImageCatBean;
import com.fd.fashion.product.bean.ProdMoveMissionBean;
import com.fd.fashion.product.bean.ProductAttrBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.bean.ProductImageBean;
import com.fd.fashion.product.bean.ProductStandardBean;
import com.fd.fashion.product.bean.StoceNoticeBean;
import com.fd.fashion.product.bean.StockoutBean;
import com.fd.fashion.product.vo.GroupVo;
import com.fd.fashion.product.vo.PriceVo;
import com.fd.fashion.product.vo.ProductConfigsVo;
import com.fd.fashion.product.vo.ProductCountVo;
import com.fd.fashion.product.vo.ProductSelectVo;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.fashion.product.vo.PromotionImageVo;
import com.fd.fashion.product.vo.SearchAttrVo;
import com.fd.fashion.product.vo.SearchProductVo;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.bean.SizeTemplateBean;
import com.fd.fashion.seller.bean.SizeTemplateDetailBean;
import com.fd.fashion.seller.vo.ResultRateVo;
import com.fd.fashion.seller.vo.ShippingRateVo;
import com.fd.fashion.seller.vo.ShippingTemplateVo;
import com.fd.util.PageInfo;

/**
 * @CreateDate: 2013-3-13 下午09:57:44
 * @author Longli
 * @Description: ProductManager接口类
 * 
 */
public interface IProductManager {
	/**
	 * 获取产品配置属性
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public ProductConfigsVo getProductConfigsVo(long productId, String catId) throws Exception;
	
	/**
	 * 使用产品ID获取产品信息
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public ProductBean getProductBean(long productId) throws Exception;
	
	/**通过产品名称查询是否存在该名称的产品
	 * @param productName
	 * @return
	 * @throws Exception
	 */
	public ProductBean getProductBeanByName(String productName) throws Exception;
	
	/**通过sku查询是否存在该sku的产品
	 * @param sku
	 * @return
	 * @throws Exception
	 */
	public ProductBean getProductBeanBySku(String sku) throws Exception;

	/**
	 * 查询ProductBean，这个方法调用要谨慎，需要预先校验设置到product中的参数值，不可全部参数为空
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public ProductBean getProductBean(ProductBean product) throws Exception;
	
	/**
	 * 使用产品ID获取产品信息
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public ProductVo getProductVo(long productId) throws Exception;
	
	/**
	 * 获取产品图片
	 * 
	 * @param producimage
	 * @return
	 * @throws Exception
	 */
	public List<ProductImageBean> getProductImageBeans(long productId) throws Exception;
	
	/**
	 * 获取图片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public List<ImageBean> getImageBeans(Long productId) throws Exception;
	
	
	/**
	 * w获取图片属性
	 * 
	 * @param imageBean
	 * @return
	 * @throws Exception
	 */
	public ImageBean getImageBean(ImageBean imageBean) throws Exception;
	
	/**获取某个用户所用空间大小
	 * @param sellerId
	 * @return
	 * @throws Exception
	 */
	public long getImageSize(long sellerId) throws Exception;
	
	/**
	 * 获取产品首图
	 * 
	 * @param imageBean
	 * @return
	 * @throws Exception
	 */
	public ImageBean getFirstProdImageBean(long productId) throws Exception ;
	
	/**
	 * 获取产品价格
	 * 
	 * @param price
	 * @return
	 * @throws Exception
	 */
	public PriceVo getPriceVo(Long productId) throws Exception;
	
	/**通过产品ID和规格ID查询库存量总 量:一种规格以上，不含一种
	 * @param productId
	 * @param productConfigId
	 * @param productConfigId2
	 * @return
	 * @throws Exception
	 */
	public Integer getStockNum(Long productId,
			Long productConfigId, Long productConfigId2) throws Exception ;

	/**通过产品ID和规格ID查询库存量:一种规格
	 * @param productId
	 * @param productConfigId
	 * @return
	 * @throws Exception
	 */
	public Integer getStockNum(Long productId,Long productConfigId) throws Exception;
		

	/**通过产品ID查询产品总库存量
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public Integer getStockNum(Long productId) throws Exception ;


	/**
	 * 获取SizeTemplate
	 * 
	 * @param SizeTemplateBean
	 * @return
	 * @throws Exception
	 */
	public SizeTemplateBean getSizeTemplateBean(Long sizeTemplateId) throws Exception;
	
	
	/**
	 * 获取SizeTemplateDetailBean
	 * 
	 * @param SizeTemplateBean
	 * @return
	 * @throws Exception
	 */
	public List<SizeTemplateDetailBean> getSizeTemplateBeanDetailList(Long sizeTemplateDetailId) throws Exception;
	
	/**
	 * 获取SizeAttrBean
	 * 
	 * @param SizeAttrBean
	 * @return
	 * @throws Exception
	 */
	public SizeAttrBean getSizeAttrBean(Long sizeAttrId) throws Exception;
	
	/**
	 * 获取SizeCatBean
	 * 
	 * @param sizeCatBean
	 * @return
	 * @throws Exception
	 */
	public SizeCatBean getSizeCatBean(Long sizeCatId) throws Exception;
	
	
	/**
	 * 获取SizeColattrBean
	 * 
	 * @param sizeColattrBean
	 * @return
	 * @throws Exception
	 */
	public List<SizeColattrBean> getSizeColattrBean(Long sizeCatId) throws Exception;
	
	
	/**
	 * 获取SizeRowattrBean
	 * 
	 * @param sizeRowattrBean
	 * @return
	 * @throws Exception
	 */
	public List<SizeRowattrBean> getSizeRowattrBean(Long sizeCatId) throws Exception;
	
	
	/**
	 * 获取CatHotProducts
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> getCatHotProducts(String catId,int num) throws Exception;
	
	/**
	 * 获取产品打包销售信息
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public GroupVo getProductGroup(long productId) throws Exception;
	
	/**获取活动信息
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public List<PromotionImageVo> getPromotionImage(long productId) throws Exception;


	/**
	 * 获取产品某个配置属性（如颜色）单独设置的图片首图
	 * 
	 * @param productConfigId
	 * @return
	 * @throws Exception
	 */
	public ImageBean getFirstConfigImg(long productConfigId) throws Exception;


	/**
	 * 获取产品某个配置属性（如颜色）单独设置的所有图片
	 * 
	 * @param productConfigId
	 * @return
	 * @throws Exception
	 */
	public List<ImageBean> getConfigImgs(long productConfigId) throws Exception;
	
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
	 * 获取最近浏览历史产品
	 * 
	 * @param productIds
	 * @return
	 * @throws Exception
	 */
	public List<ProductVo> getRecentHistoryProducts(List<Long> productIds) throws Exception;
	
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
	 * 获取缺货登记
	 * 
	 * @param stoceNotice
	 * @return
	 * @throws Exception
	 */
	public StoceNoticeBean getStoceNoticeBean(StoceNoticeBean stoceNotice)
			throws Exception;
	
	/**
	 * 获取产品的父子目录
	 * 
	 * @param stoceNotice
	 * @return
	 * @throws Exception
	 */
	public String getProductCategories(Long productId)
			throws Exception;

	/**获取某分类的父子目录
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public String getProductCategories(String catId) throws Exception;
	/**
	 * 上传产品入库
	 * 
	 * @param ImageBean
	 * @return
	 * @throws Exception
	 */
	public String saveImage(FileItem item,File uploadedFile,ImageBean imageBean)
			throws Exception;
	
	/**
	 * 获取图片空间列表
	 * 
	 * @param imageBean,pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<ImageBean> getImageList(ImageBean imageBean,PageInfo pageInfo) throws Exception;
	
	/**
	 * 删除文件
	 * 
	 * @param imageBean
	 * @return
	 * @throws Exception
	 */
	public boolean deleteImage(String imageId) throws Exception;
	
	/**
	 * 获取选择产品页产品列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ProductSelectVo> getProductSelects() throws Exception;
	
	/**
	 * 根据条件获取ImageList
	 * 
	 * @param imageBean
	 * @return
	 * @throws Exception
	 */
	public List<ImageBean> getImagesByStatus(ImageBean imageBean,PageInfo pageInfo) throws Exception;
	
	
	/**获取尺码模板列表
	 * @param sizeTemplateBean
	 * @return
	 * @throws Exception
	 */
	public List<SizeTemplateBean> getSizeTemplateBeans(SizeTemplateBean sizeTemplateBean) throws Exception;
	
	/**
	 * 获取卖家运费信息模板及运输详细
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public List<ShippingTemplateVo> getShippingTemplateVo(long sellerId) throws Exception;

	
	/**
	 * 获取自定义图片分类
	 * 
	 * @param imageBean
	 * @return
	 * @throws Exception
	 */
	public List<ImageCatBean> getImagesCatList() throws Exception;
	
	/**
	 * 添加图片分类
	 * 
	 * @param catName
	 * @return
	 * @throws Exception
	 */
	public ImageCatBean addImagesCat(String catName) throws Exception;
	
	/**
	 * 修改分类名称
	 * 
	 * @param ImageCatBean
	 * @return
	 * @throws Exception
	 */
	public int updateImagesCat(ImageCatBean icb) throws Exception;
	
	/**
	 * 删除分类
	 * 
	 * @param ImageCatBean
	 * @return
	 * @throws Exception
	 */
	public int deleteImagesCat(ImageCatBean icb) throws Exception;
	
	/**
	 * 移动图片至分类
	 * 
	 * @param imageId
	 * @return
	 * @throws Exception
	 */
	public int moveImageToCat(String imageId,String catId) throws Exception;
	
	/**添加买家用户喜欢的产品
	 * @param buyerProductList
	 * @return
	 * @throws Exception
	 */
	public BuyerProductListBean insertBuyerProductListBean(BuyerProductListBean buyerProductList) throws Exception;
	
	/**通过BUYERID,PRODUCTID查询买家用户喜欢的产品
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public BuyerProductListBean getBuyerProductListBean(BuyerProductListBean buyerProductList) throws Exception;
	
	/**
	 * 通过关键词搜索
	 * @author:  ZhouRongyu
	 * @param searchProductVo
	 * @return
	 * @throws Exception
	 */
	public SearchProductVo getProductByKey(SearchProductVo searchProductVo, PageInfo pageInfo) throws Exception;
	
	/**
	 * 搜索产品
	 * 
	 * @param searchProductVo
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> searchProductBeans(SearchProductVo searchProductVo, PageInfo pageInfo) throws Exception;
	
	/**
	 * 通过关键词搜索产品的分类聚合
	 * @author:  ZhouRongyu
	 * @param searchProductVo
	 * @return
	 * @throws Exception
	 */
	public List<CustomCategoryBean> getSearchProductCatByKey(SearchProductVo searchProductVo) throws Exception ;
	
	/**
	 * 使用产品规格属性
	 * 
	 * @param ProductStandardBean
	 * @return
	 * @throws Exception
	 */
	public ProductStandardBean getProductStandard(ProductStandardBean productStandardBean) throws Exception;
	
	/**
	 * 通过产品规格获取产品属性
	 * 
	 * @param ProductStandardBean
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,AttributeBean> getProductConfigs(Long standardId) throws Exception;
	
	/**
	 * 修改产品有效期
	 * 
	 * @param productIds
	 * @param expiredDay
	 * @return
	 * @throws Exception
	 */
	public int updateProductExpiredDay(long productIds[], int expiredDay) throws Exception;
	
	/**
	 * 修改产品备货
	 * 
	 * @param productIds
	 * @param stockDays
	 * @return
	 * @throws Exception
	 */
	public int updateProductStockDay(long productIds[], int stockDays) throws Exception;
	
	/**
	 * 修改产品备货
	 * 
	 * @param productIds
	 * @param addDays
	 * @return
	 * @throws Exception
	 */
	public int addProductStockDay(long productIds[], int addDays) throws Exception;
	
	/**
	 * 删除产品
	 * 
	 * @param productIds
	 * @return
	 * @throws Exception
	 */
	public int deleteProducts(long productIds[]) throws Exception;
	
	/**
	 * 下架产品
	 * 
	 * @param productIds
	 * @return
	 * @throws Exception
	 */
	public int offsaleProducts(long productIds[]) throws Exception;
	
	/**
	 * 恢复产品
	 * 
	 * @param productIds
	 * @return
	 * @throws Exception
	 */
	public int restoreProducts(long productIds[]) throws Exception;
	
	/**
	 * 修改产品名称
	 * 
	 * @param productIds 产品ID
	 * @param nameBefore 名称前缀
	 * @param nameAfter 名称后缀
	 * @param replaceReg 名称替换正则
	 * @param replaceStr 名称替换字符串
	 * @return
	 * @throws Exception
	 */
	public int updateProductsName(long productIds[], String nameBefore, String nameAfter, String replaceReg, String replaceStr) throws Exception;
	
	/**
	 * 修改包装信息
	 * 
	 * @param productIds
	 * @param ispackage
	 * @param packing
	 * @return
	 * @throws Exception
	 */
	public int updateProductPacking(long productIds[], int ispackage, String packing, int quantity) throws Exception;
	
	/**
	 * 修改分类
	 * 
	 * @param productIds
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public int updateProductCategory(long productIds[], String catId) throws Exception;
	
	/**
	 * 修改产品重量
	 * 
	 * @param productIds 产品编号
	 * @param addWeight 修改的重量
	 * @param editFlag 修改方法标识，0-改成统一值，1-按值修改，2-按百分比修改
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> updateProductWeight(long productIds[], double addWeight, int editFlag) throws Exception;
	
	/**
	 * 修改产品尺寸
	 * 
	 * @param productIds 产品编号
	 * @param length 长
	 * @param width 宽
	 * @param height 高
	 * @return
	 * @throws Exception
	 */
	public int updateProductSize(long productIds[], int length, int width, int height) throws Exception;
	
	/**
	 * 修改产品价格
	 * 
	 * @param productIds 产品编号
	 * @param addPrice 修改的价格
	 * @param abs false-按百分比修改，true-按值修改
	 * @return
	 * @throws Exception
	 */
	public int updateProductPrice(long productIds[], double addPrice, boolean abs) throws Exception;
	
	/**获取某个买家的favorite产品
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<ProductVo> getBuyerFavorite(long buyerId,PageInfo pageInfo) throws Exception;
	
	/**删除某个买家的某个喜欢的产品
	 * @param buyerId
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public Integer deleteBuyerFavorite(long buyerId,long productId) throws Exception;
	
	/**
	 * 搜索产品配置属性的聚合
	 * 
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public List<SearchAttrVo> searchProductConfs(SearchProductVo searchVo) throws Exception;
	
	/**
	 * 搜索产品普通属性的聚合
	 * 
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public List<SearchAttrVo> searchProductAttrs(SearchProductVo searchVo) throws Exception;
	
	/**
	 * 获取产品属性
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrBean> getProductAttrs(long productId) throws Exception;

	
	/**获取买家购买过产品
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<ProductVo> getBuyerBuyProducts(long buyerId,PageInfo pageInfo) throws Exception;
	
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
	 * 更新单个产品
	 * 
	 * @return
	 */
	public int updateProductBean(ProductBean product) throws Exception;
	
	/**
	 * 产品添加图片
	 * 
	 * @return
	 */
	public void insertProductImage(ProductImageBean pib) throws Exception;
	
	/**
	 * 产品删除图片
	 * 
	 * @return
	 */
	public void deleteProductImage(ProductImageBean pib) throws Exception;
	
	/**
	 * 获取分类下热销产品
	 * 
	 * @return
	 */
	public List<ProductVo> getHotProducts(ProductBean product) throws Exception;
	
	/**查询最近15天的热销产品
	 * @param searchProductVo
	 * @return
	 * @throws Exception
	 */
	public List<ProductVo> getHotProductsByTime(SearchProductVo searchProductVo) throws Exception;
	
	/**
	 * 添加赠品活动
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public void addGiftsActivity(ActivityBean activity, List list) throws Exception;
	/**
	 * 更新赠品活动
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public void updateGiftsActivity(ActivityBean activity,List list) throws Exception;
	/**
	 * 添加组合搭配活动
	 * 
	 * @return
	 */
	public void addGroupActivity(ActivityBean activity, GroupsBean groups) throws Exception;
	
	/**
	 * 更新组合销售活动
	 * 
	 * @return
	 */
	public void updateGroupActivity(ActivityBean activity, GroupsBean groups) throws Exception;
	
	/**
	 * 更新活动信息
	 * 
	 * @return
	 */
	public int updateActivity(ActivityBean activity) throws Exception;
	
	/**查询活动列表
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public List<ActivityBean> getActivityList(ActivityBean activity, PageInfo pageInfo) throws Exception;
	
	/**查询组合销售
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public GroupsBean getGroupsBean(String activityId) throws Exception;
	
	/**查询赠品活动列表
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public List<GiftsBean> getGiftsList(String activityId) throws Exception;
	
	/**
	 * 获取搬家任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ProdMoveMissionBean> getMoveMissions() throws Exception;
	
	/**
	 * 增加搬家任务
	 * 
	 * @param website
	 * @param storeUrl
	 * @return
	 * @throws Exception
	 */
	public ProdMoveMissionBean addMoveMission(String website, String storeUrl, String storeCode) throws Exception;
	
	/**
	 * 激活搬家任务
	 * 
	 * @param verifyUrl
	 * @param missionId
	 * @return
	 * @throws Exception
	 */
	public boolean activeMoveMission(String verifyUrl, long missionId) throws Exception;
	
	/**
	 * @param website
	 * @param storeUrl
	 * @return
	 * @throws Excepiton
	 */
	public ProdMoveMissionBean getMoveMission(String website, String storeUrl) throws Exception;
	
	/**
	 * @param missionId
	 * @return
	 * @throws Excepiton
	 */
	public ProdMoveMissionBean getMoveMission(long missionId) throws Exception;
	
	/**
	 * @param mission
	 * @return
	 * @throws Exception
	 */
	public int updateMoveMission(ProdMoveMissionBean mission) throws Exception;
	
	/**
	 * 	 * 增加缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public StockoutBean insertStockoutBean(StockoutBean stockout)
			throws Exception ;

	/**
	 * 修改缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public int updateStockoutBean(StockoutBean stockout) throws Exception ;

	/**
	 * 获取缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public StockoutBean getStockoutBean(StockoutBean stockout) throws Exception ;
}
