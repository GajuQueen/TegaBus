package org.example.tegabus.booking;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.example.tegabus.booking.Dtos.BookingDto;
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

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDto dto){
        Booking  savedBooking = bookingService.createBooking(dto);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable UUID id){
        Optional<Booking> booking = bookingService.getBookingById(id);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBooking(){
        var Bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(Bookings, HttpStatus.OK);
    }
    @Operation(
            summary = "update bookings by Id"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBookingById(@PathVariable UUID id, @RequestBody BookingDto dto){
        Optional<Booking> updated = bookingService.updateBooking(id, dto);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
