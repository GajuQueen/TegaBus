package org.example.tegabus.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    List<Schedule> findByTravelDate(LocalDate travelDate);
    List<Schedule> findByTravelDateAndDepartureTime(LocalDate travelDate, LocalDate departureDate);
    List<Schedule> findByBusId(UUID busId);
    List<Schedule> findByRouteId(UUID routeId);
    List<Schedule> findByStatus(ScheduleStatus status);

    UUID id(UUID id);
}
