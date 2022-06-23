package org.ecabs.booking.consumerservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.ecabs.booking.consumerservice.config.properties.RabbitProperties;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(RabbitProperties.class)
public class RabbitConfig {
    private final RabbitProperties properties;

    @Bean
    public MessageConverter converter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public FanoutExchange messageExchange() {
        return new FanoutExchange(properties.getMessageExchangeName());
    }

    @Bean
    public TopicExchange bookingExchange() {
        return new TopicExchange(properties.getBookingExchangeName());
    }

    @Bean
    public Queue auditQueue() {
        return new Queue(properties.getMessageAuditQueue());
    }

    @Bean
    public Queue addQueue() {
        return new Queue(properties.getAddQueue());
    }

    @Bean
    public Queue editQueue() {
        return new Queue(properties.getEditQueue());
    }

    @Bean
    public Queue deleteQueue() {
        return new Queue(properties.getDeleteQueue());
    }

    @Bean
    Binding bookingExchangeBinding() {
        return BindingBuilder.bind(bookingExchange())
                .to(messageExchange());
    }

    @Bean
    Binding auditQueueBinding() {
        return BindingBuilder.bind(auditQueue())
                .to(messageExchange());
    }

    @Bean
    Binding addQueueBinding() {
        return BindingBuilder.bind(addQueue())
                .to(bookingExchange())
                .with(properties.getAddMessageRoutingKey());
    }

    @Bean
    Binding editQueueBinding() {
        return BindingBuilder.bind(editQueue())
                .to(bookingExchange())
                .with(properties.getEditMessageRoutingKey());
    }

    @Bean
    Binding deleteQueueBinding() {
        return BindingBuilder.bind(deleteQueue())
                .to(bookingExchange())
                .with(properties.getDeleteMessageRoutingKey());
    }
}
