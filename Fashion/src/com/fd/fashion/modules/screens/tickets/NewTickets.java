package com.fd.fashion.modules.screens.tickets;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.fd.fashion.ticket.bean.TicketsBean;
import com.fd.fashion.ticket.bean.TicketsInfoBean;
import com.fd.fashion.ticket.manager.ITicketsManager;
import com.fd.fashion.ticket.vo.AttachmentVo;
import com.fd.fashion.ticket.vo.TicketInfoVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.ImageUtil;
import com.fd.util.JSONUtil;
import com.fd.util.RewriteUtil;
import com.fd.util.WebPropUtil;

/**
 * @author zhangling
 * 
 * 买家新增tickets
 *
 */
public class NewTickets  extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		//后台操作人员信息
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		
		String questionType=data.getParameters().getString("question","1");
		context.put("questionType", Integer.parseInt(questionType));
		String titleContent = data.getParameters().getString("titleContent");
		context.put("titleContent", titleContent);
		String messContent = data.getParameters().getString("messContent");
		context.put("messContent", messContent);
		String[] urls = data.getParameters().getStrings("url");
		List<AttachmentVo> listVo = new ArrayList<AttachmentVo>();
		if(urls!=null && urls.length>0){
			listVo = getAttachList(urls);
		}
		String actionFlag = data.getParameters().getString("actionFlag");
		if(actionFlag!=null && actionFlag.equals("addTicketFlag")){
			//添加ticket信息
			Calendar c = Calendar.getInstance();
			TicketsBean ticket = new TicketsBean();
			ticket.setBuyerId(buyer.getBuyerId());
			ticket.setBuyerName(buyer.getUserId());
			ticket.setIdentity(2);
			ticket.setQuestionType(questionType.equals("")?null:Integer.parseInt(questionType));
			ticket.setStatus(1);
			ticket.setTitle(titleContent);
			ticket.setSendTime(c.getTime());
			//ticketinfo
			TicketsInfoBean ticketsInfo = new TicketsInfoBean();
			ticketsInfo.setEmail(0);
			ticketsInfo.setIdentity(2);
			ticketsInfo.setMessage(messContent);
			ticketsInfo.setSender(buyer.getUserId());
			ticketsInfo.setSendTime(c.getTime());
			TicketInfoVo ticketVo = new TicketInfoVo();
			ticketVo.setTicketInfo(ticketsInfo);
			ticketVo.setAttachList(listVo);
			
			ITicketsManager  ticketsManager = (ITicketsManager)getBean("ticketsManager");
			ticket = ticketsManager.insertTicketsBean(ticket,ticketVo);
			context.put("addTicketVo", ticket);
			
		}else if(actionFlag!=null && actionFlag.equals("uploadFlag")){
			//上传文件
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
						context.put("msg", "upload failure。");
						e.printStackTrace();
						return;
					}
					context.put("url", savePath + newFileName);
					
					AttachmentVo attachmentVo = new AttachmentVo();
					TicketsAttachment tick = new TicketsAttachment();
					tick.setAttachUrl(savePath + newFileName);
					attachmentVo.setAttachment(tick);
					attachmentVo.setAttachExt(fileExt);
					attachmentVo.setAttachName(newFileName);
					attachmentVo.setAttachPath(saveUrl+newFileName);
					listVo.add(attachmentVo);
				}
			}
		}
		if (listVo != null && listVo.size() > 0) {
			context.put("attaList", listVo);
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
