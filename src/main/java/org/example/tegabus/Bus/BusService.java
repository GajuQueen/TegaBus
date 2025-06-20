package org.example.tegabus.Bus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BusService {
    public final BusRepository busRepository;

    public Bus findBusById(UUID id) {
        return busRepository.findById(id).orElseThrow(() -> new RuntimeException("Bus not found"));
    }

}
