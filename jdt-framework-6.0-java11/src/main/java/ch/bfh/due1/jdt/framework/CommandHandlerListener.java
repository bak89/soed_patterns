/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2014
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;


/**
 * Defines the listener interface for listening to a command
 * handler's state changes.
 *
 * @author Eric Dubuis
 */
public interface CommandHandlerListener {

	/**
	 * Called upon any kind of state change of a command handler.
	 *
	 * @param event a command handler's state change event
	 */
	public void commandHandlerChanged(CommandHandlerEvent event);
}
