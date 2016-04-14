/**
 * ProductBathProductUpdate.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.product;

import java.io.FileReader;
import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import au.com.bytecode.opencsv.CSVReader;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.product.bean.PriceBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.manager.IProductUploadManager;
import com.fd.fashion.product.vo.ProductUploadVo;
import com.fd.util.StringUtil;
import com.fd.util.WebPropUtil;

/**
 * @CreateDate:  2013-4-27 下午8:37:43 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ProductBathProductUpdate extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		String tmpPath = data.getParameters().getString("tmpPath");
		//文件保存目录路径
		WebPropUtil wpu = new WebPropUtil();
		String url  = wpu.get("image.path.root") +"/"+ tmpPath;
		
		//读出所有行
		CSVReader reader = new CSVReader(new FileReader(url));
		reader.readNext();
		List<String[]> csvList = reader.readAll();
		reader.close();
		IProductManager productManager = (IProductManager)this.getBean("productManager");
		IProductUploadManager productUploadManager = (IProductUploadManager)this.getBean("productUploadManager");
		
		for(int row=0;row<csvList.size();row++){
			String[] productrow = csvList.get(row);
            String skutmp = productrow[2]; //取得第row行第0列的数据
            ProductBean pb = productManager.getProductBeanBySku(skutmp);
            if(pb != null){
            	String catId = productrow[0];
            	String customCatId = productrow[1];
            	String sku = productrow[2];
            	String productName = productrow[3];
            	String price = productrow[4];
            	String description = productrow[5];
            	String specifications = productrow[6];
            	String stock = productrow[7];
            	String weight = productrow[8];
            	String stockDays = productrow[9];
            	String length = productrow[10];
            	String width = productrow[11];
            	String height = productrow[12];
            	String packageInformation = productrow[13];
            	//String keyword = productrow[14];
            	String barcode = productrow[14];
            	String brand = productrow[15];
            	String origin = productrow[16];
            	
            	ProductBean product = new ProductBean();
            	product.setCatId(StringUtil.setCatId(catId));
            	product.setCustomCatId(StringUtil.setCatId(customCatId));
            	product.setSku(sku);
            	product.setProductName(productName);
            	product.setDescription(description);
            	product.setSpecifications(specifications);
            	product.setStock(Integer.valueOf(stock));
            	product.setWeight(Double.valueOf(weight));
            	product.setStockDays(Integer.valueOf(stockDays));
            	product.setLength(Integer.valueOf(length));
            	product.setWidth(Integer.valueOf(width));
            	product.setHeight(Integer.valueOf(height));
            	product.setPackageInformation(packageInformation);
            	//product.setKeyword(keyword);
            	product.setBarcode(barcode);
            	product.setBrand(brand);
            	product.setOrigin(origin);
            	product.setProductStatus(3);
            	product.setCreateTime(new Date());
            	product.setExpiredDay(0);
            	
            	ProductUploadVo productUpload = new ProductUploadVo();
            	productUpload.setProductBean(product);
            	PriceBean priceBean = new PriceBean();
            	priceBean.setPrice(Double.valueOf(price));
            	productUpload.setPrice(priceBean);
            	productUploadManager.updateProductInfo(productUpload,pb);
            	
            }

        }
		context.put("contentdata", 1);
	}
}
