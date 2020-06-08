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
	
	@RequestMapping("index")
	public String index(Model model){
		List<User> users = userService.getUserByName("jzz");
		User user;
		if (users != null && users.size() > 0) {
			user = users.get(0);
		} else {
			user = new User(1, "user1");
		}
		model.addAttribute("user", user);	//model 用于向页面添加数据。是attribute！不是返回值！！这个对象是自动创建的
		return "index";
	}
	
	@RequestMapping("index1")
	public ModelAndView index(){
		List<User> users = userService.getUserByName("user1");
		User user;
		if (users != null && users.size() > 0) {
			user = users.get(0);
		} else {
			user = new User(1, "user1");
		}
		ModelAndView view = new ModelAndView("index");	//modelview可以向页面返回视图，另外这个对象需要手动创建！
		view.addObject("user", user);	//同时还能给request增加attribute返回给页面
		return view;
	}
}
