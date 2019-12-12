/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2011
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.selection;


/**
 * The abstraction of a factory for creating state objects for a selection tool.
 * notice, however, that while different factories can be provided for the
 * creation of state objects. the set of types of state classes is fixed with
 * this approach.
 */
public interface StateFactory {

	/**
	 * Creates and returns the state object used while the user drags on the
	 * area of a sheet.
	 * 
	 * @param context
	 *            the selection tool
	 * @return a state object
	 */
	public SelectionToolState createDragAreaState(SelectionTool context);

	/**
	 * Creates and returns the state object used while the user drags on a shape
	 * handle.
	 * 
	 * @param context
	 *            the selection tool
	 * @return a state object
	 */
	public SelectionToolState createDragHandleState(SelectionTool context);

	/**
	 * Creates and returns the state object used while the user starts using the
	 * selection tool.
	 * 
	 * @param context
	 *            the selection tool
	 * @return a state object
	 */
	public SelectionToolState createInitState(SelectionTool context);

	/**
	 * Creates and returns the state object used while the user moves one or
	 * more shapes.
	 * 
	 * @param context
	 *            the selection tool
	 * @return a state object
	 */
	public SelectionToolState createMovingState(SelectionTool context);
}
