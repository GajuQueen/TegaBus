package org.example.tegabus.bus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class BusDto {
    @NotBlank(message = "PlateNumber is required")
    private String plateNumber;
    @NotNull(message = "BusModel is required")
    private BusModel model;
    @NotNull(message = "BusStatus is required")
    private BusStatus status;
    @NotNull(message = "BusType is required")
    private BusType type;
    @NotBlank(message = "DriverName must not be blank")
    private String driverName;
    @NotBlank(message = "DriverPhoneNumber must not be blank")
    private String driverPhoneNumber;
}
