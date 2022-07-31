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
        Parking parking = new Parking(id, "ABC-1234", "SP", "Fusca", "Preto");
        parkingMap.put(parking.getId(), parking);
    }

    public List<Parking> fidAll() {
        return parkingMap.values().stream().collect(Collectors.toList());

    }

    private static String getUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }
}
