package org.ecabs.booking.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripWaypointDto {
    private UUID id;
    private String locality;
    private Double latitude;
    private Double longitude;
}
