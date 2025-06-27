package org.example.tegabus.booking;

import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final BusRepository busRepository;
    private final ScheduleRepository scheduleRepository;

    public Booking createBooking(BookingDto dto){
        Bus bus = busRepository.findById(dto.getBusId()).orElseThrow(() -> new RuntimeException("Bus not found"));
        Schedule schedule = scheduleRepository.findById(dto.getScheduleId()).orElseThrow(() -> new RuntimeException("Schedule not found"));
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = new Booking();
        booking.setPassengerName(dto.getPassengerName());
        booking.setPassengerEmail(dto.getPassengerEmail());
        booking.setPhoneNumber(dto.getPhoneNumber());
        booking.setAmountPaid(dto.getAmountPaid());
        booking.setTravelDate(dto.getTravelDate());
        booking.setCurrency(dto.getCurrency());
        booking.setQrCodeData(dto.getQrCodeData());
        booking.setTicketCode(dto.getTicketCode());
        booking.setBookingTime(dto.getBookingTime());

        booking.setBus(bus);
        booking.setSchedule(schedule);
        booking.setUser(user);
        return bookingRepository.save(booking);

    }
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }
    public Optional<Booking> getBookingById(UUID id){
        return bookingRepository.findById(id);
    }
    public Optional<Booking> updateBooking(UUID id, BookingDto dto){
        return bookingRepository.findById(id).map(existing -> {
            existing.setPassengerName(dto.getPassengerName());
            existing.setPassengerEmail(dto.getPassengerEmail());
            existing.setPhoneNumber(dto.getPhoneNumber());
            existing.setAmountPaid(dto.getAmountPaid());
            existing.setCurrency(dto.getCurrency());
            existing.setTravelDate(dto.getTravelDate());
            existing.setCurrency(dto.getCurrency());
            existing.setQrCodeData(dto.getQrCodeData());
            existing.setTicketCode(dto.getTicketCode());

            return bookingRepository.save(existing);

        });
    }
    public void deleteBooking(UUID id){
        bookingRepository.deleteById(id);
    }
}
