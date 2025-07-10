
package org.example.tegabus.schedule;

import org.example.tegabus.bus.Bus;
import org.example.tegabus.company.Company;
import org.example.tegabus.route.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    UUID id(UUID id);
    public interface BusRepository extends JpaRepository<Bus, UUID> {}
    public interface CompanyRepository extends JpaRepository<Company, UUID> {}
    public interface RouteRepository extends JpaRepository<Route, UUID> {}

}
