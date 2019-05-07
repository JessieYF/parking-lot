import exception.CarWithoutNumberException;
import exception.DuplicatedCarNumberException;
import exception.ParkingLotIsNotAvailableException;
import exception.UnmatchedTicketException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {
    private final int capacity;
    private Map<Ticket, Car> ticketCarMap = new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Map<Ticket, Car> getTicketCarMap() {
        return ticketCarMap;
    }

    public Ticket park(Car car) throws Exception {
        validCarWhenParking(car);

        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car pick(Ticket ticket) throws Exception {
        if (!containTicket(ticket)) {
            throw new UnmatchedTicketException();
        }
        Car car = ticketCarMap.get(ticket);
        ticketCarMap.remove(ticket);
        return car;
    }

    public boolean isAvailable() {
        return capacity > ticketCarMap.size();
    }

    public boolean isCarNumberDuplicated(Car car) {
        return ticketCarMap.values()
                .stream()
                .anyMatch(carValue -> Objects.equals(car.getCarNumber(), carValue.getCarNumber()));
    }

    public boolean containTicket(Ticket ticket) {
        Car car = ticketCarMap.get(ticket);
        return car != null;
    }

    public Integer availableCapacity() {
        return capacity - this.getTicketCarMap().size();
    }

    public Double vacancyRate() {
        return new Double(availableCapacity())/new Double(capacity);
    }

    private void validCarWhenParking(Car car) throws Exception {
        if (car.getCarNumber() == null) {
            throw new CarWithoutNumberException();
        }
        if (!isAvailable()) {
            throw new ParkingLotIsNotAvailableException();
        }
        if (isCarNumberDuplicated(car)) {
            throw new DuplicatedCarNumberException();
        }
    }
}
