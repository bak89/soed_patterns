/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework.util;

import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Tool;
import ch.bfh.due1.jdt.framework.ToolFactory;

/**
 * @author Eric Dubuis
 */
public class DefaultToolFactory extends ToolFactory {

	/**
	 * @see ch.bfh.due1.jdt.framework.ToolFactory#createTool(ch.bfh.due1.jdt.framework.Editor)
	 */
	@Override
	protected Tool createTool(Editor e) {
		return new MockTool(e);
	}
}
