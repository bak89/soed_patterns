/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.impl.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.util.EditorAdaptor;
import ch.bfh.due1.jdt.simple.impl.alignment.AlignJustifyHorizontalStrategy;
import ch.bfh.due1.jdt.simple.impl.alignment.AlignStrategy;
import ch.bfh.due1.jdt.simple.impl.alignment.AlignTool;
import ch.bfh.due1.jdt.simple.impl.shape.SimpleBox;

/**
 * Test class for the align justify vertical strategy.
 * 
 * @author Eric Dubuis
 */
public class AlignAdjustHorizontalStrategyTest {

	/**
	 * Adaptor for an editor which returns a command factory.
	 */
	private class MockEditor extends EditorAdaptor {

		/**
		 * Dummy implementation.
		 */
		@Override
		public CommandHandler getCommandHandler() {
			return null;
		}
	}

	/**
	 * Tests if the justify horizontal alignment of an empty selection has no
	 * effect of the empty selection.
	 */
	@Test
	public void testAlign0() {
		List<Shape> selectedShapes = new ArrayList<Shape>();
		AlignStrategy strategy = new AlignJustifyHorizontalStrategy();
		Editor e = new MockEditor();
		AlignTool t = new AlignTool(e);
		strategy.align(t, selectedShapes);
		assertEquals(new ArrayList<Shape>(), selectedShapes);
	}

	/**
	 * Tests if the justify horizontal alignment of a selection with one shape
	 * only has no effect.
	 */
	@Test
	public void testAlign1() {
		Shape box1 = new SimpleBox(0, 0, 10, 10);
		List<Shape> selectedShapes = new ArrayList<Shape>();
		selectedShapes.add(box1);
		AlignStrategy strategy = new AlignJustifyHorizontalStrategy();
		Editor e = new MockEditor();
		AlignTool t = new AlignTool(e);
		strategy.align(t, selectedShapes);
		assertEquals(box1, selectedShapes.get(0));
	}

	/**
	 * Tests if the justify horizontal alignment of three boxes aligns the other
	 * boxes to the first one.
	 */
	@Test
	public void testAlign2a() {
		Shape box1 = new SimpleBox(10, 10, 10, 10);
		Shape box2 = new SimpleBox(10, 0, 10, 10);
		Shape box3 = new SimpleBox(10, -10, 10, 10);
		List<Shape> selectedShapes = new ArrayList<Shape>();
		selectedShapes.add(box1);
		selectedShapes.add(box2);
		selectedShapes.add(box3);
		AlignStrategy strategy = new AlignJustifyHorizontalStrategy();
		Editor e = new MockEditor();
		AlignTool t = new AlignTool(e);
		strategy.align(t, selectedShapes);
		assertEquals(new BoundingBox(10, 10, 10, 10), selectedShapes.get(0)
				.getBoundingBox());
		assertEquals(new BoundingBox(10, 10, 10, 10), selectedShapes.get(1)
				.getBoundingBox());
		assertEquals(new BoundingBox(10, 10, 10, 10), selectedShapes.get(2)
				.getBoundingBox());
	}

	/**
	 * Tests if the justify horizontal alignment of three boxes aligns the other
	 * boxes to the first one.
	 */
	@Test
	public void testAlign2b() {
		Shape box1 = new SimpleBox(10, 0, 10, 10);
		Shape box2 = new SimpleBox(10, 10, 10, 10);
		Shape box3 = new SimpleBox(10, 20, 10, 10);
		List<Shape> selectedShapes = new ArrayList<Shape>();
		selectedShapes.add(box1);
		selectedShapes.add(box2);
		selectedShapes.add(box3);
		AlignStrategy strategy = new AlignJustifyHorizontalStrategy();
		Editor e = new MockEditor();
		AlignTool t = new AlignTool(e);
		strategy.align(t, selectedShapes);
		assertEquals(new BoundingBox(10, 0, 10, 10), selectedShapes.get(0)
				.getBoundingBox());
		assertEquals(new BoundingBox(10, 0, 10, 10), selectedShapes.get(1)
				.getBoundingBox());
		assertEquals(new BoundingBox(10, 0, 10, 10), selectedShapes.get(2)
				.getBoundingBox());
	}

	/**
	 * Tests if the justify horizontal alignment of three boxes aligns the other
	 * boxes to the first one.
	 */
	@Test
	public void testAlign2c() {
		Shape box1 = new SimpleBox(10, 10, 10, 10);
		Shape box2 = new SimpleBox(10, -10, 10, 10);
		Shape box3 = new SimpleBox(10, -20, 10, 10);
		List<Shape> selectedShapes = new ArrayList<Shape>();
		selectedShapes.add(box1);
		selectedShapes.add(box2);
		selectedShapes.add(box3);
		AlignStrategy strategy = new AlignJustifyHorizontalStrategy();
		Editor e = new MockEditor();
		AlignTool t = new AlignTool(e);
		strategy.align(t, selectedShapes);
		assertEquals(new BoundingBox(10, 10, 10, 10), selectedShapes.get(0)
				.getBoundingBox());
		assertEquals(new BoundingBox(10, 10, 10, 10), selectedShapes.get(1)
				.getBoundingBox());
		assertEquals(new BoundingBox(10, 10, 10, 10), selectedShapes.get(2)
				.getBoundingBox());
	}

	/**
	 * Tests if the justify horizontal alignment of three boxes aligns the other
	 * boxes to the first one.
	 */
	@Test
	public void testAlign2d() {
		Shape box1 = new SimpleBox(10, 10, 10, 10);
		Shape box2 = new SimpleBox(10, 10, 10, 30);
		Shape box3 = new SimpleBox(10, 10, 10, 50);
		List<Shape> selectedShapes = new ArrayList<Shape>();
		selectedShapes.add(box1);
		selectedShapes.add(box2);
		selectedShapes.add(box3);
		AlignStrategy strategy = new AlignJustifyHorizontalStrategy();
		Editor e = new MockEditor();
		AlignTool t = new AlignTool(e);
		strategy.align(t, selectedShapes);
		assertEquals(new BoundingBox(10, 10, 10, 10), selectedShapes.get(0)
				.getBoundingBox());
		assertEquals(new BoundingBox(10, 0, 10, 30), selectedShapes.get(1)
				.getBoundingBox());
		assertEquals(new BoundingBox(10, -10, 10, 50), selectedShapes.get(2)
				.getBoundingBox());
	}

	/**
	 * Tests if a selection already aligned is not changed.
	 */
	@Test
	public void testAlign3() {
		Shape box1 = new SimpleBox(10, 10, 10, 10);
		Shape box2 = new SimpleBox(10, 10, 10, 30);
		Shape box3 = new SimpleBox(10, 10, 10, 50);
		List<Shape> selectedShapes = new ArrayList<Shape>();
		selectedShapes.add(box1);
		selectedShapes.add(box2);
		selectedShapes.add(box3);
		AlignStrategy strategy = new AlignJustifyHorizontalStrategy();
		Editor e = new MockEditor();
		AlignTool t = new AlignTool(e);
		strategy.align(t, selectedShapes);
		assertEquals(new BoundingBox(10, 10, 10, 10), selectedShapes.get(0)
				.getBoundingBox());
		assertEquals(new BoundingBox(10, 0, 10, 30), selectedShapes.get(1)
				.getBoundingBox());
		assertEquals(new BoundingBox(10, -10, 10, 50), selectedShapes.get(2)
				.getBoundingBox());
		strategy.align(t, selectedShapes);
		assertEquals(new BoundingBox(10, 10, 10, 10), selectedShapes.get(0)
				.getBoundingBox());
		assertEquals(new BoundingBox(10, 0, 10, 30), selectedShapes.get(1)
				.getBoundingBox());
		assertEquals(new BoundingBox(10, -10, 10, 50), selectedShapes.get(2)
				.getBoundingBox());
	}
}
