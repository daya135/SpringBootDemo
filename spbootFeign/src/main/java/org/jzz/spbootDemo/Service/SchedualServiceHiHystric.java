package org.jzz.spbootDemo.Service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi{
	
	@Override
	public String helloService(@RequestParam(value = "parm") String name){
		return "sorry "+name;
	}
}
