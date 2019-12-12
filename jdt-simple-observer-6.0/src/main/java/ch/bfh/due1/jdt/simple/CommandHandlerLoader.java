/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple;

import java.io.InputStream;
import java.util.Properties;


import org.apache.log4j.Logger;

import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.due1.jdt.framework.CommandHandlerListener;

/**
 * This is a factory for loading the command handler instance. It reads, upon
 * instantiation, a properties file an tries to construct the corresponding
 * command handler according the value of a property found in that file.
 * 
 * @author Eric Dubuis
 */
public class CommandHandlerLoader {
	/**
	 * The default name of the property file name relative to the CLASSPATH.
	 */
	public final static String DEFAULT_PROPERTIES_FILENAME = "jdt/command/commandhandler.properties";

	/**
	 * The property name of the factory class for the command handler.
	 */
	public final static String PROP_CLASSNAME = "jdt.commandhandler.classname";

	/**
	 * The logger of this class with name "org.jdt.app.simple.CommandHandlerLoader".
	 */
	private Logger log = Logger.getLogger(CommandHandlerLoader.class.getName());

	/**
	 * The properties loaded from the properties file.
	 */
	private Properties props = null;

	/**
	 * Mock command handler, does nothing.
	 * @author Eric Dubuis
	 */
	private class MockCommandHandler implements CommandHandler {
		/**
		 * Void implementation.
		 * 
		 * @see ch.bfh.due1.jdt.framework.CommandHandler#addCommand(ch.bfh.due1.jdt.framework.Command)
		 */
		@Override
		public void addCommand(Command c) {
		}

		/**
		 * Void implementation.
		 * 
		 * @see ch.bfh.due1.jdt.framework.CommandHandler#clear()
		 */
		@Override
		public void clear() {
		}

		/**
		 * Void implementation.
		 * 
		 * @see ch.bfh.due1.jdt.framework.CommandHandler#redoLast()
		 */
		@Override
		public void redoLast() {
		}

		/**
		 * Void implementation. Always returns false.
		 * 
		 * @see ch.bfh.due1.jdt.framework.CommandHandler#redoPossible()
		 */
		@Override
		public boolean redoPossible() {
			return false;
		}

		/**
		 * Void implementation.
		 * 
		 * @see ch.bfh.due1.jdt.framework.CommandHandler#undoLast()
		 */
		@Override
		public void undoLast() {
		}

		/**
		 * Void implementation. Always returns false.
		 * 
		 * @see ch.bfh.due1.jdt.framework.CommandHandler#undoPossible()
		 */
		@Override
		public boolean undoPossible() {
			return false;
		}

		/**
		 * Void implementation.
		 * 
		 * @see ch.bfh.due1.jdt.framework.CommandHandler#addCommandHandlerListener(ch.bfh.due1.jdt.framework.CommandHandlerListener)
		 */
		@Override
		public void addCommandHandlerListener(
				CommandHandlerListener listener) {
		}

		/**
		 * Void implementation. Always returns false.
		 * 
		 * @see ch.bfh.due1.jdt.framework.CommandHandler#removeCommandHandlerListener(ch.bfh.due1.jdt.framework.CommandHandlerListener)
		 */
		@Override
		public boolean removeCommandHandlerListener(
				CommandHandlerListener listener) {
			return false;
		}
	}

	/**
	 * Creates an instance of a factory for a sheet using defaults.
	 */
	public CommandHandlerLoader() throws Exception {
		this(DEFAULT_PROPERTIES_FILENAME);
	}

	/**
	 * Creates an instance of a factory for the command handler.
	 * 
	 * @param fileName
	 *            The path of the file name for the properties file that defines
	 *            the command handler as properties. The file is loaded as a
	 *            resource by the classloader.
	 */
	public CommandHandlerLoader(String fileName) throws Exception {
		log.debug("Using properties from file: " + fileName);
		this.props = loadProperties(fileName);
	}

	/**
	 * Loads the properties of the given properties file.
	 * 
	 * @param fileName
	 *            the name of a file
	 * @return the properties
	 * @throws IllegalArgumentException
	 *             if the given file could not be loaded
	 */
	private Properties loadProperties(String fileName)
			throws IllegalArgumentException {
		Properties props = null;
		props = new Properties();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream is = classLoader.getResourceAsStream(fileName);
		try {
			props.load(is);
		} catch (Exception e) {
			log.error("Cannot load properties file for command factory: "
					+ fileName, e);
			throw new IllegalArgumentException(
					"Cannot load properties file for command factory: "
							+ fileName);
		}
		return props;
	}

	/**
	 * Creates and returns the command handler.
	 * 
	 * @return a command handler
	 * @throws IllegalArgumentException
	 *             if the class found in the properties file can not be
	 *             initialized.
	 */
	private CommandHandler create() throws IllegalArgumentException {
		String classname = this.props.getProperty(PROP_CLASSNAME);
		return initialize(classname);
	}

	/**
	 * Instantiates and initializes a command handler.
	 * 
	 * @param classname
	 *            the name of a class
	 * @return a command handler
	 */
	private CommandHandler initialize(String classname) {
		CommandHandler instance = null;
		Class<?> clazz = null;
		if (classname != null) {
			try {
				clazz = Class.forName(classname);
				instance = (CommandHandler) clazz.newInstance();
				log.debug("Instantiated class: " + classname);
			} catch (InstantiationException e) {
				log.error("Cannot instantiate class: " + classname);
			} catch (IllegalAccessException e) {
				log.error("Not privileged to access instance: " + classname);
			} catch (Exception e) {
				log.error("Cannot load class: " + classname);
			}
		}
		if (instance == null) {
			instance = new MockCommandHandler();
			log.warn("Instantiated mock command handler org.jdt.app.simple.CommandHandlerLoader.MockCommandHandler");
		}
		return instance;
	}

	/**
	 * Creates and returns a command handler.
	 * 
	 * @return a command handler
	 */
	public CommandHandler createCommandHandler() {
		return create();
	}
}
