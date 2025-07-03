package org.example.tegabus.route;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tegabus.route.RouteDtos.RouteDto;
import org.example.tegabus.route.RouteDtos.RouteResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @PostMapping
    @Operation(summary = "Create a Route")
    public ResponseEntity<RouteResponseDto> createRoute(@RequestBody @Valid RouteDto dto){
        Route route = routeService.createRoute(dto);
        RouteResponseDto responseDto = RouteResponseDto.builder()
                .id(route.getId())
                .origin(route.getOrigin())
                .destination(route.getDestination())
                .distanceInKm(route.getDistanceInKm())
                .durationInMinutes(route.getDurationInMinutes())
                .price(route.getPrice())
                .build();

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all Routes")
    public ResponseEntity<List<RouteResponseDto>> getAllRoutes(){
        List<Route> routes = routeService.getAllRoutes();
        List<RouteResponseDto> responseDto = routes.stream()
                .map(route -> RouteResponseDto.builder()
                .id(route.getId())
                .origin(route.getOrigin())
                .destination(route.getDestination())
                .distanceInKm(route.getDistanceInKm())
                .durationInMinutes(route.getDurationInMinutes())
                .price(route.getPrice())
                .build())
                        .toList();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Route By id")
    public ResponseEntity<RouteResponseDto> getRouteById(@PathVariable UUID id){
        Route route = routeService.getRouteById(id);
        RouteResponseDto responseDto = RouteResponseDto.builder()
                .id(route.getId())
                .origin(route.getOrigin())
                .destination(route.getDestination())
                .distanceInKm(route.getDistanceInKm())
                .durationInMinutes(route.getDurationInMinutes())
                .price(route.getPrice())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update the Route by id")
    public ResponseEntity<RouteResponseDto> updateRoute(@PathVariable UUID id, @RequestBody @Valid RouteDto dto){
        Route route = routeService.updateRoute(id, dto);
        RouteResponseDto responseDto = RouteResponseDto.builder()
                .id(route.getId())
                .origin(route.getOrigin())
                .destination(route.getDestination())
                .distanceInKm(route.getDistanceInKm())
                .durationInMinutes(route.getDurationInMinutes())
                .price(route.getPrice())
                .build();
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the Route by id")
    public ResponseEntity<Void> deleteRoute(@PathVariable UUID id){
        routeService.deleteRoute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
