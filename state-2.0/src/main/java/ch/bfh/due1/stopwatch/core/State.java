/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.stopwatch.core;

/**
 * The abstraction of all states for the stop watch. Provides a reference to the
 * stop watch. Provides the event methods being called from the context.
 * Provides Provides the actions delegated to the stop watch. Actions should be
 * called from subclasses accordingly.
 */
public abstract class State {
	/**
	 * Reference to the context.
	 */
	protected StopWatch sw;

	/**
	 * Initializes the reference to the context. Must be called first.
	 * 
	 * @param sw
	 *            a context
	 */
	public void init(StopWatch sw) {
		this.sw = sw;
	}

	// ************** EVENT calls *****************
	/**
	 * Event 'b1' handled by this state, default (null) implementation.
	 * Subclasses may override this method.
	 */
	public void handleB1() throws Exception {
	}

	/**
	 * Event 'b2' handled by this state, default (null) implementation.
	 * Subclasses may override this method.
	 */
	public void handleB2() throws Exception {
	}

	// ************** ENTER/DO/EXIT calls *****************
	/**
	 * Called from the state's context upon entering this state.
	 */
	public final void enter() {
		doEnter();
		startDo();
	}

	/**
	 * Called from the state's context upon leaving this state.
	 */
	public final void exit() {
		stopDo();
		doExit();
	}

	// ************** ENTER/DO/EXIT templates *****************
	/**
	 * Default (null) implementation of the enter action to be carried out by
	 * this state. Executed at the end of a transition. Concrete subclasses may
	 * override this method if necessary.
	 */
	protected void doEnter() {
	}

	/**
	 * Default (null) implementation of the exit action to be carried out by
	 * this state. Executed at the beginning of a transition. Concrete
	 * subclasses may override this method if necessary.
	 */
	protected void doExit() {
	}

	/**
	 * Default (null) implementation of the starting of the do action to be
	 * carried out by this state. Executed at the beginning of a transition,
	 * after the doEnter() method. Concrete subclasses may override this method
	 * if necessary.
	 */
	protected void startDo() {
	}

	/**
	 * Default (null) implementation of the ending of the do action to be
	 * carried out by this state. Executed at the ending of a transition, before
	 * the doExit() method. Concrete subclasses may override this method if
	 * necessary.
	 */
	protected void stopDo() {
	}

	// ************** ACTIONS *****************
	/**
	 * This action resets the associated timer of the stop watch.
	 */
	protected void resetTimer() {
		this.sw.resetTimer();
	}

	/**
	 * This action starts the associated timer of the stop watch.
	 */
	protected void startTimer() {
		this.sw.startTimer();
	}

	/**
	 * This action stops the associated timer of the stop watch.
	 */
	protected void stopTimer() {
		this.sw.stopTimer();
	}

	/**
	 * This action sets the associated stop watch display to zero.
	 */
	protected void displayZero() {
		this.sw.displayZero();
	}

	/**
	 * This action shows the running time on the associated stop watch display.
	 */
	protected void displayRunningTime() {
		this.sw.displayRunningTime();
	}

	/**
	 * This action shows the intermediate time on the associated stop watch
	 * display.
	 */
	protected void displayIntermediateTime() {
		this.sw.displayIntermediateTime();
	}

	/**
	 * This action shows the stopped time on the associated stop watch display.
	 */
	protected void displayStoppedTime() {
		this.sw.displayStoppedTime();
	}

	/**
	 * This action blinks stop watch display.
	 */
	protected void doBlinking() {
		this.sw.doBlinking();
	}

	/**
	 * This action stops the blinking of the stop watch display.
	 */
	protected void stopBlinking() {
		this.sw.stopBlinking();
	}

	/**
	 * Sets the text on the button 1's label.
	 * 
	 * @param text
	 *            a text
	 */
	protected void setButton1Text(String text) {
		this.sw.setButton1Text(text);
	}

	/**
	 * Sets the text on the button 2's label.
	 * 
	 * @param text
	 *            a text
	 */
	protected void setButton2Text(String text) {
		this.sw.setButton2Text(text);
	}

	// ************** STATE SETTERS *****************
	/**
	 * Sets the new state on the context to be the Intermediate state.
	 */
	protected void setIntermediateState() throws Exception {
		State s = this.sw.getStateFactory().createIntermediateState(this.sw);
		this.sw.setState(s);
	}

	/**
	 * Sets the new state on the context to be the Running state.
	 */
	protected void setRunningState() throws Exception {
		State s = this.sw.getStateFactory().createRunningState(this.sw);
		this.sw.setState(s);
	}

	/**
	 * Sets the new state on the context to be the Stopped state.
	 */
	protected void setStoppedState() throws Exception {
		State s = this.sw.getStateFactory().createStoppedState(this.sw);
		this.sw.setState(s);
	}

	/**
	 * Sets the new state on the context to be the Zero state.
	 */
	protected void setZeroState() throws Exception {
		State s = this.sw.getStateFactory().createIdleState(this.sw);
		this.sw.setState(s);
	}
}
