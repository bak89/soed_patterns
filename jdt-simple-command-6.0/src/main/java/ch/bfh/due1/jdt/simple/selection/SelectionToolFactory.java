/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple.selection;

import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Tool;
import ch.bfh.due1.jdt.framework.ToolFactory;

/**
 * A factory returning a tool for selecting shapes.
 * 
 * @author Eric Dubuis
 */
public class SelectionToolFactory extends ToolFactory {

	/**
	 * Returns a selection tool.
	 * 
	 * @see ch.bfh.due1.jdt.framework.ToolFactory#getTool(ch.bfh.due1.jdt.framework.Editor)
	 */
	@Override
	protected Tool createTool(Editor e) {
		return new SelectionTool(e);
	}
}
