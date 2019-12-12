/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Software Engineering and Design -- Design patterns
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.strategy;

/**
 * Defines a field evaluator. Notice that the field evaluator must be configured
 * with a field evaluation.
 *
 * @author Eric Dubuis
 */
public class FieldEvaluator {

	private FieldEvaluation fe;

	/**
	 * Constructs a field evaluator having a default field evaluation.
	 */
	public FieldEvaluator() {
		this.fe = new DefaultFieldEvaluation();
	}

	/**
	 * Constructs a field evaluator having a provided field evaluation.
	 *
	 * @param fe
	 *            a field evaluation
	 */
	public FieldEvaluator(FieldEvaluation fe) {
		if (fe == null) {
			throw new IllegalArgumentException("Requires a FieldEvaluation instance.");
		}
		this.fe = fe;
	}

	/**
	 * Sets a field evaluation.
	 *
	 * @param fe a field evaluation to be used from now on
	 */
	public void setFieldEvaluation(FieldEvaluation fe) {
		if (fe == null) {
			throw new IllegalArgumentException("Requires a FieldEvaluation instance.");
		}
		this.fe = fe;
	}

	/**
	 * Evaluates a given string by using the configured field evaluator.
	 *
	 * @param s
	 *            evaluates the given string
	 * @return true if the given string matches the format as defined by the
	 *         field evaluation, false otherwise
	 */
	public boolean evaluate(String s) {
		return fe.isValid(s);
	}
}
