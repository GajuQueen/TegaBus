package org.example.tegabus.route.RouteDtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RouteResponseDto {
    private UUID id;
    private String origin;
    private String destination;
    private Double distanceInKm;
    private Integer  durationInMinutes;
    private Double price;
}
