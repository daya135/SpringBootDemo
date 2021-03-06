package org.jzz.spbootDemo.config;

import javax.servlet.Filter;

import org.jzz.spbootDemo.filter.CROSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


//@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {
    
	
	//因为是重写了WebMvcConfigurationSupport，所以需要再配置一次，实际默认static就是静态路径映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
    	//默认配置是：/** 映射为 /static/ ，即访问时url少了“static/”
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    
	@Bean
	public FilterRegistrationBean<Filter> registCROSFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
        registration.setFilter(new CROSFilter());
        registration.addUrlPatterns("/cros/*");		//大坑，不支持/**这样的pattern！！浪费半天时间
        registration.setName("CROSFilter");
//        registration.addInitParameter("paramName", "paramValue");
        registration.setOrder(1);
        registration.setEnabled(true);
        return registration;
    }
    
}
