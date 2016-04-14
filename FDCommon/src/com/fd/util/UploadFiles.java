package com.fd.util;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.upload.FormFile;

public class UploadFiles {

	/**
	 * @describe:上传文件
	 * @param request
	 * @param form
	 * @return  胡杰清
	 * @throws Exception
	 */
	public String UploadFileTemplate(FileItem fileurl,String name,
			String url) throws Exception {
		String tempFileExt = fileurl.getName();
		mkdirFile(url);
		if (!tempFileExt.equals("")) {
			String fileExt = "";
			fileExt += tempFileExt.substring(tempFileExt.lastIndexOf("."));// 取得后缀名
			
			InputStream stream = fileurl.getInputStream();// 把文件读入
			
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			String datestr = sdf.format( new Date()); 

			String filePath = url+name+datestr+fileExt;
			//ByteArrayOutputStream baos = new ByteArrayOutputStream();
			OutputStream bos = new FileOutputStream(filePath);// 建立一个上传文件的输出流
			
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);// 将文件写入服务器
			}
			bos.close();
			stream.close();
			return filePath;
		} else {
			return "";
		}
	}

	/**
	 * 用于创建文件夹的方法
	 * hujieqing
	 * @param mkdirName
	 */
	public void mkdirFile(String uploadFile) throws Exception {
//		String mkdirName = uploadImgeVo.getImgFileDir();
//		String url = uploadImgeVo.getImgDirConstants() + "//";
		// 文件路径
		File dirFile = new File(uploadFile);
		
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		boolean bFile = dirFile.exists();
		
		if (!bFile) {
			bFile = dirFile.mkdir();
			if (!bFile) {
				throw new RuntimeException("文件夹创建失败，请确认磁盘没有写保护并且空间足够");
			}
		}
	}
	
	
}
