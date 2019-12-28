/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.stopwatch.timer;

import java.util.EventObject;

/**
 * An event for ticker listeners.
 */
public class TickEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates an event.
	 * 
	 * @param source
	 *            the sender, a timer
	 */
	public TickEvent(Object source) {
		super(source);
	}
}
