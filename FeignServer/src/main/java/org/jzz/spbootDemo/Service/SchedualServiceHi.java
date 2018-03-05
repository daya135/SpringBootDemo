package org.jzz.spbootDemo.Service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/* name:远程服务名 */
//@FeignClient(value = "SPBOOTDEMO")
@FeignClient(value = "SPBOOTDEMOA", fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {
	
	/* 此类中的方法和远程服务中contoller中的方法名和参数需保持一致。 */
	@RequestMapping(value = "/hello")
	String hello(@RequestParam(value = "parm") String name);
	
	@RequestMapping(value = "/hello2")
	String hello2(@RequestParam(value = "parm") String name);
}
