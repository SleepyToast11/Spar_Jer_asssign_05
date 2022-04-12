package FOO;

import java.util.Random;

/**
 * Car class starting from golden going towards bu
 * that uses shared controllers class semaphore to control flow and bridge to simulate
 * Is going slower than counterpart
 */
public class GoldenBoundCar extends Car implements Runnable {
	/**
	 * controller controlling the flow on the bridge
	 */
	Controller controller = Controller.getInstance();
	/**
	 * simulated bridge to be crossed
	 */
	Bridge bridge = Bridge.getInstance();
	/**
	 * Thread variable of class
	 */
	Thread thread = null;
	/**
	 * name of car for recognition when printed out
	 */
	private String name;

	/**
	 * constructs GoldenBoundCar object and sets name to it with passed string
	 *
	 * @param name String to remember car by
	 */
	public GoldenBoundCar(String name) {
		this.name = name;
	}

	/**
	 * getter function for name string variable
	 * @return string of name variable
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * When thread is started, will run forever.
	 * waits, then sees if it is its turn  by acquiring respective semaphore,
	 * puts itself on the bridge, waits again. after will release semaphore and check
	 * if all permits are free, if yes drain all its respective permits and releases all
	 * counterpart permits. else, waits a bit and restarts.
	 */
	@Override
	public void run() {
		Random random = new Random();
		while (true) {
			try {
				Thread.sleep(random.nextInt(10) * 100);
				controller.goldenBound.acquire();
				bridge.putCarOnBridge(this);
				Thread.sleep(125 * 4);
				bridge.putCarOffBridge();
				controller.goldenBound.release();
				if (controller.goldenBound.availablePermits() == controller.semaphoreNumGolden) {
					controller.goldenBound.drainPermits();
					controller.bishopsBound.release(controller.semaphoreNumBishops);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * if thread variable is null, initializes thread variable and start() it
	 */
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
}

