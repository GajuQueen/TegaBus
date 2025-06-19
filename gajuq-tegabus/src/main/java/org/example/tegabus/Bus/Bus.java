package org.example.tegabus.Bus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tegabus.Common;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bus extends Common {
    @Column(unique = true)
    public String plateNumber;
    public int capacity;
    public BusModel model;
    @Column(nullable = false)
    public BusStatus status;
    public BusType type;


}
