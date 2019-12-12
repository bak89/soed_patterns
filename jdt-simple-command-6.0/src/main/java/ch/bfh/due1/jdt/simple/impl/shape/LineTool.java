/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple.impl.shape;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.DefaultTool;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.simple.impl.command.ShapeCreationCommand;


/**
 * Implementation of a tool for creating lines.
 * 
 * @author Eric Dubuis
 */
public class LineTool extends DefaultTool {

	private SimpleLine shapeConstructing = null;

	/**
	 * Creates a line tool instance.
	 * 
	 * @param e
	 *            The associated editor.
	 */
	public LineTool(Editor e) {
		super(e);
	}

	/**
	 * Constructs a line at the given point (x, y) with initial size of zero.
	 * Installs the shape on the sheet associated with the current view of the
	 * editor.
	 * 
	 * @see ch.bfh.due1.jdt.framework.DefaultTool#mouseDown(Coord, KeyModifier)
	 */
	@Override
	public void mouseDown(Coord c, KeyModifier k) {
		super.mouseDown(c, k);

		this.shapeConstructing = new SimpleLine(c.getX0(), c.getY0(),
				0, 0);

		this.getEditor().addShape(this.shapeConstructing);
	}

	/**
	 * Enlarges the line relative to the origin point (x0, y0) and the current
	 * point (x, y).
	 * 
	 * @see ch.bfh.due1.jdt.framework.DefaultTool#mouseDrag(Coord, KeyModifier)
	 */
	@Override
	public void mouseDrag(Coord c, KeyModifier k) {
		super.mouseDrag(c, k);

		BoundingBox r = this.shapeConstructing.getBoundingBox();
		this.shapeConstructing.setBoundingBox(new BoundingBox(r.getX0(), r
				.getY0(), c.getX0() - r.getX0(), c.getY0()
				- r.getY0()));
	}

	/**
	 * Does nothing except logging.
	 * 
	 * @see ch.bfh.due1.jdt.framework.DefaultTool#mouseUp(Coord, KeyModifier)
	 */
	@Override
	public void mouseUp(Coord c, KeyModifier k) {
		super.mouseUp(c, k);
		BoundingBox r = this.shapeConstructing.getBoundingBox();
		this.shapeConstructing.setBoundingBox(new BoundingBox(r.getX0(), r
				.getY0(), c.getX0() - r.getX0(), c.getY0()
				- r.getY0()));
		Command cmd = new ShapeCreationCommand(getEditor().getCurrentView(),
				this.shapeConstructing);
		getEditor().getCommandHandler().addCommand(cmd);
		this.shapeConstructing = null;
	}

	/**
	 * Returns the class name of this tool implementation.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
