import exception.CarWithoutNumberException;
import exception.DuplicatedCarNumberException;
import exception.ParkingLotIsNotAvailableException;
import exception.UnmatchedTicketException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    @Test
    void should_success_and_park_in_b_parking_lot_when_park_given_a_car_with_number_and_b_parking_lot_has_more_available_location_than_a() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        Ticket ticket = smartParkingBoy.park(new Car("A12345"));

        assertNotNull(ticket);
        assertEquals("A12345", parkingLotB.pick(ticket).getCarNumber());
        assertThrows(UnmatchedTicketException.class, () -> parkingLotA.pick(ticket));
    }

    @Test
    void should_success_and_park_in_b_parking_lot_when_park_given_a_car_with_number_and_only_a_is_available() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(0);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        Ticket ticket = smartParkingBoy.park(new Car("A12345"));

        assertNotNull(ticket);
        assertEquals("A12345", parkingLotA.pick(ticket).getCarNumber());
        assertThrows(UnmatchedTicketException.class, () -> parkingLotB.pick(ticket));
    }

    @Test
    void should_fail_when_park_given_car_number_is_duplicated_and_both_parking_lots_are_available() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        String duplicatedCarNumber = "A12345";
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        smartParkingBoy.park(new Car(duplicatedCarNumber));
        assertThrows(DuplicatedCarNumberException.class, () -> smartParkingBoy.park(new Car(duplicatedCarNumber)));
    }

    @Test
    void should_fail_when_park_given_a_car_without_number_and_both_parking_lots_are_available() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        assertThrows(CarWithoutNumberException.class, () -> smartParkingBoy.park(new Car(null)));
    }

    @Test
    void should_fail_when_park_given_a_car_with_number_and_both_parking_lots_are_not_available() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        smartParkingBoy.park(new Car("A12345"));
        smartParkingBoy.park(new Car("B12345"));

        assertThrows(ParkingLotIsNotAvailableException.class, () -> smartParkingBoy.park(new Car("C12345")));
    }

    @Test
    void should_success_when_pick_given_a_matched_ticket() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        Ticket ticket = smartParkingBoy.park(new Car("A12345"));
        Car car = smartParkingBoy.pick(ticket);

        assertNotNull(car);
        assertEquals("A12345", car.getCarNumber());
    }

    @Test
    void should_fail_when_pick_given_a_unmatched_ticket() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        smartParkingBoy.park(new Car("A12345"));

        assertThrows(UnmatchedTicketException.class, () -> smartParkingBoy.pick(new Ticket()));
    }
}
