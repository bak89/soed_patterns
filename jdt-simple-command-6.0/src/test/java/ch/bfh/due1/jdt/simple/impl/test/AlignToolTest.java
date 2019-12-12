/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Logger;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.util.EditorAdaptor;
import ch.bfh.due1.jdt.simple.impl.alignment.AlignTool;
import ch.bfh.due1.jdt.simple.impl.alignment.AlignToolUserSelectionDialog;
import ch.bfh.due1.jdt.simple.impl.shape.SimpleBox;

/**
 * Test the align tool and its usage of strategies.
 * 
 * @author Eric Dubuis
 */
public class AlignToolTest {
	/**
	 * This class allows to test class jdt.app.simple.impl.AlingTool by
	 * overwriting method createDialog(). In this class, createDialog() returns
	 * a non-GUI version of a dialog.
	 * 
	 * @author Eric Dubuis
	 */
	private class TestableAlignTool extends AlignTool {

		private AlignToolUserSelectionDialog dialog = null;

		/**
		 * Creates a testable align tool.
		 */
		public TestableAlignTool(Editor e) {
			super(e);
		}

		/**
		 * Returns a non-GUI version of a dialog that allows to be used to test
		 * the real align tool.
		 * 
		 * @see ch.bfh.due1.jdt.simple.impl.alignment.AlignTool#createDialog()
		 */
		@Override
		protected AlignToolUserSelectionDialog createDialog() {
			if (this.dialog == null) {
				this.dialog = new DialogToUse();
			}
			return this.dialog;
		}

		/**
		 * Returns the count of the setVisibleCalledCount variable of the dialog
		 * used for testing purposes.
		 * 
		 * @return the value of setVisibleCalledCount of the used dialog
		 */
		public int getSetVisibleCalledCount() {
			if (this.dialog != null) {
				return ((DialogToUse) this.dialog).getSetVisibleCalledCount();
			} else {
				throw new IllegalStateException("Dialog not initialized");
			}
		}

		/**
		 * Calls super.setAlignBottom() and then calls toolDone() on the editor.
		 * 
		 * @see ch.bfh.due1.jdt.simple.impl.alignment.AlignTool#setAlignBottom()
		 */
		@Override
		public void setAlignBottom() {
			super.setAlignBottom();
			getEditor().toolDone();
		}

		/**
		 * Calls super.setAlignJustifyHorizontal() and then calls toolDone() on
		 * the editor.
		 * 
		 * @see ch.bfh.due1.jdt.simple.impl.alignment.AlignTool#setAlignJustifyHorizontal()
		 */
		@Override
		public void setAlignJustifyHorizontal() {
			super.setAlignJustifyHorizontal();
			getEditor().toolDone();
		}

		/**
		 * Calls super.setAlignJustifyVertical() and then calls toolDone() on
		 * the editor.
		 * 
		 * @see ch.bfh.due1.jdt.simple.impl.alignment.AlignTool#setAlignJustifyVertical()
		 */
		@Override
		public void setAlignJustifyVertical() {
			super.setAlignJustifyVertical();
			getEditor().toolDone();
		}

		/**
		 * Calls super.setAlignLeft() and then calls toolDone() on the editor.
		 * 
		 * @see ch.bfh.due1.jdt.simple.impl.alignment.AlignTool#setAlignLeft()
		 */
		@Override
		public void setAlignLeft() {
			super.setAlignLeft();
			getEditor().toolDone();
		}

		/**
		 * Calls super.setAlignRight() and then calls toolDone() on the editor.
		 * 
		 * @see ch.bfh.due1.jdt.simple.impl.alignment.AlignTool#setAlignRight()
		 */
		@Override
		public void setAlignRight() {
			super.setAlignRight();
			getEditor().toolDone();
		}

		/**
		 * Calls super.setAlignTop() and then calls toolDone() on the editor.
		 * 
		 * @see ch.bfh.due1.jdt.simple.impl.alignment.AlignTool#setAlignTop()
		 */
		@Override
		public void setAlignTop() {
			super.setAlignTop();
			getEditor().toolDone();
		}
	}

	private class DialogToUse implements AlignToolUserSelectionDialog {
		/**
		 * Counter that counts the number of calls of setVisible() methods.
		 * Initialized by zero.
		 */
		private int setVisibleCalledCount = 0;

		/**
		 * Just increments setVisibleCalledCount counter. Used for testing
		 * purposes only.
		 * 
		 * @see ch.bfh.due1.jdt.simple.impl.alignment.AlignToolUserSelectionDialog#setVisible(boolean,
		 *      ch.bfh.due1.jdt.simple.impl.alignment.AlignTool)
		 */
		@Override
		public void setVisible(boolean val, AlignTool alignTool) {
			this.setVisibleCalledCount++;
		}

		/**
		 * Just increments setVisibleCalledCount counter. Used for testing
		 * purposes only.
		 * 
		 * @see ch.bfh.due1.jdt.simple.impl.alignment.AlignToolUserSelectionDialog#setVisible(boolean)
		 */
		@Override
		public void setVisible(boolean val) {
			this.setVisibleCalledCount++;
		}

		/**
		 * Returns the count of the setVisibleCallCount variable.
		 * 
		 * @return the value of the setVisibleCallCount variable
		 */
		public int getSetVisibleCalledCount() {
			return this.setVisibleCalledCount;
		}
	}

	/**
	 * Editor that manages selected shapes only. Can only be used for testing
	 * purposes.
	 * 
	 * @author Eric Dubuis
	 */
	private class MockEditor extends EditorAdaptor {
		/**
		 * The list of selected shapes.
		 */
		private List<Shape> selectedShapes = new ArrayList<Shape>();

		/**
		 * Counter for counting the number of calls of toolDone(). Initialized
		 * by zero.
		 */
		private int toolDoneCount = 0;

		/**
		 * @see ch.bfh.due1.jdt.framework.Editor#addToSelection(ch.bfh.due1.jdt.framework.Shape)
		 */
		@Override
		public void addToSelection(Shape s) {
			this.selectedShapes.add(s);
		}

		/**
		 * Not used.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Editor#clearSelection()
		 */
		@Override
		public void clearSelection() {
			this.selectedShapes.clear();
		}

		/**
		 * Not used.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Editor#getSelection()
		 */
		@Override
		public List<Shape> getSelection() {
			return this.selectedShapes;
		}

		/**
		 * Increments variable toolDoneCount.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Editor#toolDone()
		 */
		@Override
		public void toolDone() {
			this.toolDoneCount++;
		}

		/**
		 * Returns the value of variable toolDoneCount.
		 * 
		 * @return the value of variable toolDoneCount
		 */
		public int getToolDoneCount() {
			return this.toolDoneCount;
		}

		/**
		 * Returns a mock logger.
		 * 
		 * @see ch.bfh.due1.jdt.framework.util.EditorAdaptor#getLogger(java.lang.Class)
		 */
		@Override
		public Logger getLogger(Class<?> logFacility) {
			return new Logger() {

				@Override
				public void debug(String message) {
				}

				@Override
				public void info(String message) {
				}
			};
		}

		/**
		 * Dummy implementation.
		 */
		@Override
		public CommandHandler getCommandHandler() {
			return null;
		}
	}

	/**
	 * Tests if align tool calls toolDone() of the editor given if there are no
	 * selected shapes.
	 */
	@Test
	public void testAlignNoSelectedShapes1() {
		MockEditor e = new MockEditor();
		assertTrue(e.getToolDoneCount() == 0);
		TestableAlignTool atUnderTest = new TestableAlignTool(e);
		atUnderTest.activate();
		// Dialog is not created when there are no selections! Don't use this:
		// assertTrue(atUnderTest.getSetVisibleCalledCount() == 0);
		assertEquals(1, e.getToolDoneCount());
	}

	/**
	 * Tests if align tool calls toolDone() of the editor given if there is one
	 * selected shape only.
	 */
	@Test
	public void testAlignSelectedShapes1() {
		MockEditor e = new MockEditor();
		e.addToSelection(new SimpleBox(0, 0, 10, 10));
		assertTrue(e.getToolDoneCount() == 0);
		TestableAlignTool atUnderTest = new TestableAlignTool(e);
		atUnderTest.activate();
		// Dialog is not created when there are no selections! Don't use this:
		// assertTrue(atUnderTest.getSetVisibleCalledCount() == 0);
		assertEquals(1, e.getToolDoneCount());
	}

	/**
	 * Tests if align tool calls toolDone() of the editor given if there are two
	 * selected shapes.
	 */
	@Test
	public void testAlignSelectedShapes2() {
		MockEditor e = new MockEditor();
		e.addToSelection(new SimpleBox(0, 0, 10, 10));
		e.addToSelection(new SimpleBox(10, 10, 10, 10));
		assertTrue(e.getToolDoneCount() == 0);
		TestableAlignTool atUnderTest = new TestableAlignTool(e);
		atUnderTest.activate();
		assertEquals(0, e.getToolDoneCount());
		assertTrue(atUnderTest.getSetVisibleCalledCount() == 1);
	}

	/**
	 * Tests if align tool calls toolDone() of the editor given if there are two
	 * or more selected shapes after a bottom alignment.
	 */
	@Test
	public void testAlignToBottomSelectedShapes() {
		MockEditor e = new MockEditor();
		e.addToSelection(new SimpleBox(0, 0, 10, 10));
		e.addToSelection(new SimpleBox(10, 10, 10, 10));
		e.addToSelection(new SimpleBox(20, 20, 10, 10));
		assertTrue(e.getToolDoneCount() == 0);
		TestableAlignTool atUnderTest = new TestableAlignTool(e);
		atUnderTest.activate();
		assertEquals(0, e.getToolDoneCount());
		atUnderTest.setAlignBottom();
		assertEquals(1, e.getToolDoneCount());
		assertTrue(atUnderTest.getSetVisibleCalledCount() == 1);
		atUnderTest.deactivate();
		assertTrue(atUnderTest.getSetVisibleCalledCount() == 2);
	}

	/**
	 * Tests if align tool calls toolDone() of the editor given if there are two
	 * or more selected shapes after a top alignment.
	 */
	@Test
	public void testAlignToTopSelectedShapes() {
		MockEditor e = new MockEditor();
		e.addToSelection(new SimpleBox(0, 0, 10, 10));
		e.addToSelection(new SimpleBox(10, 10, 10, 10));
		e.addToSelection(new SimpleBox(20, 20, 10, 10));
		assertTrue(e.getToolDoneCount() == 0);
		TestableAlignTool atUnderTest = new TestableAlignTool(e);
		atUnderTest.activate();
		assertEquals(0, e.getToolDoneCount());
		atUnderTest.setAlignTop();
		assertEquals(1, e.getToolDoneCount());
		assertTrue(atUnderTest.getSetVisibleCalledCount() == 1);
		atUnderTest.deactivate();
		assertTrue(atUnderTest.getSetVisibleCalledCount() == 2);
	}

	/**
	 * Tests if align tool calls toolDone() of the editor given if there are two
	 * or more selected shapes after a left alignment.
	 */
	@Test
	public void testAlignToLeftSelectedShapes() {
		MockEditor e = new MockEditor();
		e.addToSelection(new SimpleBox(0, 0, 10, 10));
		e.addToSelection(new SimpleBox(10, 10, 10, 10));
		e.addToSelection(new SimpleBox(20, 20, 10, 10));
		assertTrue(e.getToolDoneCount() == 0);
		TestableAlignTool atUnderTest = new TestableAlignTool(e);
		atUnderTest.activate();
		assertEquals(0, e.getToolDoneCount());
		atUnderTest.setAlignLeft();
		assertEquals(1, e.getToolDoneCount());
		assertTrue(atUnderTest.getSetVisibleCalledCount() == 1);
		atUnderTest.deactivate();
		assertTrue(atUnderTest.getSetVisibleCalledCount() == 2);
	}

	/**
	 * Tests if align tool calls toolDone() of the editor given if there are two
	 * or more selected shapes after a right alignment.
	 */
	@Test
	public void testAlignToRightSelectedShapes() {
		MockEditor e = new MockEditor();
		e.addToSelection(new SimpleBox(0, 0, 10, 10));
		e.addToSelection(new SimpleBox(10, 10, 10, 10));
		e.addToSelection(new SimpleBox(20, 20, 10, 10));
		assertTrue(e.getToolDoneCount() == 0);
		TestableAlignTool atUnderTest = new TestableAlignTool(e);
		atUnderTest.activate();
		assertEquals(0, e.getToolDoneCount());
		atUnderTest.setAlignRight();
		assertEquals(1, e.getToolDoneCount());
		assertTrue(atUnderTest.getSetVisibleCalledCount() == 1);
		atUnderTest.deactivate();
		assertTrue(atUnderTest.getSetVisibleCalledCount() == 2);
	}

	/**
	 * Tests if align tool calls toolDone() of the editor given if there are two
	 * or more selected shapes after a justify horizontal alignment.
	 */
	@Test
	public void testAlignToJustifyHorizontalSelectedShapes() {
		MockEditor e = new MockEditor();
		e.addToSelection(new SimpleBox(0, 0, 10, 10));
		e.addToSelection(new SimpleBox(10, 10, 10, 10));
		e.addToSelection(new SimpleBox(20, 20, 10, 10));
		assertTrue(e.getToolDoneCount() == 0);
		TestableAlignTool atUnderTest = new TestableAlignTool(e);
		atUnderTest.activate();
		assertEquals(0, e.getToolDoneCount());
		atUnderTest.setAlignJustifyHorizontal();
		assertEquals(1, e.getToolDoneCount());
		assertTrue(atUnderTest.getSetVisibleCalledCount() == 1);
		atUnderTest.deactivate();
		assertTrue(atUnderTest.getSetVisibleCalledCount() == 2);
	}

	/**
	 * Tests if align tool calls toolDone() of the editor given if there are two
	 * or more selected shapes after a justify vertical alignment.
	 */
	@Test
	public void testAlignToJustifyVerticalSelectedShapes() {
		MockEditor e = new MockEditor();
		e.addToSelection(new SimpleBox(0, 0, 10, 10));
		e.addToSelection(new SimpleBox(10, 10, 10, 10));
		e.addToSelection(new SimpleBox(20, 20, 10, 10));
		assertTrue(e.getToolDoneCount() == 0);
		TestableAlignTool atUnderTest = new TestableAlignTool(e);
		atUnderTest.activate();
		assertEquals(0, e.getToolDoneCount());
		atUnderTest.setAlignJustifyVertical();
		assertEquals(1, e.getToolDoneCount());
		assertTrue(atUnderTest.getSetVisibleCalledCount() == 1);
		atUnderTest.deactivate();
		assertTrue(atUnderTest.getSetVisibleCalledCount() == 2);
	}
}
