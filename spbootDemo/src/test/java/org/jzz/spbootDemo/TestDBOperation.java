package org.jzz.spbootDemo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.jzz.spbootDemo.Service.UserService;
import org.jzz.spbootDemo.model.Address;
import org.jzz.spbootDemo.model.AddressRepository;
import org.jzz.spbootDemo.model.User;
import org.jzz.spbootDemo.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TestDBOperation {
	
	Logger logger = LoggerFactory.getLogger(TestDBOperation.class);
	
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
	}
	
	@org.junit.Test
//	@Ignore
	public void getUserAndAddress_test() throws JsonProcessingException {
		User user = userService.getUserAndAddress(1);
		logger.info("getUserAndAddress_test" + " **** " + objectMapper.writeValueAsString(user));
	}
	@org.junit.Test
//	@Ignore
	public void testSearchUser() throws JsonProcessingException {
		List<User> users = userService.getUserByName("jzz");
		logger.info("testSearchUser" + " **** " + objectMapper.writeValueAsString(users));
	}
	@org.junit.Test
//	@Ignore
	public void testSearchAddress() throws JsonProcessingException {
		List<Address> addresses;
		//通过userid查询地址数据（同时也返回了user对象）
		addresses = addressDao.findByUserId(1);
		User user = addresses.size() > 0 ? addresses.get(0).getUser() : null;
		logger.info("testSearchAddress" + " **** " + objectMapper.writeValueAsString(user));	//查询多的一方也包含单的一方对象，由此看出这种级联耦合性太强了
		logger.info("testSearchAddress" + " **** " + objectMapper.writeValueAsString(addresses));
		
		//查询地址表第一条数据
		Address addressReturn;
		addressReturn = addressDao.getTopAddress();
		logger.info("testSearchAddress" + " **** " + objectMapper.writeValueAsString(addressReturn));
	}
	@org.junit.Test
	@Ignore
	public void testInsertUpdateUser() throws JsonProcessingException {
		User returnUser;
		
		//单独插入user表
		User user = new User();
		user.setUserName("插入姓名");
		user.setAge(100);
		returnUser = userDao.save(user);
		logger.info("testInsertUpdateUser" + " **** " + objectMapper.writeValueAsString(returnUser));
		
		//单独更新user表
		user.setId(3);
		user.setUserName("更新姓名");
		userDao.save(user);
		returnUser = userDao.save(user);
		logger.info("testInsertUpdateUser" + " **** " + objectMapper.writeValueAsString(returnUser));
	}
	@org.junit.Test
	@Ignore
	public void testInsertUpdatAddress() throws JsonProcessingException {
		User user = userDao.getTopUser();
		logger.info("testAddressInsertUpdate" + " **** " + objectMapper.writeValueAsString(user));
		
		//测试地址表单独插入，cascade= {CascadeType.MERGE, CascadeType.REFRESH}
		Address insertAddress = new Address();
		insertAddress.setUser(user);	//插入时，如果不需要插入新用户，则设置原有用户对象
		insertAddress.setAddressType("地址类型");
		insertAddress.setAddress("地址内容");
		insertAddress = addressDao.save(insertAddress);
		logger.info("testAddressInsertUpdate" + " **** " + objectMapper.writeValueAsString(insertAddress));
		
		//测试地址表单独更新，cascade= {CascadeType.MERGE, CascadeType.REFRESH}
		//insertAddress.setUser(xxx)	//如果user对象不一致，则会触发插入操作，fuck！
		Address updateAddress = new Address();
		updateAddress.setUser(insertAddress.getUser());	
		updateAddress.setId(insertAddress.getId());	//更新时要提供主键，不给主键就视为插入（管你条目一不一样）
		updateAddress.setAddressType("新类型");
		updateAddress.setAddress("更新或插入地址");
		updateAddress = addressDao.save(updateAddress);
		logger.info("testInsertUpdatAddress" + " **** " + objectMapper.writeValueAsString(insertAddress));
	}
	@org.junit.Test
	@Ignore
	public void testDeleteAddress() throws JsonProcessingException {
		Address address = addressDao.getTopAddress();
		logger.info("testDeleteAddress" + " **** " + objectMapper.writeValueAsString(address));
		//测试地址表删除，cascade= {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE}
//		addressDao.deleteById(address.getId());	//仅针对单条地址，都会将用户一起删除, 如果用户有多个地址，还会触发外键冲突
//		addressDao.delete(address);	//会将用户一起删除！！
		//cascade= {CascadeType.MERGE, CascadeType.REFRESH}
		addressDao.removeById(address.getId());	//手动写sql删除，总算成了。。
	}
	
	@org.junit.Test
	@Ignore
	public void Test() throws Exception {
		
		
		//自定义结果集+翻页测试
//    	Pageable pageable = PageRequest.of(1, 3, null);
//		List<Object[]> userAddress = userDao.getUserAndAddress(2, pageable);
//		logger.info(userAddress);
//		for (Object[] objs : userAddress) {
//			for (int i = 0; i < objs.length; i++)
//				logger.info(objs[i]);
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
