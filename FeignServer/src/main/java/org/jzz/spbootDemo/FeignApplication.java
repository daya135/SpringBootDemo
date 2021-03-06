package org.jzz.spbootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/* Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果。 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
/* 底下这两个注解添加后才能支持仪表盘，不添加只支持熔断*/
@EnableHystrixDashboard
@EnableCircuitBreaker
public class FeignApplication {
	
	/*
	 * Spring Boot建议将我们main方法所在的这个主要的配置类配置在根包名下。
	 */
	public static void main(String[] args) {
		SpringApplication.run(FeignApplication.class, args);
	}
	
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
}
