/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple.impl.command;

import ch.bfh.due1.jdt.framework.AbstractCommand;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.View;

/**
 * This command implements the 'paste' operation. The shape and the view onto
 * which to paste the shape is recorded.
 * <p>
 * FIXME Extend this class to support a list of shapes.
 *
 * @author Eric Dubuis
 */
public class PasteCommand extends AbstractCommand {
	/**
	 * The view a shape is pasted onto.
	 */
	private View view;

	/**
	 * The shape.
	 */
	private Shape shape;

	/**
	 * Constructs an instance which memorizes the shape that was just added
	 * to the given view.
	 *
	 * @param shape
	 *            a shape to be pasted
	 */
	public PasteCommand(View view, Shape shape) {
		this.view = view;
		this.shape = shape;
	}

	/**
	 * Copies the shapes into the view. Does not modify the clip board.
	 *
	 * @see ch.bfh.due1.jdt.framework.Command#execute()
	 */
	@Override
	public void execute() {
		this.view.addShape(this.shape);
		this.view.addToSelection(this.shape);
	}

	/**
	 * Removes the shape from the view. Does not modify the clip board.
	 *
	 * @see ch.bfh.due1.jdt.framework.Command#undo()
	 */
	@Override
	public void undo() {
		this.view.removeShape(this.shape);
		this.view.removeFromSelection(this.shape);
	}
}
