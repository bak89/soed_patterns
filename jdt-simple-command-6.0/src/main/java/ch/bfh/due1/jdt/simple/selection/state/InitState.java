/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2010
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.selection.state;

import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.simple.selection.SelectionTool;
import ch.bfh.due1.jdt.simple.selection.SelectionToolState;


/**
 * Initial state for any kind of operation of the selection tool. Discovers the
 * next state within the mouseDown() method call.
 */
public class InitState extends SelectionToolState {

	/**
	 * Creates an instance.
	 * 
	 * @param context
	 *            the associated selection tool
	 */
	public InitState(SelectionTool context) {
		super(context);
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.simple.selection.SelectionToolState#mouseDownEvent(ch.bfh.due1.jdt.framework.Coord,
	 *      ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	protected void mouseDownEvent(Coord c, KeyModifier k) {
		if (!isMultiSelection(k)) {
			if (isOnUnselectedShape(c)) {
				clearSelection();
				Shape s = getShapeByCoord(c);
				// If current shape s being beneath coordinate c is not in the list of
				// selected shapes then add it.
				if (!seletionContainsShape(s)) {
					addToSelection(s);
				}
				setMoveCursor();
				setToolState(getNewMovingState());
			} else if (isOnShapeHandle(c)) { // order matters
				ShapeHandle h = getShapeHandleByCoord(c);
				setCurrentHandle(h);
				Coord adjustedOrigin = h.getPosition();
				adjustMouseDownCoord(adjustedOrigin);
				setShapeHandleCursor(h.getCursor());
				setToolState(getNewDragHandleState());
			} else if (isOnSelectedShape(c)) {
				setToolState(getNewMovingState());
			} else if (isOnEmptyArea(c)) {
				clearSelection();
				initSelectionArea(getMouseDownCoord());
				setToolState(getNewDragAreaState());
			}
		} else { // multi-selection
			if (isOnUnselectedShape(c)) {
				Shape s = getShapeByCoord(c);
				addToSelection(s);
				setToolState(getNewInitState());
			} else if (isOnSelectedShape(c)) {
				Shape s = getShapeByCoord(c);
				removeFromSelection(s);
				setToolState(getNewInitState());
			} else if (isOnShapeHandle(c)) {
				ShapeHandle h = getShapeHandleByCoord(c);
				Shape s = h.getOwner();
				removeFromSelection(s);
				setToolState(getNewInitState());
			} else if (isOnEmptyArea(c)) {
				// nothing
				setToolState(getNewInitState());
			}
		}
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.simple.selection.SelectionToolState#mouseOverEvent(ch.bfh.due1.jdt.framework.Coord,
	 *      ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	protected void mouseOverEvent(Coord c, KeyModifier k) {
		// order matters...
		if (isOnShapeHandle(c)) {
			ShapeHandle h = getShapeHandleByCoord(c);
			setCursor(h.getCursor());
			setToolState(getNewInitState());
		} else if (isOnSelectedShape(c)) {
			setMoveCursor();
			setToolState(getNewInitState());
		} else {
			setDefaultCursor();
			setToolState(getNewInitState());
		}
	}
}
