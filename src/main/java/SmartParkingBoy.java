import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    protected Optional<ParkingLot> getParkingLotForParking() {
        return getParkingLots().stream()
                .max(Comparator.comparing(ParkingLot::availableCapacity));
    }
}
