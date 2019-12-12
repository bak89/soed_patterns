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
import ch.bfh.due1.jdt.simple.impl.shape.BoxTool;


/**
 * A factory returning a tool for creating boxes.
 * 
 * @author Eric Dubuis
 */
public class BoxToolFactory extends ToolFactory {
	/**
	 * Returns a box tool.
	 * 
	 * @see ch.bfh.due1.jdt.framework.ToolFactory#createTool(ch.bfh.due1.jdt.framework.Editor)
	 */
	@Override
	protected Tool createTool(Editor e) {
		return new BoxTool(e);
	}
}
