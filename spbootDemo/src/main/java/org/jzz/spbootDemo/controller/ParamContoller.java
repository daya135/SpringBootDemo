package org.jzz.spbootDemo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jzz.spbootDemo.Service.UserService;
import org.jzz.spbootDemo.model.UserRepository;
import org.jzz.spbootDemo.model.UserSpbt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 1.请求参数为urlencode，映射为@RequestParam
 * 3.请求参数映射为json，映射为JsonNode
 */
@Controller
@RequestMapping("/parm")
public class ParamContoller {
    
	Logger logger = LoggerFactory.getLogger(ParamContoller.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userDao;

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
//	@ModelAttribute("user") //这里相当于指定了key值
//	public UserSpbt getUser(@RequestParam(value="id",required=true) int id, 
//	         Map<String, Object> map){
//		UserSpbt user = getUserfromDB(id);
//		return user;
//	}
//    
//    @RequestMapping(value="/test", method=RequestMethod.POST)
//    public @ResponseBody UserSpbt testModelAttribute(UserSpbt user){
//		logger.info ("使用@ModelAttribute自动合并后的User: " + user);
//         
//        return user;
//    }  
    
	/** 根据姓名查询用户
	 * 测试时前端分别传递位于body、url中的urlencode格式参数
	 */
	@RequestMapping(value = "/find_name", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<UserSpbt> testUrlParms(@RequestParam(value = "userName", required = true)String name) {
		List<UserSpbt> users = userService.getUserByName(name);
		return users;
	}
	
	/** 根据姓名、年龄查询用户
	 * 测试时前端分别传递位于body、url中的urlencode格式参数
	 */
	@RequestMapping(value = "/find_name_age", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<UserSpbt> testUrlParms(@RequestParam(value = "userName", required = true)String name, @RequestParam(value = "age", required = true)int age) {
		List<UserSpbt> users = userDao.findByUserNameAndAge(name, age);
		return users;
	}
    
	/** 获得json串，解析为JsonNode（jackson）
	 * 不能直接用@responseBody映射为jsonObj或Map
	 */
	@RequestMapping(value = "/recive_json", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String testUrlParmsToJson(HttpServletRequest request) {
		String resString = "error";
		try {
			resString = new ObjectMapper().readTree(request.getInputStream()).toString();	//直接将输入流转换为json串
			logger.info("testUrlParmsToJson: " + resString);
		} catch (IOException e) {
			resString = e.getMessage();
		}
		return resString;
	}
    
}
