/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.impl.alignment;


/**
 * Presents an abstraction of a user a dialog for the align tool. Can be made
 * visible or invisible.
 * 
 * @author Eric Dubuis
 */
public interface AlignToolUserSelectionDialog {
	/**
	 * If the given boolean argument is true then makes the dialog visible, else
	 * makes the dialog invisible. The align tool argument associates the dialog
	 * with the user dialog
	 * 
	 * @param val
	 *            the visibility
	 * @param alignTool
	 *            the associated align tool
	 */
	public void setVisible(boolean val, AlignTool alignTool);

	/**
	 * Makes associated user selection dialog visible, if given argument is
	 * true, and not visible otherwise.
	 * 
	 * @param val
	 *            the visibility
	 */
	public void setVisible(boolean val);
}
