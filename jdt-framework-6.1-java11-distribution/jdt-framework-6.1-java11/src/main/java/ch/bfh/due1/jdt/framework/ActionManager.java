/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2014
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;


/**
 * This framework class creates and manages actions. Managed actions must be
 * derived from the JDT-specific action class <code>BaseAction</code>.
 * Upon creating an action, it is associated with an <code>Editor</code>
 * instance.
 *
 * @author Eric Dubuis
 */
public class ActionManager {
	/**
	 * The logger.
	 */
	private Logger log = Logger.getLogger(ActionManager.class);

	/** The associated Editor instance. */
	private Editor editor;

	/** The set of managed actions. */
	private Set<BaseAction> actions = new HashSet<>();

	/**
	 * A mock action class for the case a specified action class
	 * cannot be instantiated.
	 */
	private class DummyAction extends BaseAction {
		/** Default serial UID. */
		private static final long serialVersionUID = 1L;
		private DummyAction() {
			setEnabled(false);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			log.warn("No action class defined for: " + getName());
		}
		@Override
		public void checkAction() {
		}
	}

	/**
	 * Constructs the action manager. Typically it is the only
	 * instance of an application.
	 *
	 * @param editor the associated editor instance
	 */
	public ActionManager(Editor editor) {
		this.editor = editor;
	}

	/**
	 * Creates and configures an action, and stores it. If the action cannot
	 * be created, a mock action is created in its place.
	 *
	 * @param classname the fully qualified name of the action class
	 * @param name the name of the action (for example used as menu item names)
	 * @param shortDescription a description (optional)
	 * @param accelerator a short cut (optional)
	 * @param iconpath a (relative) path to an icon representing this action
	 * @return an action
	 */
	public Action createAndGetAction(String classname, String name,
			String shortDescription, String accelerator, String iconpath) {
		Action action = initializeAction(classname);
		if (name == null || "".equals(name))
			action.putValue(Action.NAME, "No Name");
		else
			action.putValue(Action.NAME, name);
		if (shortDescription != null && !"".equals(shortDescription))
			action.putValue(Action.SHORT_DESCRIPTION, shortDescription);
		if (accelerator != null && !"".equals(accelerator))
			action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
		if (iconpath != null && !"".equals(iconpath)) {
			Icon icon = loadIcon(iconpath);
			if (icon != null)
				action.putValue(Action.SMALL_ICON, icon);
		}
		this.actions.add((BaseAction) action);
		// Associate editor to action.
		action.putValue(BaseAction.JDT_EDITOR, this.editor);
		return action;
	}

	/**
	 * Checks and enables/disables each managed action.
	 */
	public void checkActions() {
		for (BaseAction ba : this.actions) {
			ba.checkAction();
		}
	}

	/**
	 * Initializes an action instance.
	 *
	 * @param classname its class name
	 * @return the Action instance
	 */
	private Action initializeAction(final String classname) {
		Action action = null;
		if (classname != null && !"".equals(classname)) {
			Class<?> clazz = null;
			try {
				clazz = Class.forName(classname);
				action = (Action) clazz.newInstance();
			} catch (InstantiationException e) {
				log.error("Cannot instantiate action class: " + classname);
			} catch (IllegalAccessException e) {
				log.error("Not privileged to access action instance: "
						+ classname);
			} catch (Throwable e) {
				log.error("Cannot load action class: " + classname);
				e.printStackTrace();
			}
		}
		if (action == null) {
			// Instantiate a mock action such that menu item can
			// be displayed.
			action = new DummyAction();
			log.warn("Instantiated mock action class.");
		}
		return action;
	}

	/**
	 * Loads an icon, given a string.
	 *
	 * @param iconpath the path to an icon
	 * @return an icon, or null if icon cannot be created
	 */
	private final Icon loadIcon(String iconpath) {
		Icon icon = null;
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream is = classLoader.getResourceAsStream(iconpath);
		if (is != null && is instanceof BufferedInputStream) {
			BufferedInputStream bis = (BufferedInputStream) is;
			try {
				byte[] iconData = new byte[bis.available()];
				bis.read(iconData);
				icon = new ImageIcon(iconData);
				bis.close();
			} catch (IOException e) {
				// Log that icon cannot be loaded.
				log.warn("Could not load icon: " + iconpath);
			}
		} else {
			// Log that icon cannot be found.
			log.warn("Could not find icon: " + iconpath);
		}
		return icon;
	}
}
