package org.ecabs.booking.consumerservice.mapper;

import org.ecabs.booking.api.model.CreateTripWaypointDto;
import org.ecabs.booking.api.model.TripWaypointDto;
import org.ecabs.booking.consumerservice.config.MapStructCommonConfig;
import org.ecabs.booking.consumerservice.domain.model.TripWaypoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructCommonConfig.class)
public interface TripWaypointMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "booking", ignore = true)
    TripWaypoint mapFromDto(CreateTripWaypointDto waypointDto);

    @Mapping(target = "booking", ignore = true)
    TripWaypoint mapFromDto(TripWaypointDto waypointDto);

    TripWaypointDto mapToDto(TripWaypoint waypoint);
}
