/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework.util;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.apache.log4j.Logger;

import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.Sheet;
import ch.bfh.due1.jdt.framework.SheetChangedListener;

/**
 * A dummy implementation of a model of a sheet. Beside logging this sheet does
 * nothing.
 * 
 * @author Eric Dubuis
 */
public class DefaultSheet implements Sheet {
	/** The logger for this class, named "jdt.framework.DefaultSheet". */
	private Logger log = Logger.getLogger(DefaultSheet.class);

	/**
	 * Creates a sheet.
	 */
	public DefaultSheet() {
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void addShape(Shape s) {
		log.warn("Method addShape() not implemented.");
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean removeShape(Shape s) {
		log.warn("Method removeShape() not implemented.");
		return false;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Shape> getShapes() {
		log.warn("Method getShapes() not implemented.");
		return new ArrayList<Shape>();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Shape> getShapesByStackingOrder(Collection<Shape> shapes) {
		log.warn("Method getShapesByStackingOrder() not implemented.");
		return new ArrayList<Shape>();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void draw(Graphics g) {
		log.warn("Method draw() not implemented.");
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void addSheetChangedListener(SheetChangedListener listener) {
		log.warn("Method addSheetChangedListener() not implemented.");
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean removeSheetChangedListener(SheetChangedListener listener) {
		log.warn("Method removeSheetChangedListener() not implemented.");
		return false;
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.View#createMemento()
	 */
	@Override
	public Memento createMemento() {
		log.warn("Method createMemento() not implemented.");
		return null;
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.View#setMemento(ch.bfh.due1.jdt.framework.Memento)
	 */
	@Override
	public void setMemento(Memento m) {
		log.warn("Method setMemento() not implemented.");
	}

	/**
	 * Returns the class name of this sheet implementation.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
