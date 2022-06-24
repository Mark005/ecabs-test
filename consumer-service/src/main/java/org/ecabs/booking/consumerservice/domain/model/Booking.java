package org.ecabs.booking.consumerservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking", indexes = {
        @Index(name = "idx_booking_id", columnList = "id")
})
public class Booking {

    @Id
    @GeneratedValue
    @Column(nullable = false)
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

    @OneToMany(mappedBy = "booking",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TripWaypoint> tripWaypoints = new ArrayList<>();

}
