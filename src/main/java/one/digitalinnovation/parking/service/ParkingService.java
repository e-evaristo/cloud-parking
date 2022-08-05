package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {

    private final ParkingRepository repository;

    public ParkingService(ParkingRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> fidAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Parking findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
    }

    @Transactional(readOnly = false)
    public Parking create(Parking parking) {
        parking.setId(getUUID());
        parking.setEntryDate(LocalDateTime.now());
        repository.save(parking);
        return parking;
    }

    @Transactional(readOnly = false)
    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    @Transactional(readOnly = false)
    public Parking update(String id, Parking parking) {
        Parking parkingOld = findById(id);
        parkingOld.setLicense(parking.getLicense());
        parkingOld.setState(parking.getState());
        parkingOld.setModel(parking.getModel());
        parkingOld.setColor(parking.getColor());
        repository.save(parkingOld);
        return parkingOld;
    }

    @Transactional(readOnly = false)
    public Parking exit(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        repository.save(parking);
        return parking;
    }

    private static String getUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }
}
