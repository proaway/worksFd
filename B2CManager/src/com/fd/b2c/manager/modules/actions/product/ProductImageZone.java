/**
 * ProductUploadImage.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.product;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.BaseAction;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.util.WebPropUtil;

/**
 * @CreateDate:  2013-3-19 上午11:34:31 
 * @author:  ZhouRongyu
 * @Description:  产品图片上传Action
 * 
 * @version:  V1.0
 */
public class ProductImageZone extends BaseAction {
	public void doPerform(RunData data, Context context) throws Exception {
		//根目录路径，可以指定绝对路径，比如 /var/www/attached/
		//String rootPath = data.getServletContext().getRealPath("/") + "resources/";
		WebPropUtil wpu = new WebPropUtil();
		String rootPath = wpu.get("image.path.root");
		//根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
//		String rootUrl  = data.getRequest().getContextPath() + "/resources/";
		String rootUrl = wpu.get("website.imagehttproot") + "/image/";
		//图片扩展名
		String[] fileTypes = new String[]{"jpg", "jpeg"};
		PrintWriter out = data.getResponse().getWriter();
		String dirName = data.getRequest().getParameter("dir");
		if (dirName != null) {
			if(!Arrays.<String>asList(new String[]{"image"}).contains(dirName)){
				out.println("Invalid Directory name.");
				return;
			}
			rootPath += "/" + dirName + "/";
			//rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		//根据path参数，设置各路径和URL
		String path = data.getRequest().getParameter("path") != null ? data.getRequest().getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}

		//排序形式，name or size or type
		String order = data.getRequest().getParameter("order") != null ? data.getRequest().getParameter("order").toLowerCase() : "name";

		//不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			out.println("Access is not allowed.");
			return;
		}
		//最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			out.println("Parameter is not valid.");
			return;
		}
		//目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if(!currentPathFile.isDirectory()){
			out.println("Directory does not exist.");
			return;
		}

		//遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if(currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if(file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if(file.isFile()){
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				if (!fileName.contains(".s.") && !fileName.contains(".m.") && !fileName.contains(".b.") && !fileName.contains(".src.")) {
					hash.put("filename", fileName);
					hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
					fileList.add(hash);
				}
			}
		}
//		IProductManager productManager = (IProductManager) getBean("productManager");
//		List<ImageBean> images = productManager.getImageList(new ImageBean(), null);
//		for (ImageBean imageBean : images) {
//			Hashtable<String, Object> hash = new Hashtable<String, Object>();
//			String fileName = imageBean.getImageUrl();
//			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//			hash.put("is_dir", false);
//			hash.put("has_file", false);
//			hash.put("filesize", imageBean.getImageSize());
//			hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
//			hash.put("filetype", fileExt);
//			hash.put("filename", fileName);
//			hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(imageBean.getCreateTime()));
//			fileList.add(hash);
//		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		data.getResponse().setContentType("application/json; charset=UTF-8");
		out.println(result.toString());
	}

	
	public class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
			}
		}
	}
	public class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
					return 1;
				} else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}
	public class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
			}
		}
	}
}
