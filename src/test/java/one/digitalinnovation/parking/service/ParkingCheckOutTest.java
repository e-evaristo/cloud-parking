package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.model.Parking;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParkingCheckOutTest {

    @Test
    void whenGetBillOfOneHourOfParkingThenReturnOneHourValue() {
        Parking parking = new Parking();
        parking.setEntryDate(LocalDateTime.now());
        parking.setExitDate(LocalDateTime.now().plusHours(1));
        assertEquals(ParkingCheckOut.ONE_HOUR_VALUE, ParkingCheckOut.getBill(parking));
    }

    @Test
    void whenGetBillOfOneDayThenReturnOneDayPlusAdditionalHourValue() {
        Parking parking = new Parking();
        parking.setEntryDate(LocalDateTime.now());
        parking.setExitDate(parking.getEntryDate().plusDays(1));
        long hours = parking.getEntryDate().until(parking.getExitDate(), ChronoUnit.HOURS);
        assertEquals(ParkingCheckOut.ONE_HOUR_VALUE + ParkingCheckOut.ADDITIONAL_PER_HOUR_VALUE * hours, ParkingCheckOut.getBill(parking));
    }
}