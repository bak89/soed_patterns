/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.Coord;
import ch.bfh.due1.jdt.framework.Vector;

/**
 * Test class for Coord.
 * @author Eric Dubuis
 */
public class CoordTest {
	/**
	 * Tests the proerties of a Coord object.
	 */
	@Test
	public void testCreation() {
		Coord c = new Coord(5, 10);
		assertTrue(c.getX0() == 5);
		assertTrue(c.getY0() == 10);
	}

	/**
	 * Tests equality of two Coord objects.
	 */
	@Test
	public void testEquals() {
		Coord c1 = new Coord(10, 20);
		Coord c2 = new Coord(10, 20);
		assertEquals(c1, c2);
		assertEquals(c2, c1);
		assertFalse(c1.equals(new Coord(20, 10)));
		assertFalse(new Coord(20, 10).equals(c1));
		assertFalse(new Coord(20, 10).equals(null));
		assertFalse(new Coord(20, 10).equals(new Object()));
		assertFalse(new Coord(20, 10).equals(new Coord(20, 20)));
	}

	/**
	 * Tests the hash code of two Coord objects.
	 */
	@Test
	public void testHashCode() {
		Coord c1 = new Coord(10, 20);
		Coord c2 = new Coord(10, 20);
		assertTrue(c1.hashCode() == c2.hashCode());
		Coord c3 = new Coord(20, 40);
		assertFalse(c1.hashCode() == c3.hashCode());
	}

	@Test
	public void testDisplacementVector() {
		Coord a = new Coord(2,2);
		Coord b = new Coord(4,6);
		assertEquals(new Vector(2,4), a.getDisplacementVector(b));
	}
}
