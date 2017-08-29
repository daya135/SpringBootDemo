package org.jzz.spbootDemo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class BindService {
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "hiError")
	public String helloService(String parm) {
		return restTemplate.getForObject("http://SPBOOTDEMO/hello?parm=" + parm, String.class);
	}
	
    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
