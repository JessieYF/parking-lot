import java.util.List;

public class GraduateParkingBoy {
    private List<ParkingLot> parkingLots;

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;

    }

    public Ticket park(Car car) throws Exception {
        ParkingLot firstAvailableParkingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLot.isAvailable())
                .findFirst()
                .get();
        if (firstAvailableParkingLot != null) {
            return firstAvailableParkingLot.park(car);
        }
        return null;
    }
}
