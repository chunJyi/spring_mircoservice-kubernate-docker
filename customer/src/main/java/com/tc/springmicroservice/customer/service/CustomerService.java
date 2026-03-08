package com.tc.springmicroservice.customer.service;

import com.tc.springmicroservice.amqp.RabbitMQMessageProducer;
import com.tc.springmicroservice.clients.fraud.FraudCheckResponse;
import com.tc.springmicroservice.clients.fraud.FraudClient;
import com.tc.springmicroservice.clients.notification.NotificationRequest;
import com.tc.springmicroservice.customer.model.Customer;
import com.tc.springmicroservice.customer.model.CustomerRegistrationRequest;
import com.tc.springmicroservice.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        // Use customer's name for the notification 'toCustomerName' field
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                String.format("%s %s", customer.getFirstName(), customer.getLastName()),
                String.format("Hi %s, welcome to Amigoscode...",
                        customer.getFirstName())
        );

        try {
            log.info("Publishing notification for customer id {} to exchange '{}' with routingKey '{}" ,
                    customer.getId(), "internal.exchange", "internal.notification.routing-key");
            rabbitMQMessageProducer.publish(
                    notificationRequest,
                    "internal.exchange",
                    "internal.notification.routing-key"
            );
            log.info("Published notification for customer id {}", customer.getId());
        } catch (Exception e) {
            log.error("Failed to publish notification for customer id {}", customer.getId(), e);
        }
    }
}
