/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;


/**
 * A tool defines a mode of the drawing view. All input events targeted to the
 * current view are forwarded to its current tool. Examples are: a selection
 * tool and a tool for creating a box-like shape. Tools are created once and
 * reused. They are initialized/de-initialized with activate()/deactivate().
 * <p>
 * Tools inform their editor when they are done with an interaction by calling
 * the editor's toolDone() method.
 * 
 * @author Eric Dubuis
 */
public interface Tool {
	/**
	 * Activates the tool for the given view. This method is called whenever the
	 * user switches to this tool. Use this method to reinitialize a tool.
	 */
	public void activate();

	/**
	 * Deactivates the tool. This method is called whenever the user switches to
	 * another tool. Use this method to do some clean-up when the tool is
	 * switched. Subclasses should always call super.deactivate.
	 */
	public void deactivate();

	/**
	 * Handles mouse down events in the drawing view.
	 * 
	 * @param c
	 *            The coordinate of mouse position.
	 * @param k
	 *            The key modifier corresponding to the one contained in the
	 *            original mouse event.
	 */
	public void mouseDown(Coord c, KeyModifier k);

	/**
	 * Handles mouse-drag events in the view.
	 * 
	 * @param c
	 *            The coordinate of mouse position.
	 * @param k
	 *            The key modifier corresponding to the one contained in the
	 *            original mouse event.
	 */
	public void mouseDrag(Coord c, KeyModifier k);

	/**
	 * Handles mouse up in the view.
	 * 
	 * @param c
	 *            The coordinate of mouse position.
	 * @param k
	 *            The key modifier corresponding to the one contained in the
	 *            original mouse event.
	 */
	public void mouseUp(Coord c, KeyModifier k);

	/**
	 * Handles mouse being over a shape or handle in the view.
	 * 
	 * @param c
	 *            The coordinate of mouse position.
	 * @param k
	 *            The key modifier corresponding to the one contained in the
	 *            original mouse event.
	 */
	public void mouseOver(Coord c, KeyModifier k);
}
