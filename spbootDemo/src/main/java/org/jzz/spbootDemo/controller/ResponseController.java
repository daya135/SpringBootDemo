package org.jzz.spbootDemo.controller;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.jzz.spbootDemo.model.Address;
import org.jzz.spbootDemo.model.User;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/response")
public class ResponseController {

	@GetMapping("file")
	@ResponseBody
	public FileSystemResource file(HttpServletResponse response) {
		String path = ClassUtils.getDefaultClassLoader().getResource("").getPath().toString() + "application.properties";
//		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);	//此处设置了也没用？？
		return new FileSystemResource(path);
	}
	
	@GetMapping("filedownload")
	@ResponseBody
	public ResponseEntity<FileSystemResource> file2() {
		String path = ClassUtils.getDefaultClassLoader().getResource("").getPath().toString() + "application.properties";
//		return ResponseEntity.ok().body(new FileSystemResource(path));	//不加contentTyoe则直接展示文件（text/html）
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(new FileSystemResource(path));	//文件下载
	}
	
	@ResponseBody
	@GetMapping("user")			//为什么返回的json而不是xml！！！因为ajax请求发送的accept是*/*,则MessageConverter选择第一个匹配的为json，使用浏览器则返回了xml
	public User user() {
		User user = new User();
		user.setUserName("aaa");
		Address address = new Address();
		address.setAddress("xxx大街xxx号");
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address);
		user.setAddresss(addresses);
		return user;
	}
	
}
