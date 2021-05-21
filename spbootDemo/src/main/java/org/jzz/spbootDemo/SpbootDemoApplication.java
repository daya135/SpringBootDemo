package org.jzz.spbootDemo;

import org.jzz.spbootDemo.config.Car;
import org.jzz.spbootDemo.config.Car2;
import org.jzz.spbootDemo.model.Address;
import org.jzz.spbootDemo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import ch.qos.logback.core.db.DBHelper;

//@EnableScheduling /* 定时任务 */
@EnableCaching // 使用缓存,需要实体实现Serializable接口，在方法上开启@Cacheable

//@SpringBootApplication //此注解相当于以下注解三合一
@SpringBootConfiguration // 其实就是一个Configuration,表明这个类也是个配置类
@ComponentScan // 默认用当前类所在的包作为根目录扫描，也可以指定其他
@EnableAutoConfiguration //
public class SpbootDemoApplication {
	static Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);

	/*
	 * Spring Boot建议将我们main方法所在的这个主要的配置类配置在根包名下。
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpbootDemoApplication.class, args);
//		testConfigurer(context);
	}

	public static void testConfigurer(ConfigurableApplicationContext context) {
		logger.info("*************测试SpringBoot容器*************************************************");
		User user1 = context.getBean(User.class);
		User user2 = context.getBean(User.class);
		Address address = context.getBean(Address.class);
		logger.info(String.valueOf(user1 == user2));
		logger.info(String.valueOf(user1.getAddresss().get(0) == address)); // 测试组建依赖

		logger.info("@Bean注册的组件: " + user1.toString());
		logger.info("@Import直接从外部导入的组件: " + context.getBean(DBHelper.class).toString()); // 测试导入组件
		logger.info("@ConditionalOnBean注册的组件: " + context.getBean("order"));
		logger.info("@ImportResource注册的xml组件: " + context.getBean("xmlPet"));
		logger.info("@EnableConfigurationProperties注册的组件：" + context.getBeanNamesForType(Car.class)[0]);
		logger.info("@ConfigurationProperties注册的组件：" + context.getBean("car"));
		logger.info("@EnableConfigurationProperties注册的组件：" + context.getBeanNamesForType(Car2.class)[0]);
		logger.info("@EnableConfigurationProperties注册的组件：" + context.getBean(Car2.class)); // 此处用car2作为名称获取不到。。。
	}
}
