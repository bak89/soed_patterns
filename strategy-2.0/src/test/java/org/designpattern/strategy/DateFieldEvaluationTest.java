/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.strategy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Some test for different formats and dates. By no means complete...
 *
 * @author Eric Dubuis
 */
public class DateFieldEvaluationTest {

	@Test
	public void test01() {
		// 2015-12-07
		assertTrue(new DateFieldEvaluation().isValid("2015-12-07"));
	}

	@Test
	public void test01a() {
		// 2015-12-7
		assertFalse(new DateFieldEvaluation().isValid("2015-12-7"));
	}

	@Test
	public void test02() {
		// 2015.12.07
		assertTrue(new DateFieldEvaluation().isValid("2015.12.07"));
	}

	@Test
	public void test02a() {
		// 2015.12.7
		assertFalse(new DateFieldEvaluation().isValid("2015.12.7"));
	}

	@Test
	public void test03() {
		// 07.12.2015
		assertTrue(new DateFieldEvaluation().isValid("07.12.2015"));
	}

	@Test
	public void test03a() {
		// 7.12.2015
		assertFalse(new DateFieldEvaluation().isValid("7.12.2015"));
	}

	@Test
	public void test04() {
		// 07.12.15
		assertTrue(new DateFieldEvaluation().isValid("07.12.15"));
	}

	@Test
	public void test04a() {
		// 7.12.15
		assertTrue(new DateFieldEvaluation().isValid("7.12.15"));
	}

	@Test
	public void test04b() {
		// 7.12.15
		assertTrue(new DateFieldEvaluation().isValid("7.2.15"));
	}

	@Test
	public void test05() {
		// 29.2.2015 (does not exist)
		assertFalse(new DateFieldEvaluation().isValid("29.2.2015"));
	}

	@Test
	public void test06() {
		// 2015.13.41 (does not exist)
		assertFalse(new DateFieldEvaluation().isValid("2015.13.41"));
	}
}
