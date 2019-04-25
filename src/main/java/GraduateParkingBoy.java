import exception.DuplicatedCarNumberException;

import java.util.List;

public class GraduateParkingBoy {
    private List<ParkingLot> parkingLots;

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;

    }

    public Ticket park(Car car) throws Exception {
        if (isCarNumberDuplicated(car)) {
            throw new DuplicatedCarNumberException();
        }

        ParkingLot firstAvailableParkingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLot.isAvailable())
                .findFirst()
                .get();
        if (firstAvailableParkingLot != null) {
            return firstAvailableParkingLot.park(car);
        }
        return null;
    }

    private boolean isCarNumberDuplicated(Car car) {
        return parkingLots.stream()
                .anyMatch(parkingLot -> parkingLot.isCarNumberDuplicated(car));
    }
}
