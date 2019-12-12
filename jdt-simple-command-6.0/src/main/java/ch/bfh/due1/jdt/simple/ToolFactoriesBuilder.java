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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import org.apache.log4j.Logger;

import ch.bfh.due1.jdt.framework.ToolFactory;

/**
 * This builder for tool factories reads, upon instantiation, a properties file
 * an tries to construct the corresponding tool factories according the
 * properties found in that file. Tries to build a default tool factory
 * (typically for a selection tool) and a list of other tool factories.
 * Returns the tool factories when being asked to do so.
 * 
 * @author Eric Dubuis
 */
public class ToolFactoriesBuilder {
	/**
	 * The default name of the property file name relative to the CLASSPATH.
	 */
	public final static String DEFAULT_TFB_PROPERTIES_FILENAME = "jdt/tool/toolfactories.properties";

	/**
	 * The property name of the factory class for the default tool factory.
	 */
	public final static String PROP_DEFAULTFACTORY_CLASSNAME = "jdt.tool.defaultfactory.classname";

	/**
	 * The property name of the name of the default tool factory.
	 */
	public final static String PROP_DEFAULTFACTORY_TOOLNAME = "jdt.tool.defaultfactory.toolname";

	/**
	 * The property name of the icon of the default tool factory.
	 */
	public final static String PROP_DEFAULTFACTORY_ICON = "jdt.tool.defaultfactory.icon";

	/**
	 * The property name of the factory class for a tool factory.
	 */
	public final static String PROP_FACTORY_CLASSNAME = "jdt.tool.factory.classname";

	/**
	 * The property name of the name of a tool factory.
	 */
	public final static String PROP_FACTORY_TOOLNAME = "jdt.tool.factory.toolname";

	/**
	 * The property name of the icon of a tool factory.
	 */
	public final static String PROP_FACTORY_ICON = "jdt.tool.factory.icon";

	/**
	 * The default tool factory.
	 */
	private ToolFactory defaultFactory = null;

	/**
	 * Holds the other tool factories.
	 */
	private List<ToolFactory> otherFactories = new ArrayList<>();

	/**
	 * The logger.
	 */
	private Logger log = Logger.getLogger(ToolFactoriesBuilder.class);

	/**
	 * Creates an instance of a factory for tool factories using defaults.
	 * 
	 */
	public ToolFactoriesBuilder() throws Exception {
		this(DEFAULT_TFB_PROPERTIES_FILENAME);
	}

	/**
	 * Creates an instance of a factory for tool factories.
	 * 
	 * @param fileName
	 *            The path of the file name for the properties file that defines
	 *            the tool factories as properties. The file is loaded as a
	 *            resource by the classloader.
	 */
	public ToolFactoriesBuilder(String fileName) throws Exception {
		log.debug("Using properties from file: " + fileName);
		Properties props = loadProperties(fileName);
		createDefaultFactory(props);
		createOtherFactories(props);
	}

	/**
	 * Loads the properties from the named properties file.
	 * @param fileName the given file name
	 * @return a Properties object
	 */
	private Properties loadProperties(String fileName) {
		Properties props = null;
		props = new Properties();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream is = classLoader.getResourceAsStream(fileName);
		try {
			props.load(is);
		} catch (Exception e) {
			log.error("Cannot load factories factory properties file: "
					+ fileName, e);
			throw new IllegalArgumentException(
					"Cannot load factories factory properties: " + fileName);
		}
		return props;
	}

	/**
	 * Given a Properties object, tries to construct the default tool factory
	 * and initializes the corresponding instance variable.
	 * @param props a Properties object
	 */
	private void createDefaultFactory(Properties props) {
		String classname = props.getProperty(PROP_DEFAULTFACTORY_CLASSNAME);
		String toolname = props.getProperty(PROP_DEFAULTFACTORY_TOOLNAME);
		String iconname = props.getProperty(PROP_DEFAULTFACTORY_ICON);
		log.debug("Trying to initialize the factory for the default tool");
		log.debug("Class name: "  + classname);
		log.debug("Tool name: " + toolname);
		log.debug("Icon name: " + iconname);
		this.defaultFactory = initializeToolFactory(classname, toolname,
				iconname);
		if (this.defaultFactory != null) {
			log.debug("Success!");
		}
	}

	/**
	 * Tries to construct the other tool factories and adds them to
	 * the list of tool factories.
	 * @param props a Properties object
	 */
	private void createOtherFactories(Properties props) {
		boolean done = false;
		int i = 0; // counter for additional tool factories
		while (!done) {
			String propFactoryClassame = PROP_FACTORY_CLASSNAME + "."
					+ new Integer(i).toString();
			String classname = props.getProperty(propFactoryClassame);
			if (classname != null) {
				String propFactoryToolame = PROP_FACTORY_TOOLNAME + "."
						+ new Integer(i).toString();
				String toolname = props.getProperty(propFactoryToolame);
				String propFactoryIcon = PROP_FACTORY_ICON + "."
						+ new Integer(i).toString();
				String iconname = props.getProperty(propFactoryIcon);
				log.debug("Trying to initialize the factory for another tool");
				log.debug("Class name: "  + classname);
				log.debug("Tool name: " + toolname);
				log.debug("Icon name: " + iconname);
				ToolFactory tf = initializeToolFactory(classname, toolname,
						iconname);
				if (tf != null) {
					this.otherFactories.add(tf);
					log.debug("Success!");
				} else {
					log.error("Could not initialze tool factory: " +
							classname);
				}
			} else {
				done = true;
			}
			i++;
		}
	}

	/**
	 * Initializes a tool factory.
	 * @param classname its class name
	 * @param toolname its tool name
	 * @param iconname the name of its icon
	 * @return the ToolFactory instance
	 */
	private ToolFactory initializeToolFactory(String classname,
			String toolname, String iconname) {
		ToolFactory tf = null;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(classname);
			tf = (ToolFactory) clazz.newInstance();
		} catch (InstantiationException e) {
			log.error("Cannot instantiate tool factory class: " + classname);
		} catch (IllegalAccessException e) {
			log.error("Not privileged to access tool factory instance: " + classname);
		} catch (Exception e) {
			log.error("Cannot load tool factory class: " + classname);
		}
		if (tf != null) {
			tf.setName(toolname);
			tf.setIconName(iconname);
		}
		return tf;
	}

	/**
	 * Returns the factory for the default tool.
	 * 
	 * @return A factory for the default tool.
	 */
	public ToolFactory getFactoryForDefaultTool() {
		return this.defaultFactory;
	}

	/**
	 * Returns an ordered list of other tool factories. The order of
	 * the factories can be used to set up a tool bar, for example.
	 * 
	 * @return An ordered list of other tool factories.
	 */
	public List<ToolFactory> getOtherToolFactories() {
		return this.otherFactories;
	}
}
