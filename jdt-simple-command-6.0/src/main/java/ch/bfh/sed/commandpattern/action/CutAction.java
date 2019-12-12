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
import ch.bfh.due1.jdt.framework.Editor;


/**
 * Handles the 'cut' action performed by the user.
 * <p>
 * Note: This superficial class hierarchy is due to the proper separation of
 * duties within the exercise.
 *
 * @author Eric Dubuis
 */
public abstract class CutAction extends BaseAction {
	/** The serial version UID. */
	private static final long serialVersionUID = 1807254561582687768L;

	/**
	 * Creates an instance.
	 */
	public CutAction() {
		setEnabled(false);
	}

	/**
	 * Creates a macro command, creates a cut command for each selected shape and put
	 * it into the macro command, clears the selection, adds the macro command into
	 * the command handler, and executes the command.
	 *
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		Editor e = getEditor();
		e.getClipboard().clear();
		createCommand(e);
		e.clearSelection();
		// Clear clip board for a new cut/paste cycle.
		// This is the only place where the clip board is cleared.
		e.getClipboard().clear();
		invokeCommand();
		registerCommand(e);
		e.checkEditorState();
	}

	/**
	 * Checks whether the cut action is enabled or not.
	 *
	 * @see ch.bfh.due1.jdt.framework.BaseAction#checkAction()
	 */
	@Override
	public void checkAction() {
		setEnabled(getEditor().getSelection().size() > 0);
	}

	/**
	 * Subclasses need to create the appropriate command for this action and
	 * store it in the instance.
	 *
	 * @param e
	 *            an editor
	 */
	protected abstract void createCommand(Editor e);

	/**
	 * Provokes the invocation of the execute() method on the corresponding
	 * Command instance.
	 */
	protected abstract void invokeCommand();

	/**
	 * Registers the corresponding the Command instance.
	 *
	 * @param e
	 *            an editor
	 */
	protected abstract void registerCommand(Editor e);
}
