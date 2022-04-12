package FOO;

import java.util.LinkedList;
import java.util.Queue;

/**
 * singleton class to be simulation being crossed by car by storing them
 * in a queue.
 */
public class Bridge {
	/**
	 * Instance variable holding the unique instance of bridge class
	 */
	private static Bridge bridgeIns = null;
	/**
	 * queue representing cars on bridge
	 */
	private Queue<Car> carsOnBridge = new LinkedList<>();

	/**
	 * private class constructor
	 */
	private Bridge() {
	}

	/**
	 * if bridge instance has yet to be created, creates it and return it. Else,
	 * return previously generated bridge
	 * @return singleton instance of bridge
	 */
	public static Bridge getInstance() {
		if (bridgeIns == null)
			bridgeIns = new Bridge();
		return bridgeIns;
	}

	/**
	 * puts passed car in the queue while we simulate it crossing by it waiting and prints
	 * the car that has gon on the bridge and all cars currently on the bridge to verify if only one class of car
	 * is on bridge at once.
	 * @param car car class to be put in the queue of the cars being on the bridge
	 */
	public void putCarOnBridge(Car car) {
		carsOnBridge.add(car);
		System.out.println(car.getName() + " got on bridge\n");
		System.out.println(toString(carsOnBridge));
	}

	/**
	 * helper function to get the name of all car of passed queue
	 * and generates a string with it and returns it
	 * @param cars Queues of car to get names extracted from and formatted
	 * @return formatted string of all cars on bridge currently
	 */
	private String toString(Queue<Car> cars) {
		if (cars.size() == 1)
			return ("Only " + cars.peek().getName() + " is on bridge");
		StringBuilder string = new StringBuilder();
		for (Car car : cars)
			string.append(car.getName() + " and ");
		string.append("are on bridge\n");
		return string.toString();
	}

	/**
	 * removes car from bridge, with fifo priority
	 */
	public void putCarOffBridge() {
		System.out.println(carsOnBridge.remove().getName() + " got off bridge\n");
	}
}