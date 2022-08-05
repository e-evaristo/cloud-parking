package one.digitalinnovation.parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.parking.controller.mapper.ParkingMapper;
import one.digitalinnovation.parking.service.ParkingService;
import one.digitalinnovation.parking.dto.ParkingDTO;
import one.digitalinnovation.parking.model.Parking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

    private final ParkingService service;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.service = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @ApiOperation(value = "Find all parkings")
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = service.fidAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find parking by id")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        Parking parking = service.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @ApiOperation(value = "Create parking")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingDTO parkingDTO) {
        Parking parking = parkingMapper.toParking(parkingDTO);
        Parking result = service.create(parking);
        ParkingDTO resultDTO = parkingMapper.toParkingDTO(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete parking")
    public ResponseEntity delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update parking")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingDTO parkingDTO) {
        Parking parking = parkingMapper.toParking(parkingDTO);
        Parking result = service.update(id, parking);
        ParkingDTO resultDTO = parkingMapper.toParkingDTO(result);
        return ResponseEntity.ok(resultDTO);
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "Exit parking")
    public ResponseEntity<ParkingDTO> exit(@PathVariable String id) {
        Parking result = service.exit(id);
        return ResponseEntity.ok(parkingMapper.toParkingDTO(result));
    }
}
