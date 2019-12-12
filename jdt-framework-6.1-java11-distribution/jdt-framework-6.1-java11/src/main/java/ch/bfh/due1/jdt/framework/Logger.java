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
 * Represents a logger. Concrete loggers are adapters for the log4j or the
 * standard java.util.logging frameworks.
 * 
 * @author Eric Dubuis
 */
public interface Logger {
	/**
	 * Logs the given message at the info level.
	 * 
	 * @param message
	 *            The message to log.
	 */
	public void info(String message);

	/**
	 * Logs the given message at the debug level.
	 * 
	 * @param message
	 *            The message to log.
	 */
	public void debug(String message);
}
