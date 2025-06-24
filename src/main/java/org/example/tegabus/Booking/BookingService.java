package org.example.tegabus.Booking;

import lombok.RequiredArgsConstructor;
import org.example.tegabus.Bus.Bus;
import org.example.tegabus.Bus.BusRepository;
import org.example.tegabus.Schedule.ScheduleRepository;
import org.example.tegabus.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final BusRepository busRepository;
    private final ScheduleRepository scheduleRepository;



}
