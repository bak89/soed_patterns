/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple.impl.command;

import java.util.Collections;

import ch.bfh.due1.jdt.framework.AbstractCommand;
import ch.bfh.due1.jdt.framework.Clipboard;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Shape;


/**
 * This command implements the 'copy' operation. The shape is copied onto the
 * clip board. This command does not support the undo() method, i.e., the undo()
 * method is empty.
 * <p>
 * To support a list of shapes to be copied, use a macro command, and
 * put a copy command for each individual shape into the macro command.
 * 
 * @author Eric Dubuis
 */
public class CopyCommand extends AbstractCommand {
	/**
	 * The editor this command is associated to.
	 */
	private Editor editor;

	/**
	 * A clone of the shape to be copied.
	 */
	private Shape shape;

	/**
	 * Constructs an instance which memorizes the shape that was just added to
	 * the given view.
	 * 
	 * @param editor
	 *            the editor
	 * @param shape
	 *            a shape to be pasted
	 */
	public CopyCommand(Editor editor, Shape shape) {
		this.editor = editor;
		this.shape = shape.cloneMe();
	}

	/**
	 * Copies the shapes into the clip board.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Command#execute()
	 */
	@Override
	public void execute() {
		Clipboard cp = this.editor.getClipboard();
		cp.put(Collections.singletonList(this.shape));
	}

	/**
	 * Empty implementation.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Command#undo()
	 */
	@Override
	public void undo() {
		// If the shape is pasted into the clip board, it is
		// there. This cannot be undone.
	}
}
