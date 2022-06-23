package org.ecabs.booking.consumerservice.controller;

import lombok.RequiredArgsConstructor;
import org.ecabs.booking.api.model.BookingDto;
import org.ecabs.booking.api.model.CreateBookingDto;
import org.ecabs.booking.consumerservice.domain.model.Booking;
import org.ecabs.booking.consumerservice.mapper.BookingMapper;
import org.ecabs.booking.consumerservice.service.BookingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("${rest.api.prefix}/booking")
public class BookingController {
    private final BookingMapper bookingMapper;
    private final BookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(bookingMapper.mapToDto(bookingService.getById(id)));
    }

    @GetMapping()
    public ResponseEntity<Page<BookingDto>> findAll(@RequestParam Integer page,
                                                    @RequestParam Integer pageSize) {
        Page<Booking> searchResult = bookingService.findAll(PageRequest.of(page, pageSize));
        return ResponseEntity.ok(bookingMapper.mapToDto(searchResult));
    }

    @PostMapping
    public ResponseEntity<BookingDto> addBooking(@RequestBody CreateBookingDto bookingDto) {
        Booking saved = bookingService.save(bookingMapper.mapFromDto(bookingDto));
        return ResponseEntity.ok(bookingMapper.mapToDto(saved));
    }

    @PutMapping
    public ResponseEntity<BookingDto> editBooking(@RequestBody BookingDto bookingDto) {
        Booking saved = bookingService.update(bookingMapper.mapFromDto(bookingDto));
        return ResponseEntity.ok(bookingMapper.mapToDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
