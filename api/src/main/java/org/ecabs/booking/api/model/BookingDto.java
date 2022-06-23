package org.ecabs.booking.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private UUID id;
    private String passengerName;
    private String passengerContactNumber;
    private Instant pickupTime;
    private Boolean asap;
    private Integer waitingTime;
    private Integer numberOfPassengers;
    private BigDecimal price;
    private Integer rating;
    private Instant createdOn;
    private Instant lastModifiedOn;
    private List<TripWaypointDto> tripWaypoints;
}
