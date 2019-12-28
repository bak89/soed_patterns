/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package org.designpattern.stopwatch.state;

import ch.bfh.due1.stopwatch.core.State;

/**
 * Initial state displaying 00.00 in the display and having set the labels of
 * the buttons 'b1' and 'b2' accordingly.
 */
public class DummyState extends State {
	/**
	 * Text on button 1, if being in this state.
	 */
	public final static String START_TEXT = "Replace this state...";

	/**
	 * Text on button 2, if being in this state.
	 */
	public final static String STOP_TEXT = "Replace this state...";

	/**
	 * Sets the display to zero, and sets the texts of 'b1' and 'b2'.
	 *
	 * @see ch.bfh.due1.stopwatch.core.State#doEnter()
	 */
	@Override
	protected void doEnter() {
		displayZero();
		setButton1Text(START_TEXT);
		setButton2Text(STOP_TEXT);
	}

	/**
	 * Does nothing...
	 *
	 * @see ch.bfh.due1.stopwatch.core.State#handleB1()
	 */
	@Override
	public void handleB1() throws Exception {
		// empty...
	}

	/**
	 * Does nothing...
	 *
	 * @see ch.bfh.due1.stopwatch.core.State#handleB1()
	 */
	@Override
	public void handleB2() throws Exception {
		// empty...
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof DummyState;
	}
}
