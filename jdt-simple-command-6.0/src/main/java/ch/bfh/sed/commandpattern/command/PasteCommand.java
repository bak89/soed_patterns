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
 * This command implements the 'paste' operation. The shape and the editor whose
 * associated view to paste the shape is recorded.
 * <p>
 * TODO Extend this class to support a list of shapes.
 *
 * @author Eric Dubuis
 */
public class PasteCommand extends AbstractCommand {
	// TODO: Complete...

	/**
	 * Constructs an instance which memorizes the shape that was just added to
	 * the given editor's view.
	 *
	 * @param shape
	 *            a shape to be pasted
	 */
	public PasteCommand(Editor editor, Shape shape) {
		// TODO: Complete...
	}

	/**
	 * Copies the shapes into the editor's view. Does not modify the clip board.
	 *
	 * @see ch.bfh.due1.jdt.framework.Command#execute()
	 */
	@Override
	public void execute() {
		// TODO: Complete...
	}

	/**
	 * Removes the shape from the editor's view. Does not modify the clip board.
	 * Thus, a shape can be pasted several times onto the editor's view.
	 *
	 * @see ch.bfh.due1.jdt.framework.Command#undo()
	 */
	@Override
	public void undo() {
		// TODO: Complete...
	}
}
