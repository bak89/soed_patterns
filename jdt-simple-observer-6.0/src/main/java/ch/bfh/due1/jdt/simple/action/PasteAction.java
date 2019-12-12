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
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.View;
import ch.bfh.due1.jdt.simple.impl.command.MacroCommand;
import ch.bfh.due1.jdt.simple.impl.command.PasteCommand;


/**
 * Handles the 'paste' action performed by the user.
 * 
 * @author Eric Dubuis
 */
public class PasteAction extends BaseAction {
	/** The serial version UID. */
	private static final long serialVersionUID = 1807254561582687768L;

	/**
	 * Creates an instance.
	 */
	public PasteAction() {
	}

	/**
	 * Retrieves the shapes from the editor's clip board, creates a paste
	 * command, registers it with the command handler, and executes it.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		Command mc = new MacroCommand();
		View v = getEditor().getCurrentView();
		for (Shape s : getEditor().getClipboard().get()) {
			// We put a clone of the shape of the clip board
			// into the paste command. This allows to past
			// several different instances of the shape onto
			// the appropriate sheet.
			Command c = new PasteCommand(v, s.cloneMe());
			mc.addCommand(c);
		}
		getEditor().clearSelection();
		CommandHandler h = getEditor().getCommandHandler();
		h.addCommand(mc);
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
		setEnabled(getEditor().getClipboard().get().size() > 0);
	}
}
