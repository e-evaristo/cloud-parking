package one.digitalinnovation.parking.controller.service;

import one.digitalinnovation.parking.controller.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static final Map<String, Parking> parkingMap = new HashMap();

    static {
        var id = getUUID();
        var id1 = getUUID();
        Parking parking = new Parking(id, "ABC-1234", "SP", "Fusca", "Preto");
        Parking parking1 = new Parking(id, "AAA-0909", "PB", "VW Gol", "Prata");
        parkingMap.put(id, parking);
        parkingMap.put(id1, parking1);
    }

    public List<Parking> fidAll() {
        return parkingMap.values().stream().collect(Collectors.toList());

    }

    private static String getUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if (parking == null) {
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    public Parking create(Parking parking) {
        parking.setId(getUUID());
        parking.setEntryDate(LocalDateTime.now());
        parkingMap.put(parking.getId(), parking);
        return parking;
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking update(String id, Parking parking) {
        Parking parkingOld = findById(id);
        parkingOld.setLicense(parking.getLicense());
        parkingOld.setState(parking.getState());
        parkingOld.setModel(parking.getModel());
        parkingOld.setColor(parking.getColor());
        parkingMap.replace(id, parkingOld);
        return parkingOld;
    }
}
