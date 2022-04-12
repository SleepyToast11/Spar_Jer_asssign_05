package FOO;

import java.util.Random;

/**
 * Car class starting from Bishops going towards golden
 * that uses shared controllers class semaphore to control flow and bridge to simulate
 * Is going slower than counterpart
 */
public class BishopsBoundCar extends Car implements Runnable{
	/**
	 * name of car for recognition when printed out
	 */
	private String name;
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
	 * getter function for name string variable
	 * @return String of initialised name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * constructs BishopsBoundCar object and sets name to it with passed string
	 * @param name String to remember car by
	 */
	public BishopsBoundCar(String name){
		this.name = name;
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
				Thread.sleep(random.nextInt(10));
				controller.bishopsBound.acquire();
				bridge.putCarOnBridge(this);
				Thread.sleep(125);
				bridge.putCarOffBridge();
				controller.bishopsBound.release();
				if (controller.bishopsBound.availablePermits() == controller.semaphoreNumBishops) {
					controller.bishopsBound.drainPermits();
					controller.goldenBound.release(controller.semaphoreNumGolden);
				}
				thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * if thread variable is null, initializes thread variable and start() it
	 */
	public void start(){
		if (thread == null){
			thread = new Thread(this);
			thread.start();
		}
	}
}


