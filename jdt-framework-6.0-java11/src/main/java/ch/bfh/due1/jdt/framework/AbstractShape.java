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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implements the pen, pen size and fill color capabilities
 * as well as the handling of shape changed listeners. This
 * abstract class can be used as base class for many real
 * shape classes. It should, however, not be used for
 * shapes that do not have a background color (e.g., shape
 * groups, perhaps one-dimensional shapes such as lines).
 * 
 * @author Eric Dubuis
 */
public abstract class AbstractShape implements Shape {
	/** The fill color of a shape. */
	protected Color fillColor;

	/** The pen color of a shape. */
	protected Color penColor;

	/** The pen size in pixel. */
	protected int penSize;

	/** The set of listeners for ShapeChangeEvent notifications. */
	private Set<ShapeChangedListener> shapeChangedListeners = new HashSet<ShapeChangedListener>();

	/** If true then shape is selected, false otherwise. */
	protected boolean selected = false;
	/**
	 * Sets the default values for all shapes.
	 */
	protected AbstractShape() {
		this.penSize = 2;
		this.penColor = Color.RED;
		this.fillColor = Color.LIGHT_GRAY;
	}

	/**
	 * Sets the values provided upon constructing a shape.
	 * 
	 * @param fillColor
	 *            The fill color to use.
	 * @param penColor
	 *            The pen color to use.
	 * @param penSize
	 *            The pen size to use. If the value given is less than 2 then a
	 *            value of 2 is set.
	 */
	protected AbstractShape(Color fillColor, Color penColor, int penSize) {
		super();
		this.fillColor = fillColor;
		this.penColor = penColor;
		this.penSize = penSize;
		if (penSize < 2) {
			this.penSize = 2;
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public final void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public final void setPenColor(Color penColor) {
		this.penColor = penColor;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public final void setPenSize(int penSize) {
		this.penSize = penSize;
		if (penSize < 2) {
			this.penSize = 2;
		}
	}

	/**
	 * By default, a shape of this base type is not a container.
	 * 
	 * @return Always false.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Shape#isContainer()
	 */
	@Override
	public boolean isContainer() {
		return false;
	}

	/**
	 * By default, a shape of this type is not a container, hence this method is not
	 * supported by default.
	 * 
	 * @param shape
	 *            A shape.
	 * @throws UnsupportedOperationException
	 *             to signal that, by default, a shape of this type is not a container
	 * @see ch.bfh.due1.jdt.framework.Shape#add(ch.bfh.due1.jdt.framework.Shape)
	 */
	@Override
	public void add(Shape shape) {
		throw new UnsupportedOperationException();
	}

	/**
	 * By default, a shape of this type is not a container, hence this method is not
	 * supported by default.
	 * 
	 * @param shape
	 *            A shape.
	 * @return throws UnsupportedException, see below
	 * @throws UnsupportedOperationException
	 *             to signal that, by default, a shape of this type is not a container
	 * @see ch.bfh.due1.jdt.framework.Shape#remove(ch.bfh.due1.jdt.framework.Shape)
	 */
	@Override
	public boolean remove(Shape shape) {
		throw new UnsupportedOperationException();
	}

	/**
	 * By default, a shape of this type is not a container, hence this method is not
	 * supported by default.
	 *
	 * @return an empty list
	 * @throws UnsupportedOperationException
	 *             to signal that, by default, a shape of this type is not a container
	 * @see ch.bfh.due1.jdt.framework.Shape#getShapes()
	 */
	@Override
	public List<Shape> getShapes() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the fill color.
	 * 
	 * @return a color
	 */
	public final Color getFillColor() {
		return this.fillColor;
	}

	/**
	 * Returns the pen color.
	 * 
	 * @return a color
	 */
	public final Color getPenColor() {
		return this.penColor;
	}

	/**
	 * Returns the pen size.
	 * 
	 * @return a size
	 */
	public final int getPenSize() {
		return this.penSize;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public final void setSelected(boolean value) {
		this.selected = value;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public final boolean isSelected() {
		return this.selected;
	}

	/**
	 * Draws the (abstract) shape by calling the concrete
	 * shape's doDrawShape() method and then by drawing the
	 * shape handles, if the shape is selected.
	 */
	@Override
	public final void draw(Graphics g) {
		doDrawShape(g);
		if (isSelected()) {
			drawShapeHandles(g);
		}
	}

	/**
	 * Draws shape handles.
	 *
	 * @param g the graphics context.
	 */
	private final void drawShapeHandles(Graphics g) {
		for (ShapeHandle sh : getShapeHandles()) {
			sh.draw(g);
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public final void addShapeChangedListener(ShapeChangedListener listener) {
		this.shapeChangedListeners.add(listener);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public final boolean removeShapeChangedListener(ShapeChangedListener listener) {
		return this.shapeChangedListeners.remove(listener);
	}

	/**
	 * Notifies all registered shape changed listeners. Called by subclasses
	 * upon changes of a shape.
	 */
	protected final void notifyShapeChangedListeners() {
		ShapeChangedEvent e = new ShapeChangedEvent(this);
		for (ShapeChangedListener l : this.shapeChangedListeners) {
			l.shapeChanged(e);
		}
	}

	/**
	 * Draws the concrete shape; must be implemented
	 * by concrete shape classes.
	 *
	 * @param g the graphics context.
	 */
	protected abstract void doDrawShape(Graphics g);
}
