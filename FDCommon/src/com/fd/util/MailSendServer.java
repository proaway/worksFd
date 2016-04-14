package com.fd.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.sun.crypto.provider.SunJCE;

/**
 * 发送邮件类
 * 
 * @CreateDate: 2013-3-14 下午09:35:52
 * @author Longli
 * @Description: TODO
 * 
 */
public class MailSendServer {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MailSendServer.class);
	
	private JavaMailSender mailSender;
	private String sendFrom;
	private String[] sendTo;
	
	/**
	 * 发送邮件
	 * 
	 * @param context
	 * @param report
	 * @param temp
	 * @throws Exception
	 */
	public void sendEmail(String subject, String content, File attach, String attachName)
			throws Exception {
		sendEmail(sendFrom, sendTo, subject, content, attach, attachName);
	}
	
	/**
	 * 发送邮件
	 * 
	 * @param context
	 * @param report
	 * @param temp
	 * @throws Exception
	 */
	public void sendEmail(String[] sendTo, String subject, String content, File attach, String attachName)
			throws Exception {
		sendEmail(sendFrom, sendTo, subject, content, attach, attachName);
	}
	
	/**
	 * 发送邮件
	 * 
	 * @param context
	 * @param report
	 * @param temp
	 * @throws Exception
	 */
	public void sendEmail(String[] sendTo, String subject, String templatePath, Map<String, Object> params)
			throws Exception {
		File tempFile = new File(templatePath);
		if (!tempFile.exists()) {
			return;
		}
		Properties prop = new Properties();
		prop.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, tempFile.getParent());
		VelocityEngine engine = new VelocityEngine(prop);
		String content = VelocityEngineUtils.mergeTemplateIntoString(engine, tempFile.getName(), "utf-8", params);
		sendEmail(sendFrom, sendTo, subject, content, null, null);
	}
	
	/**
	 * 发送邮件
	 * 
	 * @param context
	 * @param report
	 * @param temp
	 * @throws Exception
	 */
	public void sendEmail(String[] sendTo, String subject, String templatePath, HashMap<String, Object> params, File attach, String attachName)
			throws Exception {
		VelocityEngine engine = new VelocityEngine();
		String content = VelocityEngineUtils.mergeTemplateIntoString(engine, templatePath, "utf-8", params);
		sendEmail(sendFrom, sendTo, subject, content, attach, attachName);
	}
	
	/**
	 * 发送邮件
	 * 
	 * @param context
	 * @param report
	 * @param temp
	 * @throws Exception
	 */
	public void sendEmail(String sendFrom, String[] sendTo, String subject, String content, File attach, String attachName)
			throws Exception {
		if (sendTo == null || sendTo.length == 0)
			return;
		logger.info(String.format("开始发送邮件: %s,目标帐户个数:%d", subject,
				sendTo.length));
		MimeMessage mimeMsg = getMailSender().createMimeMessage();
		MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMsg, true, 
				"UTF-8");
		mimeHelper.setTo(sendTo);
		mimeHelper.setSubject(subject);
		mimeHelper.setFrom(sendFrom);

		mimeHelper.setText(content, true);
		if (attach != null) {
			mimeHelper.addAttachment(
					MimeUtility.encodeWord(attachName), attach);
		}
		getMailSender().send(mimeMsg);
		logger.info("发送邮件结束");
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}
	
	public static void main(String[] args) {
		try {
			MailSendServer server = (MailSendServer) AppContextUtil.getBean("mailSendServer");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSendFrom() {
		return sendFrom;
	}

	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public String[] getSendTo() {
		return sendTo;
	}

	public void setSendTo(String[] sendTo) {
		this.sendTo = sendTo;
	}
}
