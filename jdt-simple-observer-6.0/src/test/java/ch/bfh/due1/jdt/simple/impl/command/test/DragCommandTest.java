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
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Vector;
import ch.bfh.due1.jdt.framework.util.HandleAdaptor;
import ch.bfh.due1.jdt.framework.util.ShapeAdaptor;
import ch.bfh.due1.jdt.simple.impl.command.DragCommand;

/**
 * Test class for testing the 'drag' command.
 */
public class DragCommandTest {
	/**
	 * Private shape class.
	 */
	private class SimpleShape extends ShapeAdaptor {
		private BoundingBox bb;
		private ShapeHandle handle;

		private class Handle extends HandleAdaptor {
			Handle(Shape s, Coord c) {
				super(s, c);
			}

			@Override
			public void dragInteraction(Coord c, KeyModifier k) {
				BoundingBox bb = getOwner().getBoundingBox();
				BoundingBox newBb = new BoundingBox(bb.getX0(), bb.getY0(),
						c.getX0() - bb.getX0(), c.getY0()
								- bb.getY0());  // SE handle ...
				getOwner().setBoundingBox(newBb);
			}
		}

		private class SimpleShapeMemento implements Memento {
			private static final long serialVersionUID = -6528361604946748564L;
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
			this.handle = new Handle(this, new Coord(x0 + width, y0 + width));
		}

		@Override
		public BoundingBox getBoundingBox() {
			return this.bb;
		}

		@Override
		public void setBoundingBox(BoundingBox r) {
			this.bb = r;
			this.handle.setPosition(new Coord(r.getX0() + r.getWidth(), r
					.getY0() + r.getHeight()));
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
			this.handle
					.setPosition(new Coord(
							this.bb.getX0() + this.bb.getWidth(), this.bb
									.getY0() + this.bb.getHeight()));
		}

		@Override
		public List<ShapeHandle> getShapeHandles() {
			return Collections.singletonList(this.handle);
		}
	}

	/**
	 * Creates a mock shape, creates a move command, calls execute() on the
	 * command, and calls the undo() operation.
	 */
	@Test
	public void testMoveCommand() {
		Shape s1 = new SimpleShape(10, 10, 10, 10);
		ShapeHandle h = s1.getShapeHandles().get(0);
		assertNotNull(h);
		Command cmd =
				new DragCommand(h, h.getPosition(), new Coord(30, 30), KeyModifier.NONE);
		cmd.execute();
		assertEquals(new BoundingBox(10, 10, 20, 20), s1.getBoundingBox());
		assertEquals(new Coord(30, 30), h.getPosition());
		cmd.undo();
		assertEquals(new BoundingBox(10, 10, 10, 10), s1.getBoundingBox());
		assertEquals(new Coord(20, 20), h.getPosition());
	}
}
