/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.stopwatch.timer;

import java.util.EventListener;

/**
 * The interface of a ticker listener.
 */
public interface TickListener extends EventListener {

	/**
	 * Called upon receiving the tick event.
	 * 
	 * @param e
	 *            a tick event
	 */
	public void tickOccurred(TickEvent e);
}
