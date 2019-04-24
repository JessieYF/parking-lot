import exception.CarWithoutNumberException;

public class ParkingLot {
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) throws Exception {
        if (car.getCarNumber() == null) {
            throw new CarWithoutNumberException();
        }
        return new Ticket();
    }
}
