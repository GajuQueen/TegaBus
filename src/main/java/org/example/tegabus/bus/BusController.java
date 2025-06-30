package org.example.tegabus.bus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bus")
@SecurityRequirement(name = "auth")
@RequiredArgsConstructor
public class BusController {
    private final BusService busService;

    @PostMapping
    @Operation(summary = "Create a Bus")
    public ResponseEntity<Bus> createBus(@RequestBody @Valid BusDto dto){
     Bus bus = busService.createBus(dto);
        return new ResponseEntity<>(bus, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all Buses")
    public ResponseEntity<List<Bus>> findAll() {
        List<Bus> buses = busService.findAll();
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    @GetMapping("/id")
    @Operation(summary = "Find Bus by id")
    public ResponseEntity<Bus> findBusById(@PathVariable UUID id){
        Bus bus = busService.findBusById(id);
        return new ResponseEntity<>(bus, HttpStatus.OK);
    }

    @PatchMapping("/id")
    @Operation(summary = "Update a Bus")
    public ResponseEntity<Bus> updateBus(@PathVariable UUID id, @RequestBody @Valid BusDto dto){
        Bus bus = busService.updateBus(id, dto);
        return new ResponseEntity<>(bus, HttpStatus.OK);
    }

    @DeleteMapping
    @Operation(summary = "Delete Bus by id")
    public ResponseEntity<Void> deleteById(UUID id){
    busService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
