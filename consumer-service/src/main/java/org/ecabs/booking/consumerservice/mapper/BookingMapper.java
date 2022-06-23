package org.ecabs.booking.consumerservice.mapper;

import org.ecabs.booking.api.model.BookingDto;
import org.ecabs.booking.api.model.CreateBookingDto;
import org.ecabs.booking.consumerservice.config.MapStructCommonConfig;
import org.ecabs.booking.consumerservice.domain.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(config = MapStructCommonConfig.class,
        uses = TripWaypointMapper.class)
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastModifiedOn", ignore = true)
    Booking mapFromDto(CreateBookingDto bookingDto);

    Booking mapFromDto(BookingDto bookingDto);

    BookingDto mapToDto(Booking booking);

    default Page<BookingDto> mapToDto(Page<Booking> bookingsPage) {
        return bookingsPage.map(this::mapToDto);
    }

}
