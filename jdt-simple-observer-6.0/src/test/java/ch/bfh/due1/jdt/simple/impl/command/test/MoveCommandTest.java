/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2010
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.impl.command.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.Vector;
import ch.bfh.due1.jdt.framework.util.ShapeAdaptor;
import ch.bfh.due1.jdt.simple.impl.command.MoveCommand;

/**
 * Test class for testing the 'move' command.
 */
public class MoveCommandTest {
	/**
	 * Private shape class.
	 */
	private class SimpleShape extends ShapeAdaptor {
		private BoundingBox bb;

		private class SimpleShapeMemento implements Memento {
			private static final long serialVersionUID = -5305864697151157629L;
			private BoundingBox bb;

			SimpleShapeMemento(BoundingBox bb) {
				this.bb = bb;
			}

			BoundingBox getBoundingBox() {
				return this.bb;
			}
		}

		SimpleShape(int x0, int y0, int width, int height) {
			this.bb = new BoundingBox(x0, y0, width, height);
		}

		@Override
		public BoundingBox getBoundingBox() {
			return this.bb;
		}

		@Override
		public void move(Vector delta) {
			BoundingBox r = getBoundingBox();
			this.bb = new BoundingBox(r.getX0() + delta.getXComponent(),
					r.getY0() + delta.getYComponent(), r.getWidth(),
					r.getHeight());
		}

		@Override
		public Memento createMemento() {
			return new SimpleShapeMemento(this.bb);
		}

		@Override
		public void setMemento(Memento m) {
			SimpleShapeMemento ssm = (SimpleShapeMemento) m;
			this.bb = ssm.getBoundingBox();
		}
	}

	/**
	 * Creates a mock shape, creates a move command, calls execute() on the
	 * command, and calls the undo() operation.
	 */
	@Test
	public void testMoveCommand() {
		Shape s1 = new SimpleShape(10, 10, 10, 10);
		Vector d = new Vector(10, 10);
		Command cmd = new MoveCommand(s1, d);
		cmd.execute();
		assertEquals(new BoundingBox(20, 20, 10, 10), s1.getBoundingBox());
		cmd.undo();
		assertEquals(new BoundingBox(10, 10, 10, 10), s1.getBoundingBox());
	}
}
