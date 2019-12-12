/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2010-2012
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.selection.mock;

import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.KeyModifier;
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
	InitState(SelectionTool context) {
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
			// Some cases omitted intentionally...
			if (isOnEmptyArea(c)) {
				clearSelection();
				initSelectionArea(getMouseDownCoord());
				setToolState(getNewDragAreaState());
			}
		} else {
			// Some cases omitted intentionally...
			if (isOnEmptyArea(c)) {
				// nothing
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
		// Code omitted intentionally...
	}
}
