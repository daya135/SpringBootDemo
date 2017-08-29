package org.jzz.spbootDemo.controller;

import org.jzz.spbootDemo.Service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	 @Autowired
	 SchedualServiceHi schedualServiceHi;
	
	@RequestMapping(value = "/hello") 
	public String hi(@RequestParam String parm){
        return schedualServiceHi.helloService(parm);
    }
}
