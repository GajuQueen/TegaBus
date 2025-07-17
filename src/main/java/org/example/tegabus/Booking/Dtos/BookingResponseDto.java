package org.example.tegabus.booking.Dtos;

import lombok.Builder;
import lombok.Data;
import org.example.tegabus.route.routeDtos.RouteResponseDto;

import java.time.LocalDate;
import java.util.UUID;
@Data
@Builder
public class BookingResponseDto {
    private UUID id;
    private String passengerName;
    private RouteResponseDto route;
    private LocalDate travelDate;
    private String seatNumber;
}
