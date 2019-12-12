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

import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeChangedListener;
import ch.bfh.due1.jdt.framework.Sheet;
import ch.bfh.due1.jdt.framework.SheetChangedEvent;
import ch.bfh.due1.jdt.framework.SheetChangedListener;
import ch.bfh.due1.jdt.framework.util.ShapeAdaptor;

/**
 * @author Eric Dubuis
 */
public class SheetChangedListenerNotificationTest {
	private class SheetChangedListenerImpl implements SheetChangedListener {
		public int haveBeenCalled = 0;

		/**
		 * @see ch.bfh.due1.jdt.framework.SheetChangedListener#sheetChanged(ch.bfh.due1.jdt.framework.SheetChangedEvent)
		 */
		@Override
		public void sheetChanged(SheetChangedEvent e) {
			++this.haveBeenCalled;
		}
	}

	private class MockShape extends ShapeAdaptor {

		/**
		 * Default implementation which is intentionally left empty. For testing
		 * purposes only.
		 *
		 * @see ch.bfh.due1.jdt.framework.util.ShapeAdaptor#addShapeChangedListener(ch.bfh.due1.jdt.framework.ShapeChangedListener)
		 */
		@Override
		public void addShapeChangedListener(ShapeChangedListener listener) {
			// Intentionally left empty.
		}

		/**
		 * Default implementation which is intentionally left empty. For testing
		 * purposes only.
		 *
		 * @see ch.bfh.due1.jdt.framework.util.ShapeAdaptor#removeShapeChangedListener(ch.bfh.due1.jdt.framework.ShapeChangedListener)
		 */
		@Override
		public boolean removeShapeChangedListener(ShapeChangedListener listener) {
			// Intentionally left empty.
			return true;
		}
	}

	@Test
	public void testForNotificationOfListener() {
		Sheet sheet = new SimpleSheet();
		SheetChangedListenerImpl myListener = new SheetChangedListenerImpl();
		sheet.addSheetChangedListener(myListener);
		Shape shape = new MockShape();
		sheet.addShape(shape);
		assertEquals(1, myListener.haveBeenCalled);
		sheet.removeShape(shape);
		assertEquals(2, myListener.haveBeenCalled);
	}
}
