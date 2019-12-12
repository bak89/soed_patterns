/**
 * Copyright (C) 2017-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 * <p>
 * Software Engineering and Design
 */
package ch.bfh.due1.dp.template;

public abstract class Buffer<E> {

    static final int EMPTY = 0;
    static final int FULL = 1;
    protected int state = EMPTY;

    public void init(int size) throws IllegalArgumentException {
        //only bounded buff only set allow
    }

    public final synchronized void put(E x) throws InterruptedException {
        if (state != FULL) {
            //add item end buffer
            store(x);
        } else {
            //block
            awaitPutable();
        }

        checkState();
    }

    public final synchronized E get() throws InterruptedException {
        E res = null;
        if (state != EMPTY) {
            //remove item
            res = retrieve();
        } else {
            //block
            awaitGetable();
        }


        checkState();
        return res;
    }

    protected abstract void store(E x);

    protected abstract E retrieve();

    private final synchronized void awaitPutable() throws InterruptedException {
        while (state == FULL) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw e;
            }
        }
    }

    private final synchronized void awaitGetable() throws InterruptedException {
        while (state == EMPTY) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw e;
            }
        }
    }

    protected abstract void checkState();
}
