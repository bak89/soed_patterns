/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2010
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.framework.util;

import java.awt.Cursor;
import java.awt.Graphics;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;


/**
 * Almost empty shape handle adaptor class. Only some methods are implemented.
 * Unimplemented methods throw an unsupported operation exception.
 */
public class HandleAdaptor implements ShapeHandle {
	/** The associated shape. */
	private Shape s;
	/** The position of this handle. */
	private Coord pos;

	/**
	 * Constructs a handle having an associated shape.
	 * @param s the associated shape
	 * @param c the position
	 */
	public HandleAdaptor(Shape s, Coord c) {
		this.s = s;
		this.pos = c;
	}

	/**
	 * Returns the associated shape.
	 * @return a shape
	 */
	@Override
	public Shape getOwner() {
		return this.s;
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public BoundingBox getBounds() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the position.
	 * @return the position
	 */
	@Override
	public Coord getPosition() {
		return this.pos;
	}

	/**
	 * Sets the position.
	 * @param p a position
	 */
	@Override
	public void setPosition(Coord p) {
		this.pos = p;
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void draw(Graphics g) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public Cursor getCursor() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public boolean contains(Coord c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void startInteraction(Coord c, KeyModifier k) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void dragInteraction(Coord c, KeyModifier k) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void stopInteraction(Coord c, KeyModifier k) {
		throw new UnsupportedOperationException();
	}
}
