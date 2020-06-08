package org.jzz.spbootDemo.Service;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailService{
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * 引入了spring-boot-starter-mail依赖之后，会根据配置文件中的内容去创建JavaMailSender实例
	 */
	@Autowired
	private JavaMailSender mailSender;
	
	// 如果声明为静态变量则无法注入了（可以改用set方式注入）
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
    	
    	String filePath = System.getProperty("user.dir") + "/target/classes/statics/js/testAjax.js";
    	FileSystemResource attachFile1 = new FileSystemResource(new File(filePath));
    	filePath = System.getProperty("user.dir") + "/target/classes/statics/img/delisha.jpg";
    	FileSystemResource attachFile2 = new FileSystemResource(new File(filePath));
    	
    	helper.addAttachment(attachFile1.getFilename(), attachFile1);
    	helper.addAttachment(attachFile2.getFilename(), attachFile2);
    	mailSender.send(mimeMessage);
    	logger.info("sendAttachmentsMail：" + Title);
    }
	
}
