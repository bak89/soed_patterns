/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.sed.commandpattern.invoker;

import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.sed.commandpattern.action.PasteAction;


/**
 * Handles the 'paste' action performed by the user.
 *
 * @author Eric Dubuis
 */
public class PasteInvoker extends PasteAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The Command instance used by this invoker.
	 */
	// TODO: Complete...

	public PasteInvoker() {
		setEnabled(false);
	}

	/**
	 * Given an editor, create a macro command which contains paste commands for
	 * each shape which in turn contain the editor and a shape. The selected
	 * shapes can be obtained by calling getSelection() on the given editor
	 * instance. Must be the first method being called upon an instance of this
	 * class. The paste command must clone the shape instance by applying
	 * cloneMe() on the shape instance.
	 *
	 * @param e
	 *            the editor
	 */
	@Override
	protected void createCommand(Editor e) {
		// TODO: Complete...
	}

	/**
	 * When called, applies method execute() on the Command instance created by
	 * method createCommand(Editor e).
	 */
	@Override
	protected void invokeCommand() {
		// TODO: Complete...
	}

	/**
	 * Registers the Command instance previously created with the Command
	 * Handler instance. This instance is obtained from the Editor instance.
	 *
	 * @param e
	 *            the editor instance
	 */
	@Override
	protected void registerCommand(Editor e) {
		// TODO: Complete...
	}
}
