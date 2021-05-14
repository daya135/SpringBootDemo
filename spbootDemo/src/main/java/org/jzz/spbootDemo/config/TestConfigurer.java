package org.jzz.spbootDemo.config;


import java.util.ArrayList;
import java.util.List;

import org.jzz.spbootDemo.model.Address;
import org.jzz.spbootDemo.model.Order;
import org.jzz.spbootDemo.model.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import ch.qos.logback.core.db.DBHelper;

@Import(DBHelper.class)	//@Import，给容器中导入组件，调用无参构造
@ImportResource("classpath:beans.xml")
@Configuration(proxyBeanMethods = false)	//proxyBeanMethods代理bean的方法，Lite、Full模式，体现为单例与非单例
@EnableConfigurationProperties(Car2.class)	//只有容器中的组件（@Configuration）才能搭配此注解,且Car2.class要开启配置绑定功能
public class TestConfigurer {
	@Bean
	public User user() {
		User user = new User();
		user.setId(99);
		user.setUserName("TestConfigurer User");
		user.setAge(18);
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address());
		user.setAddresss(addresses);
		
		return user;
	}
	
	@Bean
	public Address address() {
		Address address = new Address();
		address.setAddress("xx city xx street");
		address.setId(1);
		
		return address;
	}
	
	@ConditionalOnBean(name = "user")
	@Bean
	public Order order() {
		Order order = new Order();
		order.setUser(user());
		order.setAmount(100D);
		order.setOrderName("xxx order");
		return order;
	}
	
}
