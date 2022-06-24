package org.ecabs.booking.consumerservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecabs.booking.consumerservice.domain.model.Booking;
import org.ecabs.booking.consumerservice.repository.BookingRepository;
import org.ecabs.booking.consumerservice.repository.TripWaypointRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final TripWaypointRepository waypointRepository;
    private final BookingRepository bookingRepository;

    @Override
    public Booking getById(UUID uuid) {
        return bookingRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Booking with id=%s, not found".formatted(uuid)));
    }

    public Page<Booking> findAll(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    @Override
    public Booking save(Booking booking) {
        Booking saved = bookingRepository.save(booking);

        if (booking.getTripWaypoints() != null) {
            booking.getTripWaypoints().forEach(waypoint -> {
                waypoint.setBooking(booking);
                waypointRepository.save(waypoint);
            });
        }
        return saved;
    }

    @Override
    public Booking update(Booking booking) {
        if (!bookingRepository.existsById(booking.getId())) {
            throw new IllegalArgumentException(
                    "Update error: \"Booking\" with id=%s}, not found".formatted(booking.getId().toString()));
        }

        bookingRepository.save(booking);
        if (booking.getTripWaypoints() != null) {
            booking.getTripWaypoints().forEach(waypoint -> {
                waypoint.setBooking(booking);
                waypointRepository.save(waypoint);
            });
        }
        return bookingRepository.save(booking);

    }

    @Override
    public void delete(UUID uuid) {
        Booking booking = bookingRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Booking with id=%s, not found".formatted(uuid)));
        bookingRepository.delete(booking);
    }
}
