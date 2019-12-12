/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.framework;

import static org.junit.Assert.assertNotNull;

import java.awt.Graphics;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.Sheet;
import ch.bfh.due1.jdt.framework.SheetChangedEvent;
import ch.bfh.due1.jdt.framework.SheetChangedListener;

/**
 * Tests if a sheet changed event is linked with a sheet.
 * 
 * @author Eric Dubuis
 */
public class SheetChangedEventTest {

	@Test
	public void testGetSheet() {
		SheetChangedEvent e = new SheetChangedEvent(new Sheet() {

			@Override
			public void addShape(Shape s) {
			}

			@Override
			public void addSheetChangedListener(SheetChangedListener listener) {
			}

			@Override
			public void draw(Graphics g) {
			}

			@Override
			public List<Shape> getShapes() {
				return null;
			}

			@Override
			public List<Shape> getShapesByStackingOrder(Collection<Shape> shapes) {
				return null;
			}

			@Override
			public boolean removeShape(Shape s) {
				return false;
			}

			@Override
			public boolean removeSheetChangedListener(
					SheetChangedListener listener) {
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
		});
		assertNotNull(e.getSheet());
	}
}
