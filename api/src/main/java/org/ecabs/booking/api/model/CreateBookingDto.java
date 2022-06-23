package org.ecabs.booking.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingDto {
    private String passengerName;
    private String passengerContactNumber;
    private Instant pickupTime;
    private Boolean asap;
    private Integer waitingTime;
    private Integer numberOfPassengers;
    private BigDecimal price;
    private Integer rating;
    private Instant createdOn;
    private List<CreateTripWaypointDto> tripWaypoints;
}
