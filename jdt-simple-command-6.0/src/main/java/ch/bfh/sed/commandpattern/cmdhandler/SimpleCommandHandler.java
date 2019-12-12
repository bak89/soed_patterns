/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.sed.commandpattern.cmdhandler;

import java.util.HashSet;
import java.util.Set;

import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.due1.jdt.framework.CommandHandlerEvent;
import ch.bfh.due1.jdt.framework.CommandHandlerListener;

/**
 * This class manages the commands. Registered commands can be undone (provided
 * they are undoable) and redone.
 *
 * @author Eric Dubuis
 */
public class SimpleCommandHandler implements CommandHandler {
	// TODO: Complete...

	/** The set of listeners for us. */
	private Set<CommandHandlerListener> listeners =
			new HashSet<>();

	/**
	 * Creates a command handler. Notice that it is not associated with an
	 * editor. To associate it with an editor call setEditor(). An associated
	 * editor receives a call to checkEditorState() upon calling each modifier.
	 */
	public SimpleCommandHandler() {
		// Empty
	}

	/**
	 * Adds a command.
	 *
	 * @see ch.bfh.due1.jdt.framework.CommandHandler#addCommand(ch.bfh.due1.jdt.framework.Command)
	 */
	@Override
	public void addCommand(Command c) {
		// TODO: Complete...
		notifyListeners();
	}


	/**
	 * Unsupported implementation.
	 *
	 * @see ch.bfh.due1.jdt.framework.CommandHandler#clear()
	 */
	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Undoes the last command.
	 *
	 * @see ch.bfh.due1.jdt.framework.CommandHandler#undoLast()
	 */
	@Override
	public void undoLast() {
		// TODO: Complete...
		notifyListeners();
	}

	/**
	 * Re-executes the last command.
	 *
	 * @see ch.bfh.due1.jdt.framework.CommandHandler#redoLast()
	 */
	@Override
	public void redoLast() {
		// TODO: Complete...
		notifyListeners();
	}

	/**
	 * Tells if there are commands having been undone.
	 *
	 * @see ch.bfh.due1.jdt.framework.CommandHandler#redoPossible()
	 */
	@Override
	public boolean redoPossible() {
		// TODO: Complete...
		return false;
	}

	/**
	 * Tells if there are commands that can be undone.
	 *
	 * @see ch.bfh.due1.jdt.framework.CommandHandler#undoPossible()
	 */
	@Override
	public boolean undoPossible() {
		// TODO: Complete...
		return false;
	}

	/**
	 * Returns the class name of this command handler implementation.
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getName();
	}

	/**
	 * Notify associated listeners.
	 */
	private void notifyListeners() {
		CommandHandlerEvent event = new CommandHandlerEvent(this);
		for (CommandHandlerListener l : this.listeners) {
			l.commandHandlerChanged(event);
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void addCommandHandlerListener(CommandHandlerListener listener) {
		this.listeners.add(listener);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean removeCommandHandlerListener(CommandHandlerListener listener) {
		return 	this.listeners.remove(listener);
	}
}
