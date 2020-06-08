package org.jzz.spbootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling /* 定时任务 */
@EnableCaching	//使用缓存,需要实体实现Serializable接口，在方法上开启@Cacheable
@SpringBootApplication
public class SpbootDemoApplication {
	
	/*
	 * Spring Boot建议将我们main方法所在的这个主要的配置类配置在根包名下。
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpbootDemoApplication.class, args);
	}
}
