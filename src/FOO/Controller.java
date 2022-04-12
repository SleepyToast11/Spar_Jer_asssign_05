package FOO;

import java.util.concurrent.Semaphore;

/**
 * shared singleton class to let car objects have access to all semaphores
 */
public class Controller{

	/**
	 * amount of permits bishops bound cars have, to be compared with
	 * availablePermits() method to verify if totality of permits are free
	 */
	public static final int semaphoreNumBishops = 100;
	/**
	 * amount of permits golden bound cars have, to be compared with
	 * availablePermits() method to verify if totality of permits are free
	 */
	public final static int semaphoreNumGolden = 100;
	/**
	 * permits letting golden bound cars go on bridge
	 */
	public static Semaphore goldenBound = new Semaphore(semaphoreNumGolden);
	/**
	 * permits letting bishops bound cars go on bridge
	 */
	public static Semaphore bishopsBound = new Semaphore(semaphoreNumBishops);
	/**
	 * controller singleton varible to hold the unique controller object
	 */
	private static Controller controllerInst = null;

	private Controller(){}
	/**
	 * if controller instance has yet to be created, creates it and return it. Else,
	 * return previously generated controller
	 * @return singleton instance of controller
	 */
	public static Controller getInstance() {
		if (controllerInst == null){
			controllerInst = new Controller();
			goldenBound.drainPermits();}
		return controllerInst;
	}

}