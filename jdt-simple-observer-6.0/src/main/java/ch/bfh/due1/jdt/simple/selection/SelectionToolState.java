/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2010, 2011
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.selection;

import java.awt.Cursor;
import java.util.List;

import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.View;


/**
 * The base state for the state classes of the selection tool. The selection
 * tool is the context.
 */
public abstract class SelectionToolState {

	/**
	 * The selection tool being the context for the state objects.
	 */
	private SelectionTool myContext;

	/**
	 * Upon creation initializes the context.
	 * 
	 * @param context
	 *            the selection tool denoting the context
	 */
	protected SelectionToolState(SelectionTool context) {
		this.myContext = context;
	}

	/**
	 * Being called after entering a state in order to handle state-specific
	 * initialization. Initialization is delegated to two methods that can
	 * be overridden: doEntry() and startDo().
	 */
	final void entry() {
		doEntry();
		startDo();
	}

	/**
	 * Being called after leaving a state in order to handle state-specific
	 * exit handling. Exit handling is delegated to two methods that can
	 * be overridden: stopDo() and doExit().
	 */
	final void exit() {
		stopDo();
		doExit();
	}

	/**
	 * Handles the mouse down event upon this state by recording first the
	 * current coordinate and then calling method mouseDownEvent().
	 * 
	 * @param c
	 *            The coordinate of mouse position.
	 * @param k
	 *            The key modifier corresponding to the one contained in the
	 *            original mouse event.
	 */
	final void mouseDown(Coord c, KeyModifier k) {
		mouseDownEvent(c, k);
	}

	/**
	 * Handles the mouse-drag event upon this state by calling method
	 * mouseDragEvent() and then recording the current coordinate.
	 * 
	 * @param c
	 *            The coordinate of mouse position.
	 * @param k
	 *            The key modifier corresponding to the one contained in the
	 *            original mouse event.
	 */
	final void mouseDrag(Coord c, KeyModifier k) {
		mouseDragEvent(c, k);
	}

	/**
	 * Handles the mouse-up event upon this state.
	 * 
	 * @param c
	 *            The coordinate of mouse position.
	 * @param k
	 *            The key modifier corresponding to the one contained in the
	 *            original mouse event.
	 */
	final void mouseUp(Coord c, KeyModifier k) {
		mouseUpEvent(c, k);
	}

	/**
	 * Handles the mouse-over event upon this state.
	 * 
	 * @param c
	 *            The coordinate of mouse position.
	 * @param k
	 *            The key modifier corresponding to the one contained in the
	 *            original mouse event.
	 */
	final void mouseOver(Coord c, KeyModifier k) {
		mouseOverEvent(c, k);
	}

	/**
	 * Handles the mouse-down event if overwritten in subclasses. The method in
	 * this base class is empty.
	 * 
	 * @param c
	 *            The coordinate of mouse position.
	 * @param k
	 *            The key modifier corresponding to the one contained in the
	 *            original mouse event.
	 */
	protected void mouseDownEvent(Coord c, KeyModifier k) {
		// Default implementation, intentionally left empty.
	}

	/**
	 * Handles the mouse-drag event if overwritten in subclasses. The method in
	 * this base class is empty.
	 * 
	 * @param c
	 *            The coordinate of mouse position.
	 * @param k
	 *            The key modifier corresponding to the one contained in the
	 *            original mouse event.
	 */
	protected void mouseDragEvent(Coord c, KeyModifier k) {
		// Default implementation, intentionally left empty.
	}

	/**
	 * Handles the mouse-up event if overwritten in subclasses. The method in
	 * this base class is empty.
	 * 
	 * @param c
	 *            The coordinate of mouse position.
	 * @param k
	 *            The key modifier corresponding to the one contained in the
	 *            original mouse event.
	 */
	protected void mouseUpEvent(Coord c, KeyModifier k) {
		// Default implementation, intentionally left empty.
	}

	/**
	 * Handles the mouse-over event if overwritten in subclasses. The method in
	 * this base class is empty.
	 * 
	 * @param c
	 *            The coordinate of mouse position.
	 * @param k
	 *            The key modifier corresponding to the one contained in the
	 *            original mouse event.
	 */
	protected void mouseOverEvent(Coord c, KeyModifier k) {
		// Default implementation, intentionally left empty.
	}

	/**
	 * Handles state-specific initialization if overridden by sub-classes.
	 */
	protected void doEntry() {
		// Intentionally left empty.
	}

	/**
	 * Handles state-specific exit handling if overridden by sub-classes.
	 */
	protected void doExit() {
		// Intentionally left empty.
	}

	/**
	 * Starts state-specific, permanent behavior if overridden by sub-classes.
	 */
	protected void startDo() {
		// Intentionally left empty.
	}

	/**
	 * Stops state-specific, permanent behavior if overridden by sub-classes.
	 */
	protected void stopDo() {
		// Intentionally left empty.
	}

	/**
	 * Sets the new state of the selection tool.
	 * 
	 * @param newState
	 *            the new state
	 */
	final protected void setToolState(SelectionToolState newState) {
		getContext().setToolState(newState);
	}

	/**
	 * Returns the context, i.e., the selection tool.
	 * 
	 * @return the selection tool
	 */
	final protected SelectionTool getContext() {
		return this.myContext;
	}

	/**
	 * Helper method that returns the state needed while user is dragging on
	 * area.
	 * 
	 * @return a state
	 */
	final protected SelectionToolState getNewDragAreaState() {
		return getContext().getStateFactory().createDragAreaState(getContext());
	}

	/**
	 * Helper method that returns the state needed while user is dragging on
	 * shape handle.
	 * 
	 * @return a state
	 */
	final protected SelectionToolState getNewDragHandleState() {
		return getContext().getStateFactory().createDragHandleState(
				getContext());
	}

	/**
	 * Helper method that returns the state needed upon completing the dragging,
	 * selecting, or moving.
	 * 
	 * @return a state
	 */
	final protected SelectionToolState getNewInitState() {
		return getContext().getStateFactory().createInitState(getContext());
	}

	/**
	 * Helper method that returns the state needed while user is moving shapes.
	 * 
	 * @return a state
	 */
	final protected SelectionToolState getNewMovingState() {
		return getContext().getStateFactory().createMovingState(getContext());
	}

	/**
	 * Returns the editor.
	 * 
	 * @return an editor
	 */
	final protected Editor getEditor() {
		return getContext().getEditor();
	}

	/**
	 * Returns a view.
	 * 
	 * @return a view
	 */
	final protected View getView() {
		return getContext().getEditor().getCurrentView();
	}

	/**
	 * Returns the shapes.
	 * 
	 * @return a list of shapes
	 */
	final protected List<Shape> getShapes() {
		return getEditor().getShapes();
	}

	/**
	 * Returns the first shape being beneath the given coordinate, if any.
	 * 
	 * @param c
	 *            a coordinate
	 * @return a shape, or null
	 */
	final protected Shape getShapeByCoord(Coord c) {
		return getContext().getShapeByCoord(c);
	}

	/**
	 * Returns a shape handle being beneath the given coordinate, if any.
	 * 
	 * @param c
	 *            a coordinate
	 * @return a shape handle, or null
	 */
	final protected ShapeHandle getShapeHandleByCoord(Coord c) {
		return getContext().getShapeHandleByCoord(c);
	}

	/**
	 * Returns the list of selected shapes. The returned list can be empty.
	 * 
	 * @return a list of selected shapes
	 */
	final protected List<Shape> getSelection() {
		return getEditor().getSelection();
	}

	/**
	 * Removes any selections of the associated view.
	 */
	final protected void clearSelection() {
		getEditor().clearSelection();
	}

	/**
	 * Returns true if there is no shape beneath the given coordinate.
	 * 
	 * @param c
	 *            a coordinate
	 * @return true if and only of there is no shape beneath the given
	 *         coordinate, false otherwise
	 */
	final protected boolean isOnEmptyArea(Coord c) {
		return getContext().isOnEmptyArea(c);
	}

	/**
	 * Returns true if there is a shape handle beneath the given coordinate.
	 * 
	 * @param c
	 *            a coordinate
	 * @return true if and only of there is a shape handle beneath the given
	 *         coordinate, false otherwise
	 */
	final protected boolean isOnShapeHandle(Coord c) {
		return getContext().isOnShapeHandle(c);
	}

	/**
	 * Returns true if there is a selected shape beneath the given coordinate.
	 * 
	 * @param c
	 *            a coordinate
	 * @return true if and only of there is a selected shape beneath the given
	 *         coordinate, false otherwise
	 */
	protected final boolean isOnSelectedShape(Coord c) {
		return getContext().isOnSelectedShape(c);
	}

	/**
	 * Returns true if there is a non-selected shape beneath the given
	 * coordinate.
	 * 
	 * @param c
	 *            a coordinate
	 * @return true if and only of there is a non-selected shape beneath the
	 *         given coordinate, false otherwise
	 */
	final protected boolean isOnUnselectedShape(Coord c) {
		return getContext().isOnUnselectedShape(c);
	}

	/**
	 * Determines if key modifier signals multi-selection. Returns true if the
	 * key modifier value is KeyModifier.CONTROL_DOWN.
	 * 
	 * @param k
	 *            key modifier
	 * @return true if multi-selection, false otherwise
	 */
	final protected boolean isMultiSelection(KeyModifier k) {
		return k == KeyModifier.CONTROL_DOWN;
	}

	/**
	 * Sets the cursor to become the 'move' cursor.
	 */
	final protected void setMoveCursor() {
		getContext().doSetMoveCursor();
	}

	/**
	 * Sets the cursor to become the 'shape handle' cursor.
	 */
	final protected void setShapeHandleCursor(Cursor cur) {
		getContext().doSetShapeHandleCursor(cur);
	}

	/**
	 * Sets the cursor to become the 'default' cursor.
	 */
	final protected void setDefaultCursor() {
		getContext().doSetDefaultCursor();
	}

	/**
	 * Sets the cursor.
	 * 
	 * @param c
	 *            the current cursor
	 */
	final protected void setCursor(Cursor c) {
		getContext().doSetCursor(c);
	}

	/**
	 * Returns the shared coordinate of the previous mouseXy() call.
	 * 
	 * @return a coordinate
	 */
	final protected Coord getPreviousMouseDragCoord() {
		return getContext().getPreviousMouseDragCoord();
	}

	/**
	 * Returns the shared coordinate of the previous mouseXy() call.
	 * 
	 * @return a coordinate
	 */
	final protected Coord getMouseDownCoord() {
		return getContext().getMouseDownCoord();
	}

	/**
	 * If user presses mouse down button within a shape handle, but not in its
	 * center, then the mouse down coordinate muse be adjusted to the center of
	 * the shape handle.
	 * 
	 * @param c
	 *            a coordinate
	 */
	final protected void adjustMouseDownCoord(Coord c) {
		getContext().adjustMouseDownCoord(c);
	}

	/**
	 * Returns the current shape handle the user is dragging on. Must have been
	 * registered previously by calling setCurrentHandle().
	 * 
	 * @return a shape handle, or null
	 */
	final protected ShapeHandle getCurrentHandle() {
		return getContext().getCurrentHandle();
	}

	/**
	 * Registers the current shape handle with the context.
	 * 
	 * @param h
	 *            a shape handle
	 */
	final protected void setCurrentHandle(ShapeHandle h) {
		getContext().setCurrentHandle(h);
	}

	/**
	 * Adds the given shape to the collection of selected shapes of the
	 * associated view.
	 * 
	 * @param s
	 *            the given shape
	 */
	final protected void addToSelection(Shape s) {
		getContext().doAddToSelection(s);
	}

	/**
	 * Removes the given shape from the collection of selected shapes of the
	 * associated view.
	 * 
	 * @param s
	 *            the given shape
	 */
	final protected void removeFromSelection(Shape s) {
		getContext().doRemoveFromSelection(s);
	}

	/**
	 * Registers a non-null command with the editor's command handler. However,
	 * if the editor does not return a command handler of if the argument is
	 * null then nothing is performed.
	 * 
	 * @param c
	 *            a command, may be null
	 */
	final protected void registerCommand(Command c) {
		if (c != null) {
			CommandHandler ch = getEditor().getCommandHandler();
			if (ch != null) {
				ch.addCommand(c);
			}
		}
	}

	/**
	 * Initializes the selection area. Creates a rectangular shape with origin c
	 * and width and size 0. Adds the shape the current sheet.
	 * 
	 * @param c
	 *            the current location of the mouse
	 */
	final protected void initSelectionArea(Coord c) {
		getContext().doInitSelectionArea(c);
	}

	/**
	 * Resizes the selection area. The given location of the mouse determines
	 * the new width and height of the selection area.
	 * 
	 * @param c
	 *            the current location of the mouse
	 */
	final protected void setSelectionAreaTo(Coord c) {
		getContext().doSetSelectionAreaTo(c);
	}

	/**
	 * Adds every non-selected shape fully contained within the selection area
	 * to the list of selected shapes. Removes every shape no longer fully
	 * contained shape within the selection area from the list of selected
	 * shapes.
	 */
	final protected void adjustSelections() {
		getContext().doAdjustSelections();
	}

	/**
	 * Removes the selection area from the current sheet, and sets the
	 * selectionArea member variable to null.
	 */
	final protected void discardSelectionArea() {
		getContext().doDiscardSelectionArea();
	}

	/**
	 * Indicates whether the given shape is within the selection area.
	 * 
	 * @param s
	 *            a shape
	 * @return true if the shape is within the selection area, false otherwise
	 */
	final protected boolean seletionAreaContainsShape(Shape s) {
		return getContext().isSeletionAreaContainsShape(s);
	}

	/**
	 * Indicates whether the given shape is in current selection.
	 * 
	 * @param s
	 *            a shape
	 * @return true if the shape is in current selection, false otherwise
	 */
	final protected boolean seletionContainsShape(Shape s) {
		return getContext().isSeletionAreaContainsShape(s);
	}

	/**
	 * Moves the selected shapes along a distance vector determined by the
	 * new location of the mouse.
	 * 
	 * @param c
	 *            the new location of the mouse
	 */
	final protected void moveSelectedShapes(Coord c) {
		getContext().doMoveSelectedShapes(c);
	}
}
