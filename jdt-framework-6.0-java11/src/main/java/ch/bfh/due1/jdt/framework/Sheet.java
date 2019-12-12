/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import java.awt.Graphics;
import java.util.Collection;
import java.util.List;


/**
 * A sheet denotes the top-level container for shapes. A sheet manages zero or
 * more shapes.
 * <p>
 * A sheet must notify the {@link ch.bfh.due1.jdt.framework.SheetChangedListener} objects. Objects
 * interested in receiving this notification must implement a
 * {@link ch.bfh.due1.jdt.framework.SheetChangedListener} interface. In addition, they must be registered
 * with a sheet by calling the
 * {@link ch.bfh.due1.jdt.framework.Sheet#addSheetChangedListener(ch.bfh.due1.jdt.framework.SheetChangedListener)} method.
 * <p>
 * A sheet must act as a listener for shapes. Thus, it must register a
 * {@link ch.bfh.due1.jdt.framework.ShapeChangedListener} with every shape added to the sheet.
 * <p>
 * The sheet can be the receiver for {@link ch.bfh.due1.jdt.framework.Command} objects.
 * 
 * @see ch.bfh.due1.jdt.framework.Shape
 * @see ch.bfh.due1.jdt.framework.Command
 * @author Eric Dubuis
 */
public interface Sheet {
	/**
	 * Adds a shape to this sheet.
	 * 
	 * @param s
	 *            The shape.
	 */
	public void addShape(Shape s);

	/**
	 * Removes a shape from this sheet.
	 * 
	 * @param s
	 *            The shape to be removed.
	 * @return True if the given shape was removed.
	 */
	public boolean removeShape(Shape s);

	/**
	 * Returns an ordered list of all shapes of a sheet.
	 * 
	 * @return An ordered list of the shapes of a sheet, or null.
	 */
	public List<Shape> getShapes();

	/**
	 * Returns a sublist of shapes, ordered along the stacking order. The
	 * sublist contains only the shapes given in the collection.
	 * 
	 * @param shapes
	 *            A collection of shapes.
	 * 
	 * @return A list of the shapes of a sheet, ordered along the stacking
	 *         order, or null.
	 */
	public List<Shape> getShapesByStackingOrder(Collection<Shape> shapes);

	/**
	 * Draws all the shapes.
	 * 
	 * @param g
	 *            The graphics context.
	 */
	public void draw(Graphics g);

	/**
	 * Registers a SheetChangedListener instance at this view. The sheet's duty
	 * is to inform this listener whenever the sheet changes its state (shape
	 * count, etc.).
	 * 
	 * @param listener
	 *            The listener to add.
	 */
	public void addSheetChangedListener(SheetChangedListener listener);

	/**
	 * Removes a listener for this sheet.
	 * 
	 * @param listener
	 *            The sheet changed listener.
	 * @return True if the given listener was removed.
	 */
	public boolean removeSheetChangedListener(SheetChangedListener listener);

	/**
	 * Creates a memento and returns it to the caretaker.
	 * @return a memento
	 */
	public Memento createMemento();

	/**
	 * Sets the memento to restore its internal state.
	 */
	public void setMemento(Memento m);
}
