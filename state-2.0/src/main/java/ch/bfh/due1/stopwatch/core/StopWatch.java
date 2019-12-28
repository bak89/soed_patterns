/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.stopwatch.core;

/**
 * The context of the state pattern denoting a stop watch those behavior depends
 * on its current state.
 */
public class StopWatch {
	private StateFactory fac;

	private Button b1;

	private Button b2;

	private Timer timer;

	private Display display;

	protected State state;

	/**
	 * Creates the common part of a context.
	 *
	 * @param fac
	 *            a factory for creating new states
	 * @param timer
	 *            a timer to manipulate
	 * @param display
	 *            a display to update
	 * @param b1
	 *            abstract button 1
	 * @param b2
	 *            abstract button 2
	 * @throws Exception
	 *             if there is an initialization error
	 */
	public StopWatch(StateFactory fac, Timer timer, Display display, Button b1, Button b2)
			throws Exception {
		this.fac = fac;
		this.timer = timer;
		this.display = display;
		this.b1 = b1;
		this.b2 = b2;
		this.state = fac.createIdleState(this);
		this.state.enter();
	}

	/**
	 * Handles the event generated upon pressing button 'b1'.
	 */
	public void button1Pressed() throws Exception {
		this.state.exit();
		this.state.handleB1();
		this.state.enter();
	}

	/**
	 * Handles the event generated upon pressing button 'b2'.
	 */
	public void button2Pressed() throws Exception {
		this.state.exit();
		state.handleB2();
		this.state.enter();
	}

	// call-back (helper) for the state classes
	/**
	 * Returns the state factory instance.
	 *
	 * @return an instance of the state factory
	 */
	StateFactory getStateFactory() {
		return this.fac;
	}

	// Called from State classes. Can also be called from a mock subclass introduced
	// for testing purposes only.

	/**
	 * Resets the associated timer.
	 */
	protected void resetTimer() {
		// Send timerReset to timer:
		timer.timerReset();
	}

	/**
	 * Starts the associated timer.
	 */
	protected void startTimer() {
		// Send timerStartContinue to timer:
		timer.timerStartContinue();
	}

	/**
	 * Stops (but not resets) the associated timer.
	 */
	protected void stopTimer() {
		// Send timerStop to timer:
		timer.timerStop();
	}

	/**
	 * Resets the associated display.
	 */
	protected void displayZero() {
		// Send displayReset to display:
		display.displayReset();
	}

	/**
	 * Displays the running time on the associated display.
	 */
	protected void displayRunningTime() {
		// Send displayRunningTime to display:
		display.displayRunningTime();
	}

	/**
	 * Displays the intermediate time on the associated display.
	 */
	protected void displayIntermediateTime() {
		// Send displayIntermediateTime to display:
		display.displayIntermediateTime();
	}

	/**
	 * Displays the stopped time on the associated display.
	 */
	protected void displayStoppedTime() {
		// Send displayFinalTime to display:
		display.displayFinalTime();
	}

	/**
	 * Starts the blinking of the display.
	 */
	protected void doBlinking() {
		// Send doBlinking to display:
		display.doBlinking();
	}

	/**
	 * Stops the blinking of the display.
	 */
	protected void stopBlinking() {
		// Send stopBlinking to display:
		display.stopBlinking();
	}

	/**
	 * Sets the text on button 1.
	 */
	protected void setButton1Text(String text) {
		this.b1.setText("B1: " + text);
	}

	/**
	 * Sets the text on button 2.
	 */
	protected void setButton2Text(String text) {
		this.b2.setText("B2: " + text);
	}

	/**
	 * Sets the current state.
	 */
	protected void setState(State s) {
		this.state = s;
	}
}
