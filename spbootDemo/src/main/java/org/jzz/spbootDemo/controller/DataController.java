package org.jzz.spbootDemo.controller;


import org.slf4j.LoggerFactory;
import org.jzz.spbootDemo.model.User;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController (包含responseBody和controller)
public class DataController {
	
	Logger logger = LoggerFactory.getLogger(DataController.class);
	
	
	/**
	 * 请求类型：POST 参数位置：body 格式：urlencoded
	 */
	@RequestMapping(value="post_urlencoded", method=RequestMethod.POST)
	public @ResponseBody String test1(@ModelAttribute User user) {
		/**
		 * @ModelAttribute 用于绑定application/x-www-form-urlencoded格式的请求数据
		 * 此处绑定表单POST的请求参数（位于httpBody，格式：application/x-www-form-urlencoded）
		 */
		logger.info("@ModelAttribute recive:" + user);
		return "post_urlencoded @ModelAttribute recive：" + user;
	}
	
	/**
	 * 请求类型：POST 参数位置：body 格式：json
	 */
	@PostMapping(value="post_json")
	public @ResponseBody String test2(User user) {	//不用加@RequestBody
		/**
		 * @RequestBody 不能绑定x-www-form-urlencoded格式
		 * 可以绑定json格式的数据，当然此数据一般是位于httpbody中了
		 * 所以此方法不能用于接收传统表单提交参数
		 */
		logger.info("@RequestBody recive:" + user);
		return "post_json @@RequestBody recive：" + user;
	}
	
	/**
	 * 请求类型：GET 参数位置：url 格式：urlencoded
	 */
	@RequestMapping(value="get_urlencoded_url", method=RequestMethod.GET)
	public @ResponseBody String test3(@ModelAttribute User user) {
		logger.info("@ModelAttribute recive:" + user);
		return "get_urlencoded_url @@ModelAttribute recive:" + user;
	}
	
	/**
	 * 请求类型：GET 参数位置：httpBody 格式：urlencoded
	 */
	@RequestMapping(value="get_urlencoded_body", method=RequestMethod.GET)
	public @ResponseBody String test4(@ModelAttribute User user) {
		logger.info("@ModelAttribute recive:" + user);
		return "get_urlencoded_body @@ModelAttribute recive:" + user;
	}
	
	/**
	 * 请求类型：GET 参数位置：httpBody 格式：json
	 */
	@RequestMapping(value="get_json_body", method=RequestMethod.GET)
	public @ResponseBody String test5(@RequestBody User user) {
		logger.info("@RequestBody recive:" + user);
		return "get_json_body @RequestBody recive:" + user;
	}
}
