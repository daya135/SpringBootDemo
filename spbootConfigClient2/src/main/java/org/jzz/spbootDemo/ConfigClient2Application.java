package org.jzz.spbootDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient //注册中心地址，用于访问配置服务
@SpringBootApplication
@RestController
@RefreshScope //必须加，否则不能刷新配置
public class ConfigClient2Application {
	
	/*
	 * Spring Boot建议将我们main方法所在的这个主要的配置类配置在根包名下。
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConfigClient2Application.class, args);
	}
	
    @Value("${foo}") //从远程配置库获取值
    String foo;
    
    @RequestMapping(value = "/hi")
    public String hi(){
        return foo;
    }
	
}
