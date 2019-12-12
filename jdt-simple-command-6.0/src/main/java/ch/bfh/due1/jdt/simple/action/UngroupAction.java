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
import ch.bfh.due1.jdt.simple.impl.command.UngroupCommand;


/**
 * This action performs an un-grouping of shapes by building and executing an
 * un-group command.
 *
 * @author Eric Dubuis
 */
public class UngroupAction extends BaseAction {
	/** The serial version UID. */
	private static final long serialVersionUID = 4592486618318434736L;

	/**
	 * Creates an un-group action.
	 */
	public UngroupAction() {
	}

	/**
	 * Gets the selected shapes, checks if the shapes are containers, and, if
	 * yes, un-groups them by making an un-group command and executing it. At
	 * the end, the un-group command is passed over to the command handler.
	 *
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Editor ed = getEditor();
		if (ed.getSelection().size() == 1 && ed.getSelection().get(0).isContainer()) {
			View view = ed.getCurrentView();
			Shape group = ed.getSelection().get(0);
			Command c = createUngroupCommand(view, group);
			if (c != null) {
				CommandHandler h = ed.getCommandHandler();
				h.addCommand(c);
				c.execute();
			}
			ed.checkEditorState();
		}
	}

	/**
	 * @inheritDoc
	 *
	 * @see ch.bfh.due1.jdt.framework.BaseAction#checkAction()
	 */
	@Override
	public void checkAction() {
		Editor ed = getEditor();
		if (ed.getSelection().size() == 1 && ed.getSelection().get(0).isContainer()) {
			setEnabled(true);
		} else {
			setEnabled(false);
		}
	}

	/**
	 * Creates an un-group command. May return null if un-group command cannot be
	 * created.
	 *
	 * @param view
	 *            a view
	 * @param group
	 *            a group of shapes
	 * @return a group command, or null
	 */
	private Command createUngroupCommand(View view, Shape group) {
		Command c = new UngroupCommand(view, group);
		return c;
	}
}
