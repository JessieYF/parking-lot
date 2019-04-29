import exception.DuplicatedCarNumberException;
import exception.ParkingLotIsNotAvailableException;
import exception.UnmatchedTicketException;

import java.util.List;
import java.util.Optional;

abstract public class ParkingBoy {
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

        Optional<ParkingLot> firstAvailableParkingLot = findFirstAvailableParkingLot();

        if (!firstAvailableParkingLot.isPresent()) {
            throw new ParkingLotIsNotAvailableException();
        }

        return firstAvailableParkingLot.get().park(car);
    }

    public Car pick(Ticket ticket) throws Exception {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.containTicket(ticket))
                .findFirst()
                .orElseThrow(UnmatchedTicketException::new)
                .pick(ticket);
    }

    protected abstract Optional<ParkingLot> findFirstAvailableParkingLot();

    private boolean isCarNumberDuplicated(Car car) {
        return parkingLots.stream()
                .anyMatch(parkingLot -> parkingLot.isCarNumberDuplicated(car));
    }
}
