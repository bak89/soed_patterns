/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import javax.swing.AbstractAction;
import javax.swing.Action;


/**
 * A base class for any kind of (GUI) actions. Handles the association with the
 * related editor.
 * 
 * @author Eric Dubuis
 */
public abstract class BaseAction extends AbstractAction {
	private static final long serialVersionUID = -6860534032948159048L;
	public static final String JDT_EDITOR = "jdt.editor.instance";

	/**
	 * Initializes this object. An Editor instance must be associated
	 * by subclass by associating it to property JDT_EDITOR.
	 */
	protected BaseAction() {
	}

	/**
	 * Returns the associated editor.
	 * 
	 * @return The associated editor.
	 */
	protected Editor getEditor() {
		return (Editor) getValue(JDT_EDITOR);
	}

	/**
	 * Allows to update the editor. Subclasses should provide a reasonable
	 * implementation.
	 */
	public abstract void checkAction();

	/**
	 * Returns the string associated to the action property
	 * Action.NAME.
	 *
	 * @return a name, or null if no name was associated upon instantiation
	 */
	public String getName() {
		return (String) getValue(Action.NAME);
	}
}
