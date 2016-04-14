package com.fd.fashion.cms.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fd.fashion.cms.bean.CmsBlockBean;
import com.fd.fashion.cms.bean.CmsChannelBean;
import com.fd.fashion.cms.bean.CmsChannelHistoyBean;
import com.fd.fashion.cms.bean.CmsTemplateBean;
import com.fd.fashion.cms.service.ICmsService;
import com.fd.fashion.cms.util.AnalyseChannel;
import com.fd.fashion.cms.util.CmsUtil;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.service.IProductService;
import com.fd.util.BeanUtil;
import com.fd.util.FileUtil;
import com.fd.util.ImageUtil;
import com.fd.util.PageInfo;
import com.fd.util.RegexUtil;
import com.fd.util.RewriteUtil;
import com.fd.util.StringUtil;

@Transactional(rollbackFor=Exception.class)
@Component("cmsManager")
public class CmsManager implements ICmsManager {
    @Autowired      
    @Qualifier("cmsService") 
	private ICmsService cmsService;

    @Autowired      
    @Qualifier("productService") 
    private IProductService productService;

	/**
	 * 获取模板的信息
	 * 
	 * @param cmsTemplateBean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<CmsTemplateBean> getCmsTemplate(CmsTemplateBean cmsTemplateBean,
			PageInfo pageInfo) throws Exception {
		List<CmsTemplateBean> templates = cmsService.getCmsTemplateBeans(cmsTemplateBean, pageInfo);
		return templates;
	}

	/**
	 * 添加模板的信息
	 * 
	 * @param template
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public String addCmsTemplate(CmsTemplateBean template)
			throws Exception {
		String templateId = null;
		if (template != null) {
			RegexUtil regex = new RegexUtil();
			// 获得最后模板编号，生成新的模板编号
			templateId = cmsService.getLastTemplateId();
			if (templateId != null) {
				int code = Integer.valueOf(regex.getMatchedStr(templateId, "(\\d+)", 1));
				code ++;
				if (code > 9999) {
					throw new RuntimeException("Template Code 超过范围 9999");
				} else if (code > 999) {
					templateId = "TMP" + code;
				} else if (code > 99) {
					templateId = "TMP0" + code;
				} else if (code > 9) {
					templateId = "TMP00" + code;
				} else {
					templateId = "TMP000" + code;
				}
			} else {
				templateId = "TMP0001";
			}
			// 读取模板内容
			template.setTemplateId(templateId);
			try {
				readTemplateContent(template, regex);
			} catch (Exception e) {
                e.printStackTrace();
                throw e;
			}
			cmsService.insertCmsTemplateBean(template);
		}
		return templateId;
	}

	/** 解析模板看有多少栏目
	 * @param template
	 * @param regex
	 * @throws Exception
	 */
	private void readTemplateContent(CmsTemplateBean template, RegexUtil regex)
			throws Exception {
		// 读取模板内容
		String tempaltePath = template.getFileUrl();
		File templateFile = new File(tempaltePath);
		if (!templateFile.exists()) {
			throw new RuntimeException("Template file not exists!");
		}
		String content = FileUtil.readFile(tempaltePath, "utf-8");
		// 计算栏目数
		String blockRegex = "<!--MICCMS:\\s*Block\\s*Name=[’'\"]([^’'\"]*)[’'\"]\\s*ItemNum=\\d*-->";
		List<String> blockNames = regex.getAllMatched(content, blockRegex, 1);
		template.setBlockNum(blockNames.size());
	}
	
	/**
	 * 更新模板信息
	 * 
	 * @param template
	 * @return
	 * @throws Exception
	 */
	public int updateCmsTemplate(CmsTemplateBean template) throws Exception {
		// 如果新模板路径非空，重新读取模板内容
		if (!StringUtil.isEmpty(template.getFileUrl())) {
			RegexUtil regex = new RegexUtil();
			readTemplateContent(template, regex);
		}
		return cmsService.updateCmsTemplateBean(template);
	}
	
	/**
	 * 删除模板
	 * 
	 * @param templateId
	 * @param operator
	 * @return
	 * @throws Exception
	 */
	public int deleteCmsTemplate(String[] templateIds, String operator) throws Exception {
		if (templateIds == null) {
			return 0;
		}
		int i=0;
		for (String templateId : templateIds) {
			CmsChannelBean channel = new CmsChannelBean();
			channel.setTemplateId(templateId);
			List<CmsChannelBean> channels = cmsService.getCmsChannelBeans(channel);
			if (channels != null && channels.size()>0) { 
				continue;
			}
			CmsTemplateBean temp = new CmsTemplateBean();
			temp.setTemplateId(templateId);
			temp.setOperateUser(operator);
			temp.setOperateDate(new Date());
			// state状态0删除，1启用，2禁用 ；
			temp.setStatus(0);
			i += cmsService.updateCmsTemplateBean(temp);
		}
		return i;
	}
	
	/**
	 * 禁用启用模板
	 * 
	 * @param templateId
	 * @param operator
	 * @return
	 * @throws Exception
	 */
	public int forbiddenCmsTemplate(String[] templateIds, int state, String operator) throws Exception {
		if (templateIds == null) {
			return 0;
		}
		int i = 0;
		for (String templateId : templateIds) {
			CmsTemplateBean temp = new CmsTemplateBean();
			temp.setTemplateId(templateId);
			temp.setOperateUser("");
			temp.setOperateDate(new Date());
			// state状态 0删除，1启用，2禁用 ；
			temp.setStatus(state);
			i += cmsService.updateCmsTemplateBean(temp);
		}
		return i;
	}
	
	/**
	 * 获取单个模板
	 * 
	 * @param cond
	 * @return
	 * @throws Exception
	 */
	public CmsTemplateBean getCmsTemplate(String templateId) throws Exception {
		if (StringUtils.isEmpty(templateId)) {
			return null;
		}
		CmsTemplateBean temp = new CmsTemplateBean();
		temp.setTemplateId(templateId);
		return cmsService.getCmsTemplateBean(temp);
	}
	
	/**
	 * 查询频道
	 * 
	 * @param channel
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<CmsChannelBean> getCmsChannels(CmsChannelBean channel, PageInfo pageInfo) throws Exception {
		return cmsService.getCmsChannelBeans(channel, pageInfo);
	}
	
	/**
	 * 获取频道
	 * 
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public CmsChannelBean getCmsChannel(String channelId) throws Exception {
		if (StringUtils.isEmpty(channelId)) {
			return null;
		}
		CmsChannelBean channel = new CmsChannelBean();
		channel.setChannelId(channelId);
		return cmsService.getCmsChannelBean(channel);
	}
	
	/**
	 * 新增频道
	 * 
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	public CmsChannelBean addCmsChannel(CmsChannelBean channel) throws Exception {
		RegexUtil regex = new RegexUtil();
		// 获得最后模板编号，生成新的模板编号
		String channelId = cmsService.getLastChannelId();
		if (channelId != null) {
			int code = Integer.valueOf(regex.getMatchedStr(channelId, "(\\d+)", 1));
			code ++;
			if (code > 99999) {
				throw new RuntimeException("Channel Code 超过范围 99999");
			} else if (code > 9999) {
				channelId = "CHN" + code;
			} else if (code > 999) {
				channelId = "CHN0" + code;
			} else if (code > 99) {
				channelId = "CHN00" + code;
			} else if (code > 9) {
				channelId = "CHN000" + code;
			} else {
				channelId = "CHN0000" + code;
			}
		} else {
			channelId = "CHN00001";
		}
		
		
		// 获取模版
		String templateId = channel.getTemplateId();
		CmsTemplateBean template = getCmsTemplate(templateId);
		
		// 解析模版，生成栏目
		List<CmsBlockBean> blocks = CmsUtil.getBlocks(template.getFileUrl());
		
		// 增加频道
		channel.setChannelId(channelId);
		channel.setBlockNum(template.getBlockNum());
		channel.setTemplateName(template.getTemplateName());
		channel.setTemplateType(template.getTemplateType());
		cmsService.insertCmsChannelBean(channel);
		
		// 增加栏目
		for (CmsBlockBean block : blocks) {
			block.setChannelId(channelId);
			cmsService.insertCmsBlockBean(block);
		}
		return channel;
	}
	
	/**
	 * 更新频道
	 * 
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	public int updateCmsChannel(CmsChannelBean channel) throws Exception {
		CmsChannelBean oldChannel = new CmsChannelBean();
		oldChannel.setChannelId(channel.getChannelId());
		oldChannel = cmsService.getCmsChannelBean(oldChannel);
		
		if (!channel.getTemplateId().equals(oldChannel.getTemplateId())) {
			// 更换模版
			String templateId = channel.getTemplateId();
			CmsTemplateBean template = getCmsTemplate(templateId);
			List<CmsBlockBean> blocks = CmsUtil.getBlocks(template.getFileUrl());

			channel.setBlockNum(template.getBlockNum());
			channel.setTemplateName(template.getTemplateName());
			channel.setTemplateType(template.getTemplateType());
			
			CmsBlockBean block = new CmsBlockBean();
			block.setChannelId(oldChannel.getChannelId());
			List<CmsBlockBean> oldBlocks = cmsService.getCmsBlockBeans(block);
			
			int loop = blocks.size() > oldBlocks.size() ? oldBlocks.size() : blocks.size();
			for (int i = 0; i < loop; i++) { // 更新旧栏目
				block = blocks.get(i);
				CmsBlockBean oldBlock = oldBlocks.get(i);
				BeanUtil.copyProperties(oldBlock, block);
				cmsService.updateCmsBlockBean(oldBlock);
			}
			
			if (blocks.size()>oldBlocks.size()) { //  补充新增栏目
				List<CmsBlockBean> leftBlocks = blocks.subList(loop, blocks.size());
				for (CmsBlockBean b : leftBlocks) {
					b.setChannelId(channel.getChannelId());
					cmsService.insertCmsBlockBean(b);
				}
			} else if (blocks.size()<oldBlocks.size()) { // 删除多余栏目
				List<CmsBlockBean> leftBlocks = oldBlocks.subList(loop, blocks.size());
				for (CmsBlockBean b : leftBlocks) {
					cmsService.deleteCmsBlockBean(b);
				}
			}
		}
		
		return cmsService.updateCmsChannelBean(channel);
	}
	
	/**
	 * 更新频道及栏目明细，这个方法不能更换频道模版
	 * 
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	public int updateCmsChannel(CmsChannelBean channel, List<CmsBlockBean> blocks) throws Exception {
		channel.setTemplateId(null);
		channel.setTemplateName(null);
		channel.setTemplateType(null);
		
		for (CmsBlockBean block : blocks) {
			if (StringUtils.isNotEmpty(block.getImgUrl()) && StringUtils.isNotEmpty(block.getImgSpec())) {
				String imgSize = block.getImgSpec();
				imgSize = imgSize.replaceAll("^[^0-9]*?([0-9]+)[^0-9]*?([0-9]+)[^0-9]*?$", "$1x$2");
				String imageUrl = block.getImgUrl();
				String cutImageUrl = imageUrl.replaceAll("(?i)\\.(jpg|gif)", "."+imgSize+".$1");
				if (new File(imageUrl).exists()) {
					ImageUtil.cutImage(RewriteUtil.imageUrl2Path(imageUrl), RewriteUtil.imageUrl2Path(cutImageUrl), imgSize);
				}
				block.setImgUrl(cutImageUrl);
			}
			cmsService.updateCmsBlockBean(block);
		}
		return cmsService.updateCmsChannelBean(channel);
	}
	
	/**
	 * 禁用启用频道
	 * 
	 * @param channelIds
	 * @return
	 * @throws Exception
	 */
	public int forbiddenChannel(String [] channelIds, int status, String operator) throws Exception {
		if (channelIds == null) {
			return 0;
		}
		CmsChannelBean channel = new CmsChannelBean();
		channel.setStatus(status);
		int i=0;
		for (String channelId : channelIds) {
			// 处理文件
			CmsChannelBean c = getChannel(channelId);
			String channelUrl = c.getChannelUrl();
			String channelPath = CmsUtil.getChannelPath(channelUrl);
			File channelFile = new File(channelPath);
			if (channelFile.exists()) {
				channelFile.renameTo(new File(channelPath + ".forbidden"));
				// 关闭频道所有已发布信息
				closeChannelHistory(channelId);
			}
			
			channel.setChannelId(channelId);
			i+=cmsService.updateCmsChannelBean(channel);
		}
		return i;
	}
	
	/**
	 * 关闭频道所有已发布信息
	 * 
	 * @param channelId
	 * @throws Exception
	 */
	private void closeChannelHistory(String channelId) throws Exception {
		List<CmsChannelHistoyBean> histories = cmsService.getNotClosedHistory(channelId);
		Date now = new Date();
		for (CmsChannelHistoyBean history : histories) {
			history.setEndDate(now);
			cmsService.updateCmsChannelHistoyBean(history);
		}
	}
	
	/**
	 * 获取频道
	 * 
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public CmsChannelBean getChannel(String channelId) throws Exception {
		if (StringUtils.isEmpty(channelId)) {
			return null;
		}
		CmsChannelBean channel = new CmsChannelBean();
		channel.setChannelId(channelId);
		channel = cmsService.getCmsChannelBean(channel);
		return channel;
	}
	
	/**
	 * 删除频道
	 * 
	 * @param channelIds
	 * @param operator
	 * @return
	 * @throws Exception
	 */
	public int deleteChannel(String[] channelIds, String operator) throws Exception {
		if (channelIds == null) {
			return 0;
		}
		CmsChannelBean channel = new CmsChannelBean();
		channel.setStatus(3);
		int i=0;
		for (String channelId : channelIds) {
			// 处理文件
			CmsChannelBean c = getChannel(channelId);
			String channelUrl = c.getChannelUrl();
			String channelPath = CmsUtil.getChannelPath(channelUrl);
			File channelFile = new File(channelPath);
			if (channelFile.exists()) {
				channelFile.renameTo(new File(channelPath + ".delete"));
				// 关闭频道所有已发布信息
				closeChannelHistory(channelId);
			}
			channel.setChannelId(channelId);
			i+=cmsService.updateCmsChannelBean(channel);
		}
		return i;
	}
	
	/**
	 * 获取频道失效栏目
	 * 
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public List<CmsBlockBean> getInvalidBlocks(String channelId) throws Exception {
		List<CmsBlockBean> blocks = getChannelBlocks(channelId);
		for (int i=blocks.size()-1; i>=0; i--) {
			CmsBlockBean block = blocks.get(i);
			if (block.getBlockType() != 0 || block.getInfoId() == null) {// 没有填产品或不是产品
				blocks.remove(block);
			}
			Long productId = block.getInfoId();
			ProductBean product = new ProductBean();
			product.setProductId(productId);
			product = productService.getProductBean(product);
			if (product != null && product.getProductStatus() == 1) {// 正常状态产品
				blocks.remove(block);
			}
		}
		return blocks;
	}
	
	/**
	 * 获取频道栏目
	 * 
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public List<CmsBlockBean> getChannelBlocks(String channelId) throws Exception {
		CmsBlockBean block = new CmsBlockBean();
		block.setChannelId(channelId);
		return cmsService.getCmsBlockBeans(block);
	}
	
	/**
	 * 修改栏目
	 * 
	 * @param block
	 * @return
	 * @throws Exception
	 */
	public int updateBlock(CmsBlockBean block) throws Exception {
		return cmsService.updateCmsBlockBean(block);
	}
	
	/**
	 * 发布频道
	 * 
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public boolean publishChannel(String channelId) throws Exception {
		CmsChannelBean channel = getChannel(channelId);
		
		String templateId = channel.getTemplateId();
		CmsTemplateBean template = getCmsTemplate(templateId);
		
		List<CmsBlockBean> blocks = getChannelBlocks(channelId);
		
		AnalyseChannel builder = new AnalyseChannel();
		
		boolean succeed = builder.buildChannelFile(template, channel, blocks);
		if (succeed) {
			Date now = new Date();
			// 修改历史发布信息
			List<CmsChannelHistoyBean> histories = cmsService.getNotClosedHistory(channelId);
			
			if (histories != null) {
				List<CmsChannelHistoyBean> endHistories = new ArrayList<CmsChannelHistoyBean>();
				for (int i=histories.size()-1; i>=0; i--) {
					CmsChannelHistoyBean history = histories.get(i);
					long productId = history.getProductId() == null ? 0 : history.getProductId();
					int location = history.getChannelLocation();
					for (int j=blocks.size()-1; j>=0; j--) {
						CmsBlockBean block = blocks.get(j);
						long productId2 = block.getInfoId() == null ? 0 : block.getInfoId();
						int location2 = block.getBlockLocation();
						
						if (location == location2) {
							if (productId != productId2) {
								// 已结束信息
								endHistories.add(history);
							} else {
								blocks.remove(j);
							}
						}
					}
				}
				for (CmsChannelHistoyBean histroy : endHistories) { // 结束
					histroy.setEndDate(now);
					cmsService.updateCmsChannelHistoyBean(histroy);
				}
			}
			for (CmsBlockBean block : blocks) { //新增
				CmsChannelHistoyBean history = new CmsChannelHistoyBean();
				history.setBlockId(block.getBlockId());
				history.setChannelId(channelId);
				history.setChannelLocation(block.getBlockLocation());
				history.setProductId(block.getInfoId());
				history.setSku(block.getSku());
				history.setPubDate(now);
				cmsService.insertCmsChannelHistoyBean(history);
			}
			channel.setPubDate(now);
			cmsService.updateCmsChannelBean(channel);
		}
		return succeed;
	}
	
	/**
	 * 获取栏目
	 * 
	 * @param channelId
	 * @param location
	 * @return
	 * @throws Exception
	 */
	public CmsBlockBean getBlock(String channelId, int location) throws Exception {
		CmsBlockBean block = new CmsBlockBean();
		block.setChannelId(channelId);
		block.setBlockLocation(location);
		block = cmsService.getCmsBlockBean(block);
		return block;
	}
}
