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
 * A sheet factory creates and returns a sheet. A factory class for a sheet
 * factory returns a factory object implementing this interface.
 * 
 * @author Eric Dubuis
 */
public interface SheetFactory {
	/**
	 * Returns a sheet that can be associated with a view.
	 * 
	 * @return The sheet.
	 */
	public Sheet createSheet();
}
