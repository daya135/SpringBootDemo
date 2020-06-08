package org.jzz.spbootDemo.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.jzz.spbootDemo.Service.UserService;
import org.jzz.spbootDemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "user")
	public @ResponseBody User searchById(@RequestParam(name = "id", required = false)Integer id, 
								@RequestParam(name = "username", required=false) String userName) {
		User user = null;
		if (id != null) {
			user = userService.getUserById(id);
		} else if (userName != null){
			List<User> users = userService.getUserByName(userName);
			user = (users == null || users.size() == 0) ? null : users.get(0);
		}
		return user;
	}
	
	@PostMapping(value = "user")
	public @ResponseBody User updateUser(@RequestParam(name = "id", required = false)Integer id) {
		User user = userService.getUserById(id);
		user.setAge(new Random().nextInt(100));
		user.setBirth(new Date());
		user = userService.saveUser(user);
		return user;
	}
	
	@DeleteMapping(value = "user")
	public @ResponseBody String deleteUser(@RequestParam(name = "id", required = false)Integer id) {
		userService.deleteUser(id);
		return "delete success! id = id";
	}
}
