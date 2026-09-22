package lab4;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T extends Human> {

    private final int maxSeats;
    private final List<T> passengers = new ArrayList<>();

    public Vehicle(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public void boardPassenger(T passenger) {
        if (passengers.size() >= maxSeats) {
            throw new VehicleFullException("У транспорті немає вільних місць");
        }

        passengers.add(passenger);
    }

    public void disembarkPassenger(T passenger) {
        if (!passengers.remove(passenger)) {
            throw new PassengerNotFoundException(
                    "Пасажир не знаходиться у транспорті"
            );
        }
    }
}