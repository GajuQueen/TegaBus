package org.example.tegabus.route.routeDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDto {

        @NotBlank(message = "Origin is required")
        private String origin;
        @NotBlank(message = "Destination is required")
        private String destination;
        @NotNull(message = "DistanceInKm is required")
        @Positive(message = "DistanceInKm must be positive")
        private Double distanceInKm;
        @NotNull(message = "DurationInMinutes is required")
        @Positive(message = "DurationInMinutes must be positive")
        private Integer  durationInMinutes;
        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        private Double price;
    }

