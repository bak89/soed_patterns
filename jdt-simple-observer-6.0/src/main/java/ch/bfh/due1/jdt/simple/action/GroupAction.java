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
import java.util.List;

import ch.bfh.due1.jdt.framework.BaseAction;
import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.View;
import ch.bfh.due1.jdt.simple.impl.command.GroupCommand;


/**
 * This action performs a grouping of shapes by building and executing a group
 * command.
 * 
 * @author Eric Dubuis
 */
public class GroupAction extends BaseAction {
	/** The serial version UID. */
	private static final long serialVersionUID = -1640098364528473142L;

	/**
	 * Creates a group action.
	 */
	public GroupAction() {
	}

	/**
	 * Groups the selected shapes by making a group command, executing it, and
	 * passing it over to the command handler.
	 * 
	 * @see javax.swing.AbstractAction#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		View view = getEditor().getCurrentView();
		List<Shape> shapes = view.getSelection();
		Command c = createGroupCommand(view, shapes);
		if (c != null) {
			CommandHandler h = getEditor().getCommandHandler();
			h.addCommand(c);
			c.execute();
		}
		getEditor().checkEditorState();
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.BaseAction#checkAction()
	 */
	@Override
	public void checkAction() {
		setEnabled(getEditor().getSelection().size() > 1);
	}

	/**
	 * Creates a group command. May return null if group command cannot be
	 * created.
	 * 
	 * @param view
	 *            a view
	 * @param shapes
	 *            a list of shapes
	 * @return a group command, or null
	 */
	private Command createGroupCommand(View view, List<Shape> shapes) {
		Command c = new GroupCommand(view, shapes);
		return c;
	}
}
