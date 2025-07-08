package org.example.tegabus.bus;

import lombok.RequiredArgsConstructor;
//import org.example.tegabus.exception.ResourceNotFoundException;
import org.example.tegabus.bus.Dtos.BusDto;
import org.example.tegabus.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BusService {
    public final BusRepository busRepository;

    public Bus createBus(BusDto dto) {
        Bus bus = new Bus();
        bus.setPlateNumber(dto.getPlateNumber());
        bus.setModel(dto.getModel());
        bus.setStatus(dto.getStatus());
        bus.setType(dto.getType());
        bus.setDriverName(dto.getDriverName());
        bus.setDriverPhoneNumber(dto.getDriverPhoneNumber());
        return busRepository.save(bus);

    }
    
    public List<Bus> findAll(){
        return busRepository.findAll();
    }

    public Bus findBusById(UUID id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));
    }

    public Bus updateBus(UUID id, BusDto dto) {

        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));
        bus.setPlateNumber(dto.getPlateNumber());
        bus.setModel(dto.getModel());
        bus.setStatus(dto.getStatus());
        bus.setType(dto.getType());
        bus.setDriverName(dto.getDriverName());
        bus.setDriverPhoneNumber(dto.getDriverPhoneNumber());
        return busRepository.save(bus);

    }

    public void deleteById(UUID id) {
        busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));
        busRepository.deleteById(id);
    }

}
