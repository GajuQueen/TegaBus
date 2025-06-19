package org.example.tegabus.Booking;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Booking extends Common {
    @Column(unique = true)
    public int seatNumber;
    public BookingStatus status;

}
