package org.jzz.spbootDemo.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
//@RabbitListener(queues = "spring-boot-queue")
public class Recive {

    @RabbitHandler
    public void process(String hello) throws InterruptedException {
    	Thread.sleep(2000);
        System.out.println("Receiver  : " + hello);
    }
}
