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
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Shape;


/**
 * This command implements the cut() operation. The shape and the current view
 * is recorded.
 * <p>
 * To support a list of shapes to be cut, use a macro command, and
 * put a cut command for each individual shape into the macro command.
 *
 * @author Eric Dubuis
 */
public class CutCommand extends AbstractCommand {
	// TODO: Complete...

	/**
	 * Constructs an instance which memorizes the shape that was just removed
	 * from the given editor's view.
	 *
	 * @param editor
	 *            the editor
	 * @param shape
	 *            a shape to be removed
	 */
	public CutCommand(Editor editor, Shape shape) {
		// TODO: Complete...
	}

	/**
	 * Removes the shape from the editor's associated view and adds it onto the
	 * clip board.
	 *
	 * @see ch.bfh.due1.jdt.framework.Command#execute()
	 */
	@Override
	public void execute() {
		// TODO: Complete...
	}

	/**
	 * Adds the shape to the editor's associated view. Shape remains on the clip
	 * board.
	 *
	 * @see ch.bfh.due1.jdt.framework.Command#undo()
	 */
	@Override
	public void undo() {
		// TODO: Complete...
	}
}
