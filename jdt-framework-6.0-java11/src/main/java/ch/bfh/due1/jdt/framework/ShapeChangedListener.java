/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import java.util.EventListener;


/**
 * Interface that defines a listener for ShapeChangedEvent objects.
 * Such an event is generated whenever a shape changes its state.
 * 
 * @see ShapeChangedEvent
 * 
 * @author Eric Dubuis
 */
public interface ShapeChangedListener extends EventListener {
	/**
	 * Called when a shape has changed its state (position, size, ...).
	 * 
	 * @param e
	 *            The event object denoting the source of the event.
	 */
	public void shapeChanged(ShapeChangedEvent e);
}
