package com.tc.springmicroservice.notification.consumer;

import com.tc.springmicroservice.clients.notification.NotificationRequest;
import com.tc.springmicroservice.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}", containerFactory = "rabbitListenerContainerFactory")
    public void handleNotification(NotificationRequest notificationRequest) {
        log.info("Received message from queue: {}", notificationRequest);
        notificationService.sendNotification(notificationRequest);
    }
}
