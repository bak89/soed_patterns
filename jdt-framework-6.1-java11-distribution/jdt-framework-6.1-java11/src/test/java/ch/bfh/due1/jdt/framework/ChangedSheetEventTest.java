/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.framework;

import java.awt.Graphics;
import java.util.Collection;
import java.util.List;

import org.junit.*;

import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.Sheet;
import ch.bfh.due1.jdt.framework.SheetChangedEvent;
import ch.bfh.due1.jdt.framework.SheetChangedListener;

import static org.junit.Assert.*;

/**
 * Tests trivial properties of changed sheet events.
 * 
 * @author Eric Dubuis
 */
public class ChangedSheetEventTest {
	private class DummySheet implements Sheet {

		/**
		 * Dummy implementation.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Sheet#addShape(ch.bfh.due1.jdt.framework.Shape)
		 */
		@Override
		public void addShape(Shape s) {
		}

		/**
		 * Dummy implementation.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Sheet#addSheetChangedListener(ch.bfh.due1.jdt.framework.SheetChangedListener)
		 */
		@Override
		public void addSheetChangedListener(SheetChangedListener listener) {
		}

		/**
		 * Dummy implementation.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Sheet#draw(java.awt.Graphics)
		 */
		@Override
		public void draw(Graphics g) {
		}

		/**
		 * Dummy implementation.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Sheet#getShapes()
		 */
		@Override
		public List<Shape> getShapes() {
			return null;
		}

		/**
		 * Dummy implementation.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Sheet#getShapesByStackingOrder(java.util.Collection)
		 */
		@Override
		public List<Shape> getShapesByStackingOrder(Collection<Shape> shapes) {
			return null;
		}

		/**
		 * Dummy implementation.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Sheet#removeShape(ch.bfh.due1.jdt.framework.Shape)
		 */
		@Override
		public boolean removeShape(Shape s) {
			return false;
		}

		/**
		 * Dummy implementation.
		 * 
		 * @see ch.bfh.due1.jdt.framework.Sheet#removeSheetChangedListener(ch.bfh.due1.jdt.framework.SheetChangedListener)
		 */
		@Override
		public boolean removeSheetChangedListener(SheetChangedListener listener) {
			return false;
		}

		/**
		 * @inheritDoc
		 * 
		 * @see ch.bfh.due1.jdt.framework.View#createMemento()
		 */
		@Override
		public Memento createMemento() {
			throw new UnsupportedOperationException("Method not yet implemented");
		}

		/**
		 * @inheritDoc
		 * 
		 * @see ch.bfh.due1.jdt.framework.View#setMemento(ch.bfh.due1.jdt.framework.Memento)
		 */
		@Override
		public void setMemento(Memento m) {
			throw new UnsupportedOperationException("Method not yet implemented");
		}
	}

	/**
	 * Tests if the illegal argument exception is throw when instantiating a
	 * sheet changed event with null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCreate1() {
		new SheetChangedEvent(null);
	}

	/**
	 * Tests if the no exception is throw when instantiating a sheet changed
	 * event with a sheet object. Test if the sheet object is returned.
	 */
	@Test
	public void testCreate3() {
		Sheet s = new DummySheet();
		SheetChangedEvent evt = new SheetChangedEvent(s);
		assertEquals(s, evt.getSource());
	}
}
