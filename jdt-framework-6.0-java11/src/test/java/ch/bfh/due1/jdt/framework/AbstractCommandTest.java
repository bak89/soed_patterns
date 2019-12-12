/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;


import org.junit.Test;

import ch.bfh.due1.jdt.framework.AbstractCommand;

/**
 * Test the pseudo default implementations of AbstractCommand.
 * @author Eric Dubuis
 */
public class AbstractCommandTest {
	/**
	 * Dummy Command class allowing to test AbstractCommand.
	 * 
	 * @author Eric Dubuis
	 */
	private class DummyCommand extends AbstractCommand {

		/**
		 * Not used.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Command#execute()
		 */
		@Override
		public void execute() {
		}

		/**
		 * Not used.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Command#undo()
		 */
		@Override
		public void undo() {
		}

	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.AbstractCommand#addCommand(ch.bfh.due1.jdt.framework.Command)}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testAddCommand() {
		new DummyCommand().addCommand(null);
	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.AbstractCommand#removeCommand(ch.bfh.due1.jdt.framework.Command)}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveCommand() {
		new DummyCommand().removeCommand(null);
	}
}
