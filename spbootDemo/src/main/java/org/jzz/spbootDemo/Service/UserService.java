package org.jzz.spbootDemo.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jzz.spbootDemo.model.Address;
import org.jzz.spbootDemo.model.AddressPK;
import org.jzz.spbootDemo.model.AddressRepository;
import org.jzz.spbootDemo.model.User;
import org.jzz.spbootDemo.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userService")
@Transactional(readOnly=true)
public class UserService {
	@Autowired
	private UserRepository userDao;
	@Autowired
	private AddressRepository addressDao;
	
	@Value("${spring.jackson.date-format}")	//获取配置文件值
	private String DATE_FORMAT;
	
	public List<User>  getAll() {
		List<User> list = userDao.findAll();
		return list;
	}
	
	public List<User> getUserById(int id){
		return userDao.findById(id);
	}
	
    @Transactional  
    public void saveUser(User user) throws Exception{  
        try{   
	        userDao.save(user);  
        } catch(Exception ex) {  
            throw ex;  
        }  
    } 
    
    @Transactional  
    public void saveUserWithException(User user) throws Exception{  
        try{   
	        userDao.save(user);  
	        throw new RuntimeException("模拟插入时抛出异常");
        } catch(Exception e) {  
        	e.printStackTrace();
            throw e;  
        }  
    } 
    
    /*
     * @Cacheable查询数据库时使用redis缓存，太神奇了！
     * 当数据库有更改时没有刷新缓存。。。bug
     */
    @Cacheable(value="user-key", key="#userName")
    public List<User> getUserByName(String name) {
        List<User> user = userDao.findByUserName(name);
        System.out.println("若没打印此句，且能查询到数据，表示redis配置生效");  
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
    
    public User findMaxAge() {
    	return userDao.findTopByOrderByAgeDesc();
    }
    
    public void deleteUser(User user) {
    	userDao.delete(user);
    }
    
    public List<User> findAll() {
    	return userDao.findAll();
    }
    
    /** 根据级联查询sql返回的字段顺序，手动拼接user对象 
     * 在使用该解决方案时，需注意以下几点要求：
		①注意维持自定义查询语句中的查询字段的顺序、格式转换、嵌套实体属性
		②此种方案在解决特别复杂的查询语句时很高效，因为只需自定义查询语句，与数据库进行一次交互即可，效率可观。但对程序的规范性要求比较高。
		③此方案在解决当前项目数据库中数据表在业务需求下创建而不符合使用JPA框架创建持久化实体之间的关联关系（即因为业务需求，所建立库表不符合数据库建库规范），而又需要进行多表关联进行复杂查询时，很实用。
		特别说明：以下所举的例子只是单纯为了演示此方案，因为使用JPA框架也可轻松实现此类简单需求。
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
    
    /** 同时插入user和address */
    @Transactional
    public User saveUserAndAddress(User user, List<Address> addresses) {
    	User userReturn;
    	user.setAddresss(addresses);
    	userReturn = userDao.save(user);	//同时插入有一个问题！user表主键还没生成，则address表插入外键报错
    	
//    	for (Address address : addresses) {
//    		address.setUser(user);
//    	}
//    	addresses = addressDao.saveAll(addresses);
//    	User userReturn = addresses.get(0).getUser();

//    	userReturn = userDao.save(user);
//    	for (Address address : addresses) {
//    		address.getId().setUserid(userReturn.getId());
//    	}
//    	addressDao.saveAll(addresses);	//不能单独保存，报错 A different object with the same identifier value was already associated with the session
//    	user.setAddresss(addresses);
    	
    	return userReturn; 
    }

}
