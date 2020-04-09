package org.jzz.spbootDemo.controller;


import org.slf4j.LoggerFactory;
import org.jzz.spbootDemo.model.UserSpbt;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Merin
 * 测试 GET vs POST; urlencoded vs json 对请求参数的要求
 * 测试'@modelAttribute' vs '@Requestbody' 绑定bean
 */
@Controller
@RequestMapping("/getandpost")
public class GetAndPostController {
	
	Logger logger = LoggerFactory.getLogger(GetAndPostController.class);
	
	/**
	 * 请求类型：GET 参数位置：url 格式：urlencoded
	 */
	@RequestMapping(value="get_url", method=RequestMethod.GET)
	public @ResponseBody UserSpbt get_url(@ModelAttribute UserSpbt user) {
		return user;
	}
	
	/**
	 * 请求类型：GET 参数位置：httpBody 格式：urlencoded
	 * 如果前端工具没有发送body，则所有字段为空！！
	 */
	@RequestMapping(value="get_urlencoded_body", method=RequestMethod.GET)
	public @ResponseBody UserSpbt get_urlencoded_body(@ModelAttribute UserSpbt user) {
		return user;
	}
	
	/**
	 * 请求类型：GET 参数位置：httpBody 格式：json
	 * 如果前端工具没有发送body，则抛出异常！！
	 */
	@RequestMapping(value="get_json_body", method=RequestMethod.GET)
	public  @ResponseBody UserSpbt get_json_body(@RequestBody UserSpbt user) {
		return user;
	}
	
	/**
	 * 请求类型：POST 参数位置：url 格式：urlencoded
	 */
	@RequestMapping(value="post_url", method=RequestMethod.POST)
	public @ResponseBody UserSpbt post_url(@ModelAttribute UserSpbt user) {
		return user;
	}
	
	/**
	 * 请求类型：POST 参数位置：body 格式：x-www-form-urlencoded
	 * 使用@ModelAttribute，绑定body中的application/x-www-form-urlencoded参数
	 */
	@RequestMapping(value="post_urlencoded_body", method=RequestMethod.POST)
	public @ResponseBody UserSpbt post_urlencoded_body(@ModelAttribute UserSpbt user) {
		return user;
	}
	
	/**
	 * 请求类型：POST 参数位置：body 格式：application/json
	 * @RequestBody  可以绑定application/json格式的数据, 不能绑定x-www-form-urlencoded格式
	 */
	@RequestMapping(value="post_json_body", method=RequestMethod.POST)
	public @ResponseBody UserSpbt post_json_body(@RequestBody UserSpbt user) {
		return user;
	}
	
}
