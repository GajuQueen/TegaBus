package org.example.tegabus.schedule;

import lombok.RequiredArgsConstructor;
import org.example.tegabus.bus.Bus;
import org.example.tegabus.bus.BusRepository;
import org.example.tegabus.company.Company;
import org.example.tegabus.company.CompanyRepository;
import org.example.tegabus.route.Route;
import org.example.tegabus.route.routeDtos.RouteResponseDto;
import org.example.tegabus.route.RouteRepository;
import org.example.tegabus.schedule.ScheduleDtos.ScheduleDto;
import org.example.tegabus.schedule.ScheduleDtos.ScheduleResponseDto;
import org.springframework.stereotype.Service;
import java.util.UUID;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final CompanyRepository companyRepository;

    public Schedule createSchedule(ScheduleDto dto) {
        System.out.println("Start service");
        Company company = companyRepository.findById(dto.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found"));
        System.out.println("Company nop");
        Bus bus = busRepository.findById(dto.getBusId()).orElseThrow(() -> new RuntimeException("Bus not found"));
        System.out.println("Bus nop");
        Route route = routeRepository.findById(dto.getRouteId()).orElseThrow(() -> new RuntimeException("Route not found"));

        System.out.println("End checking");
        Schedule schedule = new Schedule();
        schedule.setDepartureTime(dto.getDepartureTime());
        schedule.setArrivalTime(dto.getArrivalTime());
        schedule.setTravelDate(dto.getTravelDate());
        schedule.setAvailableSeats(dto.getAvailableSeats());
        schedule.setActive(dto.isActive());
        schedule.setCompany(company);
        schedule.setBus(bus);
        schedule.setRoute(route);
        schedule.setStatus(dto.getStatus());

        return scheduleRepository.save(schedule);
    }
    public ScheduleResponseDto toResponseDto(Schedule schedule){
        Bus bus = schedule.getBus();
        Route route = schedule.getRoute();

        RouteResponseDto routeResponseDto =  RouteResponseDto.builder()
                        .id(route.getId())
                .origin( route.getOrigin())
                .destination(route.getDestination())
                .price(route.getPrice())
                .durationInMinutes( route.getDurationInMinutes())
                .distanceInKm(route.getDistanceInKm())
                .build();

        return ScheduleResponseDto.builder()
                .plateNumber(bus.getPlateNumber())
                .route(routeResponseDto)
                .departureTime(schedule.getDepartureTime())
                .arrivalTime(schedule.getArrivalTime())
                .driverName(bus.getDriverName())
                .status(schedule.getStatus())
                .build();
    }

    public List<ScheduleResponseDto> getAllScheduleDtos(){
        return scheduleRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<ScheduleResponseDto> getScheduleById(UUID id){
        return scheduleRepository.findById(id)
                .map(this::toResponseDto);
    }
    public void deleteScheduleById(UUID id){
        scheduleRepository.deleteById(id);
    }
    public Optional<Schedule> updateSchedule(UUID scheduleId,ScheduleDto dto){
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




