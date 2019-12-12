/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework.util;

import java.awt.event.ActionEvent;

import ch.bfh.due1.jdt.framework.BaseAction;


/**
 * The action that undoes the lastly executed command.
 *
 * @author Eric Dubuis
 */
public class InexistentAction extends BaseAction {
	/** The serial version UID. */
	private static final long serialVersionUID = -6852637865056357128L;

	/**
	 * Creates an instance.
	 */
	public InexistentAction() {
	}

	/**
	 * Does nothing -- intentionally empty implementation.
	 *
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// intentionally left empty
	}

	/**
	 * Does nothing -- intentionally empty implementation.
	 *
	 * @see ch.bfh.due1.jdt.framework.BaseAction#checkAction()
	 */
	@Override
	public void checkAction() {
		// intentionally left empty
	}
}
