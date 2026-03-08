package com.tc.springmicroservice.notification.service;

import com.tc.springmicroservice.clients.notification.NotificationRequest;
import com.tc.springmicroservice.notification.model.Notification;
import com.tc.springmicroservice.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest notificationRequest) {

        Notification notification = Notification.builder()
                .toCustomerId(notificationRequest.getToCustomerId())
                .toCustomerName(notificationRequest.getToCustomerName())
                .sender("ttc")
                .message(notificationRequest.getMessage())
                .sentAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }
}
