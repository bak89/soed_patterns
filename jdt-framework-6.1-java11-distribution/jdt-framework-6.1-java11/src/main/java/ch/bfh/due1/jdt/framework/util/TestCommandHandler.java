/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework.util;

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
public class TestCommandHandler implements CommandHandler {
	/** The logger. */
	private Logger log = Logger.getLogger(this.getClass().getName());

	/** A list of commands. */
	private List<Command> undoableCommands = new LinkedList<Command>();

	/** A list of commands. */
	private List<Command> redoableCommands = new LinkedList<Command>();

	/** The set of listeners for us. */
	private Set<CommandHandlerListener> listeners =
			new HashSet<>();

	/**
	 * Creates an instance.
	 */
	public TestCommandHandler() {
	}

	/**
	 * Adds a command.
	 * 
	 * @see ch.bfh.due1.jdt.framework.CommandHandler#addCommand(ch.bfh.due1.jdt.framework.Command)
	 */
	@Override
	public void addCommand(Command c) {
		log.debug("Adding command: " + c);
		this.undoableCommands.add(c);
		checkEditor();
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
		if (this.undoableCommands.size() > 0) {
			Command c = undoableCommands.remove(this.undoableCommands.size() - 1);
			log.debug("Undoing command: " + c);
			c.undo();
			this.redoableCommands.add(c);
			checkEditor();
		}
	}

	/**
	 * Re-executes the last command.
	 * 
	 * @see ch.bfh.due1.jdt.framework.CommandHandler#redoLast()
	 */
	@Override
	public void redoLast() {
		if (this.redoableCommands.size() > 0) {
			Command c = this.redoableCommands.remove(this.redoableCommands.size() - 1);
			log.debug("Redoing command: " + c);
			this.undoableCommands.add(c);
			c.execute();
			checkEditor();
		}
	}

	/**
	 * Tells if there are commands having been undone.
	 * 
	 * @see ch.bfh.due1.jdt.framework.CommandHandler#redoPossible()
	 */
	@Override
	public boolean redoPossible() {
		boolean rval = this.redoableCommands.size() > 0;
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
		if (this.undoableCommands.size() > 0) {
			rval = true;
		}
		log.debug("Undo possible: " + rval);
		return rval;
	}

	private void checkEditor() {
		notifyListeners();
	}

	@Override
	public void addCommandHandlerListener(CommandHandlerListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public boolean removeCommandHandlerListener(CommandHandlerListener listener) {
		return 	this.listeners.remove(listener);
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
}
