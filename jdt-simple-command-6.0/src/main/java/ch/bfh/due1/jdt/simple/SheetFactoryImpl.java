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

import ch.bfh.due1.jdt.framework.Sheet;
import ch.bfh.due1.jdt.framework.SheetFactory;

/**
 * This is a factory for a sheet instance. It reads, upon instantiation, a
 * properties file to retrieve the class name for a Sheet class. Calling method
 * createSheet() then tries to instantiate a new instance of that class.
 * 
 * @author Eric Dubuis
 */
public class SheetFactoryImpl implements SheetFactory {
	/**
	 * The default name of the property file name relative to the CLASSPATH.
	 */
	public final static String DEFAULT_SF_PROPERTIES_FILENAME = "jdt/sheet/sheet.properties";

	/**
	 * The property name of the factory class for the sheet factory.
	 */
	public final static String PROP_SHEET_CLASSNAME = "jdt.sheet.classname";

	/**
	 * The name of a Sheet class.
	 */
	private String sheetClassName = null;

	/**
	 * The logger named "jdt.app.simple.SheetFactoryImpl".
	 */
	private Logger log = Logger.getLogger(SheetFactoryImpl.class);

	/**
	 * Reads the default properties file for getting the class name for a
	 * concrete Sheet class.
	 */
	public SheetFactoryImpl() throws Exception {
		this(DEFAULT_SF_PROPERTIES_FILENAME);
	}

	/**
	 * Reads the named properties file for getting the class name for a concrete
	 * Sheet class.
	 * 
	 * @param fileName
	 *            The path of the file name for the properties file that defines
	 *            the class name as a property. The file is loaded as a resource
	 *            by the classloader.
	 */
	public SheetFactoryImpl(String fileName) {
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
	private Properties loadProperties(String fileName) {
		Properties props = null;
		props = new Properties();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream is = classLoader.getResourceAsStream(fileName);
		try {
			props.load(is);
		} catch (Exception e) {
			log.error("Cannot load properties file for sheet: " + fileName, e);
			throw new IllegalArgumentException(
					"Cannot load properties file for sheet: " + fileName);
		}
		return props;
	}

	/**
	 * Retrieves the class name for the concrete Sheet class.
	 * 
	 * @param props
	 *            the given properties
	 */
	private void retrieveClassName(Properties props) {
		this.sheetClassName = props.getProperty(PROP_SHEET_CLASSNAME);
	}

	/**
	 * Instantiates a Sheet object. If the named class cannot be instantiated
	 * then an instance of class jdt.framework.DefaultSheet is created and
	 * returned.
	 * 
	 * @param classname
	 *            the class name
	 * @return the Sheet object
	 */
	private Sheet instantiateSheet(String classname) {
		Sheet sheet = null;
		Class<?> clazz = null;
		if (classname != null) {
			try {
				clazz = Class.forName(classname);
				sheet = (Sheet) clazz.newInstance();
				log.debug("Instantiated sheet class: " + clazz.getName());
			} catch (InstantiationException e) {
				log.error("Cannot instantiate sheet class: " + classname);
			} catch (IllegalAccessException e) {
				log.error("Not privileged to access sheet instance: " + classname);
			} catch (Exception e) {
				log.error("Cannot load sheet class: " + classname);
			}
		}
		if (sheet == null) {
			sheet = new DefaultSheet();
			log.warn("Instantiated dummy sheet org.jdt.app.simple.DefaultSheet");
		}
		return sheet;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Sheet createSheet() {
		Sheet sheet = instantiateSheet(this.sheetClassName);
		return sheet;
	}
}
