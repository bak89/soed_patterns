/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import static org.junit.Assert.assertTrue;

import java.awt.Cursor;
import java.util.Collection;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.due1.jdt.framework.CommandHandlerListener;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.framework.Logger;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeChangedEvent;
import ch.bfh.due1.jdt.framework.ShapeChangedListener;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Sheet;
import ch.bfh.due1.jdt.framework.Tool;
import ch.bfh.due1.jdt.framework.ToolFactory;
import ch.bfh.due1.jdt.framework.Vector;
import ch.bfh.due1.jdt.framework.View;
import ch.bfh.due1.jdt.framework.util.DefaultSheet;
import ch.bfh.due1.jdt.framework.util.EditorAdaptor;
import ch.bfh.due1.jdt.framework.util.ToolFactoriesBuilder;

/**
 * This class tries to test some generic features of as many Shape classes as
 * possible. It uses the jdt.app.simple.ToolFactoriesBuilder class to obtain
 * the shape creation factories. From each those factories in turn, it gets a
 * corresponding shape and executes the tests on the it.
 *
 * @author Eric Dubuis
 */
public class ShapeTest {
	private static List<ToolFactory> toolFactories;

	private int count;

	/**
	 * Initializes the factory that bootstraps the tool factories.
	 *
	 * @throws Exception
	 *             If the factory cannot be initialized.
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		ToolFactoriesBuilder tfFactory = new ToolFactoriesBuilder();
		toolFactories = tfFactory.getOtherToolFactories();
	}

	private void createShape(Tool tool) {
		tool.activate();
		tool.mouseDown(new Coord(0, 0), KeyModifier.NONE);
		tool.mouseDrag(new Coord(10, 10), KeyModifier.NONE);
		tool.mouseUp(new Coord(20, 20), KeyModifier.NONE);
		tool.deactivate();
	}

	private class TestShapeChangedListener implements ShapeChangedListener {
		@Override
		public void shapeChanged(ShapeChangedEvent e) {
			ShapeTest.this.count++;
		}
	}

	/**
	 * Mock class that implements editor interface.
	 * Some method are called, though, their
	 * functionality, however, are not used.
	 */
	private static class DummyEditor extends EditorAdaptor {
		private Shape recentlyAddedShape;
		private View view;

		public DummyEditor(View v) {
			this.view = v;
		}

		@Override
		public View getCurrentView() {
			return this.view;
		}

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

		@Override
		public void addShape(Shape s) {
			this.recentlyAddedShape = s;
		}

		/**
		 * Returns recently added shape.
		 */
		public Shape getRecentlyAddedShape() {
			return this.recentlyAddedShape;
		}

		/**
		 * Returns a dummy command handler.
		 *
		 * @see ch.bfh.due1.jdt.framework.Editor#getCommandHandler()
		 */
		@Override
		public CommandHandler getCommandHandler() {
			CommandHandler ch = new CommandHandler() {

				@Override
				public void addCommand(Command c) {
				}

				@Override
				public void clear() {
				}

				@Override
				public void redoLast() {
				}

				@Override
				public boolean redoPossible() {
					return false;
				}

				@Override
				public void undoLast() {
				}

				@Override
				public boolean undoPossible() {
					return false;
				}

				@Override
				public void addCommandHandlerListener(
						CommandHandlerListener listener) {
				}

				@Override
				public boolean removeCommandHandlerListener(
						CommandHandlerListener listener) {
					return false;
				}
			};
			return ch;
		}

		/**
		 * Empty implementation.
		 */
		@Override
		public void clearSelection() {
			// Functionality not needed.
		}

		/**
		 * Empty implementation.
		 */
		@Override
		public List<Shape> getSelection() {
			// Functionality not needed.
			return null;
		}

		/**
		 * Empty implementation.
		 */
		@Override
		public void toolDone() {
			// Functionality not needed.
		}
	}

	/**
	 * Mock class that implements the view interface.
	 */
	private static class DummyView implements View {
		private Sheet sheet;

		public DummyView(Sheet s) {
			this.sheet = s;
		}

		@Override
		public void addToSelection(ch.bfh.due1.jdt.framework.Shape s) {
		}

		@Override
		public void clearSelection() {
		}

		@Override
		public List<Shape> getSelection() {
			return null;
		}

		@Override
		public List<ShapeHandle> getSelectionHandles() {
			return null;
		}

		@Override
		public Editor getEditor() {
			return null;
		}

		public Sheet getSheet() {
			return this.sheet;
		}

		@Override
		public Tool getTool() {
			return null;
		}

		@Override
		public void removeFromSelection(ch.bfh.due1.jdt.framework.Shape s) {
		}

		@Override
		public void setCursor(Cursor c) {
		}

		@Override
		public void setEditor(Editor editor) {
		}

		@Override
		public void setSheet(Sheet s) {
		}

		@Override
		public void setTool(Tool tool) {
		}

		@Override
		public void addShape(Shape s) {
			getSheet().addShape(s);
		}

		@Override
		public List<Shape> getShapes() {
			return getSheet().getShapes();
		}

		@Override
		public List<Shape> getShapesByStackingOrder(Collection<Shape> shapes) {
			return getSheet().getShapesByStackingOrder(shapes);
		}

		@Override
		public boolean removeShape(Shape s) {
			return getSheet().removeShape(s);
		}

		/**
		 * @inheritDoc
		 *
		 * @see ch.bfh.due1.jdt.framework.View#createMemento()
		 */
		@Override
		public Memento createMemento() {
			throw new UnsupportedOperationException(
					"Method not yet implemented");
		}

		/**
		 * @inheritDoc
		 *
		 * @see ch.bfh.due1.jdt.framework.View#setMemento(ch.bfh.due1.jdt.framework.Memento)
		 */
		@Override
		public void setMemento(Memento m) {
			throw new UnsupportedOperationException(
					"Method not yet implemented");
		}
	}

	/**
	 * Tests whether a shape modification method notifies a registered listener.
	 */
	@Test
	public void testNotification1() {
		for (ToolFactory factory : toolFactories) {
			DummyEditor editor = new DummyEditor(new DummyView(
					new DefaultSheet()));
			Tool tool = factory.getTool(editor);
			this.count = 0;
			createShape(tool);
			Shape s = editor.getRecentlyAddedShape();
			// This test applies for shape creation tools only!
			if (s != null) {
				ShapeChangedListener l = new TestShapeChangedListener();
				s.addShapeChangedListener(l);
				s.move(new Vector(1, 1));
				assertTrue(
						"shapeChanged() must be called on a registered listener",
						this.count == 1);
				s.removeShapeChangedListener(l);
				s.move(new Vector(2, 2));
				assertTrue(
						"shapeChanged() must not be called on a disconnected listener",
						this.count == 1);
			}
		}
	}

	/**
	 * Tests whether a null shape modification, i.e., a modification that does
	 * not change the shape's state, does not call a registered listener.
	 */
	@Test
	public void testNotification2() {
		for (ToolFactory factory : toolFactories) {
			DummyEditor editor = new DummyEditor(new DummyView(
					new DefaultSheet()));
			Tool tool = factory.getTool(editor);
			this.count = 0;
			createShape(tool);
			Shape s = editor.getRecentlyAddedShape();
			// This test applies for shape creation tools only!
			if (s != null) {
				s.addShapeChangedListener(new TestShapeChangedListener());
				s.move(new Vector(0, 0));
				assertTrue("shapeChanged() was called for "
						+ s.getClass().getName()
						+ " even if state did not change", this.count == 0);
			}
		}
	}

	/**
	 * Tests whether a shape modification notifies several registered listeners.
	 */
	@Test
	public void testMultiListeners() {
		for (ToolFactory factory : toolFactories) {
			DummyEditor editor = new DummyEditor(new DummyView(
					new DefaultSheet()));
			Tool tool = factory.getTool(editor);
			this.count = 0;
			createShape(tool);
			Shape s = editor.getRecentlyAddedShape();
			// This test applies for shape creation tools only!
			if (s != null) {
				s.addShapeChangedListener(new TestShapeChangedListener());
				s.addShapeChangedListener(new TestShapeChangedListener());
				s.move(new Vector(1, 1));
				assertTrue("Shape " + s.getClass().getName()
						+ " does not support multiple listeners",
						this.count == 2);
			}
		}
	}

	/**
	 * Tests whether setting and getting a shape's bounding box works properly.
	 */
	@Test
	public void testBoundingBox() {
		for (ToolFactory factory : toolFactories) {
			DummyEditor editor = new DummyEditor(new DummyView(
					new DefaultSheet()));
			Tool tool = factory.getTool(editor);
			createShape(tool);
			Shape s = editor.getRecentlyAddedShape();
			// This test applies for shape creation tools only!
			if (s != null) {
				BoundingBox r = new BoundingBox(10, 20, 60, 80);
				s.setBoundingBox(r);
				BoundingBox actual = s.getBoundingBox();
				assertTrue("Shape " + s.getClass().getName()
						+ " has broken set/getBoundingBox() methods",
						r.equals(actual));
			}
		}
	}

	/**
	 * Test the shape's move() operation of the origin within the first
	 * quadrant.
	 */
	@Test
	public void testMove1() {
		for (ToolFactory factory : toolFactories) {
			DummyEditor editor = new DummyEditor(new DummyView(
					new DefaultSheet()));
			Tool tool = factory.getTool(editor);
			createShape(tool);
			Shape s = editor.getRecentlyAddedShape();
			// This test applies for shape creation tools only!
			if (s != null) {
				BoundingBox r = new BoundingBox(10, 20, 60, 80);
				s.setBoundingBox(r);
				s.move(new Vector(10, 10));
				BoundingBox expected = new BoundingBox(20, 30, 60, 80);
				BoundingBox actual = s.getBoundingBox();
				assertTrue("Shape " + s.getClass().getName()
						+ " has broken move() method", expected.equals(actual));
			}
		}
	}

	/**
	 * Test the shape's move() operation of the origin from the first to the
	 * third quadrant.
	 */
	@Test
	public void testMove2() {
		for (ToolFactory factory : toolFactories) {
			DummyEditor editor = new DummyEditor(new DummyView(
					new DefaultSheet()));
			Tool tool = factory.getTool(editor);
			createShape(tool);
			Shape s = editor.getRecentlyAddedShape();
			// This test applies for shape creation tools only!
			if (s != null) {

				BoundingBox r = new BoundingBox(10, 20, 60, 80);
				s.setBoundingBox(r);
				s.move(new Vector(-20, -40));
				BoundingBox expected = new BoundingBox(-10, -20, 60, 80);
				BoundingBox actual = s.getBoundingBox();
				assertTrue("Shape " + s.getClass().getName()
						+ " has broken move() method", expected.equals(actual));
			}
		}
	}

	/**
	 * No test since method contains() cannot be tested for arbitrary, a priori
	 * unknown kind of shapes.
	 */
	// @Test
	public void testContains1() {
		// Cannot be tested in a generic, fail-safe way.

		// for (ToolFactory factory : toolFactories) {
		// Tool tool = factory.createTool(new DummyEditor(new DummyView(
		// new DefaultSheet())));
		// if (tool instanceof ShapeCreationTool) {
		// ShapeCreationTool shapeTool = (ShapeCreationTool) tool;
		// Shape s = createShape(shapeTool);
		// Rectangle r = new Rectangle(10, 10, 1, 1);
		// s.setBoundingBox(r);
		// boolean expected = s.contains(10, 10);
		// assertTrue("Shape " + s.getClass().getName()
		// + " has broken contains() method",
		// expected);
		// }
		// }
	}
}
