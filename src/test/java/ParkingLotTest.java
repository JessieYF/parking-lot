import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotTest {
    @Test
    void should_success_and_return_the_ticket_when_park_given_a_car_with_number_and_the_parking_lot_is_available() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("A12345");
        assertNotNull(parkingLot.park(car));
    }
}
