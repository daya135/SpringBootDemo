package org.jzz.spbootDemo.Service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/* SchedualServiceHiHystric需要实现SchedualServiceHi 接口，并注入到Ioc容器中 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi{
	
	@Override
	public String hello(@RequestParam(value = "parm") String name){
		return "sorry "+name;
	}
	
	@Override
	public String hello2(@RequestParam(value = "parm") String name){
		return "sorry 2" + name;
	}
}
