<<<<<<<< HEAD:src/main/java/org/example/tegabus/schedule/ScheduleRepository.java
package org.example.tegabus.schedule;
========
package org.example.tegabus.booking;
>>>>>>>> 6536998bd342cde0273072b72e348094b1d7d45f:src/main/java/org/example/tegabus/booking/BookingRepository.java

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    UUID id(UUID id);
}
