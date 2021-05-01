package org.jzz.spbootDemo;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.jzz.spbootDemo.controller.CROSController;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpbootDemoApplicationTests {

	@Test
	public void test() throws FileNotFoundException {
		System.out.println(System.getProperty("user.dir"));//项目的根路径
		System.out.println(CROSController.class.getResource("").getPath());//获取class文件的绝对路径（如果被打成jar包则不行了）
		System.out.println(org.junit.Test.class.getResource("/").getPath());//获取class文件所在pakage的绝对路径
		System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());//获取classes目录绝对路径（spring）
		System.out.println(ResourceUtils.getURL("classpath:").getPath());//获取classes目录绝对路径（spring）

	}

}
