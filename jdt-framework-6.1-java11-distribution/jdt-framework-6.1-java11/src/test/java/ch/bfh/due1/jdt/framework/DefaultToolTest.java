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
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.DefaultTool;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Logger;
import ch.bfh.due1.jdt.framework.util.EditorAdaptor;

/**
 * Tests methods of class AbstractTool that are not yet tested.
 * 
 * @author Eric Dubuis
 */
public class DefaultToolTest {
	/**
	 * Dummy logger class.
	 * 
	 * @author Eric Dubuis
	 */
	private class DummyLogger implements Logger {
		/** flag that indicates whether method debug() was called */
		private boolean debugCalled = false;

		/** flag that indicates whether info() was called */
		private boolean infoCalled = false;

		/*
		 * (non-Javadoc)
		 * 
		 * @see jdt.framework.Logger#debug(java.lang.String)
		 */
		@Override
		public void debug(String message) {
			this.debugCalled = true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see jdt.framework.Logger#info(java.lang.String)
		 */
		@Override
		public void info(String message) {
			this.infoCalled = true;
		}

		/**
		 * Returns the value of the debug testing flag.
		 * 
		 * @return true if debug() was called, false otherwise
		 */
		boolean isDebugCalled() {
			return this.debugCalled;
		}

		/**
		 * Returns the value of the info testing flag.
		 * 
		 * @return true if info() was called, false otherwise
		 */
		boolean isInfoCalled() {
			return this.infoCalled;
		}
	}

	/**
	 * Dummy editor.
	 * 
	 * @author Eric Dubuis
	 */
	private class DummyEditor extends EditorAdaptor {
		/** the logger */
		private Logger logger;

		/**
		 * Constructs a dummy editor with a logger
		 * 
		 * @param l
		 */
		DummyEditor(Logger l) {
			this.logger = l;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see jdt.framework.Editor#getLogger(java.lang.Class)
		 */
		@Override
		public Logger getLogger(Class<?> logFacility) {
			return this.logger;
		}
	}

	/**
	 * Dummy tool.
	 * 
	 * @author Eric Dubuis
	 */
	private class DummyTool extends DefaultTool {

		/**
		 * Constructs a dummy tool.
		 * 
		 * @param e
		 *            a dummy editor
		 */
		protected DummyTool(Editor e) {
			super(e);
		}
	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.DefaultTool#DefaultTool(ch.bfh.due1.jdt.framework.Editor)}.
	 */
	@Test
	public void testAbstractTool() {
		Editor e = new DummyEditor(null);
		DummyTool t = new DummyTool(e);
		assertEquals(e, t.getEditor());
	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.DefaultTool#mouseDown(ch.bfh.due1.jdt.framework.Coord, ch.bfh.due1.jdt.framework.KeyModifier)}.
	 */
	@Test
	public void testMouseDown() {
		DummyLogger l = new DummyLogger();
		Editor e = new DummyEditor(l);
		DummyTool t = new DummyTool(e);
		t.mouseDown(null, null);
		assertTrue(((DummyLogger) t.getEditor().getLogger(t.getClass()))
				.isDebugCalled()
				|| ((DummyLogger) t.getEditor().getLogger(t.getClass()))
						.isInfoCalled());
	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.DefaultTool#mouseDrag(ch.bfh.due1.jdt.framework.Coord, ch.bfh.due1.jdt.framework.KeyModifier)}.
	 */
	@Test
	public void testMouseDrag() {
		DummyLogger l = new DummyLogger();
		Editor e = new DummyEditor(l);
		DummyTool t = new DummyTool(e);
		t.mouseDrag(null, null);
		assertTrue(((DummyLogger) t.getEditor().getLogger(t.getClass()))
				.isDebugCalled()
				|| ((DummyLogger) t.getEditor().getLogger(t.getClass()))
						.isInfoCalled());
	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.DefaultTool#mouseOver(ch.bfh.due1.jdt.framework.Coord, ch.bfh.due1.jdt.framework.KeyModifier)}.
	 */
	@Test
	public void testMouseOver() {
		DummyLogger l = new DummyLogger();
		Editor e = new DummyEditor(l);
		DummyTool t = new DummyTool(e);
		t.mouseOver(null, null);
		assertTrue(((DummyLogger) t.getEditor().getLogger(t.getClass()))
				.isDebugCalled()
				|| ((DummyLogger) t.getEditor().getLogger(t.getClass()))
						.isInfoCalled());
	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.DefaultTool#mouseUp(ch.bfh.due1.jdt.framework.Coord, ch.bfh.due1.jdt.framework.KeyModifier)}.
	 */
	@Test
	public void testMouseUp() {
		DummyLogger l = new DummyLogger();
		Editor e = new DummyEditor(l);
		DummyTool t = new DummyTool(e);
		t.mouseUp(null, null);
		assertTrue(((DummyLogger) t.getEditor().getLogger(t.getClass()))
				.isDebugCalled()
				|| ((DummyLogger) t.getEditor().getLogger(t.getClass()))
						.isInfoCalled());
	}
}
