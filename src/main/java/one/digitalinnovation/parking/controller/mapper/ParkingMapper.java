package one.digitalinnovation.parking.controller.mapper;

import one.digitalinnovation.parking.dto.ParkingDTO;
import one.digitalinnovation.parking.model.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    public ParkingDTO toParkingDTO(Parking parking) {
        return modelMapper.map(parking, ParkingDTO.class);
    }
    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
        return parkingList.stream().map(this::toParkingDTO).collect(Collectors.toList());
    }

    public Parking toParking(ParkingDTO parkingDTO) {
        return modelMapper.map(parkingDTO, Parking.class);
    }
}
