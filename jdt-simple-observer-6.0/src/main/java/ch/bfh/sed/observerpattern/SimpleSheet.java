/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.sed.observerpattern;

import java.awt.Graphics;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeChangedEvent;
import ch.bfh.due1.jdt.framework.ShapeChangedListener;
import ch.bfh.due1.jdt.framework.Sheet;
import ch.bfh.due1.jdt.framework.SheetChangedListener;

/**
 * A first-cut implementation of a model of a sheet. A sheet manages zero or
 * more shapes represented by Shape objects.
 *
 * @author Eric Dubuis
 */
public class SimpleSheet implements Sheet {
	/** The logger. */
	private Logger log = Logger.getLogger(SimpleSheet.class);

	/** The list of shapes of this sheet. */
	private List<Shape> shapes = new LinkedList<Shape>();

	/** My shape listener. */
	private ShapeChangedListener myShapeListener;

	/** The listeners for me. */
	private Set<SheetChangedListener> sheetChangedListeners = new HashSet<SheetChangedListener>();

	/**
	 * Creates a sheet. Creates a shape changed listener that can be registered
	 * with a shape.
	 */
	public SimpleSheet() {

		this.myShapeListener = new ShapeChangedListener() {

			@Override
			public void shapeChanged(ShapeChangedEvent e) {
				log.debug("Shape changed: " + e.getShape());
				// So far, for each shape that sends us an event we notify our
				// sheet listeners.
				// TODO -- notify listeners for this sheet

			}
		};
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void addShape(Shape s) {
		this.shapes.add(s);
		// TODO -- register a listener

		// TODO -- notify our listeners

	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean removeShape(Shape s) {
		boolean rval = this.shapes.remove(s);
		// TODO -- remove registered listener

		// TODO -- notify our listeners

		return rval;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Shape> getShapes() {
		return Collections.unmodifiableList(this.shapes);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Shape> getShapesByStackingOrder(Collection<Shape> shapes) {
		List<Shape> sublist = new LinkedList<Shape>();
		for (Shape s: this.shapes) {
			if (shapes.contains(s)) {
				sublist.add(s);
			}
		}
		return sublist;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void draw(Graphics g) {
		for (Shape s : this.shapes) {
			s.draw(g);
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void addSheetChangedListener(SheetChangedListener listener) {
		this.sheetChangedListeners.add(listener);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean removeSheetChangedListener(SheetChangedListener listener) {
		return this.sheetChangedListeners.remove(listener);
	}

	/**
	 * @inheritDoc
	 *
	 * @see ch.bfh.due1.jdt.framework.View#createMemento()
	 */
	@Override
	public Memento createMemento() {
		throw new UnsupportedOperationException("Method not yet implemented");
	}

	/**
	 * @inheritDoc
	 *
	 * @see ch.bfh.due1.jdt.framework.View#setMemento(Memento)
	 */
	@Override
	public void setMemento(Memento m) {
		throw new UnsupportedOperationException("Method not yet implemented");
	}

	/**
	 * Returns the class name of this sheet implementation.
	 *
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getName();
	}

	/**
	 * Notifies in turn each of the registered listeners.
	 */
	private void notifySheetChangedListeners() {
		// TODO -- implement helper that notifies our listeners

	}
}
