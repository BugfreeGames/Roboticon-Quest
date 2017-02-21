/*
	www-users.york.ac.uk/~jwa509/Ass3/RoboticonColony.jar
	No changes made
 */
package io.github.teamfractal.exception;

public class NotEnoughMoneyException extends RuntimeException {
	/**
	 * Constructs an <code>NotEnoughMoneyException</code> with no
	 * detail message.
	 */
	public NotEnoughMoneyException() {
		super();
	}

	public NotEnoughMoneyException(int required, int actual) {
		super("Not enough money (Player). \n" +
				"Required: " + String.valueOf(required) + ", \n" +
				"Actual  : " + String.valueOf(actual));
	}

	public NotEnoughMoneyException(String details, int required, int actual) {
		super("Not enough money (Player). \n" +
				"Required: " + String.valueOf(required) + ", \n" +
				"Actual  : " + String.valueOf(actual) + " \n" +
				"Details : " + details);
	}
}
