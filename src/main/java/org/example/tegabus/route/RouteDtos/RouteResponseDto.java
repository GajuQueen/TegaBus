package org.example.tegabus.route.RouteDtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteResponseDto {
    private String origin;
    private String destination;
    private Double price;
    private Integer  durationInMinutes;
    private Double distanceInKm;


}
