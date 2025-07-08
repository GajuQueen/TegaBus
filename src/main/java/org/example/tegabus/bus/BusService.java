package org.example.tegabus.bus;

import lombok.RequiredArgsConstructor;
//import org.example.tegabus.exception.ResourceNotFoundException;
import org.example.tegabus.bus.Dtos.BusDto;
import org.example.tegabus.exception.ResourceNotFoundException;
import org.example.tegabus.route.Route;
import org.example.tegabus.route.RouteDtos.RouteResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusService {
    public final BusRepository busRepository;

    private void applyDtoToBus(BusDto dto, Bus bus) {
        bus.setPlateNumber(dto.getPlateNumber());
        bus.setModel(dto.getModel());
        bus.setStatus(dto.getStatus());
        bus.setType(dto.getType());
        bus.setDriverName(dto.getDriverName());
        bus.setDriverPhoneNumber(dto.getDriverPhoneNumber());


    }
    public Bus createBus(BusDto dto) {
        Bus bus = new Bus();
        applyDtoToBus(dto, bus);
        return busRepository.save(bus);
    }
    
    public List<Bus> findAll(){
        return busRepository.findAll();
    }

    public Bus findBusById(UUID id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));
    }

    public Bus updateBus(UUID id, BusDto dto) {

        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));
                  applyDtoToBus(dto, bus);
        return busRepository.save(bus);

    }
    public BusResponseDto toResponseDto(Bus bus){
        Route route = bus.getRoute();
        RouteResponseDto routeResponseDto = new RouteResponseDto(
                route.getOrigin(),
                route.getDestination(),
                route.getPrice(),
                route.getDurationInMinutes(),
                route.getDistanceInKm()
        );
        return BusResponseDto.builder()
                .id(bus.getId())
                .plateNumber(bus.getPlateNumber())
                .status(bus.getStatus())
                .route(routeResponseDto)
                .driverName(bus.getDriverName())
                .build();
    }

    public void deleteById(UUID id) {
        busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));
        busRepository.deleteById(id);
    }

}
