/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple;

import java.awt.event.ActionEvent;

import ch.bfh.due1.jdt.framework.BaseAction;


/**
 * Handles the 'exit' action performed by the user.
 *
 * @author Eric Dubuis
 */
public class ExitAction extends BaseAction {
	/** The serial version UID. */
	private static final long serialVersionUID = 1807254561582687768L;

	/**
	 * Creates an instance.
	 *
	 */
	public ExitAction() {
	}

	/**
	 * FIXME provide description
	 *
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		// FIXME Check if we can safely terminate the JDT editor.
		System.exit(0);
	}

	/**
	 * Checks whether the cut action is enabled or not.
	 *
	 * @see ch.bfh.due1.jdt.framework.BaseAction#checkAction()
	 */
	@Override
	public void checkAction() {
		setEnabled(true);
	}
}
