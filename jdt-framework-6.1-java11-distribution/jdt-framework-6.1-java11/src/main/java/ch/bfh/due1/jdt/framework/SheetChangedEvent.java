/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import java.util.EventObject;


/**
 * An instance of this class signals that a Sheet object has changed its state
 * (new shape, etc.). The corresponding sheet can be obtained with method
 * getSheet().
 * <p>
 * Listeners interested in receiving this event must implement the
 * SheetChangedListener interface.
 * 
 * @see SheetChangedListener
 * 
 * @author Eric Dubuis
 */
public class SheetChangedEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7671853298303781763L;

	/**
	 * Creates an instance of this class.
	 * 
	 * @param source
	 *            The Sheet object that changed state.
	 */
	public SheetChangedEvent(Sheet source) {
		super(source);
	}

	/**
	 * Returns the Sheet object that changed state.
	 * 
	 * @return The corresponding Sheet object.
	 */
	public Sheet getSheet() {
		return (Sheet) getSource();
	}
}
