import exception.DuplicatedCarNumberException;
import exception.ParkingLotIsNotAvailableException;
import exception.UnmatchedTicketException;

import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;

    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public Ticket park(Car car) throws Exception {
        if (isCarNumberDuplicated(car)) {
            throw new DuplicatedCarNumberException();
        }

        Optional<ParkingLot> firstAvailableParkingLot = getParkingLotForParking();

        if (!firstAvailableParkingLot.isPresent()) {
            throw new ParkingLotIsNotAvailableException();
        }

        return firstAvailableParkingLot.get().park(car);
    }

    protected Optional<ParkingLot> getParkingLotForParking() {
        return null;
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
            throw new UnmatchedTicketException();
        }
        return matchedPackingLot.get().pick(ticket);
    }
}
