/**
 * ProductUploadManager.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.product.manager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fd.fashion.dict.service.IDictService;
import com.fd.fashion.product.bean.PriceBean;
import com.fd.fashion.product.bean.ProductAttrBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.bean.ProductConfImgBean;
import com.fd.fashion.product.bean.ProductConfigBean;
import com.fd.fashion.product.bean.ProductImageBean;
import com.fd.fashion.product.bean.ProductStandardBean;
import com.fd.fashion.product.service.IProductService;
import com.fd.fashion.product.vo.ProductUploadVo;
import com.fd.fashion.seller.service.ISellerService;
import com.fd.util.FileUtil;
import com.fd.util.WebPropUtil;

/**
 * @CreateDate:  2013-3-26 下午8:29:43 
 * @author:  ZhouRongyu
 * @Description:  上传产品Manager
 * 
 * @version:  V1.0
 */
@Transactional(rollbackFor = Exception.class)
@Component
public class ProductUploadManager implements IProductUploadManager {
	
	@Autowired
	@Qualifier("productService")
	IProductService productService;

	@Autowired
	@Qualifier("sellerService")
	ISellerService sellerService;

	@Autowired
	@Qualifier("dictService")
	IDictService dictService;

	private String saveSpecifications(String content, String path){
		String savePath = path;
		if (StringUtils.isEmpty(savePath)) {
			WebPropUtil wpu = new WebPropUtil();
			savePath = wpu.get("image.path.root");
	
			//创建文件夹
			savePath += "/specifications/";
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			savePath += ymd ;
			long dateTime = System.nanoTime();
			savePath += "/" + String.valueOf(dateTime) + ".txt";
		}
		try {
			FileUtil.writeFile(content, savePath, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savePath;
	}
	
	/**
	 * 上传产品提交方法入口
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public int addProductInfo(ProductUploadVo productUpload) throws Exception{
		ProductBean pb = productUpload.getProductBean();

		//保存产品详细描述
		String specifications = pb.getSpecifications();
		pb.setSpecifications(saveSpecifications(specifications, null));
		
		pb.setUpdateTime(new Date());
		productService.insertProductBean(pb);
		// 图片
		addProdPhotos(productUpload, pb);
		// 属性、规格
		addProdAttrStandards(productUpload, pb);
		// 价格
		PriceBean price = productUpload.getPrice();
		price.setProductId(pb.getProductId());
		productService.insertPriceBean(price);
		
		return 0;
	}
	
	/**
	 * 上传产品图片信息
	 * 
	 * @param pib
	 * @return
	 * @throws Exception
	 */
	public void addProductImages(ProductImageBean pib) throws Exception{
		productService.insertProductImageBean(pib);
	}
	
	/**
	 * 添加产品Attr属性
	 * 
	 * @param pab
	 * @return
	 * @throws Exception
	 */
	public void addProductAttr(ProductAttrBean pab) throws Exception{
		productService.insertProductAttrBean(pab);
	}
	
	/**
	 * 添加产品Config属性
	 * 
	 * @param pcb
	 * @return
	 * @throws Exception
	 */
	public void addProductConfig(ProductConfigBean pcb) throws Exception{
		productService.insertProductConfigBean(pcb);
	}
	
	/**
	 * 添加产品Standard属性
	 * 
	 * @param psb
	 * @return
	 * @throws Exception
	 */
	public void addProductStandard(ProductStandardBean psb) throws Exception{
		productService.insertProductStandardBean(psb);
	}
	
	/**
	 * 添加产品批发折扣价格
	 * 
	 * @param priceBean
	 * @return
	 * @throws Exception
	 */
	public void addProductPrice(PriceBean price) throws Exception{
		productService.insertPriceBean(price);
	}
	
	/**
	 * 添加产品ConfImg
	 * 
	 * @param priceBean
	 * @return
	 * @throws Exception
	 */
	public void addProductConfImg(ProductConfImgBean productConfImg) throws Exception{
		productService.insertProductConfImgBean(productConfImg);
	}
	
	/**
	 * 更新上传产品
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public int updateProductInfo(ProductUploadVo productUpload, ProductBean product) throws Exception{
		Long productId = product.getProductId();
		String specificationsPath = product.getSpecifications();

		ProductBean pb = productUpload.getProductBean();
		pb.setProductId(productId);
		//保存产品详细描述
		String specifications = pb.getSpecifications();
		pb.setSpecifications(saveSpecifications(specifications, null));
		//重写详细文件
		FileUtil.writeFile(specifications,specificationsPath , "UTF-8");

		pb.setCreateTime(product.getCreateTime());
		pb.setCreator(product.getCreator());
		pb.setSpecifications(specificationsPath);
		
		//删除旧产品信息
		deleteProductInfo(productId);
		//插入新产品
		productService.insertProductBean(pb);
		
		//插入新价格信息
		PriceBean price = productUpload.getPrice();
		price.setProductId(pb.getProductId());
		productService.insertPriceBean(price);

		// 图片
		addProdPhotos(productUpload, pb);
		// 属性、规格
		addProdAttrStandards(productUpload, pb);
		
		return 0;
	}

	/**
	 * @param productUpload
	 * @param pb
	 * @throws Exception
	 */
	private void addProdPhotos(ProductUploadVo productUpload, ProductBean pb)
			throws Exception {
		// 图片
		if(null != productUpload.getImages()){
			for (ProductImageBean pimg : productUpload.getImages()) {
				pimg.setProductId(pb.getProductId());
				productService.insertProductImageBean(pimg);
			}
		}
	}

	/**
	 * @param productUpload
	 * @param pb
	 * @throws Exception
	 */
	private void addProdAttrStandards(ProductUploadVo productUpload,
			ProductBean pb) throws Exception {
		// 普通属性
		if(null != productUpload.getAttrs()){
			for (ProductAttrBean pattr : productUpload.getAttrs()) {
				pattr.setProductId(pb.getProductId());
				productService.insertProductAttrBean(pattr);
			}
		}
		List<ProductConfigBean> mainConfigs = new ArrayList<ProductConfigBean>();
		// 配置属性
		if(null != productUpload.getMainConfigs()){
			mainConfigs = productUpload.getMainConfigs();
			for (ProductConfigBean pconf : mainConfigs) {
				pconf.setProductId(pb.getProductId());
				productService.insertProductConfigBean(pconf);
				// 主属性图片
				for (ProductConfImgBean pcimg : pconf.getImages()) {
					pcimg.setProductConfigId(pconf.getProductConfigId());
					productService.insertProductConfImgBean(pcimg);
				}
			}
		}
		List<ProductConfigBean> subConfigs = new ArrayList<ProductConfigBean>();
		if(null != productUpload.getSubConfigs()){
			subConfigs = productUpload.getSubConfigs();
			for (ProductConfigBean pconf : subConfigs) {
				pconf.setProductId(pb.getProductId());
				productService.insertProductConfigBean(pconf);
			}
		}
		// 规格
		if(null != productUpload.getStandards()){
			List<ProductStandardBean> standards = productUpload.getStandards();
			if (mainConfigs.size()>0) {
				if (subConfigs.size()>0) {
					int k = 0;
					for(int i=0; i<mainConfigs.size(); i++) {
						for(int j=0; j<subConfigs.size(); j++) {
							standards.get(k).setMainConfigId(mainConfigs.get(i).getProductConfigId());
							standards.get(k).setSubConfigId(subConfigs.get(j).getProductConfigId());
							k++;
						}
					}
				} else {
					for(int i=0; i<mainConfigs.size(); i++) {
						standards.get(i).setMainConfigId(mainConfigs.get(i).getProductConfigId());
					}
				}
			} else if (subConfigs.size()>0) {
				for(int j=0; j<subConfigs.size(); j++) {
					standards.get(j).setSubConfigId(subConfigs.get(j).getProductConfigId());
				}
			}
			for (ProductStandardBean standard : standards) {
				standard.setProductId(pb.getProductId());
				productService.insertProductStandardBean(standard);
			}
		}
	}
	
	/**
	 * 删除原始产品信息
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public int deleteProductInfo(Long productId) throws Exception{
		//删除原始图片数据
		ProductImageBean pimg = new ProductImageBean();
		pimg.setProductId(productId);
		productService.deleteProductImageBean(pimg);

		
		//删除图片属性
		ProductAttrBean pattr = new ProductAttrBean();
		pattr.setProductId(productId);
		productService.deleteProductAttrBean(pattr);
		
		
		//删除图片规格及规格图片
		ProductConfigBean pconf = new ProductConfigBean();
		pconf.setProductId(productId);

		List<ProductConfigBean> configs = productService.getProductConfigBeans(pconf);
		for (ProductConfigBean pcb : configs) {
			if(null != pcb.getProductConfigId()){
				ProductConfImgBean pcimg = new ProductConfImgBean();
				pcimg.setProductConfigId(pcb.getProductConfigId());
				productService.deleteProductConfImgBean(pcimg);
			}
		}
		productService.deleteProductConfigBean(pconf);

		//删除产品规格
		ProductStandardBean producstandard = new ProductStandardBean();
		producstandard.setProductId(productId);
		productService.deleteProductStandardBean(producstandard);
		
		// 删除属性
		ProductAttrBean prodattr = new ProductAttrBean();
		prodattr.setProductId(productId);
		productService.deleteProductAttrBean(prodattr);
		
		//删除产品价格信息
		PriceBean p = new PriceBean();
		p.setProductId(productId);
		productService.deletePriceBean(p);
		
		//删除原始产品
		ProductBean pb = new ProductBean();
		pb.setProductId(productId);
		return productService.deleteProductBean(pb);
	}
}
