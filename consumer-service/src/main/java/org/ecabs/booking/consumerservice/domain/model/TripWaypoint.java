package org.ecabs.booking.consumerservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trip_waypoint", indexes = {
        @Index(name = "idx_tripwaypoint_id", columnList = "id")
})
public class TripWaypoint {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;
    private String locality;
    private Double latitude;
    private Double longitude;

    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

}
