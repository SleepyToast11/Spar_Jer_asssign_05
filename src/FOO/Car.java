package FOO;

/**
 * car class to be simulated going across bridge class
 */
abstract class Car{

	/**
	 * name of car for recognition when printed out
	 */
	private String name;

	/**
	 * should create a thread and start it if no thread has already been created
	 */
	abstract public void start();

	/**
	 * should implement getter for name variable
	 * @return
	 */
	abstract public String getName();
}