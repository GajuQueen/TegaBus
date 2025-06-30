package org.example.tegabus.Schedule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleDto dto) {
        Schedule schedule = scheduleService.createSchedule(dto);
        return new ResponseEntity<>(schedule, HttpStatus.CREATED);

    }
    @Operation(summary = "Get all schedules")
    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules(){
        List<Schedule> schedules = scheduleService.getSchedules();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }
    @Operation(summary = "Get schedule by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable UUID id) {
        return scheduleService.getScheduleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Get schedules by travel date"
    )
    @GetMapping("/by-date")
    public ResponseEntity<List<Schedule>> getByTravelDate(@PathVariable LocalDate travelDate) {
        List<Schedule> schedule = scheduleService.getScheduleByTravelDate(travelDate);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }
    @Operation(
            summary = "update a schedule by id"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable UUID id, @RequestBody ScheduleDto dto) {
        Optional<Schedule> updated = scheduleService.updateSchedule(id, dto);
        return updated.map(ResponseEntity::ok)
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

