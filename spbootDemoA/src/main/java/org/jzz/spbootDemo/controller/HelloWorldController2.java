package org.jzz.spbootDemo.controller;

import java.util.Date;

import org.jzz.spbootDemo.model.UserSpbt;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class HelloWorldController2 {
	
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(HelloWorldController2.class);

	@RequestMapping("/hello2")
	public UserSpbt index() {
		UserSpbt user = new UserSpbt();
		user.setAge(12);
		user.setBirth(new Date());
		user.setUserName("测试姓名");
		
		logger.trace("日志输出， trace");
		logger.debug("日志输出， debug");
		logger.info("日志输出， info");
		logger.warn("日志输出， warn");
		logger.error("日志输出， error");
		return user;
	}
	

}
