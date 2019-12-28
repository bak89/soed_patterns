/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.stopwatch.timer;

/**
 * Defines how a ticker listener must register with a timer implementation.
 */
public interface Ticker {
	/**
	 * Registers a ticker listener
	 * 
	 * @param l
	 *            a ticker listener
	 */
	public abstract void addTickListener(TickListener l);

	/**
	 * Removes a previously registered ticker listener
	 * 
	 * @param l
	 *            a ticker listener
	 */
	public abstract void removeTickListener(TickListener l);

}
