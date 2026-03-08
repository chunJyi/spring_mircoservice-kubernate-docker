package com.tc.springmicroservice.notification.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Notification {

    @Id
    @SequenceGenerator(name = "notification_id_seq", sequenceName = "notification_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id_seq")
    private Integer notificationId;
    private Integer toCustomerId;
    private String toCustomerName;
    private String sender;
    private String message;
    private LocalDateTime sentAt;

}
