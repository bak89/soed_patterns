/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2014
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import java.util.EventObject;

/**
 * An event generated upon reporting a state change of a command
 * handler.
 *
 * @author Eric Dubuis
 */
public class CommandHandlerEvent extends EventObject {

	private static final long serialVersionUID = 5193196068883692219L;

	/**
	 * Constructs an event object.
	 *
	 * @param source the given command handler
	 */
	public CommandHandlerEvent(CommandHandler source) {
		super(source);
	}

	/**
	 * Returns the associated command handler.
	 *
	 * @return a command handler
	 */
	public CommandHandler getCommandHandler() {
		return (CommandHandler) getSource();
	}
}
