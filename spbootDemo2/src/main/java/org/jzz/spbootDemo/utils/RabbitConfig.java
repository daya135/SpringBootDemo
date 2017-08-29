package org.jzz.spbootDemo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public org.springframework.amqp.core.Queue Queue() {
        return new org.springframework.amqp.core.Queue("mailQueue");
    }

}