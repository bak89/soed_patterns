/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2010
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.framework.util;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeChangedListener;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Vector;


/**
 * Empty shape adaptor class. All methods are not implemented,
 * and throw an unsupported operation exception. Subclasses
 * must override methods as needed.
 */
public class ShapeAdaptor implements Shape {

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
	public BoundingBox getBoundingBox() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void setBoundingBox(BoundingBox r) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void move(Vector delta) {
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
	public void setFillColor(Color fillColor) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void setPenColor(Color penColor) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void setPenSize(int penSize) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public List<ShapeHandle> getShapeHandles() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void addShapeChangedListener(ShapeChangedListener listener) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public boolean removeShapeChangedListener(ShapeChangedListener listener) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public boolean isContainer() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void add(Shape shape) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public boolean remove(Shape shape) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public List<Shape> getShapes() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public Memento createMemento() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void setMemento(Memento m) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public Shape cloneMe() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void setSelected(boolean value) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public boolean isSelected() {
		throw new UnsupportedOperationException();
	}
}
