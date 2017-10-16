package org.jzz.spbootDemo.controller;

import org.jzz.spbootDemo.Service.BindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api-b")
public class HelloController {
	
	@Autowired
	BindService bindService;
	
	@RequestMapping(value = "hello") 
	public String hi(@RequestParam String parm){
        return bindService.helloService(parm);
    }
}
