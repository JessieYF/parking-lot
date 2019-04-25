import exception.DuplicatedCarNumberException;
import exception.ParkingLotIsNotAvailableException;

import java.util.List;
import java.util.Optional;

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
}
