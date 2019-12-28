/**
 * Copyright (C) 2017-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 * <p>
 * Software Engineering and Design
 */
package ch.bfh.due1.dp.template;

public class OnePlaceBuffer<E> extends Buffer<E> {
    //static final int EMPTY = 0;
    //static final int FULL = 1;
    private E item;
    // done Introduce a variable holding an item E

    @Override
    protected synchronized void store(E x) {
        // done Complete
        item = x;
    }

    @Override
    protected synchronized E retrieve() {
        // done Complete
        return item;
    }

    @Override
    protected synchronized void checkState() {
        // For every case the buffer's state is changed
        // so we notify ALL waiting threads.
        if (state == EMPTY)
            state = FULL;
        else
            state = EMPTY;

        notifyAll();
    }
}
