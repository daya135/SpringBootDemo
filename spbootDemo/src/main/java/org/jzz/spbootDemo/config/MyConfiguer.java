package org.jzz.spbootDemo.config;

import javax.servlet.Filter;

import org.jzz.spbootDemo.filter.CROSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MyConfiguer {
//	@Bean
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
