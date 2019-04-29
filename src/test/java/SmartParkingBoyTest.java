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
}
