/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package org.designpattern.stopwatch.state;

import java.io.InputStream;
import java.util.Properties;

import ch.bfh.due1.stopwatch.core.State;
import ch.bfh.due1.stopwatch.core.StateFactory;
import ch.bfh.due1.stopwatch.core.StopWatch;

/**
 * A factory allowing to create and get state objects. The class names from
 * which the state have to be instantiated are retrieved from a fixed
 * configuration file.
 */
public class StateFactoryImpl implements StateFactory {
	/**
	 * The name of the configuration file name relative to a component of the
	 * CLASSPATH.
	 */
	public final static String CONFIG_FILENAME = "statefactory.properties";

	/**
	 * The class denoting the Intermediate state.
	 */
	public final static String INTERMEDIATESTATE_CLASSNAME = "intermediatestate.classname";

	/**
	 * The class denoting the Running state.
	 */
	public final static String RUNNINGSTATE_CLASSNAME = "runningstate.classname";

	/**
	 * The class denoting the Stopped state.
	 */
	public final static String STOPPEDSTATE_CLASSNAME = "stoppedstate.classname";

	/**
	 * The class denoting the Zero state.
	 */
	public final static String IDLESTATE_CLASSNAME = "idlestate.classname";

	/**
	 * The properties loaded from the configuration file.
	 */
	private Properties props = null;

	/**
	 * Creates an instance of a factory for states.
	 */
	public StateFactoryImpl() throws Exception {
		this.props = loadProperties(CONFIG_FILENAME);
	}

	/**
	 * Loads the properties of the given configuration file.
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
			throw new IllegalArgumentException(
					"Cannot load configuration file for state factory: "
							+ fileName);
		}
		return props;
	}

	/**
	 * Creates and returns a state.
	 *
	 * @param name
	 *            the name of a state class
	 * @return a state
	 * @throws Exception
	 *             if the class found in the properties file can not be
	 *             initialized
	 */
	private State create(String name) throws Exception {
		String classname = this.props.getProperty(name);
		return initialize(classname);
	}

	/**
	 * Instantiates and initializes a command factory.
	 *
	 * @param classname
	 *            the name of a class denoting a state
	 * @return a state
	 * @throws an
	 *             exception if there is a problem
	 */
	private State initialize(String classname) throws Exception {
		State instance = null;
		Class<?> clazz = null;
		if (classname != null) {
			try {
				clazz = Class.forName(classname);
				instance = (State) clazz.newInstance();
			} catch (InstantiationException e) {
				throw new Exception("Cannot instantiate class.", e);
			} catch (IllegalAccessException e) {
				throw new Exception("Not privileged to access instance.", e);
			} catch (Exception e) {
				throw new Exception("Cannot load class: " + classname, e);
			}
		}
		return instance;
	}

	@Override
	public State createIdleState(StopWatch sw) throws Exception {
		State s = create(IDLESTATE_CLASSNAME);
		s.init(sw);
		return s;
	}

	@Override
	public State createIntermediateState(StopWatch sw) throws Exception {
		State s = create(INTERMEDIATESTATE_CLASSNAME);
		s.init(sw);
		return s;
	}

	@Override
	public State createRunningState(StopWatch sw) throws Exception {
		State s = create(RUNNINGSTATE_CLASSNAME);
		s.init(sw);
		return s;
	}

	@Override
	public State createStoppedState(StopWatch sw) throws Exception {
		State s = create(STOPPEDSTATE_CLASSNAME);
		s.init(sw);
		return s;
	}
}
