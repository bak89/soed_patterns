/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.sed.commandpattern.command;

import ch.bfh.due1.jdt.framework.AbstractCommand;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.Vector;

/**
 * This command class memorizes the start point of a move operation of a shape.
 * For the case that the user applies the redo() operation of on a move command,
 * also the end point of the original move operation is memorized.
 *
 * @author Eric Dubuis
 */
public class MoveCommand extends AbstractCommand {
	// TODO: Complete...

	/**
	 * Creates a command that moves shape s with distance d.
	 *
	 * @param s
	 *            a shape
	 * @param d
	 *            a distance vector
	 */
	public MoveCommand(Shape s, Vector d) {
		// TODO: Complete...
	}

	/**
	 * This method is used by a redo() operation performed by the user only.
	 * Normal moving of a shape is performed by the selection tool itself.
	 *
	 * @see ch.bfh.due1.jdt.framework.Command#execute()
	 */
	@Override
	public void execute() {
		// TODO: Complete...
	}

	/**
	 * Moves back the shape.
	 *
	 * @see ch.bfh.due1.jdt.framework.Command#undo()
	 */
	@Override
	public void undo() {
		// TODO: Complete...
	}
}
