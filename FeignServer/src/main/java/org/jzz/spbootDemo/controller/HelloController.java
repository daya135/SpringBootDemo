package org.jzz.spbootDemo.controller;

import org.jzz.spbootDemo.Service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api-a")
public class HelloController {
	
	 @Autowired
	 SchedualServiceHi schedualServiceHi;
	 @Autowired
	 SchedualServiceHi schedualServiceHi2;
	
	@RequestMapping(value = "helloA")
	public String helloA(@RequestParam String parm){
        return schedualServiceHi.hello(parm);
    }
	
	@RequestMapping(value = "helloB")
	public String helloB(@RequestParam String parm){
        return schedualServiceHi.hello2(parm);
    }
}
