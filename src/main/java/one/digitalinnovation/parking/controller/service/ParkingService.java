package one.digitalinnovation.parking.controller.service;

import one.digitalinnovation.parking.controller.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {

    private final ParkingRepository repository;

    public ParkingService(ParkingRepository repository) {
        this.repository = repository;
    }

    public List<Parking> fidAll() {
        return repository.findAll();

    }

    public Parking findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
    }

    public Parking create(Parking parking) {
        parking.setId(getUUID());
        parking.setEntryDate(LocalDateTime.now());
        repository.save(parking);
        return parking;
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public Parking update(String id, Parking parking) {
        Parking parkingOld = findById(id);
        parkingOld.setLicense(parking.getLicense());
        parkingOld.setState(parking.getState());
        parkingOld.setModel(parking.getModel());
        parkingOld.setColor(parking.getColor());
        repository.save(parkingOld);
        return parkingOld;
    }

    public Parking exit(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        /*TODO Calcular valor*/
        repository.save(parking);
        return parking;
    }

    private static String getUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }
}
