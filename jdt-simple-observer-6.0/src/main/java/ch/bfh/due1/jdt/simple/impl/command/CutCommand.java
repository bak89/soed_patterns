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
import ch.bfh.due1.jdt.framework.View;


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
	/**
	 * The editor.
	 */
	private Editor editor;

	/**
	 * The view the shape is cut from.
	 */
	private View view;

	/**
	 * The shape.
	 */
	private Shape shape;

	/**
	 * Constructs an instance which memorizes the shape that was just removed
	 * from the given view.
	 * 
	 * @param editor
	 *            the editor
	 * @param view
	 *            the view having the sheet the given shape is onto
	 * @param shape
	 *            a shape to be removed
	 */
	public CutCommand(Editor editor, View view, Shape shape) {
		this.editor = editor;
		this.view = view;
		this.shape = shape;
	}

	/**
	 * Removes the shape from the associated view and adds it
	 * onto the clip board.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Command#execute()
	 */
	@Override
	public void execute() {
		this.view.removeShape(this.shape);
		this.view.removeFromSelection(this.shape);
		Clipboard cp = this.editor.getClipboard();
		cp.put(Collections.singletonList(this.shape.cloneMe()));
	}

	/**
	 * Adds the shape to the associated view. Shape remains on
	 * the clip board.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Command#undo()
	 */
	@Override
	public void undo() {
		this.view.addShape(this.shape);
		this.view.addToSelection(this.shape);
	}
}
