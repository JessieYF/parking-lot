import exception.CarWithoutNumberException;
import exception.DuplicatedCarNumberException;
import exception.ParkingLotIsNotAvailableException;
import exception.UnmatchedTicketException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public Car pick(Ticket ticket) throws Exception {
        Car car = ticketCarMap.get(ticket);
        if (car == null) {
            throw new UnmatchedTicketException();
        }
        return car;
    }

    private void validCarWhenParking(Car car) throws Exception {
        if (car.getCarNumber() == null) {
            throw new CarWithoutNumberException();
        }
        if (ticketCarMap.size() >= capacity) {
            throw new ParkingLotIsNotAvailableException();
        }
        if (isCarNumberDuplicated(car)) {
            throw new DuplicatedCarNumberException();
        }
    }

    private boolean isCarNumberDuplicated(Car car) {
        return ticketCarMap.values()
                .stream()
                .anyMatch(carValue -> Objects.equals(car.getCarNumber(), carValue.getCarNumber()));
    }
}
