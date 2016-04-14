package com.fd.fashion.cms.service;

import java.util.List;

import com.fd.fashion.cms.bean.CmsBlockBean;
import com.fd.fashion.cms.bean.CmsChannelBean;
import com.fd.fashion.cms.bean.CmsChannelHistoyBean;
import com.fd.fashion.cms.bean.CmsTemplateBean;
import com.fd.util.PageInfo;

public interface ICmsService {
	/**
	 * 增加CMD模板
	 * 
	 * @param cmsTemplate
	 * @return
	 * @throws Exception
	 */
	public CmsTemplateBean insertCmsTemplateBean(CmsTemplateBean cmsTemplate)
			throws Exception;

	/**
	 * 修改CMD模板
	 * 
	 * @param cmsTemplate
	 * @return
	 * @throws Exception
	 */
	public int updateCmsTemplateBean(CmsTemplateBean cmsTemplate)
			throws Exception;

	/**
	 * 获取CMD模板
	 * 
	 * @param cmsTemplate
	 * @return
	 * @throws Exception
	 */
	public CmsTemplateBean getCmsTemplateBean(CmsTemplateBean cmsTemplate)
			throws Exception;

	/**
	 * 获取CMD模板
	 * 
	 * @param cmsTemplate
	 * @return
	 * @throws Exception
	 */
	public List<CmsTemplateBean> getCmsTemplateBeans(CmsTemplateBean cmsTemplate)
			throws Exception;

	/**
	 * 获取CMD模板
	 * 
	 * @param cmsTemplate
	 * @return
	 * @throws Exception
	 */
	public List<CmsTemplateBean> getCmsTemplateBeans(
			CmsTemplateBean cmsTemplate, PageInfo pageInfo) throws Exception;

	/**
	 * 删除CMD模板
	 * 
	 * @param cmsTemplate
	 * @return
	 * @throws Exception
	 */
	public int deleteCmsTemplateBean(CmsTemplateBean cmsTemplate)
			throws Exception;
	
	/**
	 * 获取最后一个模版ID
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getLastTemplateId() throws Exception;
	
	/**
	 * 增加频道管理
	 * 
	 * @param cmsChannel
	 * @return
	 * @throws Exception
	 */
	public CmsChannelBean insertCmsChannelBean(CmsChannelBean cmsChannel)
			throws Exception;

	/**
	 * 修改频道管理
	 * 
	 * @param cmsChannel
	 * @return
	 * @throws Exception
	 */
	public int updateCmsChannelBean(CmsChannelBean cmsChannel) throws Exception;

	/**
	 * 获取频道管理
	 * 
	 * @param cmsChannel
	 * @return
	 * @throws Exception
	 */
	public CmsChannelBean getCmsChannelBean(CmsChannelBean cmsChannel)
			throws Exception;

	/**
	 * 获取频道管理
	 * 
	 * @param cmsChannel
	 * @return
	 * @throws Exception
	 */
	public List<CmsChannelBean> getCmsChannelBeans(CmsChannelBean cmsChannel)
			throws Exception;

	/**
	 * 获取频道管理
	 * 
	 * @param cmsChannel
	 * @return
	 * @throws Exception
	 */
	public List<CmsChannelBean> getCmsChannelBeans(CmsChannelBean cmsChannel,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除频道管理
	 * 
	 * @param cmsChannel
	 * @return
	 * @throws Exception
	 */
	public int deleteCmsChannelBean(CmsChannelBean cmsChannel) throws Exception;
	
	/**
	 * 增加频道历史
	 * 
	 * @param cmsChannelHistoy
	 * @return
	 * @throws Exception
	 */
	public CmsChannelHistoyBean insertCmsChannelHistoyBean(CmsChannelHistoyBean cmsChannelHistoy) throws Exception;
	
	/**
	 * 修改频道历史
	 * 
	 * @param cmsChannelHistoy
	 * @return
	 * @throws Exception
	 */
	public int updateCmsChannelHistoyBean(CmsChannelHistoyBean cmsChannelHistoy) throws Exception;
	
	
	/**
	 * 获取频道历史
	 * 
	 * @param cmsChannelHistoy
	 * @return
	 * @throws Exception
	 */
	public CmsChannelHistoyBean getCmsChannelHistoyBean(CmsChannelHistoyBean cmsChannelHistoy) throws Exception;
	/**
	 * 获取频道历史
	 * 
	 * @param cmsChannelHistoy
	 * @return
	 * @throws Exception
	 */
	public List<CmsChannelHistoyBean> getCmsChannelHistoyBeans(CmsChannelHistoyBean cmsChannelHistoy) throws Exception;
	
	/**
	 * 获取频道历史
	 * 
	 * @param cmsChannelHistoy
	 * @return
	 * @throws Exception
	 */
	public List<CmsChannelHistoyBean> getCmsChannelHistoyBeans(CmsChannelHistoyBean cmsChannelHistoy, PageInfo pageInfo) throws Exception;
	
	/**
	 * 删除频道历史
	 * 
	 * @param cmsChannelHistoy
	 * @return
	 * @throws Exception
	 */
	public int deleteCmsChannelHistoyBean(CmsChannelHistoyBean cmsChannelHistoy) throws Exception;
	
	/**
	 * 增加栏目管理
	 * 
	 * @param cmsBlock
	 * @return
	 * @throws Exception
	 */
	public CmsBlockBean insertCmsBlockBean(CmsBlockBean cmsBlock)
			throws Exception;

	/**
	 * 修改栏目管理
	 * 
	 * @param cmsBlock
	 * @return
	 * @throws Exception
	 */
	public int updateCmsBlockBean(CmsBlockBean cmsBlock) throws Exception;

	/**
	 * 获取栏目管理
	 * 
	 * @param cmsBlock
	 * @return
	 * @throws Exception
	 */
	public CmsBlockBean getCmsBlockBean(CmsBlockBean cmsBlock) throws Exception;

	/**
	 * 获取栏目管理
	 * 
	 * @param cmsBlock
	 * @return
	 * @throws Exception
	 */
	public List<CmsBlockBean> getCmsBlockBeans(CmsBlockBean cmsBlock)
			throws Exception;

	/**
	 * 获取栏目管理
	 * 
	 * @param cmsBlock
	 * @return
	 * @throws Exception
	 */
	public List<CmsBlockBean> getCmsBlockBeans(CmsBlockBean cmsBlock,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除栏目管理
	 * 
	 * @param cmsBlock
	 * @return
	 * @throws Exception
	 */
	public int deleteCmsBlockBean(CmsBlockBean cmsBlock) throws Exception;
	
	/**
	 * 获取最后频道ID
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getLastChannelId() throws Exception;
	
	/**
	 * 获取没结束的已发布信息
	 * 
	 * @return
	 */
	public List<CmsChannelHistoyBean> getNotClosedHistory(String channelId) throws Exception;
}
