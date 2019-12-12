/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Software Engineering and Design -- Design patterns
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.strategy;

/**
 * Interface for an arbitrary field evaluation.
 *
 * @author Eric Dubuis
 */
public interface FieldEvaluation {
	/**
	 * If the given string matches the format as defined by a concrete
	 * implementation then true is returned, false otherwise.
	 *
	 * @param fieldText
	 *            a given string
	 * @return true if the given string matches the required format, false
	 *         otherwise
	 */
	public boolean isValid(String fieldText);
}
