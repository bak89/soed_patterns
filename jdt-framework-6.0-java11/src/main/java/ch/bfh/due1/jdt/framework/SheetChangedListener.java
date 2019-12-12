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
 * Interface that defines a listener for SheetChangedEvent objects.
 * Such an event is generated whenever a sheet changes its state.
 * 
 * @see SheetChangedEvent
 * 
 * @author Eric Dubuis
 */
public interface SheetChangedListener extends EventListener {
	/**
	 * Called when a sheet has changed its state (shape count, ...).
	 * 
	 * @param e
	 *            The event object denoting the source of the event.
	 */
	public void sheetChanged(SheetChangedEvent e);
}
