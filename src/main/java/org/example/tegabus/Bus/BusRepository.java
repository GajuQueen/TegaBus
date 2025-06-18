package org.example.tegabus.Bus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusRepository extends JpaRepository<BusModel, UUID> {
}
