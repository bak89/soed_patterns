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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.Vector;

/**
 * Tests some features of a vector.
 * 
 * @author Eric Dubuis
 */
public class VectorTest {

	/**
	 * Creates a vector and tests its properties.
	 */
	@Test
	public void testCreateAndGet() {
		Vector v = new Vector(5, 10);
		assertEquals(5, v.getXComponent());
		assertEquals(10, v.getYComponent());
	}

	/**
	 * Tests equality and hash code.
	 */
	@Test
	public void testEqualityAndHashCode() {
		Vector v1 = new Vector(10, 20);
		Vector v2 = new Vector(10, 20);
		Vector v3 = new Vector(20, 30);
		assertEquals(v1, v2);
		assertEquals(v2, v1);
		assertTrue(v1.hashCode() == v2.hashCode());
		assertNotSame(v1, v3);
		assertNotSame(v3, v1);
		assertFalse(v1.hashCode() == v3.hashCode());
		assertTrue(new Vector(10, 20).equals(new Vector(10, 20)));
		assertTrue(v1.equals(v1));
		assertFalse(new Vector(10, 20).equals(null));
		assertFalse(new Vector(10, 20).equals(new Object()));
		assertFalse(new Vector(10, 20).equals(new Vector(20, 10)));
		assertFalse(new Vector(10, 20).equals(new Vector(10, 10)));
	}

	@Test
	public void testScalarProduct1() {
		Vector v1 = new Vector(1, 0);
		Vector v2 = new Vector(1, 0);
		assertEquals(1.0, v1.scalarProduct(v2), 0.000001);
	}

	@Test
	public void testScalarProduct2() {
		Vector v1 = new Vector(3, 4);
		Vector v2 = new Vector(3, 4);
		assertEquals(25.0, v1.scalarProduct(v2), 0.000001);
	}

	@Test
	public void testScalarProduct3() {
		Vector v1 = new Vector(3, 4);
		Vector v2 = new Vector(-3, -4);
		assertEquals(-25.0, v1.scalarProduct(v2), 0.000001);
		assertEquals(-25.0, v2.scalarProduct(v1), 0.000001);
	}

	@Test
	public void testScalarProduct4() {
		Vector v1 = new Vector(2, 0);
		Vector v2 = new Vector(0, 5);
		assertEquals(0.0, v1.scalarProduct(v2), 0.000001);
	}

	@Test
	public void testMagnitude1() {
		Vector v = new Vector(0, 0);
		assertEquals(0.0, v.magnitude(), 0.000001);
	}

	@Test
	public void testMagnitude2() {
		Vector v = new Vector(1, 0);
		assertEquals(1.0, v.magnitude(), 0.000001);
	}

	@Test
	public void testMagnitude3() {
		Vector v = new Vector(-1, -1);
		assertEquals(Math.sqrt(2), v.magnitude(), 0.000001);
	}

	@Test
	public void testMagnitude4() {
		Vector v = new Vector(3, -4);
		assertEquals(5.0, v.magnitude(), 0.000001);
	}

	@Test
	public void testNormalVector1() {
		Vector v = new Vector(3, 4);
		assertEquals(new Vector(-4, 3), v.getNormalVector());
	}

	@Test
	public void testNormalVector2() {
		Vector v = new Vector(-4, 3);
		assertEquals(new Vector(-3, -4), v.getNormalVector());
	}

	@Test
	public void testNormalVector3() {
		Vector v = new Vector(-3, -4);
		assertEquals(new Vector(4, -3), v.getNormalVector());
	}

	@Test
	public void testNormalVector4() {
		Vector v = new Vector(4, -3);
		assertEquals(new Vector(3, 4), v.getNormalVector());
	}

	@Test
	public void testPlusVector() {
		Vector v = new Vector(3, 4);
		assertEquals(new Vector(6, 8), v.plus(new Vector(3,4)));
	}

	@Test
	public void testMinusVector() {
		Vector v = new Vector(3, 4);
		assertEquals(new Vector(0, 0), v.minus(new Vector(3,4)));
	}

	@Test
	public void testGetDeterminant() {
		Vector a = new Vector(1, 4);
		Vector b = new Vector(2, 5);
		assertEquals(-3.0, a.getDeterminantBy(b), 0.000001);
	}
}
