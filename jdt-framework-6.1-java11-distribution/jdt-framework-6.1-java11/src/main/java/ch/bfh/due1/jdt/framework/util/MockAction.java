/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2014
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework.util;

import java.awt.event.ActionEvent;

import ch.bfh.due1.jdt.framework.BaseAction;


/**
 * Mock class of an action derived from BaseAction class.
 *
 * @author Eric Dubuis
 */
public class MockAction extends BaseAction {
	private static final long serialVersionUID = -2803591824224248031L;

	/** Flag indicating that checkAction() was called. */
	public boolean checkAction = false;

	/**
	 * Unsupported operation.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Set flag 'checkAction' to true upon being called.
	 */
	@Override
	public void checkAction() {
		this.checkAction = true;
	}
}
