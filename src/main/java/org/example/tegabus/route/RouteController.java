package org.example.tegabus.route;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Route> createRoute(@RequestBody @Valid RouteDto dto){
        Route route = routeService.createRoute(dto);
        return new ResponseEntity<>(route, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all Routes")
    public ResponseEntity<List<Route>> getAllRoutes(){
        List<Route> routes = routeService.getAllRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Route By id")
    public ResponseEntity<Route> getRouteById(@PathVariable UUID id){
        Route route = routeService.getRouteById(id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update the Route by id")
    public ResponseEntity<Route> updateRoute(@PathVariable UUID id, @RequestBody @Valid RouteDto dto){
        Route route = routeService.updateRoute(id, dto);
    return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the Route by id")
    public ResponseEntity<Void> deleteRoute(@PathVariable UUID id){
        routeService.deleteRoute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
