package org.example.tegabus.route;

import org.example.tegabus.route.routeDtos.RouteResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RouteConverter {
    public RouteResponseDto toResponseDto(Route route){
        if(route == null){
            return null;
        }
        return RouteResponseDto.builder()
                .id(route.getId())
                .origin(route.getOrigin())
                .destination(route.getDestination())
                .build();
    }
}
// reusable class that convert that contain logic for converting route entity -> RouteResponseEntity