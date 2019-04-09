package com.parkingms.action;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * LC 邮件发送
 */
@Controller
public class MailAction {
	// 在spring中配置的邮件发送的bean
	@Autowired
	private JavaMailSender javaMailSender;

	@RequestMapping(value = "/sendMail.action")
	public @ResponseBody String sendMail() {
		// 创建邮件对象
		MimeMessage mMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mMessageHelper;
		Properties prop = new Properties();
		String from;
		try {
			// 从配置文件中拿到发件人邮箱地址
			prop.load(this.getClass().getResourceAsStream("/config/mail.properties"));
			from = prop.get("mail.smtp.username") + "";
			mMessageHelper = new MimeMessageHelper(mMessage, true);
			// 发件人邮箱
			mMessageHelper.setFrom(from);
			// 收件人邮箱
			mMessageHelper.setTo("408463085@qq.com");
			// 邮件的主题
			mMessageHelper.setSubject("Spring的邮件发送");
			// 邮件的文本内容，true表示文本以html格式打开
			mMessageHelper.setText("<p>这是使用spring的邮件功能发送的一封邮件</p><br/>" + "<img src='cid:fengye'>", true);
			// 在邮件中添加一张图片
			File file = new File("C:/Users/Public/Pictures/Sample Pictures/Penguins.jpg");
			FileSystemResource resource = new FileSystemResource(file);
			// 这里指定一个id,在上面引用
			mMessageHelper.addInline("fengye", resource);
			// 在邮件中添加一个附件
			mMessageHelper.addAttachment("枫叶.png", resource);
			// 发送邮件
			javaMailSender.send(mMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "发送成功";
	}
}