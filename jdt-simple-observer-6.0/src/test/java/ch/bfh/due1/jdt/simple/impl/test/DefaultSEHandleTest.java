/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007-2009
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple.impl.test;

import static org.junit.Assert.assertEquals;

import java.awt.Graphics;
import java.util.List;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.AbstractShape;
import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Vector;
import ch.bfh.due1.jdt.simple.impl.shape.DefaultSEHandle;

/**
 * Test the behavior of the DefaultSEHandle class.
 */
public class DefaultSEHandleTest {

	private class DummyShape extends AbstractShape {
		private BoundingBox bb = null;

		public DummyShape(BoundingBox bb) {
			this.bb = bb;
		}

		@Override
		public boolean contains(Coord c) {
			return false;
		}

		@Override
		protected void doDrawShape(Graphics g) {
		}

		@Override
		public BoundingBox getBoundingBox() {
			return this.bb;
		}

		@Override
		public void move(Vector delta) {
		}

		@Override
		public void setBoundingBox(BoundingBox r) {
			this.bb = r;
		}

		/**
		 * Not implemented.
		 * @see ch.bfh.due1.jdt.framework.Shape#cloneMe()
		 */
		@Override
		public Shape cloneMe() {
			throw new UnsupportedOperationException("Method not implemented");
		}

		/**
		 * No implementation needed.
		 * @see ch.bfh.due1.jdt.framework.Shape#createMemento()
		 */
		@Override
		public Memento createMemento() {
			throw new UnsupportedOperationException("Method not implemented");
		}

		/**
		 * No implementation needed.
		 * @see ch.bfh.due1.jdt.framework.Shape#setMemento(Memento)
		 */
		@Override
		public void setMemento(Memento m) {
			throw new UnsupportedOperationException("Method not implemented");
		}

		/**
		 * No implementation needed.
		 * @see ch.bfh.due1.jdt.framework.Shape#getShapeHandles()
		 */
		@Override
		public List<ShapeHandle> getShapeHandles() {
			throw new UnsupportedOperationException("Method not implemented");
		}
	}

	@Test
	public void testDragInteraction() {
		Coord loc = new Coord(10, 10);
		Shape s = new DummyShape(new BoundingBox(0, 0, 10, 10));
		ShapeHandle sh = new DefaultSEHandle(s, loc);
		sh.dragInteraction(new Coord(20, 20), null);
		assertEquals(new BoundingBox(0, 0, 20, 20), s.getBoundingBox());
	}
}
