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
import java.util.List;

import ch.bfh.due1.jdt.framework.Clipboard;
import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Logger;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Tool;
import ch.bfh.due1.jdt.framework.View;


/**
 * Empty editor adaptor class. All methods are not implemented,
 * and throw an unsupported operation exception.
 */
public class EditorAdaptor implements Editor {

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void createView() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public View getCurrentView() {
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
	public List<Shape> getShapes() {
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
	public void showStatus(String msg) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void toolDone() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void checkEditorState() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public CommandHandler getCommandHandler() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public Logger getLogger(Class<?> logFacility) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public Clipboard getClipboard() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void registerView(View view) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public int getViewCount() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void setCurrentView(int ith) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public View getIthView(int ith) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void registerDefaultTool(Tool t) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unimplemented method.
	 * @throws UnsupportedOperationException if called
	 */
	@Override
	public void registerTool(Tool t) {
		throw new UnsupportedOperationException();
	}
}
