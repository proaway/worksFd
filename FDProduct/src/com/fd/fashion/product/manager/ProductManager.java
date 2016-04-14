package com.fd.fashion.product.manager;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fd.fashion.dict.bean.AttributeBean;
import com.fd.fashion.dict.bean.CatConfigBean;
import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.bean.ShippingCostBean;
import com.fd.fashion.dict.bean.SizeAttrBean;
import com.fd.fashion.dict.bean.SizeCatBean;
import com.fd.fashion.dict.bean.SizeColattrBean;
import com.fd.fashion.dict.bean.SizeRowattrBean;
import com.fd.fashion.dict.service.IDictService;
import com.fd.fashion.product.bean.ActivityBean;
import com.fd.fashion.product.bean.BuyerProductListBean;
import com.fd.fashion.product.bean.GiftsBean;
import com.fd.fashion.product.bean.GroupsBean;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.bean.ImageCatBean;
import com.fd.fashion.product.bean.PriceBean;
import com.fd.fashion.product.bean.ProdMoveMissionBean;
import com.fd.fashion.product.bean.ProductAttrBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.bean.ProductConfImgBean;
import com.fd.fashion.product.bean.ProductConfigBean;
import com.fd.fashion.product.bean.ProductImageBean;
import com.fd.fashion.product.bean.ProductStandardBean;
import com.fd.fashion.product.bean.StoceNoticeBean;
import com.fd.fashion.product.bean.StockoutBean;
import com.fd.fashion.product.constants.ProductConstants;
import com.fd.fashion.product.service.IProductService;
import com.fd.fashion.product.vo.CommissionVO;
import com.fd.fashion.product.vo.GroupVo;
import com.fd.fashion.product.vo.PriceVo;
import com.fd.fashion.product.vo.ProductConfigShow;
import com.fd.fashion.product.vo.ProductConfigVo;
import com.fd.fashion.product.vo.ProductConfigsVo;
import com.fd.fashion.product.vo.ProductCountVo;
import com.fd.fashion.product.vo.ProductSelectVo;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.fashion.product.vo.PromotionImageVo;
import com.fd.fashion.product.vo.SearchAttrVo;
import com.fd.fashion.product.vo.SearchProductVo;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.bean.ShippingDetailBean;
import com.fd.fashion.seller.bean.ShippingInfoBean;
import com.fd.fashion.seller.bean.SizeTemplateBean;
import com.fd.fashion.seller.bean.SizeTemplateDetailBean;
import com.fd.fashion.seller.service.ISellerService;
import com.fd.fashion.seller.vo.LogisticsRelationVo;
import com.fd.fashion.seller.vo.ResultRateVo;
import com.fd.fashion.seller.vo.ShippingRateVo;
import com.fd.fashion.seller.vo.ShippingTemplateDetailVo;
import com.fd.fashion.seller.vo.ShippingTemplateOptionVo;
import com.fd.fashion.seller.vo.ShippingTemplateVo;
import com.fd.util.CommissionUtil;
import com.fd.util.CullNumUtil;
import com.fd.util.DateValidUtil;
import com.fd.util.MD5Util;
import com.fd.util.PageInfo;
import com.fd.util.ParametersUtil;
import com.fd.util.RewriteUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate: 2013-3-13 下午10:00:26
 * @author Longli
 * @Description: 产品Manager实现类
 * 
 */
@Transactional(rollbackFor = Exception.class)
@Component
public class ProductManager implements IProductManager {
	@Autowired
	@Qualifier("productService")
	IProductService productService;

	@Autowired
	@Qualifier("sellerService")
	ISellerService sellerService;

	@Autowired
	@Qualifier("dictService")
	IDictService dictService;

	/**
	 * 获取产品配置属性
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public ProductConfigsVo getProductConfigsVo(long productId, String catId)
			throws Exception {
		ProductConfigsVo configVo = new ProductConfigsVo();
		List<ProductStandardBean> standards = getProductStandards(productId);
		configVo.setStandards(standards);
		List<ProductConfigVo> mainConfigs = new ArrayList<ProductConfigVo>();
		List<ProductConfigVo> subConfigs = new ArrayList<ProductConfigVo>();
		if (standards != null && standards.size() > 0) {
			Map<Long, List<ProductStandardBean>> map = new HashMap<Long, List<ProductStandardBean>>();
			Map<Long, ProductConfigVo> mainConfigMap = new HashMap<Long, ProductConfigVo>();
			Map<Long, ProductConfigVo> subConfigMap = new HashMap<Long, ProductConfigVo>();
			for (ProductStandardBean standard : standards) {
				ProductConfigVo config1 = mainConfigMap.get(standard
						.getMainConfigId());
				if (config1 == null) {
					ProductConfigBean configBean = getProductConfig(standard
							.getMainConfigId());
					config1 = new ProductConfigVo();
					config1.setProductConfigBean(configBean);
					mainConfigMap.put(standard.getMainConfigId(), config1);
					mainConfigs.add(config1);
				}
				if (standard.getSubConfigId() != null) {
					ProductConfigVo config2 = subConfigMap.get(standard
							.getSubConfigId());
					if (config2 == null) {
						ProductConfigBean configBean = getProductConfig(standard
								.getSubConfigId());
						config2 = new ProductConfigVo();
						config2.setProductConfigBean(configBean);
						subConfigMap.put(standard.getSubConfigId(), config2);
						subConfigs.add(config2);
					}
					if (config1.getSubConfigs() == null) {
						config1.setSubConfigs(new ArrayList<ProductConfigVo>());
					}
					config1.getSubConfigs().add(config2);
				}
				List<ProductStandardBean> list = map.get(standard
						.getMainConfigId());
				if (list == null) {
					list = new ArrayList<ProductStandardBean>();
					map.put(standard.getMainConfigId(), list);

				}
				list.add(standard);
			}
		}

		List<ProductConfigVo> configs = new ArrayList<ProductConfigVo>();
		configs.addAll(mainConfigs);
		configs.addAll(subConfigs);
		if (configs != null && configs.size() > 0) {
			List<ProductConfigShow> returnList = new ArrayList<ProductConfigShow>();
			Map<Long, ProductConfigShow> map = new HashMap<Long, ProductConfigShow>();
			for (ProductConfigVo pConfigVo : configs) {
				ProductConfigBean pConfig = pConfigVo.getProductConfigBean();
				// 获取属性明细
				AttributeBean attr = new AttributeBean();
				attr.setAttrId(pConfig.getValueAttrId());
				List<AttributeBean> attrs = dictService.getAttributeBeans(attr);
				if (StringUtils.isNotEmpty(pConfig.getAttrName())) {
					attrs.get(0).setValue(pConfig.getAttrName());
				}
				pConfigVo.setAttribute(attrs.get(0));

				ProductConfigShow configShow = map
						.get(pConfig.getTitleAttrId());
				if (configShow == null) {
					configShow = new ProductConfigShow();
					map.put(pConfig.getTitleAttrId(), configShow);
					attr.setAttrId(pConfig.getTitleAttrId());
					List<AttributeBean> titleAttr = dictService
							.getAttributeBeans(attr);
					configShow.setTitleAttr(titleAttr.get(0));
					configShow.setConfigs(new ArrayList<ProductConfigVo>());
					returnList.add(configShow);
										
					CatConfigBean catConfig = new CatConfigBean();
					catConfig.setCatId(catId);
					catConfig.setAttrId(pConfig.getTitleAttrId());
					catConfig = dictService.getCatConfigBean(catConfig);
					configShow.setShowType(catConfig.getShowType());
				}
				configShow.getConfigs().add(pConfigVo);
			}
			if (returnList.size() > 0) {
				configVo.setMainShow(returnList.get(0));
			}
			if (returnList.size() > 1) {
				configVo.setSubShow(returnList.get(1));
			}
			return configVo;
		}
		return null;
	}

	/**
	 * 使用productConfigId获取产品配置
	 * 
	 * @param productConfigId
	 * @return
	 * @throws Exception
	 */
	private ProductConfigBean getProductConfig(long productConfigId)
			throws Exception {
		ProductConfigBean config = new ProductConfigBean();
		config.setProductConfigId(productConfigId);
		List<ProductConfigBean> configs = productService
				.getProductConfigBeans(config);
		if (configs != null && configs.size() > 0) {
			config = configs.get(0);
			if (StringUtils.isNotEmpty(config.getAttrName())) {
				config.setAttrName(config.getAttrName().replaceAll("\"",
						"&quot;").replaceAll("'", "&prime;"));
			}
			return config;
		}
		return null;
	}

	/**
	 * 使用产品ID获取产品信息
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public ProductBean getProductBean(long productId) throws Exception {
		if (productId <= 0) {
			return null;
		}
		ProductBean pro = new ProductBean();
		pro.setProductId(productId);
		pro = productService.getProductBean(pro);
		if (pro != null && StringUtils.isNotEmpty(pro.getProductName())) {
			pro.setProductName(pro.getProductName().replaceAll("\"", "&quot;")
					.replaceAll("'", "&prime;"));
		}
		return pro;
	}

	/**
	 * 使用产品ID获取产品信息
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public ProductVo getProductVo(long productId) throws Exception {
		if (productId <= 0) {
			return null;
		}
		ProductBean prod = getProductBean(productId);
		if (prod != null) {
			ProductVo prodVo = new ProductVo();
			prodVo.setProduct(prod);
			return prodVo;
		}
		return null;
	}

	/**
	 * 获取产品所有图片ID
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public List<ProductImageBean> getProductImageBeans(long productId)
			throws Exception {
		ProductImageBean productImage = new ProductImageBean();
		productImage.setProductId(productId);
		List<ProductImageBean> proImages = productService
				.getProductImageBeans(productImage);
		return proImages;
	}

	/**
	 * 获取图片属性
	 * 
	 * @param imageBean
	 * @return
	 * @throws Exception
	 */
	public ImageBean getImageBean(ImageBean imageBean) throws Exception {
		return productService.getImageBean(imageBean);
	}
	
	/**获取某个用户所用空间大小
	 * @param sellerId
	 * @return
	 * @throws Exception
	 */
	public long getImageSize(long sellerId) throws Exception{
		return productService.getImageSize(sellerId);
	}

	/**
	 * 获取产品所有图片路径
	 */
	public List<ImageBean> getImageBeans(Long productId) throws Exception {
		List<ProductImageBean> listImage = getProductImageBeans(productId);
		if (null == listImage || listImage.size() == 0) {
			return null;
		}
		List<ImageBean> imgList = new ArrayList<ImageBean>();
		for (ProductImageBean pro : listImage) {
			ImageBean img = new ImageBean();
			img.setImageId(pro.getImageId());
			img = productService.getImageBean(img);
			if (null != img) {
				imgList.add(img);
			}
		}
		return imgList;
	}

	public PriceVo getPriceVo(Long productId) throws Exception {
		PriceBean price = new PriceBean();
		price.setProductId(productId);
		price = productService.getPriceBean(price);
		if (price == null) {
			return null;
		}
		PriceVo priceVo = new PriceVo();
		priceVo.setPriceBean(price);
		return priceVo;
	}

	/**
	 * 通过产品ID和规格ID查询库存量总 量:一种规格以上，不含一种
	 * 
	 * @param productId
	 * @param productConfigId
	 * @param productConfigId2
	 * @return
	 * @throws Exception
	 */
	public Integer getStockNum(Long productId, Long productConfigId,
			Long productConfigId2) throws Exception {
		if (productId == null || productConfigId == null
				|| productConfigId2 == null) {
			return 0;
		}
		ProductStandardBean ps = new ProductStandardBean();
		ps.setProductId(productId);
		ps.setMainConfigId(productConfigId);
		ps.setSubConfigId(productConfigId2);
		Integer count = 0;
		count = productService.getStockNum(ps);
		return count;
	}

	/**
	 * 通过产品ID和规格ID查询库存量:一种规格
	 * 
	 * @param productId
	 * @param productConfigId
	 * @return
	 * @throws Exception
	 */
	public Integer getStockNum(Long productId, Long productConfigId)
			throws Exception {
		if (productId == null || productConfigId == null) {
			return 0;
		}
		ProductStandardBean ps = new ProductStandardBean();
		ps.setProductId(productId);
		ps.setMainConfigId(productConfigId);
		Integer count = 0;
		count = productService.getStockNum(ps);
		return count;
	}

	/**
	 * 通过产品ID查询产品总库存量
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public Integer getStockNum(Long productId) throws Exception {
		if (productId == null) {
			return -1;
		}
		ProductStandardBean ps = new ProductStandardBean();
		ps.setProductId(productId);
		Integer count = 0;
		count = productService.getStockNum(ps);
		return count;
	}

	/**
	 * 获取产品所有规格
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public List<ProductStandardBean> getProductStandards(long productId)
			throws Exception {
		ProductStandardBean standard = new ProductStandardBean();
		standard.setProductId(productId);
		return productService.getProductStandardBeans(standard);
	}


	/**
	 * 获取尺码分类模板信息
	 * 
	 */
	@Override
	public SizeTemplateBean getSizeTemplateBean(Long sizeTemplateId)
			throws Exception {
		// TODO Auto-generated method stub
		SizeTemplateBean sizeTemplateBean = new SizeTemplateBean();
		sizeTemplateBean.setSizecatId(sizeTemplateId);
		List<SizeTemplateBean> stb = sellerService
				.getSizeTemplateBeans(sizeTemplateBean);
		if (stb.size() > 0) {
			return stb.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 获取尺码数据详细信息
	 * 
	 */
	@Override
	public List<SizeTemplateDetailBean> getSizeTemplateBeanDetailList(
			Long sizeTempId) throws Exception {
		// TODO Auto-generated method stub
		SizeTemplateDetailBean sizeTemplateDetailBean = new SizeTemplateDetailBean();
		sizeTemplateDetailBean.setTemplateId(sizeTempId);
		List<SizeTemplateDetailBean> sizeTemplateDetails = new ArrayList<SizeTemplateDetailBean>();
		sizeTemplateDetails = sellerService
				.getSizeTemplateDetailBeans(sizeTemplateDetailBean);
		return sizeTemplateDetails;
	}

	/**
	 * 获取尺码分类属性
	 * 
	 */
	@Override
	public SizeAttrBean getSizeAttrBean(Long sizeAttrId) throws Exception {
		// TODO Auto-generated method stub
		SizeAttrBean sizeAttrBean = new SizeAttrBean();
		sizeAttrBean.setAttrId(sizeAttrId);
		return dictService.getSizeAttrBeans(sizeAttrBean).get(0);
	}

	/**
	 * 获取尺码分类
	 * 
	 */
	@Override
	public SizeCatBean getSizeCatBean(Long sizeCatId) throws Exception {
		// TODO Auto-generated method stub
		SizeCatBean sizeCatBean = new SizeCatBean();
		sizeCatBean.setSizecatId(sizeCatId);
		return dictService.getSizeCatBeans(sizeCatBean).get(0);
	}

	/**
	 * 获取尺码对应列信息
	 * 
	 */
	@Override
	public List<SizeColattrBean> getSizeColattrBean(Long sizeCatId)
			throws Exception {
		// TODO Auto-generated method stub
		SizeColattrBean sizeColattrBean = new SizeColattrBean();
		sizeColattrBean.setSizecatId(sizeCatId);
		List<SizeColattrBean> sizeColattrBeans = dictService
				.getSizeColattrBeans(sizeColattrBean);
		return sizeColattrBeans;
	}

	/**
	 * 获取尺码对应行信息
	 * 
	 */
	@Override
	public List<SizeRowattrBean> getSizeRowattrBean(Long sizeCatId)
			throws Exception {
		// TODO Auto-generated method stub
		SizeRowattrBean sizeRowattrBean = new SizeRowattrBean();
		sizeRowattrBean.setSizecatId(sizeCatId);
		List<SizeRowattrBean> sizeRowattrBeans = dictService
				.getSizeRowattrBeans(sizeRowattrBean);
		return sizeRowattrBeans;
	}

	/**
	 * 根据数量获取分类下的热销产品
	 * 
	 * @param catId,num
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> getCatHotProducts(String catId, int num)
			throws Exception {
		return null;
	}

	/**
	 * 获取产品打包销售信息
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public GroupVo getProductGroup(long productId) throws Exception {
		GroupVo group = new GroupVo();
		GroupsBean g = new GroupsBean();
		ActivityBean activity = new ActivityBean();
		activity.setProductId(productId);
		activity.setType(0);
		//g.setProductId(productId);
		activity = productService.getActivityBean(activity);
		if(activity != null){
			g.setActivityId(activity.getActivityId());
			List<GroupsBean> groups = productService.getGroupsBeans(g);
			if (groups != null && groups.size() > 0) {
				g = groups.get(0);
				group.setGroup(g);
				List<ProductVo> groupProducts = new ArrayList<ProductVo>();
				ProductVo p = getGroupProduct(g.getGroupProductId1());
				if (p.getProduct().getProductStatus() == ProductConstants.ONSALE) {
					groupProducts.add(p);
				}
				p = getGroupProduct(g.getGroupProductId2());
				if (p.getProduct().getProductStatus() == ProductConstants.ONSALE) {
					groupProducts.add(p);
				}
				p = getGroupProduct(g.getGroupProductId3());
				if (p.getProduct().getProductStatus() == ProductConstants.ONSALE) {
					groupProducts.add(p);
				}
				p = getGroupProduct(g.getGroupProductId4());
				if (p.getProduct().getProductStatus() == ProductConstants.ONSALE) {
					groupProducts.add(p);
				}
				group.setGroupProducts(groupProducts);
				return group;
			}
		}
		return null;
	}

	/**
	 * 获取分组产品信息
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	private ProductVo getGroupProduct(Long productId) throws Exception {
		if (productId == null || productId <= 0) {
			return null;
		}
		ProductVo product = getProductVo(productId); // 获取产品
		ProductConfigsVo configVo = getProductConfigsVo(productId, product.getProduct().getCatId()); // 获取产品配置属性和规格
		ImageBean image = getFirstProdImageBean(productId); // 获取产品首图
		product.setFirstImage(image);
		PriceVo price = getPriceVo(productId); // 获取产品价格
		product.setPriceVo(price);
		if (configVo != null) {
			product.setProductConfigs(configVo);
			configVo.setPrice(price);
		}
		return product;
	}

	/**
	 * 获取产品某个配置属性（如颜色）单独设置的图片首图
	 * 
	 * @param productConfigId
	 * @return
	 * @throws Exception
	 */
	public ImageBean getFirstConfigImg(long productConfigId) throws Exception {
		ProductConfImgBean confImg = new ProductConfImgBean();
		confImg.setProductConfigId(productConfigId);
		List<ProductConfImgBean> confImgs = productService
				.getProductConfImgBeans(confImg);
		if (confImgs != null && confImgs.size() > 0) {
			confImg = confImgs.get(0);
			ImageBean image = new ImageBean();
			image.setImageId(confImg.getImageId());
			return getImageBean(image);
		}
		return null;
	}

	/**
	 * 获取产品某个配置属性（如颜色）单独设置的所有图片
	 * 
	 * @param productConfigId
	 * @return
	 * @throws Exception
	 */
	public List<ImageBean> getConfigImgs(long productConfigId) throws Exception {
		ProductConfImgBean confImg = new ProductConfImgBean();
		confImg.setProductConfigId(productConfigId);
		List<ProductConfImgBean> confImgs = productService
				.getProductConfImgBeans(confImg);
		if (confImgs != null && confImgs.size() > 0) {
			List<ImageBean> images = new ArrayList<ImageBean>();
			for (ProductConfImgBean confImage : confImgs) {
				ImageBean image = new ImageBean();
				image.setImageId(confImage.getImageId());
				image = getImageBean(image);
				images.add(image);
			}
			return images;
		}
		return null;
	}

	/**
	 * 获取某个产品的所有赠品图片
	 * 
	 * @param promotion
	 * @return
	 * @throws Exception
	 */
	public List<PromotionImageVo> getPromotionImage(long productId)
			throws Exception {
		ActivityBean activity = new ActivityBean();
		activity.setProductId(productId);
		activity.setType(1);
		activity = productService.getActivityBean(activity);
		if(activity != null){
			GiftsBean promotion = new GiftsBean();
			promotion.setActivityId(activity.getActivityId());
			
			List<GiftsBean> gifts = productService.getGiftsBeans(promotion);
			DateValidUtil dv = new DateValidUtil();
			if (dv.betweenTwoDate(activity.getStartTime(), activity
					.getEndTime())) {
				List<PromotionImageVo> list = new ArrayList<PromotionImageVo>();
				for(int i=0;i<gifts.size();i++){
					GiftsBean gb = gifts.get(i);
					Long pid = gb.getProductId();
					ProductVo productVo = this.getProductVo(pid);
					if (productVo != null) {
						ImageBean image = this.getFirstProdImageBean(productVo.getProduct().getProductId());
						productVo.setFirstImage(image);
					}
					PromotionImageVo piv = new PromotionImageVo();
					piv.setProductId(gb.getProductId());
					piv.setImageUrl(productVo.getSmallImageUrl());
					piv.setProductName(productVo.getProductName());
					list.add(piv);
				}
				return list;
			}
		}

		return null;
	}

	/**
	 * 获取产品首图
	 * 
	 * @param imageBean
	 * @return
	 * @throws Exception
	 */
	public ImageBean getFirstProdImageBean(long productId) throws Exception {
		List<ProductImageBean> productImages = getProductImageBeans(productId);
		if (productImages != null && productImages.size() > 0) {
			ProductImageBean cond = productImages.get(0);
			ImageBean image = new ImageBean();
			image.setImageId(cond.getImageId());
			image = getImageBean(image);
			return image;
		}
		return null;
	}

	/**
	 * 得到运费计算结果
	 * 
	 * @param shippingRateVo
	 *            将运算条件封装为一个Vo作为传入参数
	 * @return
	 * @throws Exception
	 */
	public List<ResultRateVo> getShippingCalculateResult(
			ShippingRateVo shippingRateVo) throws Exception {
		List<ShippingRateVo> shippingRate = new ArrayList<ShippingRateVo>();
		shippingRate.add(shippingRateVo);
		ProductBean product = new ProductBean();
		product.setProductId(shippingRateVo.getProductId());
		product = productService.getProductBean(product);
		CommissionUtil commissionUtil = new CommissionUtil();
		CommissionVO cvo = new CommissionVO(product);
		// double commission = commissionUtil.getCommission(cvo);
		double commission = 1.05;
		List<ResultRateVo> ss = getResultRateVoList(shippingRate, null,
				commission);
		return ss;
	}

	/**
	 * @param shippingRate
	 *            将运算条件封装为一个Vo作为传入参数
	 * @return 运费计算结果
	 * @throws Exception
	 */
	private List<ResultRateVo> getResultRateVoList(
			List<ShippingRateVo> shippingRate, String shippingCompany,
			double commission) throws Exception {
		// 构造结果集
		ShippingRateVo shippingRateVo = shippingRate.get(0);
		List<ResultRateVo> resultRate = new ArrayList<ResultRateVo>();
		Long shippingTemplateId = shippingRateVo.getShippingTemplateId();
		String country = shippingRateVo.getCountry();
		// 得到该模板下的物流集合
		List<ShippingTemplateDetailVo> shippingTemplateDetail = getShippingTemplateDetail(shippingTemplateId);
		if (shippingTemplateDetail == null) {
			return null;
		}
		HashMap<String, LogisticsRelationVo> getLogisticsRelationMap = getLogisticsRelationMap(country);
		// 该模板有多少个物流,就有多少个运算结果
		for (ShippingTemplateDetailVo shippingTemplateDetailVo : shippingTemplateDetail) {
			ResultRateVo resultRateVo = new ResultRateVo();
			String tempShippingCompany = shippingTemplateDetailVo
					.getShippingCompany().trim();

			// 如果是自定义运费，CUSTOMSHIPPING，运费模板则与HongKong post一致
			String tempCompany = "";
			if (shippingCompany != null
					&& !shippingCompany.equals(tempShippingCompany)) {
				continue;
			}
			tempCompany = tempShippingCompany;
			if (!(tempShippingCompany.equals("HongKong post")
					|| tempShippingCompany.equals("FEDEX")
					|| tempShippingCompany.equals("UPS")
					|| tempShippingCompany.equals("DHL")
					|| tempShippingCompany.equals("TNT") || tempShippingCompany
					.equals("EMS"))) {
				tempShippingCompany = "HongKong post";
			}
			// 得到指定国家及指定物流下的起重价格,续重价格及运输天数等信息
			LogisticsRelationVo logisticsRelationVo = getLogisticsRelationMap
					.get(tempShippingCompany);
			if (logisticsRelationVo == null) {
				logisticsRelationVo = getLogisticsRelationMap
						.get("HongKong post");
			}
			double weightPrice = logisticsRelationVo.getWeightPrice();
			double reWeightPrice = logisticsRelationVo.getReWeightPrice();
			double realWeight = 0;
			realWeight = getRealWeight(shippingRate, tempShippingCompany);

			double freight = 0;// 默认为免费
			Long shippingTemplateDetailId = shippingTemplateDetailVo
					.getDetailId();
			String shippingType = shippingTemplateDetailVo.getShippingType();
			int amount = getProductAmount(shippingRate);
			if ("off".equals(shippingType)) {// 折扣运费
				int discountRate = shippingTemplateDetailVo.getDiscountRate();
				freight = (weightPrice + getIntegerWeight(realWeight)
						* reWeightPrice)
						* (100.0 - discountRate) / 100.0;
			} else if ("custom".equals(shippingType)) {// 自定义运费
				ShippingTemplateOptionVo shippingTemplateOptionVo = getShippingTemplateOptionVo(
						shippingTemplateDetailId, country);
				if (shippingTemplateOptionVo != null) {// 按运费组合计算

					freight = getShippingFreight(shippingTemplateOptionVo,
							amount);
				} else {// 运费组合中不包含该国家,按照折扣运费计算
					Integer discountRate = shippingTemplateDetailVo
							.getDiscountRate();
					if (discountRate == null) {
						discountRate = 0;
					}
					freight = (weightPrice + getIntegerWeight(realWeight)
							* reWeightPrice)
							* (100.0 - discountRate) / 100.0;
				}
			}
			resultRateVo.setShippingCompanyId(shippingTemplateDetailVo
					.getDetailId());
			resultRateVo.setFreight(getDoubleToString(freight * commission,
					"0.00"));
			resultRateVo.setShippingCost(freight);
			resultRateVo.setShippingTotal(freight * commission);
			tempShippingCompany = tempCompany;
			resultRateVo.setShippingCompany(tempShippingCompany);
			resultRateVo.setNumberDays(logisticsRelationVo.getTransportDays());
			resultRate.add(resultRateVo);
		}
		return resultRate;
	}

	/**
	 * 封装物流信息
	 * 
	 * @param shippingTemplateId
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<ShippingTemplateDetailVo> getShippingTemplateDetail(
			Long shippingTemplateId) throws Exception {
		// ShippingTemplateDetailVo shippingTemplateDetailVo = new
		// ShippingTemplateDetailVo();
		// shippingTemplateDetailVo.setShippingInfoId(shippingTemplateId);
		ShippingDetailBean shippingDetailBean = new ShippingDetailBean();
		shippingDetailBean.setShippingInfoId(shippingTemplateId);
		List<ShippingTemplateDetailVo> list = sellerService
				.getShippingTemplateDetails(shippingDetailBean);
		if (list != null && list.size() > 0) {
			for (ShippingTemplateDetailVo tempDetail : list) {
				if ("custom".equals(tempDetail.getShippingType())) {
					// 判断自定义运费中的免运费设置
					chackFreeShipping(tempDetail);
				} else if ("freeshipping".equals(tempDetail.getShippingType())
						|| tempDetail.getDiscountRate().intValue() == 100) {
					// 全部国家免运费
					tempDetail.setFreeShipping(true);
					tempDetail.setFreeShippingCountries("worldwide");
				}
			}
		}
		return list;
	}

	/**
	 * 判断自定义运费中的免运费设置
	 * 
	 * @param tempDetail
	 * @return
	 * @throws Exception
	 */
	private boolean chackFreeShipping(ShippingTemplateDetailVo tempDetail)
			throws Exception {
		ShippingTemplateOptionVo cond = new ShippingTemplateOptionVo();
		cond.setDetailId(tempDetail.getDetailId());
		List<ShippingTemplateOptionVo> optionList = sellerService
				.getShippingTemplateOptions(cond);
		if (optionList != null && optionList.size() > 0) {
			for (ShippingTemplateOptionVo tempOption : optionList) {
				if (tempOption.getShippingcost().floatValue() == 0) {
					tempDetail.setFreeShipping(true);
					String countryIds = tempOption.getShippingCountry();
					if (StringUtil.isEmpty(countryIds)) {
						// 全部国家
						tempDetail.setFreeShippingCountries("worldwide");
					} else {
						// 部分国家免运费
						StringBuffer sb = new StringBuffer();
						String ids[] = countryIds.split(",");
						// List<Re> countries = dictService.getCountries(ids);
						List<RegionBean> countries = dictService
								.getCountries(ids);
						for (int i = 0; i < ids.length; i++) {
							RegionBean country = countries.get(i);
							if (country == null) {
								continue;
							}
							String countryName = country.getRegionName();
							sb.append(countryName);
							sb.append(",");
						}
						sb.deleteCharAt(sb.length() - 1);
						tempDetail.setFreeShippingCountries(sb.toString());
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 得到指定国家及指定物流下的起重价格,续重价格及运输天数等信息
	 * 
	 * @param regionId,shippingCompany
	 * 
	 * @return
	 * @throws Exception
	 */
	private HashMap<String, LogisticsRelationVo> getLogisticsRelationMap(
			String country) throws Exception {

		ShippingCostBean shippingCostBean = new ShippingCostBean();
		shippingCostBean.setCountry(country);
		List<ShippingCostBean> ShippingCostBeans = dictService
				.getShippingCostBeans(shippingCostBean);

		if (ShippingCostBeans != null && ShippingCostBeans.size() > 0) {
			List<LogisticsRelationVo> logisticsVos = new ArrayList<LogisticsRelationVo>();
			for (ShippingCostBean logisticsBean : ShippingCostBeans) {
				LogisticsRelationVo logisticsVo = new LogisticsRelationVo();
				PropertyUtils.copyProperties(logisticsVo, logisticsBean);
				logisticsVos.add(logisticsVo);
			}
			// List<LogisticsRelationVo> logisticsRelation =
			// dictService.getLogisticsRelations(regionId);
			HashMap<String, LogisticsRelationVo> map = new HashMap<String, LogisticsRelationVo>();
			for (LogisticsRelationVo logisticsRelationVo : logisticsVos) {
				map.put(logisticsRelationVo.getShippingCompany(),
						logisticsRelationVo);
			}
			return map;
		} else
			return null;
	}

	/**
	 * 将体积重量和实际重量大的作为重量参数
	 * 
	 * @param shippingRate
	 * 
	 * @return
	 * @throws Exception
	 */
	private double getRealWeight(List<ShippingRateVo> shippingRate,
			String tempShippingCompany) {
		double realWeight = 0;
		for (ShippingRateVo shippingRateVo : shippingRate) {
			realWeight = realWeight
					+ (getRealWeight(shippingRateVo, tempShippingCompany) * shippingRateVo
							.getAmount());
		}
		return realWeight;
	}

	/**
	 * 将体积重量和实际重量大的作为重量参数
	 * 
	 * @param shippingRateVo
	 * 
	 * @return
	 * @throws Exception
	 */
	private double getRealWeight(ShippingRateVo shippingRateVo,
			String tempShippingCompany) {
		if ("EMS".equals(tempShippingCompany)) {
			return shippingRateVo.getHeavy();
		}
		double length = shippingRateVo.getLength();
		double width = shippingRateVo.getWidth();
		double height = shippingRateVo.getHeight();
		double heavy = shippingRateVo.getHeavy();
		double vHeavy = (length * width * height) / 6000;
		return vHeavy > heavy ? vHeavy : heavy;
	}

	private int getProductAmount(List<ShippingRateVo> shippingRate) {
		int amount = 0;
		for (ShippingRateVo shippingRateVo : shippingRate) {
			amount = amount + shippingRateVo.getAmount();
		}
		return amount;
	}

	private long getIntegerWeight(double realWeight) {
		realWeight = realWeight * 1000 - 500;
		String strFreight = getDoubleToString(realWeight, "0.000");
		String strInteger = strFreight.substring(0, strFreight.indexOf("."));
		long integerWeight = Long.parseLong(strInteger);
		if (integerWeight % 500 == 0) {
			return integerWeight / 500;
		}
		return (integerWeight / 500) + 1;
	}

	/**
	 * 将double型不以科学计数表示并转化为String
	 * 
	 * @param freight
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getDoubleToString(double num, String pattern) {
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		return decimalFormat.format(num);
	}

	private ShippingTemplateOptionVo getShippingTemplateOptionVo(
			Long shippingTemplateDetailId, String country) throws Exception {
		ShippingTemplateOptionVo shippingTemplateOptionVo = new ShippingTemplateOptionVo();
		shippingTemplateOptionVo.setDetailId(shippingTemplateDetailId);
		List<ShippingTemplateOptionVo> shippingTemplateOption = sellerService
				.getShippingTemplateOptions(shippingTemplateOptionVo);
		for (int i = 0; i < shippingTemplateOption.size(); i++) {
			shippingTemplateOptionVo = shippingTemplateOption.get(i);
			String regionIds = shippingTemplateOptionVo.getShippingCountry();
			if (regionIds == null || "".equals(regionIds)) {// 运输到所有国家
				return shippingTemplateOptionVo;
			}
			String[] regions = regionIds.split(",");
			for (int j = 0; j < regions.length; j++) {// 运费组合中是否包含该国家
				if (country.equals(regions[j])) {
					return shippingTemplateOptionVo;
				}
			}
		}
		// 运费组合中不包含该国家,按照折扣运费计算
		return null;
	}

	/**
	 * 按运费组合计算运费
	 * 
	 * @param shippingTemplateOptionVo,amount
	 * 
	 * @return
	 * @throws Exception
	 */
	private double getShippingFreight(
			ShippingTemplateOptionVo shippingTemplateOptionVo, int amount) {
		double freight = 0;
		int end = shippingTemplateOptionVo.getEndCount();
		int add = shippingTemplateOptionVo.getAddCount();
		double addShippingCost = shippingTemplateOptionVo.getAddShippingcost();
		double shippingCost = shippingTemplateOptionVo.getShippingcost();
		if (end == 0) {// 免运费
			return freight;
		}
		if (amount <= end) {
			freight = shippingCost;
		} else {
			int addTimes = (amount - end) / add;
			if ((amount - end) % add != 0) {
				addTimes++;
			}
			freight = shippingCost + addTimes * addShippingCost;
		}
		return freight;
	}

	/**
	 * 获取最近浏览历史产品
	 * 
	 * @param productIds
	 * @return
	 * @throws Exception
	 */
	public List<ProductVo> getRecentHistoryProducts(List<Long> productIds)
			throws Exception {
		List<ProductVo> products = new ArrayList<ProductVo>();
		for (long productId : productIds) {
			ProductVo prod = getProductVo(productId);
			if (prod == null) {
				continue;
			}
			ImageBean image = getFirstProdImageBean(productId);
			prod.setFirstImage(image);
			PriceVo price = getPriceVo(productId);
			prod.setPriceVo(price);
			products.add(prod);
		}
		return products;
	}

	/**
	 * 写缺货通知
	 */
	@Override
	public StoceNoticeBean insertStoceNoticeBean(StoceNoticeBean stoceNotice)
			throws Exception {
		if (stoceNotice == null) {
			return null;
		}
		StoceNoticeBean ss = productService.getStoceNoticeBean(stoceNotice);
		if (ss == null) {
			ss = productService.insertStoceNoticeBean(stoceNotice);
		}
		return ss;
	}

	/**
	 * 查询缺货通知
	 * 
	 */
	@Override
	public StoceNoticeBean getStoceNoticeBean(StoceNoticeBean stoceNotice)
			throws Exception {
		if (stoceNotice == null) {
			return null;
		}
		stoceNotice = productService.getStoceNoticeBean(stoceNotice);
		return stoceNotice;

	}

	/**
	 * 获取产品的父子目录名称导航
	 * 
	 * @param stoceNotice
	 * @return
	 * @throws Exception
	 */
	public String getProductCategories(Long productId) throws Exception {
		ProductBean pb = this.getProductBean(productId);
		String catId = pb.getCatId();
		String productCategroies = new String();
		productCategroies = getParentName(productCategroies, catId);
		productCategroies = "&nbsp;&nbsp;&gt;&nbsp;&nbsp;<a href='/fashion/allcategories.html'>All categories</a>"
				+ productCategroies;
		return productCategroies;
	}

	/**
	 * 递归获取产品父目录名称
	 * 
	 * @param name,catId
	 * @return
	 */
	public String getParentName(String name, String catId) throws Exception {
		CategoryBean cbtmp = new CategoryBean();
		cbtmp.setCatId(catId);
		cbtmp = dictService.getCategoryBeans(cbtmp).get(0);
		StringBuffer sb = new StringBuffer();
		sb.append("&nbsp;&nbsp;&gt;&nbsp;&nbsp;");
		sb.append("<a href='");
		sb.append(RewriteUtil.getCategoryUrl(cbtmp.getCatName(),
				cbtmp.getCatId()));
		sb.append("'>");
		sb.append(cbtmp.getCatName());
		sb.append("</a>");
		name = sb.toString() + name;
		if (cbtmp.getParentId() != null && cbtmp.getParentId().length() != 0) {
			return getParentName(name, cbtmp.getParentId());
		} else {
			return name;
		}

	}

	/**
	 * 上传产品入库
	 * 
	 * @param ImageBean
	 * @return
	 * @throws Exception
	 */
	public String saveImage(FileItem item, File uploadedFile,
			ImageBean imageBean) throws Exception {
		// 图片信息入库
		imageBean = productService.insertImageBean(imageBean);
		if (imageBean != null) {
			// 图片文件保存
			item.write(uploadedFile);
		}
		return null;
	}

	/**
	 * 获取图片空间列表
	 * 
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<ImageBean> getImageList(ImageBean imageBean, PageInfo pageInfo)
			throws Exception {
		List<ImageBean> images = productService.getImageBeans(imageBean,
				pageInfo);
		if(images != null) {
			for (int i = 0; i < images.size(); i++) {
				if(images.get(i).getImageUrl() != null){
					images.get(i).setImageUrl(
							RewriteUtil.getImageUrl(images.get(i).getImageUrl()));
				}
			}
		}
		return images;
	}

	/**
	 * 删除文件
	 * 
	 * @param imageBean
	 * @return
	 * @throws Exception
	 */
	public boolean deleteImage(String imageId) throws Exception {
		ImageBean ib = new ImageBean();
		long id = Long.valueOf((imageId));
		ib.setImageId(id);
		ib = productService.getImageBean(ib);
		if (productService.deleteImageBean(ib) == 1) {
			if (ib != null) {
				deleteFile(ib);
			}
			return true;
		} else {
			return false;
		}

	}

	// 删除文件
	public boolean deleteFile(ImageBean ib) {
		boolean flag = false;
		File file = new File(ib.getImageUrl());
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 根据条件获取ImageList
	 * 
	 * @param imageBean
	 * @return
	 * @throws Exception
	 */
	public List<ImageBean> getImagesByStatus(ImageBean imageBean,
			PageInfo pageInfo) throws Exception {

		List<ImageBean> images = productService.getImageBeansByStatus(imageBean, pageInfo);
		return images;
	}

	/**
	 * 获取选择产品页产品列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ProductSelectVo> getProductSelects() throws Exception {
		ProductBean product = new ProductBean();
		List<ProductBean> products = productService.getProductBeans(product);
		List<ProductSelectVo> pslist = new ArrayList<ProductSelectVo>();
		for (ProductBean p : products) {
			ProductSelectVo ps = new ProductSelectVo();
			PropertyUtils.copyProperties(ps, p);
			if(p.getImageUrl() != null){
				ps.setImageUrl(RewriteUtil.getImageUrl(p.getImageUrl(), "m"));
			}
			ps.setProductUrl(RewriteUtil.getProductUrl(p.getProductName(), p
					.getProductId()));
			ps.setProductName(p.getProductName().replaceAll("\"", "&quot;")
					.replaceAll("'", "&prime;"));
			pslist.add(ps);
		}
		return pslist;
	}
	
	
	/**获取尺码模板列表
	 * @param sizeTemplateBean
	 * @return
	 * @throws Exception
	 */
	public List<SizeTemplateBean> getSizeTemplateBeans(SizeTemplateBean sizeTemplateBean) throws Exception{
		return sellerService.getSizeTemplateBeans(sizeTemplateBean);
	}
	
	/**
	 * 获取卖家运费信息模板（主）
	 * 
	 * @param sellerId
	 * @return查询某个卖家的所有的用户自定义运费模板及详细
	 * @throws Exception
	 */
	public List<ShippingTemplateVo> getShippingTemplateVo(long sellerId) throws Exception{
		ShippingInfoBean shippingInfo = new ShippingInfoBean();
		shippingInfo.setSellerId(sellerId);
		List<ShippingInfoBean> shippingInfos = sellerService.getShippingInfoBeans(shippingInfo);
		List<ShippingTemplateVo> shippingTemplates = new ArrayList<ShippingTemplateVo>();
		if(shippingInfos != null){
			for(ShippingInfoBean info :shippingInfos){
				ShippingTemplateVo temp = new ShippingTemplateVo();
				temp.setShippingInfoBean(info);
				List<ShippingTemplateDetailVo> detailTemps = getShippingTemplateDetail(info.getShippingInfoId());
				temp.setTempDetails(detailTemps);
				if(detailTemps!=null){
					String discription = "";
					for(ShippingTemplateDetailVo sv :detailTemps){
						String str = sv.getShippingCompany()+" ";
						if(sv.getShippingType().equals("freeshipping")){
							str+="(免) ";
						}else{
							str+="(折) ";
						}
						discription = discription+str+"\n";
					}
					temp.setDescription(discription);
				}
				shippingTemplates.add(temp);
			}
		}
		return shippingTemplates;
	}
	
	/**
	 * 获取自定义图片分类
	 * 
	 * @param imageBean
	 * @return
	 * @throws Exception
	 */
	public List<ImageCatBean> getImagesCatList() throws Exception{
		
		return productService.getImageCatBeans(null);
	}
	
	/**
	 * 添加图片分类
	 * 
	 * @param catName
	 * @return
	 * @throws Exception
	 */
	public ImageCatBean addImagesCat(String catName) throws Exception{
		ImageCatBean icb = new ImageCatBean();
		icb.setCatName(catName);
		icb.setCreateTime(new Date());
		icb = productService.insertImageCatBean(icb);
		if(icb != null){
			return icb;
		}else
			return null;
	}
	
	/**
	 * 修改分类名称
	 * 
	 * @param catName
	 * @return
	 * @throws Exception
	 */
	public int updateImagesCat(ImageCatBean icb) throws Exception{
		return productService.updateImageCatBean(icb);
	}
	
	/**
	 * 删除分类
	 * 
	 * @param ImageCatBean
	 * @return
	 * @throws Exception
	 */
	public int deleteImagesCat(ImageCatBean icb) throws Exception{
		int i = productService.deleteImageCatBean(icb);
		if(i == 1){
			productService.updateImageCatInfo(icb);
		}
		return 0;
	}
	
	/**
	 * 移动图片至分类
	 * 
	 * @param imageId
	 * @return
	 * @throws Exception
	 */
	public int moveImageToCat(String imageId,String catId) throws Exception{
		ImageBean ib = new ImageBean();
		ib.setCatId(catId);
		ib.setImageId(Long.valueOf(imageId));
		return productService.updateImageBean(ib);
	}

	/**通过产品名称查询是否存在该名称的产品
	 * @param productName
	 * @return
	 * @throws Exception
	 */
	public ProductBean getProductBeanByName(String productName)
			throws Exception {
		if (productName == null || "".equals(productName)) {
			return null;
		}
		ProductBean pro = new ProductBean();
		pro.setProductName(productName);
		pro = productService.getProductBean(pro);
		return pro;
	}

	/**通过sku查询是否存在该sku的产品
	 * @param sku
	 * @return
	 * @throws Exception
	 */
	public ProductBean getProductBeanBySku(String sku) throws Exception {
		if (sku == null || "".equals(sku)) {
			return null;
		}
		ProductBean pro = new ProductBean();
		pro.setSku(sku);
		pro = productService.getProductBean(pro);
		return pro;
	}

	/**
	 * 查询ProductBean，这个方法调用要谨慎，需要预先校验设置到product中的参数值，不可全部参数为空
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public ProductBean getProductBean(ProductBean product) throws Exception {
		if (ParametersUtil.isEmpty(product)) {
			return null;
		}
		product = productService.getProductBean(product);
		return product;
	}
	
	/**添加买家用户喜欢的产品
	 * @param buyerProductList
	 * @return
	 * @throws Exception
	 */
	public BuyerProductListBean insertBuyerProductListBean(BuyerProductListBean buyerProductList) throws Exception{
		if (buyerProductList == null) {
			return null;
		}
		BuyerProductListBean buyerProduct = new BuyerProductListBean();
		buyerProduct.setBuyerId(buyerProductList.getBuyerId());
		buyerProduct.setProductId(buyerProductList.getProductId());
		buyerProduct = productService.getBuyerProductListBean(buyerProduct);
		if (buyerProduct == null) {
			buyerProduct = productService.insertBuyerProductListBean(buyerProductList);
		}
		return buyerProduct;
	}
	
	/**通过BUYERID,PRODUCTID查询买家用户喜欢的产品
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public BuyerProductListBean getBuyerProductListBean(BuyerProductListBean buyerProductList) throws Exception{
		if(buyerProductList==null ||buyerProductList.getBuyerId()<0 || buyerProductList.getProductId()<0){
			return null;
		}
		buyerProductList = productService.getBuyerProductListBean(buyerProductList);
		if(buyerProductList==null ){
			return null;
		}
		return buyerProductList;
	}

	/**获取某分类的父子目录名称导航
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public String getProductCategories(String catId) throws Exception {
		String productCategroies = new String();
		if(catId==null || "".equals(catId)){
			productCategroies = "&nbsp;&nbsp;&gt;&nbsp;&nbsp;<a href='/fashion/allcategories.html'>All categories</a>";
		}else{
			productCategroies = getParentName(productCategroies, catId);
			productCategroies = "&nbsp;&nbsp;&gt;&nbsp;&nbsp;<a href='/fashion/allcategories.html'>All categories</a>"
					+ productCategroies;
		}
		return productCategroies;
	}
	
	/**
	 * 通过关键词搜索
	 * @author:  ZhouRongyu
	 * @param searchProductVo
	 * @return
	 * @throws Exception
	 */
	public SearchProductVo getProductByKey(SearchProductVo searchProductVo, PageInfo pageInfo) throws Exception {
		// TODO Auto-generated method stub
		List<ProductBean> productList = productService.getSearchProductBeans(searchProductVo, pageInfo);
		List<ProductVo> productVoList = new ArrayList<ProductVo>();
		if(productList!=null){
			for(int i = 0;i<productList.size();i++){
				ProductBean pb = productList.get(i);
				ProductVo pv = new ProductVo();
				pv.setProduct(pb);
				PriceVo price = getPriceVo(pb.getProductId());
				if(price != null){
					pv.setPriceVo(price);
				}
				ProductConfigsVo productConfigs = getProductConfigsVo(pb.getProductId(), pb.getCatId());
				if (productConfigs != null && productConfigs.getMainShow()!=null && productConfigs.getMainShow().getConfigs()!=null) {
					for (ProductConfigVo config : productConfigs.getMainShow().getConfigs()) {
						List<ImageBean> imgs = getConfigImgs(config.getProductConfigBean().getProductConfigId());
						if (imgs != null && imgs.size()>0) {
							config.setImageUrl(RewriteUtil.getImageUrl(imgs.get(0).getImageUrl(), "m"));
						}
					}
				}
				if (productConfigs != null) {
					pv.setProductConfigs(productConfigs);
					productConfigs.setPrice(price);
				}
				productVoList.add(pv);
				ImageBean image = getFirstProdImageBean(pb.getProductId());
				pv.setFirstImage(image);
			}
		}
		searchProductVo.setProductList(productVoList);
		List<CustomCategoryBean> categoryList = getSearchProductCatByKey(searchProductVo);
		searchProductVo.setCategoryList(categoryList);
		return searchProductVo;
	}
	
	/**
	 * 搜索产品
	 * 
	 * @param searchProductVo
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<ProductBean> searchProductBeans(SearchProductVo searchProductVo, PageInfo pageInfo) throws Exception {
		List<ProductBean> productList = productService.getSearchProductBeans(searchProductVo, pageInfo);
		return productList;
	}
	
	/**
	 * 通过关键词搜索产品的分类聚合
	 * @author:  ZhouRongyu
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<CustomCategoryBean> getSearchProductCatByKey(SearchProductVo searchProductVo) throws Exception {
		// TODO Auto-generated method stub
		List<CustomCategoryBean> categoryList = productService.getSearchProductCat(searchProductVo);
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		List<CustomCategoryBean> categories = new ArrayList<CustomCategoryBean>();
		for(int i=0;i<categoryList.size();i++){
			CustomCategoryBean cb = categoryList.get(i);
			String catId = "";
			if(StringUtil.isEmpty(searchProductVo.getCatId())){
				catId = cb.getCatId().substring(0, 3);
			}else{
				if(searchProductVo.getCatId().length() < 12 && cb.getCatId().length()>=3+searchProductVo.getCatId().length()){
					catId = cb.getCatId().substring(0, 3+searchProductVo.getCatId().length());
				}else{
					catId = searchProductVo.getCatId();
				}
			}
			int count = cb.getProductCount();
			if(hm.containsKey(catId)){
				hm.put(catId, hm.get(catId)+count);
			}else{
				hm.put(catId, count);
				if (!catId.equals(searchProductVo.getCatId())) {
					CustomCategoryBean cat = new CustomCategoryBean();
					cat.setCatId(catId);
					cat = sellerService.getCustomCategoryBean(cat);
					categories.add(cat);
				}
			}
		}
		for (CustomCategoryBean cat : categories) { 
			String catId = cat.getCatId();
			int count = hm.get(catId);
			cat.setProductCount(count);
		}
		if(StringUtils.isNotEmpty(searchProductVo.getCatId())){
			String catId = searchProductVo.getCatId();
			int len = 3;
			List<CustomCategoryBean> parents = new ArrayList<CustomCategoryBean>();
			while (catId.length() >= len) {
				CustomCategoryBean cat = new CustomCategoryBean();
				cat.setCatId(catId.substring(0, len));
				cat = sellerService.getCustomCategoryBean(cat);
				parents.add(cat);
				len += 3;
			}
			if (parents.size()>0) {
				categories.addAll(0, parents);
			}
		}
		return categories;
	}
	
	/**
	 * 使用产品规格属性
	 * 
	 * @param ProductStandardBean
	 * @return
	 * @throws Exception
	 */
	public ProductStandardBean getProductStandard(ProductStandardBean productStandard) throws Exception{
		List<ProductStandardBean> productStandards = productService.getProductStandardBeans(productStandard);
		if(productStandards!=null && productStandards.size()>0){
			return productStandards.get(0);
		}
		return null;
	}
	
	/**
	 * 通过产品规格获取产品属性
	 * 
	 * @param ProductStandardBean
	 * @return 
	 * @throws Exception
	 */
	public HashMap<String,AttributeBean> getProductConfigs(Long standardId) throws Exception{
		ProductStandardBean productStandardBean = new ProductStandardBean();
		productStandardBean.setStandardId(standardId);
		productStandardBean = getProductStandard(productStandardBean);
		HashMap<String,AttributeBean> configMap = new HashMap<String,AttributeBean>();
		if(productStandardBean==null){
			return null;
		}
		if(productStandardBean.getMainConfigId() != null){
			long  mainConfigId = productStandardBean.getMainConfigId();
			ProductConfigBean producconfig = new ProductConfigBean();
			producconfig.setProductConfigId(mainConfigId);
			producconfig = productService.getProductConfigBeans(producconfig).get(0);
			long attrTitleId = producconfig.getTitleAttrId();
			AttributeBean attrTitle = new AttributeBean();
			attrTitle.setAttrId(attrTitleId);
			attrTitle = dictService.getAttributeBeans(attrTitle).get(0);
			long attrValueId = producconfig.getValueAttrId();
			AttributeBean attrValue = new AttributeBean();
			attrValue.setAttrId(attrValueId);
			attrValue = dictService.getAttributeBeans(attrValue).get(0);
			configMap.put("mainTitle", attrTitle);
			configMap.put("mainValue", attrValue);
			
		}
		if(productStandardBean.getSubConfigId() != null){
			long  subConfigId = productStandardBean.getSubConfigId();
			ProductConfigBean producconfig = new ProductConfigBean();
			producconfig.setProductConfigId(subConfigId);
			producconfig = productService.getProductConfigBeans(producconfig).get(0);
			long attrTitleId = producconfig.getTitleAttrId();
			AttributeBean attrTitle = new AttributeBean();
			attrTitle.setAttrId(attrTitleId);
			attrTitle = dictService.getAttributeBeans(attrTitle).get(0);
			long attrValueId = producconfig.getValueAttrId();
			AttributeBean attrValue = new AttributeBean();
			attrValue.setAttrId(attrValueId);
			attrValue = dictService.getAttributeBeans(attrValue).get(0);
			configMap.put("subTitle", attrTitle);
			configMap.put("subValue", attrValue);
		}
		return configMap;
	}
	
	/**
	 * 修改产品有效期
	 * 
	 * @param productIds
	 * @param expiredDay
	 * @return
	 * @throws Exception
	 */
	public int updateProductExpiredDay(long productIds[], int expiredDay) throws Exception {
		int i=0;
		for (Long productId : productIds) {
			ProductBean prod = new ProductBean();
			prod.setProductId(productId);
			prod.setExpiredDay(expiredDay);
			prod.setUpdateTime(new Date());
			i+=productService.updateProductBean(prod);
		}
		return i;
	}
	
	/**
	 * 修改产品备货
	 * 
	 * @param productIds
	 * @param stockDays
	 * @return
	 * @throws Exception
	 */
	public int updateProductStockDay(long productIds[], int stockDays) throws Exception {
		int i=0;
		for (Long productId : productIds) {
			ProductBean prod = new ProductBean();
			prod.setProductId(productId);
			prod.setStockDays(stockDays);
			prod.setUpdateTime(new Date());
			i+=productService.updateProductBean(prod);
		}
		return i;
	}
	
	/**
	 * 修改产品备货
	 * 
	 * @param productIds
	 * @param addDays
	 * @return
	 * @throws Exception
	 */
	public int addProductStockDay(long productIds[], int addDays) throws Exception {
		int i=0;
		for (Long productId : productIds) {
			ProductBean prod = new ProductBean();
			prod.setProductId(productId);
			ProductBean p = productService.getProductBean(prod);
			int days = p.getStockDays() + addDays;
			if (days <= 0) {
				days = 1;
			}
			prod.setStockDays(days);
			prod.setUpdateTime(new Date());
			i+=productService.updateProductBean(prod);
		}
		return i;
	}
	
	/**
	 * 删除产品
	 * 
	 * @param productIds
	 * @return
	 * @throws Exception
	 */
	public int deleteProducts(long productIds[]) throws Exception {
		int i=0;
		for (Long productId : productIds) {
			ProductBean prod = new ProductBean();
			prod.setProductId(productId);
			prod.setProductStatus(ProductConstants.DELETE);
			prod.setUpdateTime(new Date());
			i+=productService.updateProductBean(prod);
		}
		return i;
	}
	
	/**
	 * 下架产品
	 * 
	 * @param productIds
	 * @return
	 * @throws Exception
	 */
	public int offsaleProducts(long productIds[]) throws Exception {
		int i=0;
		for (Long productId : productIds) {
			ProductBean prod = new ProductBean();
			prod.setProductId(productId);
			prod.setProductStatus(ProductConstants.OFFSALE);
			prod.setUpdateTime(new Date());
			i+=productService.updateProductBean(prod);
		}
		return i;
	}
	
	/**
	 * 恢复垃圾箱产品
	 * 
	 * @param productIds
	 * @return
	 * @throws Exception
	 */
	public int restoreProducts(long productIds[]) throws Exception {
		int i=0;
		for (Long productId : productIds) {
			ProductBean prod = new ProductBean();
			prod.setProductId(productId);
			prod.setProductStatus(ProductConstants.DRAFT);
			prod.setUpdateTime(new Date());
			i+=productService.updateProductBean(prod);
		}
		return i;
	}
	
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
	public int updateProductsName(long productIds[], String nameBefore, String nameAfter, String replaceReg, String replaceStr) throws Exception {
		int i=0;
		for (Long productId : productIds) {
			ProductBean prod = new ProductBean();
			prod.setProductId(productId);
			ProductBean p = productService.getProductBean(prod);
			String productName = p.getProductName();
			if (StringUtils.isNotEmpty(nameBefore)) {
				productName = nameBefore + productName;
			}
			if (StringUtils.isNotEmpty(nameAfter)) {
				productName = productName + nameAfter;
			}
			if (StringUtils.isNotEmpty(replaceReg) && StringUtils.isNotEmpty(replaceStr)) {
				productName = productName.replaceAll(replaceReg, replaceStr);
			}
			prod.setProductName(productName);
			prod.setUpdateTime(new Date());
			i+=productService.updateProductBean(prod);
		}
		return i;
	}
	
	/**
	 * 修改包装信息
	 * 
	 * @param productIds
	 * @param ispackage
	 * @param packing
	 * @return
	 * @throws Exception
	 */
	public int updateProductPacking(long productIds[], int ispackage, String packing, int quantity) throws Exception {
		int i=0;
		for (Long productId : productIds) {
			ProductBean prod = new ProductBean();
			prod.setProductId(productId);
			prod.setIspackage(ispackage);
			prod.setPacking(packing);
			prod.setQuantity(quantity);
			prod.setUpdateTime(new Date());
			i+=productService.updateProductBean(prod);
		}
		return i;
	}
	
	/**
	 * 修改分类
	 * 
	 * @param productIds
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public int updateProductCategory(long productIds[], String catId) throws Exception {
		int i=0;
		for (Long productId : productIds) {
			ProductBean prod = new ProductBean();
			prod.setProductId(productId);
			prod.setCustomCatId(catId);
			prod.setUpdateTime(new Date());
			i+=productService.updateProductBean(prod);
		}
		return i;
	}
	
	/**
	 * 修改产品重量
	 * 
	 * @param productIds 产品编号
	 * @param addWeight 修改的重量
	 * @param editFlag 修改方法标识，0-改成统一值，1-按值修改，2-按百分比修改
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> updateProductWeight(long productIds[], double addWeight, int editFlag) throws Exception {
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String,Object>>();
		for (Long productId : productIds) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			ProductBean prod = new ProductBean();
			prod.setProductId(productId);
			ProductBean p = new ProductBean();
			p.setProductId(productId);
			p = productService.getProductBean(p);
			double weight = p.getWeight();
			if (editFlag == 0) {
				weight = addWeight;
			} else if (editFlag == 1) {
				weight += addWeight;
			} else if (editFlag == 2) {
				weight = weight * (1+addWeight/100.0);
			}
			weight = CullNumUtil.cullNum(weight, 3);
			prod.setWeight(weight);
			prod.setUpdateTime(new Date());
			productService.updateProductBean(prod);
			map.put("productId", productId);
			map.put("weight", weight);
			maps.add(map);
		}
		return maps;
	}
	
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
	public int updateProductSize(long productIds[], int length, int width, int height) throws Exception {
		int i=0;
		for (Long productId : productIds) {
			ProductBean prod = new ProductBean();
			prod.setProductId(productId);
			prod.setLength(length);
			prod.setWidth(width);
			prod.setHeight(height);
			prod.setUpdateTime(new Date());
			i+=productService.updateProductBean(prod);
		}
		return i;
	}
	
	/**
	 * 修改产品价格
	 * 
	 * @param productIds 产品编号
	 * @param addPrice 修改的价格
	 * @param abs false-按百分比修改，true-按值修改
	 * @return
	 * @throws Exception
	 */
	public int updateProductPrice(long productIds[], double addPrice, boolean abs) throws Exception {
		int i=0;
		for (Long productId : productIds) {
			PriceBean priceBean = new PriceBean();
			priceBean.setProductId(productId);
			PriceBean p = new PriceBean();
			p.setProductId(productId);
			p = productService.getPriceBean(p);
			double price = p.getPrice();
			if (abs) {
				price += addPrice;
			} else {
				price = price * (1+addPrice/100.0);
			}
			price = CullNumUtil.cullNum(price);
			priceBean.setPrice(price);
			i+=productService.updatePriceBean(priceBean);
			// 判断规格
			ProductStandardBean standard = new ProductStandardBean();
			standard.setProductId(productId);
			List<ProductStandardBean> standards = productService.getProductStandardBeans(standard);
			if (standards != null) {
				for (ProductStandardBean st : standards) {
					price = st.getPrice();
					if (abs) {
						price += addPrice;
					} else {
						price = price * (1+addPrice/100.0);
					}
					price = CullNumUtil.cullNum(price);
					st.setPrice(price);
					productService.updateProductStandardBean(st);
				}
			}
		}
		return i;
	}
	
	/**获取某个买家的favorite产品
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<ProductVo> getBuyerFavorite(long buyerId,PageInfo pageInfo) throws Exception{
		List<ProductVo> favoriteList = new ArrayList<ProductVo>();
		BuyerProductListBean buyerProductList = new BuyerProductListBean();
		buyerProductList.setBuyerId(buyerId);
		Integer count = productService.getBuyerFavoriteCount(buyerProductList);
		if(count<=0){
			return null;
		}
		pageInfo.setRecordCount(count);
		buyerProductList.setPageInfo(pageInfo);
		List<ProductBean> listProducts = productService.getBuyerFavorite(buyerProductList);
		if(listProducts!=null&&listProducts.size()>0){
			for(ProductBean pb :listProducts){
				ProductVo prod = new ProductVo();
				prod = getProductVo(pb.getProductId());
				prod.getProduct().setCreateTime(pb.getCreateTime());
				ImageBean image = getFirstProdImageBean(pb.getProductId());
				prod.setFirstImage(image);
				PriceVo price = getPriceVo(pb.getProductId());
				prod.setPriceVo(price);
				favoriteList.add(prod);
			}
			return favoriteList;
		}
		return null;
	}
	
	/**删除某个买家的某个喜欢的产品
	 * @param buyerId
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public Integer deleteBuyerFavorite(long buyerId,long productId) throws Exception{
		BuyerProductListBean buyerProduct = new BuyerProductListBean();
		buyerProduct.setBuyerId(buyerId);
		buyerProduct.setProductId(productId);
		buyerProduct = productService.getBuyerProductListBean(buyerProduct);
		return productService.deleteBuyerProductListBean(buyerProduct);
	}
	
	/**
	 * 搜索产品配置属性的聚合
	 * 
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public List<SearchAttrVo> searchProductConfs(SearchProductVo searchVo) throws Exception {
		List<SearchAttrVo> configs = productService.getSearchConfigs(searchVo);
		if (configs != null && configs.size()>0) {
			return formatSearchAttrVo(configs);
		}
		return null;
	}
	
	/**
	 * 搜索产品全部属性的聚合
	 * 
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public List<SearchAttrVo> searchProductAttrs(SearchProductVo searchVo) throws Exception {
		List<SearchAttrVo> attrs = productService.getSearchAttributes(searchVo);
		if (attrs != null && attrs.size()>0) {
			return formatSearchAttrVo(attrs);
		}
		return null;
	}

	/**
	 * @param list
	 * @return
	 * @throws Exception
	 */
	private List<SearchAttrVo> formatSearchAttrVo(List<SearchAttrVo> list)
			throws Exception {
		List<SearchAttrVo> resultList = new ArrayList<SearchAttrVo>();
		HashMap<Long, SearchAttrVo> map = new HashMap<Long, SearchAttrVo>();
		for (SearchAttrVo searchAttr : list) {
			Long titleId = searchAttr.getTitleId();
			SearchAttrVo node = map.get(titleId);
			if (node == null) {
				node = new SearchAttrVo();
				node.setAttrId(titleId);
				AttributeBean attr = new AttributeBean();
				attr.setAttrId(titleId);
				attr = dictService.getAttributeBean(attr);
				node.setAttrValue(attr.getValue());
				node.setNodes(new ArrayList<SearchAttrVo>());
				map.put(titleId, node);
				resultList.add(node);
			}
			List<SearchAttrVo> nodes = node.getNodes();
			nodes.add(searchAttr);
		}
		return resultList;
	}
	
	/**
	 * 获取产品属性
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrBean> getProductAttrs(long productId) throws Exception {
		ProductAttrBean prodAttr = new ProductAttrBean();
		prodAttr.setProductId(productId);
		return productService.getProductAttrBeans(prodAttr);
	}
	
	/**获取买家购买过产品
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<ProductVo> getBuyerBuyProducts(long buyerId,PageInfo pageInfo) throws Exception{
		List<ProductVo> listProduct = new ArrayList<ProductVo>();
		Integer count = productService.getBuyerBuyProductCount(buyerId);
		if(count<=0){
			return null;
		}
		pageInfo.setRecordCount(count);
		List<ProductBean> listProducts = productService.getBuyerBuyProducts(buyerId, pageInfo);
		if(listProducts!=null&&listProducts.size()>0){
			for(ProductBean pb :listProducts){
				ProductVo prod = new ProductVo();
				prod = getProductVo(pb.getProductId());
				ImageBean image = getFirstProdImageBean(pb.getProductId());
				if(image!=null){
					prod.setFirstImage(image);
				}
				PriceVo price = getPriceVo(pb.getProductId());
				if(price!=null){
					prod.setPriceVo(price);
				}
				listProduct.add(prod);
			}
			return listProduct;
		}
		return null;
	}
	
	/**
	 * 获取自定义分类产品总数
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public int getCustomCatProductCount(String catId) throws Exception {
		return productService.getCustomCatProductCount(catId);
	}
	
	/**
	 * 获取产品状态数量统计
	 * 
	 * @return
	 */
	public List<ProductCountVo> getProductStatusCount() throws Exception {
		return productService.getProductStatusCount();
	}
	
	/**
	 * 更新单个产品
	 * 
	 * @return
	 */
	public int updateProductBean(ProductBean product) throws Exception{
		return productService.updateProductBean(product);
	}
	
	/**
	 * 产品添加图片
	 * 
	 * @return
	 */
	public void insertProductImage(ProductImageBean pib) throws Exception{
		productService.insertProductImageBean(pib);
	}
	
	/**
	 * 产品删除图片
	 * 
	 * @return
	 */
	public void deleteProductImage(ProductImageBean pib) throws Exception{
		productService.deleteProductImageBean(pib);
	}
	
	/**
	 * 获取分类下热销产品
	 * 
	 * @return
	 */
	public List<ProductVo> getHotProducts(ProductBean product) throws Exception{
		List<ProductBean> pbs = productService.getHotProducts(product);
		List<ProductVo> pvs = new ArrayList<ProductVo>();
		for(int i=0;i<pbs.size();i++){
			ProductBean pb = pbs.get(i);
			ProductVo pv = new ProductVo();
			pv.setProduct(pb);
			ImageBean image = getFirstProdImageBean(pb.getProductId());
			pv.setFirstImage(image);
			PriceVo price = getPriceVo(pb.getProductId());
			pv.setPriceVo(price);
			pvs.add(pv);
		}
		return pvs;
	}
	
	/**查询最近15天的热销产品
	 * @param searchProductVo
	 * @return
	 * @throws Exception
	 */
	public List<ProductVo> getHotProductsByTime(SearchProductVo searchProductVo) throws Exception{        
		List<ProductBean> listProds = productService.getHotProductsByTime(searchProductVo);
		List<ProductVo> prodList = new ArrayList<ProductVo>();
		for(ProductBean p :listProds){
			ProductVo pv = new ProductVo();
			pv = getProductVo(p.getProductId());
			ImageBean image = getFirstProdImageBean(p.getProductId());
			pv.setFirstImage(image);
			PriceVo price = getPriceVo(p.getProductId());
			pv.setPriceVo(price);
			prodList.add(pv);
		}
		return prodList;
	}
	
	/**
	 * 添加赠品活动
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public void addGiftsActivity(ActivityBean activity,List list) throws Exception{
		productService.insertActivityBean(activity);
		for(int i=0;i<list.size();i++){
			GiftsBean gb = (GiftsBean)list.get(i);
			gb.setActivityId(activity.getActivityId());
			productService.insertGiftsBean(gb);
		}
		
	}
	
	/**
	 * 更新赠品活动
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public void updateGiftsActivity(ActivityBean activity,List list) throws Exception{
		productService.updateActivityBean(activity);
		GiftsBean gifts = new GiftsBean();
		gifts.setActivityId(activity.getActivityId());
		productService.deleteGiftsBean(gifts);
		for(int i=0;i<list.size();i++){
			GiftsBean gb = (GiftsBean)list.get(i);
			gb.setActivityId(activity.getActivityId());
			productService.insertGiftsBean(gb);
		}
		
	}
	
	
	/**
	 * 添加组合销售活动
	 * 
	 * @return
	 */
	public void addGroupActivity(ActivityBean activity, GroupsBean groups) throws Exception{
		productService.insertActivityBean(activity);
		groups.setActivityId(activity.getActivityId());
		productService.insertGroupsBean(groups);
	}
	
	/**
	 * 更新组合销售活动
	 * 
	 * @return
	 */
	public void updateGroupActivity(ActivityBean activity, GroupsBean groups) throws Exception{
		productService.updateActivityBean(activity);
		GroupsBean group = new GroupsBean();
		group.setActivityId(activity.getActivityId());
		productService.deleteGroupsBean(group);
		groups.setActivityId(activity.getActivityId());
		productService.insertGroupsBean(groups);
	}
	
	/**
	 * 更新活动信息
	 * 
	 * @return
	 */
	public int updateActivity(ActivityBean activity) throws Exception{
		return productService.updateActivityBean(activity);
	}
	
	
	/**查询活动列表
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public List<ActivityBean> getActivityList(ActivityBean activity, PageInfo pageInfo) throws Exception{
		
		return productService.getActivityBeans(activity, pageInfo);
	}
	
	/**查询组合销售
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public GroupsBean getGroupsBean(String activityId) throws Exception{
		GroupsBean groups = new GroupsBean();
		groups.setActivityId(activityId);
		return productService.getGroupsBean(groups);
	}
	
	
	/**查询赠品活动列表
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public List<GiftsBean> getGiftsList(String activityId) throws Exception{
		GiftsBean gifts = new GiftsBean();
		gifts.setActivityId(activityId);
		return productService.getGiftsBeans(gifts);
	}

	/**
	 * 获取搬家任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ProdMoveMissionBean> getMoveMissions() throws Exception {
		return productService.getProdMoveMissionBeans(new ProdMoveMissionBean());
	}
	
	/**
	 * 增加搬家任务
	 * 
	 * @param website
	 * @param storeUrl
	 * @return
	 * @throws Exception
	 */
	public ProdMoveMissionBean addMoveMission(String website, String storeUrl, String storeCode) throws Exception {
		ProdMoveMissionBean mission = new ProdMoveMissionBean();
		mission.setStoreUrl(storeUrl);
		mission.setWebsite(website);
		ProdMoveMissionBean m = productService.getProdMoveMissionBean(mission);
		if (m == null) {
			String code = MD5Util.calcMD5(Math.random()*10 + StringUtil.getDateString(new Date(),"yyyyMMddHHmmssSSS" + Math.random()*10));
			mission.setStoreCode(storeCode);
			mission.setVerifyCode(code);
			mission.setVerifyStatus(0);
			mission.setCreateTime(new Date());
			productService.insertProdMoveMissionBean(mission);
		} else {
			mission = m;
		}
		return mission;
	}
	
	/**
	 * 激活搬家任务
	 * 
	 * @param verifyUrl
	 * @param missionId
	 * @return
	 * @throws Exception
	 */
	public boolean activeMoveMission(String verifyUrl, long missionId) throws Exception {
		ProdMoveMissionBean mission = new ProdMoveMissionBean();
		mission.setMissionId(missionId);
		mission = productService.getProdMoveMissionBean(mission);
		return false;
	}
	
	/**
	 * @param website
	 * @param storeUrl
	 * @return
	 * @throws Excepiton
	 */
	public ProdMoveMissionBean getMoveMission(String website, String storeUrl) throws Exception {
		ProdMoveMissionBean mission = new ProdMoveMissionBean();
		mission.setStoreUrl(storeUrl);
		mission.setWebsite(website);
		ProdMoveMissionBean m = productService.getProdMoveMissionBean(mission);
		return m;
	}
	
	/**
	 * @param missionId
	 * @return
	 * @throws Excepiton
	 */
	public ProdMoveMissionBean getMoveMission(long missionId) throws Exception {
		ProdMoveMissionBean mission = new ProdMoveMissionBean();
		mission.setMissionId(missionId);
		ProdMoveMissionBean m = productService.getProdMoveMissionBean(mission);
		return m;
	}
	
	/**
	 * @param mission
	 * @return
	 * @throws Exception
	 */
	public int updateMoveMission(ProdMoveMissionBean mission) throws Exception {
		return productService.updateProdMoveMissionBean(mission);
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
		return productService.insertStockoutBean(stockout);
	}

	/**
	 * 修改缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public int updateStockoutBean(StockoutBean stockout) throws Exception {
		return productService.updateStockoutBean(stockout);
	}

	/**
	 * 获取缺货登记
	 * 
	 * @param stockout
	 * @return
	 * @throws Exception
	 */
	public StockoutBean getStockoutBean(StockoutBean stockout) throws Exception {
		return productService.getStockoutBean(stockout);
	}

}
