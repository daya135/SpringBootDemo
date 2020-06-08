package org.jzz.spbootDemo.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jzz.spbootDemo.controller.CookieController;
import org.jzz.spbootDemo.model.Address;
import org.jzz.spbootDemo.model.AddressPK;
import org.jzz.spbootDemo.model.AddressRepository;
import org.jzz.spbootDemo.model.User;
import org.jzz.spbootDemo.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userService")
@Transactional

public class UserService {
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userDao;
	@Autowired
	private AddressRepository addressDao;
	
	@Value("${spring.jackson.date-format}")	//获取配置文件值
	private String DATE_FORMAT;
	
	/** 查询所有用户 */
	public List<User>  getAllUser() {
		List<User> list = userDao.findAll();
		return list;
	}
	/** 查询所有地址 */
	public List<Address>  getAllAddress() {
		List<Address> list = addressDao.findAll();
		return list;
	}
	
	/** 根据id查询用户 */
    @Cacheable(value="userCache", key="#id")
	public User getUserById(int id){
    	logger.info("getUserById:" + id);
		Optional<User> optional = userDao.findById(id);
		if (optional != null && !optional.isEmpty())
			return optional.get();
		return null;
	}
    /** 根据姓名查询用户 */
    @Cacheable(value="userCache", key="#name") //缓存，key是按照参数的命名来的！
    public List<User> getUserByName(String name) {
    	logger.info("getUserByName:" + name);
        List<User> user = userDao.findByUserName(name);
        return user;
    }
    public List<User> getUserByNameLike(String name) {
        List<User> user = userDao.findByUserNameContaining(name);
        return user;
    }
    
    public List<User> getUserByNamePage(String name, int page, int size) {
    	Sort sort = Sort.by(Direction.DESC, "userName");
    	Pageable pageable = PageRequest.of(page, size, sort);
    	userDao.findAll(pageable);
    	List<User> user = userDao.findByUserNameContaining(name, pageable);
    	return user;
    }
    
	/** 根据用户id，查询地址 */
	public List<Address> getAddressByUserId(int userId) {
		return addressDao.findByUserId(userId);
	}
	/** 根据用户id和addressType，查询地址 */
	public Address getAddressByUserIdAndType(int userId, String type) {
		Optional<Address> optional = addressDao.findByUserIdAndAddressType(userId, type);
		if (optional != null && !optional.isEmpty())
			return optional.get();
		return null;
	}
	/** 只保存/更新user，忽略Address */
    public User saveUserOnly(User user) throws Exception{  
        try{   
        	user.setAddresss(null);
	        return userDao.save(user);
        } catch(Exception ex) {  
            throw ex;  
        } 
    } 
    /** 同时更新User和Address
     *  不能同时新增，外键问题！
     *  */
    @CachePut(value = "userCache", key = "#user.id")	//更新了缓存，所以再次查询时仍然可以走缓存！
    public User saveUser(User user){  
        try{   
	        return userDao.save(user);
        } catch(Exception ex) {  
            logger.info(ex.getMessage());
        }
        return user;
    }
    
    /** 集中插入User和List<Address>*/
    public User insertUserAndAddress(User user, List<Address> addresses) {
    	User userNew = userDao.save(user);	//插入user
    	for(Address address : addresses) 
    		address.setUser(user); //每个地址必须得持有user
    	userNew.setAddresss(addresses);
    	userNew = userDao.save(userNew);	//再次保存，插入address
    	return userNew;
    }
    
    @CacheEvict(value = "userCache", key = "#id")	//删除缓存，如果不删除则数据库删除后还能从缓存查到数据，亲测
    /** 通过id删除User */
    public void deleteUser(int id) {
    	userDao.deleteById(id);
    }
    /** 删除User（测试级联删除address） */
    public void deleteUserAndAddress(User user) {
    	userDao.delete(user);
    }
    /** 通过id删除Address */
    public void deleteAddress(int id) {
    	addressDao.deleteById(id);
    }
    /** 删除Address(测试级联删除user) */
    public void deleteAddress(Address address) {
    	addressDao.delete(address);
    }
    
    public void saveUserWithException(User user) throws Exception{  
        try{   
	        userDao.save(user);  
	        throw new RuntimeException("模拟插入时抛出异常");
        } catch(Exception e) {  
        	e.printStackTrace();
            throw e;  
        }  
    } 

    
    public User findMaxAge() {
    	return userDao.findTopByOrderByAgeDesc();
    }

    /** 根据级联查询sql返回的字段顺序，手动拼接user对象 
     * 在使用该解决方案时，需注意以下几点要求：
		①注意维持自定义查询语句中的查询字段的顺序、格式转换、嵌套实体属性
		②此种方案在解决特别复杂的查询语句时很高效，因为只需自定义查询语句，与数据库进行一次交互即可，效率可观。但对程序的规范性要求比较高。
		③此方案在解决当前项目数据库中数据表在业务需求下创建而不符合使用JPA框架创建持久化实体之间的关联关系（即因为业务需求，所建立库表不符合数据库建库规范），而又需要进行多表关联进行复杂查询时，很实用。
		特别说明：下面的这个例子只是单纯为了演示此方案，因为使用JPA框架也可轻松实现此类简单需求。
     */
    public User getUserAndAddress(int id) {
    	List<Object[]> uList = userDao.getUserAndAddress(id);
    	User user = new User();
    	if (uList != null && uList.size() > 0) {
    		user = new User();
    		Object[] firstObj = uList.get(0);	//返回的一对多结果中，单方只取一条即可。
    		user.setId((int)firstObj[0]);
    		user.setUserName((String)firstObj[1]);
    		user.setAge((int)firstObj[2]);
			try {
				String birthStr = (String)firstObj[3];
				Date birth = new SimpleDateFormat(DATE_FORMAT).parse(birthStr);	//反序列化Date
				user.setBirth(birth);
			} catch (ParseException e) {
				e.printStackTrace();
			}
    		user.setAddresss(new ArrayList<Address>());	//处理多方属性（嵌套实体集）
    		for (Object[] userObj : uList) {
        		Address address = new Address();
        		address.setAddressType((String)userObj[4]);
        		address.setAddress((String)userObj[5]);
        		user.getAddresss().add(address);
        	}
    	}
    	
    	return user;
    }
}
