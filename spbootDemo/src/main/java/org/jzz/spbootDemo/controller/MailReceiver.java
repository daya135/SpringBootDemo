package org.jzz.spbootDemo.controller;

import java.util.Random;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = "mailQueue")
public class MailReceiver {
	
	@RabbitHandler
	public void getAddress(String content) throws InterruptedException {
		int t = new Random().nextInt(1000)%(501) + 500; 
		Thread.sleep(t);
		System.out.println("Receiver1  : " + content);
	}
	
}
