/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;


/**
 * Partial implementation of a tool factory. Subclasses must implement the
 * createTool(Editor) factory method.
 *
 * @author Eric Dubuis
 */
public abstract class ToolFactory {
	/** The logger named "org.jdt.framework.AbstractToolFactory". */
	private Logger log = Logger.getLogger(ToolFactory.class);

	/** the name of the tool. */
	private String toolName;

	/** The icon of the tool. */
	private Icon icon = null;

	/** The name of the icon. */
	private String iconName;

	/**
	 * Null-arg constructor, which is also required by sub classes
	 * in order to be loadable by the tool factories builder.
	 */
	protected ToolFactory() {
	}

	/**
	 * Returns a tool operating for an editor.
	 * The tool must be created by a subclass
	 * of this class.
	 *
	 * @param e
	 *            the editor
	 * @return a tool
	 */
	public final Tool getTool(Editor e) {
		return createTool(e);
	}

	/**
	 * Returns an icon which can be used to provide access to the tool within a
	 * tool bar. If no icon is available then null is returned.
	 *
	 * @return an icon
	 */
	public final Icon getIcon() {
		if (this.icon == null) {
			if (getIconName() != null) {
				ClassLoader classLoader = Thread.currentThread()
						.getContextClassLoader();
				InputStream is = classLoader.getResourceAsStream(getIconName());
				if (is != null && is instanceof BufferedInputStream) {
					BufferedInputStream bis = (BufferedInputStream) is;
					try {
						byte[] iconData = new byte[bis.available()];
						bis.read(iconData);
						this.icon = new ImageIcon(iconData);
						bis.close();
					} catch (IOException e) {
						// Do nothing, cannot create the icon; just report
						// error.
						log.debug("Cannot create icon with given resource path: "
								+ getIconName());
					}
				} else {
					log.debug("Cannot create icon with given resource path: "
							+ getIconName());
				}
			}
		}
		return this.icon;
	}

	/**
	 * Returns the name / path of the tool icon.
	 *
	 * @return the name / path of the tool's icon.
	 */
	public final String getIconName() {
		return this.iconName;
	}

	/**
	 * Sets the name / path of the tool icon.
	 *
	 * @param name
	 *            The tool's icon name / path.
	 */
	public final void setIconName(String name) {
		this.iconName = name;
	}

	/**
	 * Returns a name which can be used in a menu.
	 *
	 * @return The tool name.
	 */
	public final String getName() {
		return this.toolName;
	}

	/**
	 * Sets the name of the tool.
	 *
	 * @param name
	 *            The tool name.
	 */
	public final void setName(String name) {
		this.toolName = name;
	}

	/**
	 * Creates and returns tool. Subclasses must provide
	 * a corresponding implementation.
	 *
	 * @return a tool
	 */
	protected abstract Tool createTool(Editor e);
}
