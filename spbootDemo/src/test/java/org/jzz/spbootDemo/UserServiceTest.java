package org.jzz.spbootDemo;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.jzz.spbootDemo.Service.UserService;
import org.jzz.spbootDemo.model.Address;
import org.jzz.spbootDemo.model.AddressPK;
import org.jzz.spbootDemo.model.AddressRepository;
import org.jzz.spbootDemo.model.User;
import org.jzz.spbootDemo.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class UserServiceTest {
	
	@Autowired
	UserService  userService;
	
	@Autowired
	UserRepository userDao;
	
	@Autowired
	AddressRepository addressDao;
	
	
	public static String DATE_FORMAT;

	public static ObjectMapper objectMapper;
	
	@BeforeClass
	public static void init() {
		//此处初始化时获取不到注入的值，因为容器还没初始化！！！
		//所以思路错了，这已经属于集成测试的范畴了！！
		System.out.println(DATE_FORMAT == null);
	}
	
	@org.junit.Test
	@Ignore
	public void getUserAndAddress_test() throws JsonProcessingException {
		User user = userService.getUserAndAddress(1);
		System.out.println(objectMapper.writeValueAsString(user));
	}
	@org.junit.Test
//	@Ignore
	public void SearchUserTest() throws JsonProcessingException {
		List<User> users = userService.getUserByName("jzz");
		System.out.println(objectMapper.writeValueAsString(users));
	}
	@org.junit.Test
//	@Ignore
	public void SearchAddressTest() throws JsonProcessingException {
		List<Address> addresss;
		addresss = addressDao.findByPkUserid(1);	//仅通过用户id查询
		System.out.println(objectMapper.writeValueAsString(addresss));
		addresss = addressDao.findByPkAddressType("家庭");	//需要配置数据库连接的编码，否则中文查不到结果
		System.out.println(objectMapper.writeValueAsString(addresss));	
		Address address = addressDao.findByPkUseridAndPkAddressType(1, "家庭");	//通过主键的多个字段查询
		System.out.println(objectMapper.writeValueAsString(address));	
		Optional<Address> addressOption = addressDao.findById(new AddressPK(1, "家庭"));	//通过构建联合主键对象查询结果
		System.out.println(objectMapper.writeValueAsString(addressOption.get()));
		
	}
	
	@org.junit.Test
	@Ignore
	public void Test() throws Exception {
		
		
		//自定义结果集+翻页测试
//    	Pageable pageable = PageRequest.of(1, 3, null);
//		List<Object[]> userAddress = userDao.getUserAndAddress(2, pageable);
//		System.out.println(userAddress);
//		for (Object[] objs : userAddress) {
//			for (int i = 0; i < objs.length; i++)
//				System.out.println(objs[i]);
//		}
		
		//多表插入事务测试
//		UserSpbt user = new UserSpbt();
//		AddressSpbt address = new AddressSpbt();
//		user.setUserName("事务测试");
//		address.setAddress("事务测试地址");
//		userService.regist(user, address);
		
		//发送邮件测试
//        String content="<html>\n" +
//                "<body>\n" +
//                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
//                "</body>\n" +
//                "</html>";
//        mailService.sendTextMail("jzzperson@163.com", "test html mail", "简单邮件内容");
//        mailService.sendAttachmentsMail("jzzperson@163.com", "test html mail", "简单邮件内容");
		
	}
	
	@Value("${spring.jackson.date-format}")	//静态属性，只能通过set方法注入配置文件值
	public void setDATE_FORMAT(String dATE_FORMAT) {
		DATE_FORMAT = dATE_FORMAT;
		//初始化就放在这里吧，将就一下，也可以注入一个单例容器持有此对象，在使用时getInstance
		//但带来一个新问题，如何标记此工具类仅在测试时生成
		objectMapper = new ObjectMapper();	
		objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));	//全局设置
	}
}
