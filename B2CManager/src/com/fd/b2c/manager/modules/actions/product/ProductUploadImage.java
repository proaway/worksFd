/**
 * ProductUploadImage.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.product;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.BaseAction;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.bean.ProductImageBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.util.AppContextUtil;
import com.fd.util.JSONUtil;
import com.fd.util.WebPropUtil;

/**
 * @CreateDate:  2013-3-19 上午11:34:31 
 * @author:  ZhouRongyu
 * @Description:  产品图片上传Action
 * 
 * @version:  V1.0
 */
public class ProductUploadImage extends BaseAction {
	@SuppressWarnings("unused")
	public void doPerform(RunData data, Context context) throws Exception {
		//文件保存目录路径
		//String savePath = data.getServletContext().getRealPath("/") + "resources/";
		//String savePath = "d:/upload/";
		WebPropUtil wpu = new WebPropUtil();
		String savePath = wpu.get("image.path.root");
		//文件保存目录URL
//		String saveUrl  = data.getRequest().getContextPath() + "/resources/";
		String saveUrl  = wpu.get("website.imagehttproot") + "/";

		PrintWriter out = data.getResponse().getWriter();
		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "jpg,jpeg");
		
		//最大文件大小
		long maxSize = 1000000;
		
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
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			out.println(JSONUtil.htmlEncode("目录名不正确。"));
			return;
		}
		//创建文件夹
		savePath += "/" + dirName + "/";
		saveUrl +=  dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
	
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		//MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
		FileItem item =(FileItem) data.getParameters().getFileItem("imgFile");
		//File imgFile = (File) data.getRequest().getAttribute("imgFile");
		upload.setHeaderEncoding("UTF-8");
/*		List<?> items = upload.parseRequest(data.getRequest());
		Iterator<?> itr = items.iterator();
		
		
		while (itr.hasNext()) {*/
		//	FileItem item = (FileItem) itr.next();
		String fileName = item.getName();
		long fileSize = item.getSize();
		if (!item.isFormField()) {
			//检查文件大小
			if(item.getSize() > maxSize){
				out.println(JSONUtil.htmlEncode("上传文件大小超过限制。"));
				return;
			}
			//检查扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
				out.println(JSONUtil.htmlEncode("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
				return;
			}

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			long dateTime = System.nanoTime();
			//String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
			String newFileName = dateTime + "." + fileExt;
			ImageBean ib =new ImageBean();
			try{
				//long nanoTime = System.nanoTime();
				File uploadedFile = new File(savePath, newFileName);
				//item.write(uploadedFile);
				ib.setImageSize(item.getSize());
				ib.setImageUrl(savePath+newFileName);
				ib.setImageName(fileName);
				ib.setCreateTime(new Date());
				ib.setCatId("0");
				ib.setImageId(dateTime);
				//sellerID
				//ib.setOperator(sellerId);
				
				IProductManager productMnager = (IProductManager)AppContextUtil.getBean("productManager");
				productMnager.saveImage(item,uploadedFile,ib);
			}catch(Exception e){
				out.println(JSONUtil.htmlEncode("上传文件失败。"));
				e.printStackTrace();
				return;
			}

			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", saveUrl + newFileName);
			obj.put("id", ib.getImageId());
			out.println(obj.toString());
		}
	}

}
