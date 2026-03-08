package com.tc.springmicroservice.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {

                "com.tc.springmicroservice.notification",
                "com.tc.springmicroservice.clients.notification",
                "com.tc.springmicroservice.amqp",
        }
)
@EnableFeignClients
@EnableRabbit
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class,args);
    }
}
