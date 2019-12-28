/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package org.designpattern.stopwatch.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ch.bfh.due1.stopwatch.core.Button;
import ch.bfh.due1.stopwatch.core.Display;
import ch.bfh.due1.stopwatch.core.StateFactory;
import ch.bfh.due1.stopwatch.core.Timer;

public class IntermediateTest {
	private StopWatchMock stopWatch;
	private StateFactory stateFactory;

	// Mock for Timer.
	private Timer timer = new Timer() {
		@Override
		public void timerReset() {
		}
		@Override
		public void timerStartContinue() {
		}
		@Override
		public void timerStop() {
		}
	};

	private Display display = new Display() {
		@Override
		public void displayReset() {
		}
		@Override
		public void displayRunningTime() {
		}
		@Override
		public void displayIntermediateTime() {
		}
		@Override
		public void displayFinalTime() {
		}

		@Override
		public void doBlinking() {
		}
		@Override
		public void stopBlinking() {
		}
	};

	private Button b1 = new Button() {
		@Override
		public void setText(String string) {
		}
	};

	private Button b2 = new Button() {
		@Override
		public void setText(String string) {
		}
	};

	@Before
	public void setUp() throws Exception {
		stateFactory = new StateFactoryImpl();
		stopWatch = new StopWatchMock(stateFactory, timer, display, b1, b2);
	}

	@Test
	public void testHandleB1() throws Exception {
		stopWatch.setState(stateFactory.createIntermediateState(stopWatch));
		stopWatch.button1Pressed();
		assertEquals(stateFactory.createRunningState(stopWatch), stopWatch.getState());
		assertTrue(stopWatch.isDisplayingRunningTime());
	}

	@Test
	public void testHandleB2() throws Exception {
		stopWatch.setState(stateFactory.createIntermediateState(stopWatch));
		stopWatch.button2Pressed();
		assertEquals(stateFactory.createStoppedState(stopWatch), stopWatch.getState());
		assertTrue(stopWatch.isTimerStopped());
		assertTrue(stopWatch.isDisplayingStoppedTime());
	}
}
