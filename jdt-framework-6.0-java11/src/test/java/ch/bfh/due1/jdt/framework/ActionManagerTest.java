/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2014
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.swing.Action;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.util.EditorAdaptor;
import ch.bfh.due1.jdt.framework.util.MockAction;

/**
 * Tests some parts of the behavior of the action manager.
 *
 * @author Eric Dubuis
 */
public class ActionManagerTest {

	@Test
	public void testDummyActionCreated() {
		Editor e = new EditorAdaptor();
		ActionManager am = new ActionManager(e);
		Action action = am.createAndGetAction("ch.bfh.due1.jdt.framework.util.InexistentAction", "name", "descr",
				"control Z", "jdt/icon/Undo16.gif");
		assertNotNull(action);
		assertEquals("name", action.getValue(Action.NAME));
		assertEquals("descr", action.getValue(Action.SHORT_DESCRIPTION));
		assertNotNull(action.getValue(Action.ACCELERATOR_KEY));
		assertNotNull(action.getValue(Action.SMALL_ICON));
	}

	/**
	 * Tests if checkAction() of registered actions is being called upon
	 * calling checkActions() on action manager.
	 */
	@Test
	public void testCheckActionBeingCalled() {
		Editor e = new EditorAdaptor();
		ActionManager am = new ActionManager(e);
		Action action = am.createAndGetAction("ch.bfh.due1.jdt.framework.util.MockAction", "Mock Action", null, null,
				null);
		am.checkActions();
		assertTrue("Expected flag set to true", ((MockAction) action).checkAction);
	}
}
