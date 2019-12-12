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

import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.View;
import ch.bfh.due1.jdt.framework.ViewFactory;

/**
 * This is a factory for creating a view instance. It reads, upon instantiation,
 * a properties file and retrieves the class name for a concrete View class.
 * Method createView() instantiates a concrete View object.
 * 
 * @author Eric Dubuis
 */
public class ViewFactoryImpl implements ViewFactory {
	/**
	 * The default name of the property file name relative to the CLASSPATH.
	 */
	public final static String DEFAULT_FACTORY_PROPERTIES_FILENAME = "jdt/view/view.properties";

	/**
	 * The property name of the factory class for the view.
	 */
	public final static String PROP_VIEW_CLASSNAME = "jdt.view.classname";

	/**
	 * The name of the view class.
	 */
	private String viewClassName = null;

	/**
	 * The logger name "jdt.app.simple.ViewFactoryImpl".
	 */
	private Logger log = Logger.getLogger(ViewFactoryImpl.class);

	/**
	 * Creates an instance of a factory for a sheet using defaults.
	 */
	public ViewFactoryImpl() throws Exception {
		this(DEFAULT_FACTORY_PROPERTIES_FILENAME);
	}

	/**
	 * Creates an instance of a view.
	 * 
	 * @param fileName
	 *            The path of the file name for the properties file that defines
	 *            the view class. The file is loaded as a resource by the
	 *            classloader.
	 */
	public ViewFactoryImpl(String fileName) throws Exception {
		log.debug("Using properties from file: " + fileName);
		Properties props = loadProperties(fileName);
		retrieveClassName(props);
	}

	/**
	 * Loads the name properties file.
	 * 
	 * @param fileName
	 *            the name of the file
	 * @return the properties found in the file
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
			log.error("Cannot load properties file for view: "
					+ fileName, e);
			throw new IllegalArgumentException(
					"Cannot load properties file for view: "
							+ fileName);
		}
		return props;
	}

	/**
	 * Retrieves the class name for the concrete View class.
	 * 
	 * @param props
	 *            the given properties
	 */
	private void retrieveClassName(Properties props)
			throws IllegalArgumentException {
		this.viewClassName = props.getProperty(PROP_VIEW_CLASSNAME);
	}

	/**
	 * Instantiates a View object. If the named class cannot be instantiated
	 * then null is returned.
	 * 
	 * @param classname
	 *            the class name
	 * @return the Sheet object
	 */
	private View instantiateView(String classname) {
		View instance = null;
		Class<?> clazz = null;
		if (classname != null) {
			try {
				clazz = Class.forName(classname);
				instance = (View) clazz.newInstance();
				log.error("Instantiated view class: "
						+ clazz.getName());
			} catch (InstantiationException e) {
				log.error("Cannot instantiate view class: " + classname);
			} catch (IllegalAccessException e) {
				log.error("Not privileged to access view instance: " + classname);
			} catch (Exception e) {
				log.error("Cannot load view class: " + classname);
			}
		}
		if (instance == null) {
			instance = new DefaultView();
			log.warn("Instantiated default view org.jdt.app.simple.DefaultView");
		}
		return instance;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public View createView(Editor e) {
		View view = instantiateView(this.viewClassName);
		if (view != null) {
			view.setEditor(e);
		}
		return view;
	}
}
