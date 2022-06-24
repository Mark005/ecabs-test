package org.ecabs.booking.consumerservice.service;

import org.ecabs.booking.consumerservice.domain.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookingService {
    Booking getById(UUID uuid);

    Page<Booking> findAll(Pageable pageable);

    Booking save(Booking booking);

    void delete(UUID uuid);
}
