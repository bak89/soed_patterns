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
 * The action that undoes the lastly executed command.
 *
 * @author Eric Dubuis
 */
public abstract class UndoAction extends BaseAction {
	/** The serial version UID. */
	private static final long serialVersionUID = -6852637865056357128L;

	/**
	 * Creates an instance.
	 */
	public UndoAction() {
	}

	/**
	 * Retrieves the command handler from the editor and applies
	 * method undoLast() on it.
	 *
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		undoLast(getEditor().getCommandHandler());
		getEditor().checkEditorState();
	}

	/**
	 * Retrieves the command handler from the editor and applies
	 * method undoPossible() on it.
	 *
	 * @see ch.bfh.due1.jdt.framework.BaseAction#checkAction()
	 */
	@Override
	public void checkAction() {
		setEnabled(getEditor().getCommandHandler().undoPossible());
	}

	/**
	 * Tell the command handler to undo the lastly registered command.
	 *
	 * @param ch
	 *            the command handler
	 */
	protected abstract void undoLast(CommandHandler ch);
}
