/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Software Engineering and Design -- Design patterns
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.strategy;

/**
 * This is the default field evaluation which does no evaluation at all.
 *
 * @author Eric Dubuis
 */
public class DefaultFieldEvaluation implements FieldEvaluation {

	/**
	 * Does not evaluate the given string. Returns always true.
	 *
	 * @see org.designpattern.strategy.FieldEvaluation#isValid(java.lang.String)
	 */
	@Override
	public boolean isValid(String fieldText) {
		return true;
	}
}
