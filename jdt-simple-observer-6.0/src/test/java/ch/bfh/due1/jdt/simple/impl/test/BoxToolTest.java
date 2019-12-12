/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2009
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.Logger;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.Tool;
import ch.bfh.due1.jdt.framework.util.EditorAdaptor;
import ch.bfh.due1.jdt.simple.impl.shape.BoxTool;

/**
 * Tests the tool for creating a box shape.
 */
public class BoxToolTest {
	private class DummyEditor extends EditorAdaptor {
		private List<Shape> shapes = new ArrayList<Shape>();

		@Override
		public void addShape(Shape s) {
			this.shapes.add(s);
		}

		@Override
		public Logger getLogger(Class<?> logFacility) {
			return new Logger() {

				@Override
				public void debug(String message) {
				}

				@Override
				public void info(String message) {
				}
			};
		}

		@Override
		public List<Shape> getShapes() {
			return this.shapes;
		}
	}

	@Test
	public void testMouseDown() {
		DummyEditor e = new DummyEditor();
		Tool t = new BoxTool(e);
		Coord origin = new Coord(10, 10);
		t.mouseDown(origin, null);
		List<Shape> shapes = e.getShapes();
		assertNotNull("List of shapes must not be null", shapes);
		assertEquals("Size of list of shapes", 1, shapes.size());
		assertEquals("Size of box", new BoundingBox(origin, 0, 0), shapes
				.get(0).getBoundingBox());
	}
}
