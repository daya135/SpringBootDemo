package org.jzz.spbootDemo.Service;

import java.util.List;

import org.jzz.spbootDemo.model.AddressRepository;
import org.jzz.spbootDemo.model.AddressSpbt;
import org.jzz.spbootDemo.model.UserRepository;
import org.jzz.spbootDemo.model.UserSpbt;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<UserSpbt>  getAll() {
		List<UserSpbt> list = userDao.findAll();
		return list;
	}
	
    @Transactional  
    public void saveUser(UserSpbt user) throws Exception{  
        try{   
	        userDao.save(user);  
//	        throw new RuntimeException("模拟抛出异常");
        } catch(Exception ex) {  
            System.out.println("执行出错哦：" + ex.getMessage());  
            throw ex;  
        }  
    } 
    
    /*
     * 配置查询数据库时使用缓存，太神奇了！
     * 当数据库有更改时没有刷新缓存。。。bug
     */
    @Cacheable(value="user-key", key="#userName")
    public List<UserSpbt> getUserByName(String name) {
        List<UserSpbt> user = userDao.findByUserName(name);
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");  
        return user;
    }
    
    public List<UserSpbt> getUserByNameLike(String name) {
        List<UserSpbt> user = userDao.findByUserNameContaining(name);
        return user;
    }
    
    public List<UserSpbt> getUserByNamePage(String name, int page, int size) {
    	Sort sort = Sort.by(Direction.DESC, "userName");
    	Pageable pageable = PageRequest.of(page, size, sort);
    	userDao.findAll(pageable);
    	List<UserSpbt> user = userDao.findByUserNameContaining(name, pageable);
    	return user;
    }
    
    public UserSpbt findMaxAge() {
    	return userDao.findTopByOrderByAgeDesc();
    }
    
    public void deleteUser(UserSpbt user) {
    	userDao.delete(user);
    }
    
    public List<UserSpbt> findAll() {
    	return userDao.findAll();
    }
    
    @Transactional
    public int regist(UserSpbt user, AddressSpbt address) {
    	int result = 0;
    	userDao.save(user);
//    	if (result == 0) {
//    		throw new RuntimeException("模拟抛出异常，回滚测试！");
//    	}
    	addressDao.save(address);
    	
    	return result;
    }

}
