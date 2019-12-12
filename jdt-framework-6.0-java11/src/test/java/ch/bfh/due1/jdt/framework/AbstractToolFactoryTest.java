/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.swing.Icon;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Tool;
import ch.bfh.due1.jdt.framework.ToolFactory;

/**
 * Class that completes test coverage of class AbstractToolFactory.
 * @author Eric Dubuis
 */
public class AbstractToolFactoryTest {
	/**
	 * Dummy tool factory.
	 * @author Eric Dubuis
	 */
	private class DummyToolFactory extends ToolFactory {
		/**
		 * Creates a dummy tool factory with given parameters.
		 * @param name the tool factory name
		 * @param iconName the icon name
		 */
		DummyToolFactory(String name, String iconName) {
			setName(name);
			setIconName(iconName);
		}
		/**
		 * Not used.
		 * @see ch.bfh.due1.jdt.framework.ToolFactory#getTool(ch.bfh.due1.jdt.framework.Editor)
		 */
		@Override
		protected Tool createTool(Editor e) {
			return null;
		}
	}

	/**
	 * Test method for {@link ch.bfh.due1.jdt.framework.ToolFactory#getIcon()}.
	 */
	@Test
	public void testGetIcon1() {
		DummyToolFactory t = new DummyToolFactory(null, "jdt/icon/line.png");
		Icon i = t.getIcon();
		assertNotNull(i);
	}

	/**
	 * Test method for {@link ch.bfh.due1.jdt.framework.ToolFactory#getIcon()}.
	 */
	@Test
	public void testGetIcon2() {
		DummyToolFactory t = new DummyToolFactory(null, "jdt/icon/nonexistent.png");
		Icon i = t.getIcon();
		assertNull(i);
	}

	/**
	 * Test method for {@link ch.bfh.due1.jdt.framework.ToolFactory#getIcon()}.
	 */
	@Test
	public void testGetIcon3() {
		DummyToolFactory t = new DummyToolFactory(null, "inexistent_config_file");
		Icon i = t.getIcon();
		assertNull(i);
	}

	/**
	 * Test method for {@link ch.bfh.due1.jdt.framework.ToolFactory#getIconName()}.
	 */
	@Test
	public void testGetIconName() {
		DummyToolFactory t = new DummyToolFactory(null, "xyz");
		assertEquals("xyz", t.getIconName());
	}

	/**
	 * Test method for {@link ch.bfh.due1.jdt.framework.ToolFactory#getName()}.
	 */
	@Test
	public void testGetName() {
		DummyToolFactory t = new DummyToolFactory("xyz", null);
		assertEquals("xyz", t.getName());
	}
}
