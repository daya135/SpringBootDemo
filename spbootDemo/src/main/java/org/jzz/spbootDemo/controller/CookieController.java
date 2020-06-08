package org.jzz.spbootDemo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/** 测试cookie操作，搭配页面cookie_test.html测试*/
@Controller
@RequestMapping("/cookie")
public class CookieController {
	
	Logger logger = LoggerFactory.getLogger(CookieController.class);
	
	@RequestMapping(value = "test")
	public String testCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String str = String.format("key = %s, value = %s, domain = %s, path = %s, secure = %s",
						cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getPath(), cookie.getSecure());
				//cookie的属性是一次性传给浏览器的，浏览器回传时不会带上这些信息，所以此处只能得到name，value两个属性
				logger.info(str);
			}
		}
		Cookie cookie1 = new Cookie("key1", "my_cookie");	//value不允许带空格
		cookie1.setDomain("localhost");
		cookie1.setVersion(1);
		cookie1.setMaxAge(60);
		Cookie cookie2 = new Cookie("key2", "my_cookie_unsecure");
		cookie2.setPath("/cookie/test");	//设置path，则只在访问此路径时携带此cookie
		cookie2.setMaxAge(60);
		Cookie cookie3 = new Cookie("key2", "my_cookie_secure");	//相同的key，通过设置不同的path隔离
		cookie3.setPath("/cookie/test");
		cookie3.setMaxAge(60);
		cookie3.setSecure(true);	//设置这个属性,则只有https请求能获取此cookie
		
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
		
		return "cookie_test";	//返回cookie页面
	}
	
	/** 测试cookie.setPath，的作用范围*/
	@RequestMapping(value = "test_cookie_path")
	public @ResponseBody String testPath(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String str = String.format("key = %s, value = %s, domain = %s, path = %s, secure = %s",
						cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getPath(), cookie.getSecure());
				logger.info(str);
			}
		}
		Cookie cookie = new Cookie("test_cookie_path", "my_cookie_test_cookie_path");
		cookie.setPath("/cookie/test_cookie_path");		//设置这个后，cookie_test页面的js也获取不到此cookie了
		cookie.setMaxAge(60);
		response.addCookie(cookie);
		
		
		return "test_cookie_path";
	}
	
	
	
}
