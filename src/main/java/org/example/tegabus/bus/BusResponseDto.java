package org.example.tegabus.bus;

import lombok.Builder;
import lombok.Data;

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
