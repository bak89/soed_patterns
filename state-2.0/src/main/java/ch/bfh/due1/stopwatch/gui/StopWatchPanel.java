/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.stopwatch.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.bfh.due1.stopwatch.core.Display;
import ch.bfh.due1.stopwatch.timer.TickEvent;
import ch.bfh.due1.stopwatch.timer.TickListener;


/**
 * A Swing implementation of the Display interface.
 */
public class StopWatchPanel extends JPanel implements TickListener, Display {

	private static final long serialVersionUID = 1L;

	private JLabel label;

	boolean timerRunning = false, displayIntermediate = false;

	boolean x = true;

	private int hundredthseconds = 0;

	private int tenthseconds = 0;

	private int seconds = 0;

	private int minutes = 0;

	private int hours = 0;

	private boolean displayHundredths = false;

	private Thread blinker = null;

	public StopWatchPanel() {
		initComponents();
	}

	private void initComponents() {
		label = new JLabel("00:00:00.00");
		label.setFont(new Font("SansSerif", Font.BOLD, 18));
		add(label);
	}

	private void computeTimerValue() {
		if (((hundredthseconds + 1) / 10) > 0) {
			hundredthseconds = 0;
			if (((tenthseconds + 1) / 10) > 0) {
				tenthseconds = 0;
				if (((seconds + 1) / 60) > 0) {
					seconds = 0;
					if (((minutes + 1) / 60) > 0) {
						minutes = 0;
						if (((hours + 1) / 24) > 0) {
							hours = 0;
						} else {
							hours++;
						}
					} else {
						minutes++;
					}
				} else {
					seconds++;
				}
			} else {
				tenthseconds++;
			}
		} else {
			hundredthseconds++;
		}
		if (hundredthseconds == 0 && !this.displayIntermediate) {
			displayTimerValue();
		}
	}

	private void displayTimerValue() {
		String newValue = (hours < 10 ? "0" + Integer.toString(hours) : Integer
				.toString(hours))
				+ ":"
				+ (minutes < 10 ? "0" + Integer.toString(minutes) : Integer
						.toString(minutes))
				+ ":"
				+ (seconds < 10 ? "0" + Integer.toString(seconds) : Integer
						.toString(seconds))
				+ "."
				+ Integer.toString(tenthseconds)
				+ (displayHundredths ? Integer.toString(hundredthseconds) : "0");
		label.setText(newValue);
	}

	// Implementation of Display interface:
	@Override
	public void displayReset() {
		timerRunning = false;
		displayIntermediate = false;
		hundredthseconds = 0;
		tenthseconds = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
		displayHundredths = true;
		displayTimerValue();
	}

	@Override
	public void displayRunningTime() {
		timerRunning = true;
		displayIntermediate = false;
		displayHundredths = false;
		displayTimerValue();
	}

	@Override
	public void displayIntermediateTime() {
		timerRunning = true;
		displayIntermediate = true;
		displayHundredths = true;
		displayTimerValue();
	}

	@Override
	public void displayFinalTime() {
		timerRunning = false;
		displayIntermediate = false;
		displayHundredths = true;
		displayTimerValue();
	}

	@Override
	public void doBlinking() {
		blinkDisplay(true);
	}

	@Override
	public void stopBlinking() {
		blinkDisplay(false);
	}

	// Blinking:
	private void blinkDisplay(boolean blink) {
		if (blink) {
			if (blinker == null) {
				blinker = new Thread() {
					@Override
					public void run() {
						while (!this.isInterrupted()) {
							toggleDisplay();
							try {
								sleep(500);
							} catch (InterruptedException ex) {
								// They want us to terminate.
								return;
							}
						}
					}
				};
				blinker.start();
			}
		} else if (blinker != null) {
			blinker.interrupt();
			blinker = null;
			x = true;
			label.setVisible(x);
		}
	}

	private void toggleDisplay() {
		x = x ? false : true;
		label.setVisible(x);
	}

	// Implementation of TickListener interface:
	@Override
	public void tickOccurred(TickEvent e) {
		if (timerRunning) {
			computeTimerValue();
		}
	}
}
