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
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.Vector;
import ch.bfh.due1.jdt.simple.selection.SelectionTool;
import ch.bfh.due1.jdt.simple.selection.SelectionToolState;


/**
 * Denotes the state for moving shapes.
 */
public class MovingState extends SelectionToolState {

	/**
	 * Creates an instance.
	 *
	 * @param context
	 *            the associated selection tool
	 */
	public MovingState(SelectionTool context) {
		super(context);
	}

	/**
	 * @inheritDoc
	 * @see ch.bfh.due1.jdt.simple.selection.SelectionToolState#mouseDragEvent(ch.bfh.due1.jdt.framework.Coord,
	 *      ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	protected void mouseDragEvent(Coord c, KeyModifier k) {
		moveSelectedShapes(c);
		setToolState(getNewMovingState());
	}

	/**
	 * @inheritDoc
	 * @see ch.bfh.due1.jdt.simple.selection.SelectionToolState#mouseUpEvent(ch.bfh.due1.jdt.framework.Coord,
	 *      ch.bfh.due1.jdt.framework.KeyModifier)
	 */
	@Override
	protected void mouseUpEvent(Coord c, KeyModifier k) {
		moveSelectedShapes(c);
		setDefaultCursor();
		Coord origin = getMouseDownCoord();
		Vector delta = new Vector(c.getX0() - origin.getX0(),
				c.getY0() - origin.getY0());
		Command macroCommand = new ch.bfh.sed.commandpattern.command.MacroCommand();
		for (Shape s : getSelection()) {
			// Make a move command object for every selected shape and add it to
			// the macro command object.
			Command moveCommand = new ch.bfh.sed.commandpattern.command.MoveCommand(s, delta);
			macroCommand.addCommand(moveCommand);
		}
		registerCommand(macroCommand);
		setToolState(getNewInitState());
	}
}
