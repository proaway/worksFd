package com.fd.fashion.modules.screens.tickets;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.modules.screens.SecureScreen;
import com.fd.fashion.ticket.bean.TicketsAttachment;
import com.fd.fashion.ticket.bean.TicketsInfoBean;
import com.fd.fashion.ticket.manager.ITicketsManager;
import com.fd.fashion.ticket.vo.AttachmentVo;
import com.fd.fashion.ticket.vo.SearchTicketVo;
import com.fd.fashion.ticket.vo.TicketInfoVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.ImageUtil;
import com.fd.util.RewriteUtil;
import com.fd.util.WebPropUtil;

public class TicketsReply extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		// 后台操作人员信息
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session
				.getAttribute(SessionConstants.KEY_LOGIN_BUYER);

		Long ticketId = data.getParameters().getLongObject("ticketsId");
		context.put("ticketsId", ticketId);
		
		String flag = data.getParameters().getString("flag");
		if(flag == null || flag.equals("") ){
			context.put("flag", 1);
		}
		
		
		ITicketsManager ticketsManager = (ITicketsManager) getBean("ticketsManager");

		String actionFlag = data.getParameters().getString("actionFlag");
		if (actionFlag != null && actionFlag.equals("saveTicketFlag")) {
			TicketInfoVo ticketInfo = new TicketInfoVo();
			String messContent = data.getParameters().getString("messContent");
			TicketsInfoBean ticket = new TicketsInfoBean();
			ticket.setIdentity(2);
			ticket.setMessage(messContent);
			ticket.setSender(buyer.getUserId());
			ticket.setSendTime(new Date());
			ticket.setTicketsId(ticketId);
			ticket.setEmail(0);
			ticketInfo.setTicketInfo(ticket);
			ticketInfo.setAttachList(null);
			String[] urls = data.getParameters().getStrings("url");
			if(urls!=null && urls.length>0){
				List<AttachmentVo> listVo = new ArrayList<AttachmentVo>();
				listVo = getAttachList(urls);
				ticketInfo.setAttachList(listVo);
			}
			
			ticketInfo = ticketsManager.insertTicketsInfoBean(ticketInfo);
			if (ticket != null && ticket.getTicketsInfoId() != null) {
				context.put("addTicket", ticket);
			}
		} else if (actionFlag != null && actionFlag.equals("uploadFlag")) {
			String messContent = data.getParameters().getString("messContent");
			context.put("messContent", messContent);
			String[] urls = data.getParameters().getStrings("url");

			// 上传功能
			WebPropUtil wpu = new WebPropUtil();
			String savePath = wpu.get("image.path.root");
			String saveUrl = wpu.get("website.imagehttproot") + "/";
			data.getResponse().setContentType("text/html; charset=UTF-8");
			// 创建文件夹
			savePath += "/";
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			savePath += "message" + "/" + ymd + "/";
			saveUrl += "message" + "/" + ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			FileItem item = (FileItem) data.getParameters().getFileItem(
					"fileOldName");
			upload.setHeaderEncoding("UTF-8");
			String fileName = item.getName();
			if(item.getSize()>2*1024*1024){
				context.put("msg", "this file is too large");
			}else{
				if (!item.isFormField()) {
					String fileExt = fileName.substring(
							fileName.lastIndexOf(".") + 1).toLowerCase();
					long dateTime = System.nanoTime();
					String newFileName = dateTime + "." + fileExt;
					try {
						File uploadedFile = new File(savePath, newFileName);
						item.write(uploadedFile);
					} catch (Exception e) {
						context.put("msg", "upload file failure。");
						e.printStackTrace();
						return;
					}
					context.put("url", savePath + newFileName);
					List<AttachmentVo> list = new ArrayList<AttachmentVo>();
					if (urls != null && urls.length > 0) {
						list=getAttachList(urls);
					}
					AttachmentVo attachmentVo = new AttachmentVo();
					TicketsAttachment tick = new TicketsAttachment();
					tick.setAttachUrl(savePath + newFileName);
					attachmentVo.setAttachment(tick);
					attachmentVo.setAttachExt(fileExt);
					attachmentVo.setAttachName(newFileName);
					attachmentVo.setAttachPath(saveUrl+newFileName);
					list.add(attachmentVo);
					if (list != null && list.size() > 0) {
						context.put("attaList", list);
					}
				}
			}
		}

		SearchTicketVo searchTicket = new SearchTicketVo();
		searchTicket = ticketsManager.getTicketVo(ticketId);
		context.put("ticketVo", searchTicket);

		if (searchTicket != null && searchTicket.getTicketInfoList() != null
				&& searchTicket.getTicketInfoList().size() > 0) {
			context.put("ticketInfoVo", searchTicket.getTicketInfoList().get(0));
		}
	}
	
	public List<AttachmentVo> getAttachList(String[] urls) throws Exception{
		if(urls!=null && urls.length>0){
			List<AttachmentVo> list = new ArrayList<AttachmentVo>();
			for (int i = 0; i < urls.length; i++) {
				AttachmentVo av = new AttachmentVo();
				TicketsAttachment ta = new TicketsAttachment();
				ta.setAttachUrl(urls[i]);
				av.setAttachment(ta);
				String str = RewriteUtil.getImageUrl(urls[i]);
				av.setAttachPath(str);
				int s = urls[i].lastIndexOf("/");
				str = urls[i].substring(s + 1);
				av.setAttachName(str);
				s = urls[i].lastIndexOf(".");
				str = urls[i].substring(s + 1);
				av.setAttachExt(str);
				list.add(av);
			}
			return list;
		}
		return null;
		
	}

}
