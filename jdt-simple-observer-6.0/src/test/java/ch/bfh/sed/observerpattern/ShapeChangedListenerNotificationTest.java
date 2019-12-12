/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.sed.observerpattern;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeChangedEvent;
import ch.bfh.due1.jdt.framework.ShapeChangedListener;
import ch.bfh.due1.jdt.framework.Vector;

/**
 * @author Eric Dubuis
 */
public class ShapeChangedListenerNotificationTest {
	private class ShapeChangedListenerImpl implements ShapeChangedListener {
		public int haveBeenCalled = 0;

		/**
		 * @see ch.bfh.due1.jdt.framework.ShapeChangedListener#shapeChanged(ch.bfh.due1.jdt.framework.ShapeChangedEvent)
		 */
		@Override
		public void shapeChanged(ShapeChangedEvent e) {
			++this.haveBeenCalled;
		}
	}

	@Test
	public void testForNotificationOfSimpleLine() {
		Shape s = new SimpleLine(10, 10, 10, 10);
		ShapeChangedListenerImpl myListener = new ShapeChangedListenerImpl();
		s.addShapeChangedListener(myListener);
		s.setBoundingBox(new BoundingBox(20, 20, 20, 20));
		assertEquals(1, myListener.haveBeenCalled);
		s.move(new Vector(10, 10));
		assertEquals(2, myListener.haveBeenCalled);
	}

	@Test
	public void testForNotificationOfSimpleBox() {
		Shape s = new SimpleBox(10, 10, 10, 10);
		ShapeChangedListenerImpl myListener = new ShapeChangedListenerImpl();
		s.addShapeChangedListener(myListener);
		s.setBoundingBox(new BoundingBox(20, 20, 20, 20));
		assertEquals(1, myListener.haveBeenCalled);
		s.move(new Vector(10, 10));
		assertEquals(2, myListener.haveBeenCalled);
	}

	@Test
	public void testForNotificationOfSimpleEllipse() {
		Shape s = new SimpleEllipse(10, 10, 10, 10);
		ShapeChangedListenerImpl myListener = new ShapeChangedListenerImpl();
		s.addShapeChangedListener(myListener);
		s.setBoundingBox(new BoundingBox(20, 20, 20, 20));
		assertEquals(1, myListener.haveBeenCalled);
		s.move(new Vector(10, 10));
		assertEquals(2, myListener.haveBeenCalled);
	}

}
