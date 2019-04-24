import exception.CarWithoutNumberException;
import exception.ParkingLotIsNotAvailableException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<Ticket, Car> ticketCarMap = new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) throws Exception {
        validCarWhenParking(car);

        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    private void validCarWhenParking(Car car) throws CarWithoutNumberException, ParkingLotIsNotAvailableException {
        if (car.getCarNumber() == null) {
            throw new CarWithoutNumberException();
        }
        if (ticketCarMap.size() >= capacity) {
            throw new ParkingLotIsNotAvailableException();
        }
    }
}
