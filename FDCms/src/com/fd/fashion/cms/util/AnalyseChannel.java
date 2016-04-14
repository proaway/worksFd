package com.fd.fashion.cms.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.fd.fashion.cms.bean.CmsBlockBean;
import com.fd.fashion.cms.bean.CmsChannelBean;
import com.fd.fashion.cms.bean.CmsTemplateBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.CommissionVO;
import com.fd.fashion.product.vo.PriceVo;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.util.AppContextUtil;
import com.fd.util.CommissionUtil;
import com.fd.util.CullNumUtil;
import com.fd.util.FileUtil;
import com.fd.util.RegexUtil;
import com.fd.util.RewriteUtil;

/**
 * 频道生成，通过模板替换栏目信息生成
 * @author mqr
 *
 */
public class AnalyseChannel {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AnalyseChannel.class);

	private RewriteUtil rewriteUtil = new RewriteUtil();
	
	RegexUtil regexUtil = new RegexUtil();

	/**
	 * 默认构造函数
	 */
	public AnalyseChannel(){
		
	}
	
	/**
	 * 生成频道预览页面
	 * @param template
	 * @param channel
	 * @param blocks
	 * @return
	 * @throws Exception
	 */
	public boolean buildChannelReviewFile(CmsTemplateBean template,
			CmsChannelBean channel, List<CmsBlockBean> blocks) throws Exception {
		return buildChannelFile(template, channel, blocks, true);
	}
	
	/**
	 * 生成频道页面
	 * @param template
	 * @param channel
	 * @param blocks
	 * @return
	 * @throws Exception
	 */
	public boolean buildChannelFile(CmsTemplateBean template,
			CmsChannelBean channel, List<CmsBlockBean> blocks) throws Exception {
		return buildChannelFile(template, channel, blocks, false);
	}
	
	/**
	 * 生成频道页面
	 * @param template
	 * @param channel
	 * @param blocks
	 * @return
	 * @throws Exception
	 */
	public boolean buildChannelFile(CmsTemplateBean template,
			CmsChannelBean channel, List<CmsBlockBean> blocks,
			boolean review) throws Exception {
		if (template == null ||channel == null || blocks == null) {
			logger.error("templateVo=" + template + "  channelVo=" + channel + "  blockMap=" + null);
			return false;
		}

		String content = replaceTemplate(template, channel, blocks);
		
		String channelUrl = channel.getChannelUrl();
		if ( StringUtils.isEmpty(channelUrl)) {
			logger.error("channelUrl is null");
			return false;
		}
		
		if (!channelUrl.startsWith("/")) {
			channelUrl = "/" + channelUrl;
		}
		File file = new File(channelUrl);
		if(file.exists()) {
			backupFile(file);
		}
		// 写入文件
		if (review) {
			FileUtil.writeFile(content, new File(CmsUtil.getChannelPath(CmsUtil.getChannelViewUrl(channel.getChannelUrl()))));
		} else {
			FileUtil.writeFile(content, new File(CmsUtil.getChannelPath(channel.getChannelUrl())));
		}
		logger.info("cms successful: " + channelUrl);
		return true;
	}
	
	private void backupFile(File file) throws Exception {
		String filename = file.getPath();
		int lastSeparatorIndex = filename.lastIndexOf(File.separator);
		String root = filename.substring(0, lastSeparatorIndex + 1);
		String name = filename.substring(lastSeparatorIndex + 1);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date lastModifyTime = new Date(file.lastModified());
		String dateStr = format.format(lastModifyTime);
		String bakName = root + "bak" + File.separator + name.replaceAll("\\.", dateStr + ".");
		File bakFile = new File(bakName);
		if (!bakFile.getParentFile().exists()) {
			bakFile.getParentFile().mkdirs();
		}
		FileUtils.copyFile(file, bakFile);
		// 删除超过三个月以上的备份
		File files[] = bakFile.getParentFile().listFiles();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		for (File f : files) {
			Date d = new Date(f.lastModified());
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			if (c.before(cal)) {
				try {
					f.delete();
				} catch (Exception e) {
				}
			}
		}
	}	
	
	/**
	 * 替换模板
	 * 
	 * @return
	 * @throws Exception
	 */
	public String replaceTemplate(CmsTemplateBean template,
			CmsChannelBean channel,
			List<CmsBlockBean> blocks) throws Exception {
		if (template == null ||channel == null )
			return "";

		String templatePath = template.getFileUrl();
		String templateContent = FileUtil.readFile(templatePath);
		
		if (StringUtils.isEmpty(templateContent))
			return "";
		
		if ( blocks == null)
			return templateContent;
		
		if(StringUtils.isNotEmpty(channel.getTitleInfo())){
			templateContent = RegexUtil.replaceAll(templateContent, CmsTagsConfig.TITLEINFO, channel.getTitleInfo());
		}
		if(StringUtils.isNotEmpty(channel.getDescription())){
			templateContent = RegexUtil.replaceAll(templateContent, CmsTagsConfig.METADESC, channel.getDescription());
		}
		if(StringUtils.isNotEmpty(channel.getKeywords())){
			templateContent = RegexUtil.replaceAll(templateContent, CmsTagsConfig.METAKEYWORDS, channel.getKeywords());
		}
		// 解析block
		List<String> blockStrs = regexUtil.getAllMatched(templateContent, CmsTagsConfig.BLOCK, 0);
		
		for (int i = 0; i < blockStrs.size(); i++) {
			String curBlock = blockStrs.get(i);
			String blockName = regexUtil.getMatchedStr(curBlock, CmsTagsConfig.MICBLOCK_NAME, 1);
			String regex = CmsTagsConfig.BLOCK.replace("blockName", blockName.replaceAll("([\\\\\\.\\[\\]\\(\\)\\^\\$\\-\\*\\+\\?\\{\\}\\|\\/])", "\\\\$1"));
			
			CmsBlockBean cmsBlockBean = blocks.get(i);
			
			// 替换block内容
			String afterReplaceBlock = replaceBlockInfo(cmsBlockBean, curBlock, channel, template);
			// 替换模板中对应的block
			templateContent = RegexUtil.replaceFirst(templateContent, regex,
					afterReplaceBlock);
		}
		
		return templateContent;
	}
	
	/**
	 * 替换block信息
	 * 
	 * @param block
	 * @param productMap
	 * @return
	 * @throws Exception
	 */
	public String replaceBlockInfo(CmsBlockBean cmsBlock, String block, CmsChannelBean channel, CmsTemplateBean template) throws Exception {
		// 解析替换blockinfo
		Integer blockType = cmsBlock.getBlockType();
		if(blockType == null){
			return block;
		}
		
		//取得标题要显示的长度
		String length = regexUtil.getMatchedStr(block, CmsTagsConfig.INFO_TITLE, 1);
		int titleLength = length == null ? 0 : Integer.valueOf(length);
		//取得摘要要显示的长度
		length = regexUtil.getMatchedStr(block, CmsTagsConfig.INFO_SUMMARY, 1);
		int summaryLength = length == null ? 0 : Integer.valueOf(length);
		
		String linkUrl = cmsBlock.getLinkUrl();
		String img = cmsBlock.getImgUrl();
		String summary = cmsBlock.getSummary();
		String title = cmsBlock.getTitle();
		String imgalt = cmsBlock.getImgAlt();
		// 自身链接
		block = RegexUtil.replaceAll(block, CmsTagsConfig.EDM_URL, channel.getChannelUrl());
		//替换标题
		if (StringUtils.isNotEmpty(title)) {
			int oldtitleLength = title.length();

			if (titleLength > 0 && titleLength < oldtitleLength && titleLength>3) {
				title = title.substring(0, titleLength-3)+"...";
			}
			title = title.replaceAll("\"", "&quot;");
			block = RegexUtil.replaceAll(block,
					CmsTagsConfig.INFO_TITLE, title);
		}
		// 替换摘要
		if (StringUtils.isNotEmpty(summary)) {
			int oldLength = summary.length();

			if (summaryLength > 0 && summaryLength < oldLength && summaryLength>3) {
				summary = summary.substring(0, summaryLength-3)+"...";
			}
			summary = summary.replaceAll("\"", "&quot;");
			block = RegexUtil.replaceAll(block,
					CmsTagsConfig.INFO_SUMMARY, summary);
		}
		//替换链接
		if (StringUtils.isNotEmpty(linkUrl)){
			if (3 == template.getTemplateType()) {
				linkUrl = linkUrl + "?f=EDM-" + channel.getChannelId();
				block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_LINK, linkUrl);
			} else {
				String productId = regexUtil.getMatchedStr(
						linkUrl,
						"_(\\d+)\\.html||productId=(\\d+)",
						1);
				String mouseDownAct = "\" onMouseDown=\"clickProd('0', '"
					+ (productId == null ? "" : productId) + "','"
					+ channel.getChannelId() + "_"
					+ cmsBlock.getBlockLocation() + "')";
				block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_LINK, linkUrl + mouseDownAct);
			}
		}else{			
			String mouseDownAct = "\" onMouseDown=\"clickProd('0', '','"
					+ channel.getChannelId() + "_"
					+ cmsBlock.getBlockLocation() + "')";
			block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_LINK, "#" + mouseDownAct);
		}
		//替换图片描述
		if (StringUtils.isNotEmpty(imgalt)){
			imgalt = imgalt.replaceAll("\"", "&quot;");
			block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_IMG_ALT, imgalt);
		}
		//替换图片
		if (StringUtils.isNotEmpty(img)){
			block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_IMG, img);
		}
		// 处理价格
		if (0 == cmsBlock.getBlockType()
				&& cmsBlock.getInfoId() != null) {
			String maxPriceStr = "";
			Long productId = cmsBlock.getInfoId();
			IProductManager productManager = (IProductManager) AppContextUtil.getBean("productManager");
			ProductBean product = productManager.getProductBean(productId);
			if (product == null) {
				throw new Exception("产品号错误：" + productId);
			}
			PriceVo priceVo = productManager.getPriceVo(productId);
//			ProductConfigsVo productConfigs = productManager.getProductConfigsVo(productId, product.getCatId());
//			if (productConfigs != null) {
//				productConfigs.setPrice(priceVo);
//			}
			
			double price = priceVo.getPrice();
			double disPrice = priceVo.getDiscountPrice();
			double savePrice = price - disPrice;
			double discount = priceVo.getPriceBean().getDiscount() == null ? 0 : priceVo.getPriceBean().getDiscount();
			double wholesalePrice = priceVo.getWholesalePrice();

			String priceStr = CullNumUtil.getDoubleToString(price / product.getQuantity(), "0.00");
			String disPriceStr = CullNumUtil.getDoubleToString(disPrice / product.getQuantity(), "0.00");
			String savePriceStr = CullNumUtil.getDoubleToString(savePrice / product.getQuantity(), "0.00");
			String wholesalePriceStr = CullNumUtil.getDoubleToString(wholesalePrice / product.getQuantity(), "0.00");
			String discountStr = CullNumUtil.getDoubleToString(discount, "0");

			//价格
			if (regexUtil.isMatch(block, CmsTagsConfig.INFO_PRICE)) {
				block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_PRICE, disPriceStr);
			}
			// 原价格
			if (regexUtil.isMatch(block, CmsTagsConfig.INFO_ORIGINALPRICE) && savePrice>0) {
				block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_ORIGINALPRICE, priceStr);
			}
			// 节省金额
			if (regexUtil.isMatch(block, CmsTagsConfig.INFO_SAVEPRICE) && savePrice>0) {
				block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_SAVEPRICE, savePriceStr);
			}
			// 产品批发价格
			if (regexUtil.isMatch(block, CmsTagsConfig.INFO_WHOLESALERICE) && wholesalePrice>0) {
				block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_WHOLESALERICE, wholesalePriceStr);
			}
			// 折扣
			if (regexUtil.isMatch(block, CmsTagsConfig.INFO_DISCOUNT) && savePrice > 0) {
				block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_DISCOUNT, discountStr);
			}
//			// 评分星星 Math.round(Math.floor(4.8*2))
//			if (regexUtil.isMatch(block, CmsTagsConfig.INFO_PRODUCTSTART)) {
//				ProductInfoVo piv = productManager.getProductInfoVo(product.getProductId());
//				if (piv.getProductScore() != null) {
//					String start = StringUtil.getStar(piv.getProductScore());
//					block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_PRODUCTSTART, "<span class=\"star_display_"+start+" dis_block floatL\"></span>");
//				} else {
//					block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_PRODUCTSTART, "");
//				}
//			}
//			IEvaluationManager evaluationManager = (IEvaluationManager) AppContextUtil.getBean("evaluationManager");
//			// 评价数
//			if (regexUtil.isMatch(block, CmsTagsConfig.INFO_REVIEWS)) {
//				EvaluationsVo eval = new EvaluationsVo();
//				eval.setItemCode(productId);
//				EvaluationsVo proEvalCount = evaluationManager.getEvaluationsByGNB(eval);
//				if (Integer.valueOf(proEvalCount.getCurrEvaCount()) > 0) {
////					block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_REVIEWS, "<a href='http://www.shopmadeinchina.com/buyer/template/evaluations,SellerFeedbackProfile.vm?sellerId="+product.getSellerId()+"'>"+proEvalCount+" reviews</a>");
//					block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_REVIEWS, proEvalCount.getCurrEvaCount()+" reviews");
//				} else {
//					block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_REVIEWS, "");
//				}
//			}
//			
//			//评价节选标签 和 评价数
//			if (regexUtil.isMatch(block, CmsTagsConfig.INFO_REVIEWSDES)) {
//				EvaluationsVo eval = new EvaluationsVo();
//				PageInfo page = new PageInfo();
//				page.setPageIndex(1);
//				page.setPageSize(1);
//				eval.setPageInfo(page);
//				eval.setItemCode(productId);
//				List<EvaluationsVo> evalList = evaluationManager.getEvaluationsByCondition(eval);
//				if (evalList != null && evalList.size()>0) {
//					System.out.println("list size = " + evalList.size());
//					eval = evalList.get(0);
//				} else {
//					eval = null;
//				}
//				if (eval!=null && StringUtils.isNotEmpty(eval.getRemark())) {
//					String str = eval.getRemark();
//					String length = regexUtil.getMatchedStr(block, CmsTagsConfig.INFO_REVIEWSDES, 1);
//					if (length != null) {
//						int l = Integer.valueOf(length);
//						if (str.length() >l) {
//							str = str.substring(0, l)+"...";
//						}
//					}
//					block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_REVIEWSDES, str);
//				} else {
//					block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_REVIEWSDES, "");
//				}
//			}
			
//			// 免运费
//			if (regexUtil.isMatch(block, CmsTagsConfig.INFO_FREESHIPPING)) {
//				int free = product.getIsFreeShipping();
//				if (free > 0) {
//					block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_FREESHIPPING, "<strong class=\"color_blue\">Free shipping</strong><img src=\"/static/web/images/homepage-newstyle/v2011-08/logo-free-shipping-new-ditu.gif\"  alt=\"logo free shipping\" width=\"23\" height=\"11\" align=\"absmiddle\" title=\"logo free shipping\" class=\"marR10 marT_5\"/>");
//				} else {
//					block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_FREESHIPPING, "");
//				}
//			}
			// 订单数
			if (regexUtil.isMatch(block, CmsTagsConfig.INFO_ORDERS)) {
				IOrderManager orderManager = (IOrderManager) AppContextUtil.getBean("orderManager");
				int count = orderManager.getProductOrderCount(product.getProductId());
				if (count > 0) {
					block = RegexUtil.replaceAll(block, CmsTagsConfig.INFO_ORDERS, count + " orders ");
				}
			}
			
			// 分类
			String catId = product.getCustomCatId();
			ISellerManager sellerManager = (ISellerManager) AppContextUtil.getBean("sellerManager");
			List<CustomCategoryBean> cats = sellerManager.getCustomChildrenCats(catId);
			if (cats != null) {
				CustomCategoryBean catLv1 = null;
				CustomCategoryBean catLv2 = null;
				CustomCategoryBean catLv3 = null;
				CustomCategoryBean catLv4 = null;
				if (cats.size()>0) {
					catLv1 = cats.get(cats.size()-1);
				}
				if (cats.size()>1) {
					catLv2 = cats.get(cats.size()-2);
				}
				if (cats.size()>2) {
					catLv3 = cats.get(cats.size()-3);
				}
				if (cats.size()>3) {
					catLv4 = cats.get(cats.size()-4);
				}
				// 1级分类
				if (regexUtil.isMatch(block, CmsTagsConfig.INFO_CATEGORY1) && catLv1!=null) {
					block = RegexUtil.replaceAll(block,
							CmsTagsConfig.INFO_CATEGORY1, "<a href=\""
									+ rewriteUtil.getCategoryUrl(catLv1.getCatName(), catLv1.getCatId())
									+ "\" target=\"_blank\">"
									+ catLv1.getCatName() + " &raquo;</a>");
				}
				// 2级分类
				if (regexUtil.isMatch(block, CmsTagsConfig.INFO_CATEGORY2) && catLv2!=null) {
					block = RegexUtil.replaceAll(block,
							CmsTagsConfig.INFO_CATEGORY2, "<a href=\""
									+ rewriteUtil.getCategoryUrl(catLv2.getCatName(), catLv2.getCatId())
									+ "\" target=\"_blank\">"
									+ catLv2.getCatName() + " &raquo;</a>");
				}
				// 3级分类
				if (regexUtil.isMatch(block, CmsTagsConfig.INFO_CATEGORY3) && catLv3!=null) {
					block = RegexUtil.replaceAll(block,
							CmsTagsConfig.INFO_CATEGORY3, "<a href=\""
									+ rewriteUtil.getCategoryUrl(catLv3.getCatName(), catLv3.getCatId())
									+ "\" target=\"_blank\">"
									+ catLv3.getCatName() + " &raquo;</a>");
				}
				// 4级分类
				if (regexUtil.isMatch(block, CmsTagsConfig.INFO_CATEGORY4) && catLv4!=null) {
					block = RegexUtil.replaceAll(block,
							CmsTagsConfig.INFO_CATEGORY4, "<a href=\""
									+ rewriteUtil.getCategoryUrl(catLv4.getCatName(), catLv4.getCatId())
									+ "\" target=\"_blank\">"
									+ catLv4.getCatName() + " &raquo;</a>");
				}
			}
		}
		// BLOCK头尾
		block = "<div block>" + block + "</div>";
		// 删除带有“DelLine”的行
		block = block.replaceAll(".*DelLine-->.*", "");
		// 删除多余注释
		block = block.replaceAll("<!--[^<>]*?-->", "");
		return block;
	}
}
