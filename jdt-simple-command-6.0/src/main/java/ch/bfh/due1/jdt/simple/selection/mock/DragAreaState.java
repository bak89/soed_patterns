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
 * This state handles the dragging the mouse on shapes.
 */
public class DragAreaState extends SelectionToolState {

	/**
	 * Creates an instance of this state class.
	 *
	 * @param context
	 *            the selection tool
	 */
	DragAreaState(SelectionTool context) {
		super(context);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see ch.bfh.due1.jdt.simple.selection.SelectionToolState#mouseDragEvent(ch.bfh.due1.jdt.framework.Coord, ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	protected void mouseDragEvent(Coord c, KeyModifier k) {
		setSelectionAreaTo(c);
		adjustSelections();
		setToolState(getNewDragAreaState());
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see ch.bfh.due1.jdt.simple.selection.SelectionToolState#mouseUpEvent(ch.bfh.due1.jdt.framework.Coord, ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	protected void mouseUpEvent(Coord c, KeyModifier k) {
		discardSelectionArea();
		setToolState(getNewInitState());
	}
}
