package org.ecabs.booking.producerservice.controller;

import lombok.RequiredArgsConstructor;
import org.ecabs.booking.api.model.BookingDto;
import org.ecabs.booking.api.model.CreateBookingDto;
import org.ecabs.booking.producerservice.service.RabbitSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("${producer.async-queue-sender.prefix}/booking")
public class BookingController {
    private final RabbitSender rabbitSender;

    @PostMapping
    public ResponseEntity<Void> addBooking(@RequestBody CreateBookingDto bookingDto) {
        rabbitSender.add(bookingDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> editBooking(@RequestBody BookingDto bookingDto) {
        rabbitSender.edit(bookingDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        rabbitSender.delete(id);
        return ResponseEntity.ok().build();
    }
}
