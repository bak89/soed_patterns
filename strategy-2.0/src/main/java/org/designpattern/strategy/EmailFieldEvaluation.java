/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Software Engineering and Design -- Design patterns
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.strategy;

/**
 * Implements a field evaluation for e-mail addresses.
 *
 * @author Eric Dubuis
 */
public class EmailFieldEvaluation implements FieldEvaluation {

	/**
	 * Given a string, this method returns true if and only if s represents an
	 * email address with the format <code>somestring@somecompoany.domain</code>
	 * .
	 *
	 * @param fieldText
	 *            a string
	 * @return true if the given string matches the format of an e-mail address,
	 *         false otherwise
	 */
	@Override
	public boolean isValid(String fieldText) {
		// TODO -- implement an evaluation for e-mail addresses
		// Hint: Use Java Regexp
		return false;
	}
}
