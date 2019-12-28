/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.stopwatch.core;

/**
 * Abstraction of a display of a stop watch.
 */
public interface Display {

	/**
	 * Resets the display to zero.
	 */
	public void displayReset();

	/**
	 * Sets the display to show the running time.
	 */
	public void displayRunningTime();

	/**
	 * Sets the display to show the intermediate time.
	 */
	public void displayIntermediateTime();

	/**
	 * Sets the display to show the final time.
	 */
	public void displayFinalTime();

	/**
	 * Sets the display into its blinking state.
	 */
	public void doBlinking();

	/**
	 * Sets the display into its normal (non-blinking) state.
	 */
	public void stopBlinking();
}
