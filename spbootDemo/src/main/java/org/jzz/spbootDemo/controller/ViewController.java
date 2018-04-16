package org.jzz.spbootDemo.controller;

import java.util.List;

import org.jzz.spbootDemo.Service.UserService;
import org.jzz.spbootDemo.model.UserSpbt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("index")
	public String index(Model model){
		List<UserSpbt> users = userService.getUserByName("user1");
		UserSpbt user;
		if (users != null && users.size() > 0) {
			user = users.get(0);
		} else {
			user = new UserSpbt(1L, "user1");
		}
		model.addAttribute("user", user);	//model 用于向页面添加数据。是attribute！不是返回值！！这个对象是自动创建的
		return "index";
	}
	
	@RequestMapping("index1")
	public ModelAndView index(){
		List<UserSpbt> users = userService.getUserByName("user1");
		UserSpbt user;
		if (users != null && users.size() > 0) {
			user = users.get(0);
		} else {
			user = new UserSpbt(1L, "user1");
		}
		ModelAndView view = new ModelAndView("index");	//modelview可以向页面返回视图，另外这个对象需要手动创建！
		view.addObject("user", user);	//同时还能给request增加attribute返回给页面
		return view;
	}
}