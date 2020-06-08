package org.jzz.spbootDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.jzz.spbootDemo.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMail {
	
	@Autowired
	MailService mailService;

	@Test
	public void testSendMail() throws Exception {
		mailService.sendAttachmentsMail("jzzperson@163.com", "来自springboot的测试邮件", "来自springboot的测试邮件");
	}
}
