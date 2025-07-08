package org.example.tegabus.bus.Dtos;

import lombok.Builder;
import lombok.Data;
import org.example.tegabus.bus.BusModel;
import org.example.tegabus.bus.BusStatus;
import org.example.tegabus.bus.BusType;

import java.util.UUID;

@Data
@Builder
public class BusResponseDto {
    private UUID id;
    private String plateNumber;
    private BusModel model;
    private BusStatus status;
    private BusType type;
    private String driverName;
    private String driverPhoneNumber;
}
