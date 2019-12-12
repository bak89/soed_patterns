/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

/**
 * An abstraction of commands that provides unsupported implementations for methods
 * addCommand() and removeCommand().
 * 
 * @author Eric Dubuis
 */
public abstract class AbstractCommand implements Command {

	/**
	 * Empty initialization of this base class.
	 */
	protected AbstractCommand() {
	}

	/**
	 * By default, this method is not supported.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Command#addCommand(Command)
	 */
	@Override
	public void addCommand(Command c) {
		throw new UnsupportedOperationException(
				"No command can be added to this command.");
	}

	/**
	 * By default, this method is not supported.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Command#removeCommand(Command)
	 */
	@Override
	public boolean removeCommand(Command c) {
		throw new UnsupportedOperationException(
				"No command can be removed from this command.");
	}
}
