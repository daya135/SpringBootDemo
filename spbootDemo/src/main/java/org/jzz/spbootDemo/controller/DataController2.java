package org.jzz.spbootDemo.controller;

import java.util.Map;

import org.jzz.spbootDemo.model.UserSpbt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试ModelAttribute
 */
@Controller
@RequestMapping("/modattr2")
public class DataController2 {
    
	Logger logger = LoggerFactory.getLogger(DataController2.class);

	/**
	 * @ModelAttribute会优先于@RequestMapping执行，也会在Controller中每个方法执行前被执行，
	 * 所以当一个Controller中有映射到多个Url时，需要谨慎使用
	 * 使用场景为更新操作，从数据库获取一个user，然后自动与前端提交的user合并（保留前端的值）
	 */
//	@ModelAttribute
//	public void getUser(@RequestParam(value="id",required=false) Long id, 
//	         Map<String, Object> map){
//		UserSpbt user = getUserfromDB(id);
//		map.put("user", user);
//	}
	
	/**
	 * 不同形式的写法
	 */
	@ModelAttribute("user") //这里相当于指定了key值
	public UserSpbt getUser(@RequestParam(value="id",required=false) Long id, 
	         Map<String, Object> map){
		UserSpbt user = getUserfromDB(id);
		return user;
	}
    
    @RequestMapping(value="/test", method=RequestMethod.POST)
    public @ResponseBody UserSpbt testModelAttribute(UserSpbt user){
		logger.info ("使用@ModelAttribute自动合并后的User: " + user);
         
        return user;
    }  
    
    private UserSpbt getUserfromDB(Long id) {
		UserSpbt user = new UserSpbt();
		//模拟从数据库中获取对象
		if(id != null){
		    user.setId(id);
		} else {
			user.setId(999L);
		}
		user.setUserName("modelUsername");
		user.setAge(40);
		logger.info("模拟从数据库中获取一个对象: " + user);
		return user;
    }
    
}
