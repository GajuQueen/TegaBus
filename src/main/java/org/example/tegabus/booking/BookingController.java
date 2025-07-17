package org.example.tegabus.booking;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.example.tegabus.route.Route;

import org.example.tegabus.route.routeDtos.RouteResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/bookings")
@SecurityRequirement(name = "auth")
@RequiredArgsConstructor
public class BookingController{
    private final BookingService bookingService;

    private BookingResponseDto toResponseDto(Booking booking){
        Route route = booking.getSchedule().getRoute();
RouteResponseDto routeResponseDto =  RouteResponseDto.builder()
        .origin(route.getOrigin())
        .destination(route.getDestination())
        .price(route.getPrice())
        .durationInMinutes(route.getDurationInMinutes())
        .distanceInKm(route.getDistanceInKm())
        .build();
return BookingResponseDto.builder()
        .id(booking.getId())
        .passengerName(booking.getPassengerName())
        .travelDate(booking.getTravelDate())
        .seatNumber(booking.getSeatNumber())
        .route(routeResponseDto)
        .build();

    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    @Operation(summary = "create a new booking")
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingDto dto){
        BookingResponseDto responseDto = bookingService.createBooking(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Get all bookings")
    public ResponseEntity<BookingResponseDto> getBookingById(@PathVariable UUID id){
        return bookingService.getBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    @Operation(summary = "Get all bookings")
public ResponseEntity<List<BookingResponseDto>> getAllBookings(){
        List<BookingResponseDto> responseList = bookingService.getAllBookings();
    return ResponseEntity.ok(responseList);
}
@PutMapping("/{id}")
@Operation(summary = "Update booking by id")
public ResponseEntity<BookingResponseDto> updateBooking(@PathVariable UUID id, @RequestBody BookingDto dto){
        return bookingService.updateBooking(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
}
@DeleteMapping("/{id}")
@Operation(summary = "Delete booking by id")
public ResponseEntity<Void> deleteBooking(@PathVariable UUID id){
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
}
}