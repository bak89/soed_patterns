/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple.impl.command;

import java.util.List;

import ch.bfh.due1.jdt.framework.AbstractCommand;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.View;
import ch.bfh.due1.jdt.simple.impl.shape.ShapeGroup;


/**
 * This command implements the group operation. The selected shapes of the
 * current view's sheet are formed to a group. The shapes are removed from the
 * view's sheet, and the newly created group is added.
 * 
 * @author Eric Dubuis
 */
public class GroupCommand extends AbstractCommand {
	/**
	 * The associated view, set at construction time of an instance of this
	 * class.
	 */
	private View view;

	/**
	 * The object forming a group of shape.
	 */
	private Shape group;

	/**
	 * Constructs an instance which memorizes the shapes that form a group by
	 * creating a ShapeGroup object containing the selected shapes.
	 * 
	 * @param view
	 *            The current view.
	 * @param shapes
	 *            The list of shapes to group.
	 */
	public GroupCommand(View view, List<Shape> shapes) {
		this.view = view;
		this.group = new ShapeGroup(this.view.getShapesByStackingOrder(shapes));
	}

	/**
	 * Removes the shapes of the group from the current view's sheet, clears the
	 * selection, and adds the group of shapes to the current view's sheet. Adds
	 * the group to the view's selection.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Command#execute()
	 */
	@Override
	public void execute() {
		for (Shape s : this.group.getShapes()) {
			this.view.removeShape(s);
		}
		this.view.clearSelection();
		this.view.addShape(this.group);
		this.view.addToSelection(this.group);
	}

	/**
	 * Removes the shape group from the view's sheet. Removes the shape group
	 * from the view's selection. Adds the shapes of this shape group to the
	 * view's sheet and to the view's selection.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Command#undo()
	 */
	@Override
	public void undo() {
		this.view.removeShape(this.group);
		this.view.removeFromSelection(this.group);
		for (Shape s : this.group.getShapes()) {
			this.view.addShape(s);
			this.view.addToSelection(s);
		}
	}
}
