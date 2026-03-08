package com.tc.springmicroservice.notification.notificationConfig;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMessageConverterConfig {

    @Bean(name = {"rabbitListenerContainerFactory", "simpleRabbitListenerContainerFactory"})
    @Primary
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(jackson2MessageConverter());
        return factory;
    }

    @Bean
    public MessageConverter jackson2MessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();

        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        // Trust only the packages you expect messages to come from (clients module and notification module)
        typeMapper.setTrustedPackages("com.tc.springmicroservice.clients.notification", "com.tc.springmicroservice.notification");
        // Prefer the __TypeId__ header for determining the target class
        typeMapper.setTypePrecedence(DefaultJackson2JavaTypeMapper.TypePrecedence.TYPE_ID);

        converter.setJavaTypeMapper(typeMapper);
        return converter;
    }
}
