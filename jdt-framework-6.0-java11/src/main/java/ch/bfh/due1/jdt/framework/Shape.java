/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;


/**
 * This interface denotes the common type of any drawable shape.
 * <p>
 * A shape must notify the {@link ch.bfh.due1.jdt.framework.ShapeChangedListener} objects. Objects
 * interested in receiving this notification must implement a
 * {@link ch.bfh.due1.jdt.framework.ShapeChangedListener} interface. In addition, they must be registered
 * with a shape by calling the
 * {@link ch.bfh.due1.jdt.framework.Shape#addShapeChangedListener(ch.bfh.due1.jdt.framework.ShapeChangedListener)} method.
 * <p>
 * A specially constructed Shape class may act as a container. Then, this
 * interface can be regarded as the abstract component of the composite pattern.
 * The container shape then is the composite, and concrete and elementary shapes
 * are the concrete components.
 * 
 * @author Eric Dubuis
 */
public interface Shape {

	/**
	 * Draws an implementation of this interface, i.e., a real shape, into the
	 * given Graphics context.
	 * 
	 * @param g
	 *            A Graphics context.
	 */
	public abstract void draw(Graphics g);

	/**
	 * Returns the bounding box of the shape.
	 * 
	 * @return The bounding box.
	 */
	public BoundingBox getBoundingBox();

	/**
	 * Sets the bounding box of the shape. The shape has to adjust its size and
	 * position when this method is called, and registered shape listeners have
	 * to be notified.
	 * 
	 * @param r
	 *            The new size of the bounding box.
	 */
	public void setBoundingBox(BoundingBox r);

	/**
	 * Moves an implementation of this interface along the vector (deltaX,
	 * deltaY).
	 * 
	 * @param delta
	 *            The vector along which the shape is moved.
	 */
	public abstract void move(Vector delta);

	/**
	 * Tests whether the mouse coordinate is contained in the shape. Called
	 * when the mouse is pressed in a view in order to decide which shape has to
	 * be selected.
	 * 
	 * @param c
	 *            The coordinate of the mouse position.
	 * @return True if coordinates are contained in the shape, false otherwise.
	 */
	public boolean contains(Coord c);

	/**
	 * Sets the fill color. If the shape has no area then this method has no
	 * effect.
	 * 
	 * @param fillColor
	 *            The fill color to set. Can be null which means transparency.
	 */
	public void setFillColor(Color fillColor);

	/**
	 * Sets the pen color.
	 * 
	 * @param penColor
	 *            The pen color to set. Can be null.
	 */
	public abstract void setPenColor(Color penColor);

	/**
	 * Sets the pen size.
	 * 
	 * @param penSize
	 *            The pen size to set. If the value given is less than 2 then a
	 *            value of 2 is set.
	 */
	public abstract void setPenSize(int penSize);

	/**
	 * Returns a list of shape handles. A shape handle is used to manipulate a
	 * shape. If the shape does not support shape handles, null may be returned
	 * as result.
	 * 
	 * @return A list of shape handles (may be null if shape handles are not
	 *         supported).
	 */
	public List<ShapeHandle> getShapeHandles();

	/**
	 * Registers a ShapeChangedListener instance at this shape. The shape's duty
	 * is to inform this listener whenever the shape changes its state
	 * (position, size, etc.).
	 * 
	 * @param listener
	 *            The listener to add.
	 */
	public void addShapeChangedListener(ShapeChangedListener listener);

	/**
	 * Removes a ShapeChangedListener instance at this shape.
	 * 
	 * @param listener
	 *            The listener to remove.
	 */
	public boolean removeShapeChangedListener(ShapeChangedListener listener);

	/**
	 * Tells whether this shape is a container for other shapes.
	 * 
	 * @return True if this shape is a container for other shapes, false
	 *         otherwise.
	 */
	public boolean isContainer();

	/**
	 * If this shape is a container for shapes then add the given shape else do
	 * nothing.
	 * 
	 * @param shape
	 *            The shape to be added.
	 */
	public void add(Shape shape);

	/**
	 * If this shape is a container for shapes then remove the given shape else
	 * do nothing.
	 * 
	 * @param shape
	 *            The shape to be removed.
	 * @return True if the shape was removed, false otherwise.
	 */
	public boolean remove(Shape shape);

	/**
	 * If this shape is a container for shapes the return the list of child
	 * shapes else throw an unsupported operation exception.
	 * 
	 * @return The list of child shapes or throw an exception.
	 */
	public List<Shape> getShapes();

	/**
	 * Creates a memento and returns it to the caretaker.
	 *
	 * @return a memento
	 */
	public Memento createMemento();

	/**
	 * Sets the memento to restore its internal state.
	 */
	public void setMemento(Memento m);

	/**
	 * Provides a deep copy of this shape. The shape itself will not be
	 * associated with a sheet.
	 * 
	 * @return a copy of myself
	 */
	public Shape cloneMe();

	/**
	 * Tells a shape whether it is selected or not. If selected,
	 * shape must draw its handles.
	 *
	 * @param value if true then shape is selected, false otherwise
	 */
	public void setSelected(boolean value);

	/**
	 * Tells caller if shape is selected.
	 *
	 * @return true if selected, false otherwise
	 */
	public boolean isSelected();
}
