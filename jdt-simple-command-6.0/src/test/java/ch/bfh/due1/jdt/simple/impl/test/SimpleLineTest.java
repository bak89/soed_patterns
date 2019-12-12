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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Rectangle;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.simple.impl.shape.SimpleLine;


/**
 * Tests the properties of a straight line.
 *
 * @author Eric Dubuis
 */
public class SimpleLineTest {

	/**
	 * Creates a line at (10, 10), (30, 30) and test its location.
	 */
	@Test
	public void testCreate1() {
		Shape s = new SimpleLine(10, 10, 20, 20);
		BoundingBox expected = new BoundingBox(10, 10, 20, 20);
		assertEquals(expected, s.getBoundingBox());
	}

	/**
	 * Creates a line at (10, 10), (-10, 30) and test its location.
	 */
	@Test
	public void testCreate2() {
		Shape s = new SimpleLine(10, 10, -20, 20);
		BoundingBox expected = new BoundingBox(10, 10, -20, 20);
		assertEquals(expected, s.getBoundingBox());
	}

	/**
	 * Creates a line at (10, 10), (-10, -10) and test its location.
	 */
	@Test
	public void testCreate3() {
		Shape s = new SimpleLine(10, 10, -20, -20);
		BoundingBox expected = new BoundingBox(10, 10, -20, -20);
		assertEquals(expected, s.getBoundingBox());
	}

	/**
	 * Creates a line at (10, 10), (30, -10) and test its location.
	 */
	@Test
	public void testCreate4() {
		Shape s = new SimpleLine(10, 10, 20, -20);
		BoundingBox expected = new BoundingBox(10, 10, 20, -20);
		assertEquals(expected, s.getBoundingBox());
	}

	/**
	 * Sets a line to (10, 10), (30, 30) and test its location.
	 */
	@Test
	public void testSetBoundingBox1() {
		Shape s = new SimpleLine(10, 10, 20, 20);
		BoundingBox newPlace = new BoundingBox(10, 10, 20, 20);
		s.setBoundingBox(newPlace);
		BoundingBox expected = new BoundingBox(10, 10, 20, 20);
		assertEquals(expected, s.getBoundingBox());
	}

	/**
	 * Sets a line to (10, 10), (-10, 30) and test its location.
	 */
	@Test
	public void testSetBoundingBox2() {
		Shape s = new SimpleLine(10, 10, 20, 20);
		BoundingBox newPlace = new BoundingBox(10, 10, -20, 20);
		s.setBoundingBox(newPlace);
		BoundingBox expected = new BoundingBox(10, 10, -20, 20);
		assertEquals(expected, s.getBoundingBox());
	}

	/**
	 * Sets a line to (10, 10), (-10, -10) and test its location.
	 */
	@Test
	public void testSetBoundingBox3() {
		Shape s = new SimpleLine(10, 10, 20, 20);
		BoundingBox newPlace = new BoundingBox(10, 10, -20, -20);
		s.setBoundingBox(newPlace);
		BoundingBox expected = new BoundingBox(10, 10, -20, -20);
		assertEquals(expected, s.getBoundingBox());
		//System.out.println(s);
	}

	/**
	 * Sets a line to (10, 10), (30, -10) and test its location.
	 */
	@Test
	public void testSetBoundingBox4() {
		Shape s = new SimpleLine(10, 10, 20, 20);
		BoundingBox newPlace = new BoundingBox(10, 10, 20, -20);
		s.setBoundingBox(newPlace);
		BoundingBox expected = new BoundingBox(10, 10, 20, -20);
		assertEquals(expected, s.getBoundingBox());
	}

	@Test
	public void testSetBoundingBoxX1() {
		int yBottom = 40;
		Shape s = new SimpleLine(10, 10, 20, 20);
		Rectangle r = s.getBoundingBox().getAWTRectangle();
		// From the equation: r.y + r.h + delta = yBottom
		int delta = yBottom - (r.y + r.height);
		r.y = r.y + delta;
		s.setBoundingBox(new BoundingBox(r));
		BoundingBox expected = new BoundingBox(10, 20, 20, 20);
		assertEquals(expected, s.getBoundingBox());
	}

	@Test
	public void testSetBoundingBoxX2() {
		int yBottom = 40;
		Shape s = new SimpleLine(10, 10, -20, 20);
		BoundingBox oldPlace = s.getBoundingBox();
		Rectangle r = oldPlace.getAWTRectangle();
		// From the equation: r.y + r.h + delta = yBottom
		int delta = yBottom - (r.y + r.height);
		BoundingBox newPlace = new BoundingBox(oldPlace.getX0(), oldPlace.getY0() + delta,
				oldPlace.getWidth(), oldPlace.getHeight());
		s.setBoundingBox(newPlace);
		BoundingBox expected = new BoundingBox(10, 20, -20, 20);
		assertEquals(expected, s.getBoundingBox());
	}

	@Test
	public void testSetBoundingBoxX3() {
		int yBottom = 40;
		Shape s = new SimpleLine(10, 10, -20, -20);
		BoundingBox oldPlace = s.getBoundingBox();
		Rectangle r = oldPlace.getAWTRectangle();
		// From the equation: r.y + r.h + delta = yBottom
		int delta = yBottom - (r.y + r.height);
		BoundingBox newPlace = new BoundingBox(oldPlace.getX0(), oldPlace.getY0() + delta,
				oldPlace.getWidth(), oldPlace.getHeight());
		s.setBoundingBox(newPlace);
		BoundingBox expected = new BoundingBox(10, 40, -20, -20);
		assertEquals(expected, s.getBoundingBox());
	}

	@Test
	public void testSetBoundingBoxX4() {
		int yBottom = 40;
		Shape s = new SimpleLine(10, 10, 20, -20);
		BoundingBox oldPlace = s.getBoundingBox();
		Rectangle r = oldPlace.getAWTRectangle();
		// From the equation: r.y + r.h + delta = yBottom
		int delta = yBottom - (r.y + r.height);
		BoundingBox newPlace = new BoundingBox(oldPlace.getX0(), oldPlace.getY0() + delta,
				oldPlace.getWidth(), oldPlace.getHeight());
		s.setBoundingBox(newPlace);
		BoundingBox expected = new BoundingBox(10, 40, 20, -20);
		assertEquals(expected, s.getBoundingBox());
	}

	// Note: Contains() test methods assume an epsilon of 2.
	// =====================================================

	@Test
	public void testContains1() {
		Shape s = new SimpleLine(10, 10, 20, 20);
		Coord c = new Coord(15, 15);
		assertTrue("Expected c to be contained by line", s.contains(c));
	}

	@Test
	public void testNotContains2() {
		Shape s = new SimpleLine(10, 10, 10, 10);
		Coord c = new Coord(15, 10);
		assertFalse("Expected c not to be contained by line", s.contains(c));
	}

	@Test
	public void testContains3a() {
		Shape s = new SimpleLine(10, 10, 10, 0);
		Coord c = new Coord(10, 10);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(10, 11);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(10, 12);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(10, 13);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(10, 9);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(10, 8);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(10, 7);
		assertFalse("Expected c not to be contained by line", s.contains(c));
	}

	@Test
	public void testContains3b() {
		Shape s = new SimpleLine(10, 10, 10, 0);
		Coord c = new Coord(15, 10);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(15, 11);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(15, 12);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(15, 13);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(15, 9);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(15, 8);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(15, 7);
		assertFalse("Expected c not to be contained by line", s.contains(c));
	}

	@Test
	public void testContains3c() {
		Shape s = new SimpleLine(10, 10, 10, 0);
		Coord c = new Coord(20, 10);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(20, 11);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(20, 12);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(20, 13);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(20, 9);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(20, 8);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(20, 7);
		assertFalse("Expected c not to be contained by line", s.contains(c));
	}

	@Test
	public void testContains4() {
		Shape s = new SimpleLine(10, 10, 10, 0);
		Coord c = new Coord(10, 10);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(10, 11);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(10, 12);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(9, 10);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(9, 11);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(9, 12);
		assertFalse("Expected c not to be contained by line", s.contains(c));//
		c = new Coord(9, 9);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(9, 8);
		assertFalse("Expected c not to be contained by line", s.contains(c));//
	}

	@Test
	public void testContains5() {
		Shape s = new SimpleLine(10, 10, 10, 0);
		Coord c = new Coord(20, 10);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(20, 11);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(20, 12);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(21, 10);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(21, 11);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(21, 12);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(21, 9);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(21, 8);
		assertFalse("Expected c not to be contained by line", s.contains(c));
	}

	@Test
	public void testContains6() {
		Shape s = new SimpleLine(10, 10, 0, 10);
		Coord c = new Coord(10, 10);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(10, 15);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(10, 20);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(9, 10);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(9, 15);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(9, 20);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(8, 10);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(8, 15);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(8, 20);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(11, 10);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(11, 15);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(11, 20);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(12, 10);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(12, 15);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(12, 20);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(10, 9);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(10, 21);
		assertFalse("Expected c not to be contained by line", s.contains(c));
	}

	@Test
	public void testContains7() {
		Shape s = new SimpleLine(10, 10, 10, 10);
		Coord c = new Coord(10, 10);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(20, 20);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(21, 21);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(9, 9);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(11, 11);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(11, 12);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(11, 13);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(11, 14);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(19, 19);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(19, 18);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(19, 17);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(19, 16);
		assertFalse("Expected c not to be contained by line", s.contains(c));
	}

	@Test
	public void testContains8() {
		Shape s = new SimpleLine(10, 10, 10, -10);
		Coord c = new Coord(10, 10);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(20, 0);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(21, -1);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(11, 11);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(11, 12);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(11, 9);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(11, 10);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(12, 11);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(11, 12);
		assertFalse("Expected c not to be contained by line", s.contains(c));
		c = new Coord(19, 1);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(19, 0);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(19, -1);
		assertTrue("Expected c to be contained by line", s.contains(c));
		c = new Coord(19, -2);
		assertFalse("Expected c not to be contained by line", s.contains(c));
	}

	/**
	 * Test whether the created memento restores the line.
	 */
	@Test
	public void testMemento() {
		Shape s = new SimpleLine(10, 20, 30, 40);
		Memento memento = s.createMemento();
		s.setBoundingBox(new BoundingBox(0, 0, 0, 0));
		s.setMemento(memento);
		assertEquals(new BoundingBox(10, 20, 30, 40), s.getBoundingBox());
	}
}
