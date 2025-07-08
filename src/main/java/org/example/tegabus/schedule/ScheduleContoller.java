package org.example.tegabus.schedule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tegabus.schedule.ScheduleDtos.ScheduleDto;
import org.example.tegabus.schedule.ScheduleDtos.ScheduleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/schedules")
@SecurityRequirement(name = "auth")
@RequiredArgsConstructor
@Tag(name = "Schedule Controller")
public class ScheduleContoller {
    private final ScheduleService scheduleService;


    @Operation(summary = "Create a new schedule")
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody @Valid ScheduleDto dto) {
        var saved = scheduleService.createSchedule(dto);
        return new ResponseEntity<>(scheduleService.toResponseDto(saved), HttpStatus.CREATED);

    }
    @Operation(summary = "Get all schedules")
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules(){
//
        return  new ResponseEntity<>(scheduleService.getAllScheduleDtos(), HttpStatus.OK);
    }
    @Operation(summary = "Get schedule by Id")
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable UUID id) {
        return scheduleService.getScheduleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "update a schedule by id"
    )
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable UUID id, @RequestBody ScheduleDto dto) {
       return scheduleService.updateSchedule(id, dto)
               .map(scheduleService::toResponseDto)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }
@Operation(
        summary = "Delete schedule by id"
)
@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable UUID id) {
        scheduleService.deleteScheduleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    }

