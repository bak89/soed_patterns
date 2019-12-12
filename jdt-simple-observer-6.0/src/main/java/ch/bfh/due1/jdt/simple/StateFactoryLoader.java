/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2011
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import ch.bfh.due1.jdt.simple.selection.StateFactory;


/**
 * A loader for loading a specific state factory.
 * 
 * @author Eric Dubuis
 */
public class StateFactoryLoader {
	/**
	 * The default name of the property file name relative to the CLASSPATH.
	 */
	public final static String DEFAULT_PROPERTIES_FILENAME = "jdt/selection/statefactory.properties";

	/**
	 * The property name of the factory class for the state factory loader.
	 */
	public final static String PROP_CLASSNAME = "jdt.statefactory.classname";

	/**
	 * The logger of this class with name
	 * "jdt.app.simple.selection.StateFactoryLoader".
	 */
	private Logger log = Logger.getLogger(StateFactoryLoader.class);

	/**
	 * The properties loaded from the properties file.
	 */
	private Properties props = null;

	/**
	 * Creates an instance of a factory for states using defaults.
	 */
	public StateFactoryLoader() throws Exception {
		this(DEFAULT_PROPERTIES_FILENAME);
	}

	/**
	 * Creates an instance of a factory for states.
	 * 
	 * @param fileName
	 *            The path of the file name for the properties file that defines
	 *            the state factory as a property value. The file is loaded as
	 *            a resource by the classloader.
	 */
	public StateFactoryLoader(String fileName) throws Exception {
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
			log.error("Cannot load properties file for state factory: "
					+ fileName, e);
			throw new IllegalArgumentException(
					"Cannot load properties file for state factory: "
							+ fileName);
		}
		return props;
	}

	/**
	 * Creates and returns the state factory.
	 * 
	 * @return a state factory
	 * @throws IllegalArgumentException
	 *             if the class found in the properties file can not be
	 *             initialized.
	 */
	private StateFactory create() throws IllegalArgumentException {
		String classname = this.props.getProperty(PROP_CLASSNAME);
		return initialize(classname);
	}

	/**
	 * Instantiates and initializes a state factory.
	 * 
	 * @param classname
	 *            the name of a class
	 * @return a state factory
	 */
	private StateFactory initialize(String classname) {
		StateFactory instance = null;
		Class<?> clazz = null;
		if (classname != null) {
			try {
				clazz = Class.forName(classname);
				instance = (StateFactory) clazz.newInstance();
				log.debug("Instantiated class: " + clazz.getName());
			} catch (InstantiationException e) {
				log.error("Cannot instantiate class.");
			} catch (IllegalAccessException e) {
				log.error("Not privileged to access instance.");
			} catch (Exception e) {
				log.error("Cannot load class: " + classname);
			}
		}
		if (instance == null) {
			instance = new ch.bfh.due1.jdt.simple.selection.mock.StateFactoryImpl();
			log.warn("Instantiated mock state factory org.jdt.app.simple.selection.mock.StateFactoryImpl");
		}
		return instance;
	}

	/**
	 * Creates and returns a state factory.
	 * 
	 * @return a state factory
	 */
	public StateFactory createStateFactory() {
		return create();
	}
}
