package org.jzz.spbootDemo.Service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/* name:远程服务名 */
@FeignClient(value = "SPBOOTDEMO",fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {
	
	/* 映射的地址要跟远程服务名地址一样  */
	@RequestMapping(value = "/api-a/hello",method = RequestMethod.GET)
	String helloService(@RequestParam(value = "parm") String name);
}
