package org.example.tegabus.booking;

import lombok.RequiredArgsConstructor;
import org.example.tegabus.route.Route;
import org.example.tegabus.route.routeDtos.RouteResponseDto;
import org.example.tegabus.schedule.Schedule;
import org.example.tegabus.schedule.ScheduleRepository;
import org.example.tegabus.bus.Bus;
import org.example.tegabus.bus.BusRepository;
import org.example.tegabus.user.User;
import org.example.tegabus.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final BusRepository busRepository;
    private final ScheduleRepository scheduleRepository;
private BookingResponseDto toResponseDto(Booking booking){
    Schedule schedule = booking.getSchedule();
    Route route = schedule.getRoute();
    RouteResponseDto routeDto = new RouteResponseDto(
            route.getOrigin(),
            route.getDestination(),
            route.getPrice(),
            route.getDurationInMinutes(),
            route.getDistanceInKm()
    );
    return BookingResponseDto.builder()
            .id(booking.getId())
            .passengerName(booking.getPassengerName())
            .travelDate(booking.getTravelDate())
            .seatNumber(booking.getSeatNumber())
            .route(routeDto)
            .build();

}
    public BookingResponseDto createBooking(BookingDto dto){
        Bus bus = busRepository.findById(dto.getBusId()).orElseThrow(() -> new RuntimeException("Bus not found"));
        Schedule schedule = scheduleRepository.findById(dto.getScheduleId()).orElseThrow(() -> new RuntimeException("Schedule not found"));
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = new Booking();
        booking.setPassengerName(dto.getPassengerName());
        booking.setPassengerEmail(dto.getPassengerEmail());
        booking.setPassengerPhone(dto.getPassengerPhone());
        booking.setAmountPaid(dto.getAmountPaid());
        booking.setTravelDate(dto.getTravelDate());
        booking.setCurrency(dto.getCurrency());
        booking.setQrCodeData(dto.getQrCodeData());
        booking.setTicketCode(dto.getTicketCode());
        booking.setBookingTime(dto.getBookingTime());

        booking.setBus(bus);
        booking.setSchedule(schedule);
        booking.setUser(user);
        Booking saved = bookingRepository.save(booking);
        return toResponseDto(saved);

    }
    public List<BookingResponseDto> getAllBookings(){
    return bookingRepository.findAll()
            .stream()
            .map(this::toResponseDto)
            .collect(Collectors.toList());
    }
    public Optional<BookingResponseDto> getBookingById(UUID id){
    return bookingRepository.findById(id)
            .map(this::toResponseDto);
    }
    public Optional<BookingResponseDto> updateBooking(UUID id, BookingDto dto){
        return bookingRepository.findById(id).map(existing -> {
            existing.setPassengerName(dto.getPassengerName());
            existing.setPassengerEmail(dto.getPassengerEmail());
            existing.setPassengerPhone(dto.getPassengerPhone());
            existing.setAmountPaid(dto.getAmountPaid());
            existing.setCurrency(dto.getCurrency());
            existing.setTravelDate(dto.getTravelDate());
            existing.setCurrency(dto.getCurrency());
            existing.setQrCodeData(dto.getQrCodeData());
            existing.setTicketCode(dto.getTicketCode());
            Booking updated = bookingRepository.save(existing);

            return toResponseDto(updated);

        });
    }
    public void deleteBooking(UUID id){
    bookingRepository.deleteById(id);
    }
}
