package com.fd.fashion.cms.manager;

import java.util.List;

import com.fd.fashion.cms.bean.CmsBlockBean;
import com.fd.fashion.cms.bean.CmsChannelBean;
import com.fd.fashion.cms.bean.CmsTemplateBean;
import com.fd.util.PageInfo;

public interface ICmsManager {

	/**
	 * 获取模板的信息
	 * 
	 * @param cmsTemplateBean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<CmsTemplateBean> getCmsTemplate(CmsTemplateBean template,
			PageInfo pageInfo) throws Exception;

	/**
	 * 添加模板的信息
	 * 
	 * @param template
	 * @return
	 * @throws Exception
	 */
	public String addCmsTemplate(CmsTemplateBean template) throws Exception;
	
	/**
	 * 更新模板信息
	 * 
	 * @param template
	 * @return
	 * @throws Exception
	 */
	public int updateCmsTemplate(CmsTemplateBean template) throws Exception;
	
	/**
	 * 删除模板
	 * 
	 * @param templateIds
	 * @param operator
	 * @return
	 * @throws Exception
	 */
	public int deleteCmsTemplate(String[] templateIds, String operator) throws Exception;
	
	/**
	 * 禁用模板
	 * 
	 * @param templateId
	 * @param operator
	 * @return
	 * @throws Exception
	 */
	public int forbiddenCmsTemplate(String[] templateIds, int status, String operator) throws Exception;
	
	/**
	 * 获取单个模板
	 * 
	 * @param cond
	 * @return
	 * @throws Exception
	 */
	public CmsTemplateBean getCmsTemplate(String templateId) throws Exception;
	
	/**
	 * 查询频道
	 * 
	 * @param channel
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<CmsChannelBean> getCmsChannels(CmsChannelBean channel, PageInfo pageInfo) throws Exception;
	
	/**
	 * 获取频道
	 * 
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public CmsChannelBean getCmsChannel(String channelId) throws Exception;
	
	/**
	 * 新增频道
	 * 
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	public CmsChannelBean addCmsChannel(CmsChannelBean channel) throws Exception;
	
	/**
	 * 更新频道
	 * 
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	public int updateCmsChannel(CmsChannelBean channel) throws Exception;
	
	/**
	 * 更新频道及栏目明细，这个方法不能更换频道模版
	 * 
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	public int updateCmsChannel(CmsChannelBean channel, List<CmsBlockBean> blocks) throws Exception;
	
	/**
	 * 改变频道状态
	 * 
	 * @param channelIds
	 * @return
	 * @throws Exception
	 */
	public int forbiddenChannel(String [] channelIds, int status, String operator) throws Exception;
	
	/**
	 * 删除频道
	 * 
	 * @param channelIds
	 * @param operator
	 * @return
	 * @throws Exception
	 */
	public int deleteChannel(String[] channelIds, String operator) throws Exception;
	
	/**
	 * 获取频道失效栏目
	 * 
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public List<CmsBlockBean> getInvalidBlocks(String channelId) throws Exception;
	
	/**
	 * 获取频道栏目
	 * 
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public List<CmsBlockBean> getChannelBlocks(String channelId) throws Exception;
	
	/**
	 * 修改栏目
	 * 
	 * @param block
	 * @return
	 * @throws Exception
	 */
	public int updateBlock(CmsBlockBean block) throws Exception;
	
	/**
	 * 发布频道
	 * 
	 * @param channelid
	 * @return
	 * @throws Exception
	 */
	public boolean publishChannel(String channelid) throws Exception;
	
	/**
	 * 获取栏目
	 * 
	 * @param channelId
	 * @param location
	 * @return
	 * @throws Exception
	 */
	public CmsBlockBean getBlock(String channelId, int location) throws Exception;
}
