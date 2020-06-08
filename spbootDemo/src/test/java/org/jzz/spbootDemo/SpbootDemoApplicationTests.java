package org.jzz.spbootDemo;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.jzz.spbootDemo.controller.CROSController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpbootDemoApplicationTests {
	Logger log = LoggerFactory.getLogger(SpbootDemoApplicationTests.class);
	
	@Autowired
	private ApplicationContext applicationContext;	//这个也可以被注入
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired 
	private RequestMappingHandlerMapping handlerMapping;

//	@Test
	public void test() throws FileNotFoundException {
		System.out.println(System.getProperty("user.dir"));//项目的根路径
		System.out.println(CROSController.class.getResource("").getPath());//获取class文件的绝对路径（如果被打成jar包则不行了）
		System.out.println(org.junit.Test.class.getResource("/").getPath());//获取class文件所在pakage的绝对路径
		System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());//获取classes目录绝对路径（spring）
		System.out.println(ResourceUtils.getURL("classpath:").getPath());//获取classes目录绝对路径（spring）

	}
	
	@Test
	public void testAutowired() {
		log.info(servletContext.getClass().getName());	//org.springframework.boot.test.mock.web.SpringBootMockServletContext
		log.info(applicationContext.getClass().getName());	//org.springframework.web.context.support.GenericWebApplicationContext
	}
	
	@Test
	public void testRequestMappingHandlerMapping() {
//		RequestMappingHandlerMapping handlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);	//可以被注入
		Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = handlerMapping.getHandlerMethods();	//常用来权限管理，如每个url映射方法加上自定注解，在此处筛选注解，添加到过滤器链
		for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethodMap.entrySet()) {
			HandlerMethod method = entry.getValue();
			RequestMappingInfo info = entry.getKey();
			Set<String> urlSet = info.getPatternsCondition().getPatterns();
			log.info("======================[{}]==============", info.getName());
			for (String url: urlSet) {
				log.info(url);
			}
		}
	}

}
