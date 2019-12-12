/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import java.awt.Cursor;
import java.awt.Graphics;


/**
 * Handles are used to change a shape by direct manipulation. Handles know their
 * owning shape and they provide methods to ask the handle's bounds and to track
 * changes. A handle implements a particular strategy for resizing the
 * associated shape.
 * 
 * @see Shape
 * 
 * @author Eric Dubuis
 */
public interface ShapeHandle {
	/**
	 * Gets the handle's owner.
	 * 
	 * @return The shape which owns the handle.
	 */
	public Shape getOwner();

	/**
	 * Gets the bounding box of the handle. This result depends on the current
	 * position of the shape.
	 * 
	 * @return The bounds of the handle.
	 */
	public BoundingBox getBounds();

	/**
	 * Returns the center of the shape handle.
	 * 
	 * @return The shape handle's center.
	 */
	public Coord getPosition();

	/**
	 * Sets the new position of the shape handle. It is assumed that the shape
	 * handle adjusts its related shape, too.
	 * 
	 * @param p
	 *            The shape handle's new position.
	 */
	public void setPosition(Coord p);

	/**
	 * Draws this handle.
	 * 
	 * @param g
	 *            The graphics context.
	 */
	public void draw(Graphics g);

	/**
	 * Returns a cursor which should be displayed when the mouse is over the
	 * handle. Signals the type of operation which can be performed using this
	 * handle.
	 * <P>
	 * A default implementation may return Cursor.getDefaultCursor().
	 * 
	 * @return The handle's cursor.
	 */
	public Cursor getCursor();

	/**
	 * Tests if a point is contained in the handle.
	 * 
	 * @param c
	 *            The coordinate to test.
	 * @return True if coordinates are contained in the handle, false otherwise.
	 */
	public boolean contains(Coord c);

	/**
	 * Tracks the start of an interaction. Usually, the position where an
	 * interaction starts is stored.
	 * 
	 * @param c
	 *            The coordinate where the interaction started.
	 * @param k
	 *            The key modifier.
	 */
	public void startInteraction(Coord c, KeyModifier k);

	/**
	 * Tracks the interaction.
	 * 
	 * @param c
	 *            The current position of the interaction.
	 * @param k
	 *            The key modifier.
	 */
	public void dragInteraction(Coord c, KeyModifier k);

	/**
	 * Tracks the end of a running interaction.
	 * 
	 * @param c
	 *            The coordinate where the interaction started.
	 * @param k
	 *            The key modifier.
	 */
	public void stopInteraction(Coord c, KeyModifier k);
}
