/**
 * Copyright (C) 2017-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.dp.template;

public class BoundedBuffer<E> extends Buffer<E> {
	private static final int HAS_ITEMS = 2;
	private int usedSlots = 0;

	// TODO Complete
	private Object[] buffer;

	@Override
	public void init(int size) throws IllegalArgumentException {
		if (size <= 0)
			throw new IllegalArgumentException();
		// TODO Complete

	}

	@Override
	protected synchronized void store(Object x) {
		// TODO Complete

	}

	@Override
	@SuppressWarnings("unchecked")
	protected synchronized E retrieve() {
		// TODO Complete

		return null;
	}

	@Override
	protected synchronized void checkState() {
		int oldState = state;

		if (usedSlots == 0)
			state = EMPTY;
		else if (usedSlots == buffer.length)
			state = FULL;
		else
			state = HAS_ITEMS;

		// Notify when leaving EMPTY or FULL states.
		if (state != oldState && (oldState == EMPTY || oldState == FULL))
			notifyAll();
	}
}
