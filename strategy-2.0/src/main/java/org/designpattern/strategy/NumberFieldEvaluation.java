/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Software Engineering and Design -- Design patterns
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.strategy;

/**
 * Implements a field evaluation for numbers.
 *
 * @author Eric Dubuis
 */
public class NumberFieldEvaluation implements FieldEvaluation {
	/**
	 * Given a string s, this method returns true if and
	 * only if s represents a number.
	 */
	@Override
	public boolean isValid(String fieldText)
	{
		// TODO -- implement an evaluation for dates
		return false;
	}
}
