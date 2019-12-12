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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.KeyModifier;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.simple.impl.shape.ShapeGroup;
import ch.bfh.due1.jdt.simple.impl.shape.SimpleBox;
import ch.bfh.due1.jdt.simple.impl.shape.ShapeGroup.ShapeGroupMemento;

/**
 * Tests some properties of shape groups.
 * @author Eric Dubuis
 */
public class ShapeGroupTest {

	/**
	 * Given two equal simple boxes, builds a group of shapes, and
	 * compares the resulting bounding box with the expected one.
	 */
	@Test
	public void testGetBoundingBox1() {
		Shape box1 = new SimpleBox(0, 0, 10, 10);
		Shape box2 = new SimpleBox(0, 0, 10, 10);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		shapes.add(box2);
		Shape group = new ShapeGroup(shapes);
		BoundingBox expected = new BoundingBox(0, 0, 10, 10);
		assertEquals(expected, group.getBoundingBox());
	}

	/**
	 * Given two different simple boxes, builds a group of shapes, and
	 * compares the resulting bounding box with the expected one.
	 */
	@Test
	public void testGetBoundingBox2() {
		Shape box1 = new SimpleBox(0, 0, 10, 10);
		Shape box2 = new SimpleBox(10, 10, 10, 10);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		shapes.add(box2);
		Shape group = new ShapeGroup(shapes);
		BoundingBox expected = new BoundingBox(0, 0, 20, 20);
		assertEquals(expected, group.getBoundingBox());
	}

	/**
	 * Given two differnt simple boxes, builds a group of shapes, and
	 * compares the resulting bounding box with the expected one.
	 */
	@Test
	public void testGetBoundingBox3() {
		Shape box1 = new SimpleBox(-10, -10, 10, 10);
		Shape box2 = new SimpleBox(10, 10, 10, 10);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		shapes.add(box2);
		Shape group = new ShapeGroup(shapes);
		BoundingBox expected = new BoundingBox(-10, -10, 30, 30);
		assertEquals(expected, group.getBoundingBox());
	}

	/**
	 * Tests method setBoundingBox().
	 */
	@Test
	public void testSetBoundingBox1() {
		Shape box1 = new SimpleBox(0, 0, 10, 10);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		Shape group = new ShapeGroup(shapes);
		BoundingBox newSize = new BoundingBox(0, 0, 20, 20);
		group.setBoundingBox(newSize);
		BoundingBox expected = new BoundingBox(0, 0, 20, 20);
		assertEquals(expected, group.getBoundingBox());
	}

	/**
	 * Tests method setBoundingBox().
	 */
	@Test
	public void testSetBoundingBox2() {
		Shape box1 = new SimpleBox(10, 10, 10, 10);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		Shape group = new ShapeGroup(shapes);
		BoundingBox newSize = new BoundingBox(10, 10, 20, 20);
		group.setBoundingBox(newSize);
		BoundingBox expected = new BoundingBox(10, 10, 20, 20);
		assertEquals(expected, group.getBoundingBox());
	}

	/**
	 * Tests method setBoundingBox().
	 */
	@Test
	public void testSetBoundingBox3() {
		Shape box1 = new SimpleBox(0, 0, 10, 10);
		Shape box2 = new SimpleBox(20, 20, 10, 10);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		shapes.add(box2);
		Shape group = new ShapeGroup(shapes);
		BoundingBox newSize = new BoundingBox(0, 0, 60, 60);
		group.setBoundingBox(newSize);
		BoundingBox expected = new BoundingBox(0, 0, 60, 60);
		assertEquals(expected, group.getBoundingBox());
		BoundingBox expectedBox1 = new BoundingBox(0, 0, 20, 20);
		assertEquals(expectedBox1, box1.getBoundingBox());
		BoundingBox expectedBox2 = new BoundingBox(40, 40, 20, 20);
		assertEquals(expectedBox2, box2.getBoundingBox());
	}

	/**
	 * Tests method setBoundingBox().
	 */
	@Test
	public void testSetBoundingBox4() {
		Shape box1 = new SimpleBox(0, 0, 20, 20);
		Shape box2 = new SimpleBox(40, 40, 20, 20);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		shapes.add(box2);
		Shape group = new ShapeGroup(shapes);
		BoundingBox newSize = new BoundingBox(0, 0, 30, 30);
		group.setBoundingBox(newSize);
		BoundingBox expected = new BoundingBox(0, 0, 30, 30);
		assertEquals(expected, group.getBoundingBox());
		BoundingBox expectedBox1 = new BoundingBox(0, 0, 10, 10);
		assertEquals(expectedBox1, box1.getBoundingBox());
		BoundingBox expectedBox2 = new BoundingBox(20, 20, 10, 10);
		assertEquals(expectedBox2, box2.getBoundingBox());
	}

	/**
	 * Tests method setBoundingBox().
	 */
	@Test
	public void testSetBoundingBox5() {
		Shape box1 = new SimpleBox(20, 20, 20, 20);
		Shape box2 = new SimpleBox(40, 40, 40, 40);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		shapes.add(box2);
		Shape group = new ShapeGroup(shapes);
		BoundingBox newSize = new BoundingBox(20, 20, 40, 40);
		group.setBoundingBox(newSize);
		BoundingBox expected = new BoundingBox(20, 20, 40, 40);
		assertEquals(expected, group.getBoundingBox());
		BoundingBox expectedBox1 = new BoundingBox(20, 20, 13, 13);
		assertEquals(expectedBox1, box1.getBoundingBox());
		BoundingBox expectedBox2 = new BoundingBox(33, 33, 27, 27);
		assertEquals(expectedBox2, box2.getBoundingBox());
	}

	/**
	 * Tests method setBoundingBox().
	 */
	@Test
	public void testSetBoundingBox6() {
		Shape box1 = new SimpleBox(20, 20, 20, 20);
		Shape box2 = new SimpleBox(40, 40, 40, 40);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		shapes.add(box2);
		Shape group = new ShapeGroup(shapes);
		BoundingBox newSize = new BoundingBox(20, 20, 60, 60);
		group.setBoundingBox(newSize);
		BoundingBox expected = new BoundingBox(20, 20, 60, 60);
		assertEquals(expected, group.getBoundingBox());
		BoundingBox expectedBox1 = new BoundingBox(20, 20, 20, 20);
		assertEquals(expectedBox1, box1.getBoundingBox());
		BoundingBox expectedBox2 = new BoundingBox(40, 40, 40, 40);
		assertEquals(expectedBox2, box2.getBoundingBox());
	}

	/**
	 * Tests method setBoundingBox().
	 */
	@Test
	public void testSetBoundingBox7() {
		Shape box1 = new SimpleBox(20, 20, 20, 20);
		Shape box2 = new SimpleBox(40, 40, 40, 40);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		shapes.add(box2);
		Shape group = new ShapeGroup(shapes);
		// Shrink it to zero along the x-axis.
		BoundingBox newSize = new BoundingBox(20, 20, 0, 60);
		group.setBoundingBox(newSize);
		BoundingBox expected = new BoundingBox(20, 20, 0, 60);
		assertTrue(expected.equals(group.getBoundingBox()));
		BoundingBox expectedBox1 = new BoundingBox(20, 20, 0, 20);
		assertTrue(expectedBox1.equals(box1.getBoundingBox()));
		BoundingBox expectedBox2 = new BoundingBox(20, 40, 0, 40);
		assertTrue(expectedBox2.equals(box2.getBoundingBox()));
		// Resize it along the x-axis.
		BoundingBox oldSize = new BoundingBox(20, 20, 60, 60);
		group.setBoundingBox(oldSize);
		BoundingBox expected2 = new BoundingBox(20, 20, 60, 60);
		BoundingBox res2 = group.getBoundingBox();
		assertTrue(expected2.equals(res2));
		BoundingBox expectedBox12 = new BoundingBox(20, 20, 20, 20);
		assertTrue(expectedBox12.equals(box1.getBoundingBox()));
		BoundingBox expectedBox22 = new BoundingBox(40, 40, 40, 40);
		assertTrue(expectedBox22.equals(box2.getBoundingBox()));
	}

	/**
	 * Tests method setBoundingBox().
	 */
	@Test
	public void testSetBoundingBox8() {
		Shape box1 = new SimpleBox(20, 20, 20, 20);
		Shape box2 = new SimpleBox(40, 40, 40, 40);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		shapes.add(box2);
		Shape group = new ShapeGroup(shapes);
		// Shrink it to zero along the y-axis.
		BoundingBox newSize = new BoundingBox(20, 20, 60, 0);
		group.setBoundingBox(newSize);
		BoundingBox expected = new BoundingBox(20, 20, 60, 0);
		assertTrue(expected.equals(group.getBoundingBox()));
		BoundingBox expectedBox1 = new BoundingBox(20, 20, 20, 0);
		BoundingBox resBox1 = box1.getBoundingBox();
		assertTrue(expectedBox1.equals(resBox1));
		BoundingBox expectedBox2 = new BoundingBox(40, 20, 40, 0);
		BoundingBox resBox2 = box2.getBoundingBox();
		assertTrue(expectedBox2.equals(resBox2));
		// Resize it along the y-axis.
		BoundingBox oldSize = new BoundingBox(20, 20, 60, 60);
		group.setBoundingBox(oldSize);
		BoundingBox expected2 = new BoundingBox(20, 20, 60, 60);
		BoundingBox resBox22 = group.getBoundingBox();
		assertTrue(expected2.equals(resBox22));
		BoundingBox expectedBox12 = new BoundingBox(20, 20, 20, 20);
		assertTrue(expectedBox12.equals(box1.getBoundingBox()));
		BoundingBox expectedBox22 = new BoundingBox(40, 40, 40, 40);
		assertTrue(expectedBox22.equals(box2.getBoundingBox()));
	}

	/**
	 * Tests method setBoundingBox().
	 */
	@Test
	public void testSetBoundingBox9() {
		Shape box1 = new SimpleBox(20, 20, 20, 20);
		Shape box2 = new SimpleBox(40, 40, 40, 40);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		shapes.add(box2);
		Shape group = new ShapeGroup(shapes);
		// Resize: x = x - 2.
		BoundingBox newSize = new BoundingBox(18, 20, 62, 60);
		group.setBoundingBox(newSize);
		BoundingBox expected = new BoundingBox(18, 20, 62, 60);
		BoundingBox actual = group.getBoundingBox();
		assertTrue(expected.equals(actual));
		BoundingBox expectedBox1 = new BoundingBox(18, 20, 21, 20);
		BoundingBox actualBox1 = box1.getBoundingBox();
		assertTrue(expectedBox1.equals(actualBox1));
		BoundingBox expectedBox2 = new BoundingBox(39, 40, 41, 40);
		BoundingBox actualBox2 = box2.getBoundingBox();
		assertTrue(expectedBox2.equals(actualBox2));
		// Again, resize: x = x - 2.
		BoundingBox newSize2 = new BoundingBox(16, 20, 64, 60);
		group.setBoundingBox(newSize2);
		BoundingBox expected2 = new BoundingBox(16, 20, 64, 60);
		BoundingBox actual2 = group.getBoundingBox();
		assertTrue(expected2.equals(actual2));
		BoundingBox expectedBox12 = new BoundingBox(16, 20, 21, 20);
		BoundingBox actualBox12 = box1.getBoundingBox();
		assertTrue(expectedBox12.equals(actualBox12));
		BoundingBox expectedBox22 = new BoundingBox(37, 40, 43, 40);
		BoundingBox actualBox22 = box2.getBoundingBox();
		assertTrue(expectedBox22.equals(actualBox22));
	}

	/**
	 * Tests dragging handle such that width becomes negative.
	 */
	@Test
	public void testDragHandleToInvertWidth() {
		Shape box1 = new SimpleBox(10, 10, 10, 10);
		Shape box2 = new SimpleBox(40, 40, 10, 10);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		shapes.add(box2);
		Shape group = new ShapeGroup(shapes);
		List<ShapeHandle> handles = group.getShapeHandles();
		ShapeHandle north = null;
		// Find north handle.
		for (ShapeHandle h : handles) {
			if (h.getPosition().equals(new Coord(50, 30))) {
				north = h;
				break;
				// Found north.
			}
		}
		assertNotNull("No east handle for group", north);
		for (int i = 10; i >= 0; i--) {
			north.dragInteraction(new Coord(i, 30), KeyModifier.NONE);
			// the expected width and the actual one must be within 1 pixel
			assertTrue(Math.abs((10 - i) - group.getBoundingBox().getWidth()) <= 1);
		}
	}

	/**
	 * Tests dragging handle such that height becomes negative.
	 */
	@Test
	public void testDragHandleToInvertHeight() {
		Shape box1 = new SimpleBox(10, 10, 10, 10);
		Shape box2 = new SimpleBox(40, 40, 10, 10);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		shapes.add(box2);
		Shape group = new ShapeGroup(shapes);
		List<ShapeHandle> handles = group.getShapeHandles();
		ShapeHandle north = null;
		// Find north handle.
		for (ShapeHandle h : handles) {
			if (h.getPosition().equals(new Coord(30, 10))) {
				north = h;
				break;
				// Found north.
			}
		}
		assertNotNull("No north handle for group", north);
		north.dragInteraction(new Coord(30, 50), KeyModifier.NONE);
		for (int i = 0; i <= 10; i++) {
			north.dragInteraction(new Coord(30, 50 + i), KeyModifier.NONE);
			// the expected height and the actual one must be within 1 pixel
			assertTrue(Math.abs(i - group.getBoundingBox().getHeight()) <= 1);
		}
	}

	/**
	 * Tests the creation of a shape memento and
	 * the setting of it after having reset the
	 * group.
	 */
	@Test
	public void testCreateSetMemento() {
		Shape box1 = new SimpleBox(10, 10, 10, 10);
		Shape box2 = new SimpleBox(40, 40, 10, 10);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		shapes.add(box2);
		Shape group = new ShapeGroup(shapes);
		ShapeGroup.ShapeGroupMemento sm =
				(ShapeGroupMemento) group.createMemento();
		assertNotNull(sm);
		assertEquals(new BoundingBox(10, 10, 40, 40),
					sm.getBoundingBox());
		assertEquals(2, sm.getChildMementos().size());
		// Reset the size of group and its members
		box1.setBoundingBox(new BoundingBox(0, 0, 0, 0));
		box2.setBoundingBox(new BoundingBox(0, 0, 0, 0));
		group.setBoundingBox(new BoundingBox(0, 0, 0, 0));
		// Restore the group.
		group.setMemento(sm);
		// Check it.
		assertEquals(new BoundingBox(10, 10, 40, 40),
				group.getBoundingBox());
	}

	@Test
	public void testCloneMeAndManipulateHandele() {
		Shape box1 = new SimpleBox(10, 10, 10, 10);
		Shape box2 = new SimpleBox(40, 40, 10, 10);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(box1);
		shapes.add(box2);
		Shape group = new ShapeGroup(shapes);
		Shape clone = group.cloneMe();
		assertEquals(new BoundingBox(10, 10, 40, 40),
				clone.getBoundingBox());
		List<ShapeHandle> gHandles = clone.getShapeHandles();
		// Get south-east handle.
		ShapeHandle se = gHandles.get(4);
		se.dragInteraction(new Coord(60, 60), KeyModifier.NONE);
		// Check new size of clone. Due to rounding errors we
		// must expect that the result varies slightly.
		assertTrue(
				new BoundingBox(10, 10, 50, 50).equals(clone.getBoundingBox())
				||
				new BoundingBox(10, 10, 51, 51).equals(clone.getBoundingBox())
				);
	}
}
