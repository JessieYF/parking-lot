import exception.CarWithoutNumberException;
import exception.DuplicatedCarNumberException;
import exception.ParkingLotIsNotAvailableException;
import exception.UnmatchedTicketException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GraduateParkingBoyTest {
    @Test
    void should_success_and_park_in_a_parking_lot_when_park_given_a_car_with_number_and_both_parking_lots_are_available() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        Ticket ticket = graduateParkingBoy.park(new Car("A12345"));

        assertNotNull(ticket);
        assertEquals("A12345", parkingLotA.pick(ticket).getCarNumber());
        assertThrows(UnmatchedTicketException.class, () -> parkingLotB.pick(ticket));
    }

    @Test
    void should_success_and_park_in_b_parking_lot_when_park_given_a_car_with_number_and_only_b_is_available() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(0);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        Ticket ticket = graduateParkingBoy.park(new Car("A12345"));

        assertNotNull(ticket);
        assertEquals("A12345", parkingLotB.pick(ticket).getCarNumber());
        assertThrows(UnmatchedTicketException.class, () -> parkingLotA.pick(ticket));
    }

    @Test
    void should_fail_when_park_given_car_number_is_duplicated_and_both_parking_lots_are_available() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        String duplicatedCarNumber = "A12345";
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        graduateParkingBoy.park(new Car(duplicatedCarNumber));
        assertThrows(DuplicatedCarNumberException.class, () -> graduateParkingBoy.park(new Car(duplicatedCarNumber)));
    }

    @Test
    void should_fail_when_park_given_a_car_without_number_and_both_parking_lots_are_available() {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        assertThrows(CarWithoutNumberException.class, () -> graduateParkingBoy.park(new Car(null)));
    }

    @Test
    void should_fail_when_park_given_a_car_with_number_and_both_parking_lots_are_not_available() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        graduateParkingBoy.park(new Car("A12345"));
        graduateParkingBoy.park(new Car("B12345"));

        assertThrows(ParkingLotIsNotAvailableException.class, () -> graduateParkingBoy.park(new Car("C12345")));
    }

    @Test
    void should_success_when_pick_given_a_matched_ticket() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        Ticket ticket = graduateParkingBoy.park(new Car("A12345"));
        Car car = graduateParkingBoy.pick(ticket);

        assertNotNull(car);
        assertEquals("A12345", car.getCarNumber());
    }

    @Test
    void should_fail_when_pick_given_a_unmatched_ticket() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Arrays.asList(parkingLotA, parkingLotB));
        graduateParkingBoy.park(new Car("A12345"));

        assertThrows(UnmatchedTicketException.class, () -> graduateParkingBoy.pick(new Ticket()));
    }
}
