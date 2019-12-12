/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;


/**
 * A view factory creates and returns a view.
 * 
 * @author Eric Dubuis
 */
public interface ViewFactory {
	/**
	 * Creates and returns a view.
	 * 
	 * @param e
	 *            The associated editor.
	 * 
	 * @return A view.
	 */
	public View createView(Editor e);
}
