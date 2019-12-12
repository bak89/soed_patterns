/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.sed.commandpattern.action;

import java.awt.event.ActionEvent;

import ch.bfh.due1.jdt.framework.BaseAction;
import ch.bfh.due1.jdt.framework.CommandHandler;


/**
 * The action that re-executes the most recently undone command of the command
 * handler.
 *
 * @author Eric Dubuis
 */
public abstract class RedoAction extends BaseAction {
	/** The serial version UID. */
	private static final long serialVersionUID = -262602246025006051L;

	/**
	 * Creates an instance.
	 */
	public RedoAction() {
	}

	/**
	 * Gets the command handler via editor/view and applies the redoLast()
	 * method.
	 *
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		redoLast(getEditor().getCommandHandler());
		getEditor().checkEditorState();
	}

	/**
	 * Gets the command handler via editor/view and applies the redoPossible()
	 * method.
	 *
	 * @see ch.bfh.due1.jdt.framework.BaseAction#checkAction()
	 */
	@Override
	public void checkAction() {
		setEnabled(getEditor().getCommandHandler().redoPossible());
	}

	/**
	 * Tell the command handler to re-do the last registered command.
	 *
	 * @param ch
	 *            the command handler
	 */
	protected abstract void redoLast(CommandHandler ch);
}
