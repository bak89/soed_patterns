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
 * The common abstraction of a command. Commands operate on one or more
 * receivers. Commands are managed by the command handler.
 * Commands enable the undo facility.
 * 
 * @see CommandHandler
 * 
 * @author Eric Dubuis
 */
public interface Command {
	/**
	 * Executes a command.
	 */
	public void execute();

	/**
	 * Undo this command.
	 */
	public void undo();

	/**
	 * Adds a command to the given command. Hence, this command is a macro
	 * command. Optional method.
	 * 
	 * @param c
	 *            Adds another command to this (macro) command.
	 */
	public void addCommand(Command c);

	/**
	 * Removes a command from the given command. Hence, this command is a macro
	 * command. Optional method.
	 * 
	 * @param c
	 *            Removes a command from this (macro) command.
	 * @return
	 *            Returns true if the underlying collection is modified.
	 */
	public boolean removeCommand(Command c);
}
