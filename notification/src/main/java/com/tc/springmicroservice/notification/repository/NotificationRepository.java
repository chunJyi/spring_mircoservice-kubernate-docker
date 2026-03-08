package com.tc.springmicroservice.notification.repository;


import com.tc.springmicroservice.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
