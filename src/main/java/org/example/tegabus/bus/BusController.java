package org.example.tegabus.bus;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bus")
@RequiredArgsConstructor
public class BusController {
    private final BusService busService;

    @PostMapping
    @Operation(summary = "Create a Bus")
    public ResponseEntity<BusResponseDto> createBus(@RequestBody @Valid BusDto dto) {
        Bus bus = busService.createBus(dto);
        BusResponseDto responseDto = BusResponseDto.builder()
                .id(bus.getId())
                .model(bus.getModel())
                .status(bus.getStatus())
                .type(bus.getType())
                .driverName(bus.getDriverName())
                .driverPhoneNumber(bus.getDriverPhoneNumber())
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
                        .model(bus.getModel())
                        .status(bus.getStatus())
                        .type(bus.getType())
                        .driverName(bus.getDriverName())
                        .driverPhoneNumber(bus.getDriverPhoneNumber())
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
                .model(bus.getModel())
                .status(bus.getStatus())
                .type(bus.getType())
                .driverName(bus.getDriverName())
                .driverPhoneNumber(bus.getDriverPhoneNumber())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a Bus")
    public ResponseEntity<BusResponseDto> updateBus(@PathVariable UUID id, @RequestBody @Valid BusDto dto) {
        Bus bus = busService.updateBus(id, dto);
        BusResponseDto responseDto = BusResponseDto.builder()
                .id(bus.getId())
                .model(bus.getModel())
                .status(bus.getStatus())
                .type(bus.getType())
                .driverName(bus.getDriverName())
                .driverPhoneNumber(bus.getDriverPhoneNumber())
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
