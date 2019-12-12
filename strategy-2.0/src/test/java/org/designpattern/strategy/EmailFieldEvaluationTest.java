/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Software Engineering and Design -- Design patterns
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.strategy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EmailFieldEvaluationTest {

	@Test
	public void test01() {
		// user@host: OK
		assertTrue(new EmailFieldEvaluation().isValid("user@host"));
	}

	@Test
	public void test02() {
		// first.last@host: OK
		assertTrue(new EmailFieldEvaluation().isValid("first.last@host"));
	}

	@Test
	public void test03() {
		// first.last@host.domain: OK
		assertTrue(new EmailFieldEvaluation().isValid("first.last@host.domain"));
	}

	// The following ones should produce errors:
	@Test
	public void test01e() {
		// "": FALSE
		assertTrue(!new EmailFieldEvaluation().isValid(""));
	}

	@Test
	public void test02e() {
		// " ": FALSE
		assertTrue(!new EmailFieldEvaluation().isValid(" "));
	}

	@Test
	public void test03e() {
		// "user": FALSE
		assertTrue(!new EmailFieldEvaluation().isValid("user"));
	}

	@Test
	public void test04e() {
		// "user.": FALSE
		assertTrue(!new EmailFieldEvaluation().isValid("user."));
	}

	@Test
	public void test05e() {
		// "user@": FALSE
		assertTrue(!new EmailFieldEvaluation().isValid("user@"));
	}

	@Test
	public void test06e() {
		// "user@.": FALSE
		assertTrue(!new EmailFieldEvaluation().isValid("user@."));
	}

	@Test
	public void test07e() {
		// "user.@": FALSE
		assertTrue(!new EmailFieldEvaluation().isValid("user.@"));
	}

	@Test
	public void test08e() {
		// "user@domain.": FALSE
		assertTrue(!new EmailFieldEvaluation().isValid("user@domain."));
	}

	@Test
	public void test09e() {
		// "user @ host . domain": FALSE
		assertTrue(!new EmailFieldEvaluation().isValid("user@domain."));
	}

	@Test
	public void test10e() {
		// A couple of tests according to this:
		// http://stackoverflow.com/questions/8204680/java-regex-email
		assertTrue(new EmailFieldEvaluation().isValid("\"Fred Bloggs\"@example.com"));
		assertFalse(new EmailFieldEvaluation().isValid("user@.invalid.com"));
		assertTrue(new EmailFieldEvaluation().isValid("Chuck Norris <gmail@chucknorris.com>"));
		assertTrue(new EmailFieldEvaluation().isValid("webmaster@müller.de"));
		assertTrue(new EmailFieldEvaluation().isValid("matteo@78.47.122.114"));
	}
}
