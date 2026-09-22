package lab4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    @Test
    void busCanCarryDifferentHumans() {
        Bus bus = new Bus(3);

        bus.boardPassenger(new Passenger("Serhii"));
        bus.boardPassenger(new Firefighter("Oleksandr"));
        bus.boardPassenger(new Policeman("Ivan"));

        assertEquals(3, bus.getOccupiedSeats());
    }

    @Test
    void taxiCanCarryDifferentHumans() {
        Taxi taxi = new Taxi(3);

        taxi.boardPassenger(new Passenger("Serhii"));
        taxi.boardPassenger(new Firefighter("Oleksandr"));
        taxi.boardPassenger(new Policeman("Ivan"));

        assertEquals(3, taxi.getOccupiedSeats());
    }

    @Test
    void fireTruckCanCarryFirefighter() {
        FireTruck fireTruck = new FireTruck(2);

        fireTruck.boardPassenger(new Firefighter("Petro"));

        assertEquals(1, fireTruck.getOccupiedSeats());
    }

    @Test
    void policeCarCanCarryPoliceman() {
        PoliceCar policeCar = new PoliceCar(2);

        policeCar.boardPassenger(new Policeman("Andrii"));

        assertEquals(1, policeCar.getOccupiedSeats());
    }

    @Test
    void maxSeatsWorksCorrectly() {
        Bus bus = new Bus(10);

        assertEquals(10, bus.getMaxSeats());
    }

    @Test
    void fullVehicleThrowsException() {
        Taxi taxi = new Taxi(1);

        taxi.boardPassenger(new Passenger("Serhii"));

        assertThrows(
                VehicleFullException.class,
                () -> taxi.boardPassenger(new Passenger("Ivan"))
        );
    }

    @Test
    void passengerCanLeaveVehicle() {
        Bus bus = new Bus(3);
        Passenger passenger = new Passenger("Serhii");

        bus.boardPassenger(passenger);
        bus.disembarkPassenger(passenger);

        assertEquals(0, bus.getOccupiedSeats());
    }

    @Test
    void removingMissingPassengerThrowsException() {
        Bus bus = new Bus(5);
        Passenger passenger = new Passenger("Serhii");

        assertThrows(
                PassengerNotFoundException.class,
                () -> bus.disembarkPassenger(passenger)
        );
    }

    @Test
    void roadCountsAllHumans() {
        Bus bus = new Bus(5);
        FireTruck fireTruck = new FireTruck(3);
        PoliceCar policeCar = new PoliceCar(3);

        bus.boardPassenger(new Passenger("Serhii"));
        bus.boardPassenger(new Firefighter("Oleksandr"));

        fireTruck.boardPassenger(new Firefighter("Petro"));

        policeCar.boardPassenger(new Policeman("Ivan"));

        Road road = new Road();

        road.addCarToRoad(bus);
        road.addCarToRoad(fireTruck);
        road.addCarToRoad(policeCar);

        assertEquals(4, road.getCountOfHumans());
    }
}