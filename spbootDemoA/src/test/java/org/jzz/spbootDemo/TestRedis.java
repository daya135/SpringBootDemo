package org.jzz.spbootDemo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.jzz.spbootDemo.model.UserSpbt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;



/*
 * 1.5.2以后只需要用这两个注解则可进行单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Test
	public void test (){
		stringRedisTemplate.opsForValue().set("aaa", "111");
		org.junit.Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}
	
	
	@Test
	public void testObj() throws Exception {
		UserSpbt user = new UserSpbt();
		user.setAge(12);
		user.setBirth(new Date());
		user.setUserName("HNATEST");
		ValueOperations<String, UserSpbt> operations = redisTemplate.opsForValue();
		operations.set("com.neox", user);
        operations.set("com.neo.f", user, 1, TimeUnit.SECONDS); //超时时间
        Thread.sleep(1000);
        boolean exists=redisTemplate.hasKey("com.neox");
        boolean exists2=redisTemplate.hasKey("com.neo.f"); //超时后则不存在了
        System.out.println(exists);
        System.out.println(exists2);

	}
}
