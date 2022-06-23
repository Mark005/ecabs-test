package org.ecabs.booking.consumerservice.repository;

import org.ecabs.booking.consumerservice.domain.model.TripWaypoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TripWaypointRepository extends JpaRepository<TripWaypoint, UUID> {
}