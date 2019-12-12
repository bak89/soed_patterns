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
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.framework.ShapeHandle;

/**
 * This command class memorizes the starting and finale point of a drag operation of a
 * handle.
 * 
 * @author Eric Dubuis
 */
public class DragCommand extends AbstractCommand {
	/**
	 * The handle on which we operate.
	 */
	private ShapeHandle h;

	/**
	 * The original position of the handle.
	 */
	private Coord origin;

	/**
	 * The final position of the handle.
	 */
	private Coord c;

	/**
	 * The key modifier used for the operation.
	 */
	private KeyModifier k;

	/**
	 * Constructs a drag command instance.
	 * 
	 * @param h
	 *            a handle
	 * @param origin
	 *            the origin of the handle
	 * @param c
	 *            the final coordinate of a drag operation
	 * @param k
	 *            the key modifier used for the drag operation
	 */
	public DragCommand(ShapeHandle h, Coord origin, Coord c, KeyModifier k) {
		this.h = h;
		this.origin = origin;
		this.c = c;
		this.k = k;
	}

	/**
	 * This method is used by a redo() operation performed by the user only.
	 * Normal dragging of a shape is performed by the selection tool itself.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Command#execute()
	 */
	@Override
	public void execute() {
		h.dragInteraction(c, k);
	}

	/**
	 * Drags back the shape.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Command#undo()
	 */
	@Override
	public void undo() {
		h.dragInteraction(origin, k);
	}
}
