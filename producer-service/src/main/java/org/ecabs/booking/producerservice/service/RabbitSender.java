package org.ecabs.booking.producerservice.service;

import org.ecabs.booking.api.model.BookingDto;
import org.ecabs.booking.api.model.CreateBookingDto;

import java.util.UUID;

public interface RabbitSender {
    void add(CreateBookingDto createBookingDto);

    void edit(BookingDto bookingDto);

    void delete(UUID uuid);
}
