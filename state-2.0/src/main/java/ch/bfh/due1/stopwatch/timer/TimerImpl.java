/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.stopwatch.timer;

import java.util.ArrayList;

import ch.bfh.due1.stopwatch.core.Timer;


/**
 * An old implementation of the Timer and Ticker interface. Should be
 * redesigned.
 */
public class TimerImpl implements Runnable, Timer, Ticker {
	// Private members
	private static long DEFAULTRECHECKINTERVALL = 10; // milliseconds

	private static long DEFAULTRESOLUTION = 10; // milliseconds

	private long recheckInterval;

	private Thread me = null;

	private boolean proceed;

	private boolean suspended;

	private long tickCount;

	// Due to the call to the clone method the type and the implementation
	// of tickListeners are the same
	private ArrayList<TickListener> tickListeners = new ArrayList<TickListener>();

	// Properties
	private long resolution;

	/**
	 * Creates a timer instance.
	 */
	public TimerImpl() {
		resolution = DEFAULTRESOLUTION;
		recheckInterval = DEFAULTRECHECKINTERVALL;
		proceed = false;
		suspended = false;
		tickCount = 0;
		me = null;
	}

	/**
	 * Adjusts the resolution of the timer.
	 * 
	 * @param milliseconds
	 *            the new resolution
	 */
	public void setResolution(long milliseconds) {
		resolution = milliseconds;
	}

	/**
	 * Returns the current resolution.
	 * 
	 * @return a resolution, or the default if not previously changed
	 */
	public long getResolution() {
		return resolution;
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.stopwatch.core.Timer#timerReset()
	 */
	@Override
	public void timerReset() {
		// Stop running thread if any
		if (me != null)
			terminate();
		// Initialize new timer state
		proceed = false;
		suspended = false;
		tickCount = 0;
		me = null;
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.stopwatch.core.Timer#timerStartContinue()
	 */
	@Override
	public void timerStartContinue() {
		if (me == null) {
			proceed = true;
			suspended = false;
			me = new Thread(this);
			me.setDaemon(true);
			me.start();
		} else {
			release();
		}
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.stopwatch.core.Timer#timerStop()
	 */
	@Override
	public void timerStop() {
		if (me != null)
			hold();
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.stopwatch.timer.Ticker#addTickListener(ch.bfh.due1.stopwatch.timer.TickListener)
	 */
	@Override
	public void addTickListener(TickListener l) {
		tickListeners.add(l);
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.stopwatch.timer.Ticker#removeTickListener(ch.bfh.due1.stopwatch.timer.TickListener)
	 */
	@Override
	public void removeTickListener(TickListener l) {
		tickListeners.remove(l);
	}

	// Private behavior methods:
	private void terminate() {
		proceed = false;
	}

	private void hold() {
		suspended = true;
	}

	private synchronized void release() {
		suspended = false;
		this.notify();
	}

	private void tick() {
		// Count ticks until no. ticks equals resolution. If so
		// fire TickEvent
		tickCount++;
		if (tickCount == (resolution / recheckInterval)) {
			tickCount = 0;
			new TickEventNotifier();
		}
	}

	private class TickEventNotifier extends Thread {
		public TickEventNotifier() {
			this.start();
		}

		@Override
		public void run() {
			notifyTickEvent();
		}

		private void notifyTickEvent() {
			ArrayList<?> listeners = (ArrayList<?>) tickListeners.clone();
			TickEvent tick = new TickEvent(this);
			for (int i = 0; i < listeners.size(); i++) {
				((TickListener) listeners.get(i)).tickOccurred(tick);
			}
		}
	}

	@Override
	public void run() {
		while (proceed) {
			try {
				// Check first if thread must be suspended. If so, then
				// acquire the lock.
				// See:
				// ...jdk1.2/docs/guide/misc/threadPrimitiveDeprecation.html
				// FIXME: Double-locking should not be used with Java.
				if (suspended) {
					synchronized (this) {
						while (suspended) {
							this.wait();
						}
					}
				}
				long begin = System.currentTimeMillis();
				// Thread.sleep(_recheckInterval); // needs to be changed...
				this.tick();
				long end = System.currentTimeMillis();
				long timeToSleep = recheckInterval - (end - begin);
				if (timeToSleep > 0)
					Thread.sleep(timeToSleep);
			} catch (InterruptedException ex) {
				// Ooops. We're forced to terminate.
				return;
			}
		}
	}
}
