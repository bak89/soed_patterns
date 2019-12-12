/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Software Engineering and Design -- Design patterns
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.strategy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NumberFieldEvaluationTest {

	@Test
	public void test01() {
		// 123: OK
		assertTrue(new NumberFieldEvaluation().isValid("123"));
	}

	@Test
	public void test02() {
		// .123: OK
		assertTrue(new NumberFieldEvaluation().isValid(".123"));
	}

	@Test
	public void test03() {
		// 1.123E33: OK
		assertTrue(new NumberFieldEvaluation().isValid("1.123E33"));
	}

	@Test
	public void test04() {
		// 0: OK
		assertTrue(new NumberFieldEvaluation().isValid("0"));
	}

	@Test
	public void test05() {
		// -0.0: OK
		assertTrue(new NumberFieldEvaluation().isValid("-0.0"));
	}


	// The following ones should produce errors:
	public void test01e() {
		// "": FALSE
		assertTrue(!new NumberFieldEvaluation().isValid(""));
	}

	public void test02e() {
		// "-": FALSE
		assertTrue(!new NumberFieldEvaluation().isValid("-"));
	}

	public void test03e() {
		// "0,0123": FALSE
		// Localization?!?
		assertTrue(!new NumberFieldEvaluation().isValid("0,0123"));
	}
}
