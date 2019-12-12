/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple.impl.command;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


import org.apache.log4j.Logger;

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
	/** The logger. */
	private Logger log = Logger.getLogger(SimpleCommandHandler.class.getName());

	/** The list of commands to be undone. */
	private List<Command> commands = new LinkedList<Command>();

	/** The list of undone commands. */
	private List<Command> lastCommands = new LinkedList<Command>();

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
		log.debug("Adding command: " + c);
		this.commands.add(c);
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
		if (this.commands.size() > 0) {
			Command c = commands.remove(this.commands.size() - 1);
			log.debug("Undoing command: " + c);
			c.undo();
			this.lastCommands.add(c);
			notifyListeners();
		}
	}

	/**
	 * Re-executes the last command.
	 * 
	 * @see ch.bfh.due1.jdt.framework.CommandHandler#redoLast()
	 */
	@Override
	public void redoLast() {
		if (this.lastCommands.size() > 0) {
			Command c = this.lastCommands.remove(this.lastCommands.size() - 1);
			log.debug("Redoing command: " + c);
			this.commands.add(c);
			c.execute();
			notifyListeners();
		}
	}

	/**
	 * Tells if there are commands having been undone.
	 * 
	 * @see ch.bfh.due1.jdt.framework.CommandHandler#redoPossible()
	 */
	@Override
	public boolean redoPossible() {
		boolean rval = this.lastCommands.size() > 0;
		log.debug("Redo possible: " + rval);
		return rval;
	}

	/**
	 * Tells if there are commands that can be undone.
	 * 
	 * @see ch.bfh.due1.jdt.framework.CommandHandler#undoPossible()
	 */
	@Override
	public boolean undoPossible() {
		boolean rval = false;
		if (this.commands.size() > 0) {
			rval = true;
		}
		log.debug("Undo possible: " + rval);
		return rval;
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
