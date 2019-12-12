/**
 * Copyright (C) 2017-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.dp.template;

import java.util.List;

public class UnboundedBuffer<E> extends Buffer<E> {
	// TODO Initialize a (conceptually infinite) list.
	private List<E> buffer = null;

	static final int NOT_EMPTY = 2;

	@Override
	protected synchronized void store(E x) {
		// TODO Complete

	}

	@Override
	protected synchronized E retrieve() {
		// TODO Complete

		return null;
	}

	@Override
	protected synchronized void checkState() {
		int oldState = state;

		if (buffer.size() == 0)
			state = EMPTY;
		else
			state = NOT_EMPTY;

		// Notify when leaving EMPTY state.
		if (state != oldState && oldState == EMPTY)
			notifyAll();
	}
}
