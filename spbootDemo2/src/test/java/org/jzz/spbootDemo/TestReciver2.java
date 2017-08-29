package org.jzz.spbootDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.jzz.spbootDemo.rabbit.Recive;
import org.jzz.spbootDemo.rabbit.Send;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestReciver2 {
	
	@Autowired
	private Recive recive;
	
	
	@Test
	public void test (){
		
	}
}
