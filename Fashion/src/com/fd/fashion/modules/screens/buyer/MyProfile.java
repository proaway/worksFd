package com.fd.fashion.modules.screens.buyer;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.modules.screens.SecureScreen;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.ImageUtil;
import com.fd.util.RewriteUtil;
import com.fd.util.WebPropUtil;

public class MyProfile extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/BuyerAccountLayout.html");
		FdSession session = FdSessionFactory.getFdSession(data);
		
		String loginError = (String)session.getAttribute(SessionConstants.KEY_LOGIN_ERROR);
		int loginErrorCount = 0;
		if (StringUtils.isNotEmpty(loginError)) {
			loginErrorCount = Integer.valueOf(loginError);
		}
		context.put("loginErrorCount", loginErrorCount);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		if(buyer!=null && buyer.getImageUrl()!=null){
			String imageUrl = RewriteUtil.getImageUrl(buyer.getImageUrl(),"m");
			buyer.setImageUrl(imageUrl);
		}
		String actionFlag = data.getParameters().getString("actionFlag");
		if(actionFlag==null || "".equals(actionFlag)){
			context.put("buyerInfo", buyer);
		}else if(actionFlag.equals("uploadImage")){
			WebPropUtil wpu = new WebPropUtil();
			String savePath = wpu.get("image.path.root");
			String saveUrl  = wpu.get("website.imagehttproot") + "/";
			data.getResponse().setContentType("text/html; charset=UTF-8");
			/*String dirName = data.getRequest().getParameter("dir");
			if (dirName == null) {
				dirName = "image";
			}*/
			//创建文件夹
			//savePath += "/" + dirName + "/";
			savePath += "/";
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			savePath += "buyer"+"/"+ymd + "/";
			saveUrl += "buyer"+"/"+ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			FileItem item =(FileItem) data.getParameters().getFileItem("imgOldUrl");
			upload.setHeaderEncoding("UTF-8");
			String fileName = item.getName();
			if (!item.isFormField()) {
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				long dateTime = System.nanoTime();
				//String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				String newFileName = dateTime + "." + fileExt;
				try{
					//long nanoTime = System.nanoTime();
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
					ImageUtil.cutProductImage(uploadedFile.getPath());
				}catch(Exception e){
					context.put("msg", "上传文件失败。");
					e.printStackTrace();
					return;
				}
				context.put("url", savePath+newFileName);
				buyer.setFirstName(data.getParameters().getString("firstName")) ;
				buyer.setLastName(data.getParameters().getString("lastName"));
				buyer.setSex(Integer.parseInt(data.getParameters().getString("sex")));
				buyer.setCompany(data.getParameters().getString("company"));
				buyer.setBuyerType(Integer.parseInt(data.getParameters().getString("buyerType")));
				buyer.setImageUrl(saveUrl + newFileName);
				context.put("buyerInfo", buyer);
			}
		}
		
	}

}
