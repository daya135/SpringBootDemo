package org.jzz.spbootDemo;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.jzz.spbootDemo.rabbit.Send;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSender {
	@Autowired
	private Send sender;
	
	
	@Test
	public void test () throws InterruptedException{
		for (int i = 0; i < 20; i ++) {
			Thread.sleep(200);
			sender.sendMsg("这是一条测试消息" + i);
		}
	}
}
