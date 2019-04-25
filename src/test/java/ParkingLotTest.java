import exception.CarWithoutNumberException;
import exception.DuplicatedCarNumberException;
import exception.ParkingLotIsNotAvailableException;
import exception.UnmatchedTicketException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_success_and_return_the_ticket_when_park_given_a_car_with_number_and_the_parking_lot_is_available() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("A12345");
        assertNotNull(parkingLot.park(car));
    }

    @Test
    void should_fail_when_park_given_a_car_without_number_and_the_parking_lot_is_available() {
        ParkingLot parkingLot = new ParkingLot(1);
        assertThrows(CarWithoutNumberException.class, () -> parkingLot.park(new Car(null)));
    }

    @Test
    void should_fail_when_park_given_a_car_without_number_and_the_parking_lot_is_not_available() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("A12345"));
        assertThrows(CarWithoutNumberException.class, () -> parkingLot.park(new Car(null)));
    }

    @Test
    void should_fail_when_park_given_a_car_with_number_and_the_parking_lot_is_not_available() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("A12345"));
        assertThrows(ParkingLotIsNotAvailableException.class, () -> parkingLot.park(new Car("B12345")));
    }

    @Test
    void should_fail_when_park_given_a_car_with_number_is_duplicated_in_the_parking_lot() throws Exception {
        String duplicatedCarNumber = "A12345";
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car(duplicatedCarNumber));
        assertThrows(DuplicatedCarNumberException.class, () -> parkingLot.park(new Car(duplicatedCarNumber)));
    }

    @Test
    void should_success_and_return_car_when_pick_given_a_matched_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = parkingLot.park(new Car("A12345"));
        Car car = parkingLot.pick(ticket);
        assertEquals("A12345", car.getCarNumber());
    }

    @Test
    void should_fail_when_pick_given_a_unmatched_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("A12345"));
        assertThrows(UnmatchedTicketException.class, () -> parkingLot.pick(new Ticket()));
    }
}
