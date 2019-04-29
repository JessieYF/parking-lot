import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperParkingBoy extends ParkingBoy {

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    protected Optional<ParkingLot> getParkingLotForParking() {
        return getParkingLots().stream()
                .max(Comparator.comparing(ParkingLot::vacancyRate));
    }
}
