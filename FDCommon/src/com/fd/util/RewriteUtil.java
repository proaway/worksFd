package com.fd.util;

import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

/**
 * @CreateDate: 2013-3-14 下午05:46:30
 * @author Longli
 * @Description: Rewrite规则类，包含各种rewrite之后的url写法
 * 
 */
public class RewriteUtil {
	static WebPropUtil propUtil = new WebPropUtil();
	
	/**
	 * 获取产品rewrite url
	 * 
	 * @param productName
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public static String getProductUrl(String productName, long productId) {
		productName = getShortName(productName, 50);
		String url = propUtil.getProperty("website.httproot") + "/product/"
				+ productName.replaceAll("[^a-zA-Z0-9]+", "-") + "_"
				+ productId + ".html";
		return url;
	}
	
	/**
	 * 获取产品rewrite url(String类型的ProductId)
	 * 
	 * @param productName
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public static String getProductUrl(String productName, String productId) {
		return getProductUrl(productName,Long.valueOf(productId));
	}

	
	/**
	 * 截取长度是 length 字符以内的短名称，单词之间用“-”号连接
	 * 
	 * @param str 字符串
	 * @param length 保持单词完整性，返回产品名称长度不超过 length 值
	 * @return
	 */
	public static String getShortName(String str, int length) {
		if (StringUtil.isEmpty(str)) {
			return "-";
		}
		try {
			str = str.replaceAll("[^a-zA-Z0-9]", " ");
			if (str.length() > length) {
				str = str.substring(0, str.lastIndexOf(' ',
						length));
			}
			if (str.endsWith(" ")) {
				str = str.substring(0, str.length()-1);
			}
			return str.trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 获取图片url，图片url用扩展名区分小图，中图，大图
	 * 比如，数据库图片名称 /server/upload/seller/aaaaa/1234.jpg
	 * 小图: /server/upload/seller/aaaaa/1234.s.jpg
	 * 中图: /server/upload/seller/aaaaa/1234.m.jpg
	 * 大图: /server/upload/seller/aaaaa/1234.jpg
	 * 原图: /server/upload/seller/aaaaa/1234.src.jpg
	 * 
	 * @param imagePath 传入完整的图片路径
	 * @param ext  扩展名区分小图(s)，中图(m)，大图，原图(src)
	 * @return 返回图片URL
	 * @throws Exception
	 */
	public static String getImageUrl(String imagePath, String ext) {
		if (StringUtils.isEmpty(imagePath)) {
			return "";
		}
		String root = propUtil.getProperty("website.imagehttproot");
		String imageRoot = propUtil.getProperty("image.path.root").replaceAll("\\\\", "/");
		imagePath = imagePath.replaceAll("\\\\", "/");
		imagePath = imagePath.replaceAll(imageRoot, "");
		if (StringUtils.isNotEmpty(ext)) {
			imagePath = imagePath.replaceAll("\\.([a-zA-Z]+)$", "." + ext + ".$1");
		}
		return root + imagePath;
	}
	
	/**
	 * 从图片链接获取图片物理地址
	 * 
	 * @param imageUrl
	 * @return
	 */
	public static String imageUrl2Path(String imageUrl) {
		String root = propUtil.getProperty("website.imagehttproot");
		String imageRoot = propUtil.getProperty("image.path.root").replaceAll("\\\\", "/");
		imageUrl = imageUrl.replaceAll(root, "");
		return imageRoot + imageUrl;
	}
	
	/**
	 * 获取默认图片url
	 * 
	 * @param imagePath 传入完整的图片路径
	 * @param ext  扩展名区分小图(s)，中图(m)，大图，原图(src)
	 * @return 返回图片URL
	 * @throws Exception
	 */
	public static String getImageUrl(String imagePath) {
		return getImageUrl(imagePath, "");
	}
	
	/**
	 * 构造目录页链接地址
	 * 
	 * @param catName
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public static String getCategoryUrl(String catName, String catId) {
		String url = propUtil.getProperty("website.httproot") + "/category/"
			+ catName.replaceAll("[^a-zA-Z0-9]+", "-") + "_"
			+ catId + ".html";
		return url;
	}
	
	/**
	 * 获取关键词搜索链接
	 * 
	 * @param keyword
	 * @return
	 */
	public static String getKeywordUrl(String keyword) {
		String url = null;
		try {
			url = propUtil.getProperty("website.httproot") + "/search/"
					+ URLEncoder.encode(keyword, "utf-8") + ".html";
		} catch (Exception e) {
		}
		return url;
	}
	
	/**
	 * 获取搜索链接
	 * 
	 * @param keyword
	 * @param catName
	 * @param catId
	 * @return
	 */
	public static String getSearchUrl(String keyword, String catName, String catId) {
		if (StringUtils.isEmpty(keyword)) {
			return getCategoryUrl(catName, catId);
		}
		String url = getKeywordUrl(keyword) + "?catId=" + catId;
		return url;
	}
	
	/**
	 * 获取国家国旗图片
	 * 
	 * @param countryCode
	 * @return
	 */
	public static String getCountryImg(String countryCode) {
		return propUtil.getProperty("website.httproot") + "/static/web/images/country/" + countryCode.toUpperCase() + ".png";
	}
	
	/**
	 * 获取快照地址
	 * 
	 * @param snapshotPath
	 * @return
	 */
	public static String getSnapshotUrl(String snapshotPath) {
		String url = propUtil.getProperty("website.httproot") + snapshotPath.replaceAll(propUtil.get("cms.root"), "");
		return url;
	}
}
