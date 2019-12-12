/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2010
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.selection.state;

import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.simple.impl.command.DragCommand;
import ch.bfh.due1.jdt.simple.selection.SelectionTool;
import ch.bfh.due1.jdt.simple.selection.SelectionToolState;


/**
 * The state object used during the dragging on a shape handle.
 */
public class DragHandleState extends SelectionToolState {


	/**
	 * Initializes the DragHandleState object with its context (a selection
	 * tool) and a shape handle.
	 * 
	 * @param context
	 *            a selection tool
	 */
	public DragHandleState(SelectionTool context) {
		super(context);
	}

	/**
	 * Calls dragInteraction() on the handle associated to this state.
	 * 
	 * @see ch.bfh.due1.jdt.simple.selection.SelectionToolState#mouseDrag(ch.bfh.due1.jdt.framework.Coord,
	 *      ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	protected void mouseDragEvent(Coord c, KeyModifier k) {
		getCurrentHandle().dragInteraction(c, k);
		setToolState(getNewDragHandleState());
	}

	/**
	 * Makes a final drag operation on the handle, creates and registers a drag
	 * command, and sets the tool state to InitState.
	 * 
	 * @see ch.bfh.due1.jdt.simple.selection.SelectionToolState#mouseUp(ch.bfh.due1.jdt.framework.Coord,
	 *      ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	protected void mouseUpEvent(Coord c, KeyModifier k) {
		getCurrentHandle().stopInteraction(c, k);
		Coord origin = getMouseDownCoord();
		Command cmd = new DragCommand(getCurrentHandle(), origin, c, k);
		registerCommand(cmd);
		setToolState(getNewInitState());
	}
}
