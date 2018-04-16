package org.jzz.spbootDemo.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystem;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.sun.mail.util.MailSSLSocketFactory;

//@Component
public class MailService{
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * 引入了spring-boot-starter-mail依赖之后，会根据配置文件中的内容去创建JavaMailSender实例
	 */
	@Autowired
	private JavaMailSender mailSender;
	
	/* 如果声明为静态变量则无法注入了，这是为什么？？？ */
	@Value("${mail.fromMail.addr}") 
    private String from; //发送账户
    
    public void sendTextMail(String to, String Title, String content){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(Title);
		message.setText(content);
		mailSender.send(message);
    }
    
    public void sendAttachmentsMail(String to, String Title, String content) throws Exception {
    	MimeMessage mimeMessage = mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    	helper.setFrom(from);
    	helper.setTo(to);
    	helper.setSubject(Title);
    	helper.setText(content);
    	
    	FileSystemResource attachFile1 = new FileSystemResource(new File("D:\\BugReport.txt"));
    	FileSystemResource attachFile2 = new FileSystemResource(new File("D:\\Image\\Saved Pictures\\QQ图片20170417094842.png"));
    	helper.addAttachment("附件-1.txt", attachFile1);
    	helper.addAttachment("附件-2.png", attachFile2);
    	mailSender.send(mimeMessage);
    	
    }
	
}
