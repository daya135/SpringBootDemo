package org.jzz.spbootDemo;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@RestController
public class ZipkinClient2 {
	
	
	/*
	 * Spring Boot建议将我们main方法所在的这个主要的配置类配置在根包名下。
	 */
	public static void main(String[] args) {
		SpringApplication.run(ZipkinClient2.class, args);
	}
	
	 private static final Logger logger = Logger.getLogger(ZipkinClient2.class.getName());


    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/hi")
    public String home(){
    	logger.log(Level.INFO, "hi is being called");
        return "hi i'm miya!";
    }

    @RequestMapping("/miya")
    public String info(){
    	logger.log(Level.INFO, "info is being called");
        return restTemplate.getForObject("http://localhost:8891/info",String.class);
    }

    @Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }
}
