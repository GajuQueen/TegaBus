package org.example.tegabus.bus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tegabus.bus.dtos.BusDto;
import org.example.tegabus.bus.dtos.BusResponseDto;
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
    public ResponseEntity<BusResponseDto> createBus(@RequestBody @Valid BusDto dto) {
        Bus bus = busService.createBus(dto);
        BusResponseDto responseDto = BusResponseDto.builder()
                .id(bus.getId())
                .status(bus.getStatus())
                .driverName(bus.getDriverName())
                .plateNumber(bus.getPlateNumber())
//                .route(bus.getRoute())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all Buses")
    public ResponseEntity<List<BusResponseDto>> findAll() {
        List<Bus> buses = busService.findAll();
        List<BusResponseDto> responseDto = buses.stream()
                .map(bus -> BusResponseDto.builder()
                        .id(bus.getId())
                        .status(bus.getStatus())
                        .driverName(bus.getDriverName())
                        .build())
                .toList();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Bus by id")
    public ResponseEntity<BusResponseDto> findBusById(@PathVariable UUID id) {
        Bus bus = busService.findBusById(id);
        BusResponseDto responseDto = BusResponseDto.builder()
                .id(bus.getId())
                .status(bus.getStatus())
                .driverName(bus.getDriverName())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a Bus")
    public ResponseEntity<BusResponseDto> updateBus(@PathVariable UUID id, @RequestBody @Valid BusDto dto) {
        Bus bus = busService.updateBus(id, dto);
        BusResponseDto responseDto = BusResponseDto.builder()
                .id(bus.getId())
                .status(bus.getStatus())
                .driverName(bus.getDriverName())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Bus by id")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        busService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
