package com.fd.fashion.cms.util;

import java.util.ArrayList;
import java.util.List;

import com.fd.fashion.cms.bean.CmsBlockBean;
import com.fd.util.FileUtil;
import com.fd.util.RegexUtil;
import com.fd.util.WebPropUtil;

/**
 * @CreateDate: 2013-5-20 上午11:39:24
 * @author Longli
 * @Description: 频道util方法
 * 
 */
public class CmsUtil {
	static RegexUtil regexUtil = new RegexUtil();
	
	/**
	 * 解析模版，生成栏目
	 * 
	 * @param templatePath
	 * @return
	 */
	public static List<CmsBlockBean> getBlocks(String templatePath) throws Exception {
		String content = FileUtil.readFile(templatePath);
		List<CmsBlockBean> blockBeans = new ArrayList<CmsBlockBean>();
		List<String> blockStrs = regexUtil.getAllMatched(content, CmsTagsConfig.BLOCK, 0);
		if (blockStrs != null) {
			for (int i=0; i<blockStrs.size(); i++) {
				String block = blockStrs.get(i);
				CmsBlockBean blockBean = new CmsBlockBean();
				blockBean.setBlockName(regexUtil.getMatchedStr(block, CmsTagsConfig.MICBLOCK_NAME, 1));
				String blockType = regexUtil.getMatchedStr(block, CmsTagsConfig.BLOCKINFOTYPE, 1);
				blockBean.setBlockType(blockType.equals("product") ? 0 : (blockType
					.equals("adimage") ?1 : 2));
				blockBean.setBlockLocation(i);
				blockBean.setImgSpec(regexUtil.getMatchedStr(block, CmsTagsConfig.INFO_IMG, 1));
				String length = regexUtil.getMatchedStr(block, CmsTagsConfig.INFO_TITLE, 1);
				int titleLength = length == null ? 0 : Integer.valueOf(length);
				blockBean.setTitleLength(titleLength);
				
				length = regexUtil.getMatchedStr(block, CmsTagsConfig.INFO_SUMMARY, 1);
				int summaryLength = length == null ? 0 : Integer.valueOf(length);
				blockBean.setSummaryLength(summaryLength);
				blockBeans.add(blockBean);
			}
			return blockBeans;
		}
		return null;
	}
	
	/**
	 * 根据频道url链接获取频道物理路径
	 * 
	 * @param channelUrl
	 * @return
	 * @throws Exception
	 */
	public static String getChannelPath(String channelUrl) throws Exception {
		WebPropUtil prop = new WebPropUtil();
		String cmsRoot = prop.getProperty("cms.root");
		return cmsRoot + channelUrl;
	}
	
	/**
	 * 用模版物理路径获取url路径
	 * 
	 * @param templatePath
	 * @return
	 * @throws Exception
	 */
	public static String getTemplateUrl(String templatePath) throws Exception {
		WebPropUtil prop = new WebPropUtil();
		String root = prop.getProperty("image.path.root");
		String webRoot = prop.getProperty("website.imagehttproot");
		templatePath = templatePath.replaceAll(root, "");
		return webRoot + templatePath;
	}
	
	/**
	 * 获取频道预览地址
	 * 
	 * @param channelUrl
	 * @return
	 * @throws Exception
	 */
	public static String getChannelViewUrl(String channelUrl) throws Exception {
		return channelUrl.replace(".", ".view.");
	}
}
