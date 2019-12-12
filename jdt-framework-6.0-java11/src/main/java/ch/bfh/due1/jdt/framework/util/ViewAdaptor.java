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
import java.util.Collection;
import java.util.List;

import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Sheet;
import ch.bfh.due1.jdt.framework.Tool;
import ch.bfh.due1.jdt.framework.View;


/**
 * Empty view adaptor class. All methods are not implemented,
 * and throw an unsupported operation exception.
 */
public class ViewAdaptor implements View {

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void setSheet(Sheet s) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public Editor getEditor() {
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
	public void setEditor(Editor editor) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public Tool getTool() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void setTool(Tool tool) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void addShape(Shape s) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public boolean removeShape(Shape s) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void addToSelection(Shape s) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void removeFromSelection(Shape s) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void clearSelection() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public List<Shape> getSelection() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public List<Shape> getShapesByStackingOrder(Collection<Shape> shapes) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public List<ShapeHandle> getSelectionHandles() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void setCursor(Cursor c) {
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
}
