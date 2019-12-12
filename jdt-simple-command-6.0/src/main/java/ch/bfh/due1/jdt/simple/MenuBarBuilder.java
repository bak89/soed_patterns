/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2014
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;

import ch.bfh.due1.jdt.framework.ActionManager;

/**
 * This builder for the menu bar reads, upon instantiation, a properties file
 * an tries to construct the corresponding menus and menu items according the
 * properties found in that file. Returns the menu bar when being asked to do
 * so.
 *
 * @author Eric Dubuis
 */
public class MenuBarBuilder {
	/**
	 * The default name of the property file name relative to the CLASSPATH.
	 */
	public final static String MENU_PROPERTIES_FILENAME = "jdt/menu/menu.properties";

	/** The name of the property specifying the menu type. */
	public final static String MENUTYPE = "jdt.menu.type";
	/** The name of the property specifying the menu name. */
	public final static String MENUNAME = "jdt.menu.name";

	/** The name of the property specifying the menu item. */
	public final static String MENUITEMTYPE = "jdt.menu.item.type";
	/** The name of the property specifying the menu item name. */
	public final static String MENUITEMNAME = "jdt.menu.item.name";
	/** The name of the property specifying the menu item description. */
	public final static String MENUITEMDESCRIPTION = "jdt.menu.item.description";
	/** The name of the property specifying the accelerator of a menu item. */
	public final static String MENUITEMACCELERATOR = "jdt.menu.item.accelerator";
	/** The name of the property specifying the path to an icon for the menu item. */
	public final static String MENUITEMICONPATH = "jdt.menu.item.iconpath";
	/** The name of the property specifying the action class associated to the menu item. */
	public final static String MENUITEMACTIONCLASS = "jdt.menu.item.actionclass";

	/** The name of the property value for a regular menu. */
	public final static String REGULARMENU = "Regular";
	/** The name of the property value for a spacer pseudo menu. */
	public final static String SPACERMENU = "Spacer";

	/** The name of the property value for a regular menu item. */
	public final static String REGULARMENUITEM = "Regular";
	/** The name of the property value for a separator menu item. */
	public final static String SEPARATORMENUITEM = "Separator";


	/**
	 * The logger.
	 */
	private Logger log = Logger.getLogger(MenuBarBuilder.class);

	/**
	 * Upon construction, an instance of this class is built describing
	 * a menu and its menu items.
	 *
	 * @author Eric Dubuis
	 */
	private static class Menu {
		/** The name of the menu. */
		private String name;
		/** The descriptions of menu items. */
		private List<MenuItem> menuItems;
		private Menu(String name) {
			this.name = name;
			this.menuItems = new ArrayList<>();
		}
		private void addMenuItem(MenuItem menuItem) {
			this.menuItems.add(menuItem);
		}
	}

	/**
	 * An abstract description of a menu item.
	 *
	 * @author Eric Dubuis
	 */
	private static abstract class MenuItem {
	}

	/**
	 * Upon construction, an instance of this class is built describing
	 * a menu item.
	 *
	 * @author Eric Dubuis
	 */
	private static class RegularMenuItem extends MenuItem {
		/** The name. */
		private String name;
		/** The description of the menu item. */
		private String description;
		/** The accelerator of the menu item. */
		private String accelerator;
		/** The path to an icon of the menu item. */
		private String iconpath;
		/** The action class of the menu item. */
		private String actionclass;
		private RegularMenuItem(String name, String description,
				String accelerator, String iconpath, String actionclass) {
			super();
			this.name = name;
			this.description = description;
			this.accelerator = accelerator;
			this.iconpath = iconpath;
			this.actionclass = actionclass;
		}
	}

	/**
	 * Upon construction, in instance of this marker class is built
	 * for a menu item separator.
	 *
	 * @author Eric Dubuis
	 */
	private static class MenuItemSeparator extends MenuItem {
	}

	/**
	 * The menu bar being constructed upon instantiating this class.
	 */
	private final JMenuBar menuBar;

	/**
	 * Upon instantiation, tries to create a JMenuBar instance.
	 *
	 * @param am an action manager asked to actually instantiate an action
	 */
	public MenuBarBuilder(ActionManager am) {
		// load properties for menu descriptions
		Properties props = loadProperties(MENU_PROPERTIES_FILENAME);
		// parse properties descriptive menu objects
		List<Menu> menus = parseProperties(props);
		// create menu bar
		this.menuBar = createMenuBar(am, menus);
	}

	/**
	 * Parses the Properties object and tries to create a list of
	 * menu descriptions.
	 *
	 * @param props a description of a menu bar in terms of key/value pairs
	 * @return a list of menu descriptions
	 */
	private List<Menu> parseProperties(Properties props) {
		boolean outerLoopdDone = false;
		int i = 0;
		List<Menu> menus = new ArrayList<>();
		// process menus
		while (!outerLoopdDone) {
			String menuTypePropName = MENUTYPE + "." + new Integer(i).toString();
			String menuType = props.getProperty(menuTypePropName);
			if (menuType != null) {
				// a menus specification, two cases: regular, spacer
				if (REGULARMENU.equals(menuType)) {
					String menuNamePropName = MENUNAME +
							"." + new Integer(i).toString();
					String menuName = props.getProperty(menuNamePropName);

					Menu menu = new Menu(menuName);
					menus.add(menu);

					boolean innerLoopDone = false;
					int k = 0;
					// process menu items
					while (!innerLoopDone) {
						String menuItemTypePropName = MENUITEMTYPE +
								"." + new Integer(i).toString() +
								"." + new Integer(k).toString();
						String menuItemType = props
								.getProperty(menuItemTypePropName);
						if (menuItemType != null) {
							// menu item specification, two cases: regular,
							// separator
							if (REGULARMENUITEM.equals(menuItemType)) {
								// Create regular menu item info object.
								String menuItemNamePropName = MENUITEMNAME +
										"." + new Integer(i).toString() +
										"." + new Integer(k).toString();
								String menuItemName = props
										.getProperty(menuItemNamePropName);

								String menuItemDescriptionPropName = MENUITEMDESCRIPTION
										+ "." + new Integer(i).toString()
										+ "." + new Integer(k).toString();
								String menuItemDescription = props
										.getProperty(menuItemDescriptionPropName);

								String menuItemAcceleratorPropName = MENUITEMACCELERATOR
										+ "." + new Integer(i).toString()
										+ "." + new Integer(k).toString();
								String menuItemAccelerator = props
										.getProperty(menuItemAcceleratorPropName);

								String menuItemIconpathPropName = MENUITEMICONPATH
										+ "." + new Integer(i).toString()
										+ "." + new Integer(k).toString();
								String menuItemIconpath = props
										.getProperty(menuItemIconpathPropName);

								String menuItemAcctionclassPropName = MENUITEMACTIONCLASS
										+ "." + new Integer(i).toString()
										+ "." + new Integer(k).toString();
								String menuItemAcctionclass = props
										.getProperty(menuItemAcctionclassPropName);

								MenuItem menuItem = new RegularMenuItem(
										menuItemName, menuItemDescription,
										menuItemAccelerator, menuItemIconpath,
										menuItemAcctionclass);

								// Add menu item info object to menu info
								// object.
								menu.addMenuItem(menuItem);
							} else if (SEPARATORMENUITEM.equals(menuItemType)) {
								// Adding separator menu item
								MenuItemSeparator mis = new MenuItemSeparator();
								menu.addMenuItem(mis);
							} else {
								// unknown menu item item type -- log and skip
								// it
								log.error("Unsupported menu type: "
										+ menuItemType);
							}
							// increment inner loop counter to process next menu
							// item specification
							k++;
						} else {
							// no more menu items for the current menu
							innerLoopDone = true;
						}
					}
				} else if (SPACERMENU.equals(menuType)) {
					// FIXME Implement spacer menu type
					log.warn("\"Spacer\" menu type not yet implemented.");
				} else {
					// unknown menu type -- log and skip it
					log.error("Unsupported menu type: " + menuType);
				}
				// increment out loop counter to process next menu specification
				i++;
			} else {
				// no more menus
				outerLoopdDone = true;
			}
		}
		return menus;
	}

	/**
	 * Creates a menu bar, given a list of menu descriptions.
	 * @param am an action manager allowing to instantiate actions
	 * @param menus a list of menu descriptions
	 * @return a menu bar instance
	 */
	private JMenuBar createMenuBar(ActionManager am, List<Menu> menus) {
		// create menu bar
		JMenuBar mbar = new JMenuBar();
		// loop: create menus
		for (Menu m : menus) {
			mbar.add(createJMenu(am, m));
		}
		return mbar;
	}

	/**
	 * Creates a menu instance.
	 * @param am an action manager allowing to instantiate actions
	 * @param menu a menu description
	 * @return a menu instance
	 */
	private JMenu createJMenu(ActionManager am, Menu menu) {
		// create menu
		JMenu jmenu = new JMenu(menu.name);
		// loop: create menu entries
		for (MenuItem mi : menu.menuItems) {
			if (mi instanceof RegularMenuItem) {
				jmenu.add(createJMenuItem(am, (RegularMenuItem) mi));
			}
			if (mi instanceof MenuItemSeparator) {
				jmenu.addSeparator();
			}
		}
		return jmenu;
	}

	/**
	 * Creates a menu instance.
	 * @param am an action manager allowing to instantiate actions
	 * @param item a menu item description
	 * @return a menu item instance
	 */
	private JMenuItem createJMenuItem(ActionManager am, RegularMenuItem item) {
		// create action
		Action a = am.createAndGetAction(
				item.actionclass,
				item.name,
				item.description,
				item.accelerator,
				item.iconpath);
		JMenuItem mitem = new JMenuItem(a);
		if (item.accelerator != null && !"".equals(item.accelerator)) {
			KeyStroke ks = KeyStroke.getKeyStroke(item.accelerator);
			mitem.setAccelerator(ks);
		}
		return mitem;
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
			log.error("Cannot load menu properties file: "
					+ fileName, e);
			throw new IllegalArgumentException(
					"Cannot load menu properties file: " + fileName);
		}
		return props;
	}

	/**
	 * Returns the JMenuBar instance.
	 *
	 * @return a menu bar instance
	 */
	public JMenuBar getMenuBar() {
		return this.menuBar;
	}
}
