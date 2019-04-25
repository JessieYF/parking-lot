import exception.DuplicatedCarNumberException;
import exception.ParkingLotIsNotAvailableException;

import java.util.*;
import java.util.stream.Collectors;

public class GraduateParkingBoy {
    private List<ParkingLot> parkingLots;

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;

    }

    public Ticket park(Car car) throws Exception {
        if (isCarNumberDuplicated(car)) {
            throw new DuplicatedCarNumberException();
        }

        Optional<ParkingLot> firstAvailableParkingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLot.isAvailable())
                .findFirst();

        if (!firstAvailableParkingLot.isPresent()) {
            throw new ParkingLotIsNotAvailableException();
        }

        return firstAvailableParkingLot.get().park(car);
    }

    private boolean isCarNumberDuplicated(Car car) {
        return parkingLots.stream()
                .anyMatch(parkingLot -> parkingLot.isCarNumberDuplicated(car));
    }

    public Car pick(Ticket ticket) throws Exception {
        Optional<ParkingLot> matchedPackingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLot.containTicket(ticket))
                .findFirst();

        if (!matchedPackingLot.isPresent()) {
            return null;
        }
        return matchedPackingLot.get().pick(ticket);
    }
}
