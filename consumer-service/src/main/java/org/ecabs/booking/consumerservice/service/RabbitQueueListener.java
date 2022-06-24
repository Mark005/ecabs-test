package org.ecabs.booking.consumerservice.service;

import lombok.RequiredArgsConstructor;
import org.ecabs.booking.api.model.BookingDto;
import org.ecabs.booking.api.model.CreateBookingDto;
import org.ecabs.booking.consumerservice.mapper.BookingMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RabbitQueueListener {
    private final AuditService auditService;
    private final BookingService bookingService;
    private final BookingMapper mapper;

    @RabbitListener(queues = "${rabbit.message-audit-queue}")
    public void receiveAudit(Message message) {
        auditService.auditMessage(message);
    }

    @RabbitListener(queues = "${rabbit.add-queue}")
    public void receiveAdd(CreateBookingDto createBookingDto) {
        bookingService.save(mapper.mapFromDto(createBookingDto));
    }

    @RabbitListener(queues = "${rabbit.edit-queue}")
    public void receiveUpdate(BookingDto bookingDto) {
        bookingService.update(mapper.mapFromDto(bookingDto));
    }

    @RabbitListener(queues = "${rabbit.delete-queue}")
    public void receiveDelete(UUID uuid) {
        bookingService.delete(uuid);
    }
}
