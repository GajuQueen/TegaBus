package org.example.tegabus.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findBookingsByUserId(UUID userId);
    List<Booking> findBookingByScheduleId(UUID scheduleId);
    List<Booking> findBookingByTravelDate(LocalDate travelDate);
    List<Booking> findByBusId(UUID busId);
    List<Booking> findByScheduleTravelDate(LocalDate travelDate);
}
