package org.example.tegabus.bus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusRepository extends JpaRepository<Bus, UUID> {
}
