/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple.impl;

import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Tool;
import ch.bfh.due1.jdt.framework.ToolFactory;
import ch.bfh.due1.jdt.simple.impl.shape.EllipseTool;


/**
 * A factory returning a tool for creating ellipses.
 * 
 * @author Eric Dubuis
 */
public class EllipseToolFactory extends ToolFactory {
	/**
	 * Returns an ellipse tool.
	 * 
	 * @see ch.bfh.due1.jdt.framework.ToolFactory#createTool(ch.bfh.due1.jdt.framework.Editor)
	 */
	@Override
	protected Tool createTool(Editor e) {
		return new EllipseTool(e);
	}
}
