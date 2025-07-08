
package org.example.tegabus.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    UUID id(UUID id);
}
