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
 * An instance of this class signals that a Shape object has changed its state
 * (position, size, etc.). The corresponding shape can be obtained with method
 * getShape().
 * <p>
 * Listeners interested in receiving this event must implement the
 * ShapeChangedListener interface.
 * 
 * @see ShapeChangedListener
 * 
 * @author Eric Dubuis
 */
public class ShapeChangedEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2482837780686127999L;

	/**
	 * Creates an instance of this class. Only shapes are allowed to generate this
	 * kind of event.
	 * 
	 * @param source
	 *            The Shape object that changed state.
	 */
	public ShapeChangedEvent(Shape source) {
		super(source);
	}

	/**
	 * Returns the Shape object that changed state.
	 * 
	 * @return The corresponding Shape object.
	 */
	public Shape getShape() {
		return (Shape) getSource();
	}
}
