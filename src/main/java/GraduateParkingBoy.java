import java.util.*;

public class GraduateParkingBoy extends ParkingBoy {

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    protected Optional<ParkingLot> getParkingLotForParking() {
        return getParkingLots().stream()
                .filter(parkingLot -> parkingLot.isAvailable())
                .findFirst();
    }
}
