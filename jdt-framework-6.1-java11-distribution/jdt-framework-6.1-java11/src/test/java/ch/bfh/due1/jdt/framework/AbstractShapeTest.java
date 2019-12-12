/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.AbstractShape;
import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Vector;
import ch.bfh.due1.jdt.framework.util.HandleAdaptor;

/**
 * Tests methods of AbstractShape not covered by other tests.
 * 
 * @author Eric Dubuis
 */
public class AbstractShapeTest {
	/**
	 * Dummy shape handle.
	 *
	 * @author Eric Dubuis
	 */
	private class DummyShapeHandle extends HandleAdaptor {
		public boolean drawCalled = false;
		public DummyShapeHandle(Shape s, Coord c) {
			super(s, c);
		}
		@Override
		public void draw(Graphics g) {
			this.drawCalled = true;
		}
	}

	/**
	 * Dummy shape class to test some methods of class AbstractShape.
	 * 
	 * @author Eric Dubuis
	 */
	private class DummyShape extends AbstractShape {
		public boolean drawShapeCalled = false;
		private ShapeHandle sh =
				new DummyShapeHandle(this, null);

		/**
		 * Returns true if current pen size value equals the given value.
		 * 
		 * @param value
		 *            given value
		 * @return true if pen size value equals given value, false otherwise
		 */
		boolean isPenSize(int value) {
			return value == this.penSize;
		}

		/**
		 * Returns true if fill color equals with given value, false otherwise
		 * 
		 * @param c
		 *            the given color
		 * @return true if fill color equals with given value, false otherwise
		 */
		boolean isFillColor(Color c) {
			if (c != null) {
				return c.equals(this.fillColor);
			}
			return false;
		}

		/**
		 * Returns true if pen color equals with given value, false otherwise
		 * 
		 * @param c
		 *            the given color
		 * @return true if fill color equals with given value, false otherwise
		 */
		boolean isPenColor(Color c) {
			if (c != null) {
				return c.equals(this.penColor);
			}
			return false;
		}

		/**
		 * Constructs a dummy shape. Parameter of interest is penSize.
		 * 
		 * @param fillColor
		 *            the fill color
		 * @param penColor
		 *            the pen color
		 * @param penSize
		 *            the pen size
		 */
		DummyShape(Color fillColor, Color penColor, int penSize) {
			super(fillColor, penColor, penSize);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see jdt.framework.Shape#contains(jdt.util.Coord)
		 */
		@Override
		public boolean contains(Coord c) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see jdt.framework.AbstractShape#doDrawShape(java.awt.Graphics)
		 */
		@Override
		protected void doDrawShape(Graphics g) {
			drawShapeCalled = true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see jdt.framework.Shape#getBoundingBox()
		 */
		@Override
		public BoundingBox getBoundingBox() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see jdt.framework.Shape#move(jdt.util.Vector)
		 */
		@Override
		public void move(Vector delta) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see jdt.framework.Shape#setBoundingBox(jdt.util.BoundingBox)
		 */
		@Override
		public void setBoundingBox(BoundingBox r) {
		}

		/**
		 * No implementation needed.
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
		 * Returns the dummy shape handle as the only element
		 * in the list.
		 * @return a list of shape handles having the dummy shape handle only
		 */
		@Override
		public List<ShapeHandle> getShapeHandles() {
			return Collections.singletonList(this.sh);
		}
	}

	/**
	 * Test method for {@link ch.bfh.due1.jdt.framework.AbstractShape#AbstractShape()}.
	 */
	@Test
	public void testAbstractShape() {
		DummyShape s1 = new DummyShape(null, null, 2);
		assertTrue(s1.isPenSize(2));
		DummyShape s2 = new DummyShape(null, null, 1);
		assertTrue(s2.isPenSize(2));
	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.AbstractShape#setFillColor(java.awt.Color)}.
	 */
	@Test
	public void testSetFillColor() {
		Color c = Color.BLACK;
		DummyShape s = new DummyShape(null, null, 2);
		s.setFillColor(c);
		assertTrue(s.isFillColor(c));
	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.AbstractShape#setPenColor(java.awt.Color)}.
	 */
	@Test
	public void testSetPenColor() {
		Color c = Color.BLACK;
		DummyShape s = new DummyShape(null, null, 2);
		s.setPenColor(c);
		assertTrue(s.isPenColor(c));
	}

	/**
	 * Test method for {@link ch.bfh.due1.jdt.framework.AbstractShape#setPenSize(int)}.
	 */
	@Test
	public void testSetPenSize() {
		DummyShape s = new DummyShape(null, null, 2);
		assertTrue(s.isPenSize(2));
		s.setPenSize(1);
		assertTrue(s.isPenSize(2));
		s.setPenSize(3);
		assertTrue(s.isPenSize(3));
	}

	/**
	 * Test method for {@link ch.bfh.due1.jdt.framework.AbstractShape#getShapeHandles()}.
	 */
	@Test
	public void testGetShapeHandles() {
		DummyShape s = new DummyShape(null, null, 2);
		assertNotNull(s.getShapeHandles());
		assertTrue(s.getShapeHandles().size() == 1);
	}

	/**
	 * Test method for {@link ch.bfh.due1.jdt.framework.AbstractShape#isContainer()}.
	 */
	@Test
	public void testIsContainer() {
		DummyShape s = new DummyShape(null, null, 2);
		assertFalse(s.isContainer());
	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.AbstractShape#setSelected(boolean)}
	 * and
	 * {@link ch.bfh.due1.jdt.framework.AbstractShape#isSelected()}.
	 */
	@Test
	public void testSetSelected() {
		DummyShape s = new DummyShape(null, null, 2);
		s.setSelected(true);
		assertTrue(s.isSelected());
		s.setSelected(false);
		assertFalse(s.isSelected());
	}

	/**
	 * Tests if draw() method works well with selection status.
	 */
	@Test
	public void testDrawIfSelected() {
		DummyShape s = new DummyShape(null, null, 2);
		assertFalse(s.drawShapeCalled);
		s.draw(null);
		assertTrue(s.drawShapeCalled);
		assertFalse(((DummyShapeHandle) s.getShapeHandles().get(0)).drawCalled);
		s.setSelected(true);
		s.draw(null);
		assertTrue(s.drawShapeCalled);
		assertTrue(((DummyShapeHandle) s.getShapeHandles().get(0)).drawCalled);
	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.AbstractShape#add(ch.bfh.due1.jdt.framework.Shape)}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testAdd() {
		DummyShape s = new DummyShape(null, null, 2);
		s.add(null);
	}

	/**
	 * Test method for
	 * {@link ch.bfh.due1.jdt.framework.AbstractShape#remove(ch.bfh.due1.jdt.framework.Shape)}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testRemove() {
		DummyShape s = new DummyShape(null, null, 2);
		s.remove(null);
	}

	/**
	 * Test method for {@link ch.bfh.due1.jdt.framework.AbstractShape#getShapes()}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetShapes() {
		DummyShape s = new DummyShape(null, null, 2);
		s.getShapes();
	}

}
