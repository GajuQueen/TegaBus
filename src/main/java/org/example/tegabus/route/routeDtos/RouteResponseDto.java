package org.example.tegabus.route.routeDtos;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RouteResponseDto {
    private UUID id;
    private String origin;
    private String destination;
    private Double price;
    private Integer  durationInMinutes;
    private Double distanceInKm;

    public RouteResponseDto(String origin, String destination, Double price, Integer durationInMinutes, Double distanceInKm) {
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.durationInMinutes = durationInMinutes;
        this.distanceInKm = distanceInKm;
    }

}
