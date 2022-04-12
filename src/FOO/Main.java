package FOO;

/*
 * Name: Jerome Sparnaay
 * Date: April 10th, 2022
 * Description: simulation of flow of traffic over a bridge with a single lane
 */


import java.util.ArrayList;

/**
 * main process
 */
public class Main {

    /**
     * creates car instances  and start all the threads
     * @param args command line argument
     */
    public static void main(String[] args) {
        Bridge bridge = Bridge.getInstance();
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new GoldenBoundCar("golden1"));
        cars.add(new GoldenBoundCar("golden2"));
        cars.add(new BishopsBoundCar("bishops1"));
        cars.add(new BishopsBoundCar("bishops2"));
        cars.add(new BishopsBoundCar("bishops3"));
        for (Car car : cars)
            car.start();
    }
}