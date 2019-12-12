/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2014
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import static org.junit.Assert.assertSame;

import java.awt.event.ActionEvent;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.BaseAction;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.util.EditorAdaptor;

/**
 * Test the basic behavior of the BaseAction class.
 *
 * @author Eric Dubuis
 */
public class BaseActionTest {

	/**
	 * Mock class for an action.
	 * @author Eric Dubuis
	 */
	private class MockAction extends BaseAction {

		private static final long serialVersionUID = -2803591824224248031L;

		@Override
		public void actionPerformed(ActionEvent e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void checkAction() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Editor getEditor() {
			return super.getEditor();
		}
	}

	/**
	 * Test whether a configured editor is returned.
	 */
	@Test
	public void testGetEditor() {
		Editor e = new EditorAdaptor();
		BaseAction a = new MockAction();
		a.putValue(BaseAction.JDT_EDITOR, e);
		assertSame(e, ((MockAction) a).getEditor());
	}

	/**
	 * Test whether a configured name is returned.
	 */
	@Test
	public void testGetName() {
		String name = "Test";
		BaseAction a = new MockAction();
		a.putValue(BaseAction.NAME, name);
		assertSame(name, a.getName());
	}
}
