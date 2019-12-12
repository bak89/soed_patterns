/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import java.awt.Rectangle;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;

/**
 * Tests the properties of the BoundingBox class of the framework.
 * 
 * @author Eric Dubuis
 */
public class BoundingBoxTest {
	/**
	 * Tests the properties of a new bounding box.
	 */
	@Test
	public void testCreation() {
		Coord origin = new Coord(10, 20);
		BoundingBox bb = new BoundingBox(origin, 50, 100);
		assertEquals(origin, bb.getOrigin());
		assertEquals(50, bb.getWidth());
		assertEquals(100, bb.getHeight());
	}

	/**
	 * Test equality of two bounding boxes.
	 */
	@Test
	public void testEquals1() {
		Coord origin = new Coord(10, 20);
		BoundingBox bb = new BoundingBox(origin, 50, 100);
		assertTrue(bb.equals(new BoundingBox(origin, 50, 100)));
		assertFalse(bb.equals(new BoundingBox(origin, 40, 100)));
	}

	/**
	 * Tests some special cases.
	 */
	@Test
	public void testEquals2() {
		Coord origin = new Coord(10, 20);
		BoundingBox bb = new BoundingBox(origin, 50, 100);
		// unequality if argument is null
		assertFalse(bb.equals(null));
		// uneqality if argument is not of same type
		assertFalse(bb.equals(new String()));
		// unequality if target's origin is null, but not the one of the arg
		BoundingBox bb1 = new BoundingBox(null, 50, 100);
		assertFalse(bb1.equals(bb));
		// equality if target's origin is null as well as the one of the arg
		BoundingBox bb2 = new BoundingBox(null, 50, 100);
		assertTrue(bb2.equals(bb1));
	}

	/**
	 * Test if two equal bounding boxes have the same hash code.
	 */
	@Test
	public void testHashCode1() {
		Coord origin = new Coord(10, 20);
		BoundingBox bb = new BoundingBox(origin, 50, 100);
		assertTrue(bb.equals(new BoundingBox(origin, 50, 100)));
		assertTrue(bb.hashCode() == new BoundingBox(origin, 50, 100).hashCode());
	}

	/**
	 * Tests some special cases.
	 */
	@Test
	public void testHashCode2() {
		BoundingBox bb = new BoundingBox(null, 50, 100);
		assertTrue(bb.hashCode() != 0);
	}

	/**
	 * Given a bounding box with properties bb.x, bb.y, bb.width, and bb.height
	 * where bb.width > 0 and bb.height > 0, test whether the getAWTRectangle()
	 * method returns an AWT rectangle with properties r.x = bb.x, r.y = bb.y,
	 * r.width = bb.width, and r.height = bb.height.
	 */
	@Test
	public void testGetAWTRectangle1() {
		BoundingBox bb = new BoundingBox(10, 20, 50, 100);
		Rectangle r = bb.getAWTRectangle();
		assertEquals(r.x, 10);
		assertEquals(r.y, 20);
		assertEquals(r.width, 50);
		assertEquals(r.height, 100);
	}

	/**
	 * Given a bounding box with properties bb.x, bb.y, bb.width, and bb.height
	 * where bb.width < 0 and bb.height > 0, test whether the getAWTRectangle()
	 * method returns an AWT rectangle with properties r.x = bb.x+bb.width, r.y =
	 * bb.y, r.width = -bb.widht, and r.height = bb.height.
	 */
	@Test
	public void testGetAWTRectangle2() {
		BoundingBox bb = new BoundingBox(10, 20, -50, 100);
		Rectangle r = bb.getAWTRectangle();
		assertEquals(r.x, -40);
		assertEquals(r.y, 20);
		assertEquals(r.width, 50);
		assertEquals(r.height, 100);
	}

	/**
	 * Given a bounding box with properties bb.x, bb.y, bb.width, and bb.height
	 * where bb.width > 0 and bb.height < 0, test whether the getAWTRectangle()
	 * method returns an AWT rectangle with properties r.x = bb.x, r.y =
	 * bb.y+bb.height, r.width = bb.widht, and r.height = -bb.height.
	 */
	@Test
	public void testGetAWTRectangle3() {
		BoundingBox bb = new BoundingBox(10, 20, 50, -100);
		Rectangle r = bb.getAWTRectangle();
		assertEquals(r.x, 10);
		assertEquals(r.y, -80);
		assertEquals(r.width, 50);
		assertEquals(r.height, 100);
	}

	/**
	 * Given a bounding box with properties bb.x, bb.y, bb.width, and bb.height
	 * where bb.width < 0 and bb.height < 0, test whether the getAWTRectangle()
	 * method returns an AWT rectangle with properties r.x = bb.x, r.y =
	 * bb.y+bb.height, r.width = bb.widht, and r.height = -bb.height.
	 */
	@Test
	public void testGetAWTRectangle4() {
		BoundingBox bb = new BoundingBox(10, 20, -50, -100);
		Rectangle r = bb.getAWTRectangle();
		assertEquals(r.x, -40);
		assertEquals(r.y, -80);
		assertEquals(r.width, 50);
		assertEquals(r.height, 100);
	}

	/**
	 * Given a bounding box with properties bb.x, bb.y, bb.width, and bb.height
	 * where bb.width > 0 and bb.height > 0, and the coordinate point with
	 * properties c.x and x.y where bb.x <= c.x <= bb.x + bb.width-1 and bb.y <=
	 * c.y <= bb.y + bb.width-1, tests if method contains() returns true.
	 */
	@Test
	public void testContains1() {
		int x = 10;
		int y = 20;
		int width = 50;
		int height = 100;
		BoundingBox bb = new BoundingBox(x, y, width, height);
		Coord c1 = new Coord(x, y);
		assertTrue(bb.contains(c1));
		Coord c2 = new Coord(x + width - 1, y + height - 1);
		assertTrue(bb.contains(c2));
		Coord c3 = new Coord(x + ((int) 0.5 * width), y + ((int) 0.5 * height));
		assertTrue(bb.contains(c3));
	}

	/**
	 * Given a bounding box with properties bb.x, bb.y, bb.width, and bb.height
	 * where bb.width < 0 and bb.height < 0, and the coordinate point with
	 * properties c.x and x.y where bb.x + bb.width <= c.x <= bb.x-1 and bb.y <=
	 * c.y <= bb.y + bb.width-1, tests if method contains() returns true.
	 */
	@Test
	public void testContains2() {
		int x = 10;
		int y = 20;
		int width = -50;
		int height = 100;
		BoundingBox bb = new BoundingBox(x, y, width, height);
		Coord c1 = new Coord(x + width, y);
		assertTrue(bb.contains(c1));
		Coord c2 = new Coord(x - 1, y + height - 1);
		assertTrue(bb.contains(c2));
		Coord c3 = new Coord(x + width + ((int) 0.5 * width), y
				+ ((int) 0.5 * height));
		assertTrue(bb.contains(c3));
	}

	/**
	 * Given a bounding box with properties bb.x, bb.y, bb.width, and bb.height
	 * where bb.width > 0 and bb.height < 0, and the coordinate point with
	 * properties c.x and x.y where bb.x <= c.x <= bb.x + bb.width-1 and bb.y +
	 * bb.width <= c.y <= bb.y-1, tests if method contains() returns true.
	 */
	@Test
	public void testContains3() {
		int x = 10;
		int y = 20;
		int width = 50;
		int height = -100;
		BoundingBox bb = new BoundingBox(x, y, width, height);
		Coord c1 = new Coord(x, y + height);
		assertTrue(bb.contains(c1));
		Coord c2 = new Coord(x + width - 1, y - 1);
		assertTrue(bb.contains(c2));
		Coord c3 = new Coord(x + ((int) 0.5 * width), y + height
				+ ((int) 0.5 * height));
		assertTrue(bb.contains(c3));
	}

	/**
	 * Given a bounding box with properties bb.x, bb.y, bb.width, and bb.height
	 * where bb.width > 0 and bb.height < 0, and the coordinate point with
	 * properties c.x and x.y where bb.x <= c.x <= bb.x + bb.width-1 and bb.y +
	 * bb.width <= c.y <= bb.y-1, tests if method contains() returns true.
	 */
	@Test
	public void testContains4() {
		int x = 10;
		int y = 20;
		int width = -50;
		int height = -100;
		BoundingBox bb = new BoundingBox(x, y, width, height);
		Coord c1 = new Coord(x + width, y + height);
		assertTrue(bb.contains(c1));
		Coord c2 = new Coord(x - 1, y - 1);
		assertTrue(bb.contains(c2));
		Coord c3 = new Coord(x + width + ((int) 0.5 * width), y + height
				+ ((int) 0.5 * height));
		assertTrue(bb.contains(c3));
	}

	/**
	 * Given bb1 with properties bb1.x, bb1.y, bb1.width, and bb1.height. Given
	 * bb2 with properties bb2.x, bb2.y, bb2.width, and bb2.height. Let bb1.x <
	 * bb2.x, bb1.y < bb2.x and bb1.x + bb1.width < bb2.x + bb2.width, bb1.y +
	 * bb1.height < bb2.y + bb2.height. Test whether the resulting bounding box
	 * res has the properties res.x = bb1.x, res.y = bb1.y, res.width = bb2.x +
	 * bb2.width - bb1.x, and res.height = bb2.y + bb2.height - bb1.y.
	 */
	@Test
	public void testUnion1() {
		int x1 = 10;
		int y1 = 20;
		int width1 = 40;
		int height1 = 100;
		int x2 = 40;
		int y2 = 60;
		int width2 = 40;
		int height2 = 100;
		BoundingBox bb1 = new BoundingBox(x1, y1, width1, height1);
		BoundingBox bb2 = new BoundingBox(x2, y2, width2, height2);
		BoundingBox res1 = bb1.union(bb2);
		BoundingBox res2 = bb2.union(bb1);
		assertEquals(res1, res2);
		assertEquals(res2, res1);
		assertEquals(x1, res1.getX0());
		assertEquals(y1, res1.getY0());
		assertEquals(x2 + width2 - x1, res1.getWidth());
		assertEquals(y2 + height2 - y1, res1.getHeight());
	}

	/**
	 * Given bb1 with properties bb1.x, bb1.y, bb1.width, and bb1.height. Given
	 * bb2 with properties bb2.x, bb2.y, bb2.width, and bb2.height. Let bb1.x >
	 * bb2.x, bb1.y < bb2.x and bb1.x + bb1.width > bb2.x + bb2.width, bb1.y +
	 * bb1.height < bb2.y + bb2.height. Test whether the resulting bounding box
	 * res has the properties res.x = bb2.x, res.y = bb1.y, res.width = bb1.x +
	 * bb1.width - bb2.x, and res.height = bb2.y + bb2.height - bb1.y.
	 */
	@Test
	public void testUnion2() {
		int x1 = 10;
		int y1 = 20;
		int width1 = 40;
		int height1 = 100;
		int x2 = 0;
		int y2 = 60;
		int width2 = 40;
		int height2 = 100;
		BoundingBox bb1 = new BoundingBox(x1, y1, width1, height1);
		BoundingBox bb2 = new BoundingBox(x2, y2, width2, height2);
		BoundingBox res1 = bb1.union(bb2);
		BoundingBox res2 = bb2.union(bb1);
		assertEquals(res1, res2);
		assertEquals(res2, res1);
		assertEquals(x2, res1.getX0());
		assertEquals(y1, res1.getY0());
		assertEquals(x1 + width1 - x2, res1.getWidth());
		assertEquals(y2 + height2 - y1, res1.getHeight());
	}

	/**
	 * Given bb1 with properties bb1.x, bb1.y, bb1.width, and bb1.height. Given
	 * bb2 with properties bb2.x, bb2.y, bb2.width, and bb2.height. Let bb1.x <
	 * bb2.x, bb1.y > bb2.x and bb1.x + bb1.width < bb2.x + bb2.width, bb1.y +
	 * bb1.height > bb2.y + bb2.height. Test whether the resulting bounding box
	 * res has the properties res.x = bb1.x, res.y = bb1.y, res.width = bb2.x +
	 * bb2.width - bb1.x, and res.height = bb2.y + bb2.height - bb1.y.
	 */
	@Test
	public void testUnion3() {
		int x1 = 10;
		int y1 = 20;
		int width1 = 40;
		int height1 = 100;
		int x2 = 40;
		int y2 = 10;
		int width2 = 40;
		int height2 = 100;
		BoundingBox bb1 = new BoundingBox(x1, y1, width1, height1);
		BoundingBox bb2 = new BoundingBox(x2, y2, width2, height2);
		BoundingBox res1 = bb1.union(bb2);
		BoundingBox res2 = bb2.union(bb1);
		assertEquals(res1, res2);
		assertEquals(res2, res1);
		assertEquals(x1, res1.getX0());
		assertEquals(y2, res1.getY0());
		assertEquals(x2 + width2 - x1, res1.getWidth());
		assertEquals(y1 + height1 - y2, res1.getHeight());
	}

	/**
	 * Given bb1 with properties bb1.x, bb1.y, bb1.width, and bb1.height. Given
	 * bb2 with properties bb2.x, bb2.y, bb2.width, and bb2.height. Let bb1.x <
	 * bb2.x, bb1.y < bb2.x and bb1.x + bb1.width < bb2.x + bb2.width, bb1.y +
	 * bb1.height < bb2.y + bb2.height. Test whether the resulting bounding box
	 * res has the properties res.x = bb1.x, res.y = bb1.y, res.width = bb2.x +
	 * bb2.width - bb1.x, and res.height = bb2.y + bb2.height - bb1.y.
	 */
	@Test
	public void testUnion4() {
		int x1 = 10;
		int y1 = 20;
		int width1 = 40;
		int height1 = 100;
		int x2 = 0;
		int y2 = 10;
		int width2 = 40;
		int height2 = 100;
		BoundingBox bb1 = new BoundingBox(x1, y1, width1, height1);
		BoundingBox bb2 = new BoundingBox(x2, y2, width2, height2);
		BoundingBox res1 = bb1.union(bb2);
		BoundingBox res2 = bb2.union(bb1);
		assertEquals(res1, res2);
		assertEquals(res2, res1);
		assertEquals(x2, res1.getX0());
		assertEquals(y2, res1.getY0());
		assertEquals(x1 + width1 - x2, res1.getWidth());
		assertEquals(y1 + height1 - y2, res1.getHeight());
	}

	/**
	 * Tests the toString() method.
	 */
	@Test
	public void testToString() {
		Coord origin = new Coord(10, 20);
		BoundingBox bb = new BoundingBox(origin, 50, 100);
		assertEquals("BB[10,20,50,100]", bb.toString());
	}
}
