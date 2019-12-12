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
 * The interface CommandHandler defines the handler which allows to access the
 * command history provided by a view. The list of commands can be iterated
 * through using methods undo() and redo().
 * 
 * @author Eric Dubuis
 */
public interface CommandHandler {

	/**
	 * Adds a command to this command handler.
	 * 
	 * @param c
	 *            The command.
	 */
	public void addCommand(Command c);

	/**
	 * Clears the command handler. All registered commands are cleared.
	 */
	public void clear();

	/**
	 * Undoes the last command.
	 */
	public void undoLast();

	/**
	 * Redoes the last command.
	 */
	public void redoLast();

	/**
	 * Indicates whether an undo operation is possible.
	 * @return whether undo is possible
	 */
	public boolean undoPossible();

	/**
	 * Indicates whether a redo() operation is possible.
	 * @return whether a redo() operation is possible
	 */
	public boolean redoPossible();

	/**
	 * Registers a command handler listener.
	 *
	 * @param listener a command handler listener
	 */
	public void addCommandHandlerListener(CommandHandlerListener listener);

	/**
	 * Unregisters a command handler listener.
	 *
	 * @param listener a command handler listener
	 */
	public boolean removeCommandHandlerListener(CommandHandlerListener listener);
}
