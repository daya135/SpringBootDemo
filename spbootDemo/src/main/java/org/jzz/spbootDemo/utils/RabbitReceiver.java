package org.jzz.spbootDemo.utils;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "mailQueue")
public class RabbitReceiver {
	
    @Bean  
    public Queue fooQueue() {  
        return new Queue("mailQueue");  
    } 

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }

}