package one.digitalinnovation.parking.controller.service;

import one.digitalinnovation.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap();

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
        return parkingMap.get(id);
    }

    public Parking create(Parking parking) {
        parking.setId(getUUID());
        parking.setEntryDate(LocalDateTime.now());
        parkingMap.put(parking.getId(), parking);
        return parking;
    }
}
