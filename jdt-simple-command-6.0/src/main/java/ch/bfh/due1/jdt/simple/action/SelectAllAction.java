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
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Shape;


/**
 * Handles the 'select all' action performed by the user.
 *
 * @author Eric Dubuis
 */
public class SelectAllAction extends BaseAction {
	/** The serial version UID. */
	private static final long serialVersionUID = 1807254561582687768L;

	/**
	 * Creates an instance.
	 */
	public SelectAllAction() {
		setEnabled(false);
	}

	/**
	 *
	 *
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		Editor e = getEditor();
		e.clearSelection();
		for (Shape s : e.getShapes()) {
			e.addToSelection(s);
		}
		e.checkEditorState();
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
