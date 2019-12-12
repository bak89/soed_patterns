/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Software Engineering and Design -- Design patterns
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.strategy;

import org.junit.Test;

import junit.framework.TestCase;

public class FieldEvaluatorTest extends TestCase {

	private FieldEvaluator fe;

	@Override
	protected void setUp() {
		fe = new FieldEvaluator();
	}

	@Test
	public void test_n01() {
		// 123: OK
		fe.setFieldEvaluation(new NumberFieldEvaluation());
		assertTrue(fe.evaluate("123"));
	}

	@Test
	public void test_n02() {
		// .123: OK
		fe.setFieldEvaluation(new NumberFieldEvaluation());
		assertTrue(fe.evaluate(".123"));
	}

	@Test
	public void test_n03() {
		// 1.123E33: OK
		fe.setFieldEvaluation(new NumberFieldEvaluation());
		assertTrue(fe.evaluate("1.123E33"));
	}

	@Test
	public void test_n04() {
		// 0: OK
		fe.setFieldEvaluation(new NumberFieldEvaluation());
		assertTrue(fe.evaluate("0"));
	}

	@Test
	public void test_n05() {
		// -0.0: OK
		fe.setFieldEvaluation(new NumberFieldEvaluation());
		assertTrue(fe.evaluate("-0.0"));
	}


	// The following ones should produce errors:
	@Test
	public void test_n01e() {
		// "": FALSE
		fe.setFieldEvaluation(new NumberFieldEvaluation());
		assertTrue(!fe.evaluate(""));
	}

	@Test
	public void test_n02e() {
		// "-": FALSE
		fe.setFieldEvaluation(new NumberFieldEvaluation());
		assertTrue(!fe.evaluate("-"));
	}

	@Test
	public void test_n03e() {
		// "0,0123": FALSE
		// Localization?!?
		fe.setFieldEvaluation(new NumberFieldEvaluation());
		assertTrue(!fe.evaluate("0,0123"));
	}

	// *********************
	// Test for email fields.
	//

	@Test
	public void test_e01() {
		// user@host: OK
		fe.setFieldEvaluation(new EmailFieldEvaluation());
		assertTrue(fe.evaluate("user@host"));
	}

	@Test
	public void test_e02() {
		// first.last@host: OK
		fe.setFieldEvaluation(new EmailFieldEvaluation());
		assertTrue(fe.evaluate("first.last@host"));
	}

	@Test
	public void test_e03() {
		// first.last@host.domain: OK
		fe.setFieldEvaluation(new EmailFieldEvaluation());
		assertTrue(fe.evaluate("first.last@host.domain"));
	}

	// The following ones should produce errors:
	@Test
	public void test_e01e() {
		// "": FALSE
		fe.setFieldEvaluation(new EmailFieldEvaluation());
		assertTrue(!fe.evaluate(""));
	}

	@Test
	public void test_e02e() {
		// " ": FALSE
		fe.setFieldEvaluation(new EmailFieldEvaluation());
		assertTrue(!fe.evaluate(" "));
	}

	@Test
	public void test_e03e() {
		// "user": FALSE
		fe.setFieldEvaluation(new EmailFieldEvaluation());
		assertTrue(!fe.evaluate("user"));
	}

	@Test
	public void test_e04e() {
		// "user.": FALSE
		fe.setFieldEvaluation(new EmailFieldEvaluation());
		assertTrue(!fe.evaluate("user."));
	}

	@Test
	public void test_e05e() {
		// "user@": FALSE
		fe.setFieldEvaluation(new EmailFieldEvaluation());
		assertTrue(!fe.evaluate("user@"));
	}

	@Test
	public void test_e06e() {
		// "user@.": FALSE
		fe.setFieldEvaluation(new EmailFieldEvaluation());
		assertTrue(!fe.evaluate("user@."));
	}

	@Test
	public void test_e07e() {
		// "user.@": FALSE
		fe.setFieldEvaluation(new EmailFieldEvaluation());
		assertTrue(!fe.evaluate("user.@"));
	}

	@Test
	public void test_e08e() {
		// "user@domain.": FALSE
		fe.setFieldEvaluation(new EmailFieldEvaluation());
		assertTrue(!fe.evaluate("user@domain."));
	}

	@Test
	public void test_e09e() {
		// "user @ host . domain": FALSE
		fe.setFieldEvaluation(new EmailFieldEvaluation());
		assertTrue(!fe.evaluate("user@domain."));
	}

	// *********************
	// FIXME: Test for date fields -- To be provided.
	//
}
