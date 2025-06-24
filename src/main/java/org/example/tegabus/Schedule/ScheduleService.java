package org.example.tegabus.Schedule;

import lombok.RequiredArgsConstructor;
import org.example.tegabus.bus.Bus;
import org.example.tegabus.bus.BusRepository;
import org.example.tegabus.company.Company;
import org.example.tegabus.company.CompanyRepository;
import org.example.tegabus.route.Route;
import org.example.tegabus.route.RouteRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;




@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final CompanyRepository companyRepository;

    public Schedule createSchedule(ScheduleDto dto) {
        Company company = companyRepository.findById(dto.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found"));
        Bus bus = busRepository.findById(dto.getBusId()).orElseThrow(() -> new RuntimeException("Bus not found"));
        Route route = routeRepository.findById(dto.getRouteId()).orElseThrow(() -> new RuntimeException("Route not found"));

        Schedule schedule = new Schedule();
        schedule.setDepartureTime(dto.getDepartureTime());
        schedule.setArrivalTime(dto.getArrivalTime());
        schedule.setTravelDate(dto.getTravelDate());
        schedule.setAvailableSeats(dto.getAvailableSeats());
        schedule.setActive(dto.isActive());
        schedule.setCompany(company);
        schedule.setBus(bus);
        schedule.setRoute(route);
        schedule.setStatus(schedule.getStatus());

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getSchedules(){
        return scheduleRepository.findAll();
    }
    public Optional<Schedule> getScheduleById(UUID id){
        return scheduleRepository.findById(id);
    }
    public void deleteScheduleById(UUID id){
        scheduleRepository.deleteById(id);
    }
    public Optional<Schedule> updateScheduleStatus(UUID scheduleId, ScheduleStatus status) {
        return scheduleRepository.findById(scheduleId).map(schedule -> {
            schedule.setStatus(schedule.getStatus());
            return scheduleRepository.save(schedule);
        });
    }

        public List<Schedule> getScheduleByTravelDate(LocalDate travelDate){
            return scheduleRepository.findByTravelDate(travelDate);
        }

        public List<Schedule> getScheduleByTravelDateAndDepartureTime(LocalDate travelDate, LocalDate departureTime){
        return scheduleRepository.findByTravelDateAndDepartureTime(travelDate, departureTime);
        }
        public List<Schedule> getScheduleByBusId(UUID busId){
           return scheduleRepository.findByBusId(busId);
        }

        public List<Schedule> getScheduleByRouteId(UUID routeId){
             return scheduleRepository.findByRouteId(routeId);
        }

        public List<Schedule> getScheduleByStatus(ScheduleStatus status){
           return scheduleRepository.findByStatus(status);
        }
         public Optional<Schedule> updateSchedule(UUID scheduleId, ScheduleDto dto){
        return scheduleRepository.findById(scheduleId).map(schedule -> {
            schedule.setDepartureTime(dto.getDepartureTime());
            schedule.setArrivalTime(dto.getArrivalTime());
            schedule.setTravelDate(dto.getTravelDate());
            schedule.setAvailableSeats(dto.getAvailableSeats());
            schedule.setActive(dto.isActive());
            return scheduleRepository.save(schedule);
        });
    }
}
