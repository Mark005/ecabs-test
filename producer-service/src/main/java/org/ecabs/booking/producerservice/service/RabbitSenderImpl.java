package org.ecabs.booking.producerservice.service;

import lombok.RequiredArgsConstructor;
import org.ecabs.booking.api.model.BookingDto;
import org.ecabs.booking.api.model.CreateBookingDto;
import org.ecabs.booking.producerservice.config.properties.RabbitProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RabbitSenderImpl implements RabbitSender {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties properties;

    @Override
    public void add(CreateBookingDto createBookingDto) {
        rabbitTemplate.convertAndSend(properties.getMessageExchangeName(),
                properties.getAddMessageRoutingKey(),
                createBookingDto);
    }

    @Override
    public void edit(BookingDto bookingDto) {
        rabbitTemplate.convertAndSend(properties.getMessageExchangeName(),
                properties.getEditMessageRoutingKey(),
                bookingDto);
    }

    @Override
    public void delete(UUID uuid) {
        rabbitTemplate.convertAndSend(properties.getMessageExchangeName(),
                properties.getDeleteMessageRoutingKey(),
                uuid);
    }
}
