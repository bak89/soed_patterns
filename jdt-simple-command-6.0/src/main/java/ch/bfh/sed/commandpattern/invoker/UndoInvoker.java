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
import ch.bfh.sed.commandpattern.action.UndoAction;


/**
 * The action that executes the most recent undo command of the command handler.
 *
 * @author Eric Dubuis
 */
public class UndoInvoker extends UndoAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Invoke undo operation on the lastly registered command of the given
	 * command handler.
	 *
	 * @param ch
	 *            the command handler
	 */
	@Override
	protected void undoLast(CommandHandler ch) {
		// TODO: Complete...
	}
}
