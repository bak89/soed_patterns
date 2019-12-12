/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Software Engineering and Design -- Design patterns
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.strategy;

/**
 * @author Eric Dubuis
 */
public class DateFieldEvaluation implements FieldEvaluation {

	/**
	 * @see org.designpattern.strategy.FieldEvaluation#isValid(java.lang.String)
	 */
	@Override
	public boolean isValid(String fieldText) {
		// TODO -- implement an evaluation for dates
		// Hint: Use Java Regexp
		return false;
	}

}
