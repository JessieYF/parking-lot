import exception.UnmatchedTicketException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GraduateParkingBoyTest {
    @Test
    void should_success_and_park_in_a_parking_lot_when_park_given_a_car_with_number_and_both_a_and_b_parking_lot_is_available() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        Ticket ticket = graduateParkingBoy.park(new Car("A12345"));
        assertNotNull(ticket);
        assertEquals("A12345", parkingLotA.pick(ticket).getCarNumber());
        assertThrows(UnmatchedTicketException.class, () -> parkingLotB.pick(ticket));
    }
}
