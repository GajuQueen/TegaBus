package org.example.tegabus.booking;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.example.tegabus.booking.Dtos.BookingDto;
import org.example.tegabus.booking.Dtos.BookingResponseDto;
import org.example.tegabus.route.RouteConverter;
import org.example.tegabus.route.routeDtos.RouteResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/bookings")
@SecurityRequirement(name = "auth")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService  bookingService;
    private final RouteConverter routeConverter;

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingDto dto){
        Booking  booking = bookingService.createBooking(dto);
RouteResponseDto routeDto = routeConverter.toResponseDto(booking.getRoute());

        BookingResponseDto responseDto = BookingResponseDto.builder()
                .id(booking.getId())
                .passengerName(booking.getPassengerName())
                .route(routeDto)
                .travelDate(booking.getTravelDate())
                .seatNumber(booking.getSeatNumber())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getBookingById(@PathVariable UUID id){
        Optional<Booking> optionalBooking = bookingService.getBookingById(id);
        if(optionalBooking.isPresent()){
            Booking booking = optionalBooking.get();
            BookingResponseDto responseDto = BookingResponseDto.builder()
                    .id(booking.getId())
                    .passengerName(booking.getPassengerName())
                    .route(routeConverter.toResponseDto(booking.getRoute()))
                    .travelDate(booking.getTravelDate())
                    .seatNumber(booking.getSeatNumber())
                    .build();
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getAllBooking(){
        List<Booking> bookings = bookingService.getAllBookings();
        List<BookingResponseDto> bookingResponseDtos = bookings.stream()
                .map(booking ->BookingResponseDto.builder()
                        .id(booking.getId())
                .passengerName(booking.getPassengerName())
                .route(routeConverter.toResponseDto(booking.getRoute()) )
                .travelDate(booking.getTravelDate())
                .seatNumber(booking.getSeatNumber())
                .build())
                .toList();
        return new ResponseEntity<>(bookingResponseDtos, HttpStatus.OK);
    }
    @Operation(
            summary = "update bookings by Id"
    )
    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDto> updateBookingById(@PathVariable UUID id, @RequestBody BookingDto dto){
        Optional<Booking> updatedBooking = bookingService.updateBooking(id, dto);
        return updatedBooking.map(booking -> ResponseEntity.ok(
                BookingResponseDto.builder()
                        .id(booking.getId())
                        .passengerName(booking.getPassengerName())
                        .route(routeConverter.toResponseDto(booking.getRoute()))
                        .travelDate(booking.getTravelDate())
                        .seatNumber(booking.getSeatNumber())
                        .build()
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Delete booking by Id"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookingById(@PathVariable UUID id){
        bookingService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}