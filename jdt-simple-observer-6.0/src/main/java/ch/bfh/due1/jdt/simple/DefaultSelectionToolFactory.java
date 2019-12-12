/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple;

import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Tool;
import ch.bfh.due1.jdt.framework.ToolFactory;

/**
 * A factory returning a default (dummy) selection tool.
 * 
 * @author Eric Dubuis
 */
public class DefaultSelectionToolFactory extends ToolFactory {
	public DefaultSelectionToolFactory() {
		setIconName("jdt/icon/selection.png");
		setName("Default Selection Tool");
	}

	/**
	 * Returns a selection tool.
	 * 
	 * @see ch.bfh.due1.jdt.framework.ToolFactory#createTool(ch.bfh.due1.jdt.framework.Editor)
	 */
	@Override
	protected Tool createTool(Editor e) {
		return new ch.bfh.due1.jdt.simple.selection.SelectionTool(e);
	}
}
