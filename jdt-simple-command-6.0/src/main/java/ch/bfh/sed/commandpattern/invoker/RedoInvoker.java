/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.sed.commandpattern.invoker;

import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.sed.commandpattern.action.RedoAction;


/**
 * The action that re-executes the most recent re-do command of the command
 * handler.
 *
 * @author Eric Dubuis
 */
public class RedoInvoker extends RedoAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Invoke re-do operation on the lastly registered command of the given
	 * command handler.
	 *
	 * @param ch
	 *            the command handler
	 */
	@Override
	protected void redoLast(CommandHandler ch) {
		// TODO: Complete...
	}
}
