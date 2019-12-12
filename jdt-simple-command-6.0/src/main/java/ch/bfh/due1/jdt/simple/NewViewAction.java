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
 * Handles the 'new view' action performed by the user.
 * 
 * @author Eric Dubuis
 */
public class NewViewAction extends BaseAction {
	/** The serial version UID. */
	private static final long serialVersionUID = 1807254561582687768L;

	/**
	 * Creates an instance.
	 * 
	 */
	public NewViewAction() {
	}

	/**
	 * Tells the editor to create a new view.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		getEditor().createView();
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
