/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple.action;

import java.awt.event.ActionEvent;

import ch.bfh.due1.jdt.framework.BaseAction;
import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.View;
import ch.bfh.due1.jdt.simple.impl.command.CutCommand;
import ch.bfh.due1.jdt.simple.impl.command.MacroCommand;


/**
 * Handles the 'cut' action performed by the user.
 * 
 * @author Eric Dubuis
 */
public class CutAction extends BaseAction {
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
		// Clear clip board for a new cut/paste cycle.
		// This is the only place there the clip board is cleared.
		getEditor().getClipboard().clear();
		Command mc = new MacroCommand();
		Editor e = getEditor();
		View v = getEditor().getCurrentView();
		for (Shape s : getEditor().getSelection()) {
			Command c = new CutCommand(e, v, s);
			mc.addCommand(c);
		}
		getEditor().clearSelection();
		CommandHandler h = getEditor().getCommandHandler();
		h.addCommand(mc);
		getEditor().getClipboard().clear();
		mc.execute();
		getEditor().checkEditorState();
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
}
