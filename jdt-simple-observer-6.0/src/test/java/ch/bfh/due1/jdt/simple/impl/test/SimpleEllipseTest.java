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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.BoundingBox;
import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.Memento;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Vector;
import ch.bfh.sed.observerpattern.SimpleBox;
import ch.bfh.sed.observerpattern.SimpleEllipse;

/**
 * Tests some features of a simple box.
 *
 * @author Eric Dubuis
 */
public class SimpleEllipseTest {

	/**
	 * Creates a simple ellipse and tests the size of its expected bounding box.
	 */
	@Test
	public void testCreate1() {
		Shape box = new SimpleEllipse(0, 0, 10, 10);
		BoundingBox expected = new BoundingBox(0, 0, 10, 10);
		assertEquals(expected, box.getBoundingBox());
	}

	/**
	 * Creates a simple ellipse, sets a new bounding box, and tests the size of its
	 * expected bounding box.
	 */
	@Test
	public void testSetBoundingBox1() {
		Shape box = new SimpleEllipse(10, 0, 10, 10);
		box.setBoundingBox(new BoundingBox(0, 0, 10, 10));
		BoundingBox expected = new BoundingBox(0, 0, 10, 10);
		assertEquals(expected, box.getBoundingBox());
	}

	/**
	 * Creates a simple box, moves it, and tests its expected bounding box.
	 */
	@Test
	public void testMove0() {
		Shape box = new SimpleEllipse(10, 0, 10, 10);
		box.move(new Vector(0, 0));
		BoundingBox expected = new BoundingBox(10, 0, 10, 10);
		assertEquals(expected, box.getBoundingBox());
	}

	/**
	 * Creates a simple box, moves it, and tests its expected bounding box.
	 */
	@Test
	public void testMove1() {
		Shape box = new SimpleEllipse(10, 0, 10, 10);
		box.move(new Vector(10, 20));
		BoundingBox expected = new BoundingBox(20, 20, 10, 10);
		assertEquals(expected, box.getBoundingBox());
	}

	/**
	 * Creates a simple box, and tests if a given coordinate is within the
	 * simple box.
	 */
	@Test
	public void testContains1() {
		Shape box = new SimpleEllipse(10, 0, 10, 10);
		assertTrue(box.contains(new Coord(15, 5)));
		assertFalse(box.contains(new Coord(5, 5)));
	}

	/**
	 * Tests the handles of a simple box.
	 */
	@Test
	public void testHandles1() {
		Shape box = new SimpleBox(10, 10, 10, 10);
		List<Coord> coords = new ArrayList<Coord>();
		coords.add(new Coord(10, 10));
		coords.add(new Coord(15, 10));
		coords.add(new Coord(20, 10));
		coords.add(new Coord(10, 20));
		coords.add(new Coord(15, 20));
		coords.add(new Coord(20, 20));
		coords.add(new Coord(10, 15));
		coords.add(new Coord(20, 15));
		for (ShapeHandle h : box.getShapeHandles()) {
			for (Iterator<Coord> it = coords.iterator(); it.hasNext();) {
				Coord c = it.next();
				if (h.contains(c)) {
					it.remove();
					break;
				}
			}
		}
		assertTrue(coords.size() == 0);
	}

	/**
	 * Test whether the created memento restores the ellipse.
	 */
	@Test
	public void testMemento() {
		Shape s = new SimpleEllipse(10, 20, 30, 40);
		Memento memento = s.createMemento();
		s.setBoundingBox(new BoundingBox(0, 0, 0, 0));
		s.setMemento(memento);
		assertEquals(new BoundingBox(10, 20, 30, 40), s.getBoundingBox());
	}
}
