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
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.simple.impl.command.CopyCommand;
import ch.bfh.due1.jdt.simple.impl.command.MacroCommand;


/**
 * Handles the 'copy' action performed by the user.
 * 
 * @author Eric Dubuis
 */
public class CopyAction extends BaseAction {
	/** The serial version UID. */
	private static final long serialVersionUID = 1807254561582687768L;

	/**
	 * Creates an instance.
	 */
	public CopyAction() {
		setEnabled(false);
	}

	/**
	 * Creates a macro command, creates a paste command for each selected shape
	 * and put it into the macro command, and registers the macro command with
	 * the editor as pending command.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		Command mc = new MacroCommand();
		Editor e = getEditor();
		for (Shape s : getEditor().getSelection()) {
			Command c = new CopyCommand(e, s);
			mc.addCommand(c);
		}
		getEditor().clearSelection();
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
