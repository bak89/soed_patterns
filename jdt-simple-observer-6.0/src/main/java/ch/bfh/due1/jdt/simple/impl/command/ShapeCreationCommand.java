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
 * This command class memorizes the creation of a shape and records the view
 * having the sheet to which the shape was added.
 * 
 * @author Eric Dubuis
 */
public class ShapeCreationCommand extends AbstractCommand {
	/**
	 * The view the shape is created on.
	 */
	private View view;

	/**
	 * The shape.
	 */
	private Shape shape;

	/**
	 * Constructs an instance which memorizes the shape that was just created.
	 * 
	 * @param view
	 *            The view of a sheet this shape is on.
	 * @param shape
	 *            The shape this command applies to.
	 */
	public ShapeCreationCommand(View view, Shape shape) {
		this.view = view;
		this.shape = shape;
	}

	/**
	 * Adds the shape to the view. Typically used with a redo() operation.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Command#execute()
	 */
	@Override
	public void execute() {
		this.view.addShape(this.shape);
	}

	/**
	 * Removes the shape from the view and potentially removes the shape from
	 * the list of selected shapes of a view.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Command#undo()
	 */
	@Override
	public void undo() {
		this.view.removeShape(this.shape);
		this.view.removeFromSelection(this.shape);
	}
}
