/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.sed.observerpattern;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.ShapeChangedListener;
import ch.bfh.due1.jdt.framework.Sheet;
import ch.bfh.due1.jdt.framework.util.ShapeAdaptor;

/**
 * @author Eric Dubuis
 */
public class ShapeChangedListenerRegisteredTest {
	private class MockShape extends ShapeAdaptor {
		public Set<ShapeChangedListener> listeners = new HashSet<>();

		@Override
		public void addShapeChangedListener(ShapeChangedListener l) {
			this.listeners.add(l);
		}

		@Override
		public boolean removeShapeChangedListener(ShapeChangedListener l) {
			return this.listeners.remove(l);
		}
	}

	@Test
	public void testForRegisteringListenerAtShape() {
		Sheet sheet = new SimpleSheet();
		MockShape shape = new MockShape();
		sheet.addShape(shape);
		assertEquals(1, shape.listeners.size());
		sheet.removeShape(shape);
		assertEquals(0, shape.listeners.size());
	}
}
