package org.jzz.spbootDemo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jzz.spbootDemo.Service.UserService;
import org.jzz.spbootDemo.model.AddressRepository;
import org.jzz.spbootDemo.model.AddressSpbt;
import org.jzz.spbootDemo.model.UserSpbt;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class HelloWorldController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AddressRepository addressRepository;
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
    @Value("${server.port}")
    String port;
    
    @RequestMapping("hello")
    public String hello(@RequestParam String parm) {
    	 return "this is spbootDemoB, port= "+ port +", parm=" +parm;
    }
	
	@RequestMapping("/user")
	public List<UserSpbt> index() {
		UserSpbt user = new UserSpbt();
		user.setAge(12);
		user.setBirth(new Date());
		user.setUserName("测试姓名111");
		List<UserSpbt> list = null;
		List<UserSpbt> list2 = null;
		try {
			//userService.saveUser(user);
			list = userService.getAll();
//			list2 = userService.getUserByName("地狱少女");
			System.out.println(list);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		logger.trace("日志输出， trace");
		logger.debug("日志输出， debug");
		logger.info("日志输出， info");
		logger.warn("日志输出， warn");
		logger.error("日志输出， error");
		return list;
	}
	
	@RequestMapping("/address")
	public List<AddressSpbt> getAddress(HttpServletRequest request) {
		request.getParameter("name");
		List<AddressSpbt> address = null;
		address = addressRepository.findByAddressByUserName("测试姓名");
		
		return address;
	}
	
}
