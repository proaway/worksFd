package com.fd.fashion.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fd.dao.IBaseDao;
import com.fd.fashion.cms.bean.CmsBlockBean;
import com.fd.fashion.cms.bean.CmsChannelBean;
import com.fd.fashion.cms.bean.CmsChannelHistoyBean;
import com.fd.fashion.cms.bean.CmsTemplateBean;
import com.fd.util.PageInfo;

@Component
@SuppressWarnings("unchecked")
public class CmsService implements ICmsService {
	@Autowired
	@Qualifier("dao")
	private IBaseDao dao;

	/**
	 * 增加CMD模板
	 * 
	 * @param cmsTemplate
	 * @return
	 * @throws Exception
	 */
	public CmsTemplateBean insertCmsTemplateBean(CmsTemplateBean cmsTemplate)
			throws Exception {
		dao.insertObj("insertCmsTemplateBean", cmsTemplate);
		return cmsTemplate;
	}

	/**
	 * 修改CMD模板
	 * 
	 * @param cmsTemplate
	 * @return
	 * @throws Exception
	 */
	public int updateCmsTemplateBean(CmsTemplateBean cmsTemplate)
			throws Exception {
		return dao.updateObj("updateCmsTemplateBean", cmsTemplate);
	}

	/**
	 * 获取CMD模板
	 * 
	 * @param cmsTemplate
	 * @return
	 * @throws Exception
	 */
	public CmsTemplateBean getCmsTemplateBean(CmsTemplateBean cmsTemplate)
			throws Exception {
		return (CmsTemplateBean) dao.getAsObject("getCmsTemplateBean",
				cmsTemplate);
	}

	/**
	 * 获取CMD模板
	 * 
	 * @param cmsTemplate
	 * @return
	 * @throws Exception
	 */
	public List<CmsTemplateBean> getCmsTemplateBeans(CmsTemplateBean cmsTemplate)
			throws Exception {
		return dao.getAsList("getCmsTemplateBean", cmsTemplate);
	}

	/**
	 * 获取CMD模板
	 * 
	 * @param cmsTemplate
	 * @return
	 * @throws Exception
	 */
	public List<CmsTemplateBean> getCmsTemplateBeans(
			CmsTemplateBean cmsTemplate, PageInfo pageInfo) throws Exception {
		if(pageInfo != null){
			Integer count = (Integer) dao.getAsObject("getCmsTemplateBeanCount",
					cmsTemplate);
			pageInfo.setRecordCount(count == null ? 0 : count);
			if (count != null && count > 0) {
				return dao.getAsList("getCmsTemplateBean", cmsTemplate, pageInfo);
			}
		}else{
			return dao.getAsList("getCmsTemplateBean", cmsTemplate);
		}
		return null;
	}

	/**
	 * 删除CMD模板
	 * 
	 * @param cmsTemplate
	 * @return
	 * @throws Exception
	 */
	public int deleteCmsTemplateBean(CmsTemplateBean cmsTemplate)
			throws Exception {
		return dao.deleteObj("deleteCmsTemplateBean", cmsTemplate);
	}
	
	/**
	 * 获取最后一个模版ID
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getLastTemplateId() throws Exception {
		return (String) dao.getAsObject("getLastTemplateId");
	}
	
	/**
	 * 获取最后一个频道ID
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getLastChannelId() throws Exception {
		return (String) dao.getAsObject("getLastChannelId");
	}
	
	/**
	 * 增加频道管理
	 * 
	 * @param cmsChannel
	 * @return
	 * @throws Exception
	 */
	public CmsChannelBean insertCmsChannelBean(CmsChannelBean cmsChannel)
			throws Exception {
		dao.insertObj("insertCmsChannelBean", cmsChannel);
		return cmsChannel;
	}

	/**
	 * 修改频道管理
	 * 
	 * @param cmsChannel
	 * @return
	 * @throws Exception
	 */
	public int updateCmsChannelBean(CmsChannelBean cmsChannel) throws Exception {
		return dao.updateObj("updateCmsChannelBean", cmsChannel);
	}

	/**
	 * 获取频道管理
	 * 
	 * @param cmsChannel
	 * @return
	 * @throws Exception
	 */
	public CmsChannelBean getCmsChannelBean(CmsChannelBean cmsChannel)
			throws Exception {
		return (CmsChannelBean) dao
				.getAsObject("getCmsChannelBean", cmsChannel);
	}

	/**
	 * 获取频道管理
	 * 
	 * @param cmsChannel
	 * @return
	 * @throws Exception
	 */
	public List<CmsChannelBean> getCmsChannelBeans(CmsChannelBean cmsChannel)
			throws Exception {
		return dao.getAsList("getCmsChannelBean", cmsChannel);
	}

	/**
	 * 获取频道管理
	 * 
	 * @param cmsChannel
	 * @return
	 * @throws Exception
	 */
	public List<CmsChannelBean> getCmsChannelBeans(CmsChannelBean cmsChannel,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getCmsChannelBeanCount",
				cmsChannel);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getCmsChannelBean", cmsChannel, pageInfo);
		}
		return null;
	}

	/**
	 * 删除频道管理
	 * 
	 * @param cmsChannel
	 * @return
	 * @throws Exception
	 */
	public int deleteCmsChannelBean(CmsChannelBean cmsChannel) throws Exception {
		return dao.deleteObj("deleteCmsChannelBean", cmsChannel);
	}
	
	/**
	 * 增加频道历史
	 * 
	 * @param cmsChannelHistoy
	 * @return
	 * @throws Exception
	 */
	public CmsChannelHistoyBean insertCmsChannelHistoyBean(
			CmsChannelHistoyBean cmsChannelHistoy) throws Exception {
		dao.insertObj("insertCmsChannelHistoyBean", cmsChannelHistoy);
		return cmsChannelHistoy;
	}

	/**
	 * 修改频道历史
	 * 
	 * @param cmsChannelHistoy
	 * @return
	 * @throws Exception
	 */
	public int updateCmsChannelHistoyBean(CmsChannelHistoyBean cmsChannelHistoy)
			throws Exception {
		return dao.updateObj("updateCmsChannelHistoyBean", cmsChannelHistoy);
	}

	/**
	 * 获取频道历史
	 * 
	 * @param cmsChannelHistoy
	 * @return
	 * @throws Exception
	 */
	public CmsChannelHistoyBean getCmsChannelHistoyBean(
			CmsChannelHistoyBean cmsChannelHistoy) throws Exception {
		return (CmsChannelHistoyBean) dao.getAsObject(
				"getCmsChannelHistoyBean", cmsChannelHistoy);
	}

	/**
	 * 获取频道历史
	 * 
	 * @param cmsChannelHistoy
	 * @return
	 * @throws Exception
	 */
	public List<CmsChannelHistoyBean> getCmsChannelHistoyBeans(
			CmsChannelHistoyBean cmsChannelHistoy) throws Exception {
		return dao.getAsList("getCmsChannelHistoyBean", cmsChannelHistoy);
	}

	/**
	 * 获取频道历史
	 * 
	 * @param cmsChannelHistoy
	 * @return
	 * @throws Exception
	 */
	public List<CmsChannelHistoyBean> getCmsChannelHistoyBeans(
			CmsChannelHistoyBean cmsChannelHistoy, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject(
				"getCmsChannelHistoyBeanCount", cmsChannelHistoy);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getCmsChannelHistoyBean", cmsChannelHistoy,
					pageInfo);
		}
		return null;
	}

	/**
	 * 删除频道历史
	 * 
	 * @param cmsChannelHistoy
	 * @return
	 * @throws Exception
	 */
	public int deleteCmsChannelHistoyBean(CmsChannelHistoyBean cmsChannelHistoy)
			throws Exception {
		return dao.deleteObj("deleteCmsChannelHistoyBean", cmsChannelHistoy);
	}
	
	/**
	 * 增加栏目管理
	 * 
	 * @param cmsBlock
	 * @return
	 * @throws Exception
	 */
	public CmsBlockBean insertCmsBlockBean(CmsBlockBean cmsBlock)
			throws Exception {
		dao.insertObj("insertCmsBlockBean", cmsBlock);
		return cmsBlock;
	}

	/**
	 * 修改栏目管理
	 * 
	 * @param cmsBlock
	 * @return
	 * @throws Exception
	 */
	public int updateCmsBlockBean(CmsBlockBean cmsBlock) throws Exception {
		return dao.updateObj("updateCmsBlockBean", cmsBlock);
	}

	/**
	 * 获取栏目管理
	 * 
	 * @param cmsBlock
	 * @return
	 * @throws Exception
	 */
	public CmsBlockBean getCmsBlockBean(CmsBlockBean cmsBlock) throws Exception {
		return (CmsBlockBean) dao.getAsObject("getCmsBlockBean", cmsBlock);
	}

	/**
	 * 获取栏目管理
	 * 
	 * @param cmsBlock
	 * @return
	 * @throws Exception
	 */
	public List<CmsBlockBean> getCmsBlockBeans(CmsBlockBean cmsBlock)
			throws Exception {
		return dao.getAsList("getCmsBlockBean", cmsBlock);
	}

	/**
	 * 获取栏目管理
	 * 
	 * @param cmsBlock
	 * @return
	 * @throws Exception
	 */
	public List<CmsBlockBean> getCmsBlockBeans(CmsBlockBean cmsBlock,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getCmsBlockBeanCount",
				cmsBlock);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getCmsBlockBean", cmsBlock, pageInfo);
		}
		return null;
	}

	/**
	 * 删除栏目管理
	 * 
	 * @param cmsBlock
	 * @return
	 * @throws Exception
	 */
	public int deleteCmsBlockBean(CmsBlockBean cmsBlock) throws Exception {
		return dao.deleteObj("deleteCmsBlockBean", cmsBlock);
	}
	
	/**
	 * 获取没结束的已发布信息
	 * 
	 * @return
	 */
	public List<CmsChannelHistoyBean> getNotClosedHistory(String channelId) throws Exception {
		return dao.getAsList("getNotClosedHistory", channelId);
	}
}
