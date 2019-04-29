import java.util.*;

public class GraduateParkingBoy extends ParkingBoy {

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected Optional<ParkingLot> findFirstAvailableParkingLot() {
        return getParkingLots().stream()
                .filter(parkingLot -> parkingLot.isAvailable())
                .findFirst();
    }
}
