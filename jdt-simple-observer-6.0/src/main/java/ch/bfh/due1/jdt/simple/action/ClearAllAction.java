/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2014
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
 * Handles the 'select all' action performed by the user.
 * 
 * @author Eric Dubuis
 */
public class ClearAllAction extends BaseAction {
	/** The serial version UID. */
	private static final long serialVersionUID = 1807254561582687768L;

	/**
	 * Creates an instance.
	 */
	public ClearAllAction() {
		setEnabled(false);
	}

	/**
	 * 
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
		for (Shape s : getEditor().getShapes()) {
			Command c = new CutCommand(e, v, s);
			mc.addCommand(c);
		}
		getEditor().clearSelection();
		mc.execute();
		CommandHandler h = getEditor().getCommandHandler();
		h.addCommand(mc);
		getEditor().checkEditorState();
	}

	/**
	 * Checks whether the cut action is enabled or not.
	 * 
	 * @see ch.bfh.due1.jdt.framework.BaseAction#checkAction()
	 */
	@Override
	public void checkAction() {
		setEnabled(getEditor().getShapes().size() > 0);
	}
}
