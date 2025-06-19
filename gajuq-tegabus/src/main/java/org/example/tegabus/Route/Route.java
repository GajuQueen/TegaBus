package org.example.tegabus.Route;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tegabus.Common;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "routes")
public class Route extends Common {
    public String origin;
    public String destination;
    public Double distanceInKm;
    public int durationInMinutes;

}
