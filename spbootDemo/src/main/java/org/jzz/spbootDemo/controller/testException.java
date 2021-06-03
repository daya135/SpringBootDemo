package org.jzz.spbootDemo.controller;

import org.jzz.spbootDemo.Exception.MyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/testexception")
public class testException {
	
	@GetMapping("test")
	public String test() {
		throw new MyException();
	}
}
