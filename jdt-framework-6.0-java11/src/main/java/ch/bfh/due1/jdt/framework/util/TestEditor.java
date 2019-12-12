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
import java.util.ArrayList;
import java.util.List;

import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.due1.jdt.framework.CommandHandlerEvent;
import ch.bfh.due1.jdt.framework.CommandHandlerListener;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;


/**
 * Helper class that simulates a simple editor.
 */
public class TestEditor extends EditorAdaptor {
	private List<Shape> shapes = new ArrayList<Shape>();
	private List<Shape> selectedShapes = new ArrayList<Shape>();
	private List<ShapeHandle> handles = new ArrayList<ShapeHandle>();
	private CommandHandler commandHandler = new TestCommandHandler();

	/**
	 * Implements a listener for the command handler in place. Calls
	 * checkEditorState() upon notification.
	 *
	 * @author Eric Dubuis
	 */
	private class CommandHandlerListenerImpl implements CommandHandlerListener {

		@Override
		public void commandHandlerChanged(CommandHandlerEvent event) {
			checkEditorState();
		}
	}

	public TestEditor() {
		this.commandHandler.addCommandHandlerListener(
				new CommandHandlerListenerImpl());
	}

	@Override
	public void addShape(Shape s) {
		this.shapes.add(s);
	}

	@Override
	public void addToSelection(Shape s) {
		this.selectedShapes.add(s);
		this.handles.addAll(s.getShapeHandles());
	}

	@Override
	public void clearSelection() {
		this.selectedShapes.clear();
		this.handles.clear();
	}

	@Override
	public List<Shape> getSelection() {
		return this.selectedShapes;
	}

	@Override
	public List<ShapeHandle> getSelectionHandles() {
		return this.handles;
	}

	@Override
	public List<Shape> getShapes() {
		return this.shapes;
	}

	@Override
	public void removeFromSelection(Shape s) {
		this.selectedShapes.remove(s);
		this.handles.removeAll(s.getShapeHandles());
	}

	@Override
	public boolean removeShape(Shape s) {
		return this.shapes.remove(s);
	}

	@Override
	public CommandHandler getCommandHandler() {
		return this.commandHandler ;
	}

	@Override
	public void checkEditorState() {
		// intentionally left empty
	}

	@Override
	public void setCursor(Cursor c) {
		// intentionally left empty
	}
}
