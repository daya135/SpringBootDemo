package org.jzz.spbootDemo;

import java.util.List;

import org.junit.runner.RunWith;
import org.jzz.spbootDemo.Dao.UserDao;
import org.jzz.spbootDemo.Service.MailService;
import org.jzz.spbootDemo.Service.UserService;
import org.jzz.spbootDemo.model.AddressSpbt;
import org.jzz.spbootDemo.model.UserRepository;
import org.jzz.spbootDemo.model.UserSpbt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	/* 直接@Autowired来注入我们要测试的类实例。 */
	
	@Autowired
	UserService userService;
	
	@Autowired 
	UserRepository userDao;
	
	@Autowired
	MailService mailService;
	
	@org.junit.Test
	public void Test() throws Exception {
//		List<UserSpbt> uSpbts = userService.getUserByNamePage("地狱少女");
		
//		UserSpbt user = userDao.findTopByOrderByAgeDesc();
//		System.out.println(user);
//		userService.deleteUser(user); //通过service删除不生效，why？？
//		userDao.delete(user);	//通过dao删除就生效，什么鬼！？？
//		System.out.println(userDao.findAll());
		
//		System.out.println(userDao.countByUserName("地狱少女3"));
		
		//自定义结果集+翻页测试
//    	Pageable pageable = new PageRequest(1, 3);
//		List<Object[]> userAddress = userDao.getUserAndAddress(new Long(2), pageable);
//		for (Object[] objs : userAddress) {
//			for (int i = 0; i < objs.length; i++)
//			System.out.println(objs[i]);
//		}
		
		//多表插入事务测试
//		UserSpbt user = new UserSpbt();
//		AddressSpbt address = new AddressSpbt();
//		user.setUserName("事务测试");
//		address.setAddress("事务测试地址");
//		userService.regist(user, address);
		
		//发送邮件测试
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendTextMail("jzzperson@163.com", "test html mail", "简单邮件内容");
        mailService.sendAttachmentsMail("jzzperson@163.com", "test html mail", "简单邮件内容");
	}
}
