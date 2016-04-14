/**
 * ProductUploadImage.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.bean.PriceBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.manager.IProductUploadManager;
import com.fd.fashion.product.vo.ProductUploadVo;
import com.fd.util.JSONUtil;
import com.fd.util.RewriteUtil;
import com.fd.util.StringUtil;
import com.fd.util.WebPropUtil;

/**
 * @CreateDate:  2013-3-19 上午11:34:31 
 * @author:  ZhouRongyu
 * @Description:  产品图片上传Action
 * 
 * @version:  V1.0
 */
public class ProductBathUploadFile extends SecureAction {
	@SuppressWarnings("unused")
	public void doPerform(RunData data, Context context) throws Exception {
		//文件保存目录路径
		WebPropUtil wpu = new WebPropUtil();
		String savePath = wpu.get("image.path.root");

		PrintWriter out = data.getResponse().getWriter();
		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("file", "csv,xls,xlsx");
		
		data.getResponse().setContentType("text/html; charset=UTF-8");
		
		if(!ServletFileUpload.isMultipartContent(data.getRequest())){
			out.println(JSONUtil.htmlEncode("请选择文件。"));
			return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			out.println(JSONUtil.htmlEncode("上传目录不存在。"));
			return;
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			out.println(JSONUtil.htmlEncode("上传目录没有写权限。"));
			return;
		}

		String dirName = data.getRequest().getParameter("dir");
		if (dirName == null) {
			dirName = "file";
		}
		if(!extMap.containsKey(dirName)){
			out.println(JSONUtil.htmlEncode("目录名不正确。"));
			return;
		}
		//创建文件夹
		savePath += "/" + dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		FileItem item =(FileItem) data.getParameters().getFileItem("file");
		upload.setHeaderEncoding("UTF-8");

		String fileName = item.getName();
		long fileSize = item.getSize();
		if (!item.isFormField()) {
			//检查扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
				out.println(JSONUtil.htmlEncode("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
				return;
			}

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			long dateTime = System.nanoTime();
			String newFileName = dateTime + "." + fileExt;
			ImageBean ib =new ImageBean();
			try{
				File uploadedFile = new File(savePath, newFileName);
				item.write(uploadedFile);
				
			}catch(Exception e){
				out.println(JSONUtil.htmlEncode("上传文件失败。"));
				e.printStackTrace();
				return;
			}
			String url = savePath + newFileName;


			//冲突保存文件
			String tmpPath = "tmp.csv";
			String rootPath = wpu.get("image.path.root");
			String csvFilePath = rootPath + "/" + tmpPath;
			OutputStreamWriter out2 = new OutputStreamWriter(new FileOutputStream(csvFilePath), Charset.forName("GBK"));  
			//CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath), ',');
			CSVWriter writer = new CSVWriter(out2, ',');

			
			
			//读出所有行
			InputStreamReader in = new InputStreamReader(new FileInputStream(url), Charset.forName("GBK"));   
			CSVReader reader = new CSVReader(in);
			String[] head = reader.readNext();
			List<String[]> csvList = reader.readAll();
			reader.close();
			//写入头
			//wr.writeRecord(head);
			writer.writeNext(head);
			IProductManager productManager = (IProductManager)this.getBean("productManager");
			IProductUploadManager productUploadManager = (IProductUploadManager)this.getBean("productUploadManager");
			int csvCount = 0;
			for(int row=0;row<csvList.size();row++){
				String[] productrow = csvList.get(row);
                String skutmp = productrow[2]; //取得第row行第0列的数据
                ProductBean pb = productManager.getProductBeanBySku(skutmp);
                if(pb != null){
                	//wr.writeRecord(csvList.get(row));
                	writer.writeNext(csvList.get(row));
                	csvCount++;
                }else{
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
                	productUploadManager.addProductInfo(productUpload);
                	
                }

            }
			 writer.close();

			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", savePath + newFileName);
			obj.put("csvFilePath", RewriteUtil.getImageUrl(csvFilePath));
			obj.put("tmpPath", tmpPath);
			obj.put("csvCount", csvCount);
			obj.put("id", ib.getImageId());
			out.println(obj.toString());
		}
		
	}
	

}
