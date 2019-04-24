import exception.CarWithoutNumberException;
import exception.DuplicatedCarNumberException;
import exception.ParkingLotIsNotAvailableException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        CarWithoutNumberException exception = assertThrows(CarWithoutNumberException.class, () -> parkingLot.park(new Car(null)));
        assertNotNull(exception);
    }

    @Test
    void should_fail_when_park_given_a_car_without_number_and_the_parking_lot_is_not_available() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("A12345"));
        CarWithoutNumberException exception = assertThrows(CarWithoutNumberException.class, () -> parkingLot.park(new Car(null)));
        assertNotNull(exception);
    }

    @Test
    void should_fail_when_park_given_a_car_with_number_and_the_parking_lot_is_not_available() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("A12345"));
        ParkingLotIsNotAvailableException exception = assertThrows(ParkingLotIsNotAvailableException.class, () -> parkingLot.park(new Car("B12345")));
        assertNotNull(exception);
    }

    @Test
    void should_fail_when_park_given_a_car_with_number_is_duplicated_in_the_parking_lot() throws Exception {
        String duplicatedCarNumber = "A12345";
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car(duplicatedCarNumber));
        DuplicatedCarNumberException exception = assertThrows(DuplicatedCarNumberException.class, () -> parkingLot.park(new Car(duplicatedCarNumber)));
        assertNotNull(exception);
    }
}
