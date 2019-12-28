/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.stopwatch.core;

/**
 * The interface for a timer.
 */
public interface Timer {

	/**
	 * Resets the timer.
	 */
	public abstract void timerReset();

	/**
	 * Starts the timer, of, if already started, continues the timer.
	 */
	public abstract void timerStartContinue();

	/**
	 * Stops the timer.
	 */
	public abstract void timerStop();

}