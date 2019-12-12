/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple.action.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.BaseAction;
import ch.bfh.due1.jdt.framework.Clipboard;
import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.due1.jdt.framework.CommandHandlerListener;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.View;
import ch.bfh.due1.jdt.framework.util.EditorAdaptor;
import ch.bfh.due1.jdt.framework.util.ShapeAdaptor;
import ch.bfh.due1.jdt.framework.util.ViewAdaptor;
import ch.bfh.sed.commandpattern.invoker.CutInvoker;

public class CutActionTest {
	/** Mock editor. */
	private class MockEditor extends EditorAdaptor {
		private View view = new MockView();
		private CommandHandler ch = new MockCommandHandler();
		private Clipboard cp = new MockClipboard();
		private boolean checkEditorStateCalled = false;
		@Override
		public List<Shape> getShapes() {
			return this.view.getShapes();
		}
		@Override
		public void addShape(Shape s) {
			this.view.addShape(s);
		}
		@Override
		public boolean removeShape(Shape s) {
			return this.view.removeShape(s);
		}
		@Override
		public Clipboard getClipboard() {
			return this.cp;
		}
		@Override
		public View getCurrentView() {
			return this.view;
		}
		@Override
		public void addToSelection(Shape s) {
			this.view.addToSelection(s);
		}
		@Override
		public void removeFromSelection(Shape s) {
			this.view.removeFromSelection(s);
		}

		@Override
		public List<Shape> getSelection() {
			return this.view.getSelection();
		}
		@Override
		public void clearSelection() {
			this.view.clearSelection();
		}
		@Override
		public CommandHandler getCommandHandler() {
			return ch;
		}
		@Override
		public void checkEditorState() {
			this.checkEditorStateCalled = true;
		}
	}

	/** Mock clip board. */
	private class MockClipboard implements Clipboard {
		private List<Shape> shapes;
		@Override
		public void put(List<Shape> shapes) {
			this.shapes = shapes;
		}
		@Override
		public List<Shape> get() {
			return this.shapes;
		}
		@Override
		public void remove(List<Shape> shapes) {
		}
		@Override
		public void clear() {
			if (this.shapes != null)
				this.shapes.clear();
		}
	}

	/** Mock view. */
	private class MockView extends ViewAdaptor {
		private List<Shape> shapes = new ArrayList<>();
		private List<Shape> selection = new ArrayList<>();
		@Override
		public void addShape(Shape s) {
			this.shapes.add(s);
		}
		@Override
		public boolean removeShape(Shape s) {
			return this.shapes.remove(s);
		}
		@Override
		public List<Shape> getShapes() {
			return this.shapes;
		}
		@Override
		public void addToSelection(Shape s) {
			this.selection.add(s);
		}
		@Override
		public void removeFromSelection(Shape s) {
			this.selection.remove(s);
		}
		@Override
		public List<Shape> getSelection() {
			return this.selection;
		}
		@Override
		public void clearSelection() {
			this.selection.clear();
		}
	}

	/** Mock command handler. */
	private class MockCommandHandler implements CommandHandler {
		private List<Command> commands = new ArrayList<>();
		@Override
		public void addCommand(Command c) {
			this.commands.add(c);
		}
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}
		@Override
		public void undoLast() {
		}
		@Override
		public void redoLast() {
		}
		@Override
		public boolean undoPossible() {
			return !this.commands.isEmpty();
		}
		@Override
		public boolean redoPossible() {
			return false;
		}
		@Override
		public void addCommandHandlerListener(CommandHandlerListener listener) {
		}
		@Override
		public boolean removeCommandHandlerListener(
				CommandHandlerListener listener) {
			return false;
		}
	}

	/** Mock shape. */
	private class MockShape extends ShapeAdaptor {
		@Override
		public Shape cloneMe() {
			return new MockShape();
		}
	}

	/**
	 * Test if newly created 'cut action' is disabled.
	 */
	@Test
	public void testCreateCutAction1() {
		BaseAction a = new CutInvoker();
		assertFalse(a.isEnabled());
	}

	/**
	 * Test if newly created 'cut action' is still disabled
	 * after calling checkAction().
	 */
	@Test
	public void testCreateCutAction2() {
		BaseAction a = new CutInvoker();
		Editor e = new MockEditor();
		a.putValue(BaseAction.JDT_EDITOR, e);
		assertFalse(a.isEnabled());
		a.checkAction();
		assertFalse(a.isEnabled());
	}

	/**
	 * Test if newly created 'cut action' is still disabled when editor
	 * has shapes and after calling checkAction().
	 */
	@Test
	public void testCreateCutAction3() {
		BaseAction a = new CutInvoker();
		Editor e = new MockEditor();
		e.addShape(new ShapeAdaptor());
		a.putValue(BaseAction.JDT_EDITOR, e);
		assertFalse(a.isEnabled());
		a.checkAction();
		assertFalse(a.isEnabled());
	}

	/**
	 * Test if newly created 'cut action' is enabled when editor
	 * has selected shapes and after calling checkAction().
	 */
	@Test
	public void testCreateCutAction4() {
		BaseAction a = new CutInvoker();
		Editor e = new MockEditor();
		Shape s = new ShapeAdaptor();
		e.addShape(s);
		a.putValue(BaseAction.JDT_EDITOR, e);
		assertFalse(a.isEnabled());
		e.addToSelection(s);
		a.checkAction();
		assertTrue(a.isEnabled());
	}

	/**
	 * Tests the conditions after calling actionPerformed().
	 */
	@Test
	public void testActionPerformed() {
		BaseAction a = new CutInvoker();
		Editor e = new MockEditor();
		Shape s = new MockShape();
		e.addShape(s);
		e.addToSelection(s);
		a.putValue(BaseAction.JDT_EDITOR, e);
		a.actionPerformed(null);
		// Let's check if all conditions are true.
		assertEquals(0, e.getShapes().size());
		assertEquals(0, e.getSelection().size());
		CommandHandler ch = e.getCommandHandler();
		assertNotNull(ch);
		assertEquals(true, ch.undoPossible());
		Clipboard cp = e.getClipboard();
		assertNotNull(cp);
		assertEquals(1, cp.get().size());
		assertEquals(true, ((MockEditor) e).checkEditorStateCalled);
	}
}
