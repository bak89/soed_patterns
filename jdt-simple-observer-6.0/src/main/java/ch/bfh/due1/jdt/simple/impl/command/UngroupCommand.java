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
 * This command implements the ungroup() operation. The selected group of shapes
 * of the current view's sheet is ungrouped. That is, the group's children are
 * added to the view, and the group itself is removed.
 * 
 * @author Eric Dubuis
 */
public class UngroupCommand extends AbstractCommand {
	/**
	 * The view to act on.
	 */
	private View view;

	/**
	 * The shape which is a group of shapes.
	 */
	private Shape group;

	/**
	 * Constructs an instance which memorizes the shapes that form a group.
	 * 
	 * @param view
	 *            The current view.
	 * @param shape
	 *            The shape which is also a group.
	 */
	public UngroupCommand(View view, Shape shape) {
		if (!shape.isContainer()) {
			throw new IllegalArgumentException("Not container shape given: "
					+ shape);
		}
		this.view = view;
		this.group = shape;
	}

	/**
	 * Removes the shape group from the view and from the view's selection this
	 * command is associated with. Then adds the shapes of this shape group to
	 * the view and to the view's selection.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Command#execute()
	 */
	@Override
	public void execute() {
		this.view.removeShape(this.group);
		this.view.removeFromSelection(this.group);
		for (Shape s : this.group.getShapes()) {
			this.view.addShape(s);
			this.view.addToSelection(s);
		}
	}

	/**
	 * Adds the shape group to the view and to the view's selection this command
	 * is associated with.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Command#undo()
	 */
	@Override
	public void undo() {
		this.view.clearSelection();
		this.view.addShape(this.group);
		this.view.addToSelection(this.group);
	}
}
