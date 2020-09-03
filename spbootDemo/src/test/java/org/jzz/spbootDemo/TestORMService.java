package org.jzz.spbootDemo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.jzz.spbootDemo.Service.AddressService;
import org.jzz.spbootDemo.Service.UserService;
import org.jzz.spbootDemo.model.Address;
import org.jzz.spbootDemo.model.AddressRepository;
import org.jzz.spbootDemo.model.User;
import org.jzz.spbootDemo.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/** 测试双表级联的增删改查
 *  环境：地址表有自增主键id，有userid列为实体外键
 *  */
@SpringBootTest
//	注意单元测试的版本，否则就是坑！！
//@RunWith(SpringRunner.class)	//这个是junit4的，加上就只能使用junit4的注解，否则会报错
public class TestORMService {
	static Logger logger = LoggerFactory.getLogger(TestORMService.class);
	
	@Autowired
	UserService  userService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	UserRepository userDao;
	
	@Autowired
	AddressRepository addressDao;
	
	static ObjectMapper jsonMapper;
	static Random random;
	
	//BeforeAll 是junit5的注解，注意所有单元测试的注解要配套使用，否则就是坑！！
	@BeforeAll	
	public static void initSomething() {
		jsonMapper = new ObjectMapper();
		jsonMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
		random = new Random(System.currentTimeMillis());
	}
	
//	@Test
	public void InsertUserOnly() throws Exception {
		User user = new User("saveUserOnly", 10, new Date());
		User newUser = userService.saveUserOnly(user);	//userDao.save(user) ，user.address为空，插入成功
		logger.info(jsonMapper.writeValueAsString(newUser));
	}
//	@Test
	public void insertUserAndAddress() throws Exception {
		User user = new User("insertUserAndAddress()" + random.nextInt(100), 10, new Date());
		Address address = new Address("家庭", "家庭地址saveUser");
		address.setUser(user);
		user.setAddresss(Arrays.asList(address));
		User newUser = userDao.save(user);	//user.id为空，且user.address.user.id为空，同时新增user和address， userDao.save(user)报错。
		logger.info(jsonMapper.writeValueAsString(newUser));
	}
//	@Test
	public void insertUserAndAddress2() throws Exception {
		User user = new User("insertUserAndAddress2()" + random.nextInt(100), 10, new Date());
		Address address = new Address("家庭", "insertUserAndAddress2()" + random.nextInt(100));
		Address address2 = new Address("工作",  "insertUserAndAddress2()" + random.nextInt(100));
		User insertObj = userService.insertUserAndAddress(user, Arrays.asList(address, address2));	//先保存user，再根据返回id保存address, 插入成功
		logger.info(jsonMapper.writeValueAsString(insertObj));
	}
//	@Test
	public void insertAddress() throws Exception {
		User user = userDao.getTopUser();
		logger.info(jsonMapper.writeValueAsString(user));	//自定义查询也能得到完整的实体，即只查user表仍然能得到address信息。。。
		List<Address> addresses = user.getAddresss();
		Address address = new Address("家庭", "家庭地址saveUser");
		if (addresses == null) {
			addresses = Arrays.asList(address);
		} else {
			addresses.add(address);
		}
		//这是一方放弃关系维护时，正确的多方插入姿势！！别忘了给插入的多方数据设置关联的一方对象！
		address.setUser(user);	//若不写此句，则user.id存在，且user.address.user.id不存在, 执行userDao.save(user)预期会在adress表插入一条没userid的条目，若有外键约束则报错（但即使没外键约束，插入成功也不符合逻辑）
//		User insertObj = userService.saveUser(user);	//user.id存在，且user.address.user.id存在, userDao.save(user)插入成功；
		Address insertObj = addressDao.save(address);	//address.user.id存在，addressDao.save(address)插入成功
		logger.info(jsonMapper.writeValueAsString(insertObj));
	}
	
//	@Test
	/** 删除address,在user.address中移除要删除的address对象,执行userDao.save() ，结果很诡异*/
	@Transactional
	@Rollback(false)
	public void deleteAddress1() throws Exception {
		int id = 15;
		User user = userDao.findById(id).get();
		logger.info(jsonMapper.writeValueAsString(user.getAddresss()));
		user.getAddresss().remove(0);
		user = userDao.save(user);	//执行成功，返回的user也少了地址，但数据库没删除，这是最神奇的
		logger.info(jsonMapper.writeValueAsString(user.getAddresss()));
		user = userDao.findById(id).get();	//这里查出来的user少了地址，但数据库没删除
		logger.info(jsonMapper.writeValueAsString(user.getAddresss()));
	}
	
//	@Test
	/** 删除address, 移除address.user（这种断开连接的方式是错的，user是主控方）,执行addressDao.delete(address) 不行*/
	@Transactional //测试方法必须加上（即使已在service加了此注解）,否则删除操作被跳过 
	@Rollback(false)//如果不加这个，junit删完默认会自动回滚
	public void deleteAddress3() throws Exception {
		int id = 15;
		User user = userDao.findById(id).get();
		logger.info(jsonMapper.writeValueAsString(user.getAddresss()));
		Address address = user.getAddresss().get(0);
//		address.setUser(null);	//从address方断开连接的方式不行，导致删除操作报错
		addressDao.delete(address);	//无法删除，
		user = userDao.findById(id).get();	//此处可以查到地址，证明没删除
		logger.info(jsonMapper.writeValueAsString(user.getAddresss()));
	}
//	@Test
	/** 删除address,在user.address中移除要删除的address对象，执行addressDao.delete(address) */
	@Transactional
	@Rollback(false)	
	public void deleteAddress4() throws Exception {
		int id = 16;
		User user = userDao.findById(id).get();
		logger.info(jsonMapper.writeValueAsString(user.getAddresss()));
		Address address = user.getAddresss().get(0);
		user.getAddresss().remove(address);		//从user方断开连接
		addressDao.delete(address);		//删除成功
		user = userDao.findById(id).get();	
		logger.info(jsonMapper.writeValueAsString(user.getAddresss()));
	}
	@Test
	@Transactional
	@Rollback(false)
	/** 删除address, ,在user.address中移除要删除的address对象，执行addressDao.deleteById删除*/
	public void deleteAddress5() throws Exception {
		int id = 15;
		User user = userDao.findById(id).get();
		logger.info(jsonMapper.writeValueAsString(user.getAddresss()));
		Address address = user.getAddresss().get(0);
		user.getAddresss().remove(address);	//即使是通过id删除，因为实体生命周期的原因，此处也需要手动断开连接
		addressDao.deleteById(address.getId());	//删除成功
		user = userDao.findById(id).get();	
		logger.info(jsonMapper.writeValueAsString(user.getAddresss()));
	}
	/** 删除address, 不断开连接，执行addressDao.deleteById删除*/
//	@Test
	@Transactional
//	@Rollback(false)	
	public void deleteAddress6() throws Exception {
		int id = 15;
		User user = userDao.findById(id).get();
		logger.info(jsonMapper.writeValueAsString(user.getAddresss()));
		Address address = user.getAddresss().get(0);
		logger.info("address count before= " + addressDao.findAll().size());
		addressDao.deleteById(address.getId());	//无法删除
		user = userDao.findById(id).get();	
		logger.info(jsonMapper.writeValueAsString(user.getAddresss()));
	}
	
}
