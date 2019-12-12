/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.impl.alignment;

import java.awt.Rectangle;
import java.util.List;


import org.apache.log4j.Logger;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.Vector;
import ch.bfh.due1.jdt.simple.impl.command.MacroCommand;
import ch.bfh.due1.jdt.simple.impl.command.MoveCommand;

/**
 * Aligns the right side a list of shapes to the right side line of the first
 * element of the list of shapes.
 * 
 * @author Eric Dubuis
 */
public class AlignRightStrategy implements AlignStrategy {
	/** The logger. */
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Aligns the bottoms of the given list of shapes to the bottom line of the
	 * first shape (the reference element) in the list. Creates a macro command
	 * and registers it via the tool; the macro command allows the user to undo
	 * the alignment later.
	 * 
	 * @param tool
	 *            the align tool
	 * @param selectedShapes
	 *            a list of shapes; the first element of the list is the
	 *            reference element
	 */
	@Override
	public void align(AlignTool tool, List<Shape> selectedShapes) {
		if (selectedShapes.size() > 1) {
			Shape reference = selectedShapes.get(0);
			Rectangle r = reference.getBoundingBox().getAWTRectangle();
			int xRight = r.x + r.width;
			logger.debug("Align to right: " + xRight);
			Command mc = new MacroCommand();
			for (int i = 1; i < selectedShapes.size(); i++) {
				Command c = alignShape(xRight, selectedShapes.get(i));
				mc.addCommand(c);
			}
			mc.execute();
			tool.registerCommandPerformed(mc);
		}
	}

	/**
	 * Aligns the right line of the given shape to the given right value.
	 * 
	 * @param xRight
	 *            the right value
	 * @param s
	 *            a shape
	 * @return a move command object that will align the shape accordingly.
	 */
	private Command alignShape(int xRight, Shape s) {
		BoundingBox oldPlace = s.getBoundingBox();
		Rectangle r = oldPlace.getAWTRectangle();
		// From the equation: r.x + r.width + delta = xRight
		Vector delta = new Vector(xRight - (r.x + r.width), 0);
		logger.debug("Delta for " + s + " is: " + delta);
		Command c = new MoveCommand(s, delta);
		return c;
	}
}
