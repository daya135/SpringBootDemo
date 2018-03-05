package org.jzz.spbootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient //注册中心
@EnableConfigServer //开启配置服务器的功能
@SpringBootApplication
public class ConfigServerApplication {
	
	/*
	 * Spring Boot建议将我们main方法所在的这个主要的配置类配置在根包名下。
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
	
}
