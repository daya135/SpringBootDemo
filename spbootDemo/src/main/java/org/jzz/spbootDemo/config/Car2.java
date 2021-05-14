package org.jzz.spbootDemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


//@Component //不放在容器中，则使用@EnableConfigurationProperties开启下面的注解功能
@ConfigurationProperties(prefix = "mycar2") //只有在容器中的组件才能使用此注解，且beanName=小写类名
public class Car2 {

	private String brand;
	private Integer price;
	
	
	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Car2 [brand=" + brand + ", price=" + price + "]";
	}
	
	
}

