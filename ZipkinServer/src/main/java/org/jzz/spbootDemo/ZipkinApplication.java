package org.jzz.spbootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;


@SpringBootApplication
@EnableZipkinServer //开启ZipkinServer的功能,必须用jdk1.8！！
public class ZipkinApplication {
	
	/*
	 * Spring Boot建议将我们main方法所在的这个主要的配置类配置在根包名下。
	 */
	public static void main(String[] args) {
		SpringApplication.run(ZipkinApplication.class, args);
	}
}
