package lab4;

import java.util.ArrayList;
import java.util.List;

public class Road {

    public final List<Vehicle<? extends Human>> carsInRoad = new ArrayList<>();

    public void addCarToRoad(Vehicle<? extends Human> vehicle) {
        carsInRoad.add(vehicle);
    }

    public int getCountOfHumans() {
        int count = 0;

        for (Vehicle<? extends Human> vehicle : carsInRoad) {
            count += vehicle.getOccupiedSeats();
        }

        return count;
    }
}