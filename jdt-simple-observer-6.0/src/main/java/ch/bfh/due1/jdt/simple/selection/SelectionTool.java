/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2010, 2011
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.selection;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.List;

import ch.bfh.due1.jdt.framework.AbstractShape;
import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.DefaultTool;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Vector;
import ch.bfh.due1.jdt.simple.StateFactoryLoader;


/**
 * The selection tool handles the selection of shapes (area selection, single
 * selection, multi selection), moving the selected shape(s), and resizing the
 * selected shape by operating on shown shape handles.
 * <p>
 * An instance of this class is the context object of the state pattern used by
 * the implementation of this class. Mouse events are delegated to the current
 * state object.
 */
public final class SelectionTool extends DefaultTool {
	/**
	 * The default cursor for moving shapes.
	 */
	public static final Cursor MOVE_CURSOR = new Cursor(Cursor.MOVE_CURSOR);

	/**
	 * The factory for creating state objects.
	 */
	private StateFactory factory;

	/**
	 * The current state of the selection tool.
	 */
	private SelectionToolState state = null;

	/**
	 * Represent the coordinate where the mouse went down.
	 */
	private Coord mouseDownCoord;

	/**
	 * Represents the coordinate where the mouse was when the method mouseDrag()
	 * was previously called. If there was no previous call of the method
	 * mouseDrag, but a call to mouseDown(), then this field contains the value
	 * of the coordinate where the mouse went down.
	 */
	private Coord previousMouseDragCoord;

	/**
	 * Records the current shape handle the user drags, if any.
	 */
	private ShapeHandle currentHandle;

	/**
	 * A shape for showing the selection area.
	 */
	private SelectionArea selectionArea = null;

	/**
	 * Helper shape the tracks the selection area on the current view.
	 *
	 * @author Eric Dubuis
	 */
	private class SelectionArea extends AbstractShape {
		private BoundingBox r;

		/**
		 * Constructs initial selection area with given size. Sets the pen
		 * color.
		 *
		 * @param r
		 *            bounding box for the size
		 */
		private SelectionArea(BoundingBox r) {
			this.r = r;
			this.setPenColor(Color.BLUE);
		}

		/**
		 * Not used.
		 *
		 * @see ch.bfh.due1.jdt.framework.Shape#contains(ch.bfh.due1.jdt.framework.Coord)
		 */
		@Override
		public boolean contains(Coord c) {
			// Not needed.
			return false;
		}

		/**
		 * Draws the selection area.
		 *
		 * @see ch.bfh.due1.jdt.framework.AbstractShape#doDrawShape(java.awt.Graphics)
		 */
		@Override
		protected void doDrawShape(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			Color current = g2.getColor();
			Stroke stroke = new BasicStroke(1);
			g2.setStroke(stroke);
			g2.setColor(this.penColor);
			g2.draw(this.r.getAWTRectangle());
			g2.setColor(current);
		}

		/**
		 * Returns the size of the selection area.
		 *
		 * @see ch.bfh.due1.jdt.framework.Shape#getBoundingBox()
		 */
		@Override
		public BoundingBox getBoundingBox() {
			return this.r;
		}

		/**
		 * Not used.
		 *
		 * @see ch.bfh.due1.jdt.framework.Shape#move(ch.bfh.due1.jdt.framework.Vector)
		 */
		@Override
		public void move(Vector delta) {
			// Not needed.
		}

		/**
		 * Sets the new size of the selection area.
		 *
		 * @see ch.bfh.due1.jdt.framework.Shape#setBoundingBox(ch.bfh.due1.jdt.framework.BoundingBox)
		 */
		@Override
		public void setBoundingBox(BoundingBox r) {
			if (!this.r.equals(r)) {
				this.r = r;
				notifyShapeChangedListeners();
			}
		}

		/**
		 * No implementation needed.
		 * @see ch.bfh.due1.jdt.framework.Shape#cloneMe()
		 */
		@Override
		public Shape cloneMe() {
			throw new UnsupportedOperationException();
		}

		/**
		 * No implementation needed.
		 * @see ch.bfh.due1.jdt.framework.Shape#createMemento()
		 */
		@Override
		public Memento createMemento() {
			throw new UnsupportedOperationException();
		}

		/**
		 * No implementation needed.
		 * @see ch.bfh.due1.jdt.framework.Shape#setMemento(Memento)
		 */
		@Override
		public void setMemento(Memento m) {
			throw new UnsupportedOperationException();
		}

		/**
		 * No implementation needed.
		 * @see ch.bfh.due1.jdt.framework.Shape#getShapeHandles()
		 */
		@Override
		public List<ShapeHandle> getShapeHandles() {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Constructs a selection tool. The tool knows its editor. Selections, if
	 * any, are not cleared.
	 *
	 * @param e
	 *            The tool's editor.
	 */
	public SelectionTool(Editor e) {
		super(e, false);
		this.factory = createStateFactory();
		setToolState(this.factory.createInitState(this));
	}

	/**
	 * Tries to load and return a state factory with the help of the
	 * StateFactoryLoader class.
	 *
	 * @see ch.bfh.due1.jdt.framework.Editor#getStateFactory()
	 */
	private final StateFactory createStateFactory() {
		StateFactory sf = null;
		try {
			StateFactoryLoader l = new StateFactoryLoader();
			sf = l.createStateFactory();
		} catch (Exception e) {
			// If the state factory instance cannot be created the
			// return null.
			getLogger().debug(e.getMessage());
		}
		return sf;
	}

	/**
	 * Delegates the handling of this event to the current state object.
	 *
	 * @see ch.bfh.due1.jdt.framework.DefaultTool#mouseDown(ch.bfh.due1.jdt.framework.Coord,
	 *      ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	public void mouseDown(Coord c, KeyModifier k) {
		this.state.exit();
		this.mouseDownCoord = c;
		this.previousMouseDragCoord = c;
		getToolState().mouseDown(c, k);
	}

	/**
	 * Delegates the handling of this event to the current state object.
	 *
	 * @see ch.bfh.due1.jdt.framework.DefaultTool#mouseDrag(ch.bfh.due1.jdt.framework.Coord,
	 *      ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	public void mouseDrag(Coord c, KeyModifier k) {
		this.state.exit();
		getToolState().mouseDrag(c, k);
		this.previousMouseDragCoord = c;
	}

	/**
	 * Delegates the handling of this event to the current state object.
	 *
	 * @see ch.bfh.due1.jdt.framework.DefaultTool#mouseOver(ch.bfh.due1.jdt.framework.Coord,
	 *      ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	public void mouseOver(Coord c, KeyModifier k) {
		this.state.exit();
		getToolState().mouseOver(c, k);
	}

	/**
	 * Delegates the handling of this event to the current state object.
	 *
	 * @see ch.bfh.due1.jdt.framework.DefaultTool#mouseUp(ch.bfh.due1.jdt.framework.Coord,
	 *      ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	public void mouseUp(Coord c, KeyModifier k) {
		this.state.exit();
		getToolState().mouseUp(c, k);
		getEditor().checkEditorState();
	}

	/**
	 * Returns the state factory to be used by state objects for creating new
	 * state objects.
	 *
	 * @return a state factory
	 */
	final StateFactory getStateFactory() {
		return this.factory;
	}

	/**
	 * Sets the current state of the tool.
	 */
	final void setToolState(SelectionToolState s) {
		this.state = s;
		this.state.entry();
	}

	/**
	 * Returns the list of shapes.
	 *
	 * @return a list of shapes
	 */
	final List<Shape> getShapes() {
		return getEditor().getShapes();
	}

	/**
	 * Returns the list of selected shapes.
	 *
	 * @return a list of selected shapes
	 */
	final List<Shape> getSelection() {
		return getEditor().getSelection();
	}

	/**
	 * Returns the shape under a given location, if any.
	 *
	 * @param c
	 *            a location
	 * @return a shape if there exists one at the given location, null otherwise
	 */
	final Shape getShapeByCoord(Coord c) {
		// iterate over reverse stacking order
		for (int i = getShapes().size() - 1; i >= 0; i--) {
			Shape s = getShapes().get(i);
			if (s.contains(c)) {
				return s;
			}
		}
		return null;
	}

	/**
	 * Returns the shape handle under a given location, if any.
	 *
	 * @param c
	 *            a location
	 * @return a shape handle if there exists one at the given location, null
	 *         otherwise
	 */
	final ShapeHandle getShapeHandleByCoord(Coord c) {
		for (ShapeHandle h : getSelectionHandles()) {
			if (h.contains(c)) {
				return h;
			}
		}
		return null;
	}

	/**
	 * Indicates whether the mouse is on the empty area of a sheet.
	 *
	 * @param c
	 *            the current mouse location
	 * @return true if the mouse is on the empty area, false otherwise
	 */
	final boolean isOnEmptyArea(Coord c) {
		for (Shape s : getShapes()) {
			if (s.contains(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Indicates whether the mouse is on an unselected shape.
	 *
	 * @param c
	 *            the current mouse location
	 * @return true if the mouse is on an unselected shape, false otherwise
	 */
	final boolean isOnUnselectedShape(Coord c) {
		// iterate over reverse stacking order
		for (int i = getShapes().size() - 1; i >= 0; i--) {
			Shape s = getShapes().get(i);
			if (s.contains(c)) {
				if (getSelection().contains(s)) {
					return false;
				} else {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Indicates whether the mouse is on a selected shape.
	 *
	 * @param c
	 *            the current mouse location
	 * @return true if the mouse is on a selected shape, false otherwise
	 */
	final boolean isOnSelectedShape(Coord c) {
		for (Shape s : getSelection()) {
			if (s.contains(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Indicates whether the mouse os on a shape handle.
	 *
	 * @param c
	 *            the current mouse location
	 * @return true if the mouse is on a shape handle, false otherwise
	 */
	final boolean isOnShapeHandle(Coord c) {
		for (ShapeHandle h : getSelectionHandles()) {
			if (h.contains(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sets the current cursor to be the Move cursor.
	 */
	final void doSetMoveCursor() {
		setCursor(MOVE_CURSOR);
	}

	/**
	 * Adds the given shape to the collection of selected shapes of the
	 * associated view.
	 *
	 * @param s
	 *            the given shape
	 */
	final void doAddToSelection(Shape s) {
		getEditor().addToSelection(s);
	}

	/**
	 * Removes the given shape from the collection of selected shapes of the
	 * associated view.
	 *
	 * @param s
	 *            the given shape
	 */
	final void doRemoveFromSelection(Shape s) {
		getEditor().removeFromSelection(s);
	}

	/**
	 * Sets the current cursor to be the one defined by the current shape
	 * handle.
	 *
	 * @param cursor
	 *            the cursor as determined by the current shape handle
	 */
	final void doSetShapeHandleCursor(Cursor cursor) {
		setCursor(cursor);
	}

	/**
	 * Calculates the vector of the prior recorded location of the mouse and the
	 * new location, and moves every selected shape along this vector.
	 *
	 * @param c
	 *            the new location of the mouse
	 */
	final void doMoveSelectedShapes(Coord c) {
		Coord cPrevious = getPreviousMouseDragCoord();
		if (cPrevious == null) {
			cPrevious = c;
		}
		Vector delta = new Vector(c.getX0() - cPrevious.getX0(),
				c.getY0() - cPrevious.getY0());
		for (Shape s : getSelection()) {
			s.move(delta);
		}
	}

	/**
	 * Sets the current cursor to be the default cursor.
	 */
	final void doSetDefaultCursor() {
		setCursor(Cursor.getDefaultCursor());
	}

	/**
	 * Sets the current cursor to be the Move cursor.
	 */
	final void doSetCursor(Cursor cur) {
		setCursor(cur);
	}

	/**
	 * Returns the coordinate where the mouse went down.
	 *
	 * @return a coordinate
	 */
	final Coord getMouseDownCoord() {
		return this.mouseDownCoord;
	}

	/**
	 * If user presses mouse down button within a shape handle, but not in its
	 * center, then the mouse down coordinate muse be adjusted to the center
	 * of the shape handle.
	 *
	 * @param c a coordinate
	 */
	final void adjustMouseDownCoord(Coord c) {
		this.mouseDownCoord = c;
	}

	/**
	 * Returns the coordinate of the previous mouse drag event. If this method
	 * is called right after a mouse down event then the value returned by this
	 * method corresponds to the coordinate where the mouse went down.
	 *
	 * @return a coordinate
	 */
	final Coord getPreviousMouseDragCoord() {
		return this.previousMouseDragCoord;
	}

	/**
	 * Returns the handle currently working with. Must have been recored via
	 * setCurrentHandle() prior this call.
	 *
	 * @return a shape handle
	 */
	final ShapeHandle getCurrentHandle() {
		return this.currentHandle;
	}

	/**
	 * Sets the current shape handle.
	 *
	 * @param h a shape handle
	 */
	final void setCurrentHandle(ShapeHandle h) {
		this.currentHandle = h;
	}

	/**
	 * Initializes the selection area. Creates a rectangular shape with origin c
	 * and width and size 0. Adds the shape the current sheet.
	 *
	 * @param c
	 *            the current location of the mouse
	 */
	final void doInitSelectionArea(Coord c) {
		this.selectionArea = new SelectionArea(new BoundingBox(c, 0, 0));
		getEditor().addShape(this.selectionArea);
	}

	/**
	 * Resizes the selection area. The given location of the mouse determines
	 * the new width and height of the selection area.
	 *
	 * @param c
	 *            the current location of the mouse
	 */
	final void doSetSelectionAreaTo(Coord c) {
		if (this.selectionArea != null) {
			BoundingBox b = this.selectionArea.getBoundingBox();
			this.selectionArea.setBoundingBox(new BoundingBox(b.getOrigin(), c
					.getX0() - b.getX0(), c.getY0() - b.getY0()));
		}
	}

	/**
	 * Adds every non-selected shape fully contained within the selection area
	 * to the list of selected shapes. Removes every shape no longer fully
	 * contained shape within the selection area from the list of selected
	 * shapes.
	 */
	final void doAdjustSelections() {
		for (Shape s : getShapes()) {
			if (s != this.selectionArea) { // we have to exclude the selection
				// area...
				if (isSeletionAreaContainsShape(s)) {
					if (!getSelection().contains(s)) {
						doAddToSelection(s);
					}
				} else {
					if (getSelection().contains(s)) {
						doRemoveFromSelection(s);
					}
				}
			}
		}
	}

	/**
	 * Removes the selection area from the current sheet, and sets the
	 * selectionArea member variable to null.
	 */
	final void doDiscardSelectionArea() {
		if (this.selectionArea != null) {
			getEditor().removeShape(this.selectionArea);
		}
		this.selectionArea = null;
	}

	/**
	 * Indicates whether the given shape is within the selection area.
	 *
	 * @param s
	 *            a shape
	 * @return true if the shape is within the selection area, false otherwise
	 */
	final boolean isSeletionAreaContainsShape(Shape s) {
		if (this.selectionArea != null) {
			// We cannot use AWT's Rectangle classes and their contain()
			// methods since a AWT rectangle with either with or height
			// is considered to be empty, and an empty rectangle cannot be
			// contained in a AWTrectangle.
			BoundingBox bbSelectionArea = this.selectionArea.getBoundingBox();
			BoundingBox bbShape = s.getBoundingBox();
			Coord startPoint = new Coord(bbShape.getX0(),
					bbShape.getY0());
			Coord endPoint = new Coord(bbShape.getX0() + bbShape.getWidth(),
					bbShape.getY0() + bbShape.getHeight());
			boolean c1 = bbSelectionArea.contains(startPoint);
			boolean c2 = bbSelectionArea.contains(endPoint);
			return c1 && c2;
		} else {
			return false;
		}
	}

	/**
	 * Indicates whether the given shape is in current selection.
	 *
	 * @param s
	 *            a shape
	 * @return true if the shape is in the current selection, false otherwise
	 */
	final boolean isSeletionContainsShape(Shape s) {
		return getEditor().getSelection().contains(s);
	}

	/**
	 * Sets a cursor on the current view. Should be used by mouse trackers only.
	 *
	 * @param cur
	 *            a cursor
	 */
	final private void setCursor(Cursor cur) {
		getEditor().setCursor(cur);
	}

	/**
	 * Returns the current state object.
	 *
	 * @return a state object
	 */
	private SelectionToolState getToolState() {
		return this.state;
	}

	/**
	 * Returns the list of handles of selected shapes associated with the
	 * editor's view.
	 *
	 * @return a list of handles
	 */
	final private List<ShapeHandle> getSelectionHandles() {
		return getEditor().getSelectionHandles();
	}
}