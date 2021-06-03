package org.jzz.spbootDemo.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jzz.spbootDemo.Service.UserService;
import org.jzz.spbootDemo.model.User;
import org.jzz.spbootDemo.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
	
	private UserService userService;
	
	/** 如果一个bean有一个构造器，就可以省略@Autowired */
	public ViewController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("success")
	public String index(Model model){
		model.addAttribute("h1value", "这是后台放置的内容");	//往model放数据相当于request.setAttribute(),在参数解析章节分析过了原理;
		model.addAttribute("link", "http://127.0.0.1/get_urlencoded_body");
		return "success";
	}
	
}
