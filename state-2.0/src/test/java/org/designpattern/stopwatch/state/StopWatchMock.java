/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package org.designpattern.stopwatch.state;

import ch.bfh.due1.stopwatch.core.Button;
import ch.bfh.due1.stopwatch.core.Display;
import ch.bfh.due1.stopwatch.core.State;
import ch.bfh.due1.stopwatch.core.StateFactory;
import ch.bfh.due1.stopwatch.core.StopWatch;
import ch.bfh.due1.stopwatch.core.Timer;

public class StopWatchMock extends StopWatch {
	// Helper state information.
	private enum DisplayStatus {
		Intermediate, Running, Stopped, Zero
	}

	private enum BlinkStatus {
		Blinking, Bright
	}

	private enum TimerStatus {
		TimerReset, TimerStarted, TimerStopped
	}

	// Helper instance variables for testing purposes.
	private DisplayStatus displayStatus = DisplayStatus.Zero;
	private BlinkStatus blinkStatus = BlinkStatus.Bright;
	private TimerStatus timerStatus = TimerStatus.TimerReset;

	public StopWatchMock(StateFactory fac, Timer timer, Display display, Button b1, Button b2) throws Exception {
		super(fac, timer, display, b1, b2);
	}

	protected State getState() {
		return state;
	}

	@Override
	protected void resetTimer() {
		super.resetTimer();
		timerStatus = TimerStatus.TimerReset;
	}

	/**
	 * Starts the associated timer.
	 */
	@Override
	protected void startTimer() {
		// Send timerStartContinue to timer:
		super.startTimer();
		timerStatus = TimerStatus.TimerStarted;
	}

	/**
	 * Stops (but not resets) the associated timer.
	 */
	@Override
	protected void stopTimer() {
		super.stopTimer();
		timerStatus = TimerStatus.TimerStopped;
	}

	/**
	 * Resets the associated display.
	 */
	@Override
	protected void displayZero() {
		super.displayZero();
		displayStatus = DisplayStatus.Zero;
	}

	/**
	 * Displays the running time on the associated display.
	 */
	@Override
	protected void displayRunningTime() {
		super.displayRunningTime();
		displayStatus = DisplayStatus.Running;
	}

	/**
	 * Displays the intermediate time on the associated display.
	 */
	@Override
	protected void displayIntermediateTime() {
		super.displayIntermediateTime();
		displayStatus = DisplayStatus.Intermediate;
	}

	/**
	 * Displays the stopped time on the associated display.
	 */
	@Override
	protected void displayStoppedTime() {
		super.displayStoppedTime();
		displayStatus = DisplayStatus.Stopped;
	}

	/**
	 * Starts the blinking of the display.
	 */
	@Override
	protected void doBlinking() {
		super.doBlinking();
		blinkStatus = BlinkStatus.Blinking;
	}

	/**
	 * Stops the blinking of the display.
	 */
	@Override
	protected void stopBlinking() {
		super.stopBlinking();
		blinkStatus = BlinkStatus.Bright;
	}

	// Needed here since same method in super class is not public.
	@Override
	public void setState(State s) {
		super.setState(s);
	}

	protected boolean isDisplayingIntermediateTime() {
		return displayStatus == DisplayStatus.Intermediate;
	}

	protected boolean isDisplayingRunningTime() {
		return displayStatus == DisplayStatus.Running;
	}

	protected boolean isDisplayingStoppedTime() {
		return displayStatus == DisplayStatus.Stopped;
	}

	protected boolean isDisplayingZero() {
		return displayStatus == DisplayStatus.Zero;
	}

	protected boolean isBlinking() {
		return blinkStatus == BlinkStatus.Blinking;
	}

	protected boolean isTimerReset() {
		return timerStatus == TimerStatus.TimerReset;
	}

	protected boolean isTimerStarted() {
		return timerStatus == TimerStatus.TimerStarted;
	}

	protected boolean isTimerStopped() {
		return timerStatus == TimerStatus.TimerStopped;
	}
}
