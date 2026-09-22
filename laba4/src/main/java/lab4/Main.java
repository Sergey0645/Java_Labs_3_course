package lab4;

public class Main {

    public static void main(String[] args) {

        Passenger passenger = new Passenger("Serhii");
        Firefighter firefighter = new Firefighter("Oleksandr");
        Policeman policeman = new Policeman("Ivan");

        Bus bus = new Bus(10);
        Taxi taxi = new Taxi(4);
        FireTruck fireTruck = new FireTruck(4);
        PoliceCar policeCar = new PoliceCar(4);

        bus.boardPassenger(passenger);
        bus.boardPassenger(firefighter);
        bus.boardPassenger(policeman);

        taxi.boardPassenger(passenger);

        fireTruck.boardPassenger(firefighter);

        policeCar.boardPassenger(policeman);

        Road road = new Road();

        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(fireTruck);
        road.addCarToRoad(policeCar);

        System.out.println("Bus: " + bus.getOccupiedSeats());
        System.out.println("Taxi: " + taxi.getOccupiedSeats());
        System.out.println("Fire truck: " + fireTruck.getOccupiedSeats());
        System.out.println("Police car: " + policeCar.getOccupiedSeats());

        System.out.println("Total people on the road: "
                + road.getCountOfHumans());
    }
}