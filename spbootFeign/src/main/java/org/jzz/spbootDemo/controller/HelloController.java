package org.jzz.spbootDemo.controller;

import org.jzz.spbootDemo.Service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api-a") 
public class HelloController {
	
	 @Autowired
	 SchedualServiceHi schedualServiceHi;
	
	/* 此类中的方法和远程服务中contoller中的方法名和参数需保持一致。 */
	@RequestMapping(value = "hello") 
	public String hi(@RequestParam String parm){
        return schedualServiceHi.helloService(parm);
    }
}
