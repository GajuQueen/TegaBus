package org.example.tegabus.route;

import lombok.RequiredArgsConstructor;
import org.example.tegabus.route.routeDtos.RouteDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RouteService {
    public final RouteRepository routeRepository;

    public Route createRoute(RouteDto dto) {
        Route route = new Route();
        route.setOrigin(dto.getOrigin());
        route.setDestination(dto.getDestination());
        route.setDurationInMinutes(dto.getDurationInMinutes());
        route.setDistanceInKm(dto.getDistanceInKm());
        route.setPrice(dto.getPrice());
        return routeRepository.save(route);
    }

    public List<Route> getAllRoutes() {

        return routeRepository.findAll();
    }

    public Route getRouteById(UUID id) {
        return routeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No route found with id: " + id));
    }

    public Route updateRoute(UUID id, RouteDto dto) {
        Route route = routeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No route found with id: " + id));
        route.setOrigin(dto.getOrigin());
        route.setDestination(dto.getDestination());
        route.setDurationInMinutes(dto.getDurationInMinutes());
        route.setDistanceInKm(dto.getDistanceInKm());
        route.setPrice(dto.getPrice());
        return routeRepository.save(route);
    }

    public void deleteRoute(UUID id) {
        routeRepository.deleteById(id);
    }
    }
